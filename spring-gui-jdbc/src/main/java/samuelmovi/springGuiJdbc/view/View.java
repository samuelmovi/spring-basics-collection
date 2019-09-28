package samuelmovi.springGuiJdbc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import samuelmovi.springGuiJdbc.model.Employee;


public class View {
    private JPanel contentPane;

    private JPanel allOperativesTab;
    private JPanel allActiveOperativesTab ;
    private JPanel registerNewOperativesTab ;
    private JPanel deactivateOperativesTab ;
    private JPanel deleteOperativesTab;
    private JTabbedPane tabbedPane;


    private String titleMessage;
    private String allOperativesTabTitle;
    private String allActiveOperativesTabTitle;
    private String registerNewOperativeTabTitle;
    private String deactivateOperativeTabTitle;
    private String deleteOperativeTabTitle;

    private	DefaultTableModel allOperativesModel=new DefaultTableModel();
    private DefaultTableModel allActiveOperativesModel=new DefaultTableModel();

    private JTable allOperativesTabTable;
    private JTable allActiveOperativesTabTable;
    private JTable deactivateOperativesTabTable;
    private JTable deleteOperativesTabTable;

    private TableRowSorter<TableModel> allOperativesSorter=new TableRowSorter<TableModel>();
    private TableRowSorter<TableModel> allActiveOperativesSorter=new TableRowSorter<TableModel>();
    private TableRowSorter<TableModel> deactivateOperativesSorter=new TableRowSorter<TableModel>();
    private TableRowSorter<TableModel> deleteOperativesSorter=new TableRowSorter<TableModel>();

    private String[] columnNames;

    private List<Employee> allOperatives;
    private List<Employee> allActiveOperatives;
    private JButton registerButton;
    private JButton deleteButton;
    private JButton deactivateButton;

    private JTextField lastNameField;
    private JTextField firstNameField;

    public void render() {


        JFrame frame = new JFrame(titleMessage);
        frame.setBounds(100, 100, 400, 300);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.PINK);
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);

        // CREATE PANE
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 380, 250);
        contentPane.add(tabbedPane);

        allOperatives();
        allActive();
        registerNew();
        deactivate();
        delete();

        allOperativesModel = fillModel(allOperativesModel, allOperatives);
        allActiveOperativesModel = fillModel(allActiveOperativesModel, allActiveOperatives);

    }

    public DefaultTableModel fillModel(DefaultTableModel model, List<Employee> list){
        model.setRowCount(0);
        for (Employee employee: list){
            Vector<String> vector = new Vector<String>();
            vector.add(String.valueOf(employee.getId()));
            vector.add(employee.getLastName());
            vector.add(employee.getFirstName());
            vector.add(String.valueOf(employee.isActive()));
            model.addRow(vector);
            System.out.println(employee.toString());
        }
        return model;
    }

    public void allOperatives(){
        // ALL OPERATIVES
        allOperativesTab = new JPanel();
        allOperativesTab.setBackground(Color.BLUE);
        allOperativesTab.setLayout(null);
        allOperativesTab.setLayout(null);
        allOperativesTab.setVisible(true);
        tabbedPane.addTab(allOperativesTabTitle, allOperativesTab);

        // add table with all employees
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(12, 12, 350, 200);
        allOperativesTab.add(scroll);

        // MODEL
        allOperativesModel.setRowCount(0);
        allOperativesModel.setColumnCount(4);
        allOperativesModel.setColumnIdentifiers(columnNames);

        // TABLE
        allOperativesTabTable = new JTable(allOperativesModel);
        allOperativesSorter = new TableRowSorter<TableModel>(allOperativesModel);
        allOperativesTabTable.setRowSorter(allOperativesSorter);
        // allOperativesTabTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scroll.setViewportView(allOperativesTabTable);
    }

    public void allActive(){
        // ALL ACTIVE OPERATIVES
        allActiveOperativesTab = new JPanel();
        allActiveOperativesTab.setBackground(Color.RED);
        allActiveOperativesTab.setLayout(null);
        allActiveOperativesTab.setVisible(false);
        tabbedPane.addTab(allActiveOperativesTabTitle, allActiveOperativesTab);

        // add table with all active employees
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(12, 12, 350, 200);
        allActiveOperativesTab.add(scroll);

        // MODEL
        allActiveOperativesModel.setRowCount(0);
        allActiveOperativesModel.setColumnCount(4);
        allActiveOperativesModel.setColumnIdentifiers(columnNames);

        // TABLE
        allActiveOperativesTabTable = new JTable(allActiveOperativesModel);
        allActiveOperativesSorter = new TableRowSorter<TableModel>(allActiveOperativesModel);
        allActiveOperativesTabTable.setRowSorter(allActiveOperativesSorter);

        scroll.setViewportView(allActiveOperativesTabTable);
    }

    public void registerNew(){
        // REGISTER NEW OPERATIVE
        registerNewOperativesTab = new JPanel();
        registerNewOperativesTab.setBounds(5, 50, 100, 200);
        registerNewOperativesTab.setBackground(Color.GREEN);
        registerNewOperativesTab.setLayout(null);
        registerNewOperativesTab.setVisible(false);
        tabbedPane.addTab(registerNewOperativeTabTitle, registerNewOperativesTab);

        // add last name and first name fields, and button
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(25, 10, 100, 25);
        registerNewOperativesTab.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(150,10, 100, 25);
        registerNewOperativesTab.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(25, 60, 100, 25);
        registerNewOperativesTab.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(150,60, 100, 25);
        registerNewOperativesTab.add(lastNameField);

        registerButton = new JButton("Register");
        registerButton.setBounds(150, 180, 150, 25);
        registerNewOperativesTab.add(registerButton);

    }

    public void deactivate(){
        // DEACTIVATE OPERATIVE
        deactivateOperativesTab = new JPanel();
        deactivateOperativesTab.setBounds(5, 50, 100, 200);
        deactivateOperativesTab.setBackground(Color.CYAN);
        deactivateOperativesTab.setLayout(null);
        deactivateOperativesTab.setVisible(false);

        // add table with all operatives, clickListener, button
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(12, 12, 350, 150);
        deactivateOperativesTab.add(scroll);

        // MODEL
        allActiveOperativesModel.setRowCount(0);
        allActiveOperativesModel.setColumnCount(4);
        allActiveOperativesModel.setColumnIdentifiers(columnNames);

        // TABLE
        deactivateOperativesTabTable = new JTable(allActiveOperativesModel);
        deactivateOperativesSorter = new TableRowSorter<TableModel>(allActiveOperativesModel);
        deactivateOperativesTabTable.setRowSorter(deactivateOperativesSorter);
        scroll.setViewportView(deactivateOperativesTabTable);

        deactivateButton = new JButton("Deactivate");
        deactivateButton.setBounds(150, 180, 150, 25);
        deactivateOperativesTab.add(deactivateButton);

        tabbedPane.addTab(deactivateOperativeTabTitle, deactivateOperativesTab);
    }

    public void delete(){
        // DELETE OPERATIVE
        deleteOperativesTab = new JPanel();
        deleteOperativesTab.setBounds(5, 50, 100, 200);
        deleteOperativesTab.setBackground(Color.ORANGE);
        deleteOperativesTab.setLayout(null);
        deleteOperativesTab.setVisible(false);

        // add table with all operatives, clickListener, button
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(12, 12, 350, 150);
        deleteOperativesTab.add(scroll);

        deleteOperativesTabTable = new JTable(allOperativesModel);
        // deleteOperativesTabTable.setRowSorter(deleteOperativesSorter);
        scroll.setViewportView(deleteOperativesTabTable);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 180, 100, 25);
        deleteOperativesTab.add(deleteButton);

        tabbedPane.addTab(deleteOperativeTabTitle, deleteOperativesTab);
    }


    // SETTERS AND GETTERS
    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    public void setAllOperativesTabTitle(String allOperativesTabTitle) {
        this.allOperativesTabTitle = allOperativesTabTitle;
    }

    public void setAllActiveOperativesTabTitle(String allActiveOperativesTabTitle) {
        this.allActiveOperativesTabTitle = allActiveOperativesTabTitle;
    }

    public void setRegisterNewOperativeTabTitle(String registerNewOperativeTabTitle) {
        this.registerNewOperativeTabTitle = registerNewOperativeTabTitle;
    }

    public void setDeactivateOperativeTabTitle(String deactivateOperativeTabTitle) {
        this.deactivateOperativeTabTitle = deactivateOperativeTabTitle;
    }

    public void setDeleteOperativeTabTitle(String deleteOperativeTabTitle) {
        this.deleteOperativeTabTitle = deleteOperativeTabTitle;
    }

    public List<Employee> getAllOperatives() {
        return allOperatives;
    }

    public void setAllOperatives(List<Employee> allOperatives) {
        this.allOperatives = allOperatives;
    }

    public List<Employee> getAllActiveOperatives() {
        return allActiveOperatives;
    }

    public void setAllActiveOperatives(List<Employee> allActiveOperatives) {
        this.allActiveOperatives = allActiveOperatives;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }

    public JPanel getAllOperativesTab() {
        return allOperativesTab;
    }

    public void setAllOperativesTab(JPanel allOperativesTab) {
        this.allOperativesTab = allOperativesTab;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getDeactivateButton() {
        return deactivateButton;
    }

    public void setDeactivateButton(JButton deactivateButton) {
        this.deactivateButton = deactivateButton;
    }

    public JPanel getAllActiveOperativesTab() {
        return allActiveOperativesTab;
    }

    public void setAllActiveOperativesTab(JPanel allActiveOperativesTab) {
        this.allActiveOperativesTab = allActiveOperativesTab;
    }

    public JTable getDeactivateOperativesTabTable() {
        return deactivateOperativesTabTable;
    }

    public void setDeactivateOperativesTabTable(JTable deactivateOperativesTabTable) {
        this.deactivateOperativesTabTable = deactivateOperativesTabTable;
    }

    public JTable getDeleteOperativesTabTable() {
        return deleteOperativesTabTable;
    }

    public void setDeleteOperativesTabTable(JTable deleteOperativesTabTable) {
        this.deleteOperativesTabTable = deleteOperativesTabTable;
    }

    public DefaultTableModel getAllOperativesModel() {
        return allOperativesModel;
    }

    public void setAllOperativesModel(DefaultTableModel allOperativesModel) {
        this.allOperativesModel = allOperativesModel;
    }

    public DefaultTableModel getAllActiveOperativesModel() {
        return allActiveOperativesModel;
    }

    public void setAllActiveOperativesModel(DefaultTableModel allActiveOperativesModel) {
        this.allActiveOperativesModel = allActiveOperativesModel;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columNames) {
        this.columnNames = columNames;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(JTextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(JTextField firstNameField) {
        this.firstNameField = firstNameField;
    }
}
