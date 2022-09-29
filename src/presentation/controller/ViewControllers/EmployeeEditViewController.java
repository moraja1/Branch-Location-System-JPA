package presentation.controller.ViewControllers;

import data.Employee;
import data.dao.modelsDAO.EmployeesDAO;
import presentation.model.viewModels.BranchTableInfo;
import presentation.model.viewModels.EmployeeTableInfo;
import presentation.view.ViewClasses.EmployeeEditView;

import javax.swing.*;

public class EmployeeEditViewController {
    private static EmployeeEditView employee_edit_view;

    public static EmployeeEditView getEmployee_edit_view(Object[] model) {
        employee_edit_view = new EmployeeEditView(model);
        return employee_edit_view;
    }

    public static void saveButtonPressed() {
        String id = employee_edit_view.getEmployeeID();
        String name = employee_edit_view.getEmployeeName();
        String phone_number = employee_edit_view.getEmployeePhoneNumber();
        String salary = employee_edit_view.getEmployeeSalary();
        BranchTableInfo branch = employee_edit_view.getSelectedBranch();
        EmployeeTableInfo employeeTableInfo = new EmployeeTableInfo( id, name, phone_number, Double.valueOf(salary), branch.getReference());

        //Falta la implementacion de que hacer cuando se le da al boton guardar
        //Cree otro constructor en EmployeeTableInfo porque la ventana de editar no tiene para zonaje y demas

    }
    public static void windowClosed(){
        MainWindowViewController.updateTables();
    }
}
