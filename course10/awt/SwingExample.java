package ro.ase.course10.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SwingExample extends JFrame implements ActionListener {

	String Driver=null; 
    String DB = null; 
    String USER = null; 
    String PASS  = null; 
    String SQL= null; 

    JTextField tfDriver; 
    JTextField tfDataBase; 
    JTextField tfUserName; 
    JTextField tfPassword; 
    JTextField tfSQL; 
    JButton btn; 
    JTextArea ta; 
	
    public SwingExample()
    {
    	 setTitle("Database Application"); 
         setLayout(new FlowLayout());
         //FlowLayout organizeaza componentele de la stanga la dreapta si daca se
         //umple randul curent atunci componenta urmatoare va fi plasata in randul urmator

         JLabel l1 = new JLabel("Driver"); 
         add(l1); 
         tfDriver = new JTextField(40); 
         tfDriver.setText("org.sqlite.JDBC");
         add(tfDriver);

         JLabel l2 = new JLabel("DataBase URL"); 
         add(l2); 
         tfDataBase = new JTextField(40); 
         tfDataBase.setText("jdbc:sqlite:C:/Users/Administrator/MySQLiteDB");
         add(tfDataBase);

         JLabel l3 = new JLabel("User name:"); 
         add(l3); 
         tfUserName = new JTextField(40); 
         add(tfUserName);

         JLabel l4 = new JLabel("Password:"); 
         add(l4); 
         tfPassword = new JTextField(40); 
         add(tfPassword); 

         JLabel l5 = new JLabel("SQL statement:"); 
         add(l5); 
         tfSQL = new JTextField(40); 
         tfSQL.setText("SELECT * from studenti");
         add(tfSQL); 
         
         btn = new JButton("Executa"); 
         add(btn); 
         btn.addActionListener( this); 
         
         ta= new JTextArea(10, 40); 
         add(ta); 
         
         addWindowListener(new MyWindowAdapter()); 
    }
    
    private void processData(){ 
        Driver = tfDriver.getText(); 
        if(Driver.equals("")) 
                Driver="org.sqlite.JDBC";

        DB = tfDataBase.getText(); 
        if(DB.equals("")) 
                DB="jdbc:sqlite:C:/Users/Administrator/MySQLiteDB";

        USER = tfUserName.getText();

        PASS = tfPassword.getText();

        SQL = tfSQL.getText(); 
        if (SQL.equals("")) 
                SQL="SELECT * from studenti"; 
    } 
    
    private String getField(ResultSet rs, int dataType, int col) throws SQLException 
    {
	     switch(dataType) 
	     {  
	     	case Types.VARCHAR: 
		       String str = rs.getString(col); 
		       return str;
	     	case Types.INTEGER: 
			   int x = rs.getInt(col); 
			   return (new Integer(x)).toString();
	     } 
		 return ""; 
    } 
    
    @Override
	public void actionPerformed(ActionEvent arg0) {
    	ta.setText(""); 
        processData(); 
        try
        { 
            Class.forName(Driver); 
            Connection con = DriverManager.getConnection(DB,USER,PASS); 
            Statement stmt = con.createStatement();

            // Se obtin rezultatele selectiei 
            ResultSet  rs = stmt.executeQuery(SQL); 
            
            // Informatii (metadatele) despre selectie 
            // Se poate obtine atat numarul coloanelor din selectie, cat si tipurile acestor  coloane
            ResultSetMetaData rsmd = rs.getMetaData();

            // Prelucrarea rezultatelor 
            int numCols = rsmd.getColumnCount(); 
            while(rs.next()) 
            { 
                 String record=""; 
                 for (int i=1; i<=numCols; i++) 
                 {
                     int dataType = rsmd.getColumnType(i); 
                     record = record + getField(rs, dataType, i)+" "; 
                 } 
                 ta.append(record+"\n"); 
            }
        } 
        catch(SQLException ex1)
        { 
             System.out.println(ex1.toString()); 
        } 
        catch(ClassNotFoundException ex2)
        { 
             System.out.println(ex2.toString()); 
        }
	}
    
    // Pt. tratarea evenimentului WindowClosing 
    class MyWindowAdapter extends WindowAdapter
	{ 
	      public void windowClosing(WindowEvent e )
	      { 
	             System.exit(0); 
	      } 
	}
    
	public static void main(String[] args) {
		 SwingExample frame = new SwingExample();
		 frame.setBounds(1,1,500,500); 
         frame.setVisible(true); 
	}
}
