package samuelmovi.springGuiJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import samuelmovi.springGuiJpa.model.Operator;
import samuelmovi.springGuiJpa.repo.OperatorRepository;
import samuelmovi.springGuiJpa.view.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;


public class Controller {
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private View view;

    private String operatorID;

    /*public Controller(View view){
        this.view = view;
    }*/

    public void init(){
        if( operatorRepository.count() == 0){
            populate();
        }

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
        view.getRegisterButton().addActionListener(e -> createNew(view.getLastNameField().getText(), view.getFirstNameField().getText()));
    }

    public void populate(){
        operatorRepository.save(new Operator("Jack", "Bauer"));
        operatorRepository.save(new Operator("Chloe", "O'Brian"));
        operatorRepository.save(new Operator("Kim", "Bauer"));
        operatorRepository.save(new Operator("David", "Palmer"));
        operatorRepository.save(new Operator("Michelle", "Dessler"));
    }

    public void createNew(String lastName, String firstName){
        operatorRepository.save(new Operator(firstName, lastName));
        view.clearNewOperatorFields();
        refreshModels();
    }

    public void setID(JTable table){
        operatorID = String.valueOf(table.getValueAt(table.getSelectedRow(),0));
    }

    public void deleteOperative(){
        if (operatorID != null){
            operatorRepository.deleteById(Long.valueOf(operatorID));
            operatorID = null;
        }
        refreshModels();
    }

    public void deactivateOperative(){
        if (operatorID != null){
            Optional<Operator> optional = operatorRepository.findById(Long.valueOf(operatorID));
            if (optional.isPresent()){
                Operator operator = optional.get();
                operator.setActive(false);
                operatorRepository.save(operator);
            }
            operatorID = null;
        }
        refreshModels();
    }

    public void refreshModels(){
        view.fillModel(view.getAllOperativesModel(), operatorRepository.findAll());
        view.fillModel(view.getAllActiveOperativesModel(), operatorRepository.findByActive(true));
    }

    // G's & S's

    public void setOperatorRepository(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public OperatorRepository getOperatorRepository() {
        return operatorRepository;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
