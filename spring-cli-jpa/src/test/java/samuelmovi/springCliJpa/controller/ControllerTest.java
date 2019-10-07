package samuelmovi.springCliJpa.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samuelmovi.springCliJpa.model.Operator;
import samuelmovi.springCliJpa.model.OperatorRepository;
import samuelmovi.springCliJpa.view.View;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;


@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTest {

    @Autowired
    private OperatorRepository operatorRepository;
    View mockView;
    //@Mock
    //private Scanner mockInput = new Scanner(System.in);
    @Autowired
    Controller controller;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Before
    public void before(){
        mockView = Mockito.mock(View.class);
        //mockInput = Mockito.mock(Scanner.class);
        controller.setView(mockView);
        //controller.setInput((Scanner)mockInput);
        controller.setOperatorRepository(operatorRepository);
        controller.setDone(true);
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
        // Mockito.when(mockInput.nextLine()).then(invocationOnMock -> 1);

        // run controller
        controller.run();
        // check results
        Mockito.verify(mockView, Mockito.times(1)).showMenu();
        Mockito.verify(mockView, Mockito.times(1)).showAllEmployees(operatorRepository.findAll());
    }

    // @Test
    public void testShowAllActive(){
        // test show all operators
        System.out.println("[#] test show all active operators...");
        // prepare input
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // run controller
        controller.run();
        // check results
        Mockito.verify(mockView, Mockito.times(1)).showActiveEmployees(operatorRepository.findByActive(true));
    }

}
