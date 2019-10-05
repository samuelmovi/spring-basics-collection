package samuelmovi.springGuiJpa.view;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samuelmovi.springGuiJpa.repo.OperatorRepository;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ViewTest {

    @Autowired
    View view;
    @Autowired
    private OperatorRepository operatorRepository;

    private static boolean firstRun = true;

    @Before
    public void before(){
        if (firstRun){

        }

    }

    @After
    public void after(){

    }

    @Test
    public void testAllOperatives(){
        // setup

        // execute method

        // assert expected result:
        // - allOperativesTab not null AND visible
        // - allOperativesTabTitle, allOperativesTab added to tabbedPane
        // - scroll added to tab
        // -
    }

}
