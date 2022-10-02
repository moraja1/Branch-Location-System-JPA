package presentation.view.ViewClasses;


import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.model.mouseListener.ImageMouseSensor;
import presentation.model.viewModels.BranchInfo;
import presentation.view.utils.GeneralUtilities;
import presentation.view.ViewParent;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
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
            setLocation(utils.getScreenX()/11, utils.getScreenY()/11);
            map_layered_pane = getLayeredPane();

            //Image about
            image_logo = new JLabel(new ImageIcon("src\\resources\\UNA_logo.png"));
            tab_about.add(image_logo, BorderLayout.CENTER);

            //Map Image
            ImageIcon map = new ImageIcon("src\\resources\\Mapa_de_Costa_Rica_(cantones_y_distritos).png");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(700, 700,  java.awt.Image.SCALE_SMOOTH);
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
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar un empleado de la tabla",
                            "Editar Empleado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        erase_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(emp_table.getSelectedRow() != -1){
                    int n = JOptionPane.showConfirmDialog(new JFrame(), "Está seguro que desea borrar el empleado?",
                            "Confirmación requerida", JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        MainWindowViewController.eraseEmployee();
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar un empleado de la tabla",
                            "Eliminar Empleado", JOptionPane.WARNING_MESSAGE);
                }
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
                if(branch_table.getSelectedRow() != -1){
                    MainWindowViewController.editBranch();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar una Sucursal de la tabla",
                            "Editar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        erase_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(branch_table.getSelectedRow() != -1){
                    int n = JOptionPane.showConfirmDialog(new JFrame(), "Está seguro que desea borrar la sucursal?",
                            "Confirmación requerida", JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        MainWindowViewController.eraseEmployee();
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar una Sucursal en el mapa",
                            "Eliminar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
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
        employees_srch_bar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srch_employee_button.getActionListeners()[0].actionPerformed(e);
            }
        });
        employees_srch_bar.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(employees_srch_bar.getText().isEmpty()){
                    MainWindowViewController.updateTables();
                }
            }
        });
        map_image.addMouseListener(new ImageMouseSensor() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
                List<BranchInfo> branches = (List<BranchInfo>)(List<?>) points;
                for (BranchInfo branch : branches) {
                    if(branch.isSelected()){
                        branch.mouseClickedOutside(e);
                    }
                }
                e.consume();
            }
            @Override
            public void mouseClickedOutside(MouseEvent e) {
                List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
                List<BranchInfo> branches = (List<BranchInfo>)(List<?>) points;
                for (BranchInfo branch : branches) {
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

    private void selectTableRow(BranchInfo branch) {
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
    public void setBranchPointOnMap(BranchInfo point){
        point.setVisible(false);
        int x = point.getX() + 650;
        int y = point.getY() - 30;
        point.setBounds(x, y, 80, 80);
        map_layered_pane.add(point, 1);
        repaintWindow();
    }

    private void repaintWindow() {
        List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        if (getSelectedTabIndex() == 1){
            for(Component c : points){
                c.setVisible(true);
                c.setEnabled(true);
            }
            map_layered_pane.repaint();
        }else{
            for(Component c : points){
                c.setVisible(false);
                c.setEnabled(false);
            }
            map_layered_pane.repaint();
        }
    }
    public JTable getSelectedTable() {
        if (getSelectedTabIndex() == 0){
            return emp_table;
        }else{
            return branch_table;
        }

    }
    public String getEmployeesSearchBar(){ return employees_srch_bar.getText(); }

    public String getBranchesSearchBar(){ return branches_srch_bar.getText(); }

    public void cleanLayers() {
        List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        for(int i = 0; i < points.size(); i++){
            map_layered_pane.remove(points.get(i));
        }
    }
}
