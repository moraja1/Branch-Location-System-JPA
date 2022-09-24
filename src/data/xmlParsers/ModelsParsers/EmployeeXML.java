package data.xmlParsers.ModelsParsers;

import data.Branch;
import data.xmlParsers.XMLParser;
import data.Employee;
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
    private static final String path = "src\\data\\xmlFiles\\Employees.xml";
    public EmployeeXML() {
        file = path;
        TAG = "employee";
        ROOT_TAG = "Employees";
    }

    /**
     *
     * @return
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
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
    /**
     *
     * @param employee
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Override
    public void insertElement(Employee employee) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file
        root.appendChild(setElementData(doc, employee));//Inserto el objeto en el tag
        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }
    /**
     *
     * @param obj
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Override
    public void eraseElement(Employee obj) throws ParserConfigurationException, IOException,
            SAXException, TransformerException {

        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file
        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, obj.getId());
        //Remove employee
        root.removeChild(elem);
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }

    /**
     *
     * @param obj
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Override
    public void mergeElement(Employee obj) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file

        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, obj.getId());

        Element name = (Element) elem.getElementsByTagName("name").item(0);
        Element phone_number = (Element) elem.getElementsByTagName("phone_number").item(0);
        Element base_salary = (Element) elem.getElementsByTagName("base_salary").item(0);
        Element branch = (Element) elem.getElementsByTagName("branch").item(0);

        if(name != null) {
            if(!name.getTextContent().equals(obj.getName())){
                name.setTextContent(obj.getName());
            }
        }
        if(phone_number != null) {
            if(!phone_number.getTextContent().equals(String.valueOf(obj.getPhone_number()))){
                phone_number.setTextContent(String.valueOf(obj.getPhone_number()));
            }
        }
        if(base_salary != null) {
            if(!base_salary.getTextContent().equals(String.valueOf(obj.getBase_salary()))){
                base_salary.setTextContent(String.valueOf(obj.getBase_salary()));
            }
        }
        if(branch != null) {
            if(obj.getBranch() != null){
                if(!branch.getTextContent().equals(obj.getBranch().getId())){
                    branch.setTextContent(obj.getBranch().getId());
                }
            }else{
                branch.setTextContent("null");
            }
        }
        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }
    /**
     *
     * @param doc
     * @param employee
     * @return
     */
    @Override
    protected Node setElementData(Document doc, Employee employee) {
        Element emp = doc.createElement(TAG);//Creo un tag para el objeto
        emp.setAttribute("id", employee.getId());//Asigno el atributo principal.

        //Asigno los subnodos y valores del objeto
        emp.appendChild(createSubElements(doc, "name", employee.getName()));
        emp.appendChild(createSubElements(doc, "phone_number", String.valueOf(employee.getPhone_number())));
        emp.appendChild(createSubElements(doc, "base_salary", String.valueOf(employee.getPhone_number())));
        emp.appendChild(createSubElements(doc, "branch", employee.getBranch().getId()));
        return emp;
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
        if(name != null && phone_number != null && base_salary != null && branch != null){
            //Set the value of all sub-elements.
            employee.setName(name);
            employee.setPhone_number(phone_number);
            employee.setBase_salary(Double.valueOf(base_salary));
            employee.setBranch(new Branch(branch));
        }else{
            employee.setName("");
            employee.setPhone_number("");
            employee.setBase_salary(0.0);
            employee.setBranch(new Branch());
        }
        return employee;
    }
    public void removeBranchFromEmployee(Employee e) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();
        NodeList nodes = root.getChildNodes();
        Element employee = (Element) searchNode(nodes, e.getId());
        Element branch = (Element) employee.getElementsByTagName("branch").item(0);
        if(branch != null){
            branch.setTextContent("null");
        }
        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);

    }

    public void addBranchToEmployee(Employee e) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();
        NodeList nodes = root.getChildNodes();
        Element employee = (Element) searchNode(nodes, e.getId());
        Element branch = (Element) employee.getElementsByTagName("branch").item(0);
        if(branch != null){
            branch.setTextContent(e.getBranch().getId());
        }
        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }
}
