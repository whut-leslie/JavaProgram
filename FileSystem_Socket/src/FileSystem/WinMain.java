package FileSystem;

import Frame.UserLogin;

public class WinMain {
	@SuppressWarnings("rawtypes")
	public static void main(String args[]) {
		Client application; 
		UserLogin window=new UserLogin();
		window.showMenu();
	      if ( args.length == 0 )
	         application = new Client( "127.0.0.1" ); 
	      else
	         application = new Client( args[ 0 ] ); 
	      application.runClient(); 

}
	}
