package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.model.viewModels.BranchInfo;
import presentation.view.ViewClasses.BranchEditView;

import javax.swing.*;
import java.awt.*;

public class BranchEditViewController {

    private static BranchEditView branch_edit_view;
    private static Object[] model;

    public static BranchEditView getBranch_edit_view(Object[] model) {
        BranchEditViewController.model = model;
        branch_edit_view = new BranchEditView(model);
        return branch_edit_view;
    }
    public static void saveButtonPressed() {
        //Obtengo los valores
        String id = branch_edit_view.getBranchID();
        String reference = branch_edit_view.getBranchReference();
        String address = branch_edit_view.getBranchDir();
        double zoning_percentage = Double.parseDouble(branch_edit_view.getBranchZone());
        BranchInfo point = branch_edit_view.getNewBranch();

        BranchInfo branch = new BranchInfo(id, reference, address, zoning_percentage, point.getCoords());

        if(DataServices.editBranchExecution(branch)){
            JOptionPane.showMessageDialog(new JFrame(), "Sucursal agregada correctamente", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Error en almacenamiento de los datos", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void windowInitialize() {
        BranchInfo point = DataServices.getBranchInfo(String.valueOf(model[0]));
        branch_edit_view.setNewBranch(point);
    }
    public static void clickOnMap(Point point) {
        String coords = new StringBuilder().append(point.getX()).append(", ").append(point.getY()).toString();
        BranchInfo branchInfo = new BranchInfo(coords);
        branch_edit_view.setNewBranch(branchInfo);
    }
    public static void windowClosed(){
        model = null;
        MainWindowViewController.windowInitialized();
    }
}
