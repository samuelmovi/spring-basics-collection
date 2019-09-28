package samuelmovi.springCliJpa.app;

import samuelmovi.springCliJpa.model.Operator;
import samuelmovi.springCliJpa.model.OperatorRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {


    @Autowired
    OperatorRepository operatorRepository;
    @Autowired
    App app;

    boolean firstRun = true;


    @Before
    public void before(){
        // FEED DATABASE
        System.out.println("[AppTest] Feeding database...");
        for (String[] data: employeeData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }

        if (firstRun){
            // inject samuelmovi.springCliJpa.app class
            app.setRepository(operatorRepository);
            firstRun = false;
        }
    }

    @After
    public void after(){
        // EMPTY DATABASE
        System.out.println("[AppTest] Emptying database...");
        operatorRepository.deleteAll();
    }



    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Test
    public void testAllOperators(){
        // QUERY ALL THROUGH APP INSTANCE
        app.allOperators();
        int after = app.getOperators().size();

        // ASSERT NUMBER OF ITEMS RECEIVED IS X
        Assert.assertEquals(3, after);
    }

    @Test
    public void testActiveOperators(){
        List<Operator> allOperators = (List<Operator>)operatorRepository.findAll();
        Operator operator = allOperators.get(1);
        operator.setActive(false);
        operatorRepository.save(operator);

        // QUERY ALL THROUGH APP INSTANCE
        app.activeOperators();

        // ASSERT NUMBER OF ITEMS RECEIVED IS X
        Assert.assertEquals(2, app.getOperators().size());
    }

}
