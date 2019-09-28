package samuelmovi.springCliJdbc.app;

import samuelmovi.springCliJdbc.model.Employee;
import samuelmovi.springCliJdbc.model.EmployeeDao;
import java.util.List;
import java.util.Scanner;


public class App {


    EmployeeDao employeeDao;
    Scanner input;

    public App(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
        this.input = new Scanner(System.in);
        //input.useDelimiter("\n");
    }

    public void run() {
        boolean done = false;
        int choice;

        print("*** WELCOME TO CTU EMPLOYEE MANAGEMENT TOOL ***");
        while (!done) {
            print("\n[#] Choose an option:");
            print("\t[1] All Operatives");
            print("\t[2] All Active Operatives");
            print("\t[3] Register New Operative");
            print("\t[4] Delete employee data");
            print("\t[5] Set Operative inactive");
            print("\t[0] Exit samuelmovi.springCliJdbc.app");
            try {
                System.out.print("[?]: ");
                choice = Integer.valueOf(input.nextLine());
                if (choice == 1) {
                    allEmployees();
                } else if (choice == 2) {
                    activeEmployees();
                } else if (choice == 3) {
                    newEmployee();
                } else if (choice == 4) {
                    deleteEmployee();
                } else if (choice == 5) {
                    setInactive();
                }else if (choice == 0) {
                    print("\n[#] Exiting program now...");
                    done = true;
                } else {
                    print("[!] Wrong choice try again!");
                }
            } catch (Exception e) {
                print("[!!] Error handling choice: " + e);
            }
        }
    }


    public void allEmployees() {
        List<Employee> employees;
        employees = employeeDao.findAll();
        print("\n[#] All registered Operatives:");
        for(Employee e: employees) {
            print("["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName()+"/ Active: "+e.isActive());
        }
    }

    public void activeEmployees() {
        List<Employee> employees;
        employees = employeeDao.findAllActive();
        print("\n[#] All Active Operatives:");
        for(Employee e: employees) {
            print("["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName()+"/ Active: "+e.isActive());
        }
    }

    public void newEmployee() {
        String s;
        Employee newEmployee = new Employee();
        print("[#] Enter employee data:");
        System.out.print("\t> Last Name: ");
        newEmployee.setLastName(input.nextLine());
        System.out.print("\t> First Name: ");
        newEmployee.setFirstName(input.nextLine());
        employeeDao.save(newEmployee);
    }

    public void deleteEmployee() {
        List<Employee> employees;
        employees = employeeDao.findAll();
        print("\n[#] Choose ID to delete:");
        for(Employee e: employees) {
            print("["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName());
        }
        System.out.print("[?]: ");
        int choice = Integer.valueOf(input.nextLine());
        Employee e = employeeDao.findById(choice);
        employeeDao.deleteById(e.getId());
        print("[#] Operative "+e.getId()+" deleted");
    }

    public void setInactive(){
        List<Employee> employees;
        employees = employeeDao.findAll();
        print("\n[#] Choose ID to deactivate:");
        for(Employee e: employees) {
            print("["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName());
        }
        System.out.print("[?]: ");
        int choice = Integer.valueOf(input.nextLine());
        Employee e = employeeDao.findById(choice);
        employeeDao.setInactive(e.getId());
        print("[#] Operative "+e.getId()+" deactivated");
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
