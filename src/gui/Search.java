package gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import database.DBM;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class Search extends JPanel {
	private JTable table;
	private JTextField txtName;
	private JTextField txtPhone;
	DBM db=new DBM();
	JPopupMenu popup;
	JDatePickerImpl datePicker;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Search(JTabbedPane tb) throws IOException {
		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblByName = new JLabel("By Name:");
		lblByName.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblByName);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			table.setModel(db.selectQueryForTable("select * from trans where name like '%"+txtName.getText()+"%' and phone_no like '%"+txtPhone.getText()+"%' and trans_date like '%"+datePicker.getJFormattedTextField().getText()+"%'"));
			}
		});
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblByPhoneNo = new JLabel("By Phone no:");
		lblByPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblByPhoneNo);
		
		txtPhone = new JTextField();
		txtPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				table.setModel(db.selectQueryForTable("select * from trans where name like '%"+txtName.getText()+"%' and phone_no like '%"+txtPhone.getText()+"%' and trans_date like '%"+datePicker.getJFormattedTextField().getText()+"%'"));
				}
		});
		panel.add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblByDate = new JLabel("By Date:");
		lblByDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblByDate);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel.add(datePicker);
		datePicker.getJFormattedTextField().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				table.setModel(db.selectQueryForTable("select * from trans where name like '%"+txtName.getText()+"%' and phone_no like '%"+txtPhone.getText()+"%' and trans_date like '%"+datePicker.getJFormattedTextField().getText()+"%'"));
				}
		});
		
		JButton btnReset = new JButton("");
		btnReset.setToolTipText("Refresh/Reset");
		String imagePath = "res/refresh.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath);
		//BufferedImage myImg = ImageIO.read(imgStream);
		//btnReset.setIcon(new ImageIcon(myImg));
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			txtName.setText("");
			txtPhone.setText("");
			datePicker.getJFormattedTextField().setText("");
			table.setModel(db.selectQueryForTable("select * from trans"));
			}
		});
		panel.add(btnReset);
		popup = new JPopupMenu();
	    JMenuItem menuItem = new JMenuItem("Get Detail");
	    menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tb.indexOfTab("Detail")==-1)
				{
				JScrollPane scrollPane = new JScrollPane();
				Detail rg = new Detail(tb,table.getValueAt(table.getSelectedRow(), 0).toString());
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
				else
				{
					JScrollPane scrollPane = new JScrollPane();
					Detail rg = new Detail(tb,table.getValueAt(table.getSelectedRow(), 0).toString());
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
		});
	    popup.add(menuItem);
	   

	    //Add listener to components that can bring up popup menus.
	    MouseListener popupListener = new PopupListener();
	    table.addMouseListener(popupListener);		
	    table.setModel(db.selectQueryForTable("select * from trans"));
		
	}
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
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

}
