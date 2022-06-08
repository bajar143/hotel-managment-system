package hotel;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import hotel.splash;

import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class loginPage {

	private JFrame frame;
	private JTextField tfusername;
	private JPasswordField tfpwd;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static JLabel lblNewLabel;
	JLabel Ustar = new JLabel("*");
	JLabel Pstar = new JLabel("*");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		int x;
		splash frame = new splash();
		frame.setVisible(true);
		try{
		for(x=0; x<=100; x++) {
				splash.progressBar.setValue(x);
				Thread.sleep(50);
				splash.lblNewLabel.setText("Loading:- "+Integer.toString(x)+" %");
				if(x==100) {
					frame.dispose();
				}
				
		}	
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			Hotel hotel = Hotel.getInstance();
			public void run() {
				try {
					
					loginPage window = new loginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 * @return 
	 */

	public void splash() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 150, 590, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("main.gif"));
		label.setIcon(icon);
		label.setBounds(35, 0, 550, 410);
		contentPane.add(label);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(32, 178, 170));
		progressBar.setBounds(0, 462, 590, 28);
		contentPane.add(progressBar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setForeground(new Color(0, 255, 0));
		lblNewLabel.setBounds(230, 420, 208, 32);
		contentPane.add(lblNewLabel);
		
		
		
	}
	
	private void setBounds(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		
	}

	private void setUndecorated(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	private void setContentPane(JPanel contentPane2) {
		// TODO Auto-generated method stub
		
	}

	public loginPage() {
		initialize();
		Ustar.setVisible(false);
		Pstar.setVisible(false);

		
		
	}

	
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 23));
		frame.setBounds(50,50, 898, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN ");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblLogin.setBounds(369, 190, 212, 67);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblUsername.setBounds(269, 324, 155, 50);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblPassword.setBounds(280, 413, 133, 38);
		frame.getContentPane().add(lblPassword);
		
		tfusername = new JTextField();
		tfusername.setFont(new Font("Times New Roman", Font.BOLD, 23));
		tfusername.setBounds(489, 331, 208, 38);
		frame.getContentPane().add(tfusername);
		tfusername.setColumns(10);
		
		tfpwd = new JPasswordField();
		tfpwd.setFont(new Font("Times New Roman", Font.BOLD, 23));
		tfpwd.setBounds(489, 414, 208, 38);
		frame.getContentPane().add(tfpwd);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ustar.setVisible(false);
				Pstar.setVisible(false);
				if(tfusername.getText().equals(""))
				{
					Ustar.setVisible(true);
				}
				 if(String.valueOf(tfpwd.getPassword()).equals(""))
				{
					Pstar.setVisible(true);
				}
				 else
				 {
					 //String url,user,pwd;
					 GetConnection connect=new GetConnection();
						Connection conn=connect.getConnection();
						try{
							//Connection connect =DriverManager.getConnection(url, user, pwd);
							//System.out.println("Connection success");
							PreparedStatement ps=conn.prepareStatement("SELECT * FROM login WHERE username= ? AND password=? " );
							ps.setString(1, tfusername.getText());
							ps.setString(2,String.valueOf(tfpwd.getPassword()));
						    
							ResultSet rs=ps.executeQuery();
							if(rs.next())
							{
								secondPage sp = new secondPage();
								
							    sp.setVisible(true);
							    sp.pack();
							    sp.setLocationRelativeTo(null);	    
							    sp.setBounds(50,50, 1015, 574);
							    frame.setVisible(false);
							    
							   
							}
								else
					                JOptionPane.showMessageDialog(null, "Error", "Please check user name / password",JOptionPane.ERROR_MESSAGE);
						
						
						}
							catch(SQLException p)
							{
								p.printStackTrace();
							}
				 }
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnLogin.setBounds(288, 513, 147, 50);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnCancel.setBounds(565, 513, 155, 51);
		frame.getContentPane().add(btnCancel);
		
		
		Ustar.setForeground(Color.RED);
		Ustar.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Ustar.setBounds(699, 347, 46, 21);
		frame.getContentPane().add(Ustar);
		
		
		Pstar.setForeground(Color.RED);
		Pstar.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Pstar.setBounds(699, 430, 46, 21);
		frame.getContentPane().add(Pstar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("images\\login (8).png"));
		label.setBounds(12, 234, 314, 283);
		frame.getContentPane().add(label);
		
		JLabel lblHotelManagementSystem = new JLabel("HOTEL MANAGEMENT SYSTEM");
		lblHotelManagementSystem.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
		lblHotelManagementSystem.setBounds(109, 72, 636, 80);
		frame.getContentPane().add(lblHotelManagementSystem);
	}
}
