package ro.ase.course10.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

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

    //////////////////////////////
    JButton okbtn;
    JLabel status;

    public SwingExample()
    {
        setTitle("Database Application");
        setLayout(new FlowLayout());

        JLabel l1 = new JLabel("Driver");
        add(l1);
        tfDriver = new JTextField(60);
        tfDriver.setText("org.sqlite.JDBC");
        add(tfDriver);

        JLabel l2 = new JLabel("DataBase URL");
        add(l2);
        tfDataBase = new JTextField(60);
        tfDataBase.setText("jdbc:sqlite:C:/Users/Administrator/MySQLiteDB");
        add(tfDataBase);

        JLabel l3 = new JLabel("User name:");
        add(l3);
        tfUserName = new JTextField(60);
        add(tfUserName);

        JLabel l4 = new JLabel("Password:");
        add(l4);
        tfPassword = new JTextField(60);
        add(tfPassword);

        JLabel l5 = new JLabel("SQL statement:");
        add(l5);
        tfSQL = new JTextField(60);
        tfSQL.setText("SELECT * from studenti");
        add(tfSQL);

        btn = new JButton("Executa");
        add(btn);
        btn.addActionListener( this);

        ta= new JTextArea(10, 60);
        add(ta);

        addWindowListener(new MyWindowAdapter());

        ////////////////////////////////////////////
        okbtn = new JButton("OK");
        this.add(okbtn);

        status = new JLabel();
        this.add(status);

        ta.addMouseListener(new MouseHandler());
        ta.addMouseMotionListener(new MouseHandler());

        okbtn.addActionListener(new ButtonClickListener());
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
    public void actionPerformed(ActionEvent e) {
        ta.setText("");
        processData();
        try
        {
            Class.forName(Driver);
            Connection con = DriverManager.getConnection(DB,USER,PASS);
            Statement stmt = con.createStatement();

            ResultSet  rs = stmt.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();

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
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    class MyWindowAdapter extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

//////////////////////////////////////////////////////////
    class MouseHandler implements MouseListener, MouseMotionListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            status.setText("Mouse entered!");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            status.setText("Mouse exited!");
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    ///////////////////////////////////
    class ButtonClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK"))
                ta.append("\nOK button clicked!");
        }
    }

    public static void main(String[] args) {
        SwingExample frame = new SwingExample();
        frame.setBounds(1,1,650,500);
        frame.setVisible(true);
    }
}
