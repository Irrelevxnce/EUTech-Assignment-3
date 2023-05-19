package com.irrelevxnce.LoginSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import org.bouncycastle.*;
import org.bouncycastle.crypto.generators.BCrypt;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.irrelevxnce.WelcomePage;

public class LoginForm implements ActionListener{

	static HashMap<String, String> loginInfoL = new HashMap<>();
	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	MessageDigest md;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginForm window = new LoginForm(loginInfoL);
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm(HashMap<String, String> loginInfo) {
		loginInfoL = loginInfo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo.png")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 700, 500);
		frmLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
        frmLogin.getContentPane().setLayout(springLayout);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frmLogin.getContentPane().setLocation(dim.width / 2 - frmLogin.getContentPane().getSize().width / 2, dim.height / 2 - frmLogin.getContentPane().getSize().height / 2);
		JPanel outerPanel = new GradientPanel(new Color(65, 181, 144), new Color(90, 47, 150));
		springLayout.putConstraint(SpringLayout.NORTH, outerPanel, -463, SpringLayout.SOUTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, outerPanel, -686, SpringLayout.EAST, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, outerPanel, 0, SpringLayout.SOUTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, outerPanel, 0, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(outerPanel, BorderLayout.CENTER);
		SpringLayout sl_outerPanel = new SpringLayout();
		outerPanel.setLayout(sl_outerPanel);

		JPanel innerPanel = new JPanel();
		innerPanel.setOpaque(false);
		innerPanel.setFocusable(false);
		innerPanel.setForeground(new Color(0, 0, 0));
		sl_outerPanel.putConstraint(SpringLayout.NORTH, innerPanel, 42, SpringLayout.NORTH, outerPanel);
		sl_outerPanel.putConstraint(SpringLayout.WEST, innerPanel, 42, SpringLayout.WEST, outerPanel);
		sl_outerPanel.putConstraint(SpringLayout.SOUTH, innerPanel, -42, SpringLayout.SOUTH, outerPanel);
		sl_outerPanel.putConstraint(SpringLayout.EAST, innerPanel, -42, SpringLayout.EAST, outerPanel);
		innerPanel.setPreferredSize(new Dimension(600, 400));
		innerPanel.setName("innerPanel");
		springLayout.putConstraint(SpringLayout.SOUTH, innerPanel, 0, SpringLayout.SOUTH, frmLogin.getContentPane());
		innerPanel.setBorder(new RoundedBorder(new Color(1f,1f,1f,.5f ), 200, 40, true));
		innerPanel.setBackground(new Color(0f, 0f, 0f, 0f));
		outerPanel.add(innerPanel);

		SpringLayout sl_innerPanel = new SpringLayout();
		innerPanel.setLayout(sl_innerPanel);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBackground(new Color(0, 0, 0));
		sl_innerPanel.putConstraint(SpringLayout.NORTH, usernameLabel, -150, SpringLayout.NORTH, innerPanel);
		sl_innerPanel.putConstraint(SpringLayout.WEST, usernameLabel, -150, SpringLayout.WEST, innerPanel);
		sl_innerPanel.putConstraint(SpringLayout.EAST, usernameLabel, -180, SpringLayout.EAST, innerPanel);
		usernameLabel.setForeground(new Color(59, 59, 59));
		usernameLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 27));
		innerPanel.add(usernameLabel);

		textField = new JTextField();
		textField.setBorder(null);
		textField.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 20));
		sl_innerPanel.putConstraint(SpringLayout.NORTH, textField, 30, SpringLayout.SOUTH, usernameLabel);
		sl_innerPanel.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, usernameLabel);
		sl_innerPanel.putConstraint(SpringLayout.SOUTH, textField, -10, SpringLayout.SOUTH, innerPanel);
		sl_innerPanel.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, innerPanel);
		Color c = new Color(255, 255, 255);
		textField.setBackground(c);
		textField.setColumns(10);
		innerPanel.add(textField);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(new Color(59, 59, 59));
		passwordLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 27));
		sl_innerPanel.putConstraint(SpringLayout.NORTH, passwordLabel, 100, SpringLayout.NORTH, textField);
		sl_innerPanel.putConstraint(SpringLayout.WEST, passwordLabel, -150, SpringLayout.WEST, innerPanel);
		innerPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 20));
		sl_innerPanel.putConstraint(SpringLayout.SOUTH, passwordField, 155, SpringLayout.SOUTH, innerPanel);
		passwordField.setBorder(null);
		passwordField.setBackground(c);
		sl_innerPanel.putConstraint(SpringLayout.NORTH, passwordField, 30, SpringLayout.SOUTH, passwordLabel);
		sl_innerPanel.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, passwordLabel);
		sl_innerPanel.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, innerPanel);
		innerPanel.add(passwordField);

		NoScalingIcon img = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/EuTech_logo_s.png")));
		JLabel image = new JLabel(img);
		sl_innerPanel.putConstraint(SpringLayout.NORTH, image, -105, SpringLayout.NORTH, innerPanel);
		sl_innerPanel.putConstraint(SpringLayout.WEST, image, 200, SpringLayout.EAST, usernameLabel);
		sl_innerPanel.putConstraint(SpringLayout.SOUTH, image, 80, SpringLayout.SOUTH, innerPanel);
		sl_innerPanel.putConstraint(SpringLayout.EAST, image, 160, SpringLayout.EAST, innerPanel);
		image.setMinimumSize(new Dimension(50, 80));
		image.setBackground(new Color(1f,1f,1f,0f ));
		image.setOpaque(true);
		image.setSize(image.getIcon().getIconWidth(), image.getIcon().getIconHeight());
		innerPanel.add(image);

		JButton btnNewButton = new JButton("");
		sl_innerPanel.putConstraint(SpringLayout.EAST, btnNewButton, -30, SpringLayout.EAST, image);
		sl_innerPanel.putConstraint(SpringLayout.NORTH, btnNewButton, 0, SpringLayout.NORTH, passwordField);
		sl_innerPanel.putConstraint(SpringLayout.WEST, btnNewButton, 50, SpringLayout.EAST, passwordField);
		sl_innerPanel.putConstraint(SpringLayout.SOUTH, btnNewButton, 155, SpringLayout.SOUTH, innerPanel);
		NoScalingIcon enterIcon = new NoScalingIcon(new ImageIcon(LoginForm.class.getResource("/com/irrelevxnce/img/arrow_right_alt_FILL0_wght400_GRAD0_opsz48.png")));
		btnNewButton.setIcon(enterIcon);
		btnNewButton.setBorder(new RoundedBorder(new Color(1f,1f,1f,1f ), 3, 50, true));
		btnNewButton.setBackground(new Color(230, 92, 115));
		btnNewButton.setFocusable(false);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.addActionListener(this);
		innerPanel.add(btnNewButton);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String UID = textField.getText();
		String secret = String.valueOf(passwordField.getPassword());
		if (loginInfoL.containsKey(UID)) {
			String salt = "9Zf8RiQwOMdND9rK";
			byte[] encrypted = BCrypt.generate(secret.getBytes(), salt.getBytes(), 4);
			String encodedHash = Base64.getEncoder().encodeToString(encrypted);
			System.out.println(encodedHash);
			if (loginInfoL.get(UID).contains(encodedHash)) {
				System.out.println("bruh69");
				JFrame frame1 = new JFrame("Logged in successfully!");
				frame1.setPreferredSize(new Dimension(450, 100));
	            launchFrame(frame1, "Your credentials were found in the database. Close this window to continue.");
	            frame1.addWindowListener(new WindowAdapter(){
	                @Override
					public void windowClosing(WindowEvent e){
	                	frmLogin.dispose();
	                    WelcomePage welcome;
						try {
							welcome = new WelcomePage(UID);
							WelcomePage.main(null);
						} catch (SQLException | NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	            });
			}
			else {
				System.out.println("noBruh");
				JFrame frame1 = new JFrame("Login failed!");
				frame1.setPreferredSize(new Dimension(250, 100));
				launchFrame(frame1, "Please check your credentials.");
			}
		} else {
			JFrame frame1 = new JFrame("Login failed!");
			frame1.setPreferredSize(new Dimension(250, 100));
			launchFrame(frame1, "User doesn't exist.");
		}
	}
}
