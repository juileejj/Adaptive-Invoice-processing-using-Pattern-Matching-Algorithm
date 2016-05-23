/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Prarthana
 */
public class DataLoad {

    String filename;

    int depthOfXML = 1;
    private String tc[] = new String[100];
    int m = 0, j = 0;
    static int z = 0;
    String regex = "^[a-zA-Z0-9]*$";
    private String getColumnNameString;
    private String tables[] = new String[20];
    private String myString[][] = new String[20][100];
    private String fileTableName[] = new String[20];
    private String tableColumnData[] = new String[100];
    // private static String tokens[]=new String[3];
    private String status = "old";

    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/dbteragigs";
    String USER = "root";
    String PASS = "root";

    public void loadData(String filename) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filename);

            Element elements = doc.getDocumentElement();

            int level = 1;
            //System.out.println(elements.getNodeName() + "[" + level + "]");
            tc[m] = String.valueOf(elements.getNodeName() + " " + level);

            NodeList nodeList = elements.getChildNodes();
            printNode(nodeList, level);

            for (int i = 0; i < tables.length; i++) {
                // System.out.println(tables[i]);
            }

            getTableName(tables);
            getColumnNames();
            System.out.println("The deepest level is : " + depthOfXML);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DataLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int j = 0; j < tc.length; j++) {
            if (tc[j] != null) {

                if (tc[j].endsWith("\t")) {

                    int i = tc[j].indexOf("\t");
                    String s = tc[j];
                    tc[j] = " ";
                    tc[j] = s.substring(0, i - 1);
                }
                //if(!tc[j].contains("\t") )
                //  System.out.println(tc[j]);

            }
        }
    }

    private void printNode(NodeList nodeList, int level) throws SQLException {
        level++;

        if (nodeList != null && nodeList.getLength() > 0) {

            // System.out.println("Length of node" +nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    // System.out.println(node.getNodeName() + "[" + level + "]");
                    m++;

                    tc[m] = String.valueOf(node.getNodeName() + " " + level + " " + node.getTextContent());
                    if (level == 2) {

                        tables[j] = node.getNodeName();
                        j++;

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

    public void getTableName(String s[]) {
        Connection conn = null;
        Statement stmt = null;
        CallableStatement callableStatement = null;
        CallableStatement callableStat = null;

        String tableName;
        String getTableName = "{call SP_GET_TABLE_NAME(?,?)}";
        String getTableNameSoundex = "{call SP_GET_TABLE_NAME_SOUNDEX_procedure(?,?)}";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
        } catch (Exception e) {

        }

        try {
            for (int i = 0; i < s.length; i++) {

                callableStatement = conn.prepareCall(getTableName);

                callableStatement.setString(1, s[i]);
                // System.out.println(s[i]);
                callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                callableStatement.executeUpdate();
                // System.out.println("AlterTable");

                //  System.out.println("SP executed");
                System.out.println("Before SP");
                //  System.out.println(callableStatement.getString(2));
                //if(callableStatement.getString(2).equals("null"))
                tableName = callableStatement.getString(2);
                System.out.println("tablename : " + tableName);
                //if(tableName.equals("no"))
                //{System.out.println("AFter SP");

                //  callableStat = conn.prepareCall(getTableNameSoundex);
                // callableStat.setString(1,s[i]);
                //callableStat.registerOutParameter(2, java.sql.Types.VARCHAR);
                //callableStat.executeUpdate();
                //String finalTableName = callableStat.getString(2);
                //fileTableName[i] = finalTableName;
                //System.out.println("this is the table name returned");
                //System.out.println(finalTableName);
                //}
                // else
                //{  
                // System.out.println("this is the table name returned");
                //   String tableName1 = callableStatement.getString(2);
                //  System.out.println(tableName1);
                fileTableName[i] = tableName;

            }

        } catch (Exception e) {

        }

    }

    public void getColumnNames() {

        Connection conn = null;
        Statement stmt = null;
        CallableStatement callableStatement = null;

        getColumnNameString = "{call SP_GET_COLUMN_AND_TABLE_NAME(?,?,?)}";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
        } catch (Exception e) {

        }

        try {

            label:

            for (int i = 0; i < tc.length; i++) {
                if (tc[i] != null) {
                    String tokens[] = tc[i].split("\\s+");
                    for (int k = 0; k < tables.length; k++) {
                        if (tables[k] != null) {
                            if (tokens[0].equalsIgnoreCase(tables[k])) {
                                status = "new";
                                continue label;
                            } else {
                                if (tokens[1].equals(String.valueOf(3))) {

                                    callableStatement = conn.prepareCall(getColumnNameString);

                                    callableStatement.setString(1, fileTableName[k]);
                                    callableStatement.setString(2, tokens[0]);
                                    System.out.println("Before register out");

                                    callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
                                    System.out.println("Before column SP");
                                    callableStatement.executeUpdate();
                                    System.out.println("After column SP");
                                    String columnNameSP = callableStatement.getString(3);
                                    System.out.println(columnNameSP);

                                    tableColumnData[z] = fileTableName[k] + " " + tokens[0] + " " + tokens[2];
                                    System.out.println(tableColumnData[z]);
                                    z++;
                                     System.out.println(status);
                                    if (status.equalsIgnoreCase("new")) {
                                        System.out.println("inside new");
                                        // String sql = "INSERT INTO TagNames(TagName) " + " VALUES (" + "?)";
                                        String sql = "INSERT INTO " + fileTableName[k] + "(" + columnNameSP + ")" + " VALUES (" + tokens[2] + ")";
                                        System.out.println(sql);
                                        PreparedStatement ps = conn.prepareStatement(sql);
                                        System.out.println(tokens[2]);
                                        ps.executeUpdate();
                                        status = "old";

                                    }
                                    else
                                    {
                                         System.out.println("inside old");
                                        String sqlUpdate = "update " + fileTableName[k] + " set " + columnNameSP + "='" + tokens[2] + "'" + " where auto_incremented_id = (Select t from (Select max(auto_incremented_id) t from " + fileTableName[k] + " h order by auto_incremented_id desc limit 1) as t2)";
                                        System.out.println(sqlUpdate);
                                        PreparedStatement ps = conn.prepareStatement(sqlUpdate);
                                       
                                        System.out.println(tokens[2]);
                                        ps.executeUpdate();

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
            catch(Exception e)
        {
            
        }

        }

    }
