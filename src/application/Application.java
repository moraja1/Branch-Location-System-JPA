package application;


import data.Branch;
import data.Employee;
import data.xmlParsers.ModelsParsers.BranchXML;
import data.xmlParsers.ModelsParsers.EmployeeXML;
import data.xmlParsers.XMLParser;
import org.xml.sax.SAXException;
import presentation.controller.flowController.MainController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        MainController.initFlow();
    }
}