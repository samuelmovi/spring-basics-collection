package samuelmovi.springCliJdbc.model;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import org.springframework.test.annotation.Rollback;

import java.util.List;

@ContextConfiguration(locations = "classpath:Tests.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup("testDbSetup.xml")
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Powers", "Austin"},
            {"Movi", "Sam"},
    };


    @Test
    public void testSave(){
        employeeDao.execute("truncate table employees");
        // FEED DATABASE
        System.out.println("[#] Feeding database...");
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }

        // SAVE NEW EMPLOYEE
        Employee employee = new Employee("LAST", "FIRST");
        employeeDao.save(employee);

        List<Employee> allEmployees = employeeDao.findAll();
        Assert.assertEquals(employeeData.length + 1, allEmployees.size());
    }

    @Test
    public void testDeleteById(){
        employeeDao.execute("truncate table employees");
        // FEED DATABASE
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }

        // DELETE ONE EMPLOYEE
        List<Employee> allEmployees = employeeDao.findAll();

        if (employeeDao.deleteById(allEmployees.get(1).getId()) ==1){
            allEmployees = employeeDao.findAll();
            Assert.assertEquals(employeeData.length - 1, allEmployees.size());
        }
        else{
            // raise exception here
            System.out.println("[!!] No employee with id 1 ");
        }

    }

    @Test
    @Rollback(true)
    public void testFindById(){
        employeeDao.execute("truncate table employees");
        // FEED DATABASE
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }

        // FIND BY ID
        List<Employee> allEmployees = employeeDao.findAll();
        Employee employee = employeeDao.findById(allEmployees.get(1).getId());
        Assert.assertNotNull(employee);
    }

    @Test
    public void testFindAllActive(){
        employeeDao.execute("truncate table employees");

        // FEED DATABASE
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }

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
        employeeDao.execute("truncate table employees");

        // FEED DATABASE
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }

        // FIND ALL
        List<Employee> allEmployees = employeeDao.findAll();

        Assert.assertEquals(employeeData.length, allEmployees.size());
    }

    @Test
    public void testSetInactive(){
        employeeDao.execute("delete from employees");

        // FEED DATABASE
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }

        // SET 1 EMPLOYEE AS INACTIVE
        List<Employee> allEmployees = employeeDao.findAll();
        Employee employee = employeeDao.findById(allEmployees.get(1).getId());
        employeeDao.setInactive(employee.getId());

        List<Employee> allActiveEmployees = employeeDao.findAllActive();
        Assert.assertEquals(employeeData.length - 1, allActiveEmployees.size());
    }
}
