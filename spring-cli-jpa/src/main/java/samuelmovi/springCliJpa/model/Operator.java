package samuelmovi.springCliJpa.model;

import javax.persistence.*;

@Entity
public class Operator {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "last_name", length = 200)
    private String lastName;
    @Column
    private boolean active;


	protected Operator() {}

    public Operator(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = true;
    }

    @Override
    public String toString() {
        return String.format(
                "Operator[%d] Name: '%s, %s', active=%s]",
                id,lastName,  firstName, active);
    }

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
    public boolean isActive() {
		return active;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
