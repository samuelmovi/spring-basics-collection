package samuelmovi.bootGuiJpa.controller;

import org.junit.Assert;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import samuelmovi.bootGuiJpa.model.Operator;
import samuelmovi.bootGuiJpa.repo.OperatorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import samuelmovi.bootGuiJpa.view.View;

import java.util.Optional;

//@Import(TestConfig.class)
@DataJpaTest
@RunWith(SpringRunner.class)
public class ControllerTest {

    @Autowired
    OperatorRepository operatorRepository;
    // @Autowired
    Controller controller; // = new Controller();
    @MockBean
    View view;

    boolean firstRun = true;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Before
    public void before(){
        // setup controller
        this.controller = new Controller();
        this.controller.setOperatorRepository(operatorRepository);
        this. controller.setView(view);

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

    // test populate

    // test setID

    @Test
    public void testDeleteOperative(){
        // capture current total operatives
        long before = operatorRepository.count();
        // set operativeID
        Optional<Operator> optional = operatorRepository.findByFirstName(
                operatorRepository.findAll().get(0).getFirstName()
        );
        if (optional.isPresent()){
            Operator operator = optional.get();
            // execute function
            controller.deleteOperative(operator.getId());
        }
        // test result
        Assert.assertEquals(before -1 , operatorRepository.count());

    }

    @Test
    public void testDeactivateOperative(){
        // capture current total operatives
        long before = operatorRepository.findAllByActive(true).size();
        // set operativeID
        Optional<Operator> optional = operatorRepository.findByFirstName(
                operatorRepository.findAll().get(0).getFirstName()
        );
        if (optional.isPresent()){
            Operator operator = optional.get();
            // execute function
            controller.deactivateOperative(operator.getId());
        }
        // test result
        Assert.assertEquals(before - 1, operatorRepository.findAllByActive(true).size());
    }

    // test refreshModels

    //

    // @Test
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
    }

}
