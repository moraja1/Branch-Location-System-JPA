package controller.ViewControllers;

import view.ViewClasses.LogInWindow;

public class LogInWindowViewController {
    private static String email;
    private static String password;
    private static LogInWindow logIn_window = new LogInWindow();

    /**
     * Scope called in every Class Reference.
     * Obtains email and password value from respectively JTextFields.
     */
    static{
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
