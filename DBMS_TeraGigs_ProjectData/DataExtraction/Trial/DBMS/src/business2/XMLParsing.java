/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business2;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParsing {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        Element tag =null;

        File fXmlFile = new File("DemoFile.txt");
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);

        // process
        NodeList studentobj = doc.getElementsByTagName("Invoice");
        //System.out.println(studentobj.getLength());

        for (int i=0;i<studentobj.getLength();i++)
        {
          Node row= studentobj.item(i);
          
          if (row.getNodeType()==Node.ELEMENT_NODE)
          {   
              Element erow =(Element)row;
              NodeList stditems = erow.getChildNodes();
              
              String line ="";
              
            //System.out.println(stditems.getLength());
              for (int j=0; j<stditems.getLength();j++) 
              {
                 Node value = stditems.item(j);
                 
                // System.out.println(value.getAttributes());
                 if (value.getNodeType()==Node.ELEMENT_NODE)
                 {
                    // System.out.println(value.getNodeName());
                     Element evalue = (Element)value;
                    // System.out.println(evalue.getTextContent());
                    line =line+evalue.getTextContent()+",";
                  //tag=evalue;
                  System.out.println(evalue.getNodeName());
                    
                    
                    

                 }
                 
              }
              System.out.print(line.substring(0, line.length()-1));
          }

        }





}
}