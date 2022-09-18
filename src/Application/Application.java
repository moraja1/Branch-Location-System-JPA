package Application;


import Model.Employee;
import Model.xmlParsers.ModelsParsers.EmployeeXML;
import Model.xmlParsers.XMLParser;

import java.util.HashMap;

public class Application {
    public static void main(String[] args) {
        //MainController.initFlow();
        XMLParser bxml = new EmployeeXML();
        try {
            HashMap<String, Employee> coordinates = bxml.getObjectsHashMap();
            coordinates.forEach((key, value) ->{
                System.out.print(key + "->" + value.toString() + "\n");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
