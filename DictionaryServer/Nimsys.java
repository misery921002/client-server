
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */

import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class Nimsys{

	public static final int MAX_PLAYERS = 100;  //record 100 players
	public static  NimPlayer[] players = new NimPlayer[MAX_PLAYERS]; //count the number of players
	public static int playerCount = 0; //initial the array
	// sort function to sort the players by their username
	public static void sortPlayers(NimPlayer[] players, int count)
	{
		int i, j;
    //buble sort
		for(i = 0; i < count; i++)
			for(j = 0; j < count - 1; j++){	
				if(players[j].getUserName().compareTo(players[j+1].getUserName()) > 0){
					NimPlayer temp = new NimPlayer();
					temp = players[j];
					players[j] = players[j+1];
					players[j+1] = temp;
					}
				}
		}
//read the player data from a file
	public static void readFromFile(File file){

		FileInputStream fIn;
		ObjectInputStream ipStream;

		try{

			fIn = new FileInputStream(file);
			ipStream = new ObjectInputStream(fIn);
			players = (NimPlayer[])ipStream.readObject();
			ipStream.close();
		}

		catch(Exception e){
			}
		}
// writes the player data to the file
	public static void writeToFile(File file)
	{
		FileOutputStream fOut;
		ObjectOutputStream opStream;
		try{

			fOut = new FileOutputStream(file);
			opStream = new ObjectOutputStream(fOut); 
			opStream.writeObject(players);
			opStream.close();
			}
		catch(Exception e){
		}
	 }
	//returns the number of players 
	public static int getPlayerCount()
	{
		int i;

		for(i = 0; i < players.length; i++){
			if(players[i] == null)
				break;
			}
		return i;
		}
//handle the interaction
	public void run(){

		int i, flag, flag1, flag2, index1 = 0, index2 = 0;  //indicators
	    int initialStones, upperBound;     //store initial stone number and upper bound number
		String userName, familyName, givenName; //store user's information
		String command, userResponse;          //store user's command and response
		String player1, player2;               //store users' name
		
		File file = new File("players.dat");
		if(!file.exists()){
			try	{
				file.createNewFile();
				}
			catch(IOException e){

				System.out.println("File cannot be created");
				}
			}

		readFromFile(file); // Player details read from file

		Scanner keyboard = new Scanner(System.in);
		
	

		String userCommand = keyboard.nextLine();
		//exit the system when user entered "exit"
		while (!userCommand.equals("exit")) 
		{
			StringTokenizer st = new StringTokenizer(userCommand," //,");
			command = st.nextElement().toString();
			
			try{
				//while user enter "addplayer" or "addaiplayer"
				if (command.equals("addplayer") || command.equals("addaiplayer")){

					playerCount = getPlayerCount();

					if(playerCount < MAX_PLAYERS){
						if(st.countTokens() < 3)

							throw new InvalidNumOfArgumentException();

						userName = st.nextElement().toString();
						familyName = st.nextElement().toString();
						givenName = st.nextElement().toString();

						flag = 0;
						//check the player list if the added player is already exists.
						for(i = 0; i < playerCount; i++){

							if (players[i].getUserName().equals(userName)){

								System.out.println("The player already exists.");
								flag = 1;
								break;
								}
							}
						//the player does not exists
						if (flag == 0){
							if(command.equals("addplayer")){

								NimHumanPlayer humanPlayer = new NimHumanPlayer(userName, familyName, givenName);
								players[i] = humanPlayer;}
							else{

								NimAIPlayer aiPlayer = new NimAIPlayer(userName, familyName, givenName);
								players[i] = aiPlayer;}
							}
						}
					else
						System.out.println("Maximum number of players is 100");
				}
				//while user entered "removeplayer"
				else if (command.equals("removeplayer")){
					//check the remove activity
					if (st.hasMoreElements()){	

						userName = st.nextElement().toString();
						flag = 0;
						playerCount = getPlayerCount();
						
						for(i = 0; i < playerCount; i++){
							if (players[i].getUserName().equals(userName)){
								
								players[i] = players[playerCount - 1];
								players[playerCount - 1] = null;
								flag = 1;
								break;
								}
							}

						if (flag == 0)
							System.out.println("The player does not exist.");
						}

					else{
						//delete all the players

						System.out.println("Are you sure you want to remove "
							+ "all players? (y/n)");

						userResponse = keyboard.nextLine();
						if(userResponse.equals("y"))
						{

							playerCount = getPlayerCount();
							for(i = 0; i < playerCount; i++)
								players[i] = null;
						}					
					}
				}
				//while user entered "editplayer"
				else if (command.equals("editplayer")){
					//number if argument exception
					if(st.countTokens() < 3)

						throw new InvalidNumOfArgumentException();

					userName = st.nextElement().toString();
					familyName = st.nextElement().toString();
					givenName = st.nextElement().toString();

					flag = 0;
					playerCount = getPlayerCount();

					//update the player's details
					for(i = 0; i < playerCount; i++){

						if (players[i].getUserName().equals(userName)){

							players[i].setGivenName(givenName);
							players[i].setFamilyName(familyName);
							flag = 1;
							break;
							}
					}
					if (flag == 0)
						System.out.println("The player does not exist.");
				}

				//while user entered "resetstats"
				else if (command.equals("resetstats")){
					
					//reset the statistics for a particular player
					if (st.hasMoreElements()){	

						userName = st.nextElement().toString();
						flag = 0;
						playerCount = getPlayerCount();
						
						for(i = 0; i < playerCount; i++){

							if(players[i].getUserName().equals(userName)){

								players[i].resetGame();
								flag = 1;
								break;}
							}
						if(flag == 0)
							System.out.println("The player does not exist.");
						}
					else{
						//reset all players' statistics
						System.out.println("Are you sure you want to reset all "
							+ "player statistics? (y/n)");

						userResponse = keyboard.nextLine();
						if(userResponse.equals("y")){
							
							playerCount = getPlayerCount();
							for(i = 0; i < playerCount; i++){
								players[i].resetGame();
							}
						}					
					}
				}
				//while user entered "displayplayer"
				else if (command.equals("displayplayer")){
					//display the statistic fof particular player
					if (st.hasMoreElements()){	

						userName = st.nextElement().toString();
						flag = 0;
						playerCount = getPlayerCount();

						for(i = 0; i < playerCount; i++){
							if (players[i].getUserName().equals(userName)){
								System.out.println(players[i]);
								flag = 1;
								break;
							}
						}
						if (flag == 0)
							System.out.println("The player does not exist.");
					}

					else{

						//display all players
						playerCount = getPlayerCount();
						sortPlayers(players, playerCount); 	

						for(i = 0; i < playerCount; i++){

							System.out.println(players[i]);
						}
					}
				}
				//while user entered "rankings"
				else if (command.equals("rankings")){

					int count = 0; 
					playerCount = getPlayerCount();

					sortPlayers(players, playerCount);

					for(i = 0; i < playerCount; i++){

						for(int j = 0; j < playerCount - 1; j++){	

							double ratio1 = 0, ratio2 = 0;

							try{
								ratio1 = (players[j].getNumOfWon() * 
									1.0/players[j].getNumOfGame()) * 100;
								}
							catch(ArithmeticException e){
								ratio1 = 0;
								}

							try{

								ratio2 = (players[j+1].getNumOfWon() * 
									1.0/players[j+1].getNumOfGame()) * 100;
							}
							catch(ArithmeticException e){
								ratio2 = 0;
								}
						//sort player by won rate
							if (ratio1 < ratio2){

								NimPlayer temp = new NimPlayer();
								temp = players[j];
								players[j] = players[j+1];
								players[j+1] = temp;
							}
						}
					}

					for(i = 0; i < playerCount; i++){

						double ratio = 0;
						int ratioFormatted;
						try{
							ratio = (players[i].getNumOfWon() * 
								1.0 /players[i].getNumOfGame()) * 100;
							}
						catch(ArithmeticException e){
							ratio = 0;
							}

						ratioFormatted = (int)(ratio + 0.5d);	

						System.out.printf("%-5s| %02d games | %s %s\n", 
							ratioFormatted + "%", 

							players[i].getNumOfGame(), 
							players[i].getGivenName(), 
							players[i].getFamilyName());

						count = count + 1;
						if (count == 10)
							break;
					}
				}

				else if (command.equals("startgame")){

					upperBound = 0;
					//while player entered "startgame"
					if (command.equals("startgame")){	
						//number of argument exception
						if(st.countTokens() < 4)
							throw new InvalidNumOfArgumentException();
						else{

							initialStones = Integer.parseInt
									(st.nextElement().toString());

							upperBound = Integer.parseInt
									(st.nextElement().toString());
							player1 = st.nextElement().toString();
							player2 = st.nextElement().toString();
						}
					}

					else
					{
						if(st.countTokens() < 3)
							throw new InvalidNumOfArgumentException();
						else{

							initialStones = Integer.parseInt
									(st.nextElement().toString());
							player1 = st.nextElement().toString();
							player2 = st.nextElement().toString();
							}
						}

					flag1 = 0;
					flag2 = 0;

					playerCount = getPlayerCount();
					//check the player if exists
					for(i = 0; i < playerCount; i++){

						if(players[i].getUserName().equals(player1)){

							flag1 = 1;
							index1 = i;
							}

						if(players[i].getUserName().equals(player2)){

							flag2 = 1;
							index2 = i;
							}
						}

					if (flag1 == 0 || flag2 ==0)

						System.out.println("One of the players does not exist.");

					else// both players are exists so start a new game
					{
						if (command.equals("startgame"))
						{

							NimGame newGame = new NimGame(initialStones, 
								upperBound, players[index1], players[index2]);
							newGame.playGame(keyboard, players[index1], 

								players[index2]);
						}
					}
				}
				else{
                   // imvalid command exception
					InvalidCommandException e = new InvalidCommandException(command);
					throw e;
				}
			}

			catch(InvalidCommandException e){

				e.getMsg();
				}
			catch(InvalidNumOfArgumentException e){

				e.getMsg();
				}

			finally{

				System.out.print("\n$");
				userCommand = keyboard.nextLine();
			}
		}

		System.out.println();
		//writhe the details to the file
		writeToFile(file); 
		
		keyboard.close(); 
		System.exit(0);
	}
	public static void main(String args[]){
		System.out.println("Welcome to Nim");
		System.out.print("\n$");
		Nimsys gameRun = new Nimsys();
		gameRun.run();
	}
}
