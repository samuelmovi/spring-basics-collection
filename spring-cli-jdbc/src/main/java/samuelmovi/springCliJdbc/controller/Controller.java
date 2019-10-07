package samuelmovi.springCliJdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import samuelmovi.springCliJdbc.model.EmployeeDao;
import samuelmovi.springCliJdbc.view.View;

public class Controller {

    EmployeeDao employeeDao;
    View view;

    private boolean done = false;
    private int choice;
    private String[] fullName = new String[2];

    public Controller(EmployeeDao employeeDao, View view){
        this.employeeDao = employeeDao;
        this.view = view;
    }

    public void run(){
        while (!done){
            choice = view.showMenu();

            if (choice == 1) {
                view.showAllEmployees(employeeDao.findAll());
            } else if (choice == 2) {
                view.showActiveEmployees(employeeDao.findAllActive());
            } else if (choice == 3) {
                fullName = view.newEmployee();
                employeeDao.save(fullName[0], fullName[1]);
            } else if (choice == 4) {
                int id = view.deleteEmployee(employeeDao.findAll());
                employeeDao.deleteById(id);
            } else if (choice == 5) {
                int id = view.setInactive(employeeDao.findAll());
                employeeDao.setInactive(id);
            }else if (choice == 0) {
                System.out.println("\n[#] Exiting program now...");
                done = true;
            } else {
                System.out.println("[!] Wrong choice try again!");
            }
        }
    }
}
