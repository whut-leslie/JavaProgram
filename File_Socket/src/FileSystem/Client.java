package FileSystem;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Frame.UserFrame;

public class Client extends JFrame {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String user_role="";
private static JFrame Jframe;
   private static DataOutputStream output; 
   private static DataInputStream input; 
   private String message = ""; 
   private static String user_name="";
   private static String user_password="";
   private String chatServer; 
   private static Socket client;
   private static int row;
   private static int row2;
   private static String[][] UserData;
   private static String[][] DocData;
   public Client( String host ){
      super( "Client" );
   }
   public void runClient(){
      try{
         connectToServer(); 
         getStreams(); 
         processConnection(); 
      }
      catch ( EOFException eofException ) {
         displayMessage( "Client terminated connection" );
      } 
      catch ( IOException ioException ){
         ioException.printStackTrace();
      } 
      finally{
         try {
			closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} 
      } 
   } 
   private void connectToServer() throws IOException{      
      displayMessage( "Attempting connection" );
      client = new Socket( InetAddress.getByName( chatServer ), 12345 );
      displayMessage( "Connected to: " + 
         client.getInetAddress().getHostName() );
   }
   private void getStreams() throws IOException{
      output = new DataOutputStream( client.getOutputStream() );      
      output.flush(); 
      input = new DataInputStream( client.getInputStream() );
      displayMessage( "Got I/O streams" );
   } 
   private void processConnection() throws IOException{
      do { 
         message = input.readUTF(); 
         if(message.equals("LOGIN_TRUE")) {
        	 user_role=input.readUTF();
        	 UserFrame menuWindow=new UserFrame();
			 Jframe.dispose();	 
         }
         else if(message.equals("LOGIN_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "账号或密码错误", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("SELFCHANGE_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
        	 String password=input.readUTF();
        	 user_password=password;
        	 System.out.println("SELFCHANGE_SUCCESS");
         }
         else if(message.equals("SELFCHANGE_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "修改失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("displayedUser")) {
        	 int i=0;
        	 UserData=new String[50][3];
        	 i=input.readInt();
        	 for(int j=0;j<i;j++) {
        		 UserData[j][0]=input.readUTF();
        		 UserData[j][1]=input.readUTF();
        		 UserData[j][2]=input.readUTF();
        	 }
        	 row=i;
         }
         else if(message.equals("displayedDoc")) {
        	 int i=0;
        	 DocData=new String[50][5];
        	 i=input.readInt();
        	 for(int j=0;j<i;j++) {
        		 DocData[j][0]=input.readUTF();
        		 DocData[j][1]=input.readUTF();
        		 DocData[j][2]=input.readUTF();
        		 DocData[j][3]=input.readUTF();
        		 DocData[j][4]=input.readUTF();
        	 }
        	 row2=i;
         }
         else if(message.equals("DELETE_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "删除成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 Jframe.dispose();
			 System.out.println("DELETE_SUCCESS");
         }
         else if(message.equals("DELETE_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("ADD_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "添加成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 Jframe.dispose();
			 System.out.println("ADD_SUCCESS");
         }
         else if(message.equals("ADD_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "添加失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("UPDATE_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 Jframe.dispose();
			 System.out.println("UPDATE_SUCCESS");
         }
         else if(message.equals("UPDATE_FLASE")) {
        	 JOptionPane.showMessageDialog(null, "修改失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("UPLOAD_TRUE")) {
        	 JOptionPane.showMessageDialog(null, "上传成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
			 Jframe.dispose();
			 System.out.println("UPLOAD_SUCCESS");
         }
         else if(message.equals("UPLOAD_FALSE")) {
        	 JOptionPane.showMessageDialog(null, "上传失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
         }
         else if(message.equals("SERVER>>> CLIENT_FILE_DOWN")) {
        	 String filename=input.readUTF();
        	 Long fileLength=input.readLong();
 			 FileOutputStream fos=new FileOutputStream(new File("C:\\downloadfile\\"+filename));
 			 byte[] sendBytes=new byte[1024];
 			 int transLen=0;
 			 System.out.println("----开始下载文件<"+filename+">,文件大小为<"+fileLength+">----");
 			 while(true) {
 				 int read=0;
 				 read=input.read(sendBytes);
 				 if(read==-1) break;
 				 transLen+=read;
 				 System.out.println("下载文件进度"+100*transLen*1.0/fileLength+"%...");
 				 fos.write(sendBytes,0,read);
 				 fos.flush();
 				 if(transLen>=fileLength) break;
 			 }
 			 System.out.println("----下载文件<"+filename+">成功----");
 			JOptionPane.showMessageDialog(null, "下载成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
 			Jframe.dispose();
         }
      } while ( !message.equals( "SERVER>>> TERMINATE" ) );
   } 
   public static void closeConnection() throws IOException{
      displayMessage( "Closing connection" );
      String logout="CLIENET>>> CLIENT_LOGOUT";
      output.writeUTF(logout);
      output.flush();
      System.out.println(logout);
      try{
         output.close(); 
         input.close(); 
         client.close(); 
      }
      catch ( IOException ioException ) {
         ioException.printStackTrace();
      } 
   } 
   static void sendData( String message ){
      try 
      {
         output.writeUTF( "CLIENT>>> " + message );
         output.flush();
         displayMessage( "CLIENT>>> " + message );
      } 
      catch ( IOException ioException )
      {
         System.out.println( "Error writing object" );
      } 
   } 
   static void displayMessage( final String messageToDisplay ){
      SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run() 
            {
            	System.out.println(messageToDisplay);
            } 
         } 
      ); 
   } 
   public static void Login(String name,String password,JFrame frame) throws IOException {
	   String login="CLIENT>>> CLIENT_LOGIN";
	   output.writeUTF(login);
	   System.out.println(login);
	   output.flush();
	   output.writeUTF(name);
	   user_name=name;
	   output.flush();
	   output.writeUTF(password);
	   user_password=password;
	   output.flush();
	   Jframe=frame;
   }
   public static void ChangeSelfInfo(String old_password,String new_password,String new_password2) throws IOException {
	   if(user_password.equals(old_password)) {
		   if(new_password.equals(new_password2)) {
			   String changeSelfInfo="CLIENT>>> CLIENT_SELF_MOD";
			   System.out.println("CLIENT>>> CLIENT_SELF_MOD");
			   output.writeUTF(changeSelfInfo);
			   output.flush();
			   output.writeUTF(user_name);
			   output.flush();
			   output.writeUTF(new_password);
			   output.flush();
			   output.writeUTF(user_role);
			   output.flush();
		   }
		   else {
			   JOptionPane.showMessageDialog(null, "两次输入的新密码不一致", "温馨提示", JOptionPane.ERROR_MESSAGE);
		   }
	   }
	   else {
		   JOptionPane.showMessageDialog(null, "密码错误", "温馨提示", JOptionPane.ERROR_MESSAGE);
	   }
   }
   
   static void Display_user() throws IOException {
	   output.writeUTF("displayUser");
	   output.flush();
   }
   static void Display_Doc() throws IOException {
	   output.writeUTF("displayDoc");
	   output.flush();
   }
   public static void DelUser(String del_name) throws IOException {
	   if(del_name.equals(user_name)) {
		   JOptionPane.showMessageDialog(null, "无法删除", "温馨提示", JOptionPane.ERROR_MESSAGE);
	   }
	   else {
		   output.writeUTF("USER_DELETE");
		   output.flush();
		   output.writeUTF(del_name);
		   output.flush();
		   System.out.println("CLIENT>>> "+del_name+ "USER_DELETE");
	   }
   }
   public static void UpdateUser(String name,String password,String role) throws IOException {
	   output.writeUTF("USER_UPDATE");
	   output.flush();
	   output.writeUTF(name);
	   output.flush();
	   output.writeUTF(password);
	   output.flush();
	   output.writeUTF(role);
	   output.flush();
	   System.out.println("CLIENT>>> "+name+ "USER_UPDATE");
   }
   public static void AddUser(String name, String password,String role) throws IOException {
	   output.writeUTF("USER_ADD");
	   output.flush();
	   output.writeUTF(name);
	   output.flush();
	   output.writeUTF(password);
	   output.flush();
	   output.writeUTF(role);
	   output.flush();
	   System.out.println("CLIENT>>> "+name+ "USER_ADD");
   }
   public static void Download(String ID) throws IOException {
	   output.writeUTF("DOWNLOAD");
	   output.flush();
       output.writeUTF(ID);
       output.flush();
   }
   public static void Upload(String ID,String Creator,String description,String filename) throws IOException{
	
		   output.writeUTF("UPLOAD");
	       output.flush();
	       output.writeUTF(ID);
	       output.flush();
	       output.writeUTF(Creator);
	       output.flush();
	       output.writeUTF(description);
	       output.flush();
	       File file=new File(filename.trim());
	       String fileName=file.getName();
	       output.writeUTF(fileName);
	       output.flush();
	       long fileLength=file.length();
	       output.writeLong(fileLength);
	       output.flush();
	       FileInputStream fis=new FileInputStream(file);
	       DataOutputStream dos=new DataOutputStream(client.getOutputStream());
	       byte[] sendBytes=new byte[1024];
	       int length=0;
	       while((length=fis.read(sendBytes,0,sendBytes.length))>0) {
		       output.write(sendBytes,0,length);
		       output.flush();
	       }
	       System.out.println("CLIENT>>> CLIENT_FILE_UP");
   }
  public  static int get_Rows() {
	   return row;
   }
  public static int get_Rows2() {
	   return row2;
   }
   public static String[][] get_Docs(){
	   return DocData;
   }
  public static String[][] get_Users(){
	   return UserData;
   }
   public static String get_Name() {
	   return user_name;
   }
   public static String get_Role() {
	   return user_role;
   }
}
