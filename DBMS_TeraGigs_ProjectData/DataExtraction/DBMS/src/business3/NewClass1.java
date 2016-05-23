package business3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NewClass1 {

 static int depthOfXML = 1;
 

 public static void main(String argv[]) {

   try {

    String filepath = "DemoFile.txt";
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.parse(filepath);

    Element elements = doc.getDocumentElement();

    int level = 1;
    System.out.println(elements.getNodeName() + "[" + level + "]");

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
 }

 private static void printNode(NodeList nodeList, int level) {
    level++;
    if (nodeList != null && nodeList.getLength() > 0) {

      for (int i = 0; i < nodeList.getLength(); i++) {

        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {

            System.out.println(node.getNodeName() + "[" + level + "]");
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