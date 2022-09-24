package data.xmlParsers.ModelsParsers;

import data.xmlParsers.XMLParser;
import data.Branch;
import data.Coordinates;
import data.Employee;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class BranchXML extends XMLParser<Branch> {
    protected final String path = "src\\data\\xmlFiles\\Branches.xml";
    public BranchXML() {
        file = path;
        TAG = "branch";
        ROOT_TAG = "Branches";
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

    /**
     *
     * @param key
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
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



    /**
     *
     * @param branch
     */
    @Override
    public void insertElement(Branch branch) throws TransformerException, ParserConfigurationException,
            IOException, SAXException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file
        root.appendChild(setElementData(doc, branch));//Inserto el objeto en el tag

        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }

    /**
     *
     * @param obj
     */
    @Override
    public void eraseElement(Branch obj) throws TransformerException, ParserConfigurationException,
            IOException, SAXException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file
        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, obj.getId());
        root.removeChild(elem);
        //Elimino los espacios en blanco del elemento agregado
        removeEmptyText(root);
        //Guardo los cambios
        saveChanges(doc, path);
    }
    /**
     *Merge the branch with new information on the xml file. WARNING: This method
     * do not add any employee, if the branch has new employees it only creates the
     * tag with the id of the employee but it do not add any employee to the
     * employee xml. Developer must add manually before this method
     * is called for better results.
     * @param obj
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    @Override
    public void mergeElement(Branch obj) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file

        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, obj.getId());

        Element address = (Element) elem.getElementsByTagName("address").item(0);
        Element zoning_percentage = (Element) elem.getElementsByTagName("zoning_percentage").item(0);
        Element coords = (Element) elem.getElementsByTagName("coords").item(0);
        NodeList employeesNodeList = elem.getElementsByTagName("employee");

        if(address != null) {
            address.setTextContent(obj.getAddress());
        }
        if(zoning_percentage != null) {
            zoning_percentage.setTextContent(String.valueOf(obj.getZoning_percentage()));
        }
        if(coords != null) {
            coords.setTextContent(obj.getId());
        }
        //Empleados
        if(employeesNodeList != null){
            Element employeesTag = (Element) employeesNodeList.item(0).getParentNode();
            for(int i = 0; i < employeesNodeList.getLength(); i++){
                Element employeeTag = (Element) employeesNodeList.item(i);
                employeeTag.getParentNode().removeChild(employeeTag);
            }
            List<Employee> employees = obj.getEmployees();
            if(employees != null){
                for(int i = 0; i < employees.size(); i++){
                    employeesTag.appendChild(createSubElements(doc, "employee", employees.get(i).getId()));
                }
            }else{
                employeesTag.appendChild(createSubElements(doc, "employee", "null"));
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
     * @param branch
     * @return
     */
    @Override
    protected Node setElementData(Document doc, Branch branch) {
        Element branchTag = doc.createElement(TAG);//Creo un tag para el objeto
        branchTag.setAttribute("id", branch.getId());//Asigno el atributo principal.

        //Asigno los subnodos y valores del objeto
        branchTag.appendChild(createSubElements(doc, "address", String.valueOf(branch.getAddress())));
        branchTag.appendChild(createSubElements(doc, "zoning_percentage", String.valueOf(branch.getZoning_percentage())));
        branchTag.appendChild(createSubElements(doc, "coords", branch.getId()));
        branchTag.appendChild(doc.createElement("employees"));

        NodeList employeesNodeList = branchTag.getElementsByTagName("employees");
        Element employeesTag = (Element) employeesNodeList.item(0);
        List<Employee> employees = branch.getEmployees();
        if(employees != null){
            for(int i = 0; i < employees.toArray().length; i++){
                employeesTag.appendChild(createSubElements(doc, "employee", employees.get(i).getId()));
            }

        }else{
            employeesTag.appendChild(createSubElements(doc, "employee", "null"));
        }
        return branchTag;
    }
    /**
     *
     * @param elem
     * @param branch
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    @Override
    protected Branch getElementData(Element elem, Branch branch) throws ParserConfigurationException, IOException, SAXException {
        // Get the value of all sub-elements.
        String address = elem.getElementsByTagName("address").item(0).getChildNodes().item(0).getNodeValue();
        String zoning_percentage = elem.getElementsByTagName("zoning_percentage").item(0).getChildNodes().item(0).getNodeValue();
        String coords = elem.getElementsByTagName("coords").item(0).getChildNodes().item(0).getNodeValue();
        Coordinates coordinates;
        if(coords == null){
            coordinates = new Coordinates(0, 0);
        }else{
            coordinates = new Coordinates(coords);
        }
        //Get the nodelist of employees
        NodeList employees_nodeList = elem.getElementsByTagName("employee");
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < employees_nodeList.getLength(); i++) {
            Node node = employees_nodeList.item(i);
            // Get the value of the ID attribute.
            String id_employee = node.getChildNodes().item(0).getNodeValue();
            if(!(id_employee.equals("null") || id_employee != null)){
                employees.add(new Employee(id_employee));
            }
        }
        //Set the values of all sub-elements.
        branch.setAddress(address);
        branch.setZoning_percentage(Double.valueOf(zoning_percentage));
        branch.setCoords(coordinates);
        branch.setEmployees(employees);

        return branch;
    }

    public void removeEmployeeFromBranch(Branch b, Employee obj) throws ParserConfigurationException, IOException,
            TransformerException, SAXException {
        String branchID = b.getId();

        doc = getDocument();
        NodeList branches = doc.getElementsByTagName("branch");
        Element elem = (Element) searchNode(branches, branchID);
        NodeList employees = elem.getElementsByTagName("employee");
        for (int j = 0; j < employees.getLength(); j++) {
            Node emp = employees.item(j);
            if (emp.getNodeType() == Node.ELEMENT_NODE) {
                if(emp.getTextContent().equals(obj.getId())){
                    if(employees.getLength() > 1){
                        emp.getParentNode().removeChild(emp);
                    }else{
                        emp.setTextContent("null");
                    }
                }
            }
        }
        removeEmptyText(doc.getFirstChild());
        saveChanges(doc, path);
    }
    public void addEmployeeToBranch(Employee obj) throws ParserConfigurationException, IOException,
            TransformerException, SAXException {
        String branchID = obj.getBranch().getId();
        boolean exists = false;
        doc = getDocument();
        NodeList branches = doc.getElementsByTagName("branch");
        Element elem = (Element) searchNode(branches, branchID);

        if(elem != null){
            NodeList employeesTagNodeList = elem.getElementsByTagName("employees");
            if(employeesTagNodeList != null){
                Element employeesTag = (Element) employeesTagNodeList.item(0);
                NodeList employees = employeesTag.getChildNodes();
                if(employees != null){
                    for (int j = 0; j < employees.getLength(); j++) {
                        Node emp = employees.item(j);
                        if (emp.getNodeType() == Node.ELEMENT_NODE) {
                            if ("employee".equals(emp.getNodeName())) {
                                if (emp.getTextContent().equals(obj.getId())) {
                                    exists = true;
                                }
                            }
                        }
                    }
                    if(!exists){
                        employeesTag.appendChild(createSubElements(doc, "employee", obj.getId()));
                        removeEmptyText(doc.getFirstChild());
                        saveChanges(doc, path);
                    }
                }
            }
        }
    }
    public void removeCoordinatesFromBranch(String coordinatesID) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();
        NodeList coordinates = root.getElementsByTagName("coords");
        if(coordinates != null){
            for(int i = 0; i < coordinates.getLength(); i++){
                Element coord = (Element) root.getElementsByTagName("coords").item(i);
                if(coord != null && coord.getTextContent().equals(coordinatesID)){
                    coord.setTextContent("null");
                    //Guardo los cambios
                    removeEmptyText(root);
                    saveChanges(doc, path);
                }
            }
        }
    }
}