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
        //Update Branch
        addEmployeeToBranch(employee);
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
        if(elem != null){
            //Remove employee
            root.removeChild(elem);
            //Update Branch
            removeEmployeeFromBranch(obj);
            //Elimino los espacios en blanco del elemento agregado
            removeEmptyText(root);
            //Guardo los cambios
            saveChanges(doc, path);
        }
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
        if(elem != null) {
            NodeList coord_fields = elem.getChildNodes();
            for (int j = 0; j < coord_fields.getLength(); j++) {
                Node attr = coord_fields.item(j);
                if (elem.getNodeType() == Node.ELEMENT_NODE) {
                    if ("name".equals(attr.getNodeName()) &&
                            !attr.getTextContent().equals(obj.getName())) {
                        attr.setTextContent(obj.getName());
                    }
                    if ("phone_number".equals(attr.getNodeName()) &&
                            !attr.getTextContent().equals(String.valueOf(obj.getPhone_number()))) {
                        attr.setTextContent(String.valueOf(obj.getPhone_number()));
                    }
                    if ("base_salary".equals(attr.getNodeName()) &&
                            !attr.getTextContent().equals(String.valueOf(obj.getBase_salary()))) {
                        attr.setTextContent(String.valueOf(obj.getBase_salary()));
                    }
                    if ("branch".equals(attr.getNodeName()) &&
                            !attr.getTextContent().equals(obj.getBranch().getId())) {
                        //Obtainig current employee saved
                        Employee employee = getObject(obj.getId());
                        //Update Branch
                        removeEmployeeFromBranch(employee);
                        addEmployeeToBranch(obj);
                        //Update xml
                        attr.setTextContent(obj.getBranch().getId());
                    }
                }
            }
            //Elimino los espacios en blanco del elemento agregado
            removeEmptyText(root);
            //Guardo los cambios
            saveChanges(doc, path);
        }
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

        //Set the value of all sub-elements.
        employee.setName(name);
        employee.setPhone_number(phone_number);
        employee.setBase_salary(Double.valueOf(base_salary));
        xml = new BranchXML();
        employee.setBranch((Branch)xml.getObject(branch));

        return employee;
    }
    private void removeEmployeeFromBranch(Employee obj) throws ParserConfigurationException, IOException,
            TransformerException, SAXException {
        //Update Branch
        Branch branch =  obj.getBranch();
        HashMap<String,Employee> employees = branch.getEmployees();
        if(employees.containsKey(obj.getId())){
            employees.remove(obj.getId());
            branch.setEmployees(employees);
            //Merge the Branch
            xml = new BranchXML();
            xml.mergeElement(branch);
        }
    }
    private void addEmployeeToBranch(Employee obj) throws ParserConfigurationException, IOException,
            TransformerException, SAXException {
        Branch branch = obj.getBranch();
        HashMap<String,Employee> employees = branch.getEmployees();
        if(!employees.containsKey(obj.getId())){
            employees.put(obj.getId(), obj);
            branch.setEmployees(employees);
            //Merge the Branch
            xml = new BranchXML();
            xml.mergeElement(branch);
        }
    }
}
