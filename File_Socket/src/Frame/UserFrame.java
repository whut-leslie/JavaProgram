package Frame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FileSystem.Client;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public UserFrame() {
		
		String role=Client.get_Role();
		String name=Client.get_Name();
		JMenu manageUser= new JMenu("�û�����");
			JMenuItem changeUser= new JMenuItem("�޸��û�");
			final JMenuItem addUser= new JMenuItem("����û�");
			JMenuItem deleUser  = new JMenuItem("ɾ���û�");
		
		JMenu manageFile  = new JMenu("��������");
			JMenuItem downLoadFile  = new JMenuItem("��������");
			JMenuItem upLoadFile  = new JMenuItem("�����ϴ�");;
		
		JMenu manageUserInfo= new JMenu("������Ϣ����");
			final JMenuItem changeUserInfo= new JMenuItem("�޸���Ϣ");
			JMenuItem exit=new JMenuItem("�˳�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//��Ӱ�ť
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(manageUser);
		menuBar.add(manageFile);
		menuBar.add(manageUserInfo);
		manageUser.add(changeUser);
		manageUser.add(deleUser);
		manageUser.add(addUser);
		manageFile.add(upLoadFile);
		manageFile.add(downLoadFile);
		manageUserInfo.add(changeUserInfo);
		manageUserInfo.add(exit);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		 
		/******************************���ݽ�ɫ��ͬ���˵���ť�ɷ����Բ�ͬ****************************************/
		if(role.equals("administrator")) {
			setTitle("��������Ա����");
			upLoadFile.setEnabled(false);  //�����ϴ��ļ�����ť����Ϊ���ɷ��ʣ�������
		}else if(role.equals("operator")) {
			setTitle("��������Ա����");
			manageUser.setEnabled(false);  
		}else {
			setTitle("�������Ա����");
			manageUser.setEnabled(false);  
			upLoadFile.setEnabled(false);
			}
		addUser.addActionListener(new Menu(name,role));
		deleUser.addActionListener(new Menu(name,role));
		changeUser.addActionListener(new Menu(name,role));
		downLoadFile.addActionListener(new Menu(name,role));
		upLoadFile.addActionListener(new Menu(name,role));
		exit.addActionListener(new Menu(UserFrame.this));
		changeUserInfo.addActionListener(new Menu(UserFrame.this));
		//��ȡ��ǰ��Ļ�ĳ�����Ϣ������ǰ��������Ϊ�����С���ʾ
				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
				int centerX = screenSize.width/2;
				int centerY = screenSize.height/2;
				UserFrame.this.setLocation(centerX-UserFrame.this.getWidth()/2,centerY-UserFrame.this.getHeight()/2);
		     UserFrame.this.setVisible(true);
	}

	
public	class Menu implements ActionListener{
		String name;
	    String role;
	    JFrame frame;
	   Menu(JFrame frame){
		this.frame=frame;
		}
	     Menu(String name,String role){
		this.name=name;
		this.role=role;
	}
	
		/******************************������޸��û�����ť��ִ�еĲ���******************************************/
		//JMenuItem changeUser = new JMenuItem("�޸��û�");
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand()=="�޸��û�") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(1);
					}
		/******************************���������û�����ť��ִ�еĲ���******************************************/
		//JMenuItem addUser = new JMenuItem("����û�");
				if(e.getActionCommand()=="����û�") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(0);
					}
		/******************************�����ɾ���û�����ť��ִ�еĲ���******************************************/
		//JMenuItem deleUser = new JMenuItem("ɾ���û�");
				else if(e.getActionCommand()=="ɾ���û�") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(2);
					}
			/****************************����������ϴ�����ť��ִ�еĲ���******************************************/
		//JMenuItem upLoadFile = new JMenuItem("�����ϴ�");
			else if(e.getActionCommand()=="�����ϴ�"){
					FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(1);
			}
		
		/******************************������������ء���ť��ִ�еĲ���******************************************/
		//JMenuItem downLoadFile = new JMenuItem("��������");
		
				else if(e.getActionCommand()=="��������"){
				FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(0);
			}
		/******************************������޸���Ϣ����ť��ִ�еĲ���******************************************/
		//JMenuItem changeUserInfo = new JMenuItem("�޸���Ϣ");
		
				else if(e.getActionCommand()=="�޸���Ϣ"){
				@SuppressWarnings("unused")
				ChangeInfoFrame changeInfoFrame = new ChangeInfoFrame();
			}		
		//******************************������˳�����ť��ִ�еĲ���******************************************/
		//JMenuItem exit = new JMenuItem("�˳�");
				else if(e.getActionCommand()=="�˳�") {
					frame.dispose();
					try {
						Client.closeConnection();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		
			}
	}


}	  
	