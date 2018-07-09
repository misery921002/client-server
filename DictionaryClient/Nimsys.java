
/*comp90041
 * projectB
 * WEI Chao 
 * SID:748116
 * */
import java.util.Scanner;
import java.util.StringTokenizer;

public class Nimsys
{
	public static final int maxPlayerNum = 100;      //record 100 players
	public static int countPlayer = 0;              //count the number of players
	public  NimPlayer[] players = new NimPlayer[maxPlayerNum]; //initial the array
  
	public void run(){

		int i, flag, flag1, flag2, index1 = 0, index2 = 0;  //indicators
	    int initialStones, upperBound;     //store initial stone number and upper bound number
		String userName, familyName, givenName; //store user's information
		String command, userResponse;          //store user's command and response
		String player1, player2;               //store users' name
		Scanner keyboard = new Scanner(System.in);
	
		String userCommand = keyboard.nextLine();    //scanner
		
		//exit the system when user entered "exit"
		while (!userCommand.equals("exit"))
		{
			// StringTokenizer splits tokens by " " and ","
			StringTokenizer st = new StringTokenizer(userCommand," //,");
			
			command = st.nextElement().toString();
			//while user enter "addplayer"
			if (command.equals("addplayer"))
			{
				if (countPlayer < maxPlayerNum)
				{
					userName = st.nextElement().toString();
					familyName = st.nextElement().toString();
					givenName = st.nextElement().toString();
					
					flag = 0;
					
					// Loop to check the player list if the added player 
					// already exists.
					for(i = 0; i < countPlayer; i++)
					{
						if (players[i].getUserName().equals(userName))
						{
							System.out.println("The player already exists.");
							flag = 1;
						}
					}
					
					// To insert a new player 
					if (flag == 0)
					{
						players[countPlayer] = new NimPlayer
								(userName, familyName, givenName);
						countPlayer = countPlayer + 1;
					}
				}
				
				else
					System.out.println("Maximum number of players is 100");
			}
			//while user entered "removeplayer"
			else if (command.equals("removeplayer"))
			{
				if (st.hasMoreElements())
				{	
					userName = st.nextElement().toString();
					
					flag = 0;
					
					// deletes the specific player
					for(i = 0; i < countPlayer; i++)
					{
						if (players[i].getUserName().equals(userName))
						{
							// Overwrite the details of the player to be 
							// deleted with the details of the last player 
							// in the list
							players[i] = players[countPlayer - 1];
							countPlayer = countPlayer - 1;
							flag = 1;
							break;
						}
					}
					
					if (flag == 0)
						System.out.println("The player does not exist.");
				}
				
				// delete all players.
				else
				{
					System.out.println("Are you sure you want to remove "
							+ "all players? (y/n)");
					userResponse = keyboard.nextLine();
					if(userResponse.equals("y"))
					{
						countPlayer = 0;
					}					
				}
			}
			
			else if (command.equals("editplayer"))
			{
				userName = st.nextElement().toString();
				familyName = st.nextElement().toString();
				givenName = st.nextElement().toString();
				
				flag = 0;
				
				// Checks if the player exists and if so, updates the details.
				for(i = 0; i < countPlayer; i++)
				{
					if (players[i].getUserName().equals(userName))
					{
						players[i].setGivenName(givenName);
						players[i].setFamilyName(familyName);
						flag = 1;
						break;
					}
				}
				
				if (flag == 0)
					System.out.println("The player does not exist.");
			}
			
			else if (command.equals("resetstats"))
			{
				// To reset statistics of a particular player
				if (st.hasMoreElements())
				{	
					userName = st.nextElement().toString();
					
					flag = 0;
					
					// Searches for the specified player and resets his 
					// statistics.
					for(i = 0; i < countPlayer; i++)
					{
						if (players[i].getUserName().equals(userName))
						{
							players[i].resetGame();
							flag = 1;
							break;
						}
					}
					
					if (flag == 0)
						System.out.println("The player does not exist.");
				}
				
				// A username is not provided by the user. So the user is 
				// given the option to reset statistics of all players.
				else
				{
					System.out.println("Are you sure you want to reset all "
							+ "player statistics? (y/n)");
					userResponse = keyboard.nextLine();
					if(userResponse.equals("y"))
					{
						for(i = 0; i < countPlayer; i++)
						{
							players[i].resetGame();
						}
					}					
				}
			}
			//while user entered "displayplayer"
			else if (command.equals("displayplayer"))
			{
			
				if (st.hasMoreElements())
				{	
					userName = st.nextElement().toString();
					
					flag = 0;
					for(i = 0; i < countPlayer; i++)
					{
						if (players[i].getUserName().equals(userName))
						{
							players[i].printPlayer();
							flag = 1;
							break;
						}
					}
					if (flag == 0)
						System.out.println("The player does not exist.");
				}
				
				// If the user does not provide a username, then details of 
				// all players are displayed after sorting by username.
				else 
				{
					sortPlayers(players);
					for(i = 0; i < countPlayer; i++)
						players[i].printPlayer();
				}
			}
			
			else if (command.equals("rankings"))
			{
				int count = 0; 
				
				// The list of players is bubble sorted by username.
				sortPlayers(players);
				
				// The code bubble sorts the players by winning ratio. Also, 
				// since bubble sort is a stable sorting method, if two players 
				// have the same winning ratio, they remain sorted according 
				// to username.
				for(i = 0; i < countPlayer; i++)
				{
					for(int j = 0; j < countPlayer - 1; j++)
					{	
						double ratio1 = 0, ratio2 = 0;
						
						// Code to avoid a divide-by-zero error
						if (players[j].getNumOfGame() == 0)
							ratio1 = 0;
						
						else
							ratio1 = (players[j].getNumOfWon() * 
									1.0/players[j].getNumOfGame()) * 100;
						
						// Code to avoid a divide-by-zero error
						if (players[j+1].getNumOfGame() == 0)
							ratio2 = 0;
						
						else
							ratio2 = (players[j+1].getNumOfWon() * 
									1.0/players[j+1].getNumOfGame()) * 100;

						// Players j and j + 1 are swapped if ratio of j is 
						// less than ratio of j + 1
						if (ratio1 < ratio2)
						{
							NimPlayer temp = new NimPlayer();
							temp = players[j];
							players[j] = players[j+1];
							players[j+1] = temp;
						}
						
					}
				}
				
				// Loop to display ranked list of players
				for(i = 0; i < countPlayer; i++)
				{
					double ratio = 0;
					int ratioFormatted;
					
					
					// To avoid divide-by-zero error
					if (players[i].getNumOfGame() == 0)
						ratio = 0;
					
					else
						ratio = (players[i].getNumOfWon() * 
								1.0 /players[i].getNumOfGame()) * 100;
					
					// Rounding up to the nearest integer
					ratioFormatted = (int)(ratio + 0.5d);
					
					// Displaying ranking in the prescribed format
					
					System.out.printf("%-5s| %02d games | %s %s\n", 
							ratioFormatted + "%", 
							players[i].getNumOfGame(), 
							players[i].getGivenName(), 
							players[i].getFamilyName());
					
					
					// display top 10 players
					count = count + 1;
					if (count == 10)
						break;
				}
			}
			//while user entered "startgame"
			else if (command.equals("startgame"))
			{
				initialStones = Integer.parseInt(st.nextElement().toString());
				upperBound = Integer.parseInt(st.nextElement().toString());
				player1 = st.nextElement().toString();
				player2 = st.nextElement().toString();
				
				flag1 = 0;
				flag2 = 0;
				
				// check in two players are exsits
				for(i = 0; i < countPlayer; i++)
				{
					if(players[i].getUserName().equals(player1))
					{
						flag1 = 1;
						index1 = i;
					}
					if(players[i].getUserName().equals(player2))
					{
						flag2 = 1;
						index2 = i;
					}
				}
				
				if (flag1 == 0 || flag2 ==0)
					System.out.println("One of the players does not exist.");
				
				// start a game
				else
				{
					NimGame newGame = new NimGame(initialStones, upperBound, 
							players[index1], players[index2]);
					newGame.playGame(keyboard, players[index1], 
							players[index2]);	
				}
			}
			
			System.out.print("\n$");
			userCommand = keyboard.nextLine();
		}
		
		System.out.println();
		
		keyboard.close();    //close scanner 
		System.exit(0);      //exit the system
	}
	// sort the list of players by ascending order 
	public static void sortPlayers(NimPlayer[] players)
	{
		int i, j;
		
		// bubble sort 
		for(i = 0; i < countPlayer; i++)
			for(j = 0; j < countPlayer - 1; j++)
			{	
				if(players[j].getUserName().compareTo
						(players[j+1].getUserName()) > 0)
				{
					NimPlayer temp = new NimPlayer();
					temp = players[j];
					players[j] = players[j+1];
					players[j+1] = temp;
				}
			}
	}
	//main method
	public static void main(String args[]){
		System.out.println("Welcome to Nim");
		System.out.print("\n$");	
		Nimsys gameRun = new Nimsys();
		gameRun.run();
	}
}
