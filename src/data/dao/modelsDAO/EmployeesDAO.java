package data.dao.modelsDAO;

import data.repository.Branch;
import data.repository.Employee;
import data.dao.DAO;
import data.xmlParsers.ModelsParsers.BranchXML;
import data.xmlParsers.ModelsParsers.EmployeeXML;

import java.util.HashMap;
import java.util.List;

public class EmployeesDAO extends DAO<Employee> {

    @Override
    public boolean add(Employee obj) {
        String id = obj.getId();
        xml = new EmployeeXML();
        Employee employeePersisted;
        try {
            employeePersisted = (Employee) xml.getObject(id);
        }catch (Exception e){
            e.getCause();
            employeePersisted = null;
        }
        if(employeePersisted == null){
            try {
                xml.insertElement(obj);
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean erase(Employee obj) {
        String id = obj.getId();
        Branch branch = obj.getBranch();

        xml = new EmployeeXML();
        Employee employeePersisted;
        try {
            employeePersisted = (Employee) xml.getObject(id);
            if(employeePersisted != null){
                xml.eraseElement(obj);
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(Employee obj) {
        xml = new EmployeeXML();
        try {
            Employee employeePersisted = (Employee) xml.getObject(obj.getId());
            if(employeePersisted != null){
                xml.mergeElement(obj);

                xml = new BranchXML();
                String branchPersistedID = employeePersisted.getBranch().getId();
                Branch branchPersisted = (Branch) xml.getObject(branchPersistedID);
                List<Employee> employees = branchPersisted.getEmployees();
                if(employees.contains(obj) && !obj.getBranch().getId().equals(branchPersistedID)){
                    BranchesDAO dao = new BranchesDAO();
                    dao.removeEmployee(branchPersisted, obj);
                }
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public HashMap<String, Employee> getAllObjects() {
        xml = new EmployeeXML();
        try {
            HashMap<String, Employee> employeeHashMap = xml.getObjectsHashMap();
            if(employeeHashMap != null){
                return employeeHashMap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, Employee>();
    }

    @Override
    public Employee getSingleObject(String key) {
        xml = new EmployeeXML();
        try {
            Employee employee = (Employee) xml.getObject(key);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean removeBranch(Employee e){
        xml = new EmployeeXML();
        try {
            Employee employeePersisted = (Employee) xml.getObject(e.getId());
            if(employeePersisted != null){
                EmployeeXML xml = new EmployeeXML();
                xml.removeBranchFromEmployee(employeePersisted);
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean changeBranch(Employee e) {
        EmployeeXML xml = new EmployeeXML();
        try {
            xml.addBranchToEmployee(e);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
