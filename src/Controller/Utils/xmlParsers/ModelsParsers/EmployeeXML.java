package Controller.Utils.xmlParsers.ModelsParsers;

import Controller.Utils.xmlParsers.XMLParser;
import Model.Coordinates;
import Model.Employee;
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

public class EmployeeXML extends XMLParser<Employee> {
    private static final String path = "src\\xmlFiles\\Employees.xml";
    private static final BranchXML branchXML = new BranchXML();

    public EmployeeXML() {
        super(path);
    }

    @Override
    public HashMap<String, Employee> getObjectsHashMap() throws TransformerException, ParserConfigurationException, IOException, SAXException {
        HashMap<String, Employee> employees = new HashMap<>();

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
            Employee employee = new Employee(id);

            employees.put(id, getElementData(elem, employee));
        }
        return employees;
    }

    @Override
    /**
     * An Employee depending on the key sent by parameter or null if the Employee does not exists in XML File.
     * @param key
     * @return Coordinate
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Employee getObject(String key) throws ParserConfigurationException, IOException, SAXException {
        Employee employee;
        doc = getDocument();

        NodeList nodeList = doc.getElementsByTagName("Coordinates");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            // Get the value of the ID attribute.
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            if(id.equals(key)){
                employee = new Employee(id);
                employee = getElementData(elem, employee);

                return employee;
            }
        }
        return null;
    }

    @Override
    /**
     * Returns an Employee sent by parameter but with all its field filled.
     * @param elem
     * @return Objects
     */
    protected Employee getElementData(Element elem, Employee employee) throws ParserConfigurationException, IOException, SAXException {
        // Get the value of all sub-elements.
        String name = elem.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
        String phone_number = elem.getElementsByTagName("phone_number").item(0).getChildNodes().item(0).getNodeValue();
        String base_salary = elem.getElementsByTagName("base_salary").item(0).getChildNodes().item(0).getNodeValue();
        String branch = elem.getElementsByTagName("branch").item(0).getChildNodes().item(0).getNodeValue();

        //Set the value of all sub-elements.
        employee.setName(name);
        employee.setPhone_number(phone_number);
        employee.setBase_salary(Double.valueOf(base_salary));
        employee.setBranch(branchXML.getObject(branch));

        return employee;
    }
}
