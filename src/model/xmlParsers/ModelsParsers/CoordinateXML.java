package model.xmlParsers.ModelsParsers;

import model.xmlParsers.XMLParser;
import model.Coordinates;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class CoordinateXML extends XMLParser<Coordinates> {
    private static final String path = "src\\xmlFiles\\Places.xml";
    public CoordinateXML() {
        file = path;
        TAG = "coordinates";
        ROOT_TAG = "Places";
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
                createXMLFile(doc, ROOT_TAG, "Places.xml");
            }
        }
        NodeList nodeList = doc.getElementsByTagName(TAG);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            // Get the value of the ID attribute.
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            Coordinates coord = new Coordinates(id);
            coordinates.put(id, getElementData(elem, coord));
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

        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, key);
        if(elem != null){
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            coordinate = new Coordinates(id);
            coordinate = getElementData(elem, coordinate);

            return coordinate;
        }
        return null;
    }
    /**
     * Insert a new Coordinates in the xml file. Warning: This method do not verify if the id of the Coordinates
     * already exists in the file, so you must perform a proper verification before using this method.
     * @param coord
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Override
    public void insertElement(Coordinates coord) throws ParserConfigurationException, IOException, SAXException,
            TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file

        root.appendChild(setElementData(doc, coord));//Inserto el objeto en el tag

        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }

    /**
     * This method delete the Coordinates that contains the same id as the 'key' param passed. Warning: Once this method
     * is done, the information of the Coordinate will be erase forever, no rollbacks are allowed.
     * @param key
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Override
    public void eraseElement(String key) throws ParserConfigurationException, IOException, SAXException,
            TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file

        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, key);
        if(elem != null){
            root.removeChild(elem);
        }

        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }
    /**
     * This method search for the Node that contains the same key that the Coordinates passed by param, and the
     * merge the fields into the file. Warning: This method does not work if there is not an Element containing the
     * key of the Coordinates
     * @param coord
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Override
    public void mergeElement(Coordinates coord) throws ParserConfigurationException, IOException, SAXException,
            TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file

        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, coord.getId());
        if(elem != null){
            NodeList coord_fields = elem.getChildNodes();
            for(int j = 0; j < coord_fields.getLength(); j++){
                Node attr = coord_fields.item(j);
                if (attr.getNodeType() == Node.ELEMENT_NODE){
                    if("x".equals(attr.getNodeName())){
                        attr.setTextContent(String.valueOf(coord.getX()));
                    }
                    if("y".equals(attr.getNodeName())){
                        attr.setTextContent(String.valueOf(coord.getY()));
                    }
                }
            }
        }

        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }
    /**
     * This method create a Coordinates in the xml form before adding it to the file.
     * @param doc
     * @param coord
     * @return Node
     */
    @Override
    protected Node setElementData(Document doc, Coordinates coord) {
        Element coordinate = doc.createElement(TAG);//Creo un tag para el objeto
        coordinate.setAttribute("id", coord.getId());//Asigno el atributo principal.

        //Asigno los subnodos y valores del objeto
        coordinate.appendChild(createSubElements(doc, "x", String.valueOf(coord.getX())));
        coordinate.appendChild(createSubElements(doc, "y", String.valueOf(coord.getY())));

        return coordinate;
    }
    /**
     * Returns a Coordinates object that contains all the information of the xml.
     * @param elem
     * @param coordinate
     * @return Coordinates
     */
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