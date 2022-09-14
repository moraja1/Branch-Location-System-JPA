package Controller.General;


import View.ViewClasses.LogInWindow;
import View.ViewParent;

import java.util.HashMap;

public class MainController {
    private static ViewParent main_Window;
    private static HashMap<String, ViewParent> application_panels = new HashMap<>();

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
     * Execute first window's program. And record the window panel
     * in a hashmap to fast future access.
     */
    public static void initFlow(){
        main_Window = new LogInWindow();
        recordPanel(main_Window);
        main_Window.initComponents();
    }

    /**
     * Alternate within existent windows.
     * @param new_View
     */
    public static void changeWindow(ViewParent new_View){

    }
}
