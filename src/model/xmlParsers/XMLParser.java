package model.xmlParsers;

import model.Coordinates;
import model.xmlParsers.ModelsParsers.BranchXML;
import model.xmlParsers.ModelsParsers.CoordinateXML;
import model.xmlParsers.ModelsParsers.EmployeeXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public abstract class XMLParser<T> {

    protected XMLParser xml;
    protected static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    protected static DocumentBuilder builder;
    protected Document doc;
    protected String file;
    protected String TAG;
    protected String ROOT_TAG;
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
    /**
     * This methods returns the Node of the NodeList that contains the key passed.
     * @param nd
     * @param key
     * @return
     */
    protected static Node searchNode(NodeList nd, String key){
        for (int i = 0; i < nd.getLength(); i++) {
            Node node = nd.item(i);
            // Get the value of the ID attribute.
            String id = node.getAttributes().getNamedItem("id").getNodeValue();
            if (id.equals(key)) {
                return node;
            }
        }
        return null;
    }
    /**
     * This methods inserts the changes into the xml file gotten by the Document
     * @param doc
     * @param path
     * @throws TransformerException
     */
    protected static void saveChanges(Document doc, String path) throws TransformerException {
        //Creo el transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //Le doy indentado a la configuracion del transformer
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //Creo la fuente DOM e inserto el DOM al file.
        DOMSource source = new DOMSource(doc);
        StreamResult consoleResult = new StreamResult(new File(path));
        transformer.transform(source, consoleResult);
    }
    /**
     * This method create a SubNode of the coordinate passed, this means that every time this method is called, a new
     * subTag will be created in the xml file by the DOMsource.
     * @param doc
     * @param nodeName
     * @param value
     * @return Node
     */
    protected Node createSubElements(Document doc, String nodeName, String value){
        Element subElement = doc.createElement(nodeName);//Creo el tag de cada subnodo del Objeto

        subElement.appendChild(doc.createTextNode(value));//Le doy el valor al subnodo.

        return subElement;
    }
    public void deleteNode(String elementID, String subElementName, String subElementValue) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();

        NodeList nodeList = root.getElementsByTagName(TAG);
        Node node = searchNode(nodeList, elementID);

        NodeList subElementsNodeList = node.getChildNodes();
        for(int j = 0; j < subElementsNodeList.getLength(); j++){
            Node attr = subElementsNodeList.item(j);
            if (attr.getNodeType() == Node.ELEMENT_NODE){
                if(subElementName.equals(attr.getNodeName()) && subElementValue.equals(attr.getTextContent())){
                    node.removeChild(attr);
                }
            }
        }

        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, file);
    }
    public abstract HashMap<String, T> getObjectsHashMap() throws TransformerException, ParserConfigurationException, IOException, SAXException;
    public abstract T getObject(String key) throws ParserConfigurationException, IOException, SAXException;
    protected abstract T getElementData(Element elem, T object) throws ParserConfigurationException, IOException, SAXException;
    public abstract void insertElement(T obj) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    public abstract void eraseElement(T obj) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    public abstract void mergeElement(T obj) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    protected abstract Node setElementData(Document doc, T obj);
}
