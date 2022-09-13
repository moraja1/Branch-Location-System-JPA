package Controller;

import View.LogInWindow;

public class LogInWindowController {
    private static String email;
    private static String password;
    private static LogInWindow logIn_window;

    /**
     * Scope called in every Class Reference.
     * Obtains email and password value from respectively JTextFields.
     */
    static{
        logIn_window = new LogInWindow();
        email = logIn_window.getTxt_email();
        password = logIn_window.getTxt_password();
    }

    /**
     *
     */
    public static void logInButtonClicked(){

    }

    /**
     *
     */
    public static void registerButtonClicked(){

    }
}
