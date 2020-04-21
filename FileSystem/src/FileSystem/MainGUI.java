package FileSystem;
import java.awt.Event;
import java.awt.EventQueue;
import java.sql.SQLException;

import Frame.UserFrame;
import Frame.UserLogin;

@SuppressWarnings("unused")
public class MainGUI {
	public static void main(String[] args) {
		/*启动登录界面*/
		String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document";//数据库的url;
		String user="root";//数据库用户名
		String password="";//数据库密码
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
