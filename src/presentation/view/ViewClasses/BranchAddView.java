package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.BranchAddViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BranchAddView extends ViewParent {

    private JDialog dialog;
    private JTextField add_branch_cod_text;
    private JTextField add_branch_ref_text;
    private JTextField add_branch_dir_text;
    private JTextField add_branch_zon_text;
    private JButton add_branch_guardar_btn;
    private JButton add_branch_cancel_btn;
    private JPanel add_branch_map_panel;
    private JLabel map_image;
    private JPanel branch_Add_Panel;

    private GeneralUtilities utils;
    public BranchAddView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(branch_Add_Panel)){
            dialog.setContentPane(branch_Add_Panel);
            dialog.setName("BranchAddView");
            dialog.setSize(new Dimension(500, 400));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
    }

    private void clearWindow() {
        add_branch_cod_text.setText("");
        add_branch_ref_text.setText("");
        add_branch_dir_text.setText("");
        add_branch_zon_text.setText("");
    }
    @Override
    public void initComponents() {
        add_branch_guardar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchAddViewController.addButtonPressed();
            }
        });
        add_branch_cancel_btn.addActionListener(new ActionListener() {
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

        dialog.setVisible(true);
    }
    public String getBranchID() {return add_branch_cod_text.getText();}

    public String getBranchReference() {
        return add_branch_ref_text.getText();
    }
    public String getBranchDir() {return add_branch_dir_text.getText();}

    public String getBranchZone() {
        return add_branch_zon_text.getText();
    }
}
