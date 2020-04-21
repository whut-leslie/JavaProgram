package Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import FileSystem.Client;
public class ChangeInfoFrame extends JFrame{
	private static final long serialVersionUID = 1L;
  public JPanel contentPane;
	private JTextField userName;
	private JTextField userOldPassword;
	private JTextField userNewPassword;
	private JTextField userNewPassword2;
	private JTextField userRole;
	public ChangeInfoFrame(){
		
		String name=Client.get_Name();
		String role=Client.get_Role();
		setTitle("个人信息管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 431);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(89, 57, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("原口令");
		label_1.setBounds(89, 105, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("新口令");
		label_2.setBounds(89, 154, 72, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("确认新口令");
		label_3.setBounds(71, 203, 89, 18);
		contentPane.add(label_3);
		
		JLabel lblNewLabel = new JLabel("角色");
		lblNewLabel.setBounds(101, 253, 72, 18);
		contentPane.add(lblNewLabel);

		JButton update_sure = new JButton("确认");
		update_sure.setBounds(71, 313, 102, 27);
		contentPane.add(update_sure);
		
		JButton update_cancel = new JButton("返回");
		update_cancel.setBounds(187, 313, 102, 27);
		contentPane.add(update_cancel);
		userName = new JTextField();
		userName.setBounds(156, 54, 108, 24);
		contentPane.add(userName);
	    userName.setColumns(10);
		userName.setText(name);
		userName.setEditable(false);
		
		
		userOldPassword = new JTextField();
		userOldPassword.setBounds(156, 102, 108, 24);
		contentPane.add(userOldPassword);
		userOldPassword.setColumns(10);
		
		userNewPassword = new JTextField();
		userNewPassword.setBounds(156, 151, 108, 24);
		contentPane.add(userNewPassword);
		userNewPassword.setColumns(10);
		
		userNewPassword2 = new JTextField();
		userNewPassword2.setBounds(156, 200, 108, 24);
		contentPane.add(userNewPassword2);
		userNewPassword2.setColumns(10);
		
		userRole = new JTextField();
		userRole.setBounds(156, 250, 108, 24);
		contentPane.add(userRole);
		userRole.setColumns(10);
		userRole.setText(role);
		userRole.setEditable(false);
	
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		update_sure.addActionListener(new Butt(ChangeInfoFrame.this,userOldPassword,userNewPassword,userNewPassword2));
		update_cancel.addActionListener( new Butt(ChangeInfoFrame.this));
		ChangeInfoFrame.this.setVisible(true);		
	
	}
   class Butt implements ActionListener{
	    JFrame frame;
	    JTextField userName;
	     JTextField userOldPassword;
		JTextField userNewPassword;
		JTextField userNewPassword2;
		 JTextField userRole;
	    Butt(JFrame frame, JTextField userOldPassword, JTextField userNewPassword,JTextField userNewPassword2){
	    	this.frame=frame;
	    	this.userOldPassword=userOldPassword;
	    	this.userNewPassword=userNewPassword;
	    	this.userNewPassword2=userNewPassword2;
		}
	    Butt(JFrame frame){
	    	this.frame=frame;
	    }
		public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="确认") {
					String old_password=userOldPassword.getText();
					String new_password=userNewPassword.getText();
					String new_password2=userNewPassword.getText();
					try {
						Client.ChangeSelfInfo(old_password, new_password, new_password2);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getActionCommand()=="返回"){
					frame.dispose();
				}
			}
		}
		
	}