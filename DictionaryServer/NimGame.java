
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */

import java.util.Scanner;

public class NimGame {
	private int initialStones, upperBound;
	private String player1, player2;

//constructor
	public NimGame(int initStones, int upBound, NimPlayer play1,NimPlayer play2){

		initialStones = initStones;
		upperBound = upBound;

		player1 = play1.getGivenName();
		player2 = play2.getGivenName();

		System.out.println("\nInitial stone count: " + initialStones);

		System.out.println("Maximum stone removal: " + upperBound);

		System.out.println("Player 1: " + player1 + " " 

				+ play1.getFamilyName());

		System.out.println("Player 2: " + player2 + " " 

				+ play2.getFamilyName());

		System.out.println();

	}
	
//non-argument constructor
	public NimGame(){
		
	}
	
	//get initial numbero of stones
	public int getInitialStones(){

		return initialStones;
		}
	//game function
	public void playGame(Scanner keyboard, NimPlayer play1, NimPlayer play2){

		int counter = 0, removeNumber = 0;

		play1.updateNumOfGame();
		play2.updateNumOfGame();
   //start a game while the initial stone number is not 0
		while (initialStones > 0){

			do{	

				System.out.print(initialStones + " stones left: ");

				for (int i = 0; i < initialStones - 1; i++){

					System.out.print("* ");
					}
				System.out.print("*\n");

				if (initialStones < upperBound){
					upperBound = initialStones;
					}
				//print each player's turn
				if (counter % 2 == 0){

					System.out.print(player1 + "'s turn - remove how many?\n");
//identify the player's type
					if(play1 instanceof NimHumanPlayer) {

						removeNumber = ((NimHumanPlayer) play1).makeMove(keyboard);
						}

					else if(play1 instanceof NimAIPlayer){
						removeNumber = ((NimAIPlayer) play1).makeMove
								(initialStones, upperBound);
						System.out.println();
						}
					}

				else{	
					System.out.print(player2 + "'s turn - remove how many?\n");

					if(play2 instanceof NimHumanPlayer)	{

						removeNumber = ((NimHumanPlayer) play2).makeMove (keyboard);
						}
					else if(play2 instanceof NimAIPlayer){

						removeNumber = ((NimAIPlayer) play2).makeMove
								(initialStones, upperBound);
						System.out.println();
						}
					}
				//invalid move exception
				try{
					if (removeNumber > upperBound || removeNumber == 0){

						InvalidMoveException e = new InvalidMoveException
								(upperBound);
						throw e;
						}
					}
				catch(InvalidMoveException e){

					e.getMsg();
					}

			} while (removeNumber > upperBound || removeNumber == 0);

			initialStones = initialStones - removeNumber;
			counter++;
			}
		System.out.println("Game Over");
		//print the winner
		if(counter % 2 == 0){

			System.out.println(player1 + " " + play1.getFamilyName() + " wins!");
			play1.updateNumOfWon();}
		else
		{System.out.println(player2 + " " + play2.getFamilyName() 
					+ " wins!");
			play2.updateNumOfWon();	}
		}
}
