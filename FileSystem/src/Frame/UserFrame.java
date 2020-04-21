package Frame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public void showMenu(String userName,String userRole){
		UserFrame userframe = new UserFrame(userName,userRole);
		
		//��ȡ��ǰ��Ļ�ĳ�����Ϣ������ǰ��������Ϊ�����С���ʾ
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width/2;
		int centerY = screenSize.height/2;
		userframe.setLocation(centerX-userframe.getWidth()/2,centerY-userframe.getHeight()/2);
		userframe.setVisible(true);
		
	}
	public UserFrame(final String userName,final String userRole) {
		
		JMenu manageUser= new JMenu("�û�����");
			JMenuItem changeUser= new JMenuItem("�޸��û�");
			final JMenuItem addUser= new JMenuItem("����û�");
			JMenuItem deleUser  = new JMenuItem("ɾ���û�");
		
		JMenu manageFile  = new JMenu("��������");
			JMenuItem downLoadFile  = new JMenuItem("��������");
			JMenuItem upLoadFile  = new JMenuItem("�����ϴ�");;
		
		JMenu manageUserInfo= new JMenu("������Ϣ����");
			final JMenuItem changeUserInfo= new JMenuItem("�޸���Ϣ");
			final JMenuItem exit=new JMenuItem("�˳�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//��Ӱ�ť
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(manageUser);
		menuBar.add(manageFile);
		menuBar.add(manageUserInfo);

		/******************************���ݽ�ɫ��ͬ���˵���ť�ɷ����Բ�ͬ****************************************/
		if(userRole.equals("administrator")) {
			setTitle("��������Ա����");
			upLoadFile.setEnabled(false);  //�����ϴ��ļ�����ť����Ϊ���ɷ��ʣ�������
		}else if(userRole.equals("operator")) {
			setTitle("��������Ա����");
			manageUser.setEnabled(false);  
		}else {
			setTitle("�������Ա����");
			manageUser.setEnabled(false);  
			upLoadFile.setEnabled(false);
		}
		
		/******************************������޸��û�����ť��ִ�еĲ���******************************************/
		//JMenuItem changeUser = new JMenuItem("�޸��û�");
		changeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="�޸��û�") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(userName,1);
					}
			}
			});
		manageUser.add(changeUser);
		
		/******************************���������û�����ť��ִ�еĲ���******************************************/
		//JMenuItem addUser = new JMenuItem("����û�");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="����û�") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(userName,0);
					}
			}
			});
		manageUser.add(addUser);
		/******************************�����ɾ���û�����ť��ִ�еĲ���******************************************/
		//JMenuItem deleUser = new JMenuItem("ɾ���û�");
		deleUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="ɾ���û�") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(userName,2);
					}
			}
			});
		manageUser.add(deleUser);
	
		
		/******************************����������ϴ�����ť��ִ�еĲ���******************************************/
		//JMenuItem upLoadFile = new JMenuItem("�����ϴ�");
		upLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="�����ϴ�"){
					FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(userName,1);
			}
				}
		});
		manageFile.add(upLoadFile);
		
		/******************************������������ء���ť��ִ�еĲ���******************************************/
		//JMenuItem downLoadFile = new JMenuItem("��������");
		downLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="��������"){
				FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(userName,0);
			}
				}
			});
	
		manageFile.add(downLoadFile);
		
		
		/******************************������޸���Ϣ����ť��ִ�еĲ���******************************************/
		//JMenuItem changeUserInfo = new JMenuItem("�޸���Ϣ");
		changeUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeInfoFrame update = new ChangeInfoFrame();
				update.showMenu(userName,userRole);
				update.setVisible(true);
			}
		});
		manageUserInfo.add(changeUserInfo);
		/******************************������˳�����ť��ִ�еĲ���******************************************/
		//JMenuItem deleUser = new JMenuItem("�˳�");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="�˳�") {
					UserFrame.this.dispose();
					}
			}
			});
		manageUserInfo.add(exit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

}
	  
	