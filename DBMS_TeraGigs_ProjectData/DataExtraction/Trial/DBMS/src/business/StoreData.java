/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author Prarthana
 */
public class StoreData {
    
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USER = "root";
    static final String PASS = "root";
    public static void main(String args[]) throws FileNotFoundException{
        
        Connection conn = null;
        Statement stmt = null;
        
        try{
         Class.forName("com.mysql.jdbc.Driver");   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            
            
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the file name with extention : ");
            File file = new File(input.nextLine());

            input = new Scanner(file);

            String[] tags=new String[10];
            while (input.hasNext()) {
                
                String line = input.next();
                int s1 = line.indexOf("<");
                int s2 = line.indexOf(">");
                String s = line.substring(s1+1, s2);
                System.out.println(s);
                if(!s.contains("/"))
                {
                 String sql = "INSERT INTO TagNames(TagName) " + " VALUES (" + "?)";
               System.out.println(sql);
               PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, s);
                ps.executeUpdate();
                
                
                
             //   try
               // {
                 //   DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                  //  DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                   // Document doc = docBuilder.parse(file);
                    // normalize text representation
                   // doc.getDocumentElement().normalize();
                    
                    
                    
                //}
                
                
                
                System.out.println("record inserted");
                }
            }
            input.close();
          // conn.close();

        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}