package com.qa.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;

public class TestUtils {
    public static final long WAIT = 10L;

    public static HashMap<String ,String> parseStringXML(InputStream file) throws Exception{
        HashMap<String,String> stringHashMap = new HashMap();

        //get document builder
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        //build document
        Document document = documentBuilder.parse(file);

        //Normalise the XML Structure
        document.getDocumentElement().normalize();

        //root node
        Element root = document.getDocumentElement();

        //Get all elements
        NodeList nodeList = document.getElementsByTagName("string");

        for (int temp = 0; temp < nodeList.getLength(); temp++){
            Node node = nodeList.item(temp);

            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                //store each element key value in map
                stringHashMap.put(element.getAttribute("name"),element.getTextContent());
            }
        }

        return stringHashMap;
    }
}
