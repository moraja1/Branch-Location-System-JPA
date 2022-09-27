package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.EmployeeAddViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeAddView extends ViewParent {
    private JDialog dialog;
    private JTextField add_emp_ced_text;
    private JTextField add_emp_nombre_text;
    private JTextField add_emp_tel_text;
    private JTextField add_emp_salario_text;
    private JButton add_emp_guardar_btn;
    private JButton add_emp_cancel_btn;
    private JTextField add_emp_suc_text;
    private JPanel add_emp_map_panel;
    private JPanel emp_add_panel;

    private GeneralUtilities utils;

    public EmployeeAddView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(emp_add_panel)){
            dialog.setContentPane(emp_add_panel);
            dialog.setName("EmployeeAddView");
            dialog.setSize(new Dimension(500, 400));
            dialog.setTitle("Sistema de Sucursales y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
    }

    private void clearWindow() {
        add_emp_ced_text.setText("");
        add_emp_nombre_text.setText("");
        add_emp_tel_text.setText("");
        add_emp_salario_text.setText("");
        add_emp_suc_text.setText("");
    }

    @Override
    public void initComponents() {
        add_emp_guardar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeAddViewController.addButtonPressed();
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
                MainWindowViewController.updateTable();
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



        dialog.setVisible(true);
    }

    public String getEmployeeID() {
        return add_emp_ced_text.getText();
    }
    public String getEmployeeName() { return add_emp_nombre_text.getText();}
    public String getEmployeePhoneNumber() { return add_emp_tel_text.getText();}
    public String getEmployeeSalary(){ return add_emp_salario_text.getText();}
    public String getEmployeeBranch(){ return add_emp_suc_text.getText();}


}
