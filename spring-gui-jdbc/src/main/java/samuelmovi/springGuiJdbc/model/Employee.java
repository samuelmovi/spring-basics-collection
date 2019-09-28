package samuelmovi.springGuiJdbc.model;

public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private boolean active;

    public Employee(){}

    public Employee(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString(){
        return id+" / "+lastName+" / "+firstName+" / "+active;
    }
}
