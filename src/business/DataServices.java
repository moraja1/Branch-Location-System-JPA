package business;

import data.repository.Branch;
import data.repository.Coordinates;
import data.repository.Employee;
import data.dao.DAO;
import data.dao.modelsDAO.BranchesDAO;
import data.dao.modelsDAO.CoordinatesDAO;
import data.dao.modelsDAO.EmployeesDAO;
import presentation.model.viewModels.BranchInfo;
import presentation.model.viewModels.EmployeeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataServices {
    private static DAO dataDAO;
    public static List<EmployeeInfo> getEmployeesForTable() {
        dataDAO = new EmployeesDAO();
        HashMap<String, Employee> dataEmployees = dataDAO.getAllObjects();
        List<Employee> dataEmployeesList = dataEmployees.values().stream().toList();
        dataDAO = new BranchesDAO();
        HashMap<String, Branch> dataBranches = dataDAO.getAllObjects();

        List<EmployeeInfo> employees = new ArrayList<>();

        for (Employee emp : dataEmployeesList){
            Branch branch = dataBranches.get(emp.getBranch().getId());
            if(branch == null){
                continue;
            }
            String id = emp.getId();
            String name = emp.getName();
            String phone_number = emp.getPhone_number();
            double base_salary = emp.getBase_salary();
            String branch_reference = branch.getReference();
            double zoning_percentage = branch.getZoning_percentage();
            double total_salary = (base_salary * zoning_percentage) - base_salary;


            employees.add(new EmployeeInfo(id, name, phone_number, base_salary, branch_reference,
                    zoning_percentage, total_salary));
        }
        return employees;
    }
    public static List<BranchInfo> getBranchesForTable() {
        dataDAO = new BranchesDAO();
        HashMap<String, Branch> dataBranches = dataDAO.getAllObjects();
        List<Branch> dataBranchesList = dataBranches.values().stream().toList();

        dataDAO = new CoordinatesDAO();
        HashMap<String, Coordinates> dataCoords = dataDAO.getAllObjects();

        List<BranchInfo> branches = new ArrayList<>();
        for(Branch branch : dataBranchesList){
            BranchInfo bInfo = BranchParser.toBranchInfo(branch);
            if(bInfo != null){
                branches.add(bInfo);
            }
        }
        return branches;
    }
    public static boolean addEmployeeExecution(EmployeeInfo e, BranchInfo b) {
        //Get Branch
        dataDAO = new BranchesDAO();
        Branch branch = (Branch) dataDAO.getSingleObject(b.getId());
        if(branch == null){
            return false;
        }

        Employee employee = EmployeeParser.toEmployee(e);
        branch.getEmployees().add(employee);

        if(dataDAO.edit(branch)){
            dataDAO = new EmployeesDAO();
            return dataDAO.add(employee);
        }
        return false;
    }
    public static boolean removeEmployeeExecution(EmployeeInfo e) {
        Employee employee = EmployeeParser.toEmployee(e);
        if(employee != null){
            dataDAO = new EmployeesDAO();
            dataDAO.erase(employee);

            Branch branch = employee.getBranch();
            List<Employee> employeeList = branch.getEmployees();
            Employee eCopy = null;
            for (Employee a : employeeList){
                if(a.getId().equals(employee.getId())){
                    eCopy = a;
                }
            }
            if (eCopy != null) {
                employeeList.remove(eCopy);
            }
            branch.setEmployees(employeeList);

            dataDAO = new BranchesDAO();
            return dataDAO.edit(branch);

        }
        return false;
    }

    public static BranchInfo getBranchInfo(String key) {
        BranchesDAO dataDAO = new BranchesDAO();
        Branch branch = dataDAO.getSingleObject(key);
        if(branch == null){
            branch = dataDAO.getBranchByReference(key);
            if (branch == null){
                return null;
            }
        }
        BranchInfo b = BranchParser.toBranchInfo(branch);
        return b;
    }
}
