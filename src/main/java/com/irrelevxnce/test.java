package com.irrelevxnce;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					test window = new test();
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
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame1 = new JFrame("View Inventory");
		frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setOpaque(true);
        JTextArea textArea1 = new JTextArea(50, 200);
        textArea1.setWrapStyleWord(true);
        textArea1.setEditable(false);
        textArea1.append("bruh");
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
	}

}
