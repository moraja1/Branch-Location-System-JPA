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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BranchXML extends XMLParser<Branch> {
    protected final String path = "src\\xmlFiles\\Branches.xml";
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

        //Ingreso las cordenadas
        if(branch.getCoords() != null){
            xml = new CoordinateXML();
            Coordinates coords = (Coordinates) xml.getObject(branch.getCoords().getId());
            if(coords == null){
                coords = branch.getCoords();
                xml.insertElement(coords);
            }
        }

        //Ingreso cada empleado
        List<Employee> employees = branch.getEmployees();
        if (employees != null){
            for (int i = 0; i < employees.toArray().length; i++){
                xml = new EmployeeXML();
                Employee employee = (Employee) xml.getObject(employees.get(i).getId());
                if(employee != null){
                    if(!(branch.getId().equals(employee.getBranch().getId()))){
                        employee.setBranch(branch);
                        xml.mergeElement(employee);
                    }
                }
            }
        }
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
        if(elem != null){
            root.removeChild(elem);
            //Actualizo las clases dependientes




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
    public void mergeElement(Branch obj) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        doc = getDocument();
        Element root = (Element) doc.getFirstChild();//Busco el primer tag del file

        NodeList nodeList = doc.getElementsByTagName(TAG);
        Element elem = (Element)searchNode(nodeList, obj.getId());
        if(elem != null){
            NodeList coord_fields = elem.getChildNodes();
            for(int j = 0; j < coord_fields.getLength(); j++){
                Node attr = coord_fields.item(j);
                if (attr.getNodeType() == Node.ELEMENT_NODE){
                    if("address".equals(attr.getNodeName())
                            && !attr.getTextContent().equals(obj.getAddress())){
                        attr.setTextContent(obj.getAddress());
                    }
                    if("zoning_percentage".equals(attr.getNodeName())
                            && !attr.getTextContent().equals(String.valueOf(obj.getZoning_percentage()))){
                        attr.setTextContent(String.valueOf(obj.getZoning_percentage()));
                    }
                    if("coords".equals(attr.getNodeName())
                            && !attr.getTextContent().equals(obj.getCoords().getId())){
                        xml = new CoordinateXML();
                        Coordinates coord = (Coordinates) xml.getObject(attr.getTextContent());
                        xml.eraseElement(coord);
                        xml.insertElement(obj.getCoords());
                        attr.setTextContent(obj.getCoords().getId());
                    }
                    if("employees".equals(attr.getNodeName())){
                        NodeList employeesNodeList = attr.getChildNodes();
                        List<Employee> employeesOnBranch = obj.getEmployees();
                        List<Employee> employeesOnXML = new ArrayList<>();
                        for(int i = 0; i < employeesNodeList.getLength(); i++){
                           Node emp = employeesNodeList.item(i);
                            if (emp.getNodeType() == Node.ELEMENT_NODE){
                                if("employee".equals(emp.getNodeName())){
                                    employeesOnXML.add(new Employee(emp.getTextContent()));
                                }
                            }
                        }
                        if(!employeesOnBranch.equals(employeesOnXML)){
                            System.out.println("Hola");
                        }
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
        branchTag.appendChild(createSubElements(doc, "coords", branch.getCoords().getId()));
        branchTag.appendChild(doc.createElement("employees"));

        NodeList employeesNodeList = branchTag.getElementsByTagName("employees");
        Element employeesTag = (Element) employeesNodeList.item(0);
        List<Employee> employees = branch.getEmployees();
        if(employees != null){
            for(int i = 0; i < employees.toArray().length; i++){
                employeesTag.appendChild(createSubElements(doc, "employee", employees.get(i).getId()));
            }

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
        xml = new CoordinateXML();
        Coordinates coordinates = (Coordinates) xml.getObject(coords);
        //Get the nodelist of employees
        NodeList employees_nodeList = elem.getElementsByTagName("employee");
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < employees_nodeList.getLength(); i++) {
            Node node = employees_nodeList.item(i);
            // Get the value of the ID attribute.
            String id_employee = node.getChildNodes().item(0).getNodeValue();

            employees.add(new Employee(id_employee));
        }
        //Set the values of all sub-elements.
        branch.setAddress(address);
        branch.setZoning_percentage(Double.valueOf(zoning_percentage));
        branch.setCoords(coordinates);
        branch.setEmployees(employees);

        return branch;
    }

    public void removeEmployeeFromBranch(Employee obj) throws ParserConfigurationException, IOException,
            TransformerException, SAXException {
        doc = getDocument();
        NodeList branches = doc.getElementsByTagName("branch");
        Element elem = (Element) searchNode(branches, obj.getBranch().getId());
        NodeList employee = elem.getElementsByTagName("employees");
        Element employees = (Element) employee.item(0);
        for (int j = 0; j < employee.getLength(); j++) {
            Node emp = employee.item(j);
            if (emp.getNodeType() == Node.ELEMENT_NODE) {
                if ("employee".equals(emp.getNodeName())) {
                    if(emp.getTextContent().equals(obj.getId())){
                        employees.removeChild(emp);
                    }
                }
            }
        }
        /*if(elem != null){
            NodeList elem_childs = elem.getChildNodes();
            for (int i = 0; i < elem_childs.getLength(); i++) {
                Node attr = elem_childs.item(i);
                if (attr.getNodeType() == Node.ELEMENT_NODE) {
                    if ("employees".equals(attr.getNodeName())) {
                        Element employees = (Element) attr;
                        NodeList employee = employees.getChildNodes();
                        for (int j = 0; j < elem_childs.getLength(); j++) {
                            Node emp = employee.item(j);
                            if (emp.getNodeType() == Node.ELEMENT_NODE) {
                                if ("employee".equals(emp.getNodeName())) {
                                    if(emp.getTextContent().equals(obj.getId())){
                                        employees.removeChild(emp);
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
            removeEmptyText(doc.getFirstChild());
            saveChanges(doc, path);
    }
    public void addEmployeeToBranch(Employee obj) throws ParserConfigurationException, IOException,
            TransformerException, SAXException {
        doc = getDocument();
        NodeList branches = doc.getElementsByTagName("branch");
        Element elem = (Element) searchNode(branches, obj.getBranch().getId());
        if(elem != null){
            NodeList elem_childs = elem.getChildNodes();
            for (int j = 0; j < elem_childs.getLength(); j++) {
                Node attr = elem_childs.item(j);
                if (attr.getNodeType() == Node.ELEMENT_NODE) {
                    if ("employees".equals(attr.getNodeName())) {

                    }
                }
            }
            removeEmptyText(doc.getFirstChild());
            saveChanges(doc, path);
        }
    }
}
