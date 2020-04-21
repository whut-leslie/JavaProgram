package FileSystem;
import java.sql.SQLException;
import java.util.Enumeration;
import java.sql.Timestamp;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
public abstract class User {
private String name;
private String password;
private String role;
String uploadpaths="E:\\OOP\\uploadfile\\";
String downloadpaths="E:\\OOP\\downloadfile\\";
User(String name,String password,String role){
	this.name=name;
	this.password=password;
	this.role=role;
}

public boolean changeSelfInfo(String password)throws SQLException{
	//д�û���Ϣ���洢
	if (DataProcessing.updateUser(name, password, role)){
		this.password=password;
		System.out.println("�޸ĳɹ�");
		return true;
	}else
		return false;
}


@SuppressWarnings("unused")
public boolean downloadFile(String ID)throws IOException,SQLException{
	try {
		byte[] buffer=new byte[1024];
		Doc doc=DataProcessing.searchDoc(ID);
		String  uploadpath=uploadpaths+doc.getFilename();
		String downloadpath=downloadpaths+doc.getFilename();
		if(doc==null) { 
			return false;}
		File tempFile=new File(uploadpath+doc.getFilename());
		String filename=tempFile.getName();
		BufferedInputStream infile=new BufferedInputStream(new FileInputStream(uploadpath) );
		BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(downloadpath) );
		
		while(true)
		{
			int byteRoad=infile.read(buffer);//���ļ����ݸ��ֽ�����
			if(byteRoad==-1)//���ļ�β�������ݿɶ�
				break;
			targetfile.write(buffer, 0, byteRoad);//������������д��Ŀ���ļ�
		}
		infile.close();
		targetfile.close();
		return true;
		
	}catch(Exception e) {
		System.out.println("�����ļ���������"+e.toString()); 
		//e.printStackTrace(); 
	}
	return false;
}
//�ϴ��ļ�
	public boolean uploadFile(String ID,String filename,String description,String filepath) throws IOException, SQLException{
		try {  
	    	  // String targetPath=uploadpaths;
	            @SuppressWarnings("unused")
				int bytesum = 0;  
	            int byteread = 0;  
	            File oldfile = new File(filepath);  
	            String targetfile = uploadpaths + File.separator +  oldfile.getName();  
	            if (oldfile.exists()) { //�ļ�����ʱ  
	                InputStream inStream = new FileInputStream(filepath); //����ԭ�ļ�  
	                @SuppressWarnings("resource")
					FileOutputStream fs = new FileOutputStream(targetfile);  
	                byte[] buffer = new byte[1444];  
	                while ((byteread = inStream.read(buffer)) != -1) {  
	                    bytesum += byteread; //�ֽ��� �ļ���С  
	                    fs.write(buffer, 0, byteread);  
	                }  
	                inStream.close(); 
	                Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
	                DataProcessing.insertDoc(ID, getName(), timestamp, description, filename);
	                return true;
	                
	            }  
	            else return false;
	            
	        } catch (Exception e) {  
	        	System.out.println("�ϴ��ļ���������"+e.toString()); 
	           // e.printStackTrace();  
	        } 
	     return false;
	}
	
	//��ʾ�����ļ�
public void showFileList()throws SQLException{
	Enumeration<Doc> e= DataProcessing.getAllDocs();
	Doc doc;
	while (e.hasMoreElements()) {
		doc=e.nextElement();
		System.out.println("ID��"+doc.getID()+"\t\t Creator��"+doc.getCreator()+"\t\tTime��"+doc.getTimestamp());
		System.out.println("Description��"+doc.getDescription());
	}
	
}
public static User getUser(String s1,String s2,String s3){
	if (s3.equals("operator"))
		return new Operator(s1, s2, s3);
	else if (s3.equals("browser"))
		return new Browser(s1, s2, s3);
	else if (s3.equals("administrator"))
		return new Administrator(s1, s2, s3);
	return null;
}

public void exitSystem(){
	System.out.println("ϵͳ�˳�, ллʹ�� ! ");
	System.exit(0);
}

public String getName(){
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
			}
public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public abstract void showMenu() throws SQLException;


}
