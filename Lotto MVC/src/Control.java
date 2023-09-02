import java.awt.event.*; 

public class Control extends WindowAdapter implements ActionListener{ 
 private View l; 
 private Model ll;
 private FileHandler lll;
  
 public Control(View lin){ 
  l=lin;  
  ll=new Model(); 
  lll=new FileHandler();
  
 }
 public void actionPerformed(ActionEvent e) {
	 if (e.getActionCommand().equals("Ny Lottrad")) {
		 String rad=ll.slumpMedCollection();
		 l.skrivNyRad(rad); 
	 } else if (e.getActionCommand().equals("Öppna")) {
         System.out.println("Öppnar Fil...");
         System.out.println(lll.openFile());
         open(lll.openFile());
     } else if (e.getActionCommand().equals("Spara")) {
    	 System.out.println("Sparar Filen...");
    	 lll.saveFile(l.textArea());
     } else if (e.getActionCommand().equals("Rensa")) {
    	 System.out.println("Rensar Lottrader");
    	 l.rensa();
     } else if (e.getActionCommand().equals("Avsluta")) {
         System.exit(0);
     }
 }
 
 public void open(String text) {
     l.openFiles(text);
 }
 public void windowClosing (WindowEvent e){ 
  System.exit(0); 
 } 
} 