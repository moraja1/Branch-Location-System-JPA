package presentation.controller.ViewControllers;

import presentation.view.ViewClasses.BranchEditView;

import java.awt.*;

public class BranchEditViewController {

    private static BranchEditView branch_edit_view;

    public static BranchEditView getBranch_edit_view(Object[] model) {
        branch_edit_view = new BranchEditView(model);
        return branch_edit_view;
    }

    public static void saveButtonPressed() {
        String id = branch_edit_view.getBranchID();
        String reference = branch_edit_view.getBranchReference();
        String address = branch_edit_view.getBranchDir();
        String zoning_percentage = branch_edit_view.getBranchZone();


    }
    public static void windowClosed(){
        MainWindowViewController.windowInitialized();
    }

    public static void clickOnMap(Point point) {
    }
}
