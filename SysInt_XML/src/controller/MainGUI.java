package controller;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.lang.model.element.Element;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import employee.*;
import purchase.*;

public class MainGUI {

	private JFrame frame;
	private JRadioButton httpButton, localButton;
	private ButtonGroup group, parserGroup;
	//private JTextField httpInputPath;
	private JPanel panel;
	private JTextField textField;
	public EmployeeParser empParser;
	public PurchaseParser purParser;
	private JPanel httpContainer;
	private JButton goHttp;
	private JPanel parserContainer;
	private JRadioButton empRadio;
	private JRadioButton purRadio;
	private JRadioButton bbrRadio;
	private JPanel newContainer;
	private JPanel tableContainer;
	private JPanel treeContainer;
	private JTable table;
	private TableModel tm;
	private JScrollPane scrollPane;
	private JScrollPane consoleScrollPane;
	private JTextArea consoleTextArea;
	private JTree tree;
	private JScrollPane treeScrollPane;
	private JLabel search;
	private JTextField searchTxt;
	private JButton searchBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private String getDate() {
		Date date = new Date();
		String timestamp = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		
		return timestamp;
	}
	
	private void clearTable() {
		tableContainer.removeAll();
		tableContainer.revalidate();

	}
	
	private void clearTree() {
		treeContainer.removeAll();
		treeContainer.revalidate();

	}
	
	private void initializeTable(List list){
		clearTable();
		tm = new TableModel();
		List<Employee> employeeList = (List<Employee>) list;
		
		for (Employee emp : employeeList)
		 tm.addRow(Arrays.asList(emp.getID(), emp.getFirstname(), emp.getLastname(), emp.getAge(), emp.getSalary()));
		
		table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 800, 150);
		tableContainer.add(scrollPane);
		
		frame.getContentPane().add(tableContainer);

	}
	
	private void initializeTree(){
		clearTree();
		// Get employee entities
		//List<Employee> employeeList = (List<Employee>) list;
		
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        //create the tree by passing in the root node
        tree = new JTree(root);
        tree.setSize(600, 150);
        treeContainer.add(tree);
        
		treeScrollPane = new JScrollPane(tree);
		treeScrollPane.setBounds(0, 0, 600, 150);
		treeContainer.add(treeScrollPane);
        
		frame.getContentPane().add(treeContainer);
		
		search = new JLabel("Search: ");
		search.setBounds(10, 517, 49, 16);
		frame.getContentPane().add(search);
		searchTxt = new JTextField(10);
		searchTxt.setBounds(55, 511, 150, 28);
		frame.getContentPane().add(searchTxt);
		searchBtn = new JButton("Search");
		searchBtn.setBounds(203, 512, 85, 29);
		frame.getContentPane().add(searchBtn);
		
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (empRadio.isSelected())
				{
					System.out.println(tm.searching(searchTxt.getText()));
					consoleTextArea.append(tm.searching(searchTxt.getText()) + "\n");
				} else if (purRadio.isSelected())
				{
					System.out.println(purParser.searching(searchTxt.getText()));
					consoleTextArea.append(purParser.searching(searchTxt.getText()) + "\n");
				}
			}
		});
		
	}
	
	private void updateEmployeeTree(List list){
		
		System.out.println("in updateTree method!");
		List<Employee> employeeList = (List<Employee>) list;

		clearTree();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		tree = new JTree(root);
	    tree.setSize(600, 150);
	    treeContainer.add(tree);
	    
	    treeScrollPane = new JScrollPane(tree);
		treeScrollPane.setBounds(0, 0, 600, 150);
		treeContainer.add(treeScrollPane);
        //create the root node

	    	for (Employee emp : employeeList)
			{
		        DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Employee");		

	    		
		        DefaultMutableTreeNode idNode = new DefaultMutableTreeNode(emp.getID());
		        DefaultMutableTreeNode fNameNode = new DefaultMutableTreeNode(emp.getFirstname());
		        DefaultMutableTreeNode lName = new DefaultMutableTreeNode(emp.getLastname());
		        DefaultMutableTreeNode age = new DefaultMutableTreeNode(emp.getAge());
		        DefaultMutableTreeNode salary = new DefaultMutableTreeNode(emp.getSalary());

		        employeeNode.add(idNode);
		        employeeNode.add(fNameNode);
		        employeeNode.add(lName);
		        employeeNode.add(age);
		        employeeNode.add(salary);
		        
		        root.add(employeeNode);
			}
	    tree.revalidate();
		frame.getContentPane().add(treeContainer);
		
		
	}
	
private void updatePurchaseTree(List list){
		
		List<Purchase> purchaseList = (List<Purchase>) list;

		clearTree();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		tree = new JTree(root);
	    tree.setSize(600, 150);
	    treeContainer.add(tree);
	    
	    treeScrollPane = new JScrollPane(tree);
		treeScrollPane.setBounds(0, 0, 600, 150);
		treeContainer.add(treeScrollPane);
        //create the root node

	    	for (Purchase pur : purchaseList)
			{
		        DefaultMutableTreeNode purchaseNode = new DefaultMutableTreeNode("Purchase Order");		
		        DefaultMutableTreeNode shipToNode = new DefaultMutableTreeNode("Ship to");	
		        DefaultMutableTreeNode billToNode = new DefaultMutableTreeNode("Bill to");	
		        DefaultMutableTreeNode itemsNode = new DefaultMutableTreeNode("Items");	


		        DefaultMutableTreeNode orderDateNode = new DefaultMutableTreeNode(pur.getOrderDate());
		        DefaultMutableTreeNode shipCountry = new DefaultMutableTreeNode(pur.getShipToCountry());
		        DefaultMutableTreeNode shipName = new DefaultMutableTreeNode(pur.getShipName());
		        DefaultMutableTreeNode shipStreet = new DefaultMutableTreeNode(pur.getShipStreet());
		        DefaultMutableTreeNode shipCity = new DefaultMutableTreeNode(pur.getShipCity());
		        DefaultMutableTreeNode shipState = new DefaultMutableTreeNode(pur.getShipState());
		        DefaultMutableTreeNode shipZip = new DefaultMutableTreeNode(pur.getShipZip());
		        
		        DefaultMutableTreeNode billCountry = new DefaultMutableTreeNode(pur.getBillToCountry());
		        DefaultMutableTreeNode billName = new DefaultMutableTreeNode(pur.getBillName());
		        DefaultMutableTreeNode billStreet = new DefaultMutableTreeNode(pur.getBillStreet());
		        DefaultMutableTreeNode billCity = new DefaultMutableTreeNode(pur.getBillCity());
		        DefaultMutableTreeNode billState = new DefaultMutableTreeNode(pur.getBillState());
		        DefaultMutableTreeNode billZip = new DefaultMutableTreeNode(pur.getBillZip());
		        
		        DefaultMutableTreeNode items = new DefaultMutableTreeNode(pur.getItems());
		        

		        shipToNode.add(shipCountry);
		        shipToNode.add(shipName);
		        shipToNode.add(shipStreet);
		        shipToNode.add(shipCity);
		        shipToNode.add(shipState);
		        shipToNode.add(shipZip);
		      
		        billToNode.add(billCountry);
		        billToNode.add(billName);
		        billToNode.add(billStreet);
		        billToNode.add(billCity);
		        billToNode.add(billState);
		        billToNode.add(billZip);
		        
		        for (Item itm : pur.getItems())
		        {
		        	DefaultMutableTreeNode item = new DefaultMutableTreeNode(itm.getProductName());
		        	DefaultMutableTreeNode itemQuantity = new DefaultMutableTreeNode(itm.getQuantity());
		        	DefaultMutableTreeNode itemPrice = new DefaultMutableTreeNode(itm.getPrice());
		        	DefaultMutableTreeNode itemComment = new DefaultMutableTreeNode(itm.getComment());
		        	DefaultMutableTreeNode itemShipDate = new DefaultMutableTreeNode(itm.getShipDate());
		        	
		        	item.add(itemQuantity);
		        	item.add(itemPrice);
		        	item.add(itemQuantity);
		        	item.add(itemComment);
		        	item.add(itemShipDate);
		            itemsNode.add(item);
		        }
		        purchaseNode.add(orderDateNode);
		        purchaseNode.add(shipToNode);
		        purchaseNode.add(billToNode);
		        purchaseNode.add(itemsNode);
		        root.add(purchaseNode);
			}
	    tree.revalidate();
		frame.getContentPane().add(treeContainer);
		
		
	}

	
	private void initializeConsole(){
		consoleTextArea = new JTextArea(1, 10);
        consoleTextArea.setWrapStyleWord(true);
        consoleTextArea.setLineWrap(true);      
        consoleTextArea.setEditable(false);
		consoleScrollPane = new JScrollPane();
		consoleScrollPane.setBorder(
	            BorderFactory.createTitledBorder("Console"));
		consoleScrollPane.setBounds(0, 80, 800, 100);
		consoleScrollPane.setViewportView(consoleTextArea);
		panel.add(consoleScrollPane);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(10, 10, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Group the radio buttons.
		group = new ButtonGroup();
		parserGroup = new ButtonGroup();
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 12, 798, 167);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		httpContainer = new JPanel();
		httpContainer.setBounds(0, 6, 399, 68);
		panel.add(httpContainer);
		httpContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		httpButton = new JRadioButton("HTTP");
		httpContainer.add(httpButton);
		httpButton.setSelected(true);
		group.add(httpButton);
		textField = new JTextField();
		httpContainer.add(textField);
		textField.setColumns(10);

		parserContainer = new JPanel();
		parserContainer.setBounds(401, 6, 399, 33);
		parserContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		newContainer = new JPanel();
		newContainer.setBounds(399, 200, 399, 283);
		newContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		empRadio = new JRadioButton("Emp");
		parserContainer.add(empRadio);
		empRadio.setSelected(true);

		purRadio = new JRadioButton("Pur");
		parserContainer.add(purRadio);

		bbrRadio = new JRadioButton("BBR");
		parserContainer.add(bbrRadio);
		
		/*
		JLabel search;
		private JTextField searchTxt;
		*/
		parserGroup.add(empRadio);
		parserGroup.add(purRadio);
		parserGroup.add(bbrRadio);
		
		goHttp = new JButton("Go!");
		httpContainer.add(goHttp);

		localButton = new JRadioButton("Local");
		httpContainer.add(localButton);
		group.add(localButton);
		panel.add(parserContainer);
		
		tableContainer = new JPanel();
		tableContainer.setLayout(null);
		tableContainer.setBounds(0, 200, 800, 150);
		
		treeContainer = new JPanel();
		treeContainer.setLayout(null);
		treeContainer.setBounds(0, 355, 800, 150);

		
		initializeConsole();
		initializeTree();

		localButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isset = localButton.isSelected();
				if (isset = true) {
					textField.setEditable(false);
					JFileChooser inputFile = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"XML-files", "xml");
					inputFile.setFileFilter(filter);
					int returnVal = inputFile.showOpenDialog(inputFile);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = inputFile.getSelectedFile();
						try {

							if (empRadio.isSelected()) {
								// Call employee parser
								empParser = new EmployeeParser();
								List<Employee> empList = (List<Employee>) empParser.getData(f.getPath());
								consoleTextArea.append(getDate() + " : " +empList.size() + " employees have been loaded \n");
								updateEmployeeTree(empList);
								initializeTable(empList);
							} else if (purRadio.isSelected()) {
								// Call purchase parser
								purParser = new PurchaseParser();
								List<Purchase> purList = (List<Purchase>) purParser.getData(f.getPath());
								for (Purchase pur : purList)
								consoleTextArea.append(purList.size() + " purchase have been loaded \n");
							    updatePurchaseTree(purList);
							    clearTable();
					

							} else if (bbrRadio.isSelected()) {
								// Call bbrParser
								JOptionPane.showMessageDialog(frame,
										"This parser is not yet implemented.");
							}

						} catch (Exception ex) {
							System.err.println("No file chosen!");
							ex.printStackTrace();
							JOptionPane.showMessageDialog(frame,
									"Terrible error! The file is not valid.");
						}
					}
				}
			}
		});
		
		goHttp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				boolean isset = httpButton.isSelected();
				if (isset = true) {
					textField.setEditable(true);
					String httpInput = textField.getText();
					
					/*
					 * Attempt at getting from internet. Still need to write in the field on the GUI
					 * */
					
					/*
					String uri = "http://www46.zippyshare.com/d/Wy8Gs2zS/574427/Employees.xml";

					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");

					InputStream xml = connection.getInputStream();

					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document doc = db.parse(xml);
*/
					try {
						System.out.println("Trying");
						//URL url = new URL(httpInput);
						//System.out.println(url.toString() + " ,");

						URL url = new URL(httpInput);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod("GET");
						connection.setRequestProperty("Accept", "application/xml");
						InputStream xml = connection.getInputStream();
						System.out.println("Finding parser");
						
						if (empRadio.isSelected()) {
							// Call employee parser
							System.out.println("emp it is");
							System.out.println(xml);
							empParser = new EmployeeParser();
							
							//passing InputParse to EmployeeParser class, maybe something else?
							List<Employee> empList = (List<Employee>) empParser.getOnlineData(xml);
							consoleTextArea.append(getDate() + " : " +empList.size() + " employees have been loaded \n");
							updateEmployeeTree(empList);
							initializeTable(empList);

						} else if (purRadio.isSelected()) {
							// Call purchase parser
							purParser = new PurchaseParser();
							System.out.println("pur it is");
						} else if (bbrRadio.isSelected()) {
							// Call bbrParser
							System.out.println("BBR. is selected");
							
						}
						System.out.println("Should have parsed..");
					} catch (MalformedURLException e) {
						// it wasn't a URL
						JOptionPane
								.showMessageDialog(frame,
										"Must have the following format: http://[...].*");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		httpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isset = httpButton.isSelected();
				if (isset = true) {
					textField.setEditable(true);
				}
			}
		});
	}
}