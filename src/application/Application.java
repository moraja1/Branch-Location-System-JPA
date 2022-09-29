package application;



import org.xml.sax.SAXException;
import presentation.controller.flowController.MainController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        MainController.initFlow();
    }
}