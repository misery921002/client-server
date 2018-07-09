
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */

public class InvalidCommandException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5318073238746087018L;
	private String invalidCommand;
	public InvalidCommandException(String invalidCommand){

		this.invalidCommand = invalidCommand;
}
	
	public void getMsg(){
		System.out.println("'" + invalidCommand + "'" + 

				" is not a valid command.");
	}
}
