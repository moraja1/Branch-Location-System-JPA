package model.dao.modelsDAO;

import model.Branch;
import model.dao.DAO;

import java.util.HashMap;

public class BranchesDAO extends DAO<Branch> {
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
    public HashMap<String, Branch> getAllObjects() {
        return null;
    }

    @Override
    public Branch getSingleObject() {
        return null;
    }
}
