package com.qa.utils;

import com.qa.BaseTest;
import org.apache.commons.codec.binary.Base64;
import org.testng.ITestResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestUtils {
    public static final long WAIT = 10L;
    public static final String FILE_EXTENSION = ".png";
    public static final String UNDERSCORE = "_";
    public static final String VIDEO_FILE_EXTENSION = ".mp4";

    public static HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String, String> stringHashMap = new HashMap();

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

        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                //store each element key value in map
                stringHashMap.put(element.getAttribute("name"), element.getTextContent());
            }
        }

        return stringHashMap;
    }

    public static String getDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy_hh-mm-ss");
        return simpleDateFormat.format(new Date());
    }

    public static String getImagePath(String className, String testMethodName, String deviceName, String platformName) {
        return "Screenshots" + File.separator +
                platformName + "_" +
                deviceName + File.separator +
                className + File.separator +
                testMethodName + "_" + getDateTime() +
                FILE_EXTENSION;
    }

    public static void captureVideo(ITestResult result, String base64media) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();

            String dir = "Videos" + File.separator + params.get("platformName") + UNDERSCORE + params.get("platformVersion")
                    + UNDERSCORE + params.get("deviceName") + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator +
            TestUtils.getDateTime();


            File videoDir = new File(dir);
            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(videoDir + File.separator + result.getName() + VIDEO_FILE_EXTENSION);
                try {
                    fileOutputStream.write(Base64.decodeBase64(base64media));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
