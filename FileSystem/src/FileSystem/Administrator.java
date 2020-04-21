package FileSystem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;
public class Administrator extends User{
	public Administrator(String name,String password,String role){
		super(name,password,role);
	}
    //1�ļ��б�
	//2�����ļ�
	public boolean downloadFiles(String ID){
		try{
			if(downloadFile(ID))
				return true;
			else
				return false;
		}catch(IOException e){
			System.out.println("�ļ���Ϣ���ʴ���"+e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//3�޸ĸ�������
	public boolean changeSelfPassword(String newpassword){
		try{
			if(changeSelfInfo(newpassword))return true;
			else
				return false;	
		}catch(SQLException e){
			System.out.println("������Ϣ���ʴ���"+e.toString());
		}
		return false;
	}
	
//4�޸��û���Ϣ
public boolean changeUserInfo(String changename,String changepassword,String changerole){
	try{
		return DataProcessing.updateUser(changename, changepassword, changerole);
	}catch(SQLException e){
		System.out.println("�û�������ʴ���"+e.toString());
		}
	return false;
}
	
	//5������û�
	public boolean addUser(String name,String password,String role){
		try{
			return DataProcessing.insertUser(name, password, role);
		}catch(SQLException e){
			System.out.println("����û����ʴ���"+e.toString());
		}
		return false;
	}
	//6ɾ���û�
	public boolean delUser(String delname){
		try{
			return DataProcessing.deleteUser(delname);
		}catch(SQLException e){
			System.out.println("�û���Ϣ���ʴ���"+e.toString());
		}
		return false;
	}
	//7�г�ȫ���û�
	public void listUser()throws SQLException{//����ö����Enumeration��һ�����ݽṹ�еõ�����������
		for(Enumeration<User>a=DataProcessing.getAllUser();
				a.hasMoreElements();)
			System.out.println(a.nextElement());
	}
	//�˵�
	//1��ʾ�ļ��б�
	public void showFilesList()
	{
		try{
		showFileList();
	}catch(SQLException e){
		System.out.println("�ļ����ʴ���"+e.toString());
	}
		
	}
	//2��ʾ�����ļ�
	public void showdownloadFiles(Scanner scan) throws SQLException{
		System.out.print("������Ҫ���ص��ļ�����");
		String filename=scan.nextLine();
	if(downloadFiles(filename))
	System.out.println("���سɹ�");
else System.out.println("����ʧ��");
	}
	
	//3��ʾ�޸ĸ�������
	public void showchangeSelfPassword(Scanner scan){
		System.out.print("������������");
		String newpassword=scan.nextLine();
		if(changeSelfPassword(newpassword))
			System.out.println("�����޸ĳɹ�");
		else
			System.out.println("�����޸�ʧ��");
		
	}


	//4��ʾ�޸��û���Ϣ
	public void showchangeUserInfo(Scanner scan){
		System.out.print("��������Ҫ�޸ĵ��û�������:");
		String changename=scan.nextLine();
		System.out.print("������Ҫ�޸ĵ��û�������");
		String changepassword=scan.nextLine();
		System.out.print("������Ҫ�޸ĵ��û������ͣ�administrator/operator/Browser)");
		String changerole=scan.nextLine();
		if(changeUserInfo(changename,changepassword,changerole))
		System.out.println("�޸ĳɹ�");
		else System.out.println("�޸�ʧ��");
		}
	

	//5��ʾ������û�
	public void showaddUser(Scanner scan){
		System.out.print("��������Ҫ��ӵ��û�������:");
		String username=scan.nextLine();
		System.out.print("������Ҫ��ӵ��û�������");
		String userpassword=scan.nextLine();
		System.out.print("������Ҫ��ӵ��û������ͣ�administrator/operator/Browser)");
		String userrole=scan.nextLine();
		if(addUser(username,userpassword,userrole))
		System.out.println("��ӳɹ�");
		else System.out.println("���ʧ��");
		
	}
	//6��ʾɾ���û�
	public void showdelUser(Scanner scan){
		System.out.print("������Ҫɾ�����û���������");
		String deletename=scan.nextLine();
		if(delUser(deletename)) System.out.println("�û�ɾ���ɹ�");
		else System.out.println("�û�ɾ��ʧ��");
		
	}
	

	//7��ʾ�г�ȫ���û�
	public void showlistUser(){
		try{
			listUser();
		}catch(SQLException e){
			System.out.println("�û���Ϣ���ʴ���"+e.toString());
		}
	}
	
	public void showMenu(){
		Scanner scan=new Scanner(System.in);
		String input=null;
		int nextInt=0;
		do{
			System.out.print("�������������𾴵�ϵͳ�����ߣ����á�����������\n"
					          + "��ѡ�����²�����\n"
					          + "1����ʾ�ļ��б�\n"
					          + "2�������ļ�\n"
					          + "3���޸ĸ�������\n"
					          + "4���޸��û���Ϣ\n"
					          + "5��������û�\n"
					          + "6��ɾ���û�\n"
					          + "7���г�ȫ���û�\n"
					          + "8���˳�ϵͳ"
					          + "������������������������������������������������������������ ");
			System.out.print("������Ҫ���еĲ���ѡ��:\n");
			input=scan.next().trim();
			if(!(input).matches("[1-8]")){
				System.err.println("�����������������");
			}
			else{
				nextInt=Integer.parseInt(input);//���û����������ת��Ϊ�����ַ���
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
			default:System.out.println("�������ѡ������");break;
			
			
			}
		}while(nextInt!=8);
		
	}
	
	
}

