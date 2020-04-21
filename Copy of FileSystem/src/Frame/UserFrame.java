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
					
					//获取当前屏幕的长宽信息并将当前窗口设置为【居中】显示
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
		
		JMenu manageUser= new JMenu("用户管理");
			JMenuItem changeUser= new JMenuItem("修改用户");
			final JMenuItem addUser= new JMenuItem("添加用户");
			JMenuItem deleUser  = new JMenuItem("删除用户");
		
		JMenu manageFile  = new JMenu("档案管理");
			JMenuItem downLoadFile  = new JMenuItem("档案下载");
			JMenuItem upLoadFile  = new JMenuItem("档案上传");;
		
		JMenu manageUserInfo= new JMenu("个人信息管理");
			final JMenuItem changeUserInfo= new JMenuItem("修改信息");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//添加按钮
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(manageUser);
		menuBar.add(manageFile);
		menuBar.add(manageUserInfo);
		
		/******************************根据角色不同，菜单按钮可访问性不同****************************************/
		if(userRole == "administrator" ) {
			setTitle("档案管理员界面");
			upLoadFile.setEnabled(false);  //将【上传文件】按钮设置为不可访问，下同
		}else if(userRole == "operator") {
			setTitle("档案操作员界面");
			manageUser.setEnabled(false);  
		}else {
			setTitle("档案浏览员界面");
			manageUser.setEnabled(false);  
			upLoadFile.setEnabled(false);
		}
		
		/******************************点击【修改用户】按钮后执行的操作******************************************/
		//JMenuItem changeUser = new JMenuItem("修改用户");
		changeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManageFrame changeuser;
				try {
					changeuser = new UserManageFrame();
					e.getSource().equals(changeUserInfo);
					
					changeuser.setVisible(true);
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		manageUser.add(changeUser);
		
		/******************************点击【添加用户】按钮后执行的操作******************************************/
		//JMenuItem addUser = new JMenuItem("添加用户");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManageFrame changeuser;
				try {
					changeuser = new UserManageFrame();
					e.getSource().equals(addUser);
					
					
					
					changeuser.setVisible(true);		
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}	
			}
		});
		manageUser.add(addUser);
		
		/******************************点击【删除用户】按钮后执行的操作******************************************/
		//JMenuItem deleUser = new JMenuItem("删除用户");
		deleUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManageFrame changeuser;
				try {
					changeuser = new UserManageFrame();
					changeuser.setVisible(true);
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				//changeuser.transferFocusUpCycle();
			}
		});
		manageUser.add(deleUser);
		
		/******************************点击【档案上传】按钮后执行的操作******************************************/
		//JMenuItem upLoadFile = new JMenuItem("档案上传");
		upLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					FileManageFrame file = new FileManageFrame(userRole,userName);
					file.setVisible(true);
					file.setResizable(false);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		manageFile.add(upLoadFile);
		
		/******************************点击【档案下载】按钮后执行的操作******************************************/
		//JMenuItem downLoadFile = new JMenuItem("档案下载");
		downLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("fdsf");
					FileManageFrame file = new FileManageFrame(userRole,userName);
					file.setVisible(true);
					file.setResizable(false);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		manageFile.add(downLoadFile);
		
		
		/******************************点击【修改信息】按钮后执行的操作******************************************/
		//JMenuItem changeUserInfo = new JMenuItem("修改信息");
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

	/*************不知道啥东西，自己生成的代码***************/
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
	  
	