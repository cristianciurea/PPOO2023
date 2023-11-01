package ro.ase.course10.swing;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SwingGUI extends Frame implements ActionListener
{
    String Driver = null;
    String DB = null;
    String USER = null;
    String PASS = null;
    String SQL = null;

    TextField tfDriver;
    TextField tfDatabase;
    TextField tfUsername;
    TextField tfPassword;
    TextField tfSQL;
    Button btn;
    TextArea ta;

    Button okBtn;
    Label status;

    public SwingGUI()
    {
        setTitle("Database Application");
        setLayout(new FlowLayout());

        Label l1 = new Label("Driver");
        add(l1);
        tfDriver = new TextField(60);
        tfDriver.setText("org.sqlite.JDBC");
        add(tfDriver);

        Label l2 = new Label("Database URL");
        add(l2);
        tfDatabase = new TextField(60);
        tfDatabase.setText("jdbc:sqlite:C:/Users/Administrator/MySQLiteDB");
        add(tfDatabase);

        Label l3 = new Label("User name:");
        add(l3);
        tfUsername = new TextField(60);
        add(tfUsername);

        Label l4 = new Label("Password:");
        add(l4);
        tfPassword = new TextField(60);
        add(tfPassword);

        Label l5 = new Label("SQL statement:");
        add(l5);
        tfSQL = new TextField(60);
        tfSQL.setText("SELECT * FROM studenti");
        add(tfSQL);

        btn = new Button("Executa");
        add(btn);
        btn.addActionListener(this);

        ta = new TextArea(10, 60);
        add(ta);

        okBtn = new Button("OK");
        add(okBtn);
        okBtn.addActionListener(new ButtonClickListener());

        ta.addMouseListener(new MouseHandler());
        ta.addMouseMotionListener(new MouseHandler());

        status = new Label();
        add(status);

        addWindowListener(new MyWindowAdapter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ta.setText("");
        processData();
        try
        {
            Class.forName(Driver);
            Connection con = DriverManager.getConnection(DB, USER, PASS);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();

            int numCol = rsmd.getColumnCount();
            while (rs.next())
            {
                String record = "";
                for(int i=1;i<=numCol;i++)
                {
                    int dataType = rsmd.getColumnType(i);
                    record = record+getField(rs, dataType, i)+" ";
                }
                ta.append(record+"\n");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    class MyWindowAdapter extends WindowAdapter{

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    class MouseHandler implements MouseListener, MouseMotionListener
    {
        public MouseHandler() {
            super();
        }

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
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            status.setText("Mouse entered!");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            status.setText("Mouse exited!");
        }
    }

    class ButtonClickListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals("OK"))
                ta.append("\nOK button pressed!");
        }
    }

    private void processData()
    {
        Driver = tfDriver.getText();
        if(Driver.equals(""))
            Driver = "org.sqlite.JDBC";

        DB = tfDatabase.getText();
        if(DB.equals(""))
            DB = "jdbc:sqlite:C:/Users/Cristian/MySQLiteDB";

        USER = tfUsername.getText();

        PASS = tfPassword.getText();

        SQL = tfSQL.getText();
        if(SQL.equals(""))
            SQL = "SELECT * FROM studenti";
    }

    private String getField(ResultSet rs, int dataType, int col)
            throws SQLException
    {
        switch (dataType)
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

    public static void main(String[] args){
        SwingGUI frame = new SwingGUI();
        frame.setBounds(1,1,650,500);
        frame.setVisible(true);
    }
}
