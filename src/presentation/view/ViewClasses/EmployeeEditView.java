package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.EmployeeAddViewController;
import presentation.controller.ViewControllers.EmployeeEditViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.model.mouseListener.ImageMouseSensor;
import presentation.model.viewModels.BranchInfo;
import presentation.model.viewModels.componentModels.BranchPointer;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployeeEditView extends ViewParent {
    private JDialog dialog;
    private JTextField edit_emp_ced_text;
    private JTextField edit_emp_nombre_text;
    private JTextField edit_emp_tel_text;
    private JTextField edit_emp_salario_text;
    private JButton edit_emp_save_btn;
    private JButton edit_emp_cancel_btn;
    private JPanel map_panel;
    private JPanel emp_edit_panel;
    private JLabel map_image;
    private GeneralUtilities utils;
    private BranchInfo selectedBranch;
    private JLayeredPane map_layered_pane;

    public EmployeeEditView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(emp_edit_panel)){
            dialog.setContentPane(emp_edit_panel);
            dialog.setName("EmployeeEditView");
            dialog.setSize(new Dimension(1200, 900));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
            map_layered_pane = dialog.getLayeredPane();

            //Map Image
            ImageIcon map = new ImageIcon("src\\resources\\Mapa_de_Costa_Rica_(cantones_y_distritos).png");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(900, 800,  java.awt.Image.SCALE_SMOOTH);
            map.setImage(resizer);
            map_image = new JLabel(map);
            map_image.setFocusable(true);
            map_panel.add(map_image, BorderLayout.CENTER);

        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
    }

    private void clearWindow() {
        edit_emp_ced_text.setText("");
        edit_emp_nombre_text.setText("");
        edit_emp_tel_text.setText("");
        edit_emp_salario_text.setText("");
    }
    public void initComponents() {
        edit_emp_save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getEmployeeID().isEmpty() && !getEmployeeName().isEmpty() && !getEmployeePhoneNumber().isEmpty()
                        && !getEmployeeSalary().isEmpty() && getSelectedBranch() != null){
                    EmployeeEditViewController.saveButtonPressed();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe llenar todos los campos",
                            "Agregar Empleado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        edit_emp_cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        edit_emp_tel_text.addKeyListener(new KeyAdapter()
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
        edit_emp_salario_text.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
                    e.consume();  // ignorar el evento de teclado
                }
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
                EmployeeEditViewController.windowClosed();
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
        EmployeeEditViewController.windowInitialize();
        dialog.setVisible(true);
    }
    public void setModel(Object[] model) {
        //Inicio los JFrame
        edit_emp_ced_text.setText(String.valueOf(model[0]));
        edit_emp_nombre_text.setText(String.valueOf(model[1]));
        edit_emp_tel_text.setText(String.valueOf(model[2]));
        edit_emp_salario_text.setText(String.valueOf(model[3]));
        edit_emp_ced_text.setEnabled(false);
    }
    public String getEmployeeID() { return edit_emp_ced_text.getText();}
    public String getEmployeeName() { return edit_emp_nombre_text.getText();}
    public String getEmployeePhoneNumber() { return edit_emp_tel_text.getText();}
    public String getEmployeeSalary(){ return edit_emp_salario_text.getText();}

    public BranchInfo getSelectedBranch() {
        return selectedBranch;
    }

    public void setBranchPointOnMap(BranchInfo point) {
        point.setVisible(false);
        int x = point.getX() + 245;
        int y = point.getY() - 45;
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

    public void setSelectedBranch(BranchInfo selectedBranch) {
        this.selectedBranch = selectedBranch;
    }
}
