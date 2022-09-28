package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.BranchEditViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BranchEditView extends ViewParent {

    private JDialog dialog;
    private JTextField edit_branch_cod_text;
    private JTextField edit_branch_ref_text;
    private JTextField edit_branch_dir_text;
    private JTextField edit_branch_zon_text;
    private JButton edit_branch_guardar_btn;
    private JButton edit_branch_cancel_btn;
    private JPanel edit_branch_map_panel;
    private JPanel branch_edit_panel;

    private GeneralUtilities utils;

    public BranchEditView(Object[] model) {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(branch_edit_panel)){
            dialog.setContentPane(branch_edit_panel);
            dialog.setName("BranchAddView");
            dialog.setSize(new Dimension(500, 400));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
        initComponents(model);
    }

    private void clearWindow() {
        edit_branch_cod_text.setText("");
        edit_branch_ref_text.setText("");
        edit_branch_dir_text.setText("");
        edit_branch_zon_text.setText("");
    }
    public void initComponents(Object[] model) {
        edit_branch_cod_text.setText(String.valueOf(model[0]));
        edit_branch_ref_text.setText(String.valueOf(model[1]));
        edit_branch_dir_text.setText(String.valueOf(model[2]));
        edit_branch_zon_text.setText(String.valueOf(model[3]));
        edit_branch_cod_text.setEnabled(false);
    }

    @Override
    public void initComponents() {
        edit_branch_guardar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchEditViewController.saveButtonPressed();
            }
        });
        edit_branch_cancel_btn.addActionListener(new ActionListener() {
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


    public String getBranchID() {return edit_branch_cod_text.getText();}

    public String getBranchReference() {
        return edit_branch_ref_text.getText();
    }
    public String getBranchDir() {return edit_branch_dir_text.getText();}

    public String getBranchZone() {
        return edit_branch_zon_text.getText();
    }


}
