
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
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
public class ClientThread extends Thread{
    //input stream
    DataInputStream in;
    //output stream
    DataOutputStream out;
    //client socket
    Socket clientSocket;
    public ClientThread (Socket aClientSocket) {
      try {
        clientSocket = aClientSocket;
        in=new DataInputStream(clientSocket.getInputStream());
        out=new DataOutputStream(clientSocket.getOutputStream());
      } catch(IOException e){
          System.out.println("ServerThread:" +e.getMessage());}
    }
    @Override
    public void run(){
    try { 
        String message="";  
        String reply="";
        //get data from client socket
        while ((message=in.readUTF()) != null&&!DictionaryServer.exit) 
        {
            System.out.println(message);
            switch(message.split("#")[0])
            {
                //add word
                case "add":
                    //check whether the word in the hashtable
                    if(DictionaryServer.Dictionary.containsKey(message.split("#")[1]))
                    {
                        reply="error#The word already exists. add failed.";
                        //out.writeUTF(DictionaryServer.Dictionary.get(info).toString());
                    }else{
                        //push the word into hastable
                        DictionaryServer.Dictionary.put(message.split("#")[1], message.split("#")[2]);
                        //update text file
                        DictionaryServer.writeData();
                        reply="success#Word added successfully.";
                    }
                    //respones client socket
                    out.writeUTF(reply);
                    break;
                //delete word
                case "delete":
                    //check whether the word in the hashtable
                    if(DictionaryServer.Dictionary.containsKey(message.split("#")[1]))
                    {
                        //remove the word from hashtable
                        DictionaryServer.Dictionary.remove(message.split("#")[1]);
                        //update the text file
                        DictionaryServer.writeData();
                        reply="success#Word delete successfully.";
                    }else{
                        reply="error#The word does not exist.";
                    }
                    //respones client socket
                    out.writeUTF(reply);
                    break;
                //search word
                case "search":
                    //check whether the word in the hashtable
                    if(DictionaryServer.Dictionary.containsKey(message.split("#")[1]))
                    {
                        reply="meaning#"+DictionaryServer.Dictionary.get(message.split("#")[1]);
                    }else{
                        reply="error#The word does not exist.";
                    }
                    //respones client socket
                    out.writeUTF(reply);
                    break;
            } 
        }
       
    }
   catch(EOFException e) {
          System.out.println("EOF:"+e.getMessage());}
    catch(IOException e){
          System.out.println("IO:"+e.getMessage());}
    }
  }



