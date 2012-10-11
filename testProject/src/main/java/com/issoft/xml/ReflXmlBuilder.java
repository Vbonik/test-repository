/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.mina.core.RuntimeIoException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author slavabrodnitski
 */
public class ReflXmlBuilder {

    private final static String ROOT_ELEMENT = "annotated_objects";
    private final static String FILE_PATH = "d:\\testing.xml";
    private Document document;
    private Element root;
    private File xmlFile;

    /**
     * trying of initializing of document by target file.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public ReflXmlBuilder() throws ParserConfigurationException, SAXException, IOException {
        xmlFile = new File(FILE_PATH);
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        if (xmlFile.exists()) {
            try {
                document = docBuilder.parse(xmlFile);
                document.normalize();
                root = document.getDocumentElement();
            } catch (IOException ioEx) {
                throw new RuntimeIoException(FILE_PATH + " - target file access error.", ioEx);
            } catch (SAXException saEx) {
                throw new RuntimeIoException(FILE_PATH + " - bad xml content.", saEx);
            }

        } else {
            document = docBuilder.newDocument();
            root = document.createElement(ROOT_ELEMENT);
            document.appendChild(root);
        }

    }

    /**
     * object adding to target file.
     * @param source
     * @return 
     */
    public boolean addObjToFile(Object source) {
        if (source != null) {
            Boolean completed = true;
            Map<String, String> childNodes = new HashMap<String, String>();
            for (Field field : source.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    childNodes.put(field.getName(), field.get(source).toString());
                    field.setAccessible(false);
                } catch (IllegalAccessException iaEx) {
                    completed = false;
                    break;
                }
            }
            if (childNodes.size() > 0) {
                writeNode(source.toString(), childNodes);
                return completed;
            }
        }
        return false;
    }

    private void writeNode(String name, Map<String, String> childNodes) {
        if (name != null) {
            Element currNode = document.createElement(name.replace("@", ""));
            for (String childName : childNodes.keySet()) {
                Element currChild = document.createElement(childName.replace("@", ""));
                Text childValue = document.createTextNode(childNodes.get(childName));
                currChild.appendChild(childValue);
                currNode.appendChild(currChild);
            }
            root.appendChild(currNode);
        }
    }

    /**
     * flush document.
     * @throws IllegalArgumentException
     * @throws TransformerException
     * @throws TransformerFactoryConfigurationError 
     */
    public void flush() throws IllegalArgumentException, TransformerException, TransformerFactoryConfigurationError {
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(xmlFile);
        DOMSource source = new DOMSource(document);
        trans.transform(source, result);

    }
}
