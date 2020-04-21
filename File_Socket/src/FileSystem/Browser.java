package FileSystem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Browser extends User{
	Browser(String name,String password,String role){
		super(name,password,role);
	}
	//�����ļ�
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
				System.out.println("�ļ���Ϣ���ʴ���"+e.toString());
				}
			return false;
		}
		//�޸ĸ�������
		public boolean changeSelfPassword(String newpassword){
			try{
				if(changeSelfInfo(newpassword))
					return true;
				else return false;
			}catch(SQLException e){
				System.out.println("������Ϣ���ʴ���"+e.toString());
				}
				return false;	
		}
		//Ŀ¼1�ļ��б�
		public void showFilesList(){
			try{
				showFileList();
			}catch(SQLException e){
				System.out.println("�ļ��б���ʴ���"+e.toString());
			}
		}
		//Ŀ¼2�����ļ�
		public void showdownloadFiles(Scanner scan){
			System.out.print("������Ҫ���ص��ļ�����");
			String filename=scan.next();
			if(downloadFiles(filename))
				System.out.println("���سɹ�");
			else
				System.out.println("����ʧ��");	
		}
		//Ŀ¼3�޸ĸ�������
		public void changeSelfPassword(Scanner scan){
			System.out.print("������������");
			String newpassword=scan.next();
			if(changeSelfPassword(newpassword))
				System.out.print("�����޸ĳɹ�");
			else 
				System.out.print("�����޸�ʧ��");
		}

		public void showMenu()throws SQLException{
			Scanner scan=new Scanner(System.in);
			String input=null;
			int nextInt=0;
			do{
				System.out.println("���������������𾴵ĵ�������ߣ����á�����������\n"
						+ "��ѡ�����²�����\n"
						+ "1����ʾ�ļ��б�\n"
						+ "2�������ļ�\n"
						+ "3���޸ĸ�������\n"
						+ "4���˳�ϵͳ\n"
						+ "��������������������������������������������������������������������");
				System.out.print("��������Ҫ���еĲ���:");
				input=scan.next().trim();
				if(!(input).matches("[1-8]")){
					System.err.println("�����������������");
				}
				else{
					nextInt=Integer.parseInt(input);//���û�������ַ���ת��Ϊ��������
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
