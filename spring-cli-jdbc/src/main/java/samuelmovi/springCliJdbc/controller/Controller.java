package samuelmovi.springCliJdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import samuelmovi.springCliJdbc.model.Employee;
import samuelmovi.springCliJdbc.model.EmployeeDao;
import samuelmovi.springCliJdbc.view.View;

import java.util.Scanner;

public class Controller {

    EmployeeDao employeeDao;
    View view;

    private Scanner input = new Scanner(System.in);;
    private boolean done = false;
    private int choice;
    private String[] fullName = new String[2];

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Kim", "Bauer"},
            {"Chloe", "OBrian"},
            {"David", "Palmer"},
            {"Michelle", "Dessler"}
    };

    public Controller(EmployeeDao employeeDao, View view){
        this.employeeDao = employeeDao;
        this.view = view;

        if (this.employeeDao.findAll().size() == 0){
            populate();
        }
    }
    public void populate(){
        for (String[] data: employeeData){
            Employee employee = new Employee(data[0], data[1]);
            employeeDao.save(employee);
        }
    }


    public void run(){
        do{
            view.showMenu();
            try{
                choice = Integer.valueOf(input.nextLine());

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
                    System.out.println("[!] Wrong choice try again");
                }
            }
            catch (Exception e){
                System.out.println("[!] Stop messing around");
            }

        }
        while (!done);
    }

    // G & S

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public String[] getFullName() {
        return fullName;
    }

    public void setFullName(String[] fullName) {
        this.fullName = fullName;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
