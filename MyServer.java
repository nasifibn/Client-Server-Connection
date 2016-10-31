

import java.net.*;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.DecimalFormat;


public class MyServer 
{
    //public static PORT and IP for the server.
     
       public static int server_Port = 2377;
       public static String server_IP = "localhost";
    
    // Socket and ServerSocket
              static Socket user;
              static ServerSocket MyServer;
              
      
    public static void main(String[] args)
    {
        System.out.println("One moment please...");//waiting for client
        int message=0;//int used to keep track of user choice
        DecimalFormat df = new DecimalFormat();//used to show only up to two decimal places
         
        try
        {
            //Instantiating the ServerSocket and accepting connection from client.
            MyServer = new ServerSocket( server_Port);
            user = MyServer.accept();
          DataInputStream reader = new DataInputStream(user.getInputStream());//get input from client
           DataOutputStream writer = new DataOutputStream(user.getOutputStream());//sent output to client
           float totalaccount=0;
           float deposit=0;
           float withdraw=0;
           
                     
           while(message!=4)//while client choice is not to quit
            {
                message = reader.readInt();
                if(message == 1)//for first choice 
                {
                    totalaccount= Float.parseFloat(df.format(totalaccount));//used to show only up to two decimal places
                    writer.writeUTF("Your account balance is $"+totalaccount);
                    String message1 = reader.readUTF();//message for server from client
                    System.out.println(message1);
                }
               else if (message == 2)//for second choice 
               {
                  
                   deposit = Float.parseFloat(df.format(reader.readFloat()));//used to show only up to two decimal places
                   totalaccount = totalaccount+deposit;//calculating total by adding deposit
                   writer.writeUTF("You deposited $"+ deposit);   
                   String message2 = reader.readUTF();//message for server from client
                    System.out.println(message2);
                }
                else if (message == 3)//for third choice 
               {
                  
                   withdraw = Float.parseFloat(df.format(reader.readFloat()));//used to show only up to two decimal places
                   if(withdraw>totalaccount)//making sure account can not go negative
                   {writer.writeUTF("Sorry your withdraw amount is too much");
                    }
                    else{
                   totalaccount= totalaccount-withdraw;//subtracting withdraw
                   writer.writeUTF("You withdrew $"+withdraw);
                   String message2 = reader.readUTF();//message for server from client
                    System.out.println(message2);
                }
                }
                    
            }
            //closing sockets
              user.close();
         MyServer.close();  
         reader.close();
           writer.close();
           
           System.out.println("server closed");
            
        }
        
        //Catch the most relevant exception
        catch (IOException e)
        {
            System.err.println("Server Error.");
        }  
      }
       
     }   




 