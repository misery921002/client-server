/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class DictionaryClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            new ClientForm(args[0],args[1]).setVisible(true);
        }catch(Exception e){
            System.out.println("Please enter the correct parameters.");
        }
    }
    
}
