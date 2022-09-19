package model.xmlParsers.ModelsParsers;

import model.Branch;
import model.xmlParsers.XMLParser;
import model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;

public final class EmployeeXML extends XMLParser<Employee> {
    private static final String path = "src\\xmlFiles\\Employees.xml";
    private static final String TAG = "employee";

    public EmployeeXML() {
        super(path);
    }

    @Override
    public HashMap<String, Employee> getObjectsHashMap() throws TransformerException, ParserConfigurationException, IOException, SAXException {
        HashMap<String, Employee> employees = new HashMap<>();


        NodeList nodeList = doc.getElementsByTagName(TAG);
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
     * @return Employee
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Employee getObject(String key) throws ParserConfigurationException, IOException, SAXException {
        Employee employee;
        doc = getDocument();

        NodeList nodeList = doc.getElementsByTagName(TAG);
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
    public Employee getObject(String key, Branch branch) throws ParserConfigurationException, IOException, SAXException {
        Employee employee;
        doc = getDocument();

        NodeList nodeList = doc.getElementsByTagName(TAG);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            // Get the value of the ID attribute.
            String id = elem.getAttributes().getNamedItem("id").getNodeValue();
            if(id.equals(key)){
                employee = new Employee(id);
                employee = getElementData(elem, employee, branch);

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

    protected Employee getElementData(Element elem, Employee employee, Branch branch) throws ParserConfigurationException, IOException, SAXException {
        // Get the value of all sub-elements.
        String name = elem.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
        String phone_number = elem.getElementsByTagName("phone_number").item(0).getChildNodes().item(0).getNodeValue();
        String base_salary = elem.getElementsByTagName("base_salary").item(0).getChildNodes().item(0).getNodeValue();

        //Set the value of all sub-elements.
        employee.setName(name);
        employee.setPhone_number(phone_number);
        employee.setBase_salary(Double.valueOf(base_salary));
        employee.setBranch(branch);

        return employee;
    }
    @Override
    public void insertElement(Employee employee) {

    }

    @Override
    public void eraseElement() {

    }
    @Override
    protected Node setElementData(Document doc, Employee employee) {

        return null;
    }
    @Override
    protected Node createSubElements(Document doc, String nodeName, String value){
        return null;
    }
}
