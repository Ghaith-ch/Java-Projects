import java.awt.*; 
import javax.swing.*; 

public class View { 
	 private JFrame f; 
	 private JTextArea t; 
	 private JButton b; 
	 private Control ly; 
	 private JMenu menu;
	 private JMenuItem mi1, mi2, mi3, mi4;
	 private JMenuBar mb;
 
	 public View(){ 
		 //Slump Knapp
		 f=new JFrame("Slumpa Lottrad"); 
		 ly=new Control(this); 
		 f.addWindowListener(ly); 
		 t=new JTextArea(); 
		 b=new JButton("Ny Lottrad"); 
		 b.addActionListener(ly); 
		 f.add(t); 
		 f.getContentPane().add(b, BorderLayout.SOUTH);
	  
		 //Arkiv Menyn
		 mb=new JMenuBar();  
		 menu=new JMenu("Arkiv");
		 mi1=new JMenuItem("Öppna");
		 mi1.addActionListener(ly);
		 mi2=new JMenuItem("Spara");
		 mi2.addActionListener(ly);
		 mi3=new JMenuItem("Rensa");
		 mi3.addActionListener(ly);
		 mi4=new JMenuItem("Avsluta");
		 mi4.addActionListener(ly);
		 menu.add(mi1); 
		 menu.add(mi2); 
		 menu.add(mi3);
		 menu.addSeparator();
		 menu.add(mi4);
		 mb.add(menu);
		 f.setJMenuBar(mb); 
		 
		 //Forms Plats och Storlek
		 f.setLocation(200,100); 
	     f.setSize(300,300); 
	     f.setVisible(true);  
	 } 
  
	 public void skrivNyRad(String s){ 
		 t.append(s+"\n"); 
	 }
 
	 public void rensa() {
		 t.selectAll();
		 t.replaceSelection("");
	 }
	 
	 public void openFiles(String text) {
		 t.selectAll();
		 t.replaceSelection(text);
	 }
	 
	 public String textArea() {
		 return t.getText();
	 }
 
	 public static void main(String []args){ 
		 new View();  
	 } 
} 