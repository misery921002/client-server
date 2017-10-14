
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Administrator
 */
public class DictionaryServer {

    //serverSocket port
    public static int serverPort;
    //data storage file name
    public static String filename;
    //Dictionary in momery
    public static Hashtable Dictionary=new Hashtable();
    //serverSocket exit flag
    public static boolean exit=false;
    //server socket
    public static ServerSocket serverSocket;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            new MainForm(args[0],args[1]).setVisible(true);
        }catch(Exception e){
            System.out.println("Please enter the correct parameters.");
            e.printStackTrace();
        }
    }
    
    //write data to text file
    public static void writeData()
    {
        try {
            FileWriter fileWriter=new FileWriter(DictionaryServer.filename,false);
            String str="";
            Enumeration e = DictionaryServer.Dictionary.keys(); 
            String key="";
            while (e.hasMoreElements()){
               key=e.nextElement().toString();
               str+=key+","+DictionaryServer.Dictionary.get(key)+"\r\n";
            }
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception ex) {
            System.out.println("Write data error!");
            System.out.println("Please pay attention to check your file read and write permissions!");
            ex.printStackTrace();
        }
    }
    //read data from text file
    public static void readData()
    {
        try {
            FileReader fr=new FileReader(DictionaryServer.filename);
            Scanner in = new Scanner(fr);
            String strWorkAllFields="";
            while(in.hasNext())
            {
                strWorkAllFields = in.nextLine();
                StringTokenizer tokens = new StringTokenizer(strWorkAllFields,",");
                DictionaryServer.Dictionary.put(tokens.nextToken(), tokens.nextToken());
            }// completed reading of file
            fr.close();
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.print("Write data error!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.print("Write data error!");
            ex.printStackTrace();
        }
    }
    public static String getPath(){  
        java.net.URL url = DictionaryServer.class.getProtectionDomain().getCodeSource()
                .getLocation();
        String filePath = null;
        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        java.io.File file = new java.io.File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;  
    }  
    
}
