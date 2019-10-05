package samuelmovi.bootGuiJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import samuelmovi.bootGuiJpa.model.Operator;
import samuelmovi.bootGuiJpa.repo.OperatorRepository;
import samuelmovi.bootGuiJpa.view.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

@Component
public class Controller {
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private View view;

    private String operatorID;

    @Bean
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
        view.getDeactivateButton().addActionListener(e -> deactivateOperative(Long.valueOf(operatorID)));
        view.getDeleteOperativesTabTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0){
                setID(view.getDeleteOperativesTabTable());
            }
        });
        view.getDeleteButton().addActionListener(e -> deleteOperative(Long.valueOf(operatorID)));
        view.getRegisterButton().addActionListener(e -> createNew(view.getLastNameField().getText(), view.getFirstNameField().getText()));
    }

    public void populate(){
        // add 5 entries to operators
        operatorRepository.save(new Operator("Jack", "Bauer"));
        operatorRepository.save(new Operator("Chloe", "O'Brian"));
        operatorRepository.save(new Operator("Kim", "Bauer"));
        operatorRepository.save(new Operator("David", "Palmer"));
        operatorRepository.save(new Operator("Michelle", "Dessler"));
    }

    public void createNew(String firstName, String lastName){
        operatorRepository.save(new Operator(firstName, lastName));

        view.clearNewOpFields();

        refreshModels();
    }

    public void setID(JTable table){
        operatorID = String.valueOf(table.getValueAt(table.getSelectedRow(),0));
    }

    public void deleteOperative(long id){
        operatorRepository.deleteById(id);
        refreshModels();
    }

    public void deactivateOperative(long id){
        Optional<Operator> optional = operatorRepository.findById(id);
        if (optional.isPresent()){
            Operator operator = optional.get();
            operator.setActive(false);
            operatorRepository.save(operator);
        }
        refreshModels();
    }

    public void refreshModels(){
        view.fillModel(view.getAllOperativesModel(), operatorRepository.findAll());
        view.fillModel(view.getAllActiveOperativesModel(), operatorRepository.findAllByActive(true));
    }


    // S & G
    public void setOperatorRepository(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
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

    public OperatorRepository getOperatorRepository() {
        return operatorRepository;
    }
}
