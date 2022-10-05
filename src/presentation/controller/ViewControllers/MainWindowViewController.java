package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.controller.flowController.MainController;
import presentation.model.viewModels.BranchInfo;
import presentation.model.viewModels.EmployeeInfo;
import presentation.model.viewModels.componentModels.BranchTableModel;
import presentation.model.viewModels.componentModels.EmployeeTableModel;
import presentation.view.ViewClasses.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainWindowViewController {
    private static MainWindow main_window = new MainWindow();

    public static MainWindow getMain_window() {
        return main_window;
    }
    public static void windowInitialized() {
        cleanWindow();
        updateTables();
        updateImages();
    }
    private static void cleanWindow(){
        main_window.cleanLayers();
    }
    private static void updateTables() {
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
        MainController.changeWindow(EmployeeEditViewController.getEmployee_edit_view());
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
        String id = main_window.getEmployeesSearchBar();
        if(id.isEmpty()){
            updateTables();
        }else{
            List<EmployeeInfo> employees = DataServices.getEmployeesForTable();
            List<EmployeeInfo> employeesFinded = new ArrayList<>();
            for(EmployeeInfo e : employees){
                if(e.getId().equals(id) || e.getName().equals(id) || e.getPhone_number().equals(id) || e.getBranch_reference().equals(id)){
                    employeesFinded.add(e);
                }
            }

            if(!employeesFinded.isEmpty()){
                EmployeeTableModel empTableModel = new EmployeeTableModel(employeesFinded);
                main_window.setTableModel(empTableModel);
            }
        }
    }
    public static void reportEmployee() {
        //LE CORRESPONDE A BRANDON HACERLO
    }
    public static void addBranch() {
        MainController.changeWindow(BranchAddViewController.getBranch_add_view());
    }
    public static void editBranch() {
        Object[] model = getObjectModel();
        MainController.changeWindow(BranchEditViewController.getBranch_edit_view(model));
    }
    public static void eraseBranch() {
        String id = String.valueOf(getObjectModel()[0]);
        String reference = String.valueOf(getObjectModel()[1]);
        String address = String.valueOf(getObjectModel()[2]);
        double zoning_percentage = (Double)getObjectModel()[3];
        String cords = String.valueOf(getObjectModel()[4]);

        BranchInfo branchInfo = new BranchInfo(id, reference, address, zoning_percentage, cords);
        if(DataServices.removeBranchExecution(branchInfo)){
            JOptionPane.showMessageDialog(new JFrame(), "Sucursal eliminada correctamente",
                    "Eliminar Sucursal", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Sucursal no eliminada",
                    "Eliminar Sucursal", JOptionPane.INFORMATION_MESSAGE);
        }
        windowInitialized();
    }
    public static void searchBranch() {
        String id = main_window.getBranchesSearchBar();
        if(id.isEmpty()){
            updateTables();
        }else{
            List<BranchInfo> branches = DataServices.getBranchesForTable();
            List<BranchInfo> branchesFinded = new ArrayList<>();
            for(BranchInfo e : branches){
                if(e.getId().equals(id) || e.getReference().equals(id) || e.getCoords().equals(id)){
                    e.setSelected(true);
                    branchesFinded.add(e);
                }
            }
            BranchTableModel branchTableModel = new BranchTableModel(branchesFinded);
            main_window.setTableModel(branchTableModel);
            branchesFinded.forEach(a-> main_window.selectTableRow(a));
            main_window.cleanLayers();
            branchesFinded.forEach(a-> main_window.setBranchPointOnMap(a));
        }
    }
    public static void reportBranch() {
        //LE CORRESPONDE A BRANDON HACERLO
    }
    public static Object[] getObjectModel(){
        JTable table = main_window.getSelectedTable();
        int row = table.getSelectedRow();

        Object[] model = new Object[table.getColumnCount()];

        for(int i = 0; i < table.getColumnCount(); i++){
            model[i] = table.getValueAt(row, i);
        }
        return model;
    }
    public static void mapClicked(MouseEvent e) {
        List<BranchInfo> branches = main_window.getPoints();
        for (BranchInfo branch : branches) {
            if(branch.isSelected()){
                branch.mouseClickedOutside(e);
            }
        }
        main_window.removeTableSelection();
    }
    public static void mapClickedOutside(MouseEvent e) {
        List<BranchInfo> branches = main_window.getPoints();
        for (BranchInfo branch : branches) {
            if(branch.isSelected()){
                main_window.selectTableRow(branch);

            }
        }
    }
    public static void tableRowSeleted(MouseEvent e) {
        List<BranchInfo> branches = main_window.getPoints();
        for (BranchInfo branch : branches) {
            if(branch.isSelected()){
                branch.mouseClickedOutside(e);
            }
        }
        Object[] model = getObjectModel();
        for (BranchInfo branch : branches) {
            if(branch.getId().equals(model[0])){
                branch.mouseClicked(e);
            }
        }
    }
    public static BranchInfo getSelectedPoint() {
        List<BranchInfo> branches = main_window.getPoints();
        for (BranchInfo branch : branches) {
            if(branch.isSelected()){
                return branch;
            }
        }
        return null;
    }
}
