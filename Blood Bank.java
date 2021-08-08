package src;

import java.sql.*;
import java.util.Scanner;

public class bb1 {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/";
    static String user = "root";
    static String pass = "suja1977@";
    static Connection connection = null;
    static Statement statement = null;
    public static void main(String[] args) throws Exception {
        try {
            Scanner sc = new Scanner(System.in);
            String cmnd = "";
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, user, pass);
            System.out.println("\n\t-----CREATING DATABASE-----\n");
            statement = connection.createStatement();
            
            cmnd = " CREATE DATABASE IF NOT EXISTS BloodBank";
            statement.executeUpdate(cmnd);
           // System.out.println("\n\t-----DATABASE SUCCESSFULLY CREATED-----\n");

            cmnd = " USE BloodBank";
            statement.executeUpdate(cmnd);

           cmnd = "CREATE TABLE IF NOT EXISTS Donor (Donor_ID int NOT NULL AUTO_INCREMENT, FirstName varchar(255),LastName varchar(255),Address varchar(255),City varchar(255),Contact_Number varchar(255), Blood_Group varchar(255) ,PRIMARY KEY(Donor_ID));";
            statement.executeUpdate(cmnd);

            //System.out.println("\n\t-----TABLE  DONOR SUCCESSFULLY CREATED-----\n");

            cmnd = "CREATE TABLE IF NOT EXISTS Patient (Patient_ID int NOT NULL AUTO_INCREMENT ,FirstName varchar(255),LastName varchar(255),Address varchar(255),City varchar(255),Contact_Number varchar(255), Blood_Group varchar(255), Patient_Disease varchar(255), PRIMARY KEY(Patient_ID) );";
            statement.executeUpdate(cmnd);

            // System.out.println("\n\t-----TABLE PATIENT SUCCESSFULLY CREATED-----\n");

            //cmnd = "ALTER TABLE  `bloodbank`.`patient`"+ 
            //"CHANGE COLUMN `Patient_ID` `Patient_ID` INT NOT NULL AUTO_INCREMENT ,"+
            //"ADD PRIMARY KEY (`Patient_ID`);";
            
            //statement.executeUpdate(cmnd);

            //cmnd = "ALTER TABLE `bloodbank`.`donor` CHANGE COLUMN `Contact_Number` `Contact_Number` VARCHAR(255) NULL DEFAULT NULL ;";
            //1statement.executeUpdate(cmnd);

            cmnd = "ALTER TABLE `bloodbank`.`patient` CHANGE COLUMN `Contact_Number` `Contact_Number` VARCHAR(225) NULL DEFAULT NULL ;";
            statement.executeUpdate(cmnd);
            
            int option = 0,Count=0,Count1=0;
            String firstname="" , lastname = "" , address = "" , city = "" ,blood_group = "" , contact = "";
            System.out.println("\033[H\033[2J");
            while(option!=9){
                System.out.println("\n\t1. INSERT IN DONOR DATASET\n\t2. INSERT IN PATIENT DATASET\n\t3. TO PRINT CONTENTS IN DONOR DATASET\n\t4. TO PRINT CONTENTS IN PATIENT DATASET\n\t5. MODIFY IN DONAR DATASET\n\t6. MODIFY IN PATIENT DATASET\n\t7. DELETE COTENTS FROM DONAR DATASET\n\t8. DELETE CONTENTS FROM PATIENT DATASET\n\t9. EXIT  ");
                System.out.print("\tEnter Your Option :");
                option = sc.nextInt();
                sc.nextLine();
                switch(option){
                    case 1 :{
                        System.out.println("\033[H\033[2J");
                        System.out.println("\n\t------ENTER THE CONTENTS IN DONOR DATASET--------");
                        System.out.print("\nEnter the First name :");
                        firstname = sc.nextLine();
                        System.out.print("\nEnter the Last name :");
                        lastname = sc.nextLine();
                        System.out.print("\nEnter the Address :");
                        address = sc.nextLine();
                        System.out.print("\nEnter the City :");
                        city = sc.nextLine();
                        System.out.print("\nEnter the Contact Number :");
                        contact = sc.nextLine();
                        if (contact !=""){
                        cmnd = "SELECT Contact_Number from Donor";
                        ResultSet res = statement.executeQuery(cmnd);
                        while(res.next()){
                            String Contact1 = res.getString("Contact_Number");
                            if(contact.equals(Contact1)) {
                                System.out.println("\nTHE PHONE NUMBER IS ALREADY REGISTERED");
                                System.out.print("\nREENTER THE PHONE NUMBER :");
                                contact = sc.nextLine();
                            }
                    }
                    }
                        System.out.print("\nEnter the Blood Group :");
                        blood_group = sc.nextLine();
                        cmnd = " USE BloodBank";
                        statement.executeUpdate(cmnd);
                        cmnd = "INSERT INTO Donor (FirstName, LastName, Address, City, Contact_Number , Blood_Group) VALUES ('"+firstname+"','"+lastname+"','" +address+"','"+city+"','"+contact+"','"+blood_group+"');";
                        statement.executeUpdate(cmnd);
                        System.out.print("\nCLEAR SCREEN:\n");
                        System.in.read();
                        System.out.println("\033[H\033[2J");
                    }break;
                    case 2 :{
                        System.out.println("\033[H\033[2J");
                        System.out.println("\n\t------ENTER THE CONTENTS IN PATIENT DATASET--------");
                        String disease = "";
                        System.out.print("\nEnter the First name :");
                        firstname = sc.nextLine();
                        System.out.print("\nEnter the Last name :");
                        lastname = sc.nextLine();
                        System.out.print("\nEnter the Address :");
                        address = sc.nextLine();
                        System.out.print("\nEnter the City :");
                        city = sc.nextLine();
                        System.out.print("\nEnter the Contact Number :");
                        contact = sc.nextLine();
                        if (contact !=""){
                            cmnd = "SELECT Contact_Number from Patient";
                            ResultSet res = statement.executeQuery(cmnd);
                            while(res.next()){
                                String Contact1 = res.getString("Contact_Number");
                                if(contact.equals(Contact1)) {
                                    System.out.println("\nTHE PHONE NUMBER IS ALREADY REGISTERED");
                                    System.out.print("\nREENTER THE PHONE NUMBER :");
                                    contact = sc.nextLine();
                                }
                            }
                        }
                        System.out.print("\nEnter the Blood Group :");
                        blood_group = sc.nextLine();
                        System.out.print("\nEnter the Patient Disease :");
                        disease = sc.nextLine();
                        cmnd = " USE BloodBank";
                        statement.executeUpdate(cmnd);
                        cmnd = "INSERT INTO Patient (FirstName, LastName, Address, City, Contact_Number , Blood_Group , Patient_Disease) VALUES ('"+firstname+"','"+lastname+"','" +address+"','"+city+"','"+contact+"','"+blood_group+"','"+disease+"');";
                        statement.executeUpdate(cmnd);   
                        System.out.print("\nCLEAR DATA :\n");
                        System.in.read();
                        System.out.println("\033[H\033[2J");
                    }break;
                    case 3 :{
                        System.out.println("\033[H\033[2J");
                       cmnd = "SELECT COUNT(*) FROM Donor AS Count";
                       ResultSet Result1 = statement.executeQuery(cmnd); 
                       while(Result1.next()){
                        Count = Result1.getInt("COUNT(*)");
                       }
                       if (Count == 0){
                           System.out.print("\nDATASET IS EMPTY :(\n");
                           break;
                       }
                        System.out.println("\n\t------PRINTING THE CONTENTS OF DONOR DATASET--------");
                        cmnd = "SELECT * FROM Donor";
                        ResultSet Result = statement.executeQuery(cmnd);
                        System.out.println("\nID\tFirstName\tLastname\tAddress\t\tCity\t\tNumber\t\tBlood_Group");
                        while(Result.next()){
                            int dat = Result.getInt("Donor_ID");
                            String data = Result.getString("FirstName");
                            String data1 = Result.getString("LastName");
                            String data2 = Result.getString("Address");
                            String data3 = Result.getString("City");
                            String data4 = Result.getString("Contact_Number");
                            String data5 = Result.getString("Blood_Group");
                            //System.out.println("\n"+dat+"\t\t"+data+"\t\t"+data1+"\t\t"+data2+"\t\t"+data3+"\t\t"+data4+"\t\t"+data5);
                            System.out.printf("%d\t %-14s %-14s %-15s %-16s %-15s %-5s\n",dat,data,data1,data2,data3,data4,data5);}
                            System.out.print("\nCLEAR SCREEN:\n");
                            System.in.read();
                            System.out.println("\033[H\033[2J");
                    }break;
                    case 4:{
                        System.out.println("\033[H\033[2J");
                        int num;
                        cmnd = "SELECT COUNT(*) FROM Patient AS Count";
                        ResultSet Result1 = statement.executeQuery(cmnd); 
                        while(Result1.next()){
                         Count1 = Result1.getInt("COUNT(*)");
                        }
                        if (Count1 == 0){
                            System.out.print("\nDATASET IS EMPTY :(\n");
                            break;
                        }
                        System.out.println("\n\t------PRINTING THE CONTENTS OF PATIENT DATASET--------");
                        cmnd = "SELECT * FROM Patient";
                        ResultSet Result = statement.executeQuery(cmnd);
                        System.out.println("\nID\tFirstName\tLastname\tAddress\t\tCity\t\tNumber\t\tBlood_Group\t\tDisease");
                        while(Result.next()){
                            num = Result.getInt("Patient_ID");
                            String data = Result.getString("FirstName");
                            String data1 = Result.getString("LastName");
                            String data2 = Result.getString("Address");
                            String data3 = Result.getString("City");
                            String data4 = Result.getString("Contact_Number");
                            String data5 = Result.getString("Blood_Group");
                            String data6 = Result.getString("Patient_Disease");
                            //System.out.println("\n"+dat+"\t\t"+data+"\t\t"+data1+"\t\t"+data2+"\t\t"+data3+"\t\t"+data4+"\t\t"+data5+"\t\t"+data6);
                            System.out.printf("%d\t %-14s %-14s %-15s %-16s %-15s %-23s %-10s\n",num,data,data1,data2,data3,data4,data5,data6);
                    }
                    System.out.print("\nCLEAR SCREEN:\n");
                    System.in.read();
                    System.out.println("\033[H\033[2J");
                }break;
                    case 5:{
                        System.out.println("\033[H\033[2J");
                        cmnd = "SELECT COUNT(*) FROM Donor AS Count";
                        ResultSet Result1 = statement.executeQuery(cmnd); 
                        while(Result1.next()){
                         Count = Result1.getInt("COUNT(*)");
                        }
                        if (Count == 0){
                            System.out.print("\nDATASET IS EMPTY :(\n");
                            break;
                        }
                        int f1=0;
                        String new_no = "",new_add="",First,first_data,new_name="",new_lname="",new_city="";
                        System.out.println("\n\t------MODIFYING THE CONTENTS IN DONOR DATASET--------");
                        cmnd = "SELECT * FROM Donor";
                        ResultSet Result = statement.executeQuery(cmnd);
                        System.out.print("\nEnter the ID of the person whose information you want to change :");
                        First = sc.nextLine();
                        while(Result.next()) {
                        first_data = Result.getString("Donor_ID");
                        if(First.equals(first_data)) {
                            f1=1;
                            System.out.print("\nEnter the new First Name :");
                            new_name = sc.nextLine();
                            System.out.print("\nEnter the new Last Name :");
                            new_lname = sc.nextLine();
                            System.out.print("\nEnter the new address :");
                            new_add  = sc.nextLine();
                            System.out.print("\nEnter the new city :");
                            new_city = sc.nextLine();
                            System.out.print("\nEnter the new Phone Number:");
                            new_no = sc.nextLine();
                           
                        }
                    }
                    Result.close();
                    if(f1==0) {
                        System.out.println("\nID doesn't exist !");
                    }
                    cmnd = "USE BloodBank";
                    statement.executeUpdate(cmnd);
                    if(f1 !=0){
                    cmnd = "UPDATE Donor SET Contact_Number ='"+new_no+"' WHERE Donor_ID = '"+First+"';";
                    //System.out.println(cmnd);
                    statement.executeUpdate(cmnd);
                    cmnd = "UPDATE Donor SET Address ='"+new_add+"' WHERE Donor_ID = '"+First+"';";
                    //System.out.println(cmnd);
                    statement.executeUpdate(cmnd);
                    cmnd = "UPDATE Donor SET FirstName ='"+new_name+"' WHERE Donor_ID = '"+First+"';";
                    //System.out.println(cmnd);
                    statement.executeUpdate(cmnd);
                    cmnd = "UPDATE Donor SET LastName ='"+new_lname+"' WHERE Donor_ID = '"+First+"';";
                    //System.out.println(cmnd);
                    statement.executeUpdate(cmnd);
                    cmnd = "UPDATE Donor SET City ='"+new_city+"' WHERE Donor_ID = '"+First+"';";
                    //System.out.println(cmnd);
                    statement.executeUpdate(cmnd);
                }  
                    System.out.print("\nCLEAR SCREEN:\n");
                    System.in.read();
                    System.out.println("\033[H\033[2J");
                    }break;
                case 6:{
                    System.out.println("\033[H\033[2J");
                    cmnd = "SELECT COUNT(*) FROM Patient AS Count";
                    ResultSet Result1 = statement.executeQuery(cmnd); 
                    while(Result1.next()){
                     Count1= Result1.getInt("COUNT(*)");
                    }
                    if (Count1 == 0){
                        System.out.print("\nDATASET IS EMPTY :(\n");
                        break;
                    }
                    int f1=0;
                        String  new_no = "",First,first_data,new_add="",new_name ="",new_lname="",new_city="";
                        System.out.println("\n\t------MODIFYING THE CONTENTS IN PATIENT DATASET--------");
                        cmnd = "SELECT * FROM Patient";
                        ResultSet Result = statement.executeQuery(cmnd);
                        System.out.print("\nEnter the ID of the person whose information you want to change :");
                        First = sc.nextLine();
                        while(Result.next()) {
                        first_data = Result.getString("Patient_ID");
                        if(First.equals(first_data)) {
                            f1=1;
                            System.out.print("\nEnter the new First Name :");
                            new_name = sc.nextLine();
                            System.out.print("\nEnter the new Last Name :");
                            new_lname = sc.nextLine();
                            System.out.print("\nEnter the new address :");
                            new_add  = sc.nextLine();
                            System.out.print("\nEnter the new city :");
                            new_city = sc.nextLine();
                            System.out.print("\nEnter the new Phone Number:");
                            new_no = sc.nextLine();   
                        }
                    }
                    Result.close();
                    if(f1==0) {
                        System.out.println("\nID doesn't exist !");
                    }
                    cmnd = "USE BloodBank";
                    statement.executeUpdate(cmnd);
                    if(f1!=0){
                        cmnd = "UPDATE Patient SET Contact_Number ='"+new_no+"' WHERE Patient_ID = '"+First+"';";
                        //System.out.println(cmnd);
                       cmnd = "UPDATE Patient SET Address ='"+new_add+"' WHERE Patient_ID = '"+First+"';";
                        //System.out.println(cmnd);
             statement.executeUpdate(cmnd);
                        statement.executeUpdate(cmnd);
                        cmnd = "UPDATE Patient  SET FirstName ='"+new_name+"' WHERE Patient_ID = '"+First+"';";
                        //System.out.println(cmnd);
                        statement.executeUpdate(cmnd);
                        cmnd = "UPDATE Patient  SET LastName ='"+new_lname+"' WHERE Patient_ID = '"+First+"';";
                        //System.out.println(cmnd);
                        statement.executeUpdate(cmnd);
                        cmnd = "UPDATE Patient  SET City ='"+new_city+"' WHERE Patient_ID = '"+First+"';";
                        //System.out.println(cmnd);
                        statement.executeUpdate(cmnd);
                    }  
                    System.out.print("\nCLEAR SCREEN:\n");
                    System.in.read();
                    System.out.println("\033[H\033[2J");
                }break;
                case 7:{
                    System.out.println("\033[H\033[2J");
                    cmnd = "SELECT COUNT(*) FROM Donor AS Count";
                    ResultSet Result1 = statement.executeQuery(cmnd); 
                    while(Result1.next()){
                     Count = Result1.getInt("COUNT(*)");
                    }
                    if (Count == 0){
                        System.out.print("\nDATASET IS EMPTY :(\n");
                        break;
                    }
                    int flag = 0;
                    System.out.println("\n\t--------DELETING THE CONTENTS IN DONOR DATASET--------\n\t");
                    String fname ;
                    System.out.print("Enter the ID you want to delete :");
                    fname = sc.nextLine();
                    cmnd = "SELECT * FROM Donor WHERE Donor_ID = '"+fname+"';";
                    ResultSet Result = statement.executeQuery(cmnd);
                    while(Result.next()) {
                        String content1 = Result.getString("Donor_ID");
                        if(fname.equals(content1)){
                            cmnd = "USE BloodBank";
                            statement.executeUpdate(cmnd);
                            flag = 1;
                            cmnd = "DELETE FROM Donor WHERE Donor_ID = '"+fname+"';";
                           // System.out.println(cmnd);
                            statement.executeUpdate(cmnd);
                            break;
                        }
                    }
                    Result.close();
                    if(flag == 0){
                        System.out.println("\nID doesn't exist !");
                    }
                    System.out.print("\nCLEAR SCREEN:\n");
                    System.in.read();
                    System.out.println("\033[H\033[2J");
                }break;
                
                case 8:{
                    System.out.println("\033[H\033[2J");
                    cmnd = "SELECT COUNT(*) FROM Patient AS Count";
                    ResultSet Result1 = statement.executeQuery(cmnd); 
                    while(Result1.next()){
                     Count1 = Result1.getInt("COUNT(*)");
                    }
                    if (Count1 == 0){
                        System.out.print("\nDATASET IS EMPTY :(\n");
                        break;
                    }
                    int flag = 0;
                    System.out.println("\n\t--------DELETING THE CONTENTS IN PATIENT DATASET--------\n\t");
                    String fname ;
                    cmnd = "SELECT * FROM Patient";
                    System.out.print("Enter the ID you want to delete :");
                    fname = sc.nextLine();
                    ResultSet Result = statement.executeQuery(cmnd);
                    while(Result.next()){
                        String content1 = Result.getString("Patient_ID");
                        if(fname.equals(content1)){
                            cmnd = "USE BloodBank";
                            statement.executeUpdate(cmnd);
                            flag = 1;
                            cmnd = "DELETE FROM Patient WHERE Patient_ID = '"+fname+"';";
                            //System.out.println(cmnd);
                            statement.executeUpdate(cmnd);
                            break;
                        }
                    }
                    Result.close();
                    if(flag == 0){
                        System.out.println("\nID doesn't exist !");
                    }
                    System.out.print("\nCLEAR SCREEN:\n");
                    System.in.read();
                    System.out.println("\033[H\033[2J");
                }break;
            
                case 9:{
                    System.out.println("\033[H\033[2J");
                    System.out.println("\n\tTHANK YOU :)\n\n");
                    
                }break;
            }
        }
        sc.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(connection!=null)
                connection.close();
        }
    }
}