package gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class changepassword {

	private JPanel contentPane;
	JFrame passwordframe;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	DBM db=new DBM();

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public changepassword(Main window) throws IOException {
		passwordframe=new JFrame();
		passwordframe.setTitle("Change Password");
		passwordframe.setResizable(false);
		passwordframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		passwordframe.setBounds(100, 100, 380, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		passwordframe.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		String imagePath = "res/password.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath );
		//BufferedImage myImg = ImageIO.read(imgStream);
		//passwordframe.setIconImage(myImg);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setToolTipText("Save");
		btnChangePassword.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(db.loginCheck(Main.username, passwordField.getText()))
				{
					db.updateQuery("update user set password ='"+passwordField_1.getText()+"' where username ='"+Main.username+"'");
					JOptionPane.showMessageDialog(passwordframe, "Password Changed !!!");
					passwordframe.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(passwordframe, "Please enter correct password !!!");
				}
			}
		});
		panel.add(btnChangePassword);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordframe.dispose();
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
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblOldPassword, "8, 6, right, default");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panel_1.add(passwordField, "10, 6, left, default");
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewPassword, "8, 8, right, default");
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(10);
		panel_1.add(passwordField_1, "10, 8, left, default");
		passwordframe.getRootPane().setDefaultButton(btnChangePassword);
	}

}
