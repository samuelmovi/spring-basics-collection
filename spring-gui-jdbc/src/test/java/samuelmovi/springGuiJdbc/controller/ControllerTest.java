package samuelmovi.springGuiJdbc.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samuelmovi.springGuiJdbc.model.Employee;
import samuelmovi.springGuiJdbc.model.EmployeeDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTest {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    private Controller controller;


    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    private static boolean firstRun = true;

    @Before
    public void before(){
        employeeDao.deleteAll();
        // FEED DATABASE
        System.out.println("[CtrlTest] Feeding database...");
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }
        if (firstRun){
            controller.init();
            firstRun = false;
        }
    }

    @After
    public void after(){
        // EMPTY DATABASE
        System.out.println("[CtrlTest] Emptying database...");
        employeeDao.deleteAll();
    }

    @Test
    public void testPopulate(){
        // check db count
        long before = employeeDao.findAll().size();
        // execute method
        controller.populate();
        // assert expected results
        Assert.assertEquals(before + 5, employeeDao.findAll().size());
    }

    @Test
    public void testCreateNew(){
        // check number of instances in db table
        long before = controller.getEmployeeDao().findAll().size();
        // execute method
        controller.createNew("last", "first");
        // assert new number of instances in db table
        Assert.assertEquals(before+1, controller.getEmployeeDao().findAll().size());
        // assert field values are as expected
        Assert.assertNotNull(controller.getEmployeeDao().findByField("last_name", "LAST"));
        Assert.assertTrue(controller.getEmployeeDao().findByField("first_name","asdfq123").isEmpty());
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
        controller.selectedEmployee(testTable);
        // assert expected result
        Assert.assertEquals(testValue, controller.getOperatorID());

    }

    @Test
    public void testDeleteOperative(){
        // set value for controller.operatorID
        Employee lastOperator = controller.getEmployeeDao().findAll().get(0);
        controller.setOperatorID(String.valueOf(lastOperator.getId()));
        // save number of instances in database table
        long before = controller.getEmployeeDao().findAll().size();
        // execute method
        controller.deleteOperative();
        // assert database table has one less instance
        long after = controller.getEmployeeDao().findAll().size();
        Assert.assertEquals(before-1, after);
    }

    @Test
    public void testDeactivateOperative(){
        // set value for controller.operatorID
        Employee lastEmployee = controller.getEmployeeDao().findByActive(true).get(0);
        controller.setOperatorID(String.valueOf(lastEmployee.getId()));
        // save number of active operatives in database table
        long before = controller.getEmployeeDao().findByActive(true).size();
        // execute method
        controller.deactivateOperative();
        // assert database table has one less active operative
        long after = controller.getEmployeeDao().findByActive(true).size();
        Assert.assertEquals(before-1, after);
    }

}
