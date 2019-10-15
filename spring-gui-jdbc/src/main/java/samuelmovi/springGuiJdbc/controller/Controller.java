package samuelmovi.springGuiJdbc.controller;

import samuelmovi.springGuiJdbc.model.Employee;
import samuelmovi.springGuiJdbc.view.View;
import samuelmovi.springGuiJdbc.model.EmployeeDao;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Controller {

    private EmployeeDao employeeDao;
    private View view;
    private String operatorID;

    public Controller(){}

    public Controller(EmployeeDao employeeDao, View view){
        this.employeeDao = employeeDao;
        this.view = view;
    }

    public void init(){
        if( employeeDao.findAll().size()== 0){
            populate();
        }
        view.setAllOperatives(employeeDao.findAll());
        view.setAllActiveOperatives(employeeDao.findAllActive());

        view.render();
        view.createContent();

        refreshModels();
        controls();
    }

    public void controls(){
        // set controls
        view.getDeactivateOperativesTabTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0){
                selectedEmployee(view.getDeactivateOperativesTabTable());
            }
        });
        view.getDeactivateButton().addActionListener(e -> deactivateOperative());
        view.getDeleteOperativesTabTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0){
                selectedEmployee(view.getDeleteOperativesTabTable());
            }
        });
        view.getDeleteButton().addActionListener(e -> deleteOperative());
        view.getRegisterButton().addActionListener(e -> createNew(view.getLastNameField().getText(), view.getFirstNameField().getText()));
    }

    public void populate(){
        employeeDao.save(new Employee("Jack", "Bauer"));
        employeeDao.save(new Employee("Chloe", "OBrian"));
        employeeDao.save(new Employee("Kim", "Bauer"));
        employeeDao.save(new Employee("David", "Palmer"));
        employeeDao.save(new Employee("Michelle", "Dessler"));
    }

    public void createNew(String lastName, String firstName){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employeeDao.save(employee);

        view.clearNewEmployeeFields();

        refreshModels();
    }

    public void selectedEmployee(JTable table){
        operatorID = String.valueOf(table.getValueAt(table.getSelectedRow(),0));
    }

    public void deleteOperative(){
        if (operatorID != null){
            employeeDao.deleteById(Long.valueOf(operatorID));
            operatorID = null;
        }
        refreshModels();
    }

    public void deactivateOperative(){
        if (operatorID != null){
            employeeDao.setInactive(Long.valueOf(operatorID));
            operatorID = null;
        }
        refreshModels();
    }

    public void refreshModels(){
        view.fillModel(view.getAllOperativesModel(), employeeDao.findAll());
        view.fillModel(view.getAllActiveOperativesModel(), employeeDao.findAllActive());
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

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }
}
