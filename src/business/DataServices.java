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

            String id = emp.getId();
            String name = emp.getName();
            String phone_number = emp.getPhone_number();
            double base_salary = emp.getBase_salary();
            String branch_reference;
            double zoning_percentage;
            double total_salary;
            if(branch == null){
                branch_reference = "null";
                zoning_percentage = 0.0;
                total_salary = 0.0;
            }else{
                branch_reference = branch.getReference();
                zoning_percentage = branch.getZoning_percentage();
                total_salary = (base_salary * zoning_percentage) - base_salary;
            }
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
    public static boolean editEmployeeExecution(EmployeeInfo e, BranchInfo b) {
        //Get Branch
        dataDAO = new BranchesDAO();
        Branch newBranch = (Branch) dataDAO.getSingleObject(b.getId());
        Employee newEmployee = EmployeeParser.toEmployee(e);

        dataDAO = new EmployeesDAO();
        Employee oldEmployee = (Employee) dataDAO.getSingleObject(e.getId());

        List<Employee> employeesOnNewBranch = newBranch.getEmployees();
        boolean containsEmployee = false;
        for(Employee em : employeesOnNewBranch){
            if(em.getId().equals(newEmployee.getId())) {
                containsEmployee = true;
            }
        }
        if(!containsEmployee) {
            newBranch.getEmployees().add(newEmployee);
            BranchesDAO dataDAO = new BranchesDAO();
            dataDAO.edit(newBranch);

            Branch oldBranch = dataDAO.getSingleObject(oldEmployee.getBranch().getId());
            if(oldBranch != null){
                List<Employee> employeesOnOldBranch = oldBranch.getEmployees();
                Employee employeeEraser = null;
                for(Employee em : employeesOnOldBranch){
                    if(em.getId().equals(newEmployee.getId())) {
                        employeeEraser = em;
                    }
                }
                if(employeeEraser != null) {
                    oldBranch.getEmployees().remove(employeeEraser);
                    dataDAO.edit(oldBranch);
                }
            }
        }
        dataDAO = new EmployeesDAO();
        return dataDAO.edit(newEmployee);
    }

    public static boolean addBranchExecution(BranchInfo b) {
        Branch newBranch = BranchParser.toBranch(b);
        Coordinates coordinates = newBranch.getCoords();

        dataDAO = new BranchesDAO();
        if(dataDAO.add(newBranch)){
            dataDAO = new CoordinatesDAO();
            return dataDAO.add(coordinates);
        }
        return false;
    }

    public static boolean removeBranchExecution(BranchInfo b) {
        dataDAO = new BranchesDAO();
        Branch branch = (Branch) dataDAO.getSingleObject(b.getId());
        dataDAO = new EmployeesDAO();
        List<Employee> employeesOfBranch = new ArrayList<>();
        for(Employee em : branch.getEmployees()){
            Employee emp = (Employee) dataDAO.getSingleObject(em.getId());
            emp.setBranch(null);
            employeesOfBranch.add(emp);
        }
        if(branch != null){
            Coordinates coordinates = branch.getCoords();
            dataDAO = new CoordinatesDAO();
            coordinates = (Coordinates) dataDAO.getSingleObject(coordinates.getId());
            dataDAO = new BranchesDAO();
            if(dataDAO.erase(branch)){
                dataDAO = new EmployeesDAO();
                for(Employee em : employeesOfBranch){
                    dataDAO.edit(em);
                }
                dataDAO = new CoordinatesDAO();
                return dataDAO.erase(coordinates);
            }
        }
        return false;
    }

    public static boolean editBranchExecution(BranchInfo b) {
        Branch newBranch = BranchParser.toBranch(b);
        Coordinates coordinates = newBranch.getCoords();

        dataDAO = new BranchesDAO();
        if(dataDAO.edit(newBranch)){
            dataDAO = new CoordinatesDAO();
            return dataDAO.edit(coordinates);
        }
        return false;
    }
}
