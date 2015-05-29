package purchase;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import employee.Employee;
import static purchase.XmlUtil.asList;

public class PurchaseParser {
    
	public Purchase purchase;
	public List<Purchase> purchases;
    public List<Purchase> getData(String filePath) throws ParserConfigurationException,
            SAXException, IOException {
    	
    	
    	
        //--------Document builder
        DocumentBuilderFactory builderFactory;
        DocumentBuilder builder;
       
        builderFactory = DocumentBuilderFactory.newInstance();
        builder = builderFactory.newDocumentBuilder();
        //--------Document builder
        
        //---------validation
        File myxmlfile = new File(filePath);
        
        
        File myschemafile = new File("src/purchase/po.xsd");
        //Line below is for the path to be working on my computer
        //File myschemafile = new File("SysInt_XML/src/purchase/po.xsd");
        
        Schema schema = null;
        //---------validation
         
        try
        {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(myschemafile);
            // Parse the XML document to DOM Document...
            Document doc = builder.parse(myxmlfile);
            DOMSource source = new DOMSource(doc);
            
            // ... and then validate it!
            Validator validator = schema.newValidator();
            validator.validate(source);
        }
        catch (Exception e)
        {
            System.out.println("Exception throwed.");
            e.printStackTrace();
        }
        
        
        // Load the input XML document, parse it and return an instance of the
        // Document class.
        Document document = builder.parse(myxmlfile);

        purchases = new ArrayList<Purchase>();
        List<Item> items = new ArrayList<Item>();

        // Get all the nodes
        NodeList nodeList = document.getDocumentElement().getChildNodes(); // 7 child nodes!
        
        Element rootElement = document.getDocumentElement();

        String orderDate = rootElement.getAttributes().getNamedItem("orderDate").getNodeValue();
        String shipCountry = nodeList.item(1).getAttributes().getNamedItem("country").getNodeValue();
        String billCountry = nodeList.item(3).getAttributes().getNamedItem("country").getNodeValue();
        
        NodeList shippingList = nodeList.item(1).getChildNodes(); // 11 child nodes
            Node shipNameNode = shippingList.item(1);
            Node shipStreetNode = shippingList.item(3);
            Node shipCityNode = shippingList.item(5);
            Node shipStateNode = shippingList.item(7);
            Node shipZipNode = shippingList.item(9);
            
        NodeList billingList = nodeList.item(3).getChildNodes(); // 11 child nodes
            Node billNameNode = billingList.item(1);
            Node billStreetNode = billingList.item(3);
            Node billCityNode = billingList.item(5);
            Node billStateNode = billingList.item(7);
            Node billZipNode = billingList.item(9);
            
        NodeList itemList = nodeList.item(5).getChildNodes(); // items
        
        System.out.println("itemlist: " + itemList);
        
        //some of this seems weird.. My console prints "5 \n [#text: \n ] \n [item: null]....."
        //writes a bit more, but nothing of value there. (\n = newline)
            System.out.println(itemList.getLength());
            System.out.println("before loop");
            	for(Node n: asList(itemList)) {
            		System.out.println(n);   
            	}
            System.out.println("after loop");
            System.out.println(shippingList.item(0).getNodeValue());
            System.out.println("----------------------");


        // First for loop - this is gonna get messy!
        for (int i = 0; i < itemList.getLength(); i++) {
            Node node = itemList.item(i);
          
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) node;
                String partNum = node.getAttributes().getNamedItem("partNum").getNodeValue();
                String productName = elem.getElementsByTagName("productName").item(0)
                                        .getChildNodes().item(0).getNodeValue();
                String quantity = elem.getElementsByTagName("quantity").item(0)
                                        .getChildNodes().item(0).getNodeValue();
                String USPrice = elem.getElementsByTagName("USPrice").item(0)
                                        .getChildNodes().item(0).getNodeValue();
                String comment = null;
                try {
                    comment = elem.getElementsByTagName("comment").item(0)
                            .getChildNodes().item(0).getNodeValue();
                } catch (NullPointerException nullPointerException) {
                }
                
                String shipDate = null;
                try {
                    shipDate = elem.getElementsByTagName("shipDate").item(0)
                            .getChildNodes().item(0).getNodeValue();
                } catch (NullPointerException nullPointerException) {
                }
                
                items.add(new Item(productName, quantity, USPrice, comment, shipDate));
            }
           
        }
          
        // Store the purchase-prase
          purchases.add(new Purchase(orderDate, shipCountry, shipNameNode.getTextContent(), 
                  shipStreetNode.getTextContent(), shipCityNode.getTextContent(), shipStateNode.getTextContent(), 
                  shipZipNode.getTextContent(), billCountry, billNameNode.getTextContent(), billStreetNode.getTextContent(),
                  billCityNode.getTextContent(), billStateNode.getTextContent(), billZipNode.getTextContent(), items));  
        return purchases;
    }
    
    public String searching(String searchTxt)
    {
    	int counts = 0;
    	boolean number = false;
    	int searchInt = 0;
    	try {
            searchInt = Integer.parseInt(searchTxt);
            number = true;
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
    	if (!number)
    	{
    		for (int i = 0; i < purchases.size(); i++)
    		{
    			if (purchases.get(i).getBillCity().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getBillName().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getBillState().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getBillStreet().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getBillToCountry().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getBillZip().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getOrderDate().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getShipCity().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getShipName().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getShipState().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getShipStreet().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getShipToCountry().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}
    			
    			if (purchases.get(i).getShipZip().equalsIgnoreCase(searchTxt))
    			{
    				counts++;
    			}

        		for (int j = 0; j < purchases.get(i).getItems().size(); j++)
        		{
        			try {
        			if (purchases.get(i).getItems().get(j).getComment().equalsIgnoreCase(searchTxt))
        			{
        				counts++;
        			}
        			if (purchases.get(i).getItems().get(j).getPrice().equalsIgnoreCase(searchTxt))
        			{
        				counts++;
        			}
        			if (purchases.get(i).getItems().get(j).getProductName().equalsIgnoreCase(searchTxt))
        			{
        				counts++;
        			}
        			if (purchases.get(i).getItems().get(j).getQuantity().equalsIgnoreCase(searchTxt))
        			{
        				counts++;
        			}
        			if (purchases.get(i).getItems().get(j).getShipDate().equalsIgnoreCase(searchTxt))
        			{
        				counts++;
        			}
        			} catch (NullPointerException e)
        			{
        				System.out.println("nullpointer in purchaseparser at line 244. some of the entries in this inner loop might be empty");
        				//e.printStackTrace();
        			}
        		}
    		}
    		
    	} else
    	{
    		//Handle for numbers. Not relevant, since only strings are being used in this class. 
    		//Also means that this class doesn't really support searching for numbers
    	}
    	
    	return "Number of occurences: " + counts;
    }
}