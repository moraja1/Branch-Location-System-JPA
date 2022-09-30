package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.model.viewModels.BranchInfo;
import presentation.model.viewModels.EmployeeInfo;
import presentation.view.ViewClasses.EmployeeEditView;

import java.util.List;

public class EmployeeEditViewController {
    private static EmployeeEditView employee_edit_view = new EmployeeEditView();

    public static EmployeeEditView getEmployee_edit_view() {
        return employee_edit_view;
    }
    public static void saveButtonPressed() {
        /*String id = employee_edit_view.getEmployeeID();
        String name = employee_edit_view.getEmployeeName();
        String phone_number = employee_edit_view.getEmployeePhoneNumber();
        String salary = employee_edit_view.getEmployeeSalary();
        BranchInfo branch = employee_edit_view.getSelectedBranch();
        EmployeeInfo employeeInfo = new EmployeeInfo(id, name, phone_number, Double.valueOf(salary), branch.getReference());*/

        //Falta la implementacion de que hacer cuando se le da al boton guardar
        //Cree otro constructor en EmployeeTableInfo porque la ventana de editar no tiene para zonaje y demas

    }
    public static void windowInitialize() {
        Object[] model = MainWindowViewController.getObjectModel();
        employee_edit_view.setModel(model);

        String reference = (String) model[4];
        BranchInfo b = DataServices.getBranchInfo(reference);
        if(b != null){
            b.setSelected(true);
            updateImages(b);
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
        for(BranchInfo branch : branches){
            employee_edit_view.setBranchPointOnMap(branch);
        }
    }
    public static void windowClosed(){
        MainWindowViewController.updateTables();
    }
}
