package samuelmovi.springCliJdbc.controller;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samuelmovi.springCliJdbc.model.Employee;
import samuelmovi.springCliJdbc.model.EmployeeDao;
import samuelmovi.springCliJdbc.view.View;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTest {

    View mockView;
    @Mock
    Object mockInput;
    @Autowired
    Controller controller;
    @Autowired
    private EmployeeDao employeeDao;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Before
    public void before(){

        mockView = Mockito.mock(View.class);

        controller.setView(mockView);
        controller.setInput((Scanner)mockInput);
        controller.setDone(true);
        // FEED DATABASE
        System.out.println("[#] Feeding database...");
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }
    }

    @After
    public void after(){
        employeeDao.execute("truncate table employees");
        System.out.println("[#] Cleanup...");
    }

    @Test
    public void testRun(){
        // test loop execution
        System.out.println("[#] test show menu ...");
        // prepare input
        String input = "0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // run controller
        controller.setDone(true);
        controller.run();
        // check results
        Mockito.verify(mockView, Mockito.times(1)).showMenu();
    }

    // @Test
    public void testShowAll(){
        // test show all operators
        System.out.println("[#] test show all operators ...");
        // prepare input
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // run controller
        controller.setDone(true);
        controller.run();
        // check results
        Mockito.verify(mockView, Mockito.times(1)).showMenu();
        Mockito.verify(mockView, Mockito.times(1)).showAllEmployees(employeeDao.findAll());
    }

    // @Test
    public void testShowAllActive(){
        // test show all operators
        System.out.println("[#] test show all active operators...");
        Mockito.verify(mockView, Mockito.times(1)).showActiveEmployees(employeeDao.findAllActive());
    }


}
