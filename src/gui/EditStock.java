package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import database.DBM;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EditStock{

	private JPanel contentPane;
	JFrame stockframe;
	private JTextField textField_1;
	private JTextField textField_2;
	DBM db=new DBM();
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditStock() throws IOException {
		stockframe=new JFrame();
		stockframe.setResizable(false);
		stockframe.setTitle("Edit Stock");
		stockframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		stockframe.setBounds(100, 100, 380, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		stockframe.setContentPane(contentPane);
		String imagePath = "res/editstock.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath );
		//BufferedImage myImg = ImageIO.read(imgStream);
		//stockframe.setIconImage(myImg);
		
		JPanel panel = new JPanel();
		stockframe.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.setToolTipText("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					int c=db.updateQuery("update stock set quantity = "+quantity+", price = "+price+" where bag_type = '"+comboBox.getSelectedItem()+"'");
				if(c>0)
				{
					JOptionPane.showMessageDialog(stockframe, "Stock Updated !!!");
					stockframe.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(stockframe, "Error !!!");
				}
				}

			}
		});
		panel.add(btnSave);
		
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
				ColumnSpec.decode("default:grow"),
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
		
		comboBox = new JComboBox(db.selectQueryForList("select bag_type from Stock ", "bag_type", "<<Select Bage Type>>"));
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	HashMap<String, String> hm=db.RowInfo("select quantity,price from stock where bag_type = '"+comboBox.getSelectedItem()+"'");
		    	String quantity=hm.get("quantity");
		    	String price=hm.get("price");
		    	textField_1.setText(quantity);
		    	textField_2.setText(price);
		   }
		});
		
		panel_1.add(comboBox, "10, 4, left, default");
		
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
		stockframe.getRootPane().setDefaultButton(btnSave);
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
