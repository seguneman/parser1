

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class XmlParser {
    private String xmlString = "";
    private File xmlFile = null;
    private Document doc = null;


    public static void main(String[] args) {
        String xmlString = "";
        File file = new File("C:\\Users\\chopin\\workspace\\DomParser5\\src\\police.xml");
        XmlParser xmlParser = new XmlParser(xmlString);
        xmlParser = new XmlParser(file);
        
        Map xmlMap = xmlParser.parseXML();
        System.out.println( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        print(xmlMap, 0);
    }
    
    public XmlParser(String xmlString) {
        this.xmlString = xmlString;
    }
    
    public XmlParser(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    public Map parseXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            if (this.xmlFile != null) {
                doc = dBuilder.parse(this.xmlFile);
            }
            else {
                doc = dBuilder.parse( new ByteArrayInputStream(xmlString.getBytes()) );
            }

            doc.getDocumentElement().normalize();

            NodeList resultNode = doc.getChildNodes();

            HashMap result = new HashMap();
            XmlParser.MyNodeList tempNodeList = new XmlParser.MyNodeList();

            String emptyNodeName = null, emptyNodeValue = null;

            for(int index = 0; index < resultNode.getLength(); index ++) {
                Node tempNode = resultNode.item(index);
                if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                    tempNodeList.addNode(tempNode);
                }
                emptyNodeName = tempNode.getNodeName();
                emptyNodeValue = tempNode.getNodeValue();
            }

            if (tempNodeList.getLength() == 0 && emptyNodeName != null
                    && emptyNodeValue != null) {
                result.put(emptyNodeName, emptyNodeValue);
                return result;
            }

            //this.parseXMLNode(tempNodeList, result);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
/*
    private void parseXMLNode(NodeList nList, HashMap result) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE
                    && nNode.hasChildNodes()
                    && nNode.getFirstChild() != null
                    && (nNode.getFirstChild().getNextSibling() != null
                    || nNode.getFirstChild().hasChildNodes())) {
                NodeList childNodes = nNode.getChildNodes();
                XmlParser.MyNodeList tempNodeList = new XmlParser.MyNodeList();
                for(int index = 0; index < childNodes.getLength(); index ++) {
                    Node tempNode = childNodes.item(index);
                    if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                        tempNodeList.addNode(tempNode);
                    }
                }
                HashMap dataHashMap = new HashMap();
                if (result.containsKey(nNode.getNodeName()) && result.get(nNode.getNodeName()) instanceof List) {
                    List mapExisting = (List) result.get(nNode.getNodeName());
                    mapExisting.add(dataHashMap);
                } else if(result.containsKey(nNode.getNodeName())) {
                    List counterList = new ArrayList();
                    counterList.add(result.get(nNode.getNodeName()));
                    counterList.add(dataHashMap);
                    result.put(nNode.getNodeName(), counterList);
                } else {
                    result.put(nNode.getNodeName(), dataHashMap);
                }
                if (nNode.getAttributes().getLength() > 0) {
                    Map attributeMap = new HashMap();
                    for(int attributeCounter = 0;
                        attributeCounter < nNode.getAttributes().getLength();
                        attributeCounter++) {
                        attributeMap.put(
                                nNode.getAttributes().item(attributeCounter).getNodeName(),
                                nNode.getAttributes().item(attributeCounter).getNodeValue()
                        );
                    }
                    dataHashMap.put("attributes", attributeMap);
                }
                this.parseXMLNode(tempNodeList, dataHashMap);
            } else if (nNode.getNodeType() == Node.ELEMENT_NODE
                    && nNode.hasChildNodes() && nNode.getFirstChild() != null
                    && nNode.getFirstChild().getNextSibling() == null) {
                this.putValue(result, nNode);
            } else if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                this.putValue(result, nNode);
            }
        }
    }

    private void putValue(HashMap result, Node nNode) {
        HashMap attributeMap = new HashMap();
        Object nodeValue = null;
        if(nNode.getFirstChild() != null) {
            nodeValue = nNode.getFirstChild().getNodeValue();
            if(nodeValue != null) {
                nodeValue = nodeValue.toString().trim();
            }
        }
        HashMap nodeMap = new HashMap();
        nodeMap.put("value", nodeValue);
        Object putNode = nodeValue;
        if (nNode.getAttributes().getLength() > 0) {
            for(int attributeCounter = 0;
                attributeCounter < nNode.getAttributes().getLength();
                attributeCounter++) {
                attributeMap.put(
                        nNode.getAttributes().item(attributeCounter).getNodeName(),
                        nNode.getAttributes().item(attributeCounter).getNodeValue()
                );
            }
            nodeMap.put("attributes", attributeMap);
            putNode = nodeMap;
        }
        if (result.containsKey(nNode.getNodeName()) && result.get(nNode.getNodeName()) instanceof List) {
            List mapExisting = (List) result.get(nNode.getNodeName());
            mapExisting.add(putNode);
        } else if(result.containsKey(nNode.getNodeName())) {
            List counterList = new ArrayList();
            counterList.add(result.get(nNode.getNodeName()));
            counterList.add(putNode);
            result.put(nNode.getNodeName(), counterList);
        } else {
            result.put(nNode.getNodeName(), putNode);
        }
    }
*/
    class MyNodeList implements NodeList {
        List<Node> nodes = new ArrayList<Node>();
        int length = 0;
        public MyNodeList() {}

        public void addNode(Node node) {
            nodes.add(node);
            length++;
        }

        @Override
        public Node item(int index) {
            try {
                return nodes.get(index);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        public int getLength() {
            return length;
        }
    }
    
    private static void print(Map map, Integer tab) {
        Iterator it = map.entrySet().iterator();
   
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String key = pairs.getKey().toString();
            Object value = pairs.getValue();
            if (value instanceof Map) {
                System.out.println(getTab(tab)+ "<"+ key +">@");
                print((Map) value, tab + 1);
                System.out.println(getTab(tab)+ "</"+key+ ">");
            }
            else if (value instanceof List) {
                System.out.println(getTab(tab) +"<"+ key +">$");
                print((List) value, tab + 1);
                System.out.println(getTab(tab) + "</"+key+">");
            }
            else {
                System.out.println(getTab(tab) +"<"+ key + ">" + value+"</"+key+">");
            }
        }
    }

    private static void print(List list, Integer tab) {
        for (Integer index = 0; index < list.size(); index++) {
            Object value = list.get(index);
            if (value instanceof Map) {
               // System.out.println(getTab(tab) + index.toString() + ": {");
                print((Map) value, tab + 1);
              // System.out.println(getTab(tab) + "}");
            }
            else if (value instanceof List) {
                print((List) value, tab + 1);
            }
            else {
               // System.out.println(getTab(tab) + index.toString() + ": " + value);
            }
        }
    }

    public static String getTab(Integer tab) {
        String string = "";
        for (Integer index = 0; index < tab; index++) {
            string += "    ";
        }
        return string;
    }
}