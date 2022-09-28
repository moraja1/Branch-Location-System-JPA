package presentation.controller.ViewControllers;

import data.Branch;
import data.dao.modelsDAO.BranchesDAO;
import presentation.view.ViewClasses.BranchAddView;

import javax.swing.*;

public class BranchAddViewController {
    private static BranchAddView branch_add_view;
    private static BranchesDAO branchDAO = new BranchesDAO();

    public static  BranchAddView getDepartment_add_view(){
        branch_add_view = new BranchAddView();
        return branch_add_view;
    }

    public static void addButtonPressed(){
        String id = branch_add_view.getBranchID();
        String reference = branch_add_view.getBranchReference();
        String address = branch_add_view.getBranchDir();
        String zoning_percentage = branch_add_view.getBranchZone();

        Branch branch= new Branch(id, reference,address,Double.valueOf(zoning_percentage));
        if(branchDAO.add(branch)){
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente.");
        }else{
            JOptionPane.showMessageDialog(null, "Operación no realizada.");
        }
    }
    public static void windowClosed(){
        MainWindowViewController.windowInitialized();
    }
}

