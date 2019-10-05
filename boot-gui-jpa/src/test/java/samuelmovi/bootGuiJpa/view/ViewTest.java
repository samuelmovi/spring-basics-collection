package samuelmovi.bootGuiJpa.view;


import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import samuelmovi.bootGuiJpa.model.Operator;
import samuelmovi.bootGuiJpa.repo.OperatorRepository;

import javax.swing.table.DefaultTableModel;

// @DataJpaTest
// @RunWith(SpringRunner.class)
public class ViewTest {

    /*
    private View view = new View();
    @Autowired
    private OperatorRepository operatorRepository;

    private String[][] operatorData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    private static boolean firstRun = true;

    @BeforeClass
    public static void disableHeadless(){
        System.setProperty("java.awt.headless", "false");
    }


    @Before
    public void before(){
        if (firstRun){
            //view.render();
            firstRun = false;
        }

    }

    @After
    public void after(){
        //view.getTabbedPane().removeAll();
    }

    @Test
    public void testRender(){
        // execute method
        view.render();
        // assert expected results
        Assert.assertNotNull(view.getFrame());
        Assert.assertNotNull(view.getContentPane());
        Assert.assertNotNull(view.getTabbedPane());
        Assert.assertEquals(1, view.getContentPane().getComponentCount());
    }


    @Test
    public void testAllActive(){
        // execute method
        view.allActive();
        // assert expected result:
        Assert.assertNotNull(view.getAllActiveOperativesTab());
        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(view.getAllActiveOperativesTabTitle(), view.getTabbedPane().getTitleAt(0));
    }

    @Test
    public void testAllOperatives(){
        // execute method
        view.allOperatives();
        // assert expected result:
        Assert.assertNotNull(view.getAllOperativesTab());
        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(view.getAllOperativesTabTitle(), view.getTabbedPane().getTitleAt(0));
    }

    @Test
    public void testRegisterNew(){
        // execute method
        view.registerNew();
        // assert expected result:
        Assert.assertNotNull(view.getRegisterNewOperativesTab());
        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(view.getRegisterNewOperativeTabTitle(), view.getTabbedPane().getTitleAt(0));
    }

    @Test
    public void testDeactivate(){
        // execute method
        view.deactivate();
        // assert expected result:
        Assert.assertNotNull(view.getDeactivateOperativesTab());
        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(view.getDeactivateOperativeTabTitle(), view.getTabbedPane().getTitleAt(0));
    }

    @Test
    public void testDelete(){
        // execute method
        view.delete();
        // assert expected result:
        Assert.assertNotNull(view.getDeleteOperativesTab());
        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(view.getDeleteOperativeTabTitle(), view.getTabbedPane().getTitleAt(0));
    }

    @Test
    public void testFillModels(){
        // add instances to repo
        for (String[] data: operatorData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }
        // pass empty model to method
        DefaultTableModel testModel = new DefaultTableModel();
        view.fillModel(testModel, operatorRepository.findAll());
        // assert expected state of model
        Assert.assertEquals(operatorData.length, testModel.getRowCount());
    }
*/
}
