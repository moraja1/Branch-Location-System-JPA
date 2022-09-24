package presentation.view.ViewClasses;


import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.view.utils.GeneralUtilities;
import presentation.view.ViewParent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel tab_employees;
    private JPanel tab_branches;
    private JPanel tab_about;
    private JPanel emp_table_panel;
    private JPanel dept_table_panel;
    private JButton edit_employee_button;
    private JButton edit_branch_button;
    private GeneralUtilities utils;
    private JTable emp_table;
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
        add_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.addEmployee();
            }
        });
        edit_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(emp_table.getSelectedRow() != -1){
                    MainWindowViewController.editEmployee();
                }
            }
        });
        erase_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.eraseEmployee();
            }
        });
        srch_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        edit_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        erase_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        srch_branches_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        MainWindowViewController.windowInitialized();
        setVisible(true);
    }


    public String getEmployeesSearchBar(){ return employees_srch_bar.getText(); }

    public String getBranchesSearchBar(){ return branches_srch_bar.getText(); }


}
