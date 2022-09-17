package Controller.Utils.xmlParsers.ModelsParsers;

import Controller.Utils.xmlParsers.XMLParser;
import Model.Coordinates;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class CoordinateXML extends XMLParser<Coordinates> {
    private static final String path = "src\\xmlFiles\\Places.xml";

    public CoordinateXML() {
        super(path);
    }

    @Override
    /** A HashMap for all Coordinates in Places.xml file. The HashMap is build with the id and the Coordinates indexed
     * for easier look up.
     * @return HashMap<id, Coordinate>
     * @throws SAXException
     * @throws TransformerException
     */
    public HashMap<String, Coordinates> getObjectsHashMap() throws ParserConfigurationException, IOException,
            SAXException, TransformerException {
        HashMap<String, Coordinates> coordinates = new HashMap<>();

        try {
            doc = getDocument();
        }catch (Exception e){
            File file;
            JFileChooser file_chooser = new JFileChooser();
            int i=file_chooser.showOpenDialog(new JFrame());
            if(i==JFileChooser.APPROVE_OPTION) {
                file = file_chooser.getSelectedFile();
                doc = getDocument(file);
                e.printStackTrace();
            }else{
                doc = builder.newDocument();
                createXMLFile(doc, "Places", "Places.xml");
            }
        }
        NodeList nodeList = doc.getElementsByTagName("Coordinates");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            // Get the value of the ID attribute.
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            // Get the value of all sub-elements.
            String x = elem.getElementsByTagName("x").item(0).getChildNodes().item(0).getNodeValue();
            String y = elem.getElementsByTagName("y").item(0).getChildNodes().item(0).getNodeValue();
            coordinates.put(id, new Coordinates(id, Integer.parseInt(x), Integer.parseInt(y)));
        }
        return coordinates;
    }

    @Override
    /**
     * A Coordinate depending on the key sent by parameter or null if the Coordinate does not exists in XML File.
     * @param key
     * @return Coordinate
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Coordinates getObject(String key) throws ParserConfigurationException, IOException, SAXException {
        Coordinates coordinate;
        doc = getDocument();

        NodeList nodeList = doc.getElementsByTagName("Coordinates");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            // Get the value of the ID attribute.
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            if(id.equals(key)){
                coordinate = new Coordinates(id);
                coordinate = getElementData(elem, coordinate);

                return coordinate;
            }
        }
        return null;
    }

    @Override
    protected Coordinates getElementData(Element elem, Coordinates coordinate) {
        // Get the value of all sub-elements.
        String x = elem.getElementsByTagName("x").item(0).getChildNodes().item(0).getNodeValue();
        String y = elem.getElementsByTagName("y").item(0).getChildNodes().item(0).getNodeValue();
        coordinate.setX(Integer.parseInt(x));
        coordinate.setY(Integer.parseInt(y));

        return coordinate;
    }
}
