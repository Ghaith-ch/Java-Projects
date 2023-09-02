import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View extends JFrame  {

	private JPanel pTop,pCenter,pBottom;
	private ImageIcon icon;
	private JLabel labelX,labelO,playerXscore,playerOscore,turn;
	private JButton[] gameButtons; 	
	public JButton reset;
	private Controller controller;
	
	public View() {		
		controller=new Controller(this);
		gameButtons = new JButton[9];				
		pTop=new JPanel();
		pCenter=new JPanel();
		pBottom=new JPanel();		

		labelX= new JLabel("<html><strong>Player<font color='blue'>X</font>Score:</strong></html>");
		labelX.setFont(new Font("Calibri", Font.BOLD, 20));
		playerXscore=new JLabel();
		playerXscore.setFont(new Font("Calibri", Font.BOLD, 20));
		playerXscore.setForeground(Color.BLUE);

       
		labelO= new JLabel("<html><strong>Player<font color='red'>O</font>Score:</strong></html>");
		labelO.setFont(new Font("Calibri", Font.BOLD, 20));
		playerOscore=new JLabel();
		playerOscore.setFont(new Font("Calibri", Font.BOLD, 20));
		playerOscore.setForeground(Color.RED);

		pTop.setBorder(BorderFactory.createEmptyBorder(25,10,25,10));		
		pTop.setLayout(new GridLayout(1,1));
		pTop.add (Box.createHorizontalGlue());
		pTop.add(labelX);		
		pTop.add(playerXscore);
		pTop.add(labelO);
		pTop.add(playerOscore);
		
		for(int i=0;i<9;i++) {
			gameButtons[i] = new JButton("");			
			gameButtons[i].setFont(new Font("Calibri", Font.BOLD, 70));
			gameButtons[i].addActionListener(controller);
			gameButtons[i].setActionCommand("button"+Integer.toString(i));
			pCenter.add(gameButtons[i]);		
		}
		pCenter.setLayout(new GridLayout(3,3,3,3));
		

		try {
			icon=new ImageIcon(ImageIO.read(new File("tttlogo.png")).getScaledInstance(100,100,Image.SCALE_SMOOTH));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Image not found");
			System.exit(1);
		}

		turn=new JLabel("");
		turn.setFont(new Font("Calibri", Font.BOLD, 40));
		turn.setForeground(Color.GRAY);

		reset=new JButton("Reset the board");
		reset.setFont(new Font("Calibri", Font.BOLD, 20));
		reset.setBackground(Color.GRAY);
		reset.setForeground(Color.BLACK);
		
		reset.addActionListener(controller);
		reset.setActionCommand("reset");

		pBottom.add(new JLabel(icon));
		pBottom.add(turn);
		pBottom.add(reset);
		pBottom.setLayout(new GridLayout(1,3));
		pBottom.setBorder(BorderFactory.createEmptyBorder(25,10,25,10));		


		this.setLayout(new BorderLayout());
		this.add(pTop, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		this.add(pBottom, BorderLayout.SOUTH);

		this.setSize(800,800);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initial();
	}
	
	public void initial() {
		if(controller.firstThrow()) {
			turn.setText("X turn");
		}
		else {
			turn.setText("O turn");
		}
	}
	
	public boolean checkEmpty(int number) {
		if (gameButtons[number].getText() =="") {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void uppdateViewBoard(String string, int number) {
		if(string=="X") {
			gameButtons[number].setText("X");
			gameButtons[number].setForeground(Color.BLUE);
			turn.setText("oTurn");			
		}			
		else {
			gameButtons[number].setText("O");
			gameButtons[number].setForeground(Color.RED);	
			turn.setText("xTurn");					
		}			
	}

	public void uppdateResult(int theWinner, int result) {
		
		if(theWinner==1) {		
			playerXscore.setText(Integer.toString(result));
		    JOptionPane.showMessageDialog(this,"X won The game.");  
		    resetGame();		
		}
		
		else if(theWinner==2) {
			playerOscore.setText(Integer.toString(result));			
		    JOptionPane.showMessageDialog(this,"O won The game.");  
			resetGame();
		}
		else if (theWinner==3) {
			int counter=0;
			for(int i=0;i<gameButtons.length;i++) {
				if(gameButtons[i].getText() =="") {
					counter++;
				}
			}
			if(counter==0) {
			    JOptionPane.showMessageDialog(this,"no winner");
			    resetGame();
			}
		}	
	}
	
	public void resetGame() {
		for(int i=0;i<gameButtons.length;i++) {
			gameButtons[i].setText("");		
		}
		controller.toResetModelBoard();
	}

	public static void main(String[] args) {
		new View();
	}
}
