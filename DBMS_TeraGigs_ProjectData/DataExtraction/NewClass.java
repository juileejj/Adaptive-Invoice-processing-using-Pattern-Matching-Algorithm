/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepti
 */



import com.oracle.xmlns.internal.webservices.jaxws_databinding.ObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class NewClass {

    public static void main(String[] args) throws Exception {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xp = xpf.newXPath();

        InputSource xml = new InputSource("DemoFile.xml");
        NodeList leafNodeObjects = (NodeList) xp.evaluate("//*[not(*)]", xml, XPathConstants.NODESET);

        for(int x=0; x<leafNodeObjects.getLength(); x++) {
            System.out.print("nodeElement = ");
            System.out.print(leafNodeObjects.item(x).getNodeName());
            System.out.print(" and node value = ");
            System.out.println(leafNodeObjects.item(x).getTextContent());
            
        }
    }

}