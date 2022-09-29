package presentation.controller.flowController;

import presentation.controller.ViewControllers.MainWindowViewController;
import presentation.view.ViewParent;
import java.util.HashMap;

public class MainController {
    private static ViewParent main_Window;
    private static HashMap<String, ViewParent> application_panels = new HashMap<>();

    /**
     * Execute first window's program. And record the window panel
     * in a hashmap to fast future access.
     */
    public static void initFlow(){
        main_Window = MainWindowViewController.getMain_window();
        recordPanel(main_Window);
        main_Window.initComponents();
    }

    /**
     * @param view
     * Save the window in a hashmap to fast future access.
     */
    private static void recordPanel(ViewParent view) {
        String view_name = view.getName();
        if(!application_panels.containsKey(view_name)){
            application_panels.put(view_name, view);
        }
    }

    /**
     * Alternate within existent windows.
     * @param newView
     */
    public static void changeWindow(ViewParent newView){
        newView.initComponents();
    }
}
