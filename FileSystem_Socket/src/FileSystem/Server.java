package FileSystem;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.SwingUtilities;

public class Server{
   private static DataOutputStream output; 
   private static DataInputStream input; 
   private ServerSocket server; 
   private Socket connection; 
   public Server(){
      super();
   } 
   public void runServer() throws SQLException{
      try{
         server = new ServerSocket(12345,100);
         while ( true ){
            try{
               waitForConnection(); 
               getStreams(); 
               processConnection(); 
            } 
            catch ( EOFException eofException ){
               displayMessage( "\nServer terminated connection" );
            } 
            finally {
               closeConnection(); 
            }
         }
      }
      catch ( IOException ioException ){
         ioException.printStackTrace();
      } 
   } 
   private void waitForConnection() throws IOException{
      displayMessage( "Waiting for connection" );
      connection = server.accept();            
      displayMessage( "Connection "+ " received from: " +
      connection.getInetAddress().getHostName() );
   }
   private void getStreams() throws IOException{
       output = new DataOutputStream( connection.getOutputStream() );
      output.flush();
       input = new DataInputStream( connection.getInputStream() );
      displayMessage( "Got I/O streams" );
   } 
   private void processConnection() throws IOException, SQLException{
      String message = "Connection successful";
      sendData( message ); 
      do{ 
         message = input.readUTF();
		if(message.equals("CLIENT>>> CLIENT_LOGIN")) {
		   String name=input.readUTF();
		   String password=input.readUTF();
		   try {
			   if(DataProcessing.searchUser(name, password)!=null) {
				    output.writeUTF("LOGIN_TRUE");  
				    output.flush();
				    String role=DataProcessing.searchUser(name, password).getRole();
				    output.writeUTF(role);
				    output.flush();
				    System.out.println(message);
				    System.out.println(name);
				    System.out.println("SERVER>>> SERVER_LOGIN");
			   }
			   else {
				   output.writeUTF("LOGIN_FALSE"); 
				   output.flush();
			   }
		   }catch (SQLException e) {
			    e.printStackTrace();
		   }
		}
		else if(message.equals("CLIENT>>> CLIENT_SELF_MOD")) {
			String name=input.readUTF();
			String password=input.readUTF();
			String role=input.readUTF();
			System.out.println("CLIENT_SELF_MOD");
			if(DataProcessing.updateUser(name, password, role)==true) {
				output.writeUTF("SELFCHANGE_TRUE");
				output.flush();
				output.writeUTF(password);
				output.flush();
				System.out.println("SERVER>>> SERVER_SELF_MOD");
			}
			else {
				output.writeUTF("SELFCHANGE_FALSE");
				output.flush();
			}
		}
		else if(message.equals("displayUser")) {
			Enumeration<User> e=DataProcessing.getAllUser();
			String[][] rowData=new String[50][3];
			User user;
			int i=0;
			while(e.hasMoreElements()) {
				user=e.nextElement();
				rowData[i][0]=user.getName();
				rowData[i][1]=user.getPassword();
				rowData[i][2]=user.getRole();
				i++;
			}
			output.writeUTF("displayedUser");
			output.flush();
			output.writeInt(i);
			output.flush();
			for(int j=0;j<i;j++) {
				output.writeUTF(rowData[j][0]);
				output.flush();
				output.writeUTF(rowData[j][1]);
				output.flush();
				output.writeUTF(rowData[j][2]);
				output.flush();
			}
		}
		else if(message.equals("displayDoc")) {
			Enumeration<Doc> e=DataProcessing.getAllDocs();
			String[][] rowData=new String[50][5];
			Doc doc;
			int i=0;
			while(e.hasMoreElements()) {
				doc=e.nextElement();
				rowData[i][0]=doc.getID();
				rowData[i][1]=doc.getCreator();
				rowData[i][2]=doc.getTimestamp().toString();
				rowData[i][3]=doc.getDescription();
				rowData[i][4]=doc.getFilename();
				i++;
			}
			output.writeUTF("displayedDoc");
			output.flush();
			output.writeInt(i);
			output.flush();
			for(int j=0;j<i;j++) {
				output.writeUTF(rowData[j][0]);
				output.flush();
				output.writeUTF(rowData[j][1]);
				output.flush();
				output.writeUTF(rowData[j][2]);
				output.flush();
				if(rowData[j][3]!=null) {
					output.writeUTF(rowData[j][3]);
					output.flush();
				}
				output.writeUTF(rowData[j][4]);
				output.flush();
			}
		}
		else if(message.equals("USER_DELETE")) {
			String name=input.readUTF();
			if(DataProcessing.deleteUser(name)) {
				output.writeUTF("DELETE_TRUE");
				output.flush();
				System.out.println("SERVER>>> "+name+" USER_DELETE");
			}
			else {
				output.writeUTF("DELETE_FALSE");
				output.flush();
			}
		}
		else if(message.equals("USER_ADD")) {
			String name=input.readUTF();
			String password=input.readUTF();
			String role=input.readUTF();
			if(DataProcessing.insertUser(name, password, role)) {
				output.writeUTF("ADD_TRUE");
				output.flush();
				System.out.println("SERVER>>> "+name+" USER_ADD");
			}
			else {
				output.writeUTF("ADD_FALSE");
				output.flush();
			}
		}
		else if(message.equals("USER_UPDATE")) {
			String name=input.readUTF();
			String password=input.readUTF();
			String role=input.readUTF();
			if(DataProcessing.updateUser(name, password, role)) {
				output.writeUTF("UPDATE_TRUE");
				output.flush();
				System.out.println("SERVER>>> "+name+" USER_UPDATE");
			}
			else {
				output.writeUTF("UPDATE_FALSE");
				output.flush();
			}
		}
		else if(message.equals("UPLOAD")) {
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			String ID=input.readUTF();
			String Creator=input.readUTF();
			String description=input.readUTF();
			String filename=input.readUTF();
			Long fileLength=input.readLong();
			@SuppressWarnings("resource")
			FileOutputStream fos=new FileOutputStream(new File("C:\\uploadfile\\"+filename));
			@SuppressWarnings("unused")
			DataInputStream dis=new DataInputStream(connection.getInputStream());
			byte[] sendBytes=new byte[1024];
			int transLen=0;
			System.out.println("----开始接收文件<"+filename+">,文件大小为<"+fileLength+">----");
			while(true) {
				int read=0;
				read=input.read(sendBytes,0,sendBytes.length);
				if(read<=0) break;
				transLen+=read;
				System.out.println("接收文件进度"+100*transLen*1.0/fileLength+"%...");
				fos.write(sendBytes,0,read);
				fos.flush();
				if(transLen>=fileLength) break;
			}
			System.out.println("----接收文件<"+filename+">成功----");
			if(DataProcessing.insertDoc(ID, Creator, timestamp, description, filename)){
				output.writeUTF("UPLOAD_TRUE");
				output.flush();
				System.out.println("SERVER>>> CLIENT_FILE_UP");
			}
			else {
				output.writeUTF("UPLOAD_FALSE");
				output.flush();
			}
		}
		else if(message.equals("DOWNLOAD")) {
			String ID=input.readUTF();
			output.writeUTF("SERVER>>> CLIENT_FILE_DOWN");
			output.flush();
			System.out.println("SERVER>>> CLIENT_FILE_DOWN");
			String filename=DataProcessing.searchDoc(ID).getFilename();
			output.writeUTF(filename);
			output.flush();
			String filepath="C:\\uploadfile\\";
			File file=new File(filepath+filename);
			long fileLength=file.length();
			output.writeLong(fileLength);
		    output.flush();
			@SuppressWarnings("resource")
			FileInputStream fis=new FileInputStream(file);
		    byte[] sendBytes=new byte[1024];
		    int length=0;
		    while((length=fis.read(sendBytes,0,sendBytes.length))>0) {
			    output.write(sendBytes,0,length);
			    output.flush();
		    }
		}
		else displayMessage(message); 
      } while ( !message.equals( "CLIENT>>> CLIENT_LOGOUT" ) );
   }
   private void closeConnection(){
      displayMessage( "Terminating connection" );
      try{
         output.close(); 
         input.close(); 
         connection.close();
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
   }
   static void sendData( String message ){
      try{
         output.writeUTF( "SERVER>>> " + message );
         output.flush(); 
         displayMessage( "SERVER>>> " + message );
      } 
      catch ( IOException ioException ){
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
   public static void main( String args[] ){
	   String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document";
		String user="root";
		String password="";
		try {
			DataProcessing.connectToDatabase(driverName, url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
      Server application = new Server(); 
      try {
		application.runServer();
	} catch (SQLException e) {
		e.printStackTrace();
		}
      }
   }