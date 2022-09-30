package presentation.controller.ViewControllers;

import presentation.view.ViewClasses.BranchAddView;

public class BranchAddViewController {
    private static BranchAddView branch_add_view;

    public static  BranchAddView getBranch_add_view(){
        branch_add_view = new BranchAddView();
        return branch_add_view;
    }

    public static void addButtonPressed(){
        String id = branch_add_view.getBranchID();
        String reference = branch_add_view.getBranchReference();
        String address = branch_add_view.getBranchDir();
        String zoning_percentage = branch_add_view.getBranchZone();

    }
    public static void windowClosed(){
        MainWindowViewController.windowInitialized();
    }
}

