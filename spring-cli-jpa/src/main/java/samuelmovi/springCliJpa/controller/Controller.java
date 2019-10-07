package samuelmovi.springCliJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import samuelmovi.springCliJpa.model.Operator;
import samuelmovi.springCliJpa.model.OperatorRepository;
import samuelmovi.springCliJpa.view.View;

import java.util.Optional;
import java.util.Scanner;

public class Controller {

    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private View view;

    private Scanner input;
    private int choice;
    private boolean done = false;

    private String[][] employeeData = {
            {"Bauer", "Jack"},
            {"Kim", "Bauer"},
            {"Chloe", "OBrian"},
            {"David", "Palmer"},
            {"Michelle", "Dessler"}
    };

    public void populate(){
        for (String[] data: employeeData){
            Operator operator = new Operator(data[0], data[1]);
            operatorRepository.save(operator);
        }
    }

    public void run(){
        if (operatorRepository.count() == 0){
            populate();
        }
        do{
            view.showMenu();
            try{
                choice = Integer.valueOf(input.nextLine());
                if (choice == 1) {
                    view.showAllEmployees(operatorRepository.findAll());
                } else if (choice == 2) {
                    view.showActiveEmployees(operatorRepository.findByActive(true));
                } else if (choice == 3) {
                    String[] fullName = view.newEmployee();
                    operatorRepository.save(new Operator(fullName[0], fullName[1]));
                } else if (choice == 4) {
                    int id = view.deleteEmployee(operatorRepository.findAll());
                    operatorRepository.deleteById(Long.valueOf(id));
                } else if (choice == 5) {
                    long id = view.setInactive(operatorRepository.findAll());
                    Optional optional = operatorRepository.findById(id);
                    if(optional.isPresent()){
                        Operator operator = (Operator) optional.get();
                        operator.setActive(false);
                        operatorRepository.save(operator);
                    }
                }else if (choice == 0) {
                    System.out.println("\n[#] Exiting program now...");
                    done = true;
                } else {
                    System.out.println("[!] Wrong choice try again!");
                }
            }
            catch(Exception e){
                System.out.println("[!!!] Stop messing around");
            }
        }
        while (!done);
    }

    // G & S

    public OperatorRepository getOperatorRepository() {
        return operatorRepository;
    }

    public void setOperatorRepository(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
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

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
