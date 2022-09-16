package Controller.Utils.xmlParsers.ModelsParsers;

import Controller.Utils.xmlParsers.XMLParser;
import Model.Branch;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;

public class BranchXML extends XMLParser<Branch> {
    private static final String path = "src\\xmlFiles\\Branches.xml";

    public BranchXML() {
        super(path);
    }

    @Override
    public HashMap<String, Branch> getObjectsHashMap() throws TransformerException, ParserConfigurationException, IOException, SAXException {
        return null;
    }

    @Override
    public Branch getObject(String key) throws ParserConfigurationException, IOException, SAXException {
        return null;
    }
}
