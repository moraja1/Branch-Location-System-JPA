package model.dao.modelsDAO;

import model.Branch;
import model.Coordinates;
import model.dao.DAO;

import java.util.HashMap;

public class CoordinatesDAO extends DAO<Coordinates> {
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
    public HashMap<String, Coordinates> getAllObjects() {
        return null;
    }

    @Override
    public Coordinates getSingleObject() {
        return null;
    }
}
