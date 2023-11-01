package ro.ase.course10.app;

import ro.ase.course10.calculator.Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton button1;
    private JPanel panel1;
    private JTextField textFieldUser;
    private JPasswordField textFieldPass;

    public App() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldUser.getText().equals("admin") && textFieldPass.getText().equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Autentificare cu succes! " + textFieldUser.getText());
                    Calculator frame = new Calculator();
                    frame.setBounds(1, 1, 650, 500);
                    frame.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "Autentificare esuata!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Swing Application");
        frame.setContentPane(new App().panel1);
        frame.setBounds(1,1,650,500);
        //frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
