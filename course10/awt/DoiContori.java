package ro.ase.course10.awt;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

class NumarPartajat
{
    private int n=0;
    private Graphics g;
    private int x=0;
    private int contor;

    public NumarPartajat(Graphics g,int contor)
    {
        this.g=g;
        this.contor=contor;
    }

    public synchronized void increment()
    {
        n=n+1;
        g.drawString(n+", ",x*20,30+15*contor);
        x++;
    }
}

class Contor1 extends Thread
{
    private NumarPartajat nr;

    public Contor1(NumarPartajat nr)
    {
        this.nr=nr;
    }
    public void run()
    {
        for(int i=1;i<=10;i++)
            nr.increment();
    }
}

class Contor2 extends Thread
{
    private NumarPartajat nr;

    public Contor2(NumarPartajat nr)
    {
        this.nr=nr;
    }
    public void run()
    {
        for(int i=1;i<=10;i++)
            nr.increment();
    }
}

public class DoiContori extends Frame implements ActionListener {
    private Button start;
    private int contor=0;

    public DoiContori() {
        setTitle("DoiContori Application");
        setLayout(new FlowLayout());

        start=new Button("Start");
        add(start);
        start.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource()==start)
        {
            contor++;
            Graphics g=getGraphics();
            NumarPartajat numar=new NumarPartajat(g,contor);
            Contor1 contor1=new Contor1(numar);
            Contor2 contor2=new Contor2(numar);
            contor1.start();
            contor2.start();
            try {
                Thread.sleep(5000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[])
    {
        DoiContori doiContori = new DoiContori();
        doiContori.setBounds(1,1,500,500);
        doiContori.setVisible(true);
    }
}


