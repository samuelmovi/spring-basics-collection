package samuelmovi.springCliJpa.view;

import samuelmovi.springCliJpa.model.Operator;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner input;

    public View(){
        print("*** WELCOME TO CTU EMPLOYEE MANAGEMENT TOOL ***");
        this.input = new Scanner(System.in);
    }

    public int showMenu(){
        print("\n[#] Choose an option:");
        print("\t[1] All Operatives");
        print("\t[2] All Active Operatives");
        print("\t[3] Register New Operative");
        print("\t[4] Delete employee data");
        print("\t[5] Set Operative inactive");
        print("\t[0] Exit");
        System.out.print("\n[?]: ");
        return Integer.parseInt(input.nextLine());
    }

    public void showAllEmployees(List<Operator> allEmployees){
        print("\n[#] All registered Operatives:");
        allEmployees.forEach(employee -> print(employee.toString()));
    }

    public void showActiveEmployees(List<Operator> activeEmployees){
        print("\n[#] All Active Operatives:");
        activeEmployees.forEach(employee -> print(employee.toString()));
    }

    public String[] newEmployee() {
        String[] fullName = new String[2];
        print("[#] Enter employee data:");
        System.out.print("\t> Last Name: ");
        fullName[0] = input.nextLine();
        System.out.print("\t> First Name: ");
        fullName[1] = input.nextLine();
        return fullName;
    }

    public int deleteEmployee(List<Operator> allEmployees) {
        print("\n[#] Choose ID to delete:");
        allEmployees.forEach(employee -> print(employee.toString()));
        System.out.print("[?]: ");

        return Integer.parseInt(input.nextLine());
    }

    public int setInactive(List<Operator> allEmployees){
        print("\n[#] Choose ID to deactivate:");
        allEmployees.forEach(employee -> print(employee.toString()));
        System.out.print("[?]: ");

        return Integer.parseInt(input.nextLine());
    }

    private static void print(String s) {
        System.out.println(s);
    }

}
