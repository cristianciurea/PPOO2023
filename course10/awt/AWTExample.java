package ro.ase.course10.awt;

import java.awt.*; 
import java.awt.event.*; 
import java.sql.*; 

//import javax.examples.course10.swing.JFrame;
 
//public class AWTExample extends JFrame implements ActionListener {
public class AWTExample extends Frame implements ActionListener {

        String Driver=null; 
        String DB = null; 
        String USER = null; 
        String PASS  = null; 
        String SQL= null; 
 
        TextField tfDriver; 
        TextField tfDataBase; 
        TextField tfUserName; 
        TextField tfPassword; 
        TextField tfSQL; 
        Button btn; 
        TextArea ta; 
 
        // Constructorul creeaza interfata aplicatiei si inregistreaza receptorii 
        // evenimentelor la sursele acestora
        public AWTExample(){ 
                setTitle("Database Application"); 
                setLayout(new FlowLayout());
                //FlowLayout organizeaza componentele de la stanga la dreapta si daca se
                //umple randul curent atunci componenta urmatoare va fi plasata in randul urmator

                Label l1 = new Label("Driver"); 
                add(l1); 
                tfDriver = new TextField(60); 
                tfDriver.setText("org.sqlite.JDBC");
                add(tfDriver);

                Label l2 = new Label("DataBase URL"); 
                add(l2); 
                tfDataBase = new TextField(60); 
                tfDataBase.setText("jdbc:sqlite:C:/Users/Administrator/MySQLiteDB");
                add(tfDataBase);

                Label l3 = new Label("User name:"); 
                add(l3); 
                tfUserName = new TextField(60); 
                add(tfUserName);

                Label l4 = new Label("Password:"); 
                add(l4); 
                tfPassword = new TextField(60); 
                add(tfPassword); 

                Label l5 = new Label("SQL:"); 
                add(l5); 
                tfSQL = new TextField(60); 
                tfSQL.setText("SELECT * from studenti");
                add(tfSQL); 
                
                btn = new Button("Executa"); 
                add(btn); 
                btn.addActionListener(this); 
                
                ta= new TextArea(10, 60); 
                add(ta); 
                
                addWindowListener(new MyWindowAdapter()); 
        }

        //Prelucrarea datelor din formularul prezentat in interfata aplicatiei
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
 
        // Tratarea evenimentului generat prin apasarea butonului "Executa" 
        // Se incarca driverul pt. baza de date 
        // Se creaza conexiunea cu baza de date 
        // Se creaza obiectul de tip Statement, care va executa codul SQL 
        public void actionPerformed(ActionEvent e)
        { 
            ta.setText(""); 
            processData(); 
            Connection con = null;
            try
            { 
                Class.forName(Driver); 
                con = DriverManager.getConnection(DB,USER,PASS); 
                Statement stmt = con.createStatement();

                // Se obtin rezultatele selectiei 
                ResultSet  rs = stmt.executeQuery( SQL ); 
                
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
            finally
            {
            	/*try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
            }
        } 

	    private String getField(ResultSet rs, int dataType, int col) throws SQLException 
	    {
		     switch(dataType) 
		     { 
		     /*case Types.DATE: 
		       java.sql.Date date = rs.getDate(col); 
		       return date.toString();
		
		     case Types.TIME: 
		       java.sql.Time time = rs.getTime(col); 
		       return time.toString();
		
		     case Types.TIMESTAMP: 
		       java.sql.Timestamp timestamp = rs.getTimestamp(col); 
		       return timestamp.toString();*/
		
		     //case Types.CHAR: 
		     case Types.VARCHAR: 
		     //case Types.LONGVARCHAR: 
		       String str = rs.getString(col); 
		       return str;
		
		     /*case Types.NUMERIC: 
		     case Types.DECIMAL: 
		       java.math.BigDecimal numeric = rs.getBigDecimal(col, 10); 
		       return numeric.toString();
		
		     case Types.BIT: 
		       boolean bit = rs.getBoolean(col); 
		       return (new Boolean(bit)).toString();
		
		    case Types.TINYINT: 
		       byte tinyint = rs.getByte(col); 
		       return (new Integer(tinyint)).toString();
		
		     case Types.SMALLINT: 
		       short smallint = rs.getShort(col); 
		       return (new Integer(smallint)).toString();*/
		
		     case Types.INTEGER: 
		       int integer = rs.getInt(col); 
		       return (new Integer(integer)).toString();
		
		     /*case Types.BIGINT: 
		       long bigint = rs.getLong(col); 
		       return (new Long(bigint)).toString();
		
		     case Types.REAL: 
		       float real = rs.getFloat(col); 
		       return (new Float(real)).toString();
		
		     case Types.FLOAT: 
		     case Types.DOUBLE: 
		       double longreal = rs.getDouble(col); 
		       return (new Double(longreal)).toString();*/
		
		    /*case Types.BINARY: 
		    case Types.VARBINARY: 
		    case Types.LONGVARBINARY: 
		       byte[] binary = rs.getBytes(col); 
		       return new String(binary); */
		   } 
		   return ""; 
	    } 
 
        // Pt. tratarea evenimentului WindowClosing 
	    class MyWindowAdapter extends WindowAdapter
		{ 
		      public void windowClosing(WindowEvent e )
		      { 
		             System.exit(0); 
		      } 
		}
 
        public static void main(String args[])
        { 
                AWTExample frame = new AWTExample(); 
                frame.setBounds(1,1,500,500); 
                frame.setVisible(true); 
        } 
}