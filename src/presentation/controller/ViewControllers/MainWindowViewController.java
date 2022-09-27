package presentation.controller.ViewControllers;

import business.DataServices;
import presentation.model.viewModels.BranchTableInfo;
import presentation.model.viewModels.EmployeeTableInfo;
import presentation.model.viewModels.tableModels.BranchTableModel;
import presentation.model.viewModels.tableModels.EmployeeTableModel;
import presentation.view.ViewClasses.MainWindow;

import java.util.List;

public class MainWindowViewController {
    private static MainWindow main_window = new MainWindow();

    public static MainWindow getMain_window() {
        return main_window;
    }
    public static void windowInitialized() {
        updateTables();
        updateImages();
    }
    public static void updateTables() {
        int selectedTab = main_window.getSelectedTabIndex();
        switch (selectedTab){
            case 0:
                List<EmployeeTableInfo> employees = DataServices.getEmployeesForTable();
                EmployeeTableModel empTableModel = new EmployeeTableModel(employees);
                main_window.setTableModel(empTableModel);
                break;
            case 1:
                List<BranchTableInfo> branches = DataServices.getBranchesForTable();
                BranchTableModel braTableModel = new BranchTableModel(branches);
                main_window.setTableModel(braTableModel);
                break;
            default:
                break;
        }
    }
    private static void updateImages() {
        List<BranchTableInfo> branches = DataServices.getBranchesForTable();
        for(BranchTableInfo branch : branches){
            System.out.println(branch.getPoint().getLocation());
            main_window.setBranchPointOnMap(branch.getPoint());
        }
    }
    public static void addEmployee() {

    }
    public static void editEmployee() {
    }
    public static void eraseEmployee() {
    }
    public static void searchEmployee() {
    }
    public static void reportEmployee() {
    }
    //BRANCHES
    public static void addBranch() {
    }
    public static void editBranch() {
    }
    public static void eraseBranch() {
    }
    public static void searchBranch() {
    }
    public static void reportBranch() {
    }
}
