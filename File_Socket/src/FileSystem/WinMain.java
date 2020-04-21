package FileSystem;

import Frame.UserLogin;

public class WinMain {
	public static void main(String args[]) {
		Client application; 
		@SuppressWarnings("unused")
		UserLogin window=new UserLogin();
	application = new Client( "127.0.0.1" ); 
	      application.runClient(); 

}
	}
