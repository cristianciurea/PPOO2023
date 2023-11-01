package ro.ase.course10.swing.newexamples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    static double num;
    public static void main(String[] args) {
        // set up frame
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Simple Calculator");
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up panel
        JPanel panel = new JPanel();
        // set layout to 5x2 grid layout
        panel.setLayout(new GridLayout(5, 2));

        // set up answer label
        JLabel answer = new JLabel();

        // set up number text fields
        JTextField num1 = new JTextField();
        JTextField num2 = new JTextField();

        // set up buttons
        JButton add = new JButton();
        add.setText("+");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    num = Double.parseDouble(num1.getText())
                            + Double.parseDouble(num2.getText());
                    answer.setText(Double.toString(num));
                } catch (Exception e) {
                    answer.setText("Error!");
                }
            }
        });
        JButton sub = new JButton();
        sub.setText("-");
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    num = Double.parseDouble(num1.getText())
                            - Double.parseDouble(num2.getText());
                    answer.setText(Double.toString(num));
                } catch (Exception e) {
                    answer.setText("Error!");
                }
            }
        });
        JButton mul = new JButton();
        mul.setText("*");
        mul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    num = Double.parseDouble(num1.getText())
                            * Double.parseDouble(num2.getText());
                    answer.setText(Double.toString(num));
                } catch (Exception e) {
                    answer.setText("Error!");
                }
            }
        });
        JButton div = new JButton();
        div.setText("/");
        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    num = Double.parseDouble(num1.getText())
                            / Double.parseDouble(num2.getText());
                    answer.setText(Double.toString(num));
                } catch (Exception e) {
                    answer.setText("Error!");
                }
            }
        });

        // add components to panel
        panel.add(new JLabel("Number 1"));
        panel.add(new JLabel("Number 2"));
        panel.add(num1);
        panel.add(num2);
        panel.add(add);
        panel.add(sub);
        panel.add(mul);
        panel.add(div);
        panel.add(new JLabel("Answer"));
        panel.add(answer);

        // add panel to frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }
}

