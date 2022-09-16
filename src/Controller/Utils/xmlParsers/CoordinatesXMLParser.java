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
    private static final String FILE = "src\\xmlFiles\\Places.xml";
    private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder;
    private static Document doc;
    public static Document getCoordinatesDocumento() {
        return doc;
    }

    public static Set<Coordinates> getCoordinatesSet() throws ParserConfigurationException, IOException, SAXException {
        Set<Coordinates> coordinates = new HashSet<>();
        builder = documentBuilderFactory.newDocumentBuilder();//Create DocumentBuilder
        doc = builder.parse(new File(FILE));//Create the parsed xml

        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                // Get the value of the ID attribute.
                String id = node.getAttributes().getNamedItem("id").getNodeValue();
                // Get the value of all sub-elements.
                String x = elem.getElementsByTagName("x").item(0).getChildNodes().item(0).getNodeValue();
                String y = elem.getElementsByTagName("y").item(0).getChildNodes().item(0).getNodeValue();
                coordinates.add(new Coordinates(id, Integer.parseInt(x), Integer.parseInt(y)));
            }

        }
        return coordinates;
    }
}
