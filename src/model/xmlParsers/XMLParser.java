package model.xmlParsers;

import model.Coordinates;
import model.xmlParsers.ModelsParsers.BranchXML;
import model.xmlParsers.ModelsParsers.CoordinateXML;
import model.xmlParsers.ModelsParsers.EmployeeXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public abstract class XMLParser<T> {
    protected static final CoordinateXML coordsXML = new CoordinateXML();
    protected static final EmployeeXML employeesXML = new EmployeeXML();
    protected static final BranchXML branchXML = new BranchXML();
    protected static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    protected static DocumentBuilder builder;
    protected Document doc;
    protected String file;
    public XMLParser(String path){
        file = path;
    }
    /**
     * Return a DOM Document parsed as the name of the file .xml located in src\xmlFiles\
     * @return Document
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Document getDocument() throws ParserConfigurationException, IOException,
            SAXException {
        builder = documentBuilderFactory.newDocumentBuilder();//Create DocumentBuilder
        doc = builder.parse(new File(file));//Create the parsed xml
        return doc;
    }

    /**
     * Return a DOM Document parsed as Places.xml located in src\xmlFiles\Places.xml
     * @param file
     * @return Document
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    protected Document getDocument(File file) throws ParserConfigurationException, IOException,
            SAXException {
        HashMap<String, Coordinates> coordinates = new HashMap<>();
        builder = documentBuilderFactory.newDocumentBuilder();//Create DocumentBuilder
        doc = builder.parse(file);//Create the parsed xml
        return doc;
    }

    /**
     * Create a file if there is not a file for coordinates in program´s src directory. File will be findable src\xmlFiles\
     * @param doc
     * @throws TransformerException
     */
    protected static void createXMLFile(Document doc, String root_tag, String file_name) throws TransformerException {
        Element root = doc.createElement(root_tag);
        doc.appendChild(root);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(new StringBuilder().append("src\\xmlFiles\\").append(file_name).toString()));
        transformer.transform(domSource, streamResult);
    }

    /** A HashMap for all Elements in the xml file. The HashMap is build with the id and the Objects indexed
     * for easier look up.
     * @return HashMap<id, T>
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    public abstract HashMap<String, T> getObjectsHashMap() throws TransformerException, ParserConfigurationException, IOException, SAXException;

    /**
     * A Coordinate depending on the key sent by parameter or null if the Coordinate does not exists in XML File.
     * @param key
     * @return Coordinate
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public abstract T getObject(String key) throws ParserConfigurationException, IOException, SAXException;

    /**
     * Return the same object sent by parameter but with all its field filled.
     * @param elem
     * @return Objects
     */
    protected abstract T getElementData(Element elem, T object) throws ParserConfigurationException, IOException, SAXException;
    public abstract void insertElement(T obj) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    public abstract void eraseElement(String key) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    public abstract void mergeElement(T obj) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    protected abstract Node setElementData(Document doc, T obj);
    protected abstract Node createSubElements(Document doc, String nodeName, String value);

    /**
     * This Method fix the indentation of the node passed by parameter, providing a better writting result on xml file
     * @param node
     */
    protected static void removeEmptyText(Node node){
        Node child = node.getFirstChild();

        while(child!=null){
            Node sibling = child.getNextSibling();
            if(child.getNodeType()==Node.TEXT_NODE){
                if(child.getTextContent().trim().isEmpty())
                    node.removeChild(child);
            }else
                removeEmptyText(child);
            child = sibling;
        }
    }
}
