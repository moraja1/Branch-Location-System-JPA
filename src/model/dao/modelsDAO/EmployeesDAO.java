package model.dao.modelsDAO;

import model.Branch;
import model.Employee;
import model.dao.DAO;

import java.util.HashMap;

public class EmployeesDAO extends DAO<Employee> {
    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean erase() {
        return false;
    }

    @Override
    public boolean edit() {
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
