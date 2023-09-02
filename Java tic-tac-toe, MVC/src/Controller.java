import java.awt.event.*;

public class Controller extends WindowAdapter implements ActionListener {
	private View v;
	private Model m;
	public Controller(View view) {
		m=new Model();
		v=view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "reset") {
			v.resetGame();
		}
		else {			
		String lastChar = e.getActionCommand().substring(e.getActionCommand().length() - 1);
		int number= Integer.parseInt(lastChar);
			if(m.getTurn()==true) { //IF X TURN
				if(v.checkEmpty(number)==true) {
					v.uppdateViewBoard("X",number);
					m.drag("X",number);
					v.uppdateResult(m.checkWinner(),m.getxCount()); 
					m.oTurn();
				}
			}
			else {					//IF O TURN
				if(v.checkEmpty(number)==true) {
					v.uppdateViewBoard("O",number);
					m.drag("O",number);
					v.uppdateResult(m.checkWinner(),m.getoCount()); 
					m.xTurn();
				}
			}
		}
	}
	
	public boolean firstThrow() {
		
		int number = (int)Math.floor(Math.random()*(2));	
		if(number==1) {
			m.xTurn();
		}
		else{
			m.oTurn();
		}		
		return m.getTurn();
	}
	
	public void toResetModelBoard() {
		m.resetModelBoard();
	}

	/*
	public boolean whosFirst() {
		boolean turn= m.firstThrow();
		if (turn==true) {
			return true;
		}
		else {
			return false;
		}
	}
	*/
}
	




	


