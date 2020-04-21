package FileSystem;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws SQLException{
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
			User usr=null;
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
					usr.showMenu();
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
