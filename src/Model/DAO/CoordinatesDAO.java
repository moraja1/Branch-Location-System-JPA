package Model.DAO;

import Controller.Utils.xmlParsers.CoordinatesXMLParser;
import Model.Coordinates;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * This creates a Coordinates CRUD for a XML File.
 */
public class CoordinatesDAO {
    private Document doc;

    public CoordinatesDAO(){
        doc = CoordinatesXMLParser.getCoordinatesDocumento();
    }

    public void write(){

    }

    public void erase(){

    }

    public void edit(char coord, int value){

    }


}
