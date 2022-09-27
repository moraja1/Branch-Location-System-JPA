package presentation.controller.ViewControllers;

import data.Employee;
import data.dao.modelsDAO.EmployeesDAO;
import presentation.view.ViewClasses.EmployeeEditView;

import javax.swing.*;

public class EmployeeEditViewController {
    private static EmployeeEditView employee_edit_view;
    private static EmployeesDAO empDAO = new EmployeesDAO();

    public static EmployeeEditView getEmployee_edit_view(Object[] model) {
        employee_edit_view = new EmployeeEditView(model);
        return employee_edit_view;
    }

    public static void saveButtonPressed() {
        String id = employee_edit_view.getEmployeeID();
        String name = employee_edit_view.getEmployeeName();
        String age = employee_edit_view.getEmployeePhoneNumber();
        String salary = employee_edit_view.getEmployeeSalary();
        //Branch branch = employee_edit_view.getEmployeeBranch(); agregar un branch de tipo Branch

        /*Employee employee = new Employee(id, name, Integer.parseInt(age), Double.valueOf(salary));
        if(empDAO.update(employee)){
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente.");
        }else{
            JOptionPane.showMessageDialog(null, "Operación no realizada.");
        }
         */
    }
    public static void windowClosed(){
        MainWindowViewController.updateTables();
    }
}
