package application;


import model.Coordinates;
import model.Employee;
import model.xmlParsers.ModelsParsers.CoordinateXML;
import model.xmlParsers.ModelsParsers.EmployeeXML;
import model.xmlParsers.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;

public class Application {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        //MainController.initFlow();
        XMLParser bxml = new CoordinateXML();
        try {
            HashMap<String, Coordinates> coordinates = bxml.getObjectsHashMap();
            coordinates.forEach((key, value) ->{
                System.out.print(key + "->" + value.toString() + "\n");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
