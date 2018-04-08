package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.DBM;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStock{

	private JPanel contentPane;
	JFrame stockframe;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	DBM db=new DBM();
/**
	 * Launch the application.
	

	/**
	 * Create the frame.
 * @throws IOException 
	 */
	public AddStock() throws IOException {
		stockframe = new JFrame();
		stockframe.setTitle("Add Stock");
		stockframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		stockframe.setBounds(100, 100, 380, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		stockframe.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		String imagePath = "res/addstock.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath );
		//BufferedImage myImg = ImageIO.read(imgStream);
		//stockframe.setIconImage(myImg);
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockframe.dispose();
			}
		});
		panel.add(btnCancel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblBagType = new JLabel("Bag Type:");
		lblBagType.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblBagType, "8, 4, right, default");
		
		textField = new JTextField();
		panel_1.add(textField, "10, 4, left, default");
		textField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblQuantity, "8, 6, right, default");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "10, 6, left, default");
		textField_1.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblPrice, "8, 8, right, default");
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, "10, 8, left, default");
		textField_2.setColumns(10);
	
		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bag_type = textField.getText();
				String quantity = textField_1.getText();
				String price = textField_2.getText();
				if(isNumeric(quantity))
				{
					JOptionPane.showMessageDialog(stockframe, "Please enter quantity in numeric !!!");
				}
				else if(isNumeric(price))
				{
					JOptionPane.showMessageDialog(stockframe, "Please enter price in numeric !!!");
				}
				else
				{
					int c=db.updateQuery("insert into stock values('"+bag_type+"',"+quantity+","+price+")");
				if(c>0)
				{
					JOptionPane.showMessageDialog(stockframe, "Stock Updated !!!");
					stockframe.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(stockframe, "Bag type already exist !!!");
				}
				}
			}	
		});
		panel.add(btnAdd);
		stockframe.getRootPane().setDefaultButton(btnAdd);
	}
	
	 public boolean isAlpha(String str) {
	     if(!str.equals(""))
	     {
		 for (int i=0; i<str.length(); i++) {
	            char c = str.charAt(i);
	            if (!Character.isLetter(c))
	                return true;
	        }
	     }
	     else
	     {
	    	 return true;
	     }
	        return false;
	    }
	 
	 public boolean isNumeric(String str) {
		 if(!str.equals(""))
	     {
	        for (int i=0; i<str.length(); i++) {
	            char c = str.charAt(i);
	            if (!Character.isDigit(c))
	                return true;
	        }
	     }
		 else
		 {
			 return true;
		 }
	        return false;
	    }


}
