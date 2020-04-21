package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class OtherFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtherFrame frame = new OtherFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OtherFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JFrame UploadFileWindow=new JFrame("上传文件");
		
		JLabel libID2=new JLabel("文件编号：");
		libID2.setBounds(14, 24, 75, 18);
		JTextField textID2=new JTextField(10);
		textID2.setBounds(86, 21, 238, 24);
		JLabel libUploadPath=new JLabel("上传文件：");
		libUploadPath.setBounds(14, 19, 75, 18);
		JTextField textUploadPath=new JTextField(10);
		textUploadPath.setBounds(85, 16, 162, 24);
		JButton btnSelect2=new JButton("选择");
		btnSelect2.setBounds(261, 15, 63, 27);
		JLabel libdescription = new JLabel("文件描述:");
		libdescription.setBounds(14, 14, 68, 18);
		JTextField textdescription=new JTextField(10);
		textdescription.setBounds(85, 5, 239, 50);
		JButton btnSure2=new JButton("确定");
		JButton btnReturn2=new JButton("返回");
		
		JPanel pnlUploadFileWindow=new JPanel();
		pnlUploadFileWindow.setLayout(new BorderLayout());
		//信息面板
		JPanel pnlUploadFileInfo=new JPanel();
		pnlUploadFileInfo.setLayout(new GridLayout(3, 1));
		JPanel pnlInfo1=new JPanel();
		pnlInfo1.setLayout(null);
		pnlInfo1.add(libID2);
		pnlInfo1.add(textID2);
		pnlUploadFileInfo.add(pnlInfo1);
		JPanel pnlInfo2=new JPanel();
		pnlInfo2.setLayout(null);
		pnlInfo2.add(libUploadPath);
		pnlInfo2.add(textUploadPath);
		pnlInfo2.add(btnSelect2);
		pnlUploadFileInfo.add(pnlInfo2);
		JPanel pnlInfo3=new JPanel();
		pnlInfo3.setLayout(null);
		pnlInfo3.add(libdescription);
		pnlInfo3.add(textdescription);
		pnlUploadFileInfo.add(pnlInfo3);
		pnlUploadFileWindow.add(pnlUploadFileInfo, BorderLayout.CENTER);
		//按钮面板
		JPanel pnlButton1=new JPanel();
		pnlButton1.add(btnSure2);
		pnlButton1.add(btnReturn2);
		pnlUploadFileWindow.add(pnlButton1, BorderLayout.SOUTH);
		//基本设置
		UploadFileWindow.getContentPane().add(pnlUploadFileWindow, BorderLayout.CENTER);
		UploadFileWindow.setSize(356, 250);//窗口大小
		//UploadFileWindow.setResizable(false);//不可调窗口大小
		UploadFileWindow.setLocationRelativeTo(null);//窗口设为中间
		UploadFileWindow.setVisible(true);//窗口可见
	}

}