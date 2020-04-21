package Frame;

	/****************************************/
	/*******����Ա�������棺�û�����**********/
	/****************************************/
import FileSystem.DataProcessing;
import FileSystem.User;
import FileSystem.Administrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("unused")
public class UserManageFrame extends JFrame{
/**
* 
*/
private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JTextField userName_input;
private JTextField userPassword_input;
private JTextField userNewPassword_input;
private JTable table;
private JTable table_1;
//private JComboBox userRoleChoose_change;
/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
			DataProcessing.Init();
			UserManageFrame frame = new UserManageFrame();
			frame.setResizable(false);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
});
}

/**
* Create the frame.
* @throws SQLException 
*/
//**************�û��������****************************************//
public UserManageFrame() throws SQLException {
setTitle("�û��������");

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 430, 289);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

//******************ͨ��tabbedPane����������ת�ı�ǩ**********************//
JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
tabbedPane.setBounds(0, 0, 432, 265);
contentPane.add(tabbedPane);


/**************************���޸��û�����ǩ�Լ����ִ�в���*********************/
final JPanel changeUserInfo = new JPanel();
tabbedPane.addTab("�޸��û�", null, changeUserInfo, null);
changeUserInfo.setLayout(null);


JLabel userName_change = new JLabel("�û���");
userName_change.setBounds(80, 30, 72, 18);
changeUserInfo.add(userName_change);

JLabel userPassword_change = new JLabel("����");
userPassword_change.setBounds(80, 72, 72, 18);
changeUserInfo.add(userPassword_change);

JLabel userRole_change = new JLabel("��ɫ");
userRole_change.setBounds(80, 114, 72, 18);
changeUserInfo.add(userRole_change);

//******ʹ�á�������ǩ��չʾȫ�����û�����************//
final JComboBox userNameChoose_change = new JComboBox();

//****�����е� �û���Ϣ��ӵ� �����˵���***************
Enumeration<User> d;
d = DataProcessing.getAllUser();
int index = 0;//DataProcessing.users.size();
while(d.hasMoreElements()){
	index++;
}
for (int i = 0; i < index; i++) {
	User a2 = d.nextElement();
	userNameChoose_change.addItem(a2.getName());
}
userNameChoose_change.setSelectedItem(null);
//****�����е� �û���Ϣ��ӵ� �����˵���*****************//
userNameChoose_change.setBounds(166, 27, 132, 24);
changeUserInfo.add(userNameChoose_change);

userNewPassword_input = new JTextField();
userNewPassword_input.setBounds(166, 69, 132, 24);
changeUserInfo.add(userNewPassword_input);
userNewPassword_input.setColumns(10);

//************ʹ�á�������ǩ��չʾȫ������ɫ��***********************//
final JComboBox userRoleChoose_change = new JComboBox();
userRoleChoose_change.setModel(new DefaultComboBoxModel(new String[] {"administrator", "operator", "browser"}));
userRoleChoose_change.setBounds(166, 111, 132, 24);
changeUserInfo.add(userRoleChoose_change);

//***********************������޸ġ���ť��ִ���û���Ϣ�޸Ĳ���*******************************//
JButton suer_change = new JButton("�޸�");  //���޸ġ��û���Ϣ��ť
suer_change.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		String abc = null;
		Administrator change = new Administrator(abc, abc, abc);
		
		String role;
		role = userNameChoose_change.getSelectedItem().toString();
		//System.out.println(role);  //����ʹ��  .getSelectedItem().toString()  ���ص�ǰѡ�е������б���ַ���
		//System.out.println(userNewPassword_input.getSelectionStart());  //����ʹ�� .getSelectionStart()����������ַ�����
		System.out.println(userNameChoose_change.getSelectedItem().toString());
		if(userNewPassword_input.getSelectionStart()!=0 && userNameChoose_change.getSelectedItem().toString() != null) {
			if(change.changeUserInfo(userNameChoose_change.getSelectedItem().toString(), userNewPassword_input.getText(), userRoleChoose_change.getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(null, "��Ϣ�޸ĳɹ���");
				changeUserInfo.add(userNameChoose_change);
				
				//������ˢ�²������ر�ҳ������¼��أ�����ˢ����Ϣ
				dispose();   //�ر�
				UserManageFrame changeuser;  //���¶��岢��ʾ
				try {
					changeuser = new UserManageFrame();
					changeuser.setVisible(true);
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			};
		}
		else {
			JOptionPane.showMessageDialog(null, "������������");
		}//else  if		
	}
});
suer_change.setBounds(80, 163, 96, 27);
changeUserInfo.add(suer_change);

//*******************�����ȡ������ť��ȡ�����������رյ�ǰ��������*********************//
JButton cancel_change = new JButton("ȡ��");
cancel_change.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		UserManageFrame.this.dispose(); //�رմ���
	}
});
cancel_change.setBounds(202, 163, 96, 27);
changeUserInfo.add(cancel_change);


//*************************��ɾ���û�����ǩ�Լ����ִ�в���*********************//
JPanel deleteUser = new JPanel();
tabbedPane.addTab("ɾ���û�", null, deleteUser, null);
deleteUser.setLayout(null);

JLabel userDelName = new JLabel("��ѡ���û���");
userDelName.setBounds(71, 47, 90, 18);
deleteUser.add(userDelName);

final JComboBox userDelNameList = new JComboBox();
Enumeration<User> d1;
d1 = DataProcessing.getAllUser();
int index1 = 0;//DataProcessing.users.size();
while(d1.hasMoreElements()){
	index1++;
}
for (int i = 0; i < index1; i++) {
	User a1 = d1.nextElement();
	System.out.println(a1.getName());
	userDelNameList.addItem(a1.getName());
}
userDelNameList.setSelectedItem(null);
userDelNameList.setBounds(187, 44, 127, 24);
deleteUser.add(userDelNameList);

/***********************ִ�� ɾ���û�  ����******************************************/
JButton sure_del = new JButton("ɾ��");  //ȷ�ϡ�ɾ�����û���ť
sure_del.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		String abc = null;
		Administrator del = new Administrator(abc, abc, abc);
		if(del.delUser(userDelNameList.getSelectedItem().toString())) {
			System.out.println(userDelNameList.getSelectedItem().toString());
			JOptionPane.showMessageDialog(null, "�û� "+userDelNameList.getSelectedItem().toString()+" ɾ���ɹ���");
			
			//������ˢ�²������ر�ҳ������¼��أ�����ˢ����Ϣ
			dispose();   //�ر�
			UserManageFrame changeuser;  //���¶��岢��ʾ
			try {
				changeuser = new UserManageFrame();
				changeuser.setVisible(true);
				changeuser.setResizable(false);
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			
		}	
	}
});
sure_del.setBounds(71, 114, 113, 27);
deleteUser.add(sure_del);

JButton cancel_del = new JButton("ȡ��");
cancel_del.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		UserManageFrame.this.dispose(); //�رմ���
	}
	
});
cancel_del.setBounds(201, 114, 113, 27);
deleteUser.add(cancel_del);


//**********************������û�����ǩ�Լ����ִ�в���*********************//
JPanel addUser = new JPanel();
tabbedPane.addTab("�����û�", null, addUser, null);
addUser.setLayout(null);


JLabel userName_add = new JLabel("�û���");
userName_add.setBounds(82, 30, 72, 18);
addUser.add(userName_add);

JLabel userPassword_add = new JLabel("����");
userPassword_add.setBounds(82, 73, 72, 18);
addUser.add(userPassword_add);

JLabel userRole_add = new JLabel("��ɫ");
userRole_add.setBounds(82, 114, 72, 18);
addUser.add(userRole_add);

userName_input = new JTextField();
userName_input.setBounds(168, 27, 155, 24);
addUser.add(userName_input);
userName_input.setColumns(10);

userPassword_input = new JTextField();
userPassword_input.setBounds(168, 70, 155, 24);
addUser.add(userPassword_input);
userPassword_input.setColumns(10);

final JComboBox userRole_list = new JComboBox();
userRole_list.setModel(new DefaultComboBoxModel(new String[] {"administrator", "operator", "browser"}));
userRole_list.setToolTipText("");
userRole_list.setBounds(168, 111, 155, 24);
addUser.add(userRole_list);
JButton sure_add = new JButton("���");  //����ӡ��û���ť
sure_add.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		String abc = null;
		Administrator add = new Administrator(abc, abc, abc);
		
		//System.out.println(userRole_list.getSelectedIndex());   //��������
		
		if(userName_input.getSelectionEnd()!=0 && userPassword_input.getSelectionEnd()!=0 ) {
			if(add.addUser(userName_input.getText(),userPassword_input.getText(),userRole_list.getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(null, "�û�"+userName_input.getText()+"��ӳɹ���");
				
				//������ˢ�²������ر�ҳ������¼��أ�����ˢ����Ϣ
				dispose();   //�ر�
				UserManageFrame changeuser;  //���¶��岢��ʾ
				try {
					changeuser = new UserManageFrame();
					changeuser.setVisible(true);
					changeuser.setResizable(false);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "�û��Ѵ��ڣ�����");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "�û����ʧ�ܣ�");
		}
		
	}
});
sure_add.setBounds(82, 164, 102, 27);
addUser.add(sure_add);

JButton cancel_add = new JButton("ȡ��");
cancel_add.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		UserManageFrame.this.dispose(); //�رմ���
	}
});
cancel_add.setBounds(221, 164, 102, 27);
addUser.add(cancel_add);

}
}
