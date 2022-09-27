package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.EmployeeEditViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeEditView extends ViewParent {
    private JDialog dialog;
    private JTextField edit_emp_ced_text;
    private JTextField edit_emp_nombre_text;
    private JTextField edit_emp_tel_text;
    private JTextField edit_emp_salario_text;
    private JTextField edit_emp_suc_text;
    private JButton edit_emp_guardar_btn;
    private JButton edit_emp_cancel_btn;
    private JPanel edit_emp_map_panel;
    private JPanel emp_edit_panel;
    private GeneralUtilities utils;

    public EmployeeEditView(Object[] model) {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(emp_edit_panel)){
            dialog.setContentPane(emp_edit_panel);
            dialog.setName("EmployeeEditView");
            dialog.setSize(new Dimension(500, 400));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
        initComponents(model);
    }

    private void clearWindow() {
        edit_emp_ced_text.setText("");
        edit_emp_nombre_text.setText("");
        edit_emp_tel_text.setText("");
        edit_emp_salario_text.setText("");
        edit_emp_suc_text.setText("");
    }

    private void initComponents(Object[] model) {
        edit_emp_ced_text.setText(String.valueOf(model[0]));
        edit_emp_nombre_text.setText(String.valueOf(model[1]));
        edit_emp_tel_text.setText(String.valueOf(model[2]));
        edit_emp_salario_text.setText(String.valueOf(model[3]));
        edit_emp_suc_text.setText(String.valueOf(model[4]));
        edit_emp_ced_text.setEnabled(false);
    }

    public void initComponents() {
        edit_emp_guardar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeEditViewController.saveButtonPressed();
            }
        });
        edit_emp_cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
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
        dialog.setVisible(true);
    }

    public String getEmployeeID() { return edit_emp_ced_text.getText();}
    public String getEmployeeName() { return edit_emp_nombre_text.getText();}
    public String getEmployeePhoneNumber() { return edit_emp_tel_text.getText();}
    public String getEmployeeSalary(){ return edit_emp_salario_text.getText();}
    public String getEmployeeBranch(){ return edit_emp_suc_text.getText();}
}
