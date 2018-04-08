package gui;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;

public class Main {

	JFrame frmBillingApp;//reference for JFrame
	static Main window;//reference for Main class
	static String username;//reference for user name
	JTabbedPane tabbedPane;//reference for JTabbedPane
	JMenuBar menuBar;//reference for JMenuBar
	JMenu mnOption;//reference for JMenu
	JMenu User ;//reference for user JMenu
	JMenu mnStock;//reference for JMenu
	JMenuItem mntmBilling;//reference for JMenuItem
	JMenuItem mntmSearch;//reference for JMenuItem
	JMenuItem mntmAddStock;//reference for JMenuItem
	JMenuItem mntmEditStock;//reference for JMenuItem
	JMenuItem mntmDeleteStock;//reference for JMenuItem
	JMenuItem mntmShowStock;//reference for JMenuItem
	JMenuItem mntmLogOut;//reference for JMenuItem
	JMenuItem mntmChangePassword;//reference for JMenuItem
	
	public static void main(String[] args) {
		System.out.println("i am here");
				try {
					//set UI for Application to System UI
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
					//Initialize and set Main class
					window = new Main();
					window.frmBillingApp.setVisible(true);
					window.frmBillingApp.setEnabled(false);
					//call login window
					System.out.println("i am here");
					Login login = new Login(window);
					login.loginframe.setVisible(true);
					//set coordinate with main frame and login frame
					System.out.println("i am here");
					login.loginframe.setLocation((int)window.frmBillingApp.getLocation().getX()+(int)(window.frmBillingApp.getSize().getWidth()/2)-(int)(login.loginframe.getSize().getWidth()/2), (int)window.frmBillingApp.getLocation().getY()+(int)(window.frmBillingApp.getSize().getHeight()/2)-(int)(login.loginframe.getSize().getHeight()/2));
				} catch (Exception e) {
					System.out.println(e);
				}
	}

	public Main() throws IOException {
		//Initialize of JFrame
		frmBillingApp = new JFrame();
		//set properties for JFrame
		frmBillingApp.setTitle("Billing App");
		frmBillingApp.setBounds(100, 0, 740, 700);
		frmBillingApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmBillingApp.setIconImage(ImageIO.read(Main.class.getResourceAsStream("res/main.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmBillingApp.setLocation(dim.width/2-frmBillingApp.getSize().width/2, dim.height/2-frmBillingApp.getSize().height/2);
		
		//add JMenuBar to JFrame	
		menuBar = new JMenuBar();
		frmBillingApp.setJMenuBar(menuBar);
		
		//add JTabbedPane to ContentPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmBillingApp.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//add JMenu to JMenuBar 
		mnOption = new JMenu("Bill");
		mnOption.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnOption);
		//add JMenu to JMenuBar		
		mnStock = new JMenu("Stock");
		mnStock.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnStock);
		//add JMenu to JMenuBar 
		User = new JMenu("User");
		User.setFont(new Font("Segoe UI", Font.BOLD, 14));
		//User.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/profile.png"))));
		menuBar.add(Box.createHorizontalGlue());//using HorizontalGlue 
		menuBar.add(User);
				
		//Initialize JMenuItem
		mntmBilling = new JMenuItem("Billing",KeyEvent.VK_B);
		//mntmBilling.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/billing.png"))));
		//set key shortcut for JMenuItem
		mntmBilling.setMnemonic(KeyEvent.VK_B);
		mntmBilling.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		//add event to JMenuItem
		mntmBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tabbedPane.indexOfTab("Billing")==-1){
					try {
						JScrollPane scrollPane = new JScrollPane();
						Billing rg = new Billing(tabbedPane);
						scrollPane.setViewportView(rg);
						tabbedPane.addTab("Billing", scrollPane);
						tabbedPane.setTabComponentAt(tabbedPane.indexOfTab("Billing"),new ButtonTabComponent(tabbedPane,"res/billing1.png"));
						tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Billing"));
					} catch (IOException e) {}
				}
				else{
				tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Billing"));
				}
			}
		});
		//add JMenuItem to JMenu
		mnOption.add(mntmBilling);
		
		//Initialize JMenuItem
		mntmSearch = new JMenuItem("Search",KeyEvent.VK_S);
		//mntmSearch.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/search.png"))));
		//set key shortcut for JMenuItem
		mntmSearch.setMnemonic(KeyEvent.VK_S);
		mntmSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		//add event to JMenuItem
		mntmSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tabbedPane.indexOfTab("Search")==-1){
					try {
						JScrollPane scrollPane = new JScrollPane();
						Search rg=new Search(tabbedPane);
						scrollPane.setViewportView(rg);		
						tabbedPane.addTab("Search",scrollPane);
						tabbedPane.setTabComponentAt(tabbedPane.indexOfTab("Search"),new ButtonTabComponent(tabbedPane,"res/search1.png"));
						tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Search"));
					} catch (IOException e) {}
				}
				else {
					tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Search"));
				}
			}
		});
		//add JMenuItem to JMenu
		mnOption.add(mntmSearch);
		
		//Initialize JMenuItem
		mntmAddStock = new JMenuItem("Add Stock",KeyEvent.VK_A);
		//mntmAddStock.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/addstock.png"))));
		//set key shortcut for JMenuItem
		mntmAddStock.setMnemonic(KeyEvent.VK_A);
		mntmAddStock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		//add event to JMenuItem
		mntmAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddStock cp=new AddStock();
					cp.stockframe.setVisible(true);
					cp.stockframe.setLocation((int)window.frmBillingApp.getLocation().getX()+(int)(window.frmBillingApp.getSize().getWidth()/2)-(int)(cp.stockframe.getSize().getWidth()/2), (int)window.frmBillingApp.getLocation().getY()+(int)(window.frmBillingApp.getSize().getHeight()/2)-(int)(cp.stockframe.getSize().getHeight()/2));
				} catch (IOException e) {}
			}
		});
		//add JMenuItem to Menu
		mnStock.add(mntmAddStock);
		
		//Initialize JMenuItem
		mntmEditStock = new JMenuItem("Edit Stock",KeyEvent.VK_E);
		//mntmEditStock.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/editstock.png"))));
		//set key shortcut for JMenuItem
		mntmEditStock.setMnemonic(KeyEvent.VK_E);
		mntmEditStock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		//add event to JMenuItem
		mntmEditStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EditStock cp = new EditStock();
					cp.stockframe.setVisible(true);
					cp.stockframe.setLocation((int)window.frmBillingApp.getLocation().getX()+(int)(window.frmBillingApp.getSize().getWidth()/2)-(int)(cp.stockframe.getSize().getWidth()/2), (int)window.frmBillingApp.getLocation().getY()+(int)(window.frmBillingApp.getSize().getHeight()/2)-(int)(cp.stockframe.getSize().getHeight()/2));
				} catch (IOException e1) {}
			}
		});
		//add JMenuItem to JMenu
		mnStock.add(mntmEditStock);
		
		//Initialize JMenuItem
		mntmDeleteStock = new JMenuItem("Delete Stock",KeyEvent.VK_D);
		//mntmDeleteStock.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/removestock.png"))));
		//set key shortcut for JMenuItem
		mntmDeleteStock.setMnemonic(KeyEvent.VK_D);
		mntmDeleteStock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		//add event to JMenuItem
		mntmDeleteStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteStock cp = new DeleteStock();
					cp.stockframe.setVisible(true);
					cp.stockframe.setLocation((int)window.frmBillingApp.getLocation().getX()+(int)(window.frmBillingApp.getSize().getWidth()/2)-(int)(cp.stockframe.getSize().getWidth()/2), (int)window.frmBillingApp.getLocation().getY()+(int)(window.frmBillingApp.getSize().getHeight()/2)-(int)(cp.stockframe.getSize().getHeight()/2));
				} catch (IOException e1) {}
			}
		});
		//add JMenuItem to JMenu
		mnStock.add(mntmDeleteStock);
		
		//Initialize JMenuItem
		mntmShowStock = new JMenuItem("Show Stock",KeyEvent.VK_S);
		//mntmShowStock.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/showstock.png"))));
		//set key shortcut for JMenuItem
		mntmShowStock.setMnemonic(KeyEvent.VK_S);
		mntmShowStock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		//add event to JMenuItem
		mntmShowStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ShowStock cp = new ShowStock();
					cp.stockframe.setVisible(true);
					cp.stockframe.setLocation((int)window.frmBillingApp.getLocation().getX()+(int)(window.frmBillingApp.getSize().getWidth()/2)-(int)(cp.stockframe.getSize().getWidth()/2), (int)window.frmBillingApp.getLocation().getY()+(int)(window.frmBillingApp.getSize().getHeight()/2)-(int)(cp.stockframe.getSize().getHeight()/2));
				} catch (IOException e1) {}
			}
		});
		//add JMenuItem to JMenu
		mnStock.add(mntmShowStock);
		
		//Initialize JMenuItem
		mntmLogOut = new JMenuItem("Log Out");
		//mntmLogOut.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/logout.png"))));
		//add event to JMenuItem
		mntmLogOut.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				window.frmBillingApp.setEnabled(false);
				window.User.setText("User");
				window.username=null;
				try {
					Login login = new Login(window);
					login.loginframe.setVisible(true);
					login.loginframe.setLocation((int)window.frmBillingApp.getLocation().getX()+(int)(window.frmBillingApp.getSize().getWidth()/2)-(int)(login.loginframe.getSize().getWidth()/2), (int)window.frmBillingApp.getLocation().getY()+(int)(window.frmBillingApp.getSize().getHeight()/2)-(int)(login.loginframe.getSize().getHeight()/2));
				} catch (IOException e) {}
			}
		});
		//add JMenuItem to JMenu
		User.add(mntmLogOut);
		
		//Initialize JMenuItem
		mntmChangePassword = new JMenuItem("Change Password");
		//mntmChangePassword.setIcon(new ImageIcon(ImageIO.read(Login.class.getResourceAsStream("res/password.png"))));
		//add event to JMenuItem
		mntmChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					changepassword cp=new changepassword(window);
					cp.passwordframe.setVisible(true);
					cp.passwordframe.setLocation((int)window.frmBillingApp.getLocation().getX()+(int)(window.frmBillingApp.getSize().getWidth()/2)-(int)(cp.passwordframe.getSize().getWidth()/2), (int)window.frmBillingApp.getLocation().getY()+(int)(window.frmBillingApp.getSize().getHeight()/2)-(int)(cp.passwordframe.getSize().getHeight()/2));
				} catch (IOException e) {}
			}
		});
		//add JMenuItem to JMenu
		User.add(mntmChangePassword);
	}
}
