package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.BranchAddViewController;
import presentation.controller.ViewControllers.EmployeeAddViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.model.mouseListener.ImageMouseSensor;
import presentation.model.viewModels.BranchInfo;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployeeAddView extends ViewParent {
    private JDialog dialog;
    private JTextField add_emp_ced_text;
    private JTextField add_emp_nombre_text;
    private JTextField add_emp_tel_text;
    private JTextField add_emp_salario_text;
    private JButton add_emp_save_btn;
    private JButton add_emp_cancel_btn;
    private JPanel map_panel;
    private JPanel emp_add_panel;
    private JLabel map_image;
    private JLayeredPane map_layered_pane;
    private GeneralUtilities utils;
    private BranchInfo selectedBranch;

    public EmployeeAddView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(emp_add_panel)){
            dialog.setContentPane(emp_add_panel);
            dialog.setName("EmployeeAddView");
            dialog.setSize(new Dimension(1000, 800));
            dialog.setTitle("Sistema de Sucursales y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
            dialog.setResizable(false);
            map_layered_pane = dialog.getLayeredPane();

            //Map Image
            ImageIcon map = new ImageIcon("src\\resources\\Doodle_Map_of_Costa_Rica_With_States_generated.jpg");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(700, 700,  java.awt.Image.SCALE_SMOOTH);
            map.setImage(resizer);
            map_image = new JLabel(map);
            map_image.setFocusable(true);
            map_panel.add(map_image, BorderLayout.CENTER);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
    }

    private void clearWindow() {
        add_emp_ced_text.setText("");
        add_emp_nombre_text.setText("");
        add_emp_tel_text.setText("");
        add_emp_salario_text.setText("");
    }

    @Override
    public void initComponents() {
        add_emp_save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getEmployeeID().isEmpty() && !getEmployeeName().isEmpty() && !getEmployeePhoneNumber().isEmpty()
                        && !getEmployeeSalary().isEmpty() && getSelectedBranch() != null){
                    EmployeeAddViewController.addButtonPressed();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe llenar todos los campos",
                            "Agregar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add_emp_cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                MainWindowViewController.updateTables();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        add_emp_tel_text.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.'))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        add_emp_ced_text.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.'))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        add_emp_salario_text.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.'))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        map_image.addMouseListener(new ImageMouseSensor() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.util.List<Component> points = java.util.List.of(map_layered_pane.getComponentsInLayer(0));
                java.util.List<BranchInfo> branches = (java.util.List<BranchInfo>)(java.util.List<?>) points;
                for (BranchInfo branch : branches) {
                    if(branch.isSelected()){
                        branch.mouseClickedOutside(e);
                    }
                }
                e.consume();
            }
            @Override
            public void mouseClickedOutside(MouseEvent e) {
                java.util.List<Component> points = java.util.List.of(map_layered_pane.getComponentsInLayer(0));
                java.util.List<BranchInfo> branches = (java.util.List<BranchInfo>)(java.util.List<?>) points;
                for (BranchInfo branch : branches) {
                    if(branch.isSelected()){
                        selectedBranch = branch;
                    }
                }
                e.consume();
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        //Controller initialize other components
        EmployeeAddViewController.windowInitialized();
        dialog.setVisible(true);
    }

    public void setBranchPointOnMap(BranchInfo point){
        point.setVisible(false);
        int x = point.getX() + 135;
        int y = point.getY() - 80;
        point.setBounds(x, y, 80, 80);
        map_layered_pane.add(point, 1);
        repaintWindow();
    }

    private void repaintWindow() {
        java.util.List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        for(Component c : points){
            c.setVisible(true);
            c.setEnabled(true);
        }
    }

    public String getEmployeeID() {
        return add_emp_ced_text.getText();
    }
    public String getEmployeeName() { return add_emp_nombre_text.getText();}
    public String getEmployeePhoneNumber() { return add_emp_tel_text.getText();}
    public String getEmployeeSalary(){ return add_emp_salario_text.getText();}
    public BranchInfo getSelectedBranch() {
        return selectedBranch;
    }
}
