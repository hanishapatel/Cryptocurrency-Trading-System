import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.io.FileWriter;

public class PatelP2Server{

    public static void main(String[] args){
        try{

          
            System.out.println("Please go and start to connect the client server\n");

            //create objects
            RemoteUtilImpl c1 = new RemoteUtilImpl("Bitcoin", "BTC", "Bitcoin, often described as a cryptocurrency, a virtual currency or a digital currency - is a type of money that is completely virtual. It's like an online version of cash.", 897.13, 29.4, 47149.81);
            RemoteUtilImpl c2 = new RemoteUtilImpl("Ethereum", "ETH", "Ethereum is a platform powered by blockchain technology that is best known for its native cryptocurrency, called ether, or ETH, or simply ethereum.", 410.2, 16, 3393.48);
            RemoteUtilImpl c3 = new RemoteUtilImpl("Litecoin", "LTC", "Litecoin is a peer-to-peer (P2P) virtual currency, which means it is not governed by a central authority. Litecoin's network offers instant, near-zero cost payments that can be conducted by individuals or institutions across the globe.", 9.2, 938.1, 129.79);
            RemoteUtilImpl c4 = new RemoteUtilImpl("Maidsafecoin", "MAID", "MaidSafecoin is the temporary cryptocurrency coin used for the alpha and beta versions of the SAFE network, which stands for Secure Access For Everyone.", 192.7, 36771.58, 0.4323);
            RemoteUtilImpl c5 = new RemoteUtilImpl("Blackcoin", "BLK", "Blackcoin is a peer-to-peer cryptocurrency. What this means is that the cryptocurrency allows the exchange of data and transaction between parties without the need for a central authority.", 2.3, 4924.13, 0.0341);

            //export the objects using stub which is used to communicate with the client
            RemoteUtil stub1 = (RemoteUtil) UnicastRemoteObject.exportObject(c1,0);
            RemoteUtil stub2 = (RemoteUtil) UnicastRemoteObject.exportObject(c2,0);
            RemoteUtil stub3 = (RemoteUtil) UnicastRemoteObject.exportObject(c3,0);
            RemoteUtil stub4 = (RemoteUtil) UnicastRemoteObject.exportObject(c4,0);
            RemoteUtil stub5 = (RemoteUtil) UnicastRemoteObject.exportObject(c5,0);

            //register the exported class in RMI registry with some name
            //client will use that name to get the reference of those exported object
            //get the registry to register the object
            Registry registry = LocateRegistry.createRegistry(1486);

            registry.bind("b", stub1);
            registry.bind("e", stub2);
            registry.bind("l", stub3);
            registry.bind("m", stub4);
            registry.bind("c", stub5);

        }
        catch(Exception e){
            System.out.println(e);
        }
       
        try{  
            ServerSocket ss=new ServerSocket(14866);  
            Socket s=ss.accept();//establishes connection   
            DataInputStream din = new DataInputStream(s.getInputStream());  
            DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
            String  str1 = (String)din.readUTF();  
            String str2 = (String)din.readUTF();
            System.out.println("Username: "+ str1);  
            System.out.println("Password: "+ str2); 

            File Userfile = new File("C:/Users/hanis/Desktop/AOS_2/src/userinfo.txt");
            Scanner Scanuserfile = new Scanner(Userfile);
            
            while(Scanuserfile.hasNextLine()){
                String line = Scanuserfile.nextLine();
                String username = str1;
                String password = str2;
                System.out.println("Client Username -  "+ username + "  " + "Client password - " + password);
               
                while (true){
                    String[] arrayofstring = line.split(" ");
                    
                    if(username.equals(arrayofstring[0]))
                    {
                        if(password.equals(arrayofstring[1]))
                        {
                            
                            System.out.println("Now, Client sucessfully login with all credentials validates");
                            dout.writeUTF("Now, Client sucessfully login with username: " + str1 + " and password: " + str2);
                            File clientfile = new File("C:/Users/hanis/Desktop/AOS_2/src/client.txt");
                            Scanner Scanclientfile = new Scanner(clientfile);
                            while(true){
                                String line2 = Scanclientfile.nextLine();
                                while(true){
                                    
                                    String[] arr = line2.split(" ");
                                    if(str1.equals(arr[0])){
                                        dout.writeUTF("Now, Client allow to buy/sell coins and do transaction");
                                        
                                        String product = String.join(" ", arr);
                                        dout.writeUTF(product);
                                        String details_old = (String)din.readUTF();
                                        String filePath = "C:/Users/hanis/Desktop/AOS_2/src/client.txt";
                                        Scanner sc = new Scanner(new File(filePath));
                                        StringBuffer buffer = new StringBuffer();
                                        //Reading lines of the file and appending them to StringBuffer
                                        while (sc.hasNextLine()) {
                                            buffer.append(sc.nextLine()+System.lineSeparator());
                                        }
                                        String fileContents = buffer.toString();
                                        sc.close();
                                        String oldLine = product;
                                        String newLine = details_old;
                                        fileContents = fileContents.replaceAll(oldLine, newLine);
                                        FileWriter writer = new FileWriter(filePath);
                                        writer.append(fileContents);
                                        writer.flush();
                                    }
                                    line2 = Scanclientfile.nextLine();
                                    if(line2.equals(" "))
                                    {
                                        break;
                                    }
                                }
                            } 
                        }                            
                    }
                    
                    line = Scanuserfile.nextLine();
                    if(line.equals(" "))
                    {
                       
                        break;
                    }
                }     
            } 
            ss.close(); 
    
        }   
        catch(Exception e){

        } 
   
    }
}
