package View.ViewClasses;


import View.ViewParent;

import javax.swing.*;

public class MainWindow extends ViewParent {
    private JPanel MainWindowPanel;
    private JTabbedPane tabbedPane;
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


    public void initComponents(){ }


    public String getEmployeesSearchBar(){ return employees_srch_bar.getText(); }

    public String getBranchesSearchBar(){ return branches_srch_bar.getText(); }


}
