package controller.utils;

import java.awt.*;

public class GeneralUtilities {
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private int screenX;
    private int screenY;

    private GeneralUtilities(){
        screenX = toolkit.getScreenSize().width;
        screenY = toolkit.getScreenSize().height;
    }
    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }
    public static GeneralUtilities getInstanceOf(){
        GeneralUtilities generalUtilities = new GeneralUtilities();
        return generalUtilities;
    }

}
