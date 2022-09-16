package Application;

import Controller.General.MainController;
import Controller.Utils.xmlParsers.CoordinatesXMLParser;
import Model.Coordinates;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        MainController.initFlow();
        try{
            Set<Coordinates> coords = CoordinatesXMLParser.getCoordinatesSet();
            for(Coordinates coord : coords){
                System.out.println(coord.toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
