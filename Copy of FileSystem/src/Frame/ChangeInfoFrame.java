package Frame;
import FileSystem.DataProcessing;
import FileSystem.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class ChangeInfoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField userOldPassword;
	private JPasswordField userNewPassword;
	private JPasswordField userNewPassword2;
	private JTextField userRole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String name = null;
					String role = null;
					ChangeInfoFrame frame = new ChangeInfoFrame(name,role);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangeInfoFrame(final String name,final String role) {
		setTitle("������Ϣ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("�û���");
		label.setBounds(89, 57, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("ԭ����");
		label_1.setBounds(89, 105, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("������");
		label_2.setBounds(89, 154, 72, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("ȷ��������");
		label_3.setBounds(71, 203, 89, 18);
		contentPane.add(label_3);
		
		JLabel lblNewLabel = new JLabel("��ɫ");
		lblNewLabel.setBounds(101, 253, 72, 18);
		contentPane.add(lblNewLabel);
		
		userName = new JTextField();
		userName.setBounds(156, 54, 108, 24);
		contentPane.add(userName);
		userName.setColumns(10);
		userName.setText(name);
		userName.setEditable(false);
		
		
		userOldPassword = new JPasswordField();
		userOldPassword.setBounds(156, 102, 108, 24);
		contentPane.add(userOldPassword);
		userOldPassword.setColumns(10);
		
		userNewPassword = new JPasswordField();
		userNewPassword.setBounds(156, 151, 108, 24);
		contentPane.add(userNewPassword);
		userNewPassword.setColumns(10);
		
		userNewPassword2 = new JPasswordField();
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
		
		JButton update_sure = new JButton("ȷ��");
		update_sure.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				User user = null;
				try {
					user = DataProcessing.searchUser(name, userOldPassword.getText());
						if(user != null && userOldPassword.getText().equals(user.getPassword())) 
							if( (userNewPassword.getText().equals(userNewPassword2.getText())) == true && userNewPassword.getSelectionEnd()!= 0) {
								if(DataProcessing.updateUser(userName.getText(), userNewPassword.getText(), role))
								{//if(user.changeSelfInfo(userNewPassword.getText())) {
									JOptionPane.showMessageDialog(null, "�û���Ϣ���³ɹ���");
									ChangeInfoFrame.this.dispose();
								}else {
									JOptionPane.showMessageDialog(null, "�û���Ϣ����ʧ�ܣ�");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "�����벻һ�»�Ϊ�գ�����ȷ���룡");
							}
						else {
							JOptionPane.showMessageDialog(null, "ԭ������󣡣�");
						}
					
				}	
				 catch (SQLException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
				
			}
			
		});
		update_sure.setBounds(71, 313, 102, 27);
		contentPane.add(update_sure);
		
		JButton update_cancel = new JButton("����");
		update_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeInfoFrame.this.dispose(); //�رմ���
			}
		});
		update_cancel.setBounds(187, 313, 102, 27);
		contentPane.add(update_cancel);
	}
	

}
