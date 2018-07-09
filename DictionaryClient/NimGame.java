
/*comp90041
 * projectB
 * WEI Chao 
 * SID:748116
 * */
import java.util.Scanner;

public class NimGame {

	private int initialStoneNum; //initial number of stone
	private int upperBound;      //initial number of upper bound
	private String player1, player2;  // players' name
	
	
	// initial the instance variable
	public NimGame(int initStones, int upBound, NimPlayer play1, 
			NimPlayer play2)
	{
		initialStoneNum = initStones;
		upperBound = upBound;
		player1 = play1.getGivenName();
		player2 = play2.getGivenName();
		System.out.println("\nInitial stone count: " + initialStoneNum);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + player1 + " " 
				+ play1.getFamilyName());
		System.out.println("Player 2: " + player2 + " " 
				+ play2.getFamilyName());
		System.out.println();
	}
	
	public NimGame()
	{
		
	}
	
	// implements the single nim game
	public void playGame(Scanner keyboard, NimPlayer play1, NimPlayer play2)
	{
		int counter = 0;    //count the game turn
		int removeNumber;   //the number of removal of each turn
		
		// upadte the number of games that players played
		play1.updateNumOfGame();
		play2.updateNumOfGame();
		
		
		while (initialStoneNum > 0)   //printout remaining asterisks after each removal
 		{
			
			do
			{	
				System.out.print(initialStoneNum + " stones left: ");
				
				// print stones 
				for (int i = 0; i < initialStoneNum - 1; i++)
				{
					System.out.print("* ");
				}
				System.out.print("*\n");
				
				//printout each player's turn
				if (counter % 2 == 0)
					System.out.print(player1 + "'s turn - ");
				else
					System.out.print(player2 + "'s turn - ");
				
				System.out.print("remove how many?\n");
				removeNumber = keyboard.nextInt();

				System.out.println();
				keyboard.nextLine();
				
			    //warning the invalid move
				if (initialStoneNum < upperBound)
					upperBound = initialStoneNum;
				if (removeNumber > upperBound || removeNumber == 0)
				{
					System.out.println("Invalid move. You must remove between "
							+ "1 and " + upperBound + " stones.\n");
				}
			
			}  //if there is no asterisk left, the game is over
			while (removeNumber > upperBound || removeNumber == 0);
			
			initialStoneNum = initialStoneNum - removeNumber;
			
			
			counter++;
		}
		
		System.out.println("Game Over");
		
		if(counter % 2 == 0)
		{
			System.out.println(player1 + " " + play1.getFamilyName() //print the winner
					+ " wins!");
			play1.updateNumOfWon();                      //update number of win
		}
			
		else
		{
			System.out.println(player2 + " " + play2.getFamilyName() //print the winner
					+ " wins!");
			play2.updateNumOfWon();                      //update number of win
		}
	}
}