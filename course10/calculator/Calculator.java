package ro.ase.course10.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton calculeazaButton;


    public Calculator() {

        setTitle("Calculator Application");

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        String operatii[] = { "adunare","inmultire", "impartire"};
        JComboBox comboBox = new JComboBox(operatii);
        comboBox.setBounds(40,40,90,20);
        panel1.add(comboBox);

        JLabel label1 = new JLabel("Valoare 1");
        panel1.add(label1);
        textField1 = new JTextField(10);
        panel1.add(textField1);

        JLabel label2 = new JLabel("Valoare 2");
        panel1.add(label2);
        textField2 = new JTextField(10);
        panel1.add(textField2);

        JLabel label3 = new JLabel("Total");
        panel1.add(label3);
        textField3 = new JTextField(10);
        panel1.add(textField3);

        calculeazaButton = new JButton("Calculeaza");
        panel1.add(calculeazaButton);

        add(panel1);

        calculeazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (comboBox.getSelectedIndex()) {
                    case 0:
                        try {
                            int val1 = Integer.valueOf(textField1.getText());
                            int val2 = Integer.valueOf(textField2.getText());
                            int total = val1 + val2;
                            textField3.setText(String.valueOf(total));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 1:
                        //to do
                        break;
                    case 2:
                        //to do
                        break;
                }
            }
        });
    }

    public static void main(String[] args){
        Calculator frame = new Calculator();
        frame.setBounds(1,1,650,500);
        frame.setVisible(true);
    }
}
