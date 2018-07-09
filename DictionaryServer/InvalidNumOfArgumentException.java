
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */


public class InvalidNumOfArgumentException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8521601033298959885L;

	public InvalidNumOfArgumentException(){
	}

	public void getMsg(){
		System.out.println("Incorrect number of arguments supplied to command.");
	}

}
