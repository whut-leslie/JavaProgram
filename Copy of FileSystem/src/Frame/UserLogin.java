package Frame;
import FileSystem.DataProcessing;
import FileSystem.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

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

@SuppressWarnings("unused")
public class UserLogin<Textfield> extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("rawtypes")
			public void run() {
				try {
					DataProcessing.Init();
					
				UserLogin loginFr = new UserLogin();
					
					//获取当前屏幕的长宽信息并将当前窗口设置为【居中】显示
					Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
					int centerX = screenSize.width/2;
					int centerY = screenSize.height/2;
					loginFr.setLocation(centerX-loginFr.getWidth()/2,centerY-loginFr.getHeight()/2);

					loginFr.setVisible(true);
					loginFr.setResizable(false); //设置不可改变窗口大小
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserLogin() {
		super("欢迎使用档案管理系统");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//定义【输入账号】的 【文本框】
		final TextField inputUserName_Field = new TextField();  
		inputUserName_Field.setBounds(183, 61, 162, 25);
		contentPane.add(inputUserName_Field);
		
		//定义【输入密码】的【文本框】
		final TextField inputUserPassword_Field = new TextField();  
		inputUserPassword_Field.setBounds(183, 108, 162, 25);
		contentPane.add(inputUserPassword_Field);
		
		/**************监听【输入账号】的【文本框】判断输入的内容是否为【回车】***************************************************/
		inputUserName_Field.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				//【回车】按键判断
				if(e.getKeyChar() == KeyEvent.VK_ENTER )  {
					successLogin(inputUserName_Field.getText().length(), inputUserPassword_Field.getText().length(),inputUserName_Field.getText(),null);
				}
			}
		});
		
		//监听【输入密码】的【文本框】，判断输入的内容是否为【回车】
		inputUserPassword_Field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//【回车】按键判断
				if(e.getKeyChar() == KeyEvent.VK_ENTER )  {
					successLogin(inputUserName_Field.getText().length(), inputUserPassword_Field.getText().length(),inputUserName_Field.getText(), inputUserPassword_Field.getText());
				}
			}
		});
		
		
		JLabel inputUserName_tip = new JLabel("请输入用户名：");
		inputUserName_tip.setBounds(83, 66, 123, 18);
		contentPane.add(inputUserName_tip);
		
		JLabel inputUserPassword_tip = new JLabel("请输入密码：");
		inputUserPassword_tip.setBounds(86, 108, 91, 18);
		contentPane.add(inputUserPassword_tip);
		
		/********************************【登陆】按钮及其【监听】***********************************************/
		JButton login = new JButton("登陆");
		login.setBounds(83, 169, 110, 27);
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				successLogin(inputUserName_Field.getText().length(), inputUserPassword_Field.getText().length(),inputUserName_Field.getText(), inputUserPassword_Field.getText());
			}
		}); 
		contentPane.add(login);
		
		/********************************【取消】按钮及其【监听】*******************************************/
		JButton btnNewButton = new JButton("取消");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin.this.dispose();
			}
		});
		btnNewButton.setBounds(235, 169, 110, 27);
		contentPane.add(btnNewButton);
	}

	
	//用户点击【登陆】按钮之后的信息处理以及界面展示
	/******部分用户喜欢输入密码之后就按【回车】键进行登陆，所以要将【密码】文本框进行监听，按下【回车】键进行登陆操作*/
	//将【文本框】与【登陆按钮】的操作写成一个方法，方便调用，操作方便
	public void successLogin(int namelength,int passwordlength,String userName,String userPassword) {
		String userRole = null;
		//String userName = inputUserName_Field.getText();//inputUserName_Field.getText();
		
		//如果用户没有输入任何信息,就点击【登陆】按钮，进行提示
		if(namelength==0 || passwordlength==0) {   
			JOptionPane.showMessageDialog(null, "用户名或密码为空！\n　　请正确输入！");
		}
		//用户正常输入用户名和密码，点击【登陆】按钮，程序正常进行  
		else {
		
			User usr = null;
			try {
				//从数据库中查找当前用户，是否存在
				usr = DataProcessing.searchUser(userName, userPassword);

			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				//e.printStackTrace();
				//System.out.println("数据库错误："+ e1.toString());
				JOptionPane.showMessageDialog(null, "请正确输入信息！");
			}
			//如果没有找到
			if(usr == null) {
				//System.out.println("用户名或密码错误！");
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}else {
				//usr.showMenu();
				userRole = usr.getRole();    //得到当前用户的角色
				userName = usr.getName();	 //从数据库中获取当前用户名

				UserLogin.this.setVisible(false);
				UserFrame f2 = new UserFrame(userName,userRole);
				
				//获取当前屏幕的长宽信息并将当前窗口设置为【居中】显示
				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
				int centerX = screenSize.width/2;
				int centerY = screenSize.height/2;
				f2.setLocation(centerX-f2.getWidth()/2,centerY-f2.getHeight()/2);
				
				f2.setVisible(true);
			}//if
		}//else
	}//successLogin()
}
	
