package presentation.controller.ViewControllers;

import data.Branch;
import data.dao.modelsDAO.BranchesDAO;
import presentation.view.ViewClasses.BranchEditView;

import javax.swing.*;

public class BranchEditViewController {
    private static BranchEditView branch_edit_view;
    private static BranchesDAO branchesDAO = new BranchesDAO();

    public static BranchEditView getBranch_edit_view(Object[] model) {
        branch_edit_view = new BranchEditView(model);
        return branch_edit_view;
    }

    public static void saveButtonPressed() {
        String id = branch_edit_view.getBranchID();
        String reference = branch_edit_view.getBranchReference();
        String address = branch_edit_view.getBranchDir();
        String zoning_percentage = branch_edit_view.getBranchZone();

        Branch branch= new Branch(id, reference,address,Double.valueOf(zoning_percentage));
        if(branchesDAO.edit(branch)){
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente.");
        }else{
            JOptionPane.showMessageDialog(null, "Operación no realizada.");
        }
    }
    public static void windowClosed(){
        MainWindowViewController.windowInitialized();
    }
}
