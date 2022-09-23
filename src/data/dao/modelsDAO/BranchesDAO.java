package data.dao.modelsDAO;

import data.Branch;

import data.Coordinates;
import data.dao.DAO;
import data.xmlParsers.ModelsParsers.BranchXML;
import data.xmlParsers.ModelsParsers.CoordinateXML;

import java.util.HashMap;

public class BranchesDAO extends DAO<Branch> {

    @Override
    public boolean add(Branch obj) {
        String id = obj.getId();
        xml = new BranchXML();
        Branch branchPersisted;
        try {
            branchPersisted = (Branch) xml.getObject(id);
        }catch (Exception e){
            e.printStackTrace();
            branchPersisted = null;
        }
        if(branchPersisted == null){
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
    public boolean erase(Branch obj) {
        String id = obj.getId();
        xml = new BranchXML();
        Branch branchPersisted;
        try {
            branchPersisted = (Branch) xml.getObject(id);
        }catch (Exception e){
            e.printStackTrace();
            branchPersisted = null;
        }
        if(branchPersisted != null){
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
    public boolean edit(Branch obj) {
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
