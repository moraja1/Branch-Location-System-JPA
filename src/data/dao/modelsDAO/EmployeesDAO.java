package data.dao.modelsDAO;

import data.Coordinates;
import data.Employee;
import data.dao.DAO;
import data.xmlParsers.ModelsParsers.CoordinateXML;
import data.xmlParsers.ModelsParsers.EmployeeXML;

import java.util.HashMap;

public class EmployeesDAO extends DAO<Employee> {

    @Override
    public boolean add(Employee obj) {
        String id = obj.getId();
        xml = new EmployeeXML();
        Employee employeePersisted;
        try {
            employeePersisted = (Employee) xml.getObject(id);
        }catch (Exception e){
            e.printStackTrace();
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
        xml = new EmployeeXML();
        Employee employeePersisted;
        try {
            employeePersisted = (Employee) xml.getObject(id);
        }catch (Exception e){
            e.printStackTrace();
            employeePersisted = null;
        }
        if(employeePersisted != null){
            try {
                xml.eraseElement(id);
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean edit(Employee obj) {
        return false;
    }

    @Override
    public HashMap<String, Employee> getAllObjects() {
        return null;
    }

    @Override
    public Employee getSingleObject() {
        return null;
    }
}
