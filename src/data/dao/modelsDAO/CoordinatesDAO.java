package data.dao.modelsDAO;

import data.Coordinates;
import data.dao.DAO;
import data.xmlParsers.ModelsParsers.CoordinateXML;
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
                //FALTA VERIFICAR LA ELIMINACION EN LA BRANCH
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean edit(Coordinates obj) {
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
