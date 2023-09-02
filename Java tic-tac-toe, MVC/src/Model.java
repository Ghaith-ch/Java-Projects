public class Model {

	private String[]gameField;
	private boolean turn;
	private int xCount,oCount;
	
	public Model() {
		gameField=new String[9];
		xCount=0;
		oCount=0;
	}
	
	public void xTurn() {
		turn=true;
	}
	
	public void oTurn() {
		turn=false;
	}
	
	public boolean getTurn() {
		return turn;
	}
	
	public int getxCount() {
		return xCount;
	}
	
	public int getoCount() {
		return oCount;
	}

	public void drag(String value,int postion) {
		gameField[postion]=value;		
	}
	
	public int checkWinner() {
		
		if(checkX()==true) {			
			xCount++;
			return 1;
		}
		else if (checkO()==true) {
			oCount++;
			return 2;
		}		
		else {
			return 3;
		}
	}

	public boolean checkX() {   		//check X win conditions
		if((gameField[0]=="X") && (gameField[1]=="X") && (gameField[2]=="X")) {return true;}
		
		if((gameField[3]=="X") && (gameField[4]=="X") && (gameField[5]=="X")) {return true;}
		
		if((gameField[6]=="X") && (gameField[7]=="X") && (gameField[8]=="X")) {return true;}
		
		if((gameField[0]=="X") && (gameField[3]=="X") && (gameField[6]=="X")) {return true;}
		
		if((gameField[1]=="X") && (gameField[4]=="X") && (gameField[7]=="X")) {return true;}
		
		if((gameField[2]=="X") && (gameField[5]=="X") && (gameField[8]=="X")) {return true;}
		
		if((gameField[0]=="X") && (gameField[4]=="X") && (gameField[8]=="X")) {return true;}
		
		if((gameField[2]=="X") && (gameField[4]=="X") && (gameField[6]=="X")) {return true;}
		
		return false;	
	}
	
	public boolean checkO() {   		//check O win conditions
		if((gameField[0]=="O") && (gameField[1]=="O") && (gameField[2]=="O")) {return true;}
		
		if((gameField[3]=="O") && (gameField[4]=="O") && (gameField[5]=="O")) {return true;}
		
		if((gameField[6]=="O") && (gameField[7]=="O") && (gameField[8]=="O")) {return true;}
		
		if((gameField[0]=="O") && (gameField[3]=="O") && (gameField[6]=="O")) {return true;}
		
		if((gameField[1]=="O") && (gameField[4]=="O") && (gameField[7]=="O")) {return true;}
		
		if((gameField[2]=="O") && (gameField[5]=="O") && (gameField[8]=="O")) {return true;}
		
		if((gameField[0]=="O") && (gameField[4]=="O") && (gameField[8]=="O")) {return true;}
		
		if((gameField[2]=="O") && (gameField[4]=="O") && (gameField[6]=="O")) {return true;}
		
		return false;	
	}

	public void resetModelBoard() {
		for(int i=0;i<gameField.length;i++) {
			gameField[i]="";		
		}
	}	
}


