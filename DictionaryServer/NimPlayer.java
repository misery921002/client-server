
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */

public class NimPlayer{
	private String givenName, familyName, userName;  //store user's information
	private int numGamesPlayed, numGamesWon;         //store number of game played and win

	// constructor 
	public NimPlayer(String userName, String familyName, String givenName){
		this.userName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
		numGamesPlayed = 0;
		numGamesWon = 0;
		}

	// Displays details of players
	public String toString(){
		return userName + "," + givenName + "," + familyName 
				+ "," + numGamesPlayed + " games," + numGamesWon + " wins";
		}

	// set given name
	public void setGivenName(String givenName){
		this.givenName = givenName;
		}
	
	// get given name
		public String getGivenName(){
			return givenName;
		}

	// set family name
	public void setFamilyName(String familyName){
		this.familyName = familyName;
		}
	
	// get family name
	public String getFamilyName(){
		return familyName;
		}
	
	// get user name
	public String getUserName(){
		return userName;
		}
	
	// set user name
	public void setUserName(String userName){
		this.userName = userName;
		}

	// Resets 
	public void resetGame(){
		numGamesPlayed = 0;
		numGamesWon = 0;
		}
	
	// get number of played game
	public int getNumOfGame(){
		return numGamesPlayed;
		}
	
	// set number of played game 
		public void setNumOfGame(int num){
			numGamesPlayed = num;
			}
		
	// get number if won game
	public int getNumOfWon(){
		return numGamesWon;
		}
	
	// set number of won game
	public void setNumOfWon(int num){
		numGamesWon = num;
		}
	
	// update number of played game 
	public void updateNumOfGame(){
		numGamesPlayed++;
		}
	
	// update number of won game
	public void updateNumOfWon(){
		numGamesWon++;
		}
	
	//non-argument constructor
	public NimPlayer(){
		
	}
}
