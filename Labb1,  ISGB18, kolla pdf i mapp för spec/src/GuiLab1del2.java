
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	public class GuiLab1del2 extends WindowAdapter implements ActionListener
	{
	private JFrame f;
	private JTextArea t;
	private JMenuBar mb;
	private JMenu alt;
	private JMenuItem slumpa, sort, skriv, storstMinst,sok;
	private Lab1del2 l;
	private JPanel p;
	private JLabel lsok,lsvar;
	private JTextField tsok, tsvar;
	public GuiLab1del2() 
		{
			f=new JFrame("100 tal");
			l=new Lab1del2();
			f.addWindowListener(this);
			mb=new JMenuBar();
			alt= new JMenu("Alternativ");
			slumpa=new JMenuItem("Slumpa");
			sort=new JMenuItem("Sortera");
			skriv=new JMenuItem("Skriv ut");
			storstMinst=new JMenuItem("Största och minsta tal");
			sok=new JMenuItem("Sök");
			slumpa.addActionListener(this);
			sort.addActionListener(this);
			skriv.addActionListener(this);
			storstMinst.addActionListener(this);
			sok.addActionListener(this);
			alt.add(slumpa);
			alt.add(sort);
			alt.add(skriv);
			alt.add(storstMinst);
			alt.add(sok);
			mb.add(alt);
			f.setJMenuBar(mb);
			t=new JTextArea();
			f.add(t);
			p=new JPanel();
			lsok=new JLabel("Ange söktal");
			lsvar=new JLabel("Talet finns på plats");
			tsok=new JTextField(4);
			tsvar=new JTextField(12);
			p.add(lsok);
			p.add(tsok);
			p.add(lsvar);
			p.add(tsvar);
			f.add(p,BorderLayout.SOUTH);
			f.setSize(500, 500);
			f.setLocation(200, 200);
			f.setVisible(true);
		}
	
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getActionCommand().equals("Slumpa")) 
		{
			l.slumpa();
			t.append("Nu är vektorn slumpad\n");
		}
		
		if(ae.getActionCommand().equals("Sortera")) 
		{
			l.sortera();
			t.append("Nu är vektorn sorterad\n");
		}
		
		if(ae.getActionCommand().equals("Skriv ut"))
			t.append(l.skrivut());
		
		if(ae.getActionCommand().equals("Största och minsta tal")) 
			t.append(l.storsMinst());
		
		if(ae.getActionCommand().equals("Sök")) 
		{
			int tal=Integer.parseInt(tsok.getText());
			int svar=l.binsok(tal);
			if(svar==-1)
			tsvar.setText("Talet fanns ej");
			if(svar==-2)
			tsvar.setText("Sortera först");
			if(svar>0)
			tsvar.setText(""+svar);
		}
	}
	
	public void windowClosing(WindowEvent we) 
	{
		System.out.println("Hej då");
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		new GuiLab1del2();
	}
}