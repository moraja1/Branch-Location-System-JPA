package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.BranchAddViewController;
import presentation.controller.ViewControllers.BranchEditViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.model.mouseListener.ImageMouseSensor;
import presentation.model.viewModels.BranchInfo;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BranchEditView extends ViewParent {

    private JDialog dialog;
    private JTextField edit_branch_cod_text;
    private JTextField edit_branch_ref_text;
    private JTextField edit_branch_dir_text;
    private JTextField edit_branch_zon_text;
    private JButton edit_branch_save_btn;
    private JButton edit_branch_cancel_btn;
    private JPanel map_panel;
    private JPanel branch_edit_panel;
    private JLabel map_image;
    private JLayeredPane map_layered_pane;
    private GeneralUtilities utils;
    private BranchInfo newBranch = null;

    public BranchEditView(Object[] model) {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(branch_edit_panel)){
            dialog.setContentPane(branch_edit_panel);
            dialog.setName("BranchAddView");
            dialog.setSize(new Dimension(1000, 800));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
            map_layered_pane = dialog.getLayeredPane();

            //Map Image
            ImageIcon map = new ImageIcon("src\\resources\\Mapa_de_Costa_Rica_(cantones_y_distritos).png");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(700, 700,  java.awt.Image.SCALE_SMOOTH);
            map.setImage(resizer);
            map_image = new JLabel(map);
            map_image.setFocusable(true);
            map_panel.add(map_image);
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
        if(newBranch != null){
            map_layered_pane.remove(newBranch);
        }
        map_layered_pane.repaint();
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
        edit_branch_save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getBranchID().isEmpty() && !getBranchReference().isEmpty() && !getBranchDir().isEmpty()
                        && !getBranchZone().isEmpty() && getNewBranch() != null){
                    BranchEditViewController.saveButtonPressed();
                }else if(getNewBranch() == null){
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar en el mapa la ubicacion de la sucursal.",
                            "Editar Sucursal", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe llenar todos los campos.",
                            "Editar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
                BranchEditViewController.windowInitialize();
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
                MainWindowViewController.windowInitialized();
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
                BranchEditViewController.clickOnMap(e.getPoint());
            }
            @Override
            public void mouseClickedOutside(MouseEvent e) {
                e.consume();
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        BranchEditViewController.windowInitialize();
        dialog.setVisible(true);
    }
    public void setNewBranch(BranchInfo newBranch) {
        if (this.newBranch != null) {
            map_layered_pane.remove(this.newBranch);
        }
        this.newBranch = newBranch;
        updatePointer(newBranch);
    }
    public void updatePointer(BranchInfo newPointer) {
        int x = newPointer.getX() + 253;
        int y = newPointer.getY() - 45;
        newPointer.setBounds(x, y, 80, 80);
        map_layered_pane.add(newPointer, 1);
        map_layered_pane.repaint();
    }
    public String getBranchID() {return edit_branch_cod_text.getText();}

    public String getBranchReference() {
        return edit_branch_ref_text.getText();
    }
    public String getBranchDir() {return edit_branch_dir_text.getText();}
    public BranchInfo getNewBranch() {
        return newBranch;
    }
    public String getBranchZone() {
        return edit_branch_zon_text.getText();
    }
}
