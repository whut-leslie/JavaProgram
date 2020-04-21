package FileSystem;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws SQLException{
		String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document";//数据库的url;
		String user="root";//数据库用户名
		String b="";//密码
		try {
			DataProcessing.connectToDatabase(driverName, url, user, b);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();}
		
		System.out.println("————————欢迎使用文件管理系统————————\n"
				+ "1、登录\n"
				+ "2、退出\n"
				+ "——————————————————————————————\n"
				+ "请选择菜单：");
		Scanner scan=new Scanner(System.in);
		int i=scan.nextInt();
		do{
		if(i==1){
			System.out.print("请输入用户的姓名：");
			String name=scan.next();
			System.out.print("请输入用户的密码：");
			String password=scan.next();
			User usr = null;
			try{
				usr=DataProcessing.searchUser(name,password);
			}catch(SQLException e){
				System.out.println("数据库错误:"+e.toString());
			}
			if(usr==null){
				System.out.println("用户名或密码错误");
			}
				else
				{
					String role=usr.getRole();
					
					if(role=="operator"){
						Operator operator=new Operator(name,password,role);
						operator.showMenu();
						
					}else if(role=="admistrator"){
						Administrator admistrator=new Administrator(name,password,role);
						admistrator.showMenu();
						
					}else{
						Browser browser=new Browser(name,password,role);
						browser.showMenu();
					}
				}
			}
		if(i==2)
		{
			System.out.println("系统退出，谢谢使用");
			scan.close();
		}
		
		
	}while(i!=2);
		}
	}
