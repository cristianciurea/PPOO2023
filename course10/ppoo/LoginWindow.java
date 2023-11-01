package ro.ase.course10.ppoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow {

    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;

    public LoginWindow() {

        panel1 = new JPanel();

        textField1 = new JTextField(20);
        passwordField1 = new JPasswordField(20);

        panel1.add(textField1);
        panel1.add(passwordField1);
        panel1.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Autentificare cu succes!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame= new JFrame("Login application");
        frame.setContentPane(new LoginWindow().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
