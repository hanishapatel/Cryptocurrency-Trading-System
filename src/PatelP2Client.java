import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class PatelP2Client{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        try{
            //locate the registry
           
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1486);

            //get the reference of exported object from rmi registry
            RemoteUtil bitcoin = (RemoteUtil) registry.lookup("b");
            RemoteUtil ethereum = (RemoteUtil) registry.lookup("e");
            RemoteUtil litecoin = (RemoteUtil) registry.lookup("l");
            RemoteUtil maidsafecoin = (RemoteUtil) registry.lookup("m");
            RemoteUtil blackcoin = (RemoteUtil) registry.lookup("c");

            System.out.println("Client is successfully connected to the server");

            //now invoke the me""thod of the referenced objects
            System.out.println("THE COINS IN THE SYSTEM ARE: \n\n");

            System.out.println("----------BITCOIN----------");
            System.out.println("The name of the coin is " + bitcoin.getName());
            System.out.println("The Abv name of the coin is " + bitcoin.getAbvName());
            System.out.println("The description is " + bitcoin.getDescription());
            System.out.println("The marketcap of the coin is " + bitcoin.getMarketCap() + "B");
            System.out.println("The trading volume of the coin is " + bitcoin.getTradingVolume() + "B");
            System.out.println("The price is " + bitcoin.getPrice());

            System.out.println("\n----------ETHEREUM----------");
            System.out.println("The name of the coin is " + ethereum.getName());
            System.out.println("The Abv name of the coin is " + ethereum.getAbvName());
            System.out.println("The description is " + ethereum.getDescription());
            System.out.println("The marketcap of the coin is " + ethereum.getMarketCap() + "B");
            System.out.println("The trading volume of the coin is " + ethereum.getTradingVolume() + "B");
            System.out.println("The price is " + ethereum.getPrice());

            System.out.println("\n----------LITECOIN----------");
            System.out.println("The name of the coin is " + litecoin.getName());
            System.out.println("The Abv name of the coin is " + litecoin.getAbvName());
            System.out.println("The description is " + litecoin.getDescription());
            System.out.println("The marketcap of the coin is " + litecoin.getMarketCap() + "B");
            System.out.println("The trading volume of the coin is " + litecoin.getTradingVolume() + "M");
            System.out.println("The price is " + litecoin.getPrice());

            System.out.println("\n----------MAIDSAFECOIN----------");
            System.out.println("The name of the coin is " + maidsafecoin.getName());
            System.out.println("The Abv name of the coin is " + maidsafecoin.getAbvName());
            System.out.println("The description is " + maidsafecoin.getDescription());
            System.out.println("The marketcap of the coin is " + maidsafecoin.getMarketCap() + "M");
            System.out.println("The trading volume of the coin is " + maidsafecoin.getTradingVolume());
            System.out.println("The price is " + maidsafecoin.getPrice());


            System.out.println("\n----------BLACKCOIN----------");
            System.out.println("The name of the coin is " + blackcoin.getName());
            System.out.println("The Abv name of the coin is " + blackcoin.getAbvName());
            System.out.println("The description is " + blackcoin.getDescription());
            System.out.println("The marketcap of the coin is " + blackcoin.getMarketCap() + "M");
            System.out.println("The trading volume of the coin is " + blackcoin.getTradingVolume());
            System.out.println("The price is " + blackcoin.getPrice());

            try{      
                Socket s=new Socket("localhost",14866); 
                DataInputStream din = new DataInputStream(s.getInputStream());   
                DataOutputStream dout=new DataOutputStream(s.getOutputStream());   
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Put your's Username: \n");
                String username_value = br.readLine();
                System.out.println("Put your's Password: \n");
                String password_value = br.readLine();
                dout.writeUTF(username_value);
                dout.writeUTF(password_value);  
                String  inputstr1 = (String)din.readUTF();  
                System.out.println("Verified-"+inputstr1);

                String  inputstr2 = (String)din.readUTF();  
                System.out.println(inputstr2);
                
                String details = (String)din.readUTF();
                String[] array_Of_String = details.split(" ");
                System.out.println(details);

                System.out.println("Type 1 for Buy the coins and 2 for sell the coins");
                String  inputdata1 = br.readLine();
                int input = Integer.parseInt(inputdata1);

                if(input == 1)
                {
                    System.out.println("buy the coin");
                    System.out.println("Choose from the list of coins: 1 -> Bitcoin\n2 -> Ethereum\n3 -> Litecoin\n4 -> Maidsafecoin\n5 -> Blackcoin");
                    String choice = br.readLine();
                    int buy =Integer.parseInt(choice);

                    if(buy == 1)
                    {
                        System.out.println("Enter the quantity to want to buy: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("b"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array - (quantity*bitcoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 + quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                array_Of_String[i+3] = time;

                                break;

                            } 
                            if(i == (array_Of_String.length-1) && word != "b")
                            {
                                String[] new_array_Of_String = Arrays.copyOf(array_Of_String, array_Of_String.length + 4);
                                new_array_Of_String[array_Of_String.length] = "b";
                                new_array_Of_String[array_Of_String.length + 1] = quantity_str;
                                new_array_Of_String[array_Of_String.length + 2] = String.valueOf(bitcoin.getPrice());

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                new_array_Of_String[array_Of_String.length + 3] = time;

                                double arr1 = Double.parseDouble(new_array_Of_String[2]);
                                arr1 = arr1 - (quantity*bitcoin.getPrice());
                                String s3 = String.valueOf(arr1);
                                new_array_Of_String[2] = s3;

                                String client_new = String.join(" ",new_array_Of_String);
                                System.out.println(client_new);
                                dout.writeUTF(client_new);
                                array_Of_String[i+1] = "b";
                                System.out.println(array_Of_String);
                            }
                        }

                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(buy == 2)
                    {
                        System.out.println("Enter the quantity to want to buy: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("e"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array - (quantity*ethereum.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 + quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                array_Of_String[i+3] = time;

                                break;

                            } 

                            if(i == (array_Of_String.length-1) && word != "e")
                            {
                                String[] new_array_Of_String = Arrays.copyOf(array_Of_String, array_Of_String.length + 4);
                                new_array_Of_String[array_Of_String.length] = "e";
                                new_array_Of_String[array_Of_String.length + 1] = quantity_str;
                                new_array_Of_String[array_Of_String.length + 2] = String.valueOf(ethereum.getPrice());
                                double arr1 = Double.parseDouble(new_array_Of_String[2]);
                                arr1 = arr1 - (quantity*ethereum.getPrice());
                                String s3 = String.valueOf(arr1);
                                new_array_Of_String[2] = s3;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                new_array_Of_String[array_Of_String.length + 3] = time;

                                String client_new = String.join(" ",new_array_Of_String);
                                System.out.println(client_new);
                                dout.writeUTF(client_new);
                                array_Of_String[i+1] = "e";
                                System.out.println(array_Of_String);
                            }
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(buy == 3)
                    {
                        System.out.println("Enter the quantity to want to buy: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("l"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array - (quantity*litecoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 + quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                array_Of_String[i+3] = time;

                                break;

                            } 

                            if(i == (array_Of_String.length-1) && word != "l")
                            {
                                String[] new_array_Of_String = Arrays.copyOf(array_Of_String, array_Of_String.length + 4);
                                new_array_Of_String[array_Of_String.length] = "l";
                                new_array_Of_String[array_Of_String.length + 1] = quantity_str;
                                new_array_Of_String[array_Of_String.length + 2] = String.valueOf(litecoin.getPrice());
                                double arr1 = Double.parseDouble(new_array_Of_String[2]);
                                arr1 = arr1 - (quantity*litecoin.getPrice());
                                String s3 = String.valueOf(arr1);
                                new_array_Of_String[2] = s3;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                new_array_Of_String[array_Of_String.length + 3] = time;


                                String client_new = String.join(" ",new_array_Of_String);
                                System.out.println(client_new);
                                dout.writeUTF(client_new);
                                array_Of_String[i+1] = "l";
                                System.out.println(array_Of_String);
                            }
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(buy == 4)
                    {
                        System.out.println("Enter the quantity to want to buy: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("m"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array - (quantity*maidsafecoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 + quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                array_Of_String[i+3] = time;

                                break;

                            } 

                            if(i == (array_Of_String.length-1) && word != "m")
                            {
                                String[] new_array_Of_String = Arrays.copyOf(array_Of_String, array_Of_String.length + 4);
                                new_array_Of_String[array_Of_String.length] = "m";
                                new_array_Of_String[array_Of_String.length + 1] = quantity_str;
                                new_array_Of_String[array_Of_String.length + 2] = String.valueOf(maidsafecoin.getPrice());
                                double arr1 = Double.parseDouble(new_array_Of_String[2]);
                                arr1 = arr1 - (quantity*maidsafecoin.getPrice());
                                String s3 = String.valueOf(arr1);
                                new_array_Of_String[2] = s3;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                new_array_Of_String[array_Of_String.length + 3] = time;


                                String client_new = String.join(" ",new_array_Of_String);
                                System.out.println(client_new);
                                dout.writeUTF(client_new);
                                array_Of_String[i+1] = "m";
                                System.out.println(array_Of_String);
                            }
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(buy == 5)
                    {
                        System.out.println("Enter the quantity to want to buy: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("c"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array - (quantity*blackcoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 + quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                array_Of_String[i+3] = time;

                                break;

                            } 

                            if(i == (array_Of_String.length-1) && word != "c")
                            {
                                String[] new_array_Of_String = Arrays.copyOf(array_Of_String, array_Of_String.length + 4);
                                new_array_Of_String[array_Of_String.length] = "c";
                                new_array_Of_String[array_Of_String.length + 1] = quantity_str;
                                new_array_Of_String[array_Of_String.length + 2] = String.valueOf(blackcoin.getPrice());
                                double arr1 = Double.parseDouble(new_array_Of_String[2]);
                                arr1 = arr1 - (quantity*blackcoin.getPrice());
                                String s3 = String.valueOf(arr1);
                                new_array_Of_String[2] = s3;

                                LocalDateTime localDateTime = LocalDateTime.now();
                                String time = String.valueOf(localDateTime);
                                new_array_Of_String[array_Of_String.length + 3] = time;

                                String client_new = String.join(" ",new_array_Of_String);
                                System.out.println(client_new);
                                dout.writeUTF(client_new);
                                array_Of_String[i+1] = "c";
                                System.out.println(array_Of_String);
                            }
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                }

                else if(input == 2)
                {
                    System.out.println("sell the coin");
                    System.out.println("Choose from the list of coins: 1 -> Bitcoin\n2 -> Ethereum\n3 -> Litecoin\n4 -> Maidsafecoin\n5 -> Blackcoin");
                    String choice = br.readLine();
                    int sell =Integer.parseInt(choice);

                    if(sell == 1)
                    {
                        System.out.println("Enter the quantity to want to sell: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("b"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array + (quantity*bitcoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 - quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                            } 
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(sell == 2)
                    {
                        System.out.println("Enter the quantity to want to sell: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("e"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array + (quantity*ethereum.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 - quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                            } 
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(sell == 3)
                    {
                        System.out.println("Enter the quantity to want to sell: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("l"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array + (quantity*litecoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 - quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                            } 
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(sell == 4)
                    {
                        System.out.println("Enter the quantity to want to sell: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("m"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array + (quantity*maidsafecoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 - quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                            } 
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                    else if(sell == 5)
                    {
                        System.out.println("Enter the quantity to want to sell: ");
                        int quantity = sc.nextInt();
                        String quantity_str = String.valueOf(quantity);
                        for(int i = 0; i < array_Of_String.length; i++)
                        {
                            String word = array_Of_String[i];
                            if(word.equals("c"))
                            {
                                double array = Double.parseDouble(array_Of_String[2]);
                                array = array + (quantity*blackcoin.getPrice());
                                System.out.println(array);
                                String s1 = String.valueOf(array);
                                array_Of_String[2] = s1;

                                int array1 = Integer.parseInt(array_Of_String[i+1]);
                                array1 = array1 - quantity;
                                System.out.println(array1);
                                String s2 = String.valueOf(array1);
                                array_Of_String[i+1] = s2;

                            } 
                        }
                        String client = String.join(" ",array_Of_String);
                        System.out.println("\nNew Client State:");
                        System.out.println(client);
                        dout.writeUTF(client);
                    }

                }
                
                s.close();  
            }
            catch(Exception e){System.out.println(e);}  

        }  
        catch(Exception e){
            System.out.println(e);
        }
    }
}
