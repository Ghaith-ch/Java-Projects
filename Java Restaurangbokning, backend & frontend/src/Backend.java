import java.awt.Color;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class Backend extends WindowAdapter implements ActionListener{

	private Frontend f;
	public Backend(Frontend frontend) {
		f=frontend;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "add") {
			String name = JOptionPane.showInputDialog("Please enter the name!");
			String namber = JOptionPane.showInputDialog("Please enter the number of people");
			
			try {
				int number=Integer.parseInt(namber);
				f.addToLista(name,number);
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(f,"Please enter a number!");
			}
		}
		else if (e.getActionCommand() == "delet") {
			f.deletFromLista();
		}
		
		else {	
			int number= Integer.parseInt(e.getActionCommand());
			System.out.print(number);

			if(f.checkColor(number)) 
				f.updateView(number,new Color(30,172,139));
			else 
				f.updateView(number,new Color(112,41,99));					
		}
	}
}
