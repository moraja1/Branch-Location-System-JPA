package Controller.Utils.xmlParsers.ModelsParsers;

import Controller.Utils.xmlParsers.XMLParser;
import Model.Employee;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;

public class EmployeeXML extends XMLParser<Employee> {
    private static final String path = "src\\xmlFiles\\Employees.xml";

    public EmployeeXML() {
        super(path);
    }

    @Override
    public HashMap<String, Employee> getObjectsHashMap() throws TransformerException, ParserConfigurationException, IOException, SAXException {
        return null;
    }

    @Override
    public Employee getObject(String key) throws ParserConfigurationException, IOException, SAXException {
        return null;
    }
}
