package samuelmovi.springCliJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import samuelmovi.springCliJpa.model.Operator;
import samuelmovi.springCliJpa.model.OperatorRepository;
import samuelmovi.springCliJpa.view.View;

import java.util.Optional;

public class Controller {

    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private View view;

    private boolean done = false;

    public Controller(){

    }

    public void run(){
        while (!done){
            int choice = view.showMenu();

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
    }
}
