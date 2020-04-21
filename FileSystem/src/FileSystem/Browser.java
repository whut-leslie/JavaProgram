package FileSystem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Browser extends User{
	Browser(String name,String password,String role){
		super(name,password,role);
	}
	//下载文件
     public boolean downloadFiles(String ID){
		
			try{
				try {
					if(downloadFile(ID)) 
						return true;
					else 
						return false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(IOException e){
				System.out.println("文件信息访问错误"+e.toString());
				}
			return false;
		}
		//修改个人密码
		public boolean changeSelfPassword(String newpassword){
			try{
				if(changeSelfInfo(newpassword))
					return true;
				else return false;
			}catch(SQLException e){
				System.out.println("密码信息访问错误"+e.toString());
				}
				return false;	
		}
		//目录1文件列表
		public void showFilesList(){
			try{
				showFileList();
			}catch(SQLException e){
				System.out.println("文件列表访问错误"+e.toString());
			}
		}
		//目录2下载文件
		public void showdownloadFiles(Scanner scan){
			System.out.print("请输入要下载的文件名：");
			String filename=scan.next();
			if(downloadFiles(filename))
				System.out.println("下载成功");
			else
				System.out.println("下载失败");	
		}
		//目录3修改个人密码
		public void changeSelfPassword(Scanner scan){
			System.out.print("请输入新密码");
			String newpassword=scan.next();
			if(changeSelfPassword(newpassword))
				System.out.print("密码修改成功");
			else 
				System.out.print("密码修改失败");
		}

		public void showMenu()throws SQLException{
			Scanner scan=new Scanner(System.in);
			String input=null;
			int nextInt=0;
			do{
				System.out.println("———————尊敬的档案浏览者，您好——————\n"
						+ "请选择以下操作：\n"
						+ "1、显示文件列表\n"
						+ "2、下载文件\n"
						+ "3、修改个人密码\n"
						+ "4、退出系统\n"
						+ "——————————————————————————————————");
				System.out.print("请输入您要进行的操作:");
				input=scan.next().trim();
				if(!(input).matches("[1-8]")){
					System.err.println("输入错误，请重新输入");
				}
				else{
					nextInt=Integer.parseInt(input);//将用户输入的字符串转化为整型数字
				switch(nextInt)
				{
				case 1:showFilesList();break;
				case 2:showdownloadFiles(scan);break;
				case 3:changeSelfPassword(scan);break;
				case 4:exitSystem();scan.close();break;
				}
				}
	
			}while(nextInt!=4);
			}
	}
