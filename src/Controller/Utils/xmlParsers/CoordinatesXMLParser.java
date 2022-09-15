package Controller.Utils.xmlParsers;

import Model.Coordinates;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CoordinatesXMLParser {
    private static final String FILE = "Places.xml";
    private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder;
    private static Document doc;

    public static Set<Coordinates> getCoordinatesSet(String xmlFile) throws ParserConfigurationException, IOException, SAXException {

        Set<Coordinates> coordinates = new HashSet<>();//Create Coordinates´ Set.
        builder = documentBuilderFactory.newDocumentBuilder();//Create DocumentBuilder
        doc = builder.parse(new File(FILE));//Create the parsed xml

        NodeList nodeList = doc.getElementsByTagName("Coordinates");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);


        }
        return coordinates;
    }
}
