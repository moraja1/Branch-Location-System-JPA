package application;


import model.Branch;
import model.Coordinates;
import model.Employee;
import model.xmlParsers.ModelsParsers.BranchXML;
import model.xmlParsers.ModelsParsers.CoordinateXML;
import model.xmlParsers.ModelsParsers.EmployeeXML;
import model.xmlParsers.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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