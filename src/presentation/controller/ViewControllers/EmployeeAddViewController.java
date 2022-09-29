package presentation.controller.ViewControllers;

import business.DataServices;
import data.Branch;
import data.Employee;
import presentation.model.viewModels.BranchTableInfo;
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
        List<BranchTableInfo> branches = DataServices.getBranchesForTable();
        for(BranchTableInfo branch : branches){
            employee_add_view.setBranchPointOnMap(branch);
        }
    }
    public static void addButtonPressed(){
        String id = employee_add_view.getEmployeeID();
        String name = employee_add_view.getEmployeeName();
        String phone_number = employee_add_view.getEmployeePhoneNumber();
        String salary = employee_add_view.getEmployeeSalary();
        BranchTableInfo branch = employee_add_view.getSelectedBranch();

        if(!id.isEmpty() || !name.isEmpty() || !phone_number.isEmpty() || !salary.isEmpty()){

        }else{

        }
    }
    public static void windowClosed(){
        MainWindowViewController.updateTables();
    }
}
