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
					
					//��ȡ��ǰ��Ļ�ĳ�����Ϣ������ǰ��������Ϊ�����С���ʾ
					Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
					int centerX = screenSize.width/2;
					int centerY = screenSize.height/2;
					loginFr.setLocation(centerX-loginFr.getWidth()/2,centerY-loginFr.getHeight()/2);

					loginFr.setVisible(true);
					loginFr.setResizable(false); //���ò��ɸı䴰�ڴ�С
					
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
		super("��ӭʹ�õ�������ϵͳ");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//���塾�����˺š��� ���ı���
		final TextField inputUserName_Field = new TextField();  
		inputUserName_Field.setBounds(183, 61, 162, 25);
		contentPane.add(inputUserName_Field);
		
		//���塾�������롿�ġ��ı���
		final TextField inputUserPassword_Field = new TextField();  
		inputUserPassword_Field.setBounds(183, 108, 162, 25);
		contentPane.add(inputUserPassword_Field);
		
		/**************�����������˺š��ġ��ı����ж�����������Ƿ�Ϊ���س���***************************************************/
		inputUserName_Field.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				//���س��������ж�
				if(e.getKeyChar() == KeyEvent.VK_ENTER )  {
					successLogin(inputUserName_Field.getText().length(), inputUserPassword_Field.getText().length(),inputUserName_Field.getText(),null);
				}
			}
		});
		
		//�������������롿�ġ��ı��򡿣��ж�����������Ƿ�Ϊ���س���
		inputUserPassword_Field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//���س��������ж�
				if(e.getKeyChar() == KeyEvent.VK_ENTER )  {
					successLogin(inputUserName_Field.getText().length(), inputUserPassword_Field.getText().length(),inputUserName_Field.getText(), inputUserPassword_Field.getText());
				}
			}
		});
		
		
		JLabel inputUserName_tip = new JLabel("�������û�����");
		inputUserName_tip.setBounds(83, 66, 123, 18);
		contentPane.add(inputUserName_tip);
		
		JLabel inputUserPassword_tip = new JLabel("���������룺");
		inputUserPassword_tip.setBounds(86, 108, 91, 18);
		contentPane.add(inputUserPassword_tip);
		
		/********************************����½����ť���䡾������***********************************************/
		JButton login = new JButton("��½");
		login.setBounds(83, 169, 110, 27);
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				successLogin(inputUserName_Field.getText().length(), inputUserPassword_Field.getText().length(),inputUserName_Field.getText(), inputUserPassword_Field.getText());
			}
		}); 
		contentPane.add(login);
		
		/********************************��ȡ������ť���䡾������*******************************************/
		JButton btnNewButton = new JButton("ȡ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin.this.dispose();
			}
		});
		btnNewButton.setBounds(235, 169, 110, 27);
		contentPane.add(btnNewButton);
	}

	
	//�û��������½����ť֮�����Ϣ�����Լ�����չʾ
	/******�����û�ϲ����������֮��Ͱ����س��������е�½������Ҫ�������롿�ı�����м��������¡��س��������е�½����*/
	//�����ı����롾��½��ť���Ĳ���д��һ��������������ã���������
	public void successLogin(int namelength,int passwordlength,String userName,String userPassword) {
		String userRole = null;
		//String userName = inputUserName_Field.getText();//inputUserName_Field.getText();
		
		//����û�û�������κ���Ϣ,�͵������½����ť��������ʾ
		if(namelength==0 || passwordlength==0) {   
			JOptionPane.showMessageDialog(null, "�û���������Ϊ�գ�\n��������ȷ���룡");
		}
		//�û����������û��������룬�������½����ť��������������  
		else {
		
			User usr = null;
			try {
				//�����ݿ��в��ҵ�ǰ�û����Ƿ����
				usr = DataProcessing.searchUser(userName, userPassword);

			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				//e.printStackTrace();
				//System.out.println("���ݿ����"+ e1.toString());
				JOptionPane.showMessageDialog(null, "����ȷ������Ϣ��");
			}
			//���û���ҵ�
			if(usr == null) {
				//System.out.println("�û������������");
				JOptionPane.showMessageDialog(null, "�û������������");
			}else {
				//usr.showMenu();
				userRole = usr.getRole();    //�õ���ǰ�û��Ľ�ɫ
				userName = usr.getName();	 //�����ݿ��л�ȡ��ǰ�û���

				UserLogin.this.setVisible(false);
				UserFrame f2 = new UserFrame(userName,userRole);
				
				//��ȡ��ǰ��Ļ�ĳ�����Ϣ������ǰ��������Ϊ�����С���ʾ
				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
				int centerX = screenSize.width/2;
				int centerY = screenSize.height/2;
				f2.setLocation(centerX-f2.getWidth()/2,centerY-f2.getHeight()/2);
				
				f2.setVisible(true);
			}//if
		}//else
	}//successLogin()
}
	
