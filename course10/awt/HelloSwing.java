package ro.ase.course10.awt;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelloSwing extends JFrame {
	
	private final JButton btn = new JButton();

	public HelloSwing() 
	{
		super();
		this.setTitle("JavaFXHelloWorld Swing App");
		this.getContentPane().setLayout(null);
		this.setBounds(100, 100, 300, 300);
		this.add(makeButton());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JButton makeButton() 
	{
		btn.setText("Click me!");
		btn.setBounds(40, 40, 100, 30);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(btn, "Hello World!");
			}
		});
		return btn;
	}

	public static void main(String[] args) {
		new HelloSwing();
    }
}