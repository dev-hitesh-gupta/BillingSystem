package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import database.DBM;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Billing extends JPanel {
	JTextField txtPrice;
	JTable table;
	JTextField textField;
	JTextField textField_1;
	DBM db=new DBM();
	Vector<Vector<Object>> row;
	Vector<String> columnNames;
	HashMap<String,HashMap<String,String>> stock;
	String[] options={"Yes","No"};
	String id;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public Billing(JTabbedPane tb) throws IOException {
		setLayout(new BorderLayout(0, 0));
		columnNames=new Vector<String>();
		row=new Vector<Vector<Object>>();
		columnNames.add("Bag_Type");
		columnNames.add("Quantity");
		columnNames.add("Price");
		DefaultTableModel tm=new DefaultTableModel(row,columnNames);
		stock=db.getStock();
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblTotalPrice);
		txtPrice = new JTextField();
		txtPrice.setEnabled(false);
		txtPrice.setText("price");
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setToolTipText("Genrate Bill");
		
		panel.add(btnGenerate);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblName);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone no.:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblPhoneNo);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblDate);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel_1.add(datePicker);
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setColumnHeaderView(panel_2);
		
		JLabel lblBagtype = new JLabel("Bag_type:");
		lblBagtype.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(lblBagtype);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox(db.selectQueryForList("select bag_type from Stock ", "bag_type", "<<Select Bage Type>>"));
		panel_2.add(comboBox);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(lblQuantity);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox_1 = new JComboBox();
		panel_2.add(comboBox_1);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	int quantity=Integer.parseInt(stock.get(comboBox.getSelectedItem()).get("quantity"));
		    	comboBox_1.removeAllItems();
		    	for(int i = 1;i<=quantity;i++)
		    	{
		    		comboBox_1.addItem(i);
		    	}
		    }
		});
		
		JButton btnAdd = new JButton("");
		btnAdd.setToolTipText("Add");
		String imagePath = "res/add.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath);
		//BufferedImage myImg = ImageIO.read(imgStream);
		//btnAdd.setIcon(new ImageIcon(myImg));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			boolean check=true;
				for(int i =0;i<tm.getRowCount();i++)
			{
				if(comboBox.getSelectedItem().toString().equals(tm.getValueAt(i, 0)))
				{
					check=false;
				}
			}
				if(check)
				{
				Vector<String> rowData=new Vector<String>();
				rowData.add(comboBox.getSelectedItem().toString());
				rowData.add(comboBox_1.getSelectedItem().toString());
				int price=Integer.parseInt(stock.get(comboBox.getSelectedItem()).get("price"))*Integer.parseInt(comboBox_1.getSelectedItem().toString());
				rowData.add(price+"");
				tm.addRow(rowData);
				int totalprice=0;
				for(int i =0;i<tm.getRowCount();i++)
				{
					totalprice=totalprice+Integer.parseInt(tm.getValueAt(i, 2).toString());
				}
				txtPrice.setText(totalprice+"");
				}
				else{
					JOptionPane.showMessageDialog(panel_2, comboBox.getSelectedItem()+" is Already entered !!!");
				}
			}
		});
		panel_2.add(btnAdd);
		
		JButton btnRemove = new JButton("");
		btnRemove.setToolTipText("Remove");
		imagePath = "res/remove.png";
		imgStream = Login.class.getResourceAsStream(imagePath);
		//myImg = ImageIO.read(imgStream);
		//btnRemove.setIcon(new ImageIcon(myImg));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowindex=table.getSelectedRow();
				txtPrice.setText(Integer.parseInt(txtPrice.getText())-Integer.parseInt(tm.getValueAt(rowindex, 2).toString())+"");
				tm.removeRow(rowindex);
			}
		});
		panel_2.add(btnRemove);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);
		
		table = new JTable(tm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table);

		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showOptionDialog(tb,
                        "You really want to genrate bill?",
                        "", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
				if (n == JOptionPane.YES_OPTION) {
					if(datePicker.getJFormattedTextField().getText().equals(""))
					{
						JOptionPane.showMessageDialog(panel_1, "Date is mandatory !!!");
			
					}
					if(isAlpha(textField.getText()))
				{
					JOptionPane.showMessageDialog(panel_1, "Name should be Aphabetical !!!");
				}
				else if(isNumeric(textField_1.getText()))
				{
					JOptionPane.showMessageDialog(panel_1, "Phone no. should be Numeric !!!");
				}
				else if(textField_1.getText().length()!=10)
				{
					JOptionPane.showMessageDialog(panel_1, "Phone no. should be 10 Digit !!!");
				}
				else
				{
					for(int i=0;i<tm.getRowCount();i++)
					{
						stock.get(tm.getValueAt(i,0)).put("quantity",Integer.parseInt(stock.get(tm.getValueAt(i,0)).get("quantity"))-Integer.parseInt(tm.getValueAt(i,1).toString())+"");
					}
					db.reStock(stock);
					db.updateQuery("insert into trans (name,phone_no,trans_date,total_price)values('"+textField.getText()+"','"+textField_1.getText()+"','"+datePicker.getJFormattedTextField().getText()+"',"+txtPrice.getText()+")");
					id=db.getTransactionId();
					for(int i=0;i<tm.getRowCount();i++)
					{
						db.updateQuery("insert into transdetail (bag_type,quantity,price,trans_id)values('"+tm.getValueAt(i, 0)+"',"+tm.getValueAt(i, 1)+","+tm.getValueAt(i, 2)+","+id+")");
					}
					tb.remove(tb.getSelectedIndex());
					if(tb.indexOfTab("Detail")==-1)
					{
					try {
						JScrollPane scrollPane = new JScrollPane();
						Detail rg = new Detail(tb,id);
						tb.addTab("Detail", null, scrollPane, null);
						
						tb.setTabComponentAt(tb.indexOfTab("Detail"),new ButtonTabComponent(tb,"res/detail1.png"));
						scrollPane.setViewportView(rg);
						tb.setSelectedIndex(tb.indexOfTab("Detail"));	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					}
					else
					{
						JScrollPane scrollPane = new JScrollPane();
						Detail rg = new Detail(tb,id);
						tb.remove(tb.indexOfTab("Detail"));
						tb.addTab("Detail", null, scrollPane, null);
						try {
							
							tb.setTabComponentAt(tb.indexOfTab("Detail"),new ButtonTabComponent(tb,"res/detail1.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						scrollPane.setViewportView(rg);
						tb.setSelectedIndex(tb.indexOfTab("Detail"));
							
					}
				}
				}
			}
		});
	}
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

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
