package FileSystem;
import java.awt.Event;
import java.awt.EventQueue;
import java.sql.SQLException;

import Frame.UserFrame;
import Frame.UserLogin;

@SuppressWarnings("unused")
public class MainGUI {
	public static void main(String[] args) {
		/*������¼����*/
		String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document";//���ݿ��url;
		String user="root";//���ݿ��û���
		String password="";//���ݿ�����
		try {
			DataProcessing.connectToDatabase(driverName,url,user,password);
		}catch(ClassNotFoundException e)
					{
			e.printStackTrace();
					}catch (SQLException e) {
						e.printStackTrace();
					}
			@SuppressWarnings("rawtypes")
			UserLogin frame=new UserLogin();
			frame.showMenu();
	
}}
