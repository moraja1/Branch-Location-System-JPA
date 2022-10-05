package presentation.view;

import javax.swing.*;
import java.awt.*;

public abstract class ViewParent extends JFrame {

    public ViewParent(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\resources\\logoESCINF.jpg");
        setIconImage(icon);
    }

    /**
     * Initialize window components of every class that extends this superclass.
     */
    public abstract void initComponents();
}
