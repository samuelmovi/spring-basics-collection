package samuelmovi.springGuiJpa.controller;

import org.junit.Assert;
import org.mockito.Mockito;
import samuelmovi.springGuiJpa.model.Operator;
import samuelmovi.springGuiJpa.repo.OperatorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samuelmovi.springGuiJpa.view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Optional;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
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

    // private static boolean firstRun = true;

    @Before
    public void before(){
        operatorRepository.deleteAll();
        // FEED DATABASE
        for (String[] data: employeeData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }

        mockView = Mockito.mock(View.class);
        controller = new Controller();
        controller.setView(mockView);
        controller.setOperatorRepository(operatorRepository);
    }

    @After
    public void after(){

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
        long before = controller.getOperatorRepository().count();
        // execute method
        controller.createNew("last", "first");
        // assert new number of instances in db table
        Assert.assertEquals(before+1, controller.getOperatorRepository().count());
        // assert field values are as expected
        Assert.assertNotNull(operatorRepository.findByLastNameAllIgnoringCase("LAST"));
        Assert.assertTrue(operatorRepository.findByFirstNameAllIgnoringCase("asdfq123").isEmpty());
        Mockito.verify(mockView, Mockito.times(1)).clearNewOperatorFields();
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
        // set value for controller.operatorID
        Operator lastOperator = controller.getOperatorRepository().findAll().get(0);
        controller.setOperatorID(String.valueOf(lastOperator.getId()));
        // save number of instances in database table
        long before = controller.getOperatorRepository().findAll().size();
        // execute method
        controller.deleteOperative();
        // assert database table has one less instance
        long after = controller.getOperatorRepository().findAll().size();
        Assert.assertEquals(before-1, after);
        // assert no operator exists with id
        Assert.assertFalse(operatorRepository.findById(lastOperator.getId()).isPresent());
    }

    @Test
    public void testDeactivateOperative(){
        // set value for controller.operatorID
        Operator lastOperator = controller.getOperatorRepository().findByActive(true).get(0);
        controller.setOperatorID(String.valueOf(lastOperator.getId()));
        // save number of active operatives in database table
        long before = controller.getOperatorRepository().findByActive(true).size();
        // execute method
        controller.deactivateOperative();
        // assert database table has one less active operative
        long after = controller.getOperatorRepository().findByActive(true).size();
        Assert.assertEquals(before-1, after);
        Optional<Operator> optional = operatorRepository.findById(lastOperator.getId());
        Assert.assertTrue(optional.isPresent());
        Operator operator = optional.get();
        Assert.assertFalse(operator.isActive());
    }

}
