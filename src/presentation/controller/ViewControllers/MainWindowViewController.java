package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.controller.flowController.MainController;
import presentation.model.viewModels.BranchInfo;
import presentation.model.viewModels.EmployeeInfo;
import presentation.model.viewModels.componentModels.BranchTableModel;
import presentation.model.viewModels.componentModels.EmployeeTableModel;
import presentation.view.ViewClasses.MainWindow;

import javax.swing.*;
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
                List<EmployeeInfo> employees = DataServices.getEmployeesForTable();
                EmployeeTableModel empTableModel = new EmployeeTableModel(employees);
                main_window.setTableModel(empTableModel);
                break;
            case 1:
                List<BranchInfo> branches = DataServices.getBranchesForTable();
                BranchTableModel braTableModel = new BranchTableModel(branches);
                main_window.setTableModel(braTableModel);
                break;
            default:
                break;
        }
    }
    private static void updateImages() {
        List<BranchInfo> branches = DataServices.getBranchesForTable();
        for(BranchInfo branch : branches){
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
        double salary = (Double)getObjectModel()[3];
        String reference = String.valueOf(getObjectModel()[4]);
        double zone_percentage = (Double)getObjectModel()[5];
        double total_salary = (Double)getObjectModel()[6];

        EmployeeInfo employee = new EmployeeInfo(id, name, phone_number, salary, reference, zone_percentage, total_salary);
        if(DataServices.removeEmployeeExecution(employee)){
            JOptionPane.showMessageDialog(new JFrame(), "Empleado eliminado correctamente",
                    "Eliminar Empleado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Empleado no eliminado",
                    "Eliminar Empleado", JOptionPane.INFORMATION_MESSAGE);
        }
        updateTables();
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
        Object[] model = new Object[empTable.getColumnCount()];
        for(int i = 0; i < empTable.getColumnCount(); i++){
            model[i] = empTable.getValueAt(row, i);
        }
        return model;
    }
}
