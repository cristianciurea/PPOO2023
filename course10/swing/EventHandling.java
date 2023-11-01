package ro.ase.course10.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

public class EventHandling extends JFrame implements ActionListener {

	String Driver=null; 
    String DB = null; 
    String SQL= null; 

    JTextField tfDriver; 
    JTextField tfDataBase; 
    JTextField tfSQL; 
    JButton btn; 
    JTextArea ta; 
    JButton okbtn; 
    JLabel status;
	
    public EventHandling()
    {
    	 this.setTitle("Swing Application"); 
    	 this.setLayout(new FlowLayout());

         JLabel l1 = new JLabel("Driver"); 
         this.add(l1); 
         tfDriver = new JTextField(40); 
         tfDriver.setText("org.sqlite.JDBC");
         this.add(tfDriver);

         JLabel l2 = new JLabel("DataBase URL"); 
         this.add(l2); 
         tfDataBase = new JTextField(40); 
         tfDataBase.setText("jdbc:sqlite:C:/Users/Administrator/MySQLiteDB");
         this.add(tfDataBase);

         JLabel l5 = new JLabel("SQL statement:"); 
         this.add(l5); 
         tfSQL = new JTextField(40); 
         tfSQL.setText("SELECT * from studenti");
         this.add(tfSQL); 
         
         btn = new JButton("Execute"); 
         this.add(btn); 
         btn.addActionListener(this); 
         
         ta= new JTextArea(10, 40); 
         this.add(ta); 
         ta.addMouseListener(new MouseHandler());
         ta.addMouseMotionListener(new MouseHandler());
         
         okbtn = new JButton("OK"); 
         this.add(okbtn); 
         okbtn.addActionListener(new ButtonClickListener()); 
         
         status = new JLabel();
         this.add(status);
         
         this.addWindowListener(new MyWindowAdapter()); 
    }
    
    private void processData(){ 
        Driver = tfDriver.getText(); 
        if(Driver.equals("")) 
                Driver="org.sqlite.JDBC";

        DB = tfDataBase.getText(); 
        if(DB.equals("")) 
                DB="jdbc:sqlite:C:/Users/Administrator/MySQLiteDB";

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
            Connection con = DriverManager.getConnection(DB); 
            Statement stmt = con.createStatement();

            ResultSet  rs = stmt.executeQuery(SQL); 
            
            ResultSetMetaData rsmd = rs.getMetaData();

            int numCols = rsmd.getColumnCount(); 
            int numRows = 0;
            while(rs.next()) 
            { 
                 String record=""; 
                 for (int i=1; i<=numCols; i++) 
                 {
                     int dataType = rsmd.getColumnType(i); 
                     record = record + getField(rs, dataType, i)+" "; 
                 } 
                 ta.append(record+"\n"); 
                 numRows++;
            }
            rs.close();
            ta.append("Total records: "+numRows);
        } 
        catch(SQLException ex1)
        { 
             System.out.println(ex1.toString()); 
             ta.append(ex1.toString());
        } 
        catch(ClassNotFoundException ex2)
        { 
             System.out.println(ex2.toString()); 
             ta.append(ex2.toString());
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
    
    class ButtonClickListener implements ActionListener
    {
        
    	public void actionPerformed(ActionEvent e) 
    	{
           String command = e.getActionCommand();  
           if (command.equals("OK"))
              ta.append("\nOK button clicked!");
        }		
    }
    
    class MouseHandler implements MouseListener, MouseMotionListener
    {
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			status.setText("Mouse entered!"); 
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			status.setText("Mouse exited!");
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }
    
	public static void main(String[] args) {
		 EventHandling frame = new EventHandling();
		 //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setBounds(1,1,500,500); 
         frame.setVisible(true); 
	}
}
