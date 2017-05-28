package com.codecrimson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohamednur on 2017-05-27.
 */
public class InputParser {
    Map<String, String> tokenMap;

    public InputParser() {
        tokenMap = new HashMap<String, String>();
    }

    public void parse(String file) {
        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    NodeList elementList = nNode.getChildNodes();

                    tokenMap.put("id", eElement.getAttribute("id"));
                    for (int idx = 0; idx < elementList.getLength(); idx++) {
                        Node cNode = elementList.item(idx);
                        if(cNode.getNodeType() == Node.ELEMENT_NODE) {
                            String elementTagName = cNode.getNodeName();
                            String elementTextContent = eElement.getElementsByTagName(elementTagName).item(0).getTextContent();
                            tokenMap.put(elementTagName, elementTextContent);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
