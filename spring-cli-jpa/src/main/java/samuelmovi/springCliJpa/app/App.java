package samuelmovi.springCliJpa.app;

import samuelmovi.springCliJpa.model.Operator;
import samuelmovi.springCliJpa.model.OperatorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class App {

    private Scanner input;
    private OperatorRepository repository;
    private List<Operator> operators;


    public void run(OperatorRepository repository){
        this.repository = repository;
        this.input = new Scanner(System.in);

        populate();
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
            print("\t[0] Exit samuelmovi.springCliJpa.app");
            try {
                System.out.print("[?]: ");
                choice = Integer.valueOf(input.nextLine());
                if (choice == 1) {
                    allOperators();
                } else if (choice == 2) {
                    activeOperators();
                } else if (choice == 3) {
                    newOperator();
                } else if (choice == 4) {
                    deleteOperator();

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
        System.exit(0);
    }

    public void populate(){
        if (repository.count() == 0){
            repository.save(new Operator("Jack", "Bauer"));
            repository.save(new Operator("Chloe", "O'Brian"));
            repository.save(new Operator("Kim", "Bauer"));
            repository.save(new Operator("David", "Palmer"));
            repository.save(new Operator("Michelle", "Dessler"));
        }
    }

    public void allOperators() {
        operators = (List<Operator>) repository.findAll();
        print("\n[#] All registered Operatives:");
        for(Operator e: operators) {
            print("["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName()+"/ Active: "+e.isActive());
        }
    }

    public void activeOperators() {
        operators = repository.findByActive(true);
        print("\n[#] All Active Operatives:");
        for(Operator e: operators) {
            print("["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName()+"/ Active: "+e.isActive());
        }
    }

    public void newOperator() {
        String s;
        print("[#] Enter employee data:");
        System.out.print("\t> Last Name: ");
        String firstName = input.nextLine();
        System.out.print("\t> First Name: ");
        String lastName =  input.nextLine();
        repository.save(new Operator(firstName, lastName));
    }

    public void deleteOperator() {
        operators = (List<Operator>) repository.findAll();
        print("\n[#] Choose ID to delete:");
        for(Operator e: operators) {
            print("\t["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName());
        }
        System.out.print("\n[?]: ");
        int choice = Integer.valueOf(input.nextLine());
        Optional<Operator> opOp = repository.findById(Long.valueOf(choice));
        if (opOp.isPresent()){
            Operator operator = opOp.get();
            repository.delete(operator);
            print("[#] Operative deleted: "+operator.toString());

        }
    }

    public void setInactive(){
        operators = (List<Operator>) repository.findAll();
        print("\n[#] Choose ID to deactivate:");
        for(Operator e: operators) {
            print("\t["+e.getId()+"] Name: "+e.getLastName()+", "+e.getFirstName());
        }
        System.out.print("\n[?]: ");
        int choice = Integer.valueOf(input.nextLine());
        Optional<Operator> opOp = repository.findById(Long.valueOf(choice));
        if (opOp.isPresent()){
            Operator operator = opOp.get();
            operator.setActive(false);
            repository.save(operator);
            print("[#] Operative deactivated: "+operator.toString());
        }
    }


    // S & G
    static void print(String s) {
        System.out.print(s+"\n");
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public OperatorRepository getRepository() {
        return repository;
    }

    public void setRepository(OperatorRepository repository) {
        this.repository = repository;
    }
}
