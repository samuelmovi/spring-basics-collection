package samuelmovi.springGuiJpa.controller;

import org.junit.Assert;
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

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTest {

    @Autowired
    OperatorRepository operatorRepository;
    @Autowired
    Controller controller;
    @Autowired
    View view;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Before
    public void before(){
        // FEED DATABASE
        System.out.println("[CtrlTest] Feeding database...");
        for (String[] data: employeeData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }
        controller.init();
    }

    @After
    public void after(){
        // EMPTY DATABASE
        System.out.println("[CtrlTest] Emptying database...");
        operatorRepository.deleteAll();
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
        Assert.assertNotNull(controller.getOperatorRepository().findByLastNameAllIgnoringCase("LAST"));
        Assert.assertTrue(controller.getOperatorRepository().findByFirstNameAllIgnoringCase("asdfq123").isEmpty());
    }

    // @Test
    public void testSetId(){
        // create JTable with one row, and one column

        // set value to known number

        // set row as active

        // execute method

        // assert expected result


    }

    // @Test
    public void testDeleteOperative(){
        // set value for controller.operatorID

        // save number of instances in database table

        // execute method

        // assert database table has one less instance

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
    }

    // @Test
    public void testRefreshModels(){

    }


}
