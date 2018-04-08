package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import database.DBM;

public class DeleteStock{

	private JPanel contentPane;
	JFrame stockframe;
	DBM db=new DBM();
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeleteStock() throws IOException {
		stockframe=new JFrame();
		stockframe.setAlwaysOnTop(true);
		stockframe.setResizable(false);
		stockframe.setTitle("Delete Stock");
		stockframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		stockframe.setBounds(100, 100, 380, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		stockframe.setContentPane(contentPane);
		String imagePath = "res/removestock.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath );
		//BufferedImage myImg = ImageIO.read(imgStream);
		//stockframe.setIconImage(myImg);
		
		JPanel panel = new JPanel();
		stockframe.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Remove");
		btnSave.setToolTipText("Remove");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c=db.updateQuery("delete from stock where bag_type = '"+comboBox.getSelectedItem()+"'");
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
		panel_1.add(comboBox, "10, 4, left, default");
	}

}
