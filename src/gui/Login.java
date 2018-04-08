package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import database.DBM;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login  {
	JFrame loginframe;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	DBM db =new DBM();
	
	

	public Login(Main window) throws IOException {
		
		loginframe = new JFrame();
		String imagePath = "res/login.png";
		InputStream imgStream = Login.class.getResourceAsStream(imagePath );
		//BufferedImage myImg = ImageIO.read(imgStream);
		//loginframe.setIconImage(myImg);
		loginframe.setResizable(false);
		loginframe.setTitle("Login");
		loginframe.setType(Type.POPUP);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.setBounds(320, 240, 320, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginframe.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("LogIn");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(db.loginCheck(textField.getText(), passwordField.getText()))
				{
					window.frmBillingApp.setEnabled(true);
					window.User.setText(textField.getText().toUpperCase());
					Main.username=textField.getText();
					loginframe.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(loginframe, "Please enter correct username & password !!!");
				}
			}
		});
		btnNewButton.setToolTipText("Click Here !!");
		panel.add(btnNewButton);
		
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
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblUsername, "6, 4, right, default");
		
		textField = new JTextField();
		panel_1.add(textField, "8, 4, left, default");
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblPassword, "6, 6, right, default");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panel_1.add(passwordField, "8, 6, left, default");
		
		loginframe.getRootPane().setDefaultButton(btnNewButton);
	}

}
