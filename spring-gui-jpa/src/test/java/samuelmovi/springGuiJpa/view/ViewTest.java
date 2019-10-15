package samuelmovi.springGuiJpa.view;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samuelmovi.springGuiJpa.model.Operator;
import samuelmovi.springGuiJpa.repo.OperatorRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ViewTest {

    private View view =  new View();
    @Autowired
    private OperatorRepository operatorRepository;

    private String[][] operatorData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    //private static boolean firstRun = true;

    @Before
    public void before(){
        operatorRepository.deleteAll();
    }

    @After
    public void after(){

    }

    @Test
    public void testRender(){
        // check null
        Assert.assertNull(view.getFrame());
        Assert.assertNull(view.getContentPane());
        Assert.assertNull(view.getTabbedPane());

        // execute method
        view.render();

        // assert expected results
        Assert.assertNotNull(view.getFrame());
        Assert.assertNotNull(view.getContentPane());
        Assert.assertNotNull(view.getTabbedPane());
        Assert.assertEquals(1, view.getContentPane().getComponentCount());
        Assert.assertEquals(0, view.getTabbedPane().getComponentCount());
    }

    @Test
    public void testAllOperatives(){
        // check null
        Assert.assertNull(view.getAllOperativesTab());
        Assert.assertNull(view.getAllOperativesTabTable());

        // set object state
        view.setTabbedPane(new JTabbedPane());
        String[] columnNames = {"ID", "Last Name", "First Name", "Status"};
        view.setColumnNames(columnNames);

        // execute method
        view.allOperatives();
        // assert expected result:
        Assert.assertNotNull(view.getAllOperativesTab());
        Assert.assertNotNull(view.getAllOperativesTabTable());
        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
    }


    @Test
    public void testAllActive(){
        // check null
        Assert.assertNull(view.getAllActiveOperativesTab());
        Assert.assertNull(view.getAllActiveOperativesTabTable());

        // set object state
        view.setTabbedPane(new JTabbedPane());

        // execute method
        view.allActive();
        // assert expected result:
        Assert.assertNotNull(view.getAllActiveOperativesTab());
        Assert.assertNotNull(view.getAllActiveOperativesTabTable());
    }


    @Test
    public void testRegisterNew(){
        // check null
        Assert.assertNull(view.getRegisterNewOperativesTab());
        Assert.assertNull(view.getFirstNameField());
        Assert.assertNull(view.getLastNameField());

        // set object state
        view.setTabbedPane(new JTabbedPane());

        // execute method
        view.registerNew();
        // assert expected result:
        Assert.assertNotNull(view.getRegisterNewOperativesTab());
        Assert.assertNotNull(view.getFirstNameField());
        Assert.assertNotNull(view.getLastNameField());

        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(5, view.getRegisterNewOperativesTab().getComponentCount());
    }

    @Test
    public void testDeactivate(){
        // check null
        Assert.assertNull(view.getDeactivateOperativesTab());
        Assert.assertNull(view.getDeactivateOperativesTabTable());
        Assert.assertNull(view.getDeactivateButton());

        // set object state
        view.setTabbedPane(new JTabbedPane());

        // execute method
        view.deactivate();
        // assert expected result:
        Assert.assertNotNull(view.getDeactivateOperativesTab());
        Assert.assertNotNull(view.getDeactivateOperativesTabTable());
        Assert.assertNotNull(view.getDeactivateButton());

        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(2, view.getDeactivateOperativesTab().getComponentCount());

    }

    @Test
    public void testDelete(){
        // check null
        Assert.assertNull(view.getDeleteOperativesTab());
        Assert.assertNull(view.getDeleteOperativesTabTable());
        Assert.assertNull(view.getDeleteButton());

        // set object state
        view.setTabbedPane(new JTabbedPane());

        // execute method
        view.delete();
        // assert expected result:
        Assert.assertNotNull(view.getDeleteOperativesTab());
        Assert.assertNotNull(view.getDeleteOperativesTabTable());
        Assert.assertNotNull(view.getDeleteButton());

        Assert.assertEquals(1, view.getTabbedPane().getComponentCount());
        Assert.assertEquals(2, view.getDeleteOperativesTab().getComponentCount());

    }

    @Test
    public void testClearNewEmployeeFields(){
        // set object state
        view.setFirstNameField(new JTextField());
        view.setLastNameField(new JTextField());

        String testString = "REFFEqREWFqweewqr";
        view.getFirstNameField().setText(testString);
        view.getLastNameField().setText(testString);

        // execute method
        view.clearNewOperatorFields();

        // assert expected results
        Assert.assertEquals("", view.getFirstNameField().getText());
        Assert.assertEquals("", view.getLastNameField().getText());
    }

    @Test
    public void testFillModel(){
        // set object state
        for (String[] data: operatorData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }

        //execute method
        view.fillModel(view.getAllOperativesModel(), operatorRepository.findAll());

        // assert expected state of model
        Assert.assertEquals(operatorData.length, view.getAllOperativesModel().getRowCount());
    }

}
