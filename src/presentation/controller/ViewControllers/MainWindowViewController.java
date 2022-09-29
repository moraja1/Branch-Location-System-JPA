package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.controller.flowController.MainController;
import presentation.model.viewModels.BranchTableInfo;
import presentation.model.viewModels.EmployeeTableInfo;
import presentation.model.viewModels.componentModels.BranchTableModel;
import presentation.model.viewModels.componentModels.EmployeeTableModel;
import presentation.view.ViewClasses.MainWindow;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.List;

public class MainWindowViewController {
    private static MainWindow main_window = new MainWindow();

    public static MainWindow getMain_window() {
        return main_window;
    }
    public static void windowInitialized() {
        updateTables();
        updateImages();
    }
    public static void updateTables() {
        int selectedTab = main_window.getSelectedTabIndex();
        switch (selectedTab){
            case 0:
                List<EmployeeTableInfo> employees = DataServices.getEmployeesForTable();
                EmployeeTableModel empTableModel = new EmployeeTableModel(employees);
                main_window.setTableModel(empTableModel);
                break;
            case 1:
                List<BranchTableInfo> branches = DataServices.getBranchesForTable();
                BranchTableModel braTableModel = new BranchTableModel(branches);
                main_window.setTableModel(braTableModel);
                break;
            default:
                break;
        }
    }
    private static void updateImages() {
        List<BranchTableInfo> branches = DataServices.getBranchesForTable();
        for(BranchTableInfo branch : branches){
            main_window.setBranchPointOnMap(branch);
        }
    }
    public static void addEmployee() {
        MainController.changeWindow(EmployeeAddViewController.getEmployee_add_view());
    }
    public static void editEmployee() {
        Object[] model = getObjectModel();
        MainController.changeWindow(EmployeeEditViewController.getEmployee_edit_view(model));
    }
    public static void eraseEmployee() {
        String id = String.valueOf(getObjectModel()[0]);
        String name = String.valueOf(getObjectModel()[1]);
        String phone_number = String.valueOf(getObjectModel()[2]);
        Double salary = (Double)getObjectModel()[3];
        String reference = String.valueOf(getObjectModel()[4]);
        Double zon_percentage = (Double)getObjectModel()[5];
        Double total_salary = (Double)getObjectModel()[6];

        //Falta implementacion para el delete junto al update table

    }
    public static void searchEmployee() {
    }
    public static void reportEmployee() {
    }
    //BRANCHES
    public static void addBranch() {
        MainController.changeWindow(BranchAddViewController.getBranch_add_view());
    }
    public static void editBranch() {
        Object[] model = getObjectModelBranch();
        MainController.changeWindow(BranchEditViewController.getBranch_edit_view(model));
    }
    public static void eraseBranch() {
    }
    public static void searchBranch() {
    }
    public static void reportBranch() {
    }

    private static Object[] getObjectModelBranch(){
        JTable depTable = main_window.getDepartmentTable();
        int row = depTable.getSelectedRow();
        Object[] model = new Object[2];
        for(int i = 0; i < 2; i++){
            model[i] = depTable.getValueAt(row, i);
        }
        return model;
    }
    private static Object[] getObjectModel(){
        JTable empTable = main_window.getEmployeeTable();
        int row = empTable.getSelectedRow();
        Object[] model = new Object[4];
        for(int i = 0; i < 4; i++){
            model[i] = empTable.getValueAt(row, i);
        }
        return model;
    }
}
