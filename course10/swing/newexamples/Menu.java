package ro.ase.course10.swing.newexamples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Menu {
    public static void main(String[] args) {
        // define list
        ArrayList<Integer> list = new ArrayList<>();

        // set up frame
        JFrame frame = new JFrame();
        frame.setSize(300, 500);
        frame.setTitle("List Menu");
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up panel
        JPanel panel = new JPanel();
        // set layout to 5x1 grid layout
        panel.setLayout(new GridLayout(5, 1));

        // set up buttons
        JButton rand, add, search, print, exit;
        rand = new JButton("Fill List with Random Numbers");
        rand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 20; i++) {
                    // add random number of range [1,100)
                    list.add((int) (1 + Math.random() * 100));
                }
            }
        });
        add = new JButton("Add specific number to List");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input;
                try {
                    input = Integer.parseInt(JOptionPane.showInputDialog("Give me a number"));
                    list.add(input);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An Error Occured!");
                }
            }
        });
        search = new JButton("Search for a number in List");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input;
                try {
                    input = Integer.parseInt(JOptionPane.showInputDialog("Give me a number"));
                    if (list.contains(input)) {
                        JOptionPane.showMessageDialog(null, input + " was found!");
                    } else {
                        JOptionPane.showMessageDialog(null, input + " wasn't found!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An Error Occured!");
                }
            }
        });
        print = new JButton("Print List");
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    s += list.get(i) + " ";
                }
                JOptionPane.showMessageDialog(null, "List: " + s);
            }
        });
        exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bye!");
                System.exit(0);
            }
        });

        // add components to panel
        panel.add(rand);
        panel.add(add);
        panel.add(search);
        panel.add(print);
        panel.add(exit);

        // add panel to frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }
}
