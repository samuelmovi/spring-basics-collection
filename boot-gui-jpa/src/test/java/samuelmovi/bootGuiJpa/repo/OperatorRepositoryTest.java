package samuelmovi.bootGuiJpa.repo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.Assert;
import org.springframework.test.context.junit4.SpringRunner;
import samuelmovi.bootGuiJpa.model.Operator;


import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class OperatorRepositoryTest {
    // NO REAL REASON FOR THIS TEST AS IT TESTS SPRING'S CODE

    @Autowired
    OperatorRepository operatorRepository;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Before
    public void before(){
        // FEED DATABASE
        System.out.println("[RepoTest] Feeding database...");
        for (String[] data: employeeData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }
    }

    @After
    public void after(){
        // EMPTY DATABASE
        System.out.println("[RepoTest] Emptying database...");
        operatorRepository.deleteAll();
    }

    @Test
    public void testFindByActive(){
        // FIND ACTIVE
        List<Operator> activeOperators = operatorRepository.findByActive(true);
        Assert.assertEquals(3, activeOperators.size());
    }


}
