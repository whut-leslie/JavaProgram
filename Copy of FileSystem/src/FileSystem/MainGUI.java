package FileSystem;
import java.awt.Event;
import java.awt.EventQueue;

import Frame.UserLogin;

@SuppressWarnings("unused")
public class MainGUI {
	public static void main(String[] args) {
		/*������¼����*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
