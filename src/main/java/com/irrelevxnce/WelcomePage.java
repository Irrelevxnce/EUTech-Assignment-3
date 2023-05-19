package com.irrelevxnce;

import java.awt.Dimension;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFontFactory;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;

import com.irrelevxnce.LoginSystem.GradientPanel;
import com.irrelevxnce.LoginSystem.LoginForm;
import com.irrelevxnce.LoginSystem.NoScalingIcon;
import com.irrelevxnce.LoginSystem.RoundedBorder;

import javax.swing.SpringLayout;
import javax.swing.border.SoftBevelBorder;

import org.bouncycastle.crypto.generators.BCrypt;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;

public class WelcomePage {
	
	static String username;
	private JFrame frame;
	private String UID;
	private String secretToParse;
	private Boolean isAdministrator;
	EmbeddedDatabaseConnector edc = new EmbeddedDatabaseConnector();
	HashMap <Integer, String> items;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage window = new WelcomePage(username);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws NoSuchAlgorithmException 
	 */
	public WelcomePage(String username) throws SQLException, NoSuchAlgorithmException {
		this.username = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws NoSuchAlgorithmException 
	 */
	@SuppressWarnings("deprecation")
	private void initialize() throws SQLException, NoSuchAlgorithmException {
		edc.connect(false, false, false, null, null, null, null, null, null);
		items = edc.items;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
		frame.setTitle("EUTech Inventory System");
		frame.setBounds(100, 100, 700, 500);
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    frame.setSize((int) (screenWidth / 1.5), (int) (screenHeight / 1.5));
	    frame.setLocation(screenWidth / 6, screenHeight / 6);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel sidePanel = new GradientPanel(new Color(48, 48, 48), new Color(131, 92, 230));
		sidePanel.setVisible(false);
		sidePanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		springLayout.putConstraint(SpringLayout.NORTH, sidePanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, sidePanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, sidePanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, sidePanel, 275, SpringLayout.WEST, frame.getContentPane());
		sidePanel.setBackground(new Color(0, 64, 0));
		frame.getContentPane().add(sidePanel);
		SpringLayout sl_sidePanel = new SpringLayout();
		sidePanel.setLayout(sl_sidePanel);
		
		JPanel mainPanel = new GradientPanel(new Color(230, 92, 115), new Color(131, 92, 230));
		springLayout.putConstraint(SpringLayout.NORTH, mainPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, mainPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, mainPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, mainPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(mainPanel);
		SpringLayout sl_mainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);
		
		JButton menuButton = new JButton("");
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, menuButton, 60, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, menuButton, 60, SpringLayout.WEST, mainPanel);
		NoScalingIcon btnIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/menu_FILL0_wght400_GRAD0_opsz48.png")));
		menuButton.setIcon(btnIcon);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, menuButton, 10, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, menuButton, 10, SpringLayout.WEST, mainPanel);
		menuButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		menuButton.setBackground(new Color(230, 92, 115));
		menuButton.setFocusable(false);
		menuButton.setOpaque(false);
		menuButton.setContentAreaFilled(false);
		menuButton.addActionListener(e -> {
			sidePanel.setVisible(true);
			menuButton.setVisible(false);
		});
		mainPanel.add(menuButton);
		
		JButton addItemButton = new JButton("");
		addItemButton.setRolloverEnabled(false);
		NoScalingIcon btnAddIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/assignment_add_FILL0_wght400_GRAD0_opsz48.png")));
		addItemButton.setIcon(btnAddIcon);
		addItemButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		addItemButton.setBackground(new Color(230, 92, 115));
		addItemButton.setFocusable(false);
		addItemButton.setOpaque(false);
		addItemButton.setContentAreaFilled(false);
		addItemButton.addActionListener(e -> {
				try {
					if (edc.getAdminStatus(username)) {
						JFrame frame1 = new JFrame("Add Item");
					    frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
					    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					    JPanel panel1 = new JPanel();
					    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
					    panel1.setOpaque(true);
					    JPanel inputpanel = new JPanel();
					    inputpanel.setLayout(new FlowLayout());
					    JTextField name = new JTextField(30);
					    JTextField amount = new JTextField(4);
					    JTextField description = new JTextField(50);
					    JTextField price = new JTextField(4);
					    JLabel itemnameLabel = new JLabel("Item name: ");
					    inputpanel.add(itemnameLabel);
					    inputpanel.add(name);
					    
					    JLabel amountLabel = new JLabel("Amount: ");
					    inputpanel.add(amountLabel);
					    inputpanel.add(amount);
					    
					    JLabel descLabel = new JLabel("Description: ");
					    inputpanel.add(descLabel);
					    inputpanel.add(description);
					    
					    JLabel priceLabel = new JLabel("Price: ");
					    inputpanel.add(priceLabel);
					    inputpanel.add(price);
					    
					    JButton button = new JButton("Enter");
					    frame1.getRootPane().setDefaultButton(button);
					    button.addActionListener(z -> {
					    	String itemName = name.getText();
							String itemAmount = amount.getText();
							String itemDescription = description.getText();
							String itemPrice = price.getText();
							try {
								edc.connect(true, false, false, itemName, "", false, itemAmount, itemDescription, itemPrice);
							} catch (NoSuchAlgorithmException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    });
					    inputpanel.add(button);
						panel1.add(inputpanel);
				        frame1.getContentPane().add(BorderLayout.CENTER, panel1);
				        frame1.pack();
				        frame1.setLocationByPlatform(true);
				        frame1.setVisible(true);
				        frame1.setResizable(false);
				        name.requestFocus();
					} else {
						JFrame frame1 = new JFrame("Account status");
						frame1.setPreferredSize(new Dimension(550, 100));
						launchFrame(frame1, "Your account does not have administrator privileges. You are unable to access administrator controls.");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});
		mainPanel.add(addItemButton);
		
		JButton editItemButton = new JButton("");
		NoScalingIcon btnEditIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/edit_note_FILL0_wght400_GRAD0_opsz48.png")));
		editItemButton.setIcon(btnEditIcon);
		editItemButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		editItemButton.setBackground(new Color(230, 92, 115));
		editItemButton.setFocusable(false);
		editItemButton.setOpaque(false);
		editItemButton.setContentAreaFilled(false);
		editItemButton.addActionListener(e -> {
			try {
				if(edc.getAdminStatus(username)) {
					JFrame frame1 = new JFrame("Edit Item");
				    frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
				    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				    JPanel panel1 = new JPanel();
				    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
				    panel1.add(createVerticalStrut(10));
				    panel1.add(createHorizontalStrut(10));
				    panel1.setOpaque(true);
				    JPanel inputpanel = new JPanel();
				    inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.Y_AXIS));
				    JTextField id = new JTextField(5);
				    JLabel editLabel = new JLabel("Enter the ID of the item to edit: ");
				    inputpanel.add(editLabel);
				    inputpanel.add(id);
				    JTextField newName = new JTextField(15);
				    JLabel nameLabel = new JLabel("Enter new name for the item associated to the ID (Leave blank to keep previous value): ");
				    inputpanel.add(nameLabel);
				    inputpanel.add(newName);
				    JTextField amount = new JTextField(4);
				    JLabel amountLabel = new JLabel("Enter new amount for the item associated to the ID (Leave blank to keep previous value): ");
				    inputpanel.add(amountLabel);
				    inputpanel.add(amount);
				    JTextField desc = new JTextField(50);
				    JLabel descLabel = new JLabel("Enter new description for the item associated to the ID (Leave blank to keep previous value): ");
				    inputpanel.add(descLabel);
				    inputpanel.add(desc);
				    JTextField price = new JTextField(4);
				    JLabel priceLabel = new JLabel("Enter new price for the item associated to the ID (Leave blank to keep previous value): ");
				    inputpanel.add(priceLabel);
				    inputpanel.add(price);
				    JButton button = new JButton("Edit");
				    frame1.getRootPane().setDefaultButton(button);
				    button.addActionListener(d -> {
				    	String sql = "UPDATE Inventory SET";
				    	String finalSQL = null;
				    	String itemToEdit = id.getText();
				    	int itemId = Integer.parseInt(itemToEdit);
				    	try {
				    		boolean hasPreviousUpdate = true;
				    		System.out.println(items.get(itemId));
				    		String nameUpdate = newName.getText();
				    		String amountUpdate = amount.getText();
				    		String descUpdate = desc.getText();
				    		String priceUpdate = price.getText();
				    		try {
				    			if (!nameUpdate.isBlank()) {
				    				System.out.println("xd1");
				        			sql = sql.concat(" ITEMID = '" + nameUpdate.toUpperCase() + "',");
				        		} else {
				        			hasPreviousUpdate = false;
				        		}
				        		if (!amountUpdate.isBlank()) {
				        			System.out.println("xd2");
				        			sql = sql.concat(" AMOUNT = '" + amountUpdate + "',");
				        		} 
				        		if (!descUpdate.isBlank()) {
				        			System.out.println("xd3");
				        			sql = sql.concat(" DESCRIPTION = '" + descUpdate + "',");
				        		}
				        		if (!priceUpdate.isBlank()) {
				        			System.out.println("xd4");
				        			sql = sql.concat(" PRICE = '" + priceUpdate + "'");
				        		}
				        		if(sql.charAt(sql.length() - 1) == ',') {
				        			finalSQL = sql.substring(0, sql.length() - 1);
				        		} else {
				        			finalSQL = sql;
				        		}
				        		String [] tokens = items.get(itemId).split(",");
				        		String itemNameGetter = tokens[0].substring(4);
				        		finalSQL = finalSQL.concat(" WHERE ITEMID = '" + itemNameGetter + "'");
				        		System.out.println("asdasdas" + finalSQL);
				        		edc.connect(finalSQL);
				    		} catch(Exception e5) {
				    			System.out.println("Error at " + finalSQL);
				    		}
				    		
				    	} catch (Exception e2) {
				    		System.out.println("Item not found");
				    	}
				    });
				    inputpanel.add(button);
				    panel1.add(inputpanel);
				    frame1.add(panel1);
				    frame1.getContentPane().add(BorderLayout.CENTER, panel1);
				    frame1.pack();
				    frame1.setVisible(true);
				    frame1.setResizable(false);
				} else {
					JFrame frame1 = new JFrame("Account status");
					frame1.setPreferredSize(new Dimension(550, 100));
					launchFrame(frame1, "Your account does not have administrator privileges. You are unable to access administrator controls.");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		mainPanel.add(editItemButton);
		
		JButton deleteButton = new JButton("");
		NoScalingIcon btnDeleteIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/delete_FILL0_wght400_GRAD0_opsz48.png")));
		deleteButton.setIcon(btnDeleteIcon);
		deleteButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		deleteButton.setBackground(new Color(230, 92, 115));
		deleteButton.setFocusable(false);
		deleteButton.setOpaque(false);
		deleteButton.setContentAreaFilled(false);
		deleteButton.addActionListener(e -> {
			try {
				if(edc.getAdminStatus(username)) {
					JFrame frame1 = new JFrame("Delete Item");
				    frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
				    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				    JPanel panel1 = new JPanel();
				    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
				    panel1.setOpaque(true);
				    JPanel inputpanel = new JPanel();
				    inputpanel.setLayout(new FlowLayout());
				    JTextField itemId = new JTextField(15);
				    JLabel deleteLabel = new JLabel("Enter ID of the item to delete: ");
				    inputpanel.add(deleteLabel);
				    inputpanel.add(itemId);
				    JButton button = new JButton("Delete");
				    frame1.getRootPane().setDefaultButton(button);
					button.addActionListener(z -> {
				        int id = Integer.parseInt(itemId.getText());
						String [] tokens = items.get(id).split(",");
						String itemNameGetter = tokens[0].substring(4);
						String sql = "DELETE FROM Inventory WHERE ITEMID = '" + itemNameGetter + "'";
						try {
							edc.connect(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Deleted " + itemNameGetter);
					});
					inputpanel.add(button);
					panel1.add(inputpanel);
				    frame1.getContentPane().add(BorderLayout.CENTER, panel1);
				    frame1.pack();
				    frame1.setLocationByPlatform(true);
				    frame1.setVisible(true);
				    frame1.setResizable(false);
				    itemId.requestFocus();
				} else {
					JFrame frame1 = new JFrame("Account status");
					frame1.setPreferredSize(new Dimension(550, 100));
					launchFrame(frame1, "Your account does not have administrator privileges. You are unable to access administrator controls.");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		mainPanel.add(deleteButton);
		
		JButton viewButton = new JButton("");
		sl_mainPanel.putConstraint(SpringLayout.WEST, viewButton, 168, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, viewButton, -358, SpringLayout.SOUTH, mainPanel);
		NoScalingIcon viewIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/preview_FILL0_wght400_GRAD0_opsz48.png")));
		viewButton.setIcon(viewIcon);
		viewButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		viewButton.setBackground(new Color(230, 92, 115));
		viewButton.setFocusable(false);
		viewButton.setOpaque(false);
		viewButton.setRolloverEnabled(false);
		viewButton.setContentAreaFilled(false);
		viewButton.addActionListener(e -> {
			try {
				JFrame frame1 = new JFrame("View Inventory");
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel panel1 = new JPanel();
	            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	            panel1.setOpaque(true);
	            JTextArea textArea1 = new JTextArea(5, 50);
	            textArea1.setWrapStyleWord(true);
	            textArea1.setEditable(false);
	            for(int i = 1; i < items.size() + 1; i++) {
	            	textArea1.append((i) + ". " + items.get(i) + "\n");
	            }
	            JScrollPane scroller = new JScrollPane(textArea1);
	            scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	            scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	            JPanel inputpanel = new JPanel();
	            inputpanel.setLayout(new FlowLayout());
	            panel1.add(scroller);
	            panel1.add(inputpanel);
	            frame1.getContentPane().add(BorderLayout.CENTER, panel1);
	            frame1.pack();
	            frame1.setLocationByPlatform(true);
	            frame1.setVisible(true);
	            frame1.setResizable(false);
			} catch (Exception z) {
				z.printStackTrace();
			}
		});
		mainPanel.add(viewButton);
		
		JButton exportButton = new JButton("");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, exportButton, 131, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, exportButton, -170, SpringLayout.EAST, mainPanel);
		NoScalingIcon exportIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/export_notes_FILL0_wght400_GRAD0_opsz48.png")));
		exportButton.setIcon(exportIcon);
		exportButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		exportButton.setBackground(new Color(230, 92, 115));
		exportButton.setFocusable(false);
		exportButton.setOpaque(false);
		exportButton.setContentAreaFilled(false);
		exportButton.addActionListener(e -> {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = null;
			try {
			contentStream = new PDPageContentStream(document, page);
			PDType1Font font = PDType1Font.COURIER;
			contentStream.setFont(font, 12);
			contentStream.beginText();
			File f = new File(System.getProperty("user.home") + "/Documents/", "MostRecentReport.pdf");
			PDRectangle mediaBox = page.getMediaBox();
			float pageHeight = mediaBox.getHeight();
			float textHeight = PDType1Font.HELVETICA.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
			float y = pageHeight - textHeight - 50;
			contentStream.newLineAtOffset(30, 700);
			int lineCount = 0;
			for (int i = 1 ; i < items.size() + 1 ; i++) {	
				String[] lines = WordUtils.wrap(i + ". " + items.get(i).toString(), (int) y / 12).split("\\r?\\n");
				for (int j = 0 ; j < lines.length; j++) {
					if (lineCount >= 48) {
	                    contentStream.endText();
	                    contentStream.close();

	                    page = new PDPage();
	                    document.addPage(page);

	                    contentStream = new PDPageContentStream(document, page);
	                    contentStream.setFont(font, 12);
	                    contentStream.beginText();
	                    contentStream.newLineAtOffset(30, 700);
	                    lineCount = 0;
	                }
	                lineCount++;
					contentStream.showText(lines[j]);
					contentStream.moveTextPositionByAmount(0, - 12 * 1.2f);
				}
			}
			try {
				contentStream.close();
			} catch (Exception e3) {
				System.out.println("a");
			}
			document.save(f);
			document.close();
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		mainPanel.add(exportButton);
		
		JButton searchButton = new JButton("");
		sl_mainPanel.putConstraint(SpringLayout.WEST, exportButton, 168, SpringLayout.EAST, searchButton);
		sl_mainPanel.putConstraint(SpringLayout.EAST, viewButton, -168, SpringLayout.WEST, searchButton);
		sl_mainPanel.putConstraint(SpringLayout.WEST, searchButton, 448, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, searchButton, -450, SpringLayout.EAST, mainPanel);
		NoScalingIcon searchIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/find_in_page_FILL0_wght400_GRAD0_opsz48.png")));
		searchButton.setIcon(searchIcon);
		searchButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		searchButton.setBackground(new Color(230, 92, 115));
		searchButton.setFocusable(false);
		searchButton.setOpaque(false);
		searchButton.setContentAreaFilled(false);
		searchButton.addActionListener(e -> {
			JFrame frame1 = new JFrame("Search...");
		    frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
		    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    JPanel panel1 = new JPanel();
		    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		    panel1.setOpaque(true);
		    JPanel inputpanel = new JPanel();
		    inputpanel.setLayout(new FlowLayout());
		    JTextField name = new JTextField(15);
		    JLabel usernameLabel = new JLabel("Enter the name or ID of the item to search for: ");
	        inputpanel.add(usernameLabel);
	        inputpanel.add(name);
	        JButton button = new JButton("Search");
	        frame1.getRootPane().setDefaultButton(button);
	        button.addActionListener(d -> {
	        	String textBox = name.getText();
	        	Boolean isID = false;
	        	int id = 0;
	        	try {
	        		id = Integer.parseInt(textBox);
	        		isID = true;
	        	} catch (NumberFormatException z){
	        		
	        	}
		    	JFrame frame2 = new JFrame("Search Results");
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel panel2 = new JPanel();
		        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		        panel1.setOpaque(true);
		        JTextArea textArea1 = new JTextArea(5, 50);
		        textArea1.setWrapStyleWord(true);
		        textArea1.setEditable(false);
		        if (isID) {
		        	textArea1.append((id) + ". " + items.get(id) + "\n");
		        } else {
		        	for (int i = 1 ; i < items.size() + 1 ; i++) {
		        		if (items.get(i).contains(textBox.toUpperCase())) {
		        			id = i;
		        		}
		        	}
		        	textArea1.append("Item number: " + id + ". " + items.get(id) + "\n");
		        }
		        JScrollPane scroller = new JScrollPane(textArea1);
		        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		        JPanel displayItem = new JPanel();
		        displayItem.setLayout(new FlowLayout());
		        panel1.add(scroller);
		        panel1.add(displayItem);
		        frame1.getContentPane().add(BorderLayout.CENTER, panel1);
		        frame1.pack();
		        frame1.setVisible(true);
		        frame1.setResizable(false);
			}); 
		    inputpanel.add(button);
			panel1.add(inputpanel);
	        frame1.getContentPane().add(BorderLayout.CENTER, panel1);
	        frame1.pack();
	        frame1.setLocationByPlatform(true);
	        frame1.setVisible(true);
	        frame1.setResizable(false);
	        name.requestFocus();
		});
		mainPanel.add(searchButton);
		
		JLabel addLabel = new JLabel("Add item");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, addItemButton, 6, SpringLayout.SOUTH, addLabel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, addItemButton, 56, SpringLayout.SOUTH, addLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, addItemButton, 0, SpringLayout.EAST, addLabel);
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		addLabel.setForeground(new Color(255, 255, 255));
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, addLabel, -124, SpringLayout.SOUTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, addItemButton, 0, SpringLayout.WEST, addLabel);
		addLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addLabel.setLabelFor(addItemButton);
		mainPanel.add(addLabel);
		
		JLabel editLabel = new JLabel("Edit item");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, editItemButton, 6, SpringLayout.SOUTH, editLabel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, editItemButton, 56, SpringLayout.SOUTH, editLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, editItemButton, 0, SpringLayout.EAST, editLabel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, searchButton, -213, SpringLayout.NORTH, editLabel);
		editLabel.setForeground(new Color(255, 255, 255));
		editLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		sl_mainPanel.putConstraint(SpringLayout.WEST, editItemButton, 0, SpringLayout.WEST, editLabel);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, editLabel, 0, SpringLayout.NORTH, addLabel);
		editLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editLabel.setLabelFor(editItemButton);
		mainPanel.add(editLabel);
		
		JLabel deleteLabel = new JLabel("Delete item");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, deleteButton, 6, SpringLayout.SOUTH, deleteLabel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, deleteButton, 56, SpringLayout.SOUTH, deleteLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, deleteButton, 0, SpringLayout.EAST, deleteLabel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, exportButton, -213, SpringLayout.NORTH, deleteLabel);
		deleteLabel.setForeground(new Color(255, 255, 255));
		deleteLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		sl_mainPanel.putConstraint(SpringLayout.WEST, deleteButton, 0, SpringLayout.WEST, deleteLabel);
		deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, deleteLabel, 0, SpringLayout.NORTH, addLabel);
		deleteLabel.setLabelFor(deleteButton);
		mainPanel.add(deleteLabel);
		
		JLabel viewLabel = new JLabel("View item list");
		sl_mainPanel.putConstraint(SpringLayout.WEST, addLabel, 0, SpringLayout.WEST, viewLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, addLabel, 0, SpringLayout.EAST, viewLabel);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, viewButton, 6, SpringLayout.SOUTH, viewLabel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, viewLabel, 0, SpringLayout.WEST, viewButton);
		viewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewLabel.setForeground(new Color(255, 255, 255));
		viewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		viewLabel.setLabelFor(viewButton);
		mainPanel.add(viewLabel);
		
		JLabel exportLabel = new JLabel("Export");
		sl_mainPanel.putConstraint(SpringLayout.EAST, exportLabel, -170, SpringLayout.EAST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, deleteLabel, 0, SpringLayout.WEST, exportLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, deleteLabel, 0, SpringLayout.EAST, exportLabel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, exportLabel, -414, SpringLayout.SOUTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, viewLabel, 0, SpringLayout.NORTH, exportLabel);
		exportLabel.setForeground(new Color(255, 255, 255));
		exportLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		exportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exportLabel.setLabelFor(exportButton);
		mainPanel.add(exportLabel);
		
		JLabel searchLabel = new JLabel("Search");
		sl_mainPanel.putConstraint(SpringLayout.EAST, searchLabel, -450, SpringLayout.EAST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, exportLabel, 168, SpringLayout.EAST, searchLabel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, searchLabel, 448, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, editLabel, 0, SpringLayout.WEST, searchLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, editLabel, 0, SpringLayout.EAST, searchLabel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, searchLabel, -414, SpringLayout.SOUTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, viewLabel, -168, SpringLayout.WEST, searchLabel);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, searchButton, 6, SpringLayout.SOUTH, searchLabel);
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		searchLabel.setForeground(new Color(255, 255, 255));
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setLabelFor(searchButton);
		mainPanel.add(searchLabel);
		
		JLabel regularLabel = new JLabel("Regular user options:");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, regularLabel, 24, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, regularLabel, 228, SpringLayout.EAST, menuButton);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, regularLabel, -465, SpringLayout.SOUTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, regularLabel, -288, SpringLayout.EAST, mainPanel);
		regularLabel.setHorizontalAlignment(SwingConstants.CENTER);
		regularLabel.setForeground(new Color(255, 255, 255));
		regularLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		mainPanel.add(regularLabel);
		
		JLabel label = new JLabel("New label");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, label, 178, SpringLayout.SOUTH, regularLabel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, label, 315, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, label, 181, SpringLayout.SOUTH, regularLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, label, 315, SpringLayout.WEST, mainPanel);
		mainPanel.add(label);
		
		JLabel adminLabel = new JLabel("Administrator options:");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, adminLabel, 38, SpringLayout.SOUTH, label);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, adminLabel, -59, SpringLayout.NORTH, editLabel);
		adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
		adminLabel.setForeground(new Color(255, 255, 255));
		adminLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		sl_mainPanel.putConstraint(SpringLayout.WEST, adminLabel, 0, SpringLayout.WEST, regularLabel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, adminLabel, 0, SpringLayout.EAST, regularLabel);
		mainPanel.add(adminLabel);
		
		JButton closeButton = new JButton("");
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, closeButton, 60, SpringLayout.NORTH, sidePanel);
		sl_sidePanel.putConstraint(SpringLayout.EAST, closeButton, 60, SpringLayout.WEST, sidePanel);
		closeButton.setOpaque(false);
		NoScalingIcon closeBtnIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/close_FILL0_wght400_GRAD0_opsz48.png")));
		closeButton.setIcon(closeBtnIcon);
		sl_sidePanel.putConstraint(SpringLayout.NORTH, closeButton, 10, SpringLayout.NORTH, sidePanel);
		sl_sidePanel.putConstraint(SpringLayout.WEST, closeButton, 10, SpringLayout.WEST, sidePanel);
		closeButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		closeButton.setBackground(new Color(48, 48, 48));
		closeButton.setFocusable(false);
		closeButton.setOpaque(false);
		closeButton.setContentAreaFilled(false);
		closeButton.addActionListener(e -> {
			sidePanel.setVisible(false);
			menuButton.setVisible(true);
		});
		sidePanel.add(closeButton);
		
		JLabel welcomeLabel = new JLabel("Welcome back,");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_sidePanel.putConstraint(SpringLayout.NORTH, welcomeLabel, 6, SpringLayout.SOUTH, closeButton);
		sl_sidePanel.putConstraint(SpringLayout.WEST, welcomeLabel, 10, SpringLayout.WEST, sidePanel);
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, welcomeLabel, 35, SpringLayout.SOUTH, closeButton);
		sidePanel.add(welcomeLabel);
		
		JLabel nameLabel = new JLabel("username");
		sl_sidePanel.putConstraint(SpringLayout.EAST, welcomeLabel, 0, SpringLayout.EAST, nameLabel);
		sl_sidePanel.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, closeButton);
		nameLabel.setVerticalAlignment(SwingConstants.TOP);
		nameLabel.setText(username + "!");
		sl_sidePanel.putConstraint(SpringLayout.NORTH, nameLabel, 41, SpringLayout.SOUTH, closeButton);
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, nameLabel, -376, SpringLayout.SOUTH, sidePanel);
		sl_sidePanel.putConstraint(SpringLayout.EAST, nameLabel, 259, SpringLayout.WEST, sidePanel);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		sidePanel.add(nameLabel);
		
		JButton addButton = new JButton("");
		sl_sidePanel.putConstraint(SpringLayout.NORTH, addButton, -89, SpringLayout.SOUTH, sidePanel);
		sl_sidePanel.putConstraint(SpringLayout.WEST, addButton, 0, SpringLayout.WEST, welcomeLabel);
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, addButton, -10, SpringLayout.SOUTH, sidePanel);
		sl_sidePanel.putConstraint(SpringLayout.EAST, addButton, 0, SpringLayout.EAST, welcomeLabel);
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		NoScalingIcon addBtnIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/face_FILL0_wght400_GRAD0_opsz48.png")));
		addButton.setIcon(addBtnIcon);
		addButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		addButton.setBackground(new Color(48, 48, 48));
		addButton.setFocusable(false);
		addButton.setOpaque(false);
		addButton.addActionListener(e -> {
			try {
				if (edc.getAdminStatus(username)) {
					System.out.println("seks");
					JFrame frame1 = new JFrame("Add User");
				    frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
				    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				    JPanel panel1 = new JPanel();
				    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
				    panel1.setOpaque(true);
				    JPanel inputpanel = new JPanel();
				    inputpanel.setLayout(new FlowLayout());
				    JTextField name = new JTextField(15);
				    JPasswordField pass = new JPasswordField(20);
				    JCheckBox isAdmin = new JCheckBox();
				    JLabel usernameLabel = new JLabel("Username: ");
			        inputpanel.add(usernameLabel);
			        inputpanel.add(name);
			        
			        JLabel passwordLabel = new JLabel("Password: ");
			        inputpanel.add(passwordLabel);
			        inputpanel.add(pass);
			        
			        JLabel adminAccLabel = new JLabel("Administrator account:");
			        inputpanel.add(adminAccLabel);
			        inputpanel.add(isAdmin);
			        
				    JButton button = new JButton("Enter");
				    frame1.getRootPane().setDefaultButton(button);
				    button.addActionListener(d -> {
				        UID = name.getText();
				        secretToParse = String.valueOf(pass.getPassword());
				        isAdministrator = isAdmin.isSelected();
						try {
							String salt = "9Zf8RiQwOMdND9rK";
				    		byte[] encrypted = BCrypt.generate(secretToParse.getBytes(), salt.getBytes(), 4);
				    		String secret = Base64.getEncoder().encodeToString(encrypted);
							edc.connect(true, false, true, UID, secret, isAdministrator, "0", "", "0");
							frame1.dispose();
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("Error generating hash.");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}); 
				    inputpanel.add(button);
					panel1.add(inputpanel);
			        frame1.getContentPane().add(BorderLayout.CENTER, panel1);
			        frame1.pack();
			        frame1.setLocationByPlatform(true);
			        frame1.setVisible(true);
			        frame1.setResizable(false);
			        name.requestFocus();
				} else {
					JFrame frame1 = new JFrame("Account status");
					frame1.setPreferredSize(new Dimension(550, 100));
					launchFrame(frame1, "Your account does not have administrator privileges. You are unable to create a new user at this time.");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			});
	        
	        
		addButton.setContentAreaFilled(false);
        sidePanel.add(addButton);
		
		
		JLabel statusLabel = new JLabel("Administrator status: ");
		statusLabel.setForeground(new Color(255, 255, 255));
		sl_sidePanel.putConstraint(SpringLayout.NORTH, statusLabel, 6, SpringLayout.SOUTH, nameLabel);
		sl_sidePanel.putConstraint(SpringLayout.WEST, statusLabel, 0, SpringLayout.WEST, closeButton);
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, statusLabel, 40, SpringLayout.SOUTH, nameLabel);
		sl_sidePanel.putConstraint(SpringLayout.EAST, statusLabel, 0, SpringLayout.EAST, welcomeLabel);
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		sidePanel.add(statusLabel);
		
		JLabel status = new JLabel(edc.getAdminStatus(username).toString());
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setForeground(new Color(255, 255, 255));
		status.setFont(new Font("Tahoma", Font.PLAIN, 32));
		sl_sidePanel.putConstraint(SpringLayout.NORTH, status, 6, SpringLayout.SOUTH, statusLabel);
		sl_sidePanel.putConstraint(SpringLayout.WEST, status, 10, SpringLayout.WEST, sidePanel);
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, status, 56, SpringLayout.SOUTH, statusLabel);
		sl_sidePanel.putConstraint(SpringLayout.EAST, status, 259, SpringLayout.WEST, sidePanel);
		sidePanel.add(status);
		
		JLabel createLabel1 = new JLabel("Use the button below to");
		sl_sidePanel.putConstraint(SpringLayout.WEST, createLabel1, 0, SpringLayout.WEST, closeButton);
		createLabel1.setVerticalAlignment(SwingConstants.BOTTOM);
		createLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		createLabel1.setForeground(new Color(255, 255, 255));
		sl_sidePanel.putConstraint(SpringLayout.NORTH, createLabel1, 63, SpringLayout.SOUTH, status);
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, createLabel1, 113, SpringLayout.SOUTH, status);
		sl_sidePanel.putConstraint(SpringLayout.EAST, createLabel1, 0, SpringLayout.EAST, welcomeLabel);
		createLabel1.setBackground(new Color(255, 255, 255));
		createLabel1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		sidePanel.add(createLabel1);
		
		JLabel createLabel2 = new JLabel("create a new user.");
		sl_sidePanel.putConstraint(SpringLayout.NORTH, createLabel2, 6, SpringLayout.SOUTH, createLabel1);
		sl_sidePanel.putConstraint(SpringLayout.WEST, createLabel2, 0, SpringLayout.WEST, closeButton);
		sl_sidePanel.putConstraint(SpringLayout.SOUTH, createLabel2, 35, SpringLayout.SOUTH, createLabel1);
		sl_sidePanel.putConstraint(SpringLayout.EAST, createLabel2, 0, SpringLayout.EAST, welcomeLabel);
		createLabel2.setVerticalAlignment(SwingConstants.TOP);
		createLabel2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		createLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		createLabel2.setForeground(new Color(255, 255, 255));
		sidePanel.add(createLabel2);
	
	}
	
	public void launchFrame (JFrame frame1, String textToShow) {
		frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
        frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JLabel errLabel = new JLabel(textToShow);
        errLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
        errLabel.setForeground(Color.WHITE);
        frame1.getContentPane().add(errLabel);
        errLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
        errLabel.setForeground(Color.BLACK);
        errLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame1.pack();
        frame1.setLocationRelativeTo(frame1);
        frame1.setVisible(true);
        frame1.setResizable(false);
	}
	
	private static Component createVerticalStrut(int height) {
        return Box.createRigidArea(new Dimension(0, height));
    }
		
	private static Component createHorizontalStrut(int width) {
        return Box.createRigidArea(new Dimension(width, 0));
    }
}

