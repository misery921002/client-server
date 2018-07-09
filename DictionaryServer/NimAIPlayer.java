
/*
 * project C
 * student number:748116
 * student name:WEI Chao
 */


import java.util.Random;

public class NimAIPlayer extends NimPlayer implements Testable{
   // non-argument constructor
	public NimAIPlayer(){
		
	}
   //constructor
	public NimAIPlayer(String userName, String familyName, String givenName){

		super(userName,familyName,givenName);
		}
	//the number of stones that AI would remove
	public int makeMove(int initialStones, int upperBound)
	{
		int numStonesToRemove = 0;
		int k;
		
		for(k = 0; (k * (upperBound + 1) + 1) <= initialStones; k++);
		k--;

		if ((k * (upperBound + 1) + 1) == initialStones){

			Random random = new Random(); 
			numStonesToRemove = random.nextInt(upperBound) + 1; 
			}
		
		else{
			numStonesToRemove = initialStones - (k * (upperBound + 1) + 1);
			}
	return numStonesToRemove;
	}

	public String advancedMove(boolean[] available, String lastMove){

        String str = "";
        return str;
        }
}
