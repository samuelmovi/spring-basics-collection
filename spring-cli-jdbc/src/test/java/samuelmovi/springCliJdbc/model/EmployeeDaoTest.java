package samuelmovi.springCliJdbc.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;


import java.util.List;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };

    @Before
    public void before(){
        // FEED DATABASE
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }
    }

    @After
    public void after(){
        // CLEAR DATABASE
        employeeDao.execute("truncate table employees");
    }


    @Test
    public void testSave(){
        // SAVE NEW EMPLOYEE
        Employee employee = new Employee("LAST", "FIRST");
        employeeDao.save(employee);

        List<Employee> allEmployees = employeeDao.findAll();
        Assert.assertEquals(employeeData.length + 1, allEmployees.size());
    }

    @Test
    public void testDeleteById(){
        // DELETE ONE EMPLOYEE
        List<Employee> allEmployees = employeeDao.findAll();
        long id = employeeDao.deleteById(allEmployees.get(1).getId());
        if (id == 1){
            allEmployees = employeeDao.findAll();
            Assert.assertEquals(employeeData.length - 1, allEmployees.size());
        }
        else{
            // raise exception here
            System.out.println("[!!] No employee with id: "+id);
        }

    }

    @Test
    public void testFindById(){
        // FIND BY ID
        List<Employee> allEmployees = employeeDao.findAll();
        Employee employee = employeeDao.findById(allEmployees.get(1).getId());
        Assert.assertNotNull(employee);
    }

    @Test
    public void testFindAllActive(){
        // SET 1 EMPLOYEE AS INACTIVE
        List<Employee> allEmployees = employeeDao.findAll();
        Employee employee = employeeDao.findById(allEmployees.get(1).getId());
        employee.setActive(false);
        employeeDao.update(employee);

        List<Employee> allActiveEmployees = employeeDao.findAllActive();
        Assert.assertEquals(employeeData.length - 1, allActiveEmployees.size());
    }

    @Test
    public void testFindAll(){
        // FIND ALL
        List<Employee> allEmployees = employeeDao.findAll();

        Assert.assertEquals(employeeData.length, allEmployees.size());
    }

    @Test
    public void testSetInactive(){
        // SET 1 EMPLOYEE AS INACTIVE
        List<Employee> allEmployees = employeeDao.findAll();
        Employee employee = employeeDao.findById(allEmployees.get(1).getId());
        employeeDao.setInactive(employee.getId());

        List<Employee> allActiveEmployees = employeeDao.findAllActive();
        Assert.assertEquals(employeeData.length - 1, allActiveEmployees.size());
    }
}
