package presentation.controller.ViewControllers;

import data.Branch;
import data.Employee;
import data.dao.modelsDAO.EmployeesDAO;
import presentation.view.ViewClasses.EmployeeAddView;

import javax.swing.*;
public class EmployeeAddViewController {
    private static EmployeeAddView employee_add_view;
    private static EmployeesDAO empDAO = new EmployeesDAO();
    public static EmployeeAddView getEmployee_add_view() {
        employee_add_view = new EmployeeAddView();
        return employee_add_view;
    }
    public static void addButtonPressed(){
        String id = employee_add_view.getEmployeeID();
        String name = employee_add_view.getEmployeeName();
        String phone_number = employee_add_view.getEmployeePhoneNumber();
        String salary = employee_add_view.getEmployeeSalary();
        //Branch branch = employee_add_view.getEmployeeBranch(); agregar un branch de tipo Branch


        /*Employee employee = new Employee(id,name,phone_number, Double.valueOf(salary), branch);
        if(empDAO.create(employee)){
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente.");
        }else{
            JOptionPane.showMessageDialog(null, "Operación no realizada.");
        }*/
    }
    public static void windowClosed(){
        MainWindowViewController.windowInitialized();
    }



}
