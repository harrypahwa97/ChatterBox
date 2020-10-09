import java.awt.*;
import java.sql.*;

import javax.net.ssl.HostnameVerifier;
import javax.swing.*;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatterBox {

	public JFrame frame;
	public JTextField username;
	public JPasswordField passwordField;
	public String Username;
	JPanel CoverLogin,UserMenu,Signup,ChangePassword,ChatUI,Chat;
	JTextArea textArea,textArea_1,textArea_2;
	JLabel receiver,user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatterBox window = new ChatterBox();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection conn=null;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField currentPassword;
	private JPasswordField newPassword;
	private JTextField Receiver;
	public ChatterBox() {
		initialize();
		conn=SQLiteConnection.sqliteConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\hitesh pahwa\\workspace\\Chat Messenger\\img\\chaticon.png");  
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\hitesh pahwa\\workspace\\Chat Messenger\\img\\chaticon.png")); 
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 441, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(242, -13, 91, 124);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\hitesh pahwa\\workspace\\Chat Messenger\\img\\HelloIcon.png"));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblChatter = new JLabel("CHATTERBOX");
		lblChatter.setBounds(103, 37, 127, 31);
		frame.getContentPane().add(lblChatter);
		lblChatter.setBackground(Color.BLACK);
		lblChatter.setFont(new Font("Mistral", Font.PLAIN, 30));
		lblChatter.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatter.setForeground(Color.BLACK);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 94, 399, 366);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		CoverLogin = new JPanel();
		CoverLogin.setBackground(new Color(245, 245, 245));
		layeredPane.add(CoverLogin, "name_192817986190400");
		CoverLogin.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(56, 28, 78, 20);
		CoverLogin.add(lblUsername);
		lblUsername.setForeground(Color.DARK_GRAY);
		lblUsername.setFont(new Font("Sitka Small", Font.PLAIN, 15));

		username = new JTextField();
		username.setBounds(56, 55, 284, 29);
		CoverLogin.add(username);
		username.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		username.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(56, 95, 73, 20);
		CoverLogin.add(lblPassword);
		lblPassword.setForeground(Color.DARK_GRAY);
		lblPassword.setFont(new Font("Sitka Small", Font.PLAIN, 15));

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		passwordField.setBounds(58, 120, 284, 29);
		CoverLogin.add(passwordField);

		JButton btnLogin = new JButton("Log in");
		btnLogin.setBounds(58, 176, 284, 29);
		CoverLogin.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="Select * from userdetails where username=? and password=?";
					PreparedStatement ps=conn.prepareStatement(query);
					ps.setString(1, username.getText());
					ps.setString(2, passwordField.getText());
					ResultSet rs=ps.executeQuery();
					int count=0;
					while(rs.next()){
						count++;	
					}
					if(count>=1){
						user.setText("Hey! "+username.getText());
						layeredPane.removeAll();
						layeredPane.add(UserMenu);
						layeredPane.repaint();
						layeredPane.revalidate();
					}
					else{
						JOptionPane.showMessageDialog(null,"Username or Password not correct");
						username.setText(null);
						passwordField.setText(null);
					}
					rs.close();
					ps.close();
				}catch(Exception p){
					JOptionPane.showMessageDialog(null, p);
				}
			}
		});
		btnLogin.setBackground(SystemColor.textHighlight);
		btnLogin.setForeground(new Color(245, 245, 245));
		btnLogin.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));

		JLabel lblDontHaveAn = new JLabel("Don't have an account?");
		lblDontHaveAn.setBounds(40, 255, 181, 24);
		CoverLogin.add(lblDontHaveAn);
		lblDontHaveAn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));

		JButton btnSignUo = new JButton("Sign up");
		btnSignUo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Signup);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnSignUo.setBounds(245, 255, 109, 29);
		CoverLogin.add(btnSignUo);
		btnSignUo.setBackground(new Color(245, 245, 245));
		btnSignUo.setForeground(SystemColor.textHighlight);
		btnSignUo.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));

		UserMenu = new JPanel();
		UserMenu.setBackground(new Color(245, 245, 245));
		layeredPane.add(UserMenu, "name_193157301741200");
		UserMenu.setLayout(null);

		JButton btnChat = new JButton("Chat");
		btnChat.setBackground(SystemColor.textHighlight);
		btnChat.setForeground(new Color(245, 245, 245));
		btnChat.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(null);
				try{
					String query6="Select username from userdetails where username!=?";
					PreparedStatement ps=conn.prepareStatement(query6);
					ps.setString(1, username.getText());
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						textArea.setText(textArea.getText()+rs.getString(1)+"\n");
					}
					rs.close();
					ps.close();
					layeredPane.removeAll();
					layeredPane.add(ChatUI);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
				catch(Exception y){
					JOptionPane.showMessageDialog(null,y);
				}
			}
		});
		btnChat.setBounds(54, 124, 291, 25);
		UserMenu.add(btnChat);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setForeground(new Color(245, 245, 245));
		btnChangePassword.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		btnChangePassword.setBackground(SystemColor.textHighlight);
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				layeredPane.removeAll();
				layeredPane.add(ChangePassword);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnChangePassword.setBounds(54, 167, 291, 25);
		UserMenu.add(btnChangePassword);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(SystemColor.textHighlight);
		btnLogOut.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		btnLogOut.setBackground(new Color(245, 245, 245));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText(null);
				passwordField.setText(null);
				layeredPane.removeAll();
				layeredPane.add(CoverLogin);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnLogOut.setBounds(286, 13, 101, 25);
		UserMenu.add(btnLogOut);
		
		user = new JLabel("");
		user.setForeground(SystemColor.textHighlight);
		user.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		user.setBounds(12, 13, 262, 25);
		UserMenu.add(user);

		Signup = new JPanel();
		Signup.setBackground(new Color(245, 245, 245));
		layeredPane.add(Signup, "name_193521894073100");
		Signup.setLayout(null);

		JLabel lblusername = new JLabel("Enter Username");
		lblusername.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblusername.setBounds(75, 76, 160, 16);
		Signup.add(lblusername);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		textField_1.setBounds(75, 105, 244, 25);
		Signup.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblenterpassword = new JLabel("Enter Password");
		lblenterpassword.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblenterpassword.setBounds(75, 152, 160, 16);
		Signup.add(lblenterpassword);

		JButton btnregister = new JButton("Register");
		btnregister.setForeground(new Color(245, 245, 245));
		btnregister.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		btnregister.setBackground(SystemColor.textHighlight);
		btnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query="Select username from userdetails where username=?";
					PreparedStatement ps=conn.prepareStatement(query);
					ps.setString(1,textField_1.getText());
					ResultSet rs=ps.executeQuery();
					int count=0;
					while(rs.next()){
						count++;	
					}
					rs.close();
					ps.close();
					if(count>0){
						JOptionPane.showMessageDialog(null,"Username already exists! Try again");
						textField_1.setText(null);
						passwordField_1.setText(null);
						layeredPane.removeAll();
						layeredPane.add(Signup);
						layeredPane.repaint();
						layeredPane.revalidate();
					}
					else{
						try{
							String str="";
							if(str.equals(passwordField_1.getText())){
								JOptionPane.showMessageDialog(null,"password can't be empty");
							}
							else{
								String query2 = "INSERT INTO userdetails(username,Password) VALUES(?,?)";
								PreparedStatement pstmt = conn.prepareStatement(query2); 
								pstmt.setString(1,textField_1.getText() );
								pstmt.setString(2,passwordField_1.getText() );
								pstmt.executeUpdate();
								pstmt.close();
								textField_1.setText(null);
								passwordField_1.setText(null);
								JOptionPane.showMessageDialog(null,"Registered Successfully! Please Log In");							
								layeredPane.removeAll();
								layeredPane.add(CoverLogin);
								layeredPane.repaint();
								layeredPane.revalidate();
							}
						} catch (SQLException q) {
							JOptionPane.showMessageDialog(null, q);
						}					
					}
				}catch(Exception p){
					JOptionPane.showMessageDialog(null, p);
				}

			}
		});
		btnregister.setBounds(75, 230, 239, 29);
		Signup.add(btnregister);

		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		passwordField_1.setBounds(75, 181, 239, 25);
		Signup.add(passwordField_1);

		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(245, 245, 245));
		btnBack.setForeground(SystemColor.textHighlight);
		btnBack.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(null);
				passwordField_1.setText(null);
				layeredPane.removeAll();
				layeredPane.add(CoverLogin);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnBack.setBounds(286, 13, 101, 25);
		Signup.add(btnBack);

		ChangePassword = new JPanel();
		ChangePassword.setBackground(new Color(245, 245, 245));
		layeredPane.add(ChangePassword, "name_206456150640900");
		ChangePassword.setLayout(null);

		JLabel UsernameChangePsw = new JLabel("Current Password");
		UsernameChangePsw.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		UsernameChangePsw.setBounds(93, 80, 164, 25);
		ChangePassword.add(UsernameChangePsw);

		currentPassword = new JTextField();
		currentPassword.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		currentPassword.setBounds(93, 107, 216, 25);
		ChangePassword.add(currentPassword);
		currentPassword.setColumns(10);

		JLabel NewPassword = new JLabel("New Password");
		NewPassword.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		NewPassword.setHorizontalAlignment(SwingConstants.LEFT);
		NewPassword.setBounds(93, 146, 164, 25);
		ChangePassword.add(NewPassword);

		JButton btnChangePsd = new JButton("Change Password");
		btnChangePsd.setBackground(SystemColor.textHighlight);
		btnChangePsd.setForeground(new Color(245, 245, 245));
		btnChangePsd.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		btnChangePsd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query4="Select * from userdetails where username=? and password=?";
					PreparedStatement ps=conn.prepareStatement(query4);
					ps.setString(1,username.getText());
					ps.setString(2,currentPassword.getText());
					ResultSet rs=ps.executeQuery();
					int count=0;
					while(rs.next()){
						count++;	
					}
					rs.close();
					ps.close();
					if(count>0){
						try{
							String str="";
							if(str.equals(newPassword.getText())){
								JOptionPane.showMessageDialog(null,"password can't be empty");
							}
							else{
								String query3 = "UPDATE userdetails SET password=? WHERE username=?";
								PreparedStatement pstmt = conn.prepareStatement(query3); 
								pstmt.setString(1,newPassword.getText() );
								pstmt.setString(2,username.getText() );
								pstmt.executeUpdate();
								pstmt.close();
								JOptionPane.showMessageDialog(null,"Password Changed Successfully!");							
								currentPassword.setText(null);
								newPassword.setText(null);
								layeredPane.removeAll();
								layeredPane.add(UserMenu);
								layeredPane.repaint();
								layeredPane.revalidate();
							}
						} catch (SQLException q) {
							JOptionPane.showMessageDialog(null, q);
						}		
					}
					else{
						JOptionPane.showMessageDialog(null,"current password wrong");
						currentPassword.setText(null);
						newPassword.setText(null);
					}
				}
				catch(Exception y){
					JOptionPane.showMessageDialog(null,y);
				}

			}
		});
		btnChangePsd.setBounds(93, 221, 216, 25);
		ChangePassword.add(btnChangePsd);

		JButton btnBack2 = new JButton("Back");
		btnBack2.setBackground(new Color(245, 245, 245));
		btnBack2.setForeground(SystemColor.textHighlight);
		btnBack2.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		btnBack2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPassword.setText(null);
				newPassword.setText(null);
				layeredPane.removeAll();
				layeredPane.add(UserMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnBack2.setBounds(286, 13, 101, 25);
		ChangePassword.add(btnBack2);

		newPassword = new JPasswordField();
		newPassword.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		newPassword.setBounds(92, 172, 217, 25);
		ChangePassword.add(newPassword);
		
		ChatUI = new JPanel();
		ChatUI.setBackground(new Color(245, 245, 245));
		layeredPane.add(ChatUI, "name_2422435481000");
		ChatUI.setLayout(null);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setForeground(SystemColor.textHighlight);
		btnBack_1.setBackground(new Color(245, 245, 245));
		btnBack_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Receiver.setText(null); 
				layeredPane.removeAll();
				layeredPane.add(UserMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnBack_1.setBounds(284, 13, 103, 25);
		ChatUI.add(btnBack_1);
		
		JLabel lblFriendList = new JLabel("Friend List");
		lblFriendList.setForeground(SystemColor.textHighlight);
		lblFriendList.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		lblFriendList.setBounds(33, 13, 116, 25);
		ChatUI.add(lblFriendList);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 44, 330, 237);
		ChatUI.add(panel);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		textArea.setText("");
		textArea.setBounds(0, 0, 330, 237);
		panel.add(textArea);
		
		JScrollPane js=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setBounds(0, 0, 330, 237);
		panel.add(js);
		
		
		
		JLabel lblEnterTheUser = new JLabel("Enter the user name you want to chat with :");
		lblEnterTheUser.setForeground(SystemColor.textHighlight);
		lblEnterTheUser.setFont(new Font("Sitka Small", Font.PLAIN, 13));
		lblEnterTheUser.setBounds(33, 285, 309, 16);
		ChatUI.add(lblEnterTheUser);
		
		Receiver = new JTextField();
		Receiver.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		Receiver.setBounds(33, 314, 220, 22);
		ChatUI.add(Receiver);
		Receiver.setColumns(10);
		
		JButton btnChat_1 = new JButton("Chat");
		btnChat_1.setBackground(new Color(245, 245, 245));
		btnChat_1.setForeground(SystemColor.textHighlight);
		btnChat_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		btnChat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="Select * from userdetails where username=?";
					PreparedStatement ps=conn.prepareStatement(query);
					ps.setString(1, Receiver.getText());
					ResultSet rs=ps.executeQuery();
					int count=0;
					while(rs.next()){
						count++;	
					}
					rs.close();
					ps.close();
					if(count>=1){
						try{
							String query7="Select sender,message from chat where sender=? and receiver=? or sender=? and receiver=?";
							PreparedStatement ps2=conn.prepareStatement(query7);
							ps2.setString(1, username.getText());
							ps2.setString(2, Receiver.getText());
							ps2.setString(3, Receiver.getText());
							ps2.setString(4, username.getText());
							ResultSet rs2=ps2.executeQuery();
							while(rs2.next()){
								textArea_1.setText(textArea_1.getText()+rs2.getString(1)+" : "+rs2.getString(2)+"\n");
							}
							rs2.close();
							ps2.close();
							receiver.setText(Receiver.getText());
							layeredPane.removeAll();
							layeredPane.add(Chat);
							layeredPane.repaint();
							layeredPane.revalidate();
						}
						catch(Exception y){
							JOptionPane.showMessageDialog(null,y);
						}	
					}
					else{
						JOptionPane.showMessageDialog(null,"Friend not found. Please try again");
						Receiver.setText(null);
					}
				}catch(Exception p){
					JOptionPane.showMessageDialog(null, p);
				}
				
			}
		});
		btnChat_1.setBounds(265, 314, 97, 25);
		ChatUI.add(btnChat_1);
		
		Chat = new JPanel();
		Chat.setBackground(new Color(245, 245, 245));
		layeredPane.add(Chat, "name_62923298470700");
		Chat.setLayout(null);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.setBackground(new Color(245, 245, 245));
		btnBack_2.setForeground(SystemColor.textHighlight);
		btnBack_2.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Receiver.setText(null);
				receiver.setText(null);
				textArea_1.setText(null);
				textArea_2.setText(null);
				layeredPane.removeAll();
				layeredPane.add(ChatUI);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnBack_2.setBounds(288, 13, 99, 25);
		Chat.add(btnBack_2);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 45, 375, 258);
		Chat.add(panel_1);
		panel_1.setLayout(null);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		textArea_1.setBounds(1, 1, 4, 22);
		panel_1.add(textArea_1);
		
		JScrollPane js2=new JScrollPane(textArea_1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js2.setBounds(0, 0, 375, 258);
		panel_1.add(js2);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBackground(new Color(245, 245, 245));
		btnSend.setForeground(SystemColor.textHighlight);
		btnSend.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String str="";
					if(str.equals(textArea_2.getText())){
						JOptionPane.showMessageDialog(null,"message can't be empty");
					}
					else{
						String query7 = "INSERT INTO chat(sender,receiver,message) VALUES(?,?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query7); 
						pstmt.setString(1,username.getText() );
						pstmt.setString(2,Receiver.getText() );
						pstmt.setString(3,textArea_2.getText() );
						pstmt.executeUpdate();
						pstmt.close();
					}
					
				} catch (SQLException q) {
					JOptionPane.showMessageDialog(null, q);
				}
				textArea_1.setText(null);
				try{
					String query7="Select sender,message from chat where sender=? and receiver=? or sender=? and receiver=?";
					PreparedStatement ps=conn.prepareStatement(query7);
					ps.setString(1, username.getText());
					ps.setString(2, Receiver.getText());
					ps.setString(3, Receiver.getText());
					ps.setString(4, username.getText());
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						textArea_1.setText(textArea_1.getText()+rs.getString(1)+" : "+rs.getString(2)+"\n");
					}
					rs.close();
					ps.close();
				}
				catch(Exception y){
					JOptionPane.showMessageDialog(null,y);
				}	
				textArea_2.setText(null);
				layeredPane.removeAll();
				layeredPane.add(Chat);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnSend.setBounds(307, 316, 80, 46);
		Chat.add(btnSend);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 316, 287, 46);
		Chat.add(panel_2);
		panel_2.setLayout(null);
		
		textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		textArea_2.setBounds(1, 1, 285, 32);
		panel_2.add(textArea_2);
		
		JScrollPane js3=new JScrollPane(textArea_2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js3.setBounds(0, 0, 287, 46);
		panel_2.add(js3);
		
		receiver = new JLabel("");
		receiver.setForeground(SystemColor.textHighlight);
		receiver.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		receiver.setBounds(12, 13, 254, 25);
		Chat.add(receiver);
		
		Username=username.getText();
	}
}