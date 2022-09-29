package business;

import data.Branch;
import data.Coordinates;
import data.Employee;
import data.dao.DAO;
import data.dao.modelsDAO.BranchesDAO;
import data.dao.modelsDAO.CoordinatesDAO;
import data.dao.modelsDAO.EmployeesDAO;
import presentation.model.viewModels.BranchTableInfo;
import presentation.model.viewModels.EmployeeTableInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataServices {
    private static DAO dataDAO;
    public static List<EmployeeTableInfo> getEmployeesForTable() {
        dataDAO = new EmployeesDAO();
        HashMap<String, Employee> dataEmployees = dataDAO.getAllObjects();
        List<Employee> dataEmployeesList = dataEmployees.values().stream().toList();
        dataDAO = new BranchesDAO();
        HashMap<String, Branch> dataBranches = dataDAO.getAllObjects();

        List<EmployeeTableInfo> employees = new ArrayList<>();

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


            employees.add(new EmployeeTableInfo(id, name, phone_number, base_salary, branch_reference,
                    zoning_percentage, total_salary));
        }
        return employees;
    }
    public static List<BranchTableInfo> getBranchesForTable() {
        dataDAO = new BranchesDAO();
        HashMap<String, Branch> dataBranches = dataDAO.getAllObjects();
        List<Branch> dataBranchesList = dataBranches.values().stream().toList();

        dataDAO = new CoordinatesDAO();
        HashMap<String, Coordinates> dataCoords = dataDAO.getAllObjects();

        List<BranchTableInfo> branches = new ArrayList<>();
        for(Branch branch : dataBranchesList){
            Coordinates coord = dataCoords.get(branch.getCoords().getId());
            if(coord == null){
                continue;
            }
            String id = branch.getId();
            String reference = branch.getReference();
            String address = branch.getAddress();
            double zoning_percentage = branch.getZoning_percentage();
            String coords = new StringBuilder().append(coord.getX()).append(", ").append(coord.getY()).toString();

            branches.add(new BranchTableInfo(id, reference, address, zoning_percentage, coords));
        }
        return branches;
    }

}
