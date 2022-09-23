package application;


import data.Branch;
import data.Employee;
import data.xmlParsers.ModelsParsers.BranchXML;
import data.xmlParsers.ModelsParsers.EmployeeXML;
import data.xmlParsers.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        //MainController.initFlow();
        XMLParser xml = new BranchXML();
        Branch coord = (Branch) xml.getObject("0");
        List<Employee> employees = new ArrayList<>();
        xml = new EmployeeXML();
        employees.add((Employee) xml.getObject("1"));
        coord.setEmployees(employees);
        xml = new BranchXML();
        xml.mergeElement(coord);
    }
}