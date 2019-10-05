package samuelmovi.bootGuiJpa.view;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samuelmovi.bootGuiJpa.repo.OperatorRepository;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ViewTest {

    @Autowired
    View view;
    @Autowired
    OperatorRepository operatorRepository;

    private String[][] operatorData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    private static boolean firstRun = true;

    @Before
    public void before(){
        if (firstRun){
            view.render();
            firstRun = false;
        }

    }

    @After
    public void after(){
        view.getTabbedPane().removeAll();
    }


}
