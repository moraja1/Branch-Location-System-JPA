package View;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainWindow extends ViewParent {
    private JPanel MainWindowPanel;
    private JTabbedPane tabbedPane1;
    private JTextField sucTextField;
    private JButton buscarSucButton;
    private JButton agregarSucButton;
    private JButton borrarSucButton;
    private JButton reporteSucButton;
    private JTextField empTextField;
    private JButton buscarEmpButto;
    private JButton agregarEmpButton1;
    private JButton borrarEmpButton;
    private JButton reporteEmpButton;



    public void initComponents(){ }


    public String getEmpTextField(){ return empTextField.getText(); }

    public String getSucTextField(){ return sucTextField.getText(); }


}
