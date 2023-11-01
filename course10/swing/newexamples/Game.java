package ro.ase.course10.swing.newexamples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
    static int points = 9;
    public static void main(String[] args) {
        // set up frame
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Game");
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // set up panel
        JPanel panel = new JPanel();
        // set layout to 3x3 grid layout
        panel.setLayout(new GridLayout(3, 3));

        // set up point label
        JLabel point = new JLabel();
        point.setText("Points: 9");

        // set up buttons
        JButton[] buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setName("0");
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // get source object
                    JButton b = (JButton) e.getSource();
                    // check if wining tile
                    if (b.getName().equals("1")) {
                        JOptionPane.showMessageDialog(null, "You won with " + points + " points");
                        // set points
                        point.setText("Points: 9");
                        points = 9;
                        // reset buttons
                        for (int i = 0; i < buttons.length; i++) {
                            buttons[i].setName("0");
                            buttons[i].setBackground(Color.WHITE);
                            buttons[i].setEnabled(true);
                        }
                        // random wining tile
                        buttons[(int) (Math.random() * buttons.length)].setName("1");
                        return;
                    }
                    // else
                    points--;
                    point.setText("Points: " + Integer.toString(points));
                    b.setBackground(Color.RED);
                    b.setEnabled(false);
                }
            });
        }
        // random wining tile
        buttons[(int) (Math.random() * buttons.length)].setName("1");

        // add components to panel
        for (int i = 0; i < buttons.length; i++) {
            panel.add(buttons[i]);
        }

        // add components to frame and make it visible
        frame.add(point, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
