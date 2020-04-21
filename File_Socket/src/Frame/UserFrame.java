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
		JMenu manageUser= new JMenu("用户管理");
			JMenuItem changeUser= new JMenuItem("修改用户");
			final JMenuItem addUser= new JMenuItem("添加用户");
			JMenuItem deleUser  = new JMenuItem("删除用户");
		
		JMenu manageFile  = new JMenu("档案管理");
			JMenuItem downLoadFile  = new JMenuItem("档案下载");
			JMenuItem upLoadFile  = new JMenuItem("档案上传");;
		
		JMenu manageUserInfo= new JMenu("个人信息管理");
			final JMenuItem changeUserInfo= new JMenuItem("修改信息");
			JMenuItem exit=new JMenuItem("退出");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//添加按钮
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
		 
		/******************************根据角色不同，菜单按钮可访问性不同****************************************/
		if(role.equals("administrator")) {
			setTitle("档案管理员界面");
			upLoadFile.setEnabled(false);  //将【上传文件】按钮设置为不可访问，下类似
		}else if(role.equals("operator")) {
			setTitle("档案操作员界面");
			manageUser.setEnabled(false);  
		}else {
			setTitle("档案浏览员界面");
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
		//获取当前屏幕的长宽信息并将当前窗口设置为【居中】显示
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
	
		/******************************点击【修改用户】按钮后执行的操作******************************************/
		//JMenuItem changeUser = new JMenuItem("修改用户");
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand()=="修改用户") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(1);
					}
		/******************************点击【添加用户】按钮后执行的操作******************************************/
		//JMenuItem addUser = new JMenuItem("添加用户");
				if(e.getActionCommand()=="添加用户") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(0);
					}
		/******************************点击【删除用户】按钮后执行的操作******************************************/
		//JMenuItem deleUser = new JMenuItem("删除用户");
				else if(e.getActionCommand()=="删除用户") {
					UserManageFrame userWindow=new UserManageFrame();
					userWindow.showMenu(2);
					}
			/****************************点击【档案上传】按钮后执行的操作******************************************/
		//JMenuItem upLoadFile = new JMenuItem("档案上传");
			else if(e.getActionCommand()=="档案上传"){
					FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(1);
			}
		
		/******************************点击【档案下载】按钮后执行的操作******************************************/
		//JMenuItem downLoadFile = new JMenuItem("档案下载");
		
				else if(e.getActionCommand()=="档案下载"){
				FileManageFrame fileManageFrame=new FileManageFrame();
					fileManageFrame.showMenu(0);
			}
		/******************************点击【修改信息】按钮后执行的操作******************************************/
		//JMenuItem changeUserInfo = new JMenuItem("修改信息");
		
				else if(e.getActionCommand()=="修改信息"){
				@SuppressWarnings("unused")
				ChangeInfoFrame changeInfoFrame = new ChangeInfoFrame();
			}		
		//******************************点击【退出】按钮后执行的操作******************************************/
		//JMenuItem exit = new JMenuItem("退出");
				else if(e.getActionCommand()=="退出") {
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
	