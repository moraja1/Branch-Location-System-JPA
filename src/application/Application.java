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
        BranchXML xml = new BranchXML();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1"));
        Branch branch = new Branch("1", "San Jose, San Jose, Mata Redonda", 0.7, new Coordinates("2", 50, 25), employees);
        xml.insertElement(branch);
        System.out.println(((Branch)xml.getObject("1")).toString());
    }
}
