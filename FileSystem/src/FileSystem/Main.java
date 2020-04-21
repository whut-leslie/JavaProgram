package FileSystem;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws SQLException{
		String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document";//���ݿ��url;
		String user="root";//���ݿ��û���
		String b="";//����
		try {
			DataProcessing.connectToDatabase(driverName, url, user, b);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();}
		
		System.out.println("������������������ӭʹ���ļ�����ϵͳ����������������\n"
				+ "1����¼\n"
				+ "2���˳�\n"
				+ "������������������������������������������������������������\n"
				+ "��ѡ��˵���");
		Scanner scan=new Scanner(System.in);
		int i=scan.nextInt();
		do{
		if(i==1){
			System.out.print("�������û���������");
			String name=scan.next();
			System.out.print("�������û������룺");
			String password=scan.next();
			User usr = null;
			try{
				usr=DataProcessing.searchUser(name,password);
			}catch(SQLException e){
				System.out.println("���ݿ����:"+e.toString());
			}
			if(usr==null){
				System.out.println("�û������������");
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
			System.out.println("ϵͳ�˳���ллʹ��");
			scan.close();
		}
		
		
	}while(i!=2);
		}
	}
