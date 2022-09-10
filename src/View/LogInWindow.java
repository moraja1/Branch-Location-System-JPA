package View;

import Controller.LogInWindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInWindow extends ViewParent {

    private JButton logIn_Button;
    private JButton register_Button;
    private JTextField txt_email;
    private JTextField txt_password;

    private JPanel logIn_panel;

    /**
     * Constructs Log In Window while its hidden.
     */
    public LogInWindow(){
        if(!getContentPane().equals(logIn_panel)){
            setContentPane(logIn_panel);
            setName("LogInWindow");
            setSize(new Dimension(400, 200));
            setTitle("Log In Window");
        }
    }

    /**
     * Initialize all components listeners and set the window visible.
     */
    @Override
    public void initComponents(){
        logIn_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInWindowController.logInButtonClicked();
            }
        });

        register_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setVisible(true);
    }

    /**
     *
     * @return Text from email JTextField
     */
    public String getTxt_email() {
        return txt_email.getText();
    }

    /**
     *
     * @return Text from password JTextField
     */
    public String getTxt_password() {
        return txt_password.getText();
    }
}
