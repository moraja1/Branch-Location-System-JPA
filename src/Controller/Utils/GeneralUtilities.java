package Controller.Utils;

import java.awt.*;

public class GeneralUtilities {
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private int screenX;
    private int screenY;

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    private GeneralUtilities(){
        screenX = toolkit.getScreenSize().width;
        screenY = toolkit.getScreenSize().height;
    }

    public static GeneralUtilities getInstanceOf(){
        GeneralUtilities generalUtilities = new GeneralUtilities();
        return generalUtilities;
    }

}
