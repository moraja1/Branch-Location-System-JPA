package presentation.view.ViewClasses;


import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.view.utils.GeneralUtilities;
import presentation.view.ViewParent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
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
    private JPanel branch_table_panel;
    private JButton edit_employee_button;
    private JButton edit_branch_button;
    private JLabel image_logo;
    private GeneralUtilities utils;
    private JTable emp_table;
    private JTable branch_table;
    public MainWindow(){
        utils = GeneralUtilities.getInstanceOf();

        if(!getContentPane().equals(main_window_panel)){
            setContentPane(main_window_panel);
            setName("MainWindow");
            setSize(new Dimension(1300, 900));
            setTitle("Sistema de Sucursales y Empleados");
            setLocation(utils.getScreenX()/6, utils.getScreenY()/8);

            //Insert Tables
            emp_table = new JTable();
            emp_table_panel.setLayout(new BorderLayout());
            emp_table_panel.add(new JScrollPane(emp_table), BorderLayout.CENTER);

            branch_table = new JTable();
            branch_table_panel.setLayout(new BorderLayout());
            branch_table_panel.add(new JScrollPane(branch_table));
        }
    }

    public void initComponents(){
        //Employees Action Listeners
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
                MainWindowViewController.searchEmployee();
            }
        });
        report_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.reportEmployee();
            }
        });
        //BRANCHES ACTION LISTENERS
        add_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.addBranch();
            }
        });
        edit_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(emp_table.getSelectedRow() != -1){
                    MainWindowViewController.editBranch();
                }
            }
        });
        erase_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.eraseBranch();
            }
        });
        srch_branches_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.searchBranch();
            }
        });
        report_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.reportBranch();
            }
        });
        tabbed_Pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                MainWindowViewController.updateTables();
            }
        });

        //Controller initialize other components
        MainWindowViewController.updateTables();
        //Window opens
        setVisible(true);
    }


    public String getEmployeesSearchBar(){ return employees_srch_bar.getText(); }

    public String getBranchesSearchBar(){ return branches_srch_bar.getText(); }

    public int getSelectedTabIndex(){
        return tabbed_Pane.getSelectedIndex();
    }
    public void setTableModel(TableModel tm){
        int tabSelected = getSelectedTabIndex();
        if(tabSelected == 0){
            emp_table.setModel(tm);
        }
        if(tabSelected == 1){
            branch_table.setModel(tm);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        image_logo = new JLabel(new ImageIcon("resources\\UNA_logo.png"));
        //map_image = new JLabel(new ImageIcon("resources\\CR_map_image,jpg"));
    }
}
