package samuelmovi.bootGuiJpa.controller;

import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import samuelmovi.bootGuiJpa.model.Operator;
import samuelmovi.bootGuiJpa.repo.OperatorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import samuelmovi.bootGuiJpa.view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ControllerTest {

    @Autowired
    private OperatorRepository operatorRepository;

    private Controller controller;

    private View mockView;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Before
    public void before(){
        // setup mocks
        mockView = Mockito.mock(View.class);

        // setup controller
        this.controller = new Controller();
        this.controller.setOperatorRepository(operatorRepository);
        this. controller.setView(mockView);

        // FEED DATABASE
        System.out.println("[CtrlTest] Feeding database...");
        for (String[] data: employeeData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }
    }

    @After
    public void after(){
        // EMPTY DATABASE
        System.out.println("[CtrlTest] Emptying database...");
        operatorRepository.deleteAll();
    }

    @Test
    public void testPopulate(){
        // check db count
        long before = operatorRepository.count();
        // execute method
        controller.populate();
        // assert expected results
        Assert.assertEquals(before + 5, operatorRepository.count());
    }

    @Test
    public void testCreateNew(){
        // check number of instances in db table
        long before = operatorRepository.count();
        // execute method
        controller.createNew("first", "last");
        // assert new number of instances in db table
        Assert.assertEquals(before+1, operatorRepository.count());
        // assert field values are as expected
        Assert.assertNotNull(operatorRepository.findByLastNameAllIgnoringCase("LAST"));
        Assert.assertTrue(operatorRepository.findByFirstNameAllIgnoringCase("asdfq123").isEmpty());
        Mockito.verify(mockView, Mockito.times(1)).clearNewOpFields();
    }

    @Test
    public void testSetId(){
        String testValue = "33";
        // create table model with one row, and one column
        DefaultTableModel testModel = new DefaultTableModel();
        testModel.setColumnCount(1);
        testModel.setRowCount(1);
        testModel.setValueAt(testValue, 0, 0);
        // create JTable
        JTable testTable = new JTable(testModel);
        // set row as active
        testTable.setRowSelectionInterval(0,0);
        // execute method
        controller.setID(testTable);
        // assert expected result
        Assert.assertEquals(testValue, controller.getOperatorID());

    }

    @Test
    public void testDeleteOperative(){
        // capture current total operatives
        long before = operatorRepository.count();
        // set operativeID
        Optional<Operator> optional = operatorRepository.findByFirstName(
                operatorRepository.findAll().get(0).getFirstName()
        );
        Assert.assertTrue(optional.isPresent());
        Operator operator = optional.get();
        // execute function
        controller.deleteOperative(operator.getId());

        // test result
        Assert.assertEquals(before -1 , operatorRepository.count());
        // assert no operator exists with id
        Assert.assertFalse(operatorRepository.findById(operator.getId()).isPresent());

    }

    @Test
    public void testDeactivateOperative(){
        // set value for controller.operatorID
        Operator lastOperator = controller.getOperatorRepository().findAllByActive(true).get(0);
        // save number of active operatives in database table
        long before = controller.getOperatorRepository().findAllByActive(true).size();

        // execute method
        controller.deactivateOperative(lastOperator.getId());

        // assert database table has one less active operative
        long after = controller.getOperatorRepository().findAllByActive(true).size();
        Assert.assertEquals(before-1, after);
        Optional<Operator> optional = operatorRepository.findById(lastOperator.getId());
        Assert.assertTrue(optional.isPresent());
        Assert.assertFalse(optional.get().isActive());
    }



}
