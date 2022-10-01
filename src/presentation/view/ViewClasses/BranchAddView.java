package presentation.view.ViewClasses;

import presentation.controller.ViewControllers.BranchAddViewController;
import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.model.mouseListener.ImageMouseSensor;
import presentation.model.viewModels.BranchInfo;
import presentation.view.ViewParent;
import presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BranchAddView extends ViewParent {

    private JDialog dialog;
    private JTextField add_branch_cod_text;
    private JTextField add_branch_ref_text;
    private JTextField add_branch_dir_text;
    private JTextField add_branch_zon_text;
    private JButton add_branch_guardar_btn;
    private JButton add_branch_cancel_btn;
    private JPanel map_panel;
    private JLabel map_image;
    private JPanel branch_Add_Panel;
    private JLayeredPane map_layered_pane;
    private BranchInfo newBranch = null;
    private GeneralUtilities utils;
    public BranchAddView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(branch_Add_Panel)){
            dialog.setContentPane(branch_Add_Panel);
            dialog.setName("BranchAddView");
            dialog.setSize(new Dimension(1000, 800));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
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
                BranchAddViewController.windowClosed();
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
        map_image.addMouseListener(new ImageMouseSensor() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BranchAddViewController.clickOnMap(e.getPoint());
            }
            @Override
            public void mouseClickedOutside(MouseEvent e) {
                /*java.util.List<Component> points = java.util.List.of(map_layered_pane.getComponentsInLayer(0));
                java.util.List<BranchInfo> branches = (java.util.List<BranchInfo>)(java.util.List<?>) points;
                for (BranchInfo branch : branches) {
                    if(branch.isSelected()){
                        selectedBranch = branch;
                    }
                }
                e.consume();*/
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        dialog.setVisible(true);
    }
    public void updatePointer(BranchInfo newPointer) {
        cleanLayer();
        newPointer.setVisible(false);
        int x = newPointer.getX() + 253;
        int y = newPointer.getY() - 45;
        newPointer.setBounds(x, y, 80, 80);
        map_layered_pane.add(newPointer, 1);
        repaintWindow();
    }

    private void cleanLayer() {

    }

    private void repaintWindow() {
        java.util.List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        for(Component c : points){
            c.setVisible(true);
            c.setEnabled(true);
        }
        map_layered_pane.repaint();
    }
    public String getBranchID() {return add_branch_cod_text.getText();}

    public String getBranchReference() {
        return add_branch_ref_text.getText();
    }
    public String getBranchDir() {return add_branch_dir_text.getText();}

    public String getBranchZone() {
        return add_branch_zon_text.getText();
    }
    public JLabel getNewBranch() {
        return newBranch;
    }
    public void setNewBranch(BranchInfo newBranch) {
        if(this.newBranch == null){
            this.newBranch = newBranch;
            updatePointer(newBranch);
        }else{
            map_layered_pane.remove(this.newBranch);
            this.newBranch = newBranch;
            updatePointer(newBranch);
        }

    }
}
