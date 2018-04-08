package gui;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.DBM;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.HashMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Detail extends JPanel {
	private JTable table;
	String[] options={"Yes","No"};
DBM db=new DBM();
	/**
	 * Create the panel.
	 */
	public Detail(JTabbedPane tb,String ID) {
		setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		HashMap<String, String> hm=db.RowInfo("Select * from trans where trans_id = "+ID);
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblName, "4, 2");
		
		JLabel lblName_1 = new JLabel(hm.get("name"));
		add(lblName_1, "6, 2");
		
		JLabel lblPhoneNo = new JLabel("Phone no:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblPhoneNo, "4, 4");
		
		JLabel lblPhone = new JLabel(hm.get("phone_no"));
		add(lblPhone, "6, 4");
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblDate, "4, 6");
		
		JLabel lblDate_1 = new JLabel(hm.get("trans_date"));
		add(lblDate_1, "6, 6");
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTotalPrice, "4, 8");
		
		JLabel lblPrice = new JLabel(hm.get("total_price"));
		add(lblPrice, "6, 8");
		
		JLabel lblTransId = new JLabel("Trans ID");
		lblTransId.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTransId, "4, 10");
		
		JLabel lblId = new JLabel(ID);
		add(lblId, "6, 10");
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showOptionDialog(table,
                        "You really want to delete bill?",
                        "", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
				if (n == JOptionPane.YES_OPTION) {
				
				db.updateQuery("delete from trans where trans_id = '"+ID+"'");
			db.updateQuery("delete from transdetail where trans_id = '"+ID+"'");
			tb.removeTabAt(tb.getSelectedIndex());
				}
			}
		});
		add(btnDelete, "6, 12, right, default");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "1, 14, 6, 1, fill, fill");
		
		table = new JTable();
		table.setModel(db.selectQueryForTable("select * from transdetail where trans_id = '"+ID+"'"));
		scrollPane.setViewportView(table);

	}

}
