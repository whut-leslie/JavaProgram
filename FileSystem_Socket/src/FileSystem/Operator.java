package FileSystem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Operator extends User{
	
	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	
	//�޸���������
	public boolean changeSelfPassword(String newpassword){
		try{
			if(changeSelfInfo(newpassword))return true;
			else return false;
		}catch(SQLException e){
			System.out.println("������ʴ���"+e.toString());
		}
		return false;
	}
	//�����ļ�
	public boolean downloadFiles(String ID,String a)throws SQLException{
		try{
			if(downloadFile(ID,a))
				return true;
			else
				return false;
		}catch(IOException e){
			System.out.println("�ļ���Ϣ���ʴ���"+e.toString());
		}
		return false;
	}
	//�ϴ��ļ�
	public boolean uploadFile(String ID,String filename,String description,String filepath) throws IOException, SQLException{
		if(uploadFile(ID,filename,description,filepath)) {
			System.out.println("�ϴ��ɹ���");
			return true;
		}else System.out.println("�ļ�·��������ļ������ڣ�");
		return false;
		
	}
	
	//Ŀ¼1��ʾ�ļ��б�
	public void showFilesList(){
		try{
			showFileList();
		}catch(SQLException e){
			System.out.println("�ļ������б���ʴ���"+e.toString());
		}
	}
	//Ŀ¼2�����ļ�
	public void showdownloadFiles(Scanner scan)
	{
	System.out.println("������Ҫ���ص��ļ�����");
	String ID=scan.next();
	try {
		if(downloadFiles(ID,ID))
			System.out.println("���سɹ�");
		else
			System.out.print("����ʧ��");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	//Ŀ¼3�ϴ��ļ�
	public void showuploadFile(Scanner scan)
	{
		System.out.print("�������ļ���ַ��");
		String address=scan.next();
		System.out.print("�������ļ������ţ�");
    	String ID=scan.next();
    	System.out.print("�������ļ����ƣ�");
		String filename = scan.next();
		System.out.print("�������ļ�������");
		String description = scan.next();
		try {
			uploadFile(ID,filename,description,address);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Ŀ¼4�޸ĸ�������
		public void showchangeSelfPassword(Scanner scan){
			System.out.print("������������");
			String newpassword=scan.next();
			if(changeSelfPassword(newpassword))
				System.out.println("�޸ĳɹ�");
			else
				System.out.print("�޸�ʧ��");
		}
	//�˵�
	public void showMenu()throws SQLException{
		Scanner scan=new Scanner(System.in);
		String input = null;
		int nextInt = 0;
		
		do{
			System.out.println("�����������������𾴵ĵ���¼���ߣ����á���������������\n"
					+ "��ѡ�����²�����"
					+ "1����ʾ�ļ��б�\n"
					+ "2�������ļ�\n"
					+ "3���ϴ��ļ�\n"
					+ "4���޸ĸ�������\n"
					+ "5���˳�ϵͳ\n"
					+ "����������������������������������������������������������������������");
			System.out.print("��������Ҫ���еĲ�����");
			input=scan.next().trim();
			if(!(input).matches("[1-8]")) {       //Ҳ����д�ɣ�if(!(input).matches("1|2|3|4|5|6|7"));
				System.err.print("����������������룡");
			}else {
				nextInt = Integer.parseInt(input);//���û�������ַ���ת������������
		
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