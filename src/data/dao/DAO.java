package data.dao;

import data.xmlParsers.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;

/**
 * This creates a DAO template for any object's DAO development.
 */
public abstract class DAO<T> {
    protected XMLParser xml;
    protected DAO dao;
    public DAO(){
    }
    protected boolean exists(String key){
        try{
            HashMap<String, T> objects = new HashMap<>(xml.getObjectsHashMap());
            if(objects.containsKey(key)){
                return true;
            }else{
                return false;
            }
        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            e.printStackTrace();
            return false;
        }
    }
    public abstract boolean add(T obj);
    public abstract boolean erase(T obj);
    public abstract boolean edit(T obj);
    public abstract HashMap<String, T> getAllObjects();
    public abstract T getSingleObject(String key);


}
