package presentation.view.ViewClasses;


import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.model.mouseListener.ImageMouseSensor;
import presentation.model.viewModels.BranchTableInfo;
import presentation.view.utils.GeneralUtilities;
import presentation.view.ViewParent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

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
    private JPanel map_panel;
    private JPanel branch_info_panel;
    private JLabel map_image;
    private JLabel image_logo;
    private GeneralUtilities utils;
    private JTable emp_table;
    private JTable branch_table;
    private JLayeredPane map_layered_pane;
    public MainWindow(){
        utils = GeneralUtilities.getInstanceOf();

        if(!getContentPane().equals(main_window_panel)){
            setContentPane(main_window_panel);
            setName("MainWindow");
            setSize(new Dimension(1600, 900));
            setTitle("Sistema de Sucursales y Empleados");
            setLocation(utils.getScreenX()/9, utils.getScreenY()/8);
            map_layered_pane = getLayeredPane();

            //Image about
            image_logo = new JLabel(new ImageIcon("src\\resources\\UNA_logo.png"));
            tab_about.add(image_logo, BorderLayout.CENTER);

            //Map Image
            ImageIcon map = new ImageIcon("src\\resources\\Doodle_Map_of_Costa_Rica_With_States_generated.jpg");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(900, 800,  java.awt.Image.SCALE_SMOOTH);
            map.setImage(resizer);
            map_image = new JLabel(map);
            map_image.setFocusable(true);
            map_panel.add(map_image, BorderLayout.CENTER);

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
                repaintWindow();
            }
        });
        /*
        SI HAGO UNA IMPLEMENTACION CON CLICKOUTSIDE PUEDO MANDAR A LLAMARLO DESDE EL PUNTO
         */
        map_image.addMouseListener(new ImageMouseSensor() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
                List<BranchTableInfo> branches = (List<BranchTableInfo>)(List<?>) points;
                for (BranchTableInfo branch : branches) {
                    if(branch.isSelected()){
                        branch.mouseClikedOutside(e);
                    }
                }
                System.out.println(e.getPoint());
                e.consume();
            }
            @Override
            public void mouseClickedOutside(MouseEvent e) {
                List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
                List<BranchTableInfo> branches = (List<BranchTableInfo>)(List<?>) points;
                for (BranchTableInfo branch : branches) {
                    if(branch.isSelected()){
                        selectTableRow(branch);
                    }
                }
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        //Controller initialize other components
        MainWindowViewController.windowInitialized();
        //Window opens
        setVisible(true);
    }

    private void selectTableRow(BranchTableInfo branch) {
        String branchID = branch.getId();
        String tableID;
        for(int i = 0; i < branch_table.getRowCount(); i++){
            tableID = (String) branch_table.getValueAt(i,0);
            if(branchID.equals(tableID)){
                branch_table.setRowSelectionInterval(i, i);
            }
        }
    }
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
    public void setBranchPointOnMap(BranchTableInfo point){
        point.setVisible(false);
        int x = point.getX() + 600;
        int y = point.getY();
        point.setBounds(x, y, 80, 80);
        map_layered_pane.add(point, 1);
        repaintWindow();
    }

    private void repaintWindow() {
        if (getSelectedTabIndex() == 1){
            List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
            for(Component c : points){
                c.setVisible(true);
            }
            map_layered_pane.repaint();
        }
    }
    public String getEmployeesSearchBar(){ return employees_srch_bar.getText(); }

    public String getBranchesSearchBar(){ return branches_srch_bar.getText(); }
}
