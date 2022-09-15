package View.ViewClasses;


import Controller.Utils.GeneralUtilities;
import View.ViewParent;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends ViewParent {
    private JPanel main_window_panel;
    private JTabbedPane tabbed_Pane;
    private JTextField branches_srch_bar;
    private JButton srch_branches_button;
    private JButton add_branch_button;
    private JButton erase_branch_button;
    private JButton report_branch_button;
    private JTextField employees_srch_bar;
    private JButton srch_employee_button;
    private JButton add_employee_button;
    private JButton erase_employee_button;
    private JButton report_employee_button;
    private JTable table_employees;
    private JTable table_branches;
    private JPanel tab_employees;
    private JPanel tab_branches;
    private JPanel tab_about;
    private GeneralUtilities utils;
    public MainWindow(){
        utils = GeneralUtilities.getInstanceOf();

        if(!getContentPane().equals(main_window_panel)){
            setContentPane(main_window_panel);
            setName("MainWindow");
            setSize(new Dimension(1000, 800));
            setTitle("Sistema de Sucursales y Empleados");
            setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
        }
    }

    public void initComponents(){
        setVisible(true);
    }


    public String getEmployeesSearchBar(){ return employees_srch_bar.getText(); }

    public String getBranchesSearchBar(){ return branches_srch_bar.getText(); }


}
