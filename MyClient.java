 

import java.net.*;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;


public class MyClient
{
    //static Socket for the Client
    static Socket user;

    public static void main(String[] args)
    {
        System.out.println("Hello banker! \n ");
        try
        {
            //Instantiate the Socket; use the PORT and IP from the Server.
            user = new Socket("localhost", 2377);
            int number = 0;
            float dep=0;//send deposit amount to server
            float wdraw=0;//send withdraw amount to server
            Scanner sc = new Scanner(System.in);//scanner object to read user input
            DataInputStream reader = new DataInputStream(user.getInputStream());
            DataOutputStream writer = new DataOutputStream(user.getOutputStream());
             
            while(number != 4)
            {

                System.out.println("\nPlease select the number of choice\n1)Veiw Account\n2)Deposit\n3)Withdraw\n4)Quit");
                 while (!sc.hasNextInt())//using loop to check if user input is correct
                 {
                     System.out.println("You entered the wrong input try again");
                     sc.next();
                    }

                number = sc.nextInt();//number equals user choice
                 writer.writeInt(number);//sending user choice to server
                       
                        

                if(number == 1)//printing account total from server
                {

                    String message1 = reader.readUTF();
                    writer.writeUTF("total account balance sent to Client");
                    System.out.println(message1);

                }
                else if(number==2)//sending user input to server and reciving calculations
                {


                    System.out.println("Enter deposit amount here: ");
                    while (!sc.hasNextFloat())//using loop to check if user input is correct
                    {
                     System.out.println("You entered the wrong input try again");
                     sc.next();
                    }
                    dep = sc.nextFloat();
                    writer.writeFloat(dep);//sending info to server
                    String message2 = reader.readUTF();
                    writer.writeUTF("deposit amount sent to Client");
                    System.out.println(message2);


                }
                else if(number==3)//sending user input to server and reciving calculations
                {


                    System.out.println("Enter withdraw amount here: ");
                    while (!sc.hasNextFloat())//using loop to check if user input is correct 
                    {
                     System.out.println("You entered the wrong input try again");
                     sc.next();
                    }
                    wdraw = sc.nextFloat();
                    writer.writeFloat(wdraw);//sending info to server
                    String message3 = reader.readUTF();
                    writer.writeUTF("withdraw amount sent to Client");
                    System.out.println(message3);


                }
                else if (number == 4)//if choice is to quit then exit program with final message
                {
                    System.out.println("\nThank you have a nice day!");
                    break;
                }
                else
                {
                    System.out.println("You entered the wrong input try again");

                }
            }
            //closing sockets
            user.close();
            reader.close();
            writer.close();
        }catch (IOException e)
        {
            System.err.println("Server Error.");
        }


    }

}



