package gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DBM;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ShowStock{

	private JPanel contentPane;
JFrame stockframe;
private JTable table;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ShowStock() throws IOException {
		stockframe = new JFrame();
		stockframe.setResizable(false);
		stockframe.setTitle("Show Stock");
		stockframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		stockframe.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		stockframe.setContentPane(contentPane);
		String imagePath = "res/showstock.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath);
		//BufferedImage myImg = ImageIO.read(imgStream);
		//stockframe.setIconImage(myImg);
				
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(new DBM().selectQueryForTable("select * from stock"));
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
	}

}
