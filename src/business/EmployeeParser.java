package business;

import data.Branch;
import data.Employee;
import data.dao.modelsDAO.BranchesDAO;
import presentation.model.viewModels.EmployeeInfo;

public class EmployeeParser {
    public static Employee toEmployee(EmployeeInfo e){
        //Create Employee
        String id = e.getId();
        String name = e.getName();
        String phone_number = e.getPhone_number();
        double base_salary = e.getBase_salary();
        String reference = e.getBranch_reference();

        BranchesDAO dataDAO = new BranchesDAO();
        Branch branch = dataDAO.getBranchByReference(reference);
        if(branch != null){
            return new Employee(id, name, phone_number, base_salary, branch);
        }
        return null;
    }
}
