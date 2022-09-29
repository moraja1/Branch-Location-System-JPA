package data.dao.modelsDAO;

import data.Branch;

import data.Coordinates;
import data.Employee;
import data.dao.DAO;
import data.xmlParsers.ModelsParsers.BranchXML;
import data.xmlParsers.ModelsParsers.CoordinateXML;
import data.xmlParsers.ModelsParsers.EmployeeXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BranchesDAO extends DAO<Branch> {

    @Override
    public boolean add(Branch obj) {
        String id = obj.getId();
        Branch branchPersisted;
        Coordinates coordinates = obj.getCoords();
        coordinates.setId(obj.getId());
        try {
            xml = new BranchXML();
            branchPersisted = (Branch) xml.getObject(id);
        }catch (Exception e){
            e.printStackTrace();
            branchPersisted = null;
        }
        if(branchPersisted == null){
            try {
                xml.insertElement(obj);
                dao = new CoordinatesDAO();
                dao.add(coordinates);
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean erase(Branch obj) {
        String id = obj.getId();
        xml = new BranchXML();
        Branch branchPersisted;
        Coordinates coordinates = obj.getCoords();
        List<Employee> employees = obj.getEmployees();
        try {
            branchPersisted = (Branch) xml.getObject(id);
            if (branchPersisted != null) {
                xml.eraseElement(id);
                if(coordinates != null){
                    dao = new CoordinatesDAO();
                    dao.erase(coordinates);
                }
                if (employees != null) {
                    EmployeesDAO dao = new EmployeesDAO();
                    for (Employee e : employees) {
                        dao.removeBranch(e);
                    }
                }
            }
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(Branch obj) {
        String id = obj.getId();
        xml = new BranchXML();
        Branch branchPersisted;
        try {
            branchPersisted = (Branch) xml.getObject(id);
            if (branchPersisted != null) {
                xml.mergeElement(obj);
            }
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public HashMap<String, Branch> getAllObjects() {
        xml = new BranchXML();
        try {
            HashMap<String, Branch> branchesHashMap = xml.getObjectsHashMap();
            if(branchesHashMap != null){
                return branchesHashMap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, Branch>();
    }

    @Override
    public Branch getSingleObject(String key) {
        xml = new BranchXML();
        try {
            Branch branch = (Branch) xml.getObject(key);
            return branch;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean removeEmployee(Branch b, Employee e){
        BranchXML xml = new BranchXML();
        try {
            List<Employee> employees = b.getEmployees();
            if(employees != null && employees.contains(e)){
                xml.removeEmployeeFromBranch(b, e);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
