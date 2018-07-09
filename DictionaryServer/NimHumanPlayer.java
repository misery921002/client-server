
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */

import java.util.Scanner;


public class NimHumanPlayer extends NimPlayer {
	//constructor
	public NimHumanPlayer(String userName, String familyName, String givenName){
		
		super(userName,familyName,givenName);
		}
	public int makeMove(Scanner keyboard){

		int numStonesToRemove = 0;
		numStonesToRemove = keyboard.nextInt();

		System.out.println();
		keyboard.nextLine();

		return numStonesToRemove;
		}
	//invalid move exveption
public void throwException(){
	try{

			InvalidMoveException e = new InvalidMoveException();
			throw e;
			}
	
	catch(InvalidMoveException e){

			e.displayMessage();
			}
	}

	}
