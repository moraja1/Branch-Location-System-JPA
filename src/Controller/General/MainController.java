package Controller.General;


import View.LogInWindow;
import View.ViewParent;

import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;

public class MainController {
    private static ViewParent main_Window;
    private static HashMap<String, ViewParent> application_panels = new HashMap<>();
    private static Container window_panel;

    /**
     * @param view
     * @return Return the view of the windows after save it in a hashmap.
     */
    private static Container recordPanel(ViewParent view) {
        String view_name = view.getName();
        if(!application_panels.containsKey(view_name)){
            application_panels.put(view_name, view);
        }
        return application_panels.get(view_name);
    }

    /**
     * Execute first window's program. And record the window panel
     * in a hashmap to fast future access.
     */
    public static void initFlow(){
        main_Window = new LogInWindow();
        window_panel = recordPanel(main_Window);
        main_Window.initComponents();
    }

    /**
     * Alternate within existent windows.
     * @param newPanel
     */
    public static void changeWindow(JPanel newPanel){

    }
}
