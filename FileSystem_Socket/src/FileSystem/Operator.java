package FileSystem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Operator extends User{
	
	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	
	//修改自身密码
	public boolean changeSelfPassword(String newpassword){
		try{
			if(changeSelfInfo(newpassword))return true;
			else return false;
		}catch(SQLException e){
			System.out.println("密码访问错误"+e.toString());
		}
		return false;
	}
	//下载文件
	public boolean downloadFiles(String ID,String a)throws SQLException{
		try{
			if(downloadFile(ID,a))
				return true;
			else
				return false;
		}catch(IOException e){
			System.out.println("文件信息访问错误"+e.toString());
		}
		return false;
	}
	//上传文件
	public boolean uploadFile(String ID,String filename,String description,String filepath) throws IOException, SQLException{
		if(uploadFile(ID,filename,description,filepath)) {
			System.out.println("上传成功！");
			return true;
		}else System.out.println("文件路径错误或文件不存在！");
		return false;
		
	}
	
	//目录1显示文件列表
	public void showFilesList(){
		try{
			showFileList();
		}catch(SQLException e){
			System.out.println("文件访问列表访问错误"+e.toString());
		}
	}
	//目录2下载文件
	public void showdownloadFiles(Scanner scan)
	{
	System.out.println("请输入要下载的文件名：");
	String ID=scan.next();
	try {
		if(downloadFiles(ID,ID))
			System.out.println("下载成功");
		else
			System.out.print("下载失败");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	//目录3上传文件
	public void showuploadFile(Scanner scan)
	{
		System.out.print("请输入文件地址：");
		String address=scan.next();
		System.out.print("请输入文件档案号：");
    	String ID=scan.next();
    	System.out.print("请输入文件名称：");
		String filename = scan.next();
		System.out.print("请输入文件描述：");
		String description = scan.next();
		try {
			uploadFile(ID,filename,description,address);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//目录4修改个人密码
		public void showchangeSelfPassword(Scanner scan){
			System.out.print("请输入新密码");
			String newpassword=scan.next();
			if(changeSelfPassword(newpassword))
				System.out.println("修改成功");
			else
				System.out.print("修改失败");
		}
	//菜单
	public void showMenu()throws SQLException{
		Scanner scan=new Scanner(System.in);
		String input = null;
		int nextInt = 0;
		
		do{
			System.out.println("————————尊敬的档案录入者，您好————————\n"
					+ "请选择以下操作："
					+ "1、显示文件列表\n"
					+ "2、下载文件\n"
					+ "3、上传文件\n"
					+ "4、修改个人密码\n"
					+ "5、退出系统\n"
					+ "———————————————————————————————————");
			System.out.print("请输入您要进行的操作：");
			input=scan.next().trim();
			if(!(input).matches("[1-8]")) {       //也可以写成：if(!(input).matches("1|2|3|4|5|6|7"));
				System.err.print("输入错误，请重新输入！");
			}else {
				nextInt = Integer.parseInt(input);//将用户输入的字符串转换成整形数字
		
			switch(nextInt){
			case 1:showFilesList();break;
			case 2:showdownloadFiles(scan);break;
			case 3:showuploadFile(scan);break;
			case 4:showchangeSelfPassword(scan);break;
			case 5:exitSystem();scan.close();break;

			}
		}
		
		
	}while(nextInt!=5);

}}