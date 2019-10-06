package samuelmovi.springGuiJdbc.view;

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

import javax.swing.table.DefaultTableModel;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ViewTest {

    @Autowired
    private View view;
    @Autowired
    private EmployeeDao employeeDao;

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
            Employee operator = new Employee(data[0], data[1]);
            employeeDao.save(operator);
        }
        // pass empty model to method
        DefaultTableModel testModel = new DefaultTableModel();
        view.fillModel(testModel, employeeDao.findAll());
        // assert expected state of model
        Assert.assertEquals(operatorData.length, testModel.getRowCount());
    }
}
