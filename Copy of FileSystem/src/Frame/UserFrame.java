package Frame;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String role = "administrator";
					String name = null;
					UserFrame userframe = new UserFrame(name,role);
					
					//��ȡ��ǰ��Ļ�ĳ�����Ϣ������ǰ��������Ϊ�����С���ʾ
					Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
					int centerX = screenSize.width/2;
					int centerY = screenSize.height/2;
					userframe.setLocation(centerX-userframe.getWidth()/2,centerY-userframe.getHeight()/2);
					
					userframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//��Ӱ�ť
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(manageUser);
		menuBar.add(manageFile);
		menuBar.add(manageUserInfo);
		
		/******************************���ݽ�ɫ��ͬ���˵���ť�ɷ����Բ�ͬ****************************************/
		if(userRole == "administrator" ) {
			setTitle("��������Ա����");
			upLoadFile.setEnabled(false);  //�����ϴ��ļ�����ť����Ϊ���ɷ��ʣ���ͬ
		}else if(userRole == "operator") {
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
				UserManageFrame changeuser;
				try {
					changeuser = new UserManageFrame();
					e.getSource().equals(changeUserInfo);
					
					changeuser.setVisible(true);
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
			}
		});
		manageUser.add(changeUser);
		
		/******************************���������û�����ť��ִ�еĲ���******************************************/
		//JMenuItem addUser = new JMenuItem("����û�");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManageFrame changeuser;
				try {
					changeuser = new UserManageFrame();
					e.getSource().equals(addUser);
					
					
					
					changeuser.setVisible(true);		
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}	
			}
		});
		manageUser.add(addUser);
		
		/******************************�����ɾ���û�����ť��ִ�еĲ���******************************************/
		//JMenuItem deleUser = new JMenuItem("ɾ���û�");
		deleUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManageFrame changeuser;
				try {
					changeuser = new UserManageFrame();
					changeuser.setVisible(true);
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				//changeuser.transferFocusUpCycle();
			}
		});
		manageUser.add(deleUser);
		
		/******************************����������ϴ�����ť��ִ�еĲ���******************************************/
		//JMenuItem upLoadFile = new JMenuItem("�����ϴ�");
		upLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					FileManageFrame file = new FileManageFrame(userRole,userName);
					file.setVisible(true);
					file.setResizable(false);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		manageFile.add(upLoadFile);
		
		/******************************������������ء���ť��ִ�еĲ���******************************************/
		//JMenuItem downLoadFile = new JMenuItem("��������");
		downLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("fdsf");
					FileManageFrame file = new FileManageFrame(userRole,userName);
					file.setVisible(true);
					file.setResizable(false);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		manageFile.add(downLoadFile);
		
		
		/******************************������޸���Ϣ����ť��ִ�еĲ���******************************************/
		//JMenuItem changeUserInfo = new JMenuItem("�޸���Ϣ");
		changeUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeInfoFrame update = new ChangeInfoFrame(userName,userRole);
				update.setVisible(true);
			}
		});
		manageUserInfo.add(changeUserInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

	/*************��֪��ɶ�������Լ����ɵĴ���***************/
	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
	  
	