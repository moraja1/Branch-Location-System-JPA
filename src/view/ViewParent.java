package view;

import javax.swing.JFrame;

public abstract class ViewParent extends JFrame {

    public ViewParent(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialize window components of every class that extends this superclass.
     */
    public abstract void initComponents();
}
