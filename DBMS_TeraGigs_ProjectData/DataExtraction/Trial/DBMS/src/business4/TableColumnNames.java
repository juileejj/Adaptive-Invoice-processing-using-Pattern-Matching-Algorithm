package business4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TableColumnNames {

 static int depthOfXML = 1;
 private static String tc[] = new String[100];
 static int m=0;
 static String regex="^[a-zA-Z0-9]*$";
 
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";  
  static final String USER = "root";
  static final String PASS = "root";
  

 public static void main(String argv[]) throws FileNotFoundException, SQLException {
 
    
      

   try {

       
     
    String filepath = "DemoFile.txt";
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.parse(filepath);

    Element elements = doc.getDocumentElement();

    int level = 1;
  //System.out.println(elements.getNodeName() + "[" + level + "]");
  tc[m]=String.valueOf(elements.getNodeName() + " " + level );

    NodeList nodeList = elements.getChildNodes();
    printNode(nodeList, level);

    System.out.println("The deepest level is : " + depthOfXML);

   } catch (ParserConfigurationException pce) {
    pce.printStackTrace();
   } catch (IOException ioe) {
    ioe.printStackTrace();
   } catch (SAXException sae) {
    sae.printStackTrace();
   }
   
   for(int j = 0;j<tc.length;j++)
   {
       if(tc[j]!=null)
       {
           
           if(tc[j].endsWith("\t"))
           {
               
               int i = tc[j].indexOf("\t");
               String s = tc[j];
               tc[j]=" ";
               tc[j]=s.substring(0, i-1);
           }
      if(!tc[j].contains("\t") )
          System.out.println(tc[j]);
           
       
     
   }
 }
 }
 private static void printNode(NodeList nodeList, int level) throws SQLException { 
    level++;
    Connection conn = null;
        Statement stmt = null;
        CallableStatement callableStatement = null;
        
        String tableName ;
        String getTableName = "{call SP_GET_TABLE_NAME(?,?)}";
        try{
         Class.forName("com.mysql.jdbc.Driver");   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try{
            
            
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
        }
        catch(Exception e)
        {
            
        }
    if (nodeList != null && nodeList.getLength() > 0) {

       // System.out.println("Length of node" +nodeList.getLength());
      for (int i = 0; i < nodeList.getLength(); i++) {

        Node node = nodeList.item(i);
        
                
        if (node.getNodeType() == Node.ELEMENT_NODE) {

            
         // System.out.println(node.getNodeName() + "[" + level + "]");
            m++;
                      
                tc[m]=String.valueOf(node.getNodeName()+" "+level+" "+node.getTextContent());
                if(level == 2)
                {
                    
                   
                    try
                    {
                     callableStatement = conn.prepareCall(getTableName);
                     callableStatement.setString(1,node.getNodeName());
                     callableStatement.registerOutParameter(2,java.sql.Types.VARCHAR);
                     callableStatement.executeUpdate();
                     
                     tableName = callableStatement.getString(2);
                     System.out.println(tableName);
                    }
                    catch(Exception e)
                    {
                        
                    }
                   
                }
            
            printNode(node.getChildNodes(), level);

            // how depth is it?
            if (level > depthOfXML) {
                depthOfXML = level;
            }

         }

        }

      }

 }
 

}