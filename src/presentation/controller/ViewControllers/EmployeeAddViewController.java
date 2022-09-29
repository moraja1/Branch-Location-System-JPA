package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.model.viewModels.BranchInfo;
import presentation.model.viewModels.EmployeeInfo;
import presentation.view.ViewClasses.EmployeeAddView;

import javax.swing.*;
import java.util.List;

public class EmployeeAddViewController {
    private static final EmployeeAddView employee_add_view = new EmployeeAddView();
    public static EmployeeAddView getEmployee_add_view() {
        return employee_add_view;
    }
    public static void windowInitialized() {
        updateImages();
    }
    private static void updateImages() {
        List<BranchInfo> branches = DataServices.getBranchesForTable();
        for(BranchInfo branch : branches){
            employee_add_view.setBranchPointOnMap(branch);
        }
    }
    public static void addButtonPressed(){
        String id = employee_add_view.getEmployeeID();
        String name = employee_add_view.getEmployeeName();
        String phone_number = employee_add_view.getEmployeePhoneNumber();
        double salary = Double.parseDouble(employee_add_view.getEmployeeSalary());
        BranchInfo branch = employee_add_view.getSelectedBranch();

        EmployeeInfo employee = new EmployeeInfo(id, name, phone_number, salary);

        if(DataServices.addEmployeeExecution(employee, branch)){
            JOptionPane.showMessageDialog(new JFrame(), "Empleado agregado correctamente", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Error en almacenamiento de los datos", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void windowClosed(){
        MainWindowViewController.updateTables();
    }
}
