package FileSystem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;
public class Administrator extends User{
	public Administrator(String name,String password,String role){
		super(name,password,role);
	}
    //1文件列表
	//2下载文件
	public boolean downloadFiles(String ID){
		try{
			if(downloadFile(ID))
				return true;
			else
				return false;
		}catch(IOException e){
			System.out.println("文件信息访问错误"+e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//3修改个人密码
	public boolean changeSelfPassword(String newpassword){
		try{
			if(changeSelfInfo(newpassword))return true;
			else
				return false;	
		}catch(SQLException e){
			System.out.println("密码信息访问错误"+e.toString());
		}
		return false;
	}
	
//4修改用户信息
public boolean changeUserInfo(String changename,String changepassword,String changerole){
	try{
		return DataProcessing.updateUser(changename, changepassword, changerole);
	}catch(SQLException e){
		System.out.println("用户密码访问错误"+e.toString());
		}
	return false;
}
	
	//5添加新用户
	public boolean addUser(String name,String password,String role){
		try{
			return DataProcessing.insertUser(name, password, role);
		}catch(SQLException e){
			System.out.println("添加用户访问错误"+e.toString());
		}
		return false;
	}
	//6删除用户
	public boolean delUser(String delname){
		try{
			return DataProcessing.deleteUser(delname);
		}catch(SQLException e){
			System.out.println("用户信息访问错误"+e.toString());
		}
		return false;
	}
	//7列出全部用户
	public void listUser()throws SQLException{//利用枚举类Enumeration从一个数据结构中得到连续的数据
		for(Enumeration<User>a=DataProcessing.getAllUser();
				a.hasMoreElements();)
			System.out.println(a.nextElement());
	}
	//菜单
	//1显示文件列表
	public void showFilesList()
	{
		try{
		showFileList();
	}catch(SQLException e){
		System.out.println("文件访问错误"+e.toString());
	}
		
	}
	//2显示下载文件
	public void showdownloadFiles(Scanner scan) throws SQLException{
		System.out.print("请输入要下载的文件名：");
		String filename=scan.nextLine();
	if(downloadFiles(filename))
	System.out.println("下载成功");
else System.out.println("下载失败");
	}
	
	//3显示修改个人密码
	public void showchangeSelfPassword(Scanner scan){
		System.out.print("请输入新密码");
		String newpassword=scan.nextLine();
		if(changeSelfPassword(newpassword))
			System.out.println("密码修改成功");
		else
			System.out.println("密码修改失败");
		
	}


	//4显示修改用户信息
	public void showchangeUserInfo(Scanner scan){
		System.out.print("请输入需要修改的用户的姓名:");
		String changename=scan.nextLine();
		System.out.print("请输入要修改的用户的密码");
		String changepassword=scan.nextLine();
		System.out.print("请输入要修改的用户的类型（administrator/operator/Browser)");
		String changerole=scan.nextLine();
		if(changeUserInfo(changename,changepassword,changerole))
		System.out.println("修改成功");
		else System.out.println("修改失败");
		}
	

	//5显示添加新用户
	public void showaddUser(Scanner scan){
		System.out.print("请输入需要添加的用户的姓名:");
		String username=scan.nextLine();
		System.out.print("请输入要添加的用户的密码");
		String userpassword=scan.nextLine();
		System.out.print("请输入要添加的用户的类型（administrator/operator/Browser)");
		String userrole=scan.nextLine();
		if(addUser(username,userpassword,userrole))
		System.out.println("添加成功");
		else System.out.println("添加失败");
		
	}
	//6显示删除用户
	public void showdelUser(Scanner scan){
		System.out.print("请输入要删除的用户的姓名：");
		String deletename=scan.nextLine();
		if(delUser(deletename)) System.out.println("用户删除成功");
		else System.out.println("用户删除失败");
		
	}
	

	//7显示列出全部用户
	public void showlistUser(){
		try{
			listUser();
		}catch(SQLException e){
			System.out.println("用户信息访问错误"+e.toString());
		}
	}
	
	public void showMenu(){
		Scanner scan=new Scanner(System.in);
		String input=null;
		int nextInt=0;
		do{
			System.out.print("——————尊敬的系统管理者，您好——————\n"
					          + "请选择以下操作：\n"
					          + "1、显示文件列表\n"
					          + "2、下载文件\n"
					          + "3、修改个人密码\n"
					          + "4、修改用户信息\n"
					          + "5、添加新用户\n"
					          + "6、删除用户\n"
					          + "7、列出全部用户\n"
					          + "8、退出系统"
					          + "—————————————————————————————— ");
			System.out.print("请输入要进行的操作选项:\n");
			input=scan.next().trim();
			if(!(input).matches("[1-8]")){
				System.err.println("输入错误，请重新输入");
			}
			else{
				nextInt=Integer.parseInt(input);//将用户输入的内容转换为整型字符串
			}
			switch(nextInt)
			{
			case'1':showFilesList();break;
			case'2':try {
					showdownloadFiles(scan);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}break;
			case'3':showchangeSelfPassword(scan);break;
			case'4':showchangeUserInfo(scan);break;
			case'5':showaddUser(scan);break;
			case'6':showdelUser(scan);break;
			case'7':showlistUser();break;
			case'8':exitSystem();scan.close();break;
			default:System.out.println("您输入的选项有误");break;
			
			
			}
		}while(nextInt!=8);
		
	}
	
	
}

