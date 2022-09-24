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
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;

public class CoordinatesDAO extends DAO<Coordinates> {

    @Override
    public boolean add(Coordinates obj) {
        String id = obj.getId();
        xml = new CoordinateXML();
        Coordinates coordinatesPersisted;
        try {
            coordinatesPersisted = (Coordinates) xml.getObject(id);
        }catch (Exception e){
            e.printStackTrace();
            coordinatesPersisted = null;
        }
        if(coordinatesPersisted == null){
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
    public boolean erase(Coordinates obj) {
        String id = obj.getId();
        xml = new CoordinateXML();
        Coordinates coordinatesPersisted;
        try {
            coordinatesPersisted = (Coordinates) xml.getObject(id);
        }catch (Exception e){
            e.printStackTrace();
            coordinatesPersisted = null;
        }
        if(coordinatesPersisted != null){
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
    public boolean edit(Coordinates obj) {
        xml = new CoordinateXML();
        try {
            Coordinates coordinatesPersisted = (Coordinates) xml.getObject(obj.getId());
            if(coordinatesPersisted != null){
                xml.mergeElement(obj);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public HashMap<String, Coordinates> getAllObjects() {
        xml = new CoordinateXML();
        try {
            HashMap<String, Coordinates> coordinatesHashMap = xml.getObjectsHashMap();
            if(coordinatesHashMap != null){
                return coordinatesHashMap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, Coordinates>();
    }

    @Override
    public Coordinates getSingleObject(String key) {
        xml = new CoordinateXML();
        try {
            Coordinates coord = (Coordinates) xml.getObject(key);
            return coord;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
