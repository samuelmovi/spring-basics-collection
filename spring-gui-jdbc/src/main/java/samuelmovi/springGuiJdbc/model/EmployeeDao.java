package samuelmovi.springGuiJdbc.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public class EmployeeDao {

    String schema = "create table if not exists employees(\n" +
            "  id int AUTO_INCREMENT primary key,  \n" +
            "  last_name text not null, \n" +
            "  first_name text not null,\n" +
            "  active boolean\n" +
            "  );" ;

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.update(schema);
    }

    public void execute(String query){
        jdbcTemplate.execute(query);
    }

    public int save(Employee employee) {
        String query="insert into employees(last_name, first_name, active) values( '" + employee.getLastName()+"','"+ employee.getFirstName()+"',true)";
        return jdbcTemplate.update(query);
    }

    public Employee findEmployee(Employee employee) {
        String query = "select * from employees where ";
        query += " first_name='"+employee.getFirstName()+"',";
        query += " last_name='"+employee.getLastName()+"',";
        query += " active='"+employee.isActive();
        List<Employee> books = jdbcTemplate.query(query, new EmployeeMapper());
        return books.get(0);
    }

    public Employee findById(long index) {
        String query = "select * from employees where id="+index;
        List<Employee> employees = jdbcTemplate.query(query, new EmployeeMapper());
        return employees.get(0);
    }

    public List<Employee> findAllActive(){
        String query = "select * from employees where active=true";
        List <Employee> employee = jdbcTemplate.query(query, new EmployeeMapper());
        return employee;
    }

    public List<Employee> findAll(){
        String query = "select * from employees";
        List <Employee> employee = jdbcTemplate.query(query, new EmployeeMapper());
        return employee;
    }

    public List<Employee> findByField(String field, String value){
        String query = "select * from employees where "+field+"='"+value+"'";
        List <Employee> employee = jdbcTemplate.query(query, new EmployeeMapper());
        return employee;
    }

    public List<Employee> findByField(String field, String value, boolean isNumber){
        String query = "select * from employees where "+field+"=";
        if(isNumber) {
            query += value;
        }
        else {
            query += "'"+value+"'";
        }
        List <Employee> employee = jdbcTemplate.query(query, new EmployeeMapper());
        return employee;
    }

    public int update(Employee employee) {
        String query="update employees set ";
        query += " first_name='"+employee.getFirstName()+"',";
        query += " last_name='"+employee.getLastName()+"',";
        query += " active="+employee.isActive();
        query += " where id="+employee.getId();
        return jdbcTemplate.update(query);
    }

    public int deleteById(long index) {
        String query="delete from employees where id="+index;
        return jdbcTemplate.update(query);
    }

    public int setInactive(long index){
        String query = "update employees set active=false where id="+index;
        return jdbcTemplate.update(query);
    }
}
