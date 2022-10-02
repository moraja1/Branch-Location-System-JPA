package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.model.viewModels.BranchInfo;
import presentation.view.ViewClasses.BranchAddView;

import javax.swing.*;
import java.awt.*;

public class BranchAddViewController {
    private static BranchAddView branch_add_view;

    public static  BranchAddView getBranch_add_view(){
        branch_add_view = new BranchAddView();
        return branch_add_view;
    }
    public static void addButtonPressed(){
        //Obtengo los valores
        String id = branch_add_view.getBranchID();
        String reference = branch_add_view.getBranchReference();
        String address = branch_add_view.getBranchDir();
        double zoning_percentage = Double.parseDouble(branch_add_view.getBranchZone());
        BranchInfo point = branch_add_view.getNewBranch();

        BranchInfo branch = new BranchInfo(id, reference, address, zoning_percentage, point.getCoords());

        if(DataServices.addBranchExecution(branch)){
            JOptionPane.showMessageDialog(new JFrame(), "Sucursal agregada correctamente", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Error en almacenamiento de los datos", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void clickOnMap(Point point) {
        String coords = new StringBuilder().append(point.getX()).append(", ").append(point.getY()).toString();
        BranchInfo branchInfo = new BranchInfo(coords);
        branch_add_view.setNewBranch(branchInfo);
    }
    public static void windowClosed(){
        MainWindowViewController.windowInitialized();
    }
}

