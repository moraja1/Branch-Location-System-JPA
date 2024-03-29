package data.dao.modelsDAO;

import data.repository.Branch;

import data.repository.Coordinates;
import data.repository.Employee;
import data.dao.DAO;
import data.xmlParsers.ModelsParsers.BranchXML;

import java.util.HashMap;
import java.util.List;

public class BranchesDAO extends DAO<Branch> {

    @Override
    public boolean add(Branch obj) {
        String id = obj.getId();
        Branch branchPersisted;
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
            if (branchPersisted != null) {
                xml.eraseElement(obj);
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
    public Branch getBranchByReference(String reference) {
        BranchXML xml = new BranchXML();
        try {
            Branch branch = (Branch) xml.getBranchByReference(reference);
            if(branch != null){
                return branch;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
