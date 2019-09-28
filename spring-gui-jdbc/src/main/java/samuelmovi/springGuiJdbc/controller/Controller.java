package samuelmovi.springGuiJdbc.controller;

import samuelmovi.springGuiJdbc.model.Employee;
import samuelmovi.springGuiJdbc.view.View;
import samuelmovi.springGuiJdbc.model.EmployeeDao;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Controller {

    EmployeeDao employeeDao;
    View view;

    String operatorID;

    public Controller(EmployeeDao employeeDao, View view){
        this.employeeDao = employeeDao;
        this.view = view;
    }

    public void init(){
        view.setAllOperatives(employeeDao.findAll());
        view.setAllActiveOperatives(employeeDao.findAllActive());

        view.render();

        // set controls
        view.getDeactivateOperativesTabTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0){
                setID(view.getDeactivateOperativesTabTable());
            }
        });
        view.getDeactivateButton().addActionListener(e -> deactivateOperative());
        view.getDeleteOperativesTabTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0){
                setID(view.getDeleteOperativesTabTable());
            }
        });
        view.getDeleteButton().addActionListener(e -> deleteOperative());
        view.getRegisterButton().addActionListener(e -> createNew());
    }

    public void createNew(){
        Employee employee = new Employee();
        employee.setFirstName(view.getLastNameField().getText());
        employee.setLastName(view.getFirstNameField().getText());
        employeeDao.save(employee);

        view.getLastNameField().setText("");
        view.getFirstNameField().setText("");

        refreshModels();
    }

    public void setID(JTable table){
        operatorID = String.valueOf(table.getValueAt(table.getSelectedRow(),0));
    }

    public void deleteOperative(){
        if (operatorID != null){
            employeeDao.deleteById(Long.valueOf(operatorID));
        }
        refreshModels();
    }

    public void deactivateOperative(){
        if (operatorID != null){
            employeeDao.setInactive(Long.valueOf(operatorID));
        }
        refreshModels();
    }

    public void refreshModels(){
        view.fillModel(view.getAllOperativesModel(), employeeDao.findAll());
        view.fillModel(view.getAllActiveOperativesModel(), employeeDao.findAllActive());
    }
}
