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
		
		//获取当前屏幕的长宽信息并将当前窗口设置为【居中】显示
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width/2;
		int centerY = screenSize.height/2;
		userframe.setLocation(centerX-userframe.getWidth()/2,centerY-userframe.getHeight()/2);
		userframe.setVisible(true);
		
	}
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
			final JMenuItem exit=new JMenuItem("退出");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//添加按钮
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(manageUser);
		menuBar.add(manageFile);
		menuBar.add(manageUserInfo);

		/******************************根据角色不同，菜单按钮可访问性不同****************************************/
		if(userRole.equals("administrator")) {
			setTitle("档案管理员界面");
			upLoadFile.setEnabled(false);  //将【上传文件】按钮设置为不可访问，下类似
		}else if(userRole.equals("operator")) {
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
				if(e.getActionCommand()=="修改用户") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(userName,1);
					}
			}
			});
		manageUser.add(changeUser);
		
		/******************************点击【添加用户】按钮后执行的操作******************************************/
		//JMenuItem addUser = new JMenuItem("添加用户");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="添加用户") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(userName,0);
					}
			}
			});
		manageUser.add(addUser);
		/******************************点击【删除用户】按钮后执行的操作******************************************/
		//JMenuItem deleUser = new JMenuItem("删除用户");
		deleUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="删除用户") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(userName,2);
					}
			}
			});
		manageUser.add(deleUser);
	
		
		/******************************点击【档案上传】按钮后执行的操作******************************************/
		//JMenuItem upLoadFile = new JMenuItem("档案上传");
		upLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="档案上传"){
					FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(userName,1);
			}
				}
		});
		manageFile.add(upLoadFile);
		
		/******************************点击【档案下载】按钮后执行的操作******************************************/
		//JMenuItem downLoadFile = new JMenuItem("档案下载");
		downLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="档案下载"){
				FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(userName,0);
			}
				}
			});
	
		manageFile.add(downLoadFile);
		
		
		/******************************点击【修改信息】按钮后执行的操作******************************************/
		//JMenuItem changeUserInfo = new JMenuItem("修改信息");
		changeUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeInfoFrame update = new ChangeInfoFrame();
				update.showMenu(userName,userRole);
				update.setVisible(true);
			}
		});
		manageUserInfo.add(changeUserInfo);
		/******************************点击【退出】按钮后执行的操作******************************************/
		//JMenuItem deleUser = new JMenuItem("退出");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="退出") {
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
	  
	