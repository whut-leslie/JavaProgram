package Frame;
import FileSystem.Client;
import FileSystem.DataProcessing;
import FileSystem.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

@SuppressWarnings("unused")
public class UserLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public void showMenu(){
	 UserLogin loginFr = new UserLogin();
	
	//获取当前屏幕的长宽信息并将当前窗口设置为【居中】显示
	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	int centerX = screenSize.width/2;
	int centerY = screenSize.height/2;
	loginFr.setLocation(centerX-loginFr.getWidth()/2,centerY-loginFr.getHeight()/2);

	loginFr.setVisible(true);
	loginFr.setResizable(false); //设置不可改变窗口大小
	}
		
	public UserLogin(){
		super("欢迎使用档案管理系统");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//定义【输入用户名】的 【文本框】
		JTextField inputUserName_Field = new JTextField();  
		inputUserName_Field.setBounds(183, 61, 162, 25);
		contentPane.add(inputUserName_Field);
		
		//定义【输入密码】的【文本框】
		JPasswordField inputUserPassword_Field = new JPasswordField();  
		inputUserPassword_Field.setBounds(183, 108, 162, 25);
		contentPane.add(inputUserPassword_Field);
		//定义【输入用户名】的按钮
		JLabel inputUserName_tip = new JLabel("请输入用户名：");
		inputUserName_tip.setBounds(83, 66, 123, 18);
		contentPane.add(inputUserName_tip);
		//定义输入密码的【按钮】
		JLabel inputUserPassword_tip = new JLabel("请输入密码：");
		inputUserPassword_tip.setBounds(86, 108, 91, 18);
		contentPane.add(inputUserPassword_tip);
		//定义【登录】的【按钮】
		JButton login = new JButton("登录");
		login.setBounds(83, 169, 110, 27);
		contentPane.add(login);
		//定义【取消】的【按钮】
		JButton btnNewButton = new JButton("取消");
		btnNewButton.setBounds(235, 169, 110, 27);
		contentPane.add(btnNewButton);
		login.addActionListener(new ButtonHandler(UserLogin.this,inputUserName_Field,inputUserPassword_Field));
		btnNewButton.addActionListener(new ButtonHandler(UserLogin.this));
		this.setVisible(true);
		}
	public class ButtonHandler implements ActionListener{
		public JTextField te1=new JTextField();
	    public JPasswordField te2=new JPasswordField();
	    public JFrame frame=new JFrame();
	    ButtonHandler(JFrame frame){
	    	this.frame=frame;
	    }
	    ButtonHandler(JFrame frame,JTextField te1,JPasswordField te2) {
	    	this.frame=frame;
			this.te1=te1;
			this.te2=te2;
		}

			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="登录") {
					String name=te1.getText();
					String password=String.valueOf(te2.getPassword());
					try {
						try {
							Client.Login(name,password,frame);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					}
				}
				else {
					frame.dispose();
				}	
			}
		}
	

}
	
