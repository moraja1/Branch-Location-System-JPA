package model.xmlParsers.ModelsParsers;

import model.xmlParsers.XMLParser;
import model.Branch;
import model.Coordinates;
import model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class BranchXML extends XMLParser<Branch> {
    private static final String path = "src\\xmlFiles\\Branches.xml";
    private static final String TAG = "branch";

    public BranchXML() {
        super(path);
    }
    @Override
    public HashMap<String, Branch> getObjectsHashMap() throws TransformerException, ParserConfigurationException, IOException, SAXException {
        HashMap<String, Branch> branches = new HashMap<>();

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
                createXMLFile(doc, "Branches", "Branches.xml");
            }
        }
        NodeList nodeList = doc.getElementsByTagName(TAG);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            // Get the value of the ID attribute.
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            Branch branch = new Branch(id);
            branches.put(id, getElementData(elem, branch));
        }
        return branches;
    }

    @Override
    public Branch getObject(String key) throws ParserConfigurationException, IOException, SAXException {
        Branch branch;

        doc = getDocument();

        NodeList nodeList = doc.getElementsByTagName(TAG);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            // Get the value of the ID attribute.
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            if(id.equals(key)){
                branch = new Branch(id);
                branch = getElementData(elem, branch);

                return branch;
            }
        }
        return null;
    }

    @Override
    protected Branch getElementData(Element elem, Branch branch) throws ParserConfigurationException, IOException, SAXException {
        // Get the value of all sub-elements.
        String address = elem.getElementsByTagName("address").item(0).getChildNodes().item(0).getNodeValue();
        String zoning_percentage = elem.getElementsByTagName("zoning_percentage").item(0).getChildNodes().item(0).getNodeValue();
        String coords = elem.getElementsByTagName("coords").item(0).getChildNodes().item(0).getNodeValue();
        Coordinates coordinates = coordsXML.getObject(coords);
        //Get the nodelist of employees
        NodeList employees_nodeList = elem.getElementsByTagName("employee");
        HashMap<String, Employee> employees = new HashMap<>();
        for (int i = 0; i < employees_nodeList.getLength(); i++) {
            Node node = employees_nodeList.item(i);
            // Get the value of the ID attribute.
            String id_employee = node.getChildNodes().item(0).getNodeValue();

            employees.put(id_employee, employeesXML.getObject(id_employee, branch));
        }
        //Set the values of all sub-elements.
        branch.setAddress(address);
        branch.setZoning_percentage(Double.valueOf(zoning_percentage));
        branch.setCoords(coordinates);
        branch.setEmployees(employees);

        return branch;
    }

    @Override
    public void insertElement(Branch branch) {

    }

    @Override
    public void eraseElement() {

    }
    @Override
    protected Node setElementData(Document doc, Branch branch) {

        return null;
    }
    @Override
    protected Node createSubElements(Document doc, String nodeName, String value){
        return null;
    }
}
