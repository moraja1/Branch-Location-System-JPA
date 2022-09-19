package model.dao;

import org.w3c.dom.Document;

import java.util.HashMap;

/**
 * This creates a DAO template for any object's DAO development.
 */
public abstract class DAO<T> {
    public DAO(){
    }

    public abstract boolean add();
    public abstract boolean erase();
    public abstract boolean edit();
    public abstract HashMap<String, T> getAllObjects();
    public abstract T getSingleObject();


}
