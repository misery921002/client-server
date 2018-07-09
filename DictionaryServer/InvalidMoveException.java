
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */

public class InvalidMoveException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3360042488733611895L;
	int upperBound;
	public InvalidMoveException(){
	}
	
    public InvalidMoveException(int upperBound){

		this.upperBound = upperBound;

	}
    
	public void getMsg(){

		System.out.println("Invalid move. You must remove between "

				+ "1 and " + upperBound + " stones.\n");
	}

	public void displayMessage(){

		System.out.println("\nInvalid move.\n");
		}
}

