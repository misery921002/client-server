
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class ServerThread extends Thread{
    public ServerThread () {
    }
    @Override
    public void run(){
        try{
            DictionaryServer.serverSocket=new ServerSocket(DictionaryServer.serverPort);
            System.out.println("Server is listening on port "+DictionaryServer.serverPort);
            //Listen socket on port
            while(!DictionaryServer.exit) {
                
                Socket s=DictionaryServer.serverSocket.accept();
                //Creat client socket connect
                ClientThread t = new ClientThread(s);
                //start thread
                t.start();
            }
            
        } catch(IOException e) {
            System.out.println("Listen :"+e.getMessage());
        }
    }
  }



