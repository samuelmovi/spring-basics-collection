package samuelmovi.springGuiJpa.view;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ViewTest {

    @Autowired
    View view;

    @Before
    public void before(){

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
