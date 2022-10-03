package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.model.viewModels.BranchInfo;
import presentation.view.ViewClasses.BranchEditView;

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
        String id = branch_edit_view.getBranchID();
        String reference = branch_edit_view.getBranchReference();
        String address = branch_edit_view.getBranchDir();
        String zoning_percentage = branch_edit_view.getBranchZone();
    }
    public static void windowInitialize() {
        BranchInfo point = DataServices.getBranchInfo(String.valueOf(model[1]));
        branch_edit_view.setPointOnMap(point);
    }
    public static void clickOnMap(Point point) {
    }
    public static void windowClosed(){
        model = null;
        MainWindowViewController.windowInitialized();
    }
}
