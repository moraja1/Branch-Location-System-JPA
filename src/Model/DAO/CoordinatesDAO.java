package Model.DAO;

import Controller.Utils.xmlParsers.ModelsParsers.CoordinateXML;
import Controller.Utils.xmlParsers.XMLParser;
import org.w3c.dom.Document;

/**
 * This creates a Coordinates CRUD for a XML File.
 */
public class CoordinatesDAO {
    private Document doc;

    public CoordinatesDAO(){
        try{
            doc = CoordinateXML.getDocument();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void write(){

    }

    public void erase(){

    }

    public void edit(char coord, int value){

    }


}
