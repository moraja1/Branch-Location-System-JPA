package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.model.viewModels.BranchInfo;
import presentation.model.viewModels.EmployeeInfo;
import presentation.view.ViewClasses.EmployeeEditView;

import javax.swing.*;
import java.util.List;

public class EmployeeEditViewController {
    private static EmployeeEditView employee_edit_view = new EmployeeEditView();

    public static EmployeeEditView getEmployee_edit_view() {
        return employee_edit_view;
    }
    public static void saveButtonPressed() {
        String id = employee_edit_view.getEmployeeID();
        String name = employee_edit_view.getEmployeeName();
        String phone_number = employee_edit_view.getEmployeePhoneNumber();
        double salary = Double.parseDouble(employee_edit_view.getEmployeeSalary());
        BranchInfo branch = employee_edit_view.getSelectedBranch();
        String reference = branch.getReference();

        EmployeeInfo employee = new EmployeeInfo(id, name, phone_number, salary, reference);

        if(DataServices.editEmployeeExecution(employee, branch)){
            JOptionPane.showMessageDialog(new JFrame(), "Empleado editado correctamente", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Error en almacenamiento de los datos", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void windowInitialize() {
        Object[] model = MainWindowViewController.getObjectModel();
        employee_edit_view.setModel(model);

        String reference = (String) model[4];
        BranchInfo b = DataServices.getBranchInfo(reference);
        if(b != null){
            b.setSelected(true);
            updateImages(b);
        }else {
            updateImages();
        }
    }
    private static void updateImages() {
        List<BranchInfo> branches = DataServices.getBranchesForTable();
        for(BranchInfo branch : branches){
            employee_edit_view.setBranchPointOnMap(branch);
        }
    }
    private static void updateImages(BranchInfo b) {
        List<BranchInfo> branches = DataServices.getBranchesForTable();
        BranchInfo branchDeleter = null;
        for(BranchInfo bi : branches){
            if(bi.getId().equals(b.getId())){
                branchDeleter = bi;
            }
        }
        if(branchDeleter != null){
            branches.remove(branchDeleter);
            branches.add(b);
        }
        employee_edit_view.setSelectedBranch(b);
        for(BranchInfo branch : branches){
            employee_edit_view.setBranchPointOnMap(branch);
        }
    }
    public static void windowClosed(){
        employee_edit_view = null;
        employee_edit_view = new EmployeeEditView();
        MainWindowViewController.updateTables();
    }
}
