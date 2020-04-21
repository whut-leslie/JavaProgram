package Frame;
import FileSystem.DataProcessing;
import FileSystem.Doc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;




@SuppressWarnings({ "serial" })
public class FileManageFrame extends JFrame {

	protected static final String FileOperation = null;
	private JPanel contentPane;
	private JTable table;
	
	String uploadfilepath = "E:\\OOP\\downloadfile";
	String downLoadPath = "E:\\OOP\\downloadfile"; //设置默认下载路径
	
	String userName;
	String filename;
	static String uploadpaths="E:\\OOP\\uploadfile\\";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String a = null;
					FileManageFrame frame = new FileManageFrame(a,a);
					frame.setResizable(false);//设置窗口大小不可调
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	public FileManageFrame(final String userRole,String nowname) throws SQLException {
		super("文件管理");
		
		userName = nowname;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/***************************************下载文件 窗口******************************************/
		final JFrame DownloadFileWindow=new JFrame("下载文件");
		
		JLabel libID=new JLabel("档案编号：");
		libID.setBounds(26, 13, 75, 18);
		
		final JTextField textID=new JTextField(10);
		textID.setBounds(108, 10, 180, 24);
		
		JLabel libDownloadPath=new JLabel("下载路径:");
		libDownloadPath.setBounds(26, 61, 68, 18);
		
		final JTextField textDownloadPath=new JTextField(10);
		textDownloadPath.setBounds(108, 58, 103, 24);
		
		/***选择文件下载路径*/
		JButton btnSelect=new JButton("选择");
		btnSelect.addActionListener(new ActionListener() {//当点击【选择】选项时，进行操作
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser choose = new JFileChooser();
				choose.setDialogTitle("请选择保存路径 ");
				choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = choose.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					
					FileManageFrame.this.downLoadPath = new String(choose.getSelectedFile().getPath());
					textDownloadPath.setText(FileManageFrame.this.downLoadPath);
				}
				if (result == JFileChooser.CANCEL_OPTION)
					dispose();//*
			}
				
		});
		btnSelect.setBounds(225, 57, 63, 27);
		
		/**点击【确定】按钮，执行下载操作*/
		JButton btnSure=new JButton("确定");
		btnSure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					System.out.println("\t"+textID.getText()+"\t"+	textDownloadPath.getText());
					if(	downloadFile(textID.getText(),textDownloadPath.getText())) {
						JOptionPane.showMessageDialog(null, "下载成功！！！");
						
						FileManageFrame.this.dispose();
						//刷新，重新加载页面
						try {
							
							FileManageFrame file = new FileManageFrame(userRole,userName);
							file.setVisible(true);
							file.setResizable(false);
						} catch (SQLException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						
						
					}else {
						JOptionPane.showMessageDialog(null, "下载失败！！！");
					}
					DownloadFileWindow.dispose();  //关闭窗口
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnReturn=new JButton("返回");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DownloadFileWindow.dispose();      //点击【返回】按钮，关闭窗口
			}
		});
		
		/*****点击【下载】时，弹出的窗口***下载文件的窗口******************/
		JPanel pnlDownloadFileWindow=new JPanel();
		pnlDownloadFileWindow.setLayout(new BorderLayout());
		//信息面板
		//将各个按钮导入到面板中
		JPanel pnlDownloadFileInfo=new JPanel();
		pnlDownloadFileInfo.setLayout(null);
		pnlDownloadFileInfo.add(libID);
		pnlDownloadFileInfo.add(textID);
		pnlDownloadFileInfo.add(libDownloadPath);
		pnlDownloadFileInfo.add(textDownloadPath);
		pnlDownloadFileInfo.add(btnSelect);
		pnlDownloadFileWindow.add(pnlDownloadFileInfo, BorderLayout.CENTER);
		//按钮面板
		JPanel pnlButton=new JPanel();
		pnlButton.add(btnSure);
		pnlButton.add(btnReturn);
		pnlDownloadFileWindow.add(pnlButton, BorderLayout.SOUTH);
		//基本设置
		DownloadFileWindow.getContentPane().add(pnlDownloadFileWindow, BorderLayout.CENTER);
		DownloadFileWindow.setSize(320, 185);//窗口大小
		DownloadFileWindow.setResizable(false);//不可调窗口大小
		DownloadFileWindow.setLocationRelativeTo(null);//窗口设为中间
		//DownloadFileWindow.setVisible(true);//窗口可见
		
		/**************************************下载文件按钮******************************************************/
		Button downloadFileButton = new Button("下载文件");
		downloadFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DownloadFileWindow.setVisible(true);/***点击【下载文件】按钮后，将下载界面显示*/
			}
		});
		downloadFileButton.setBounds(10, 2, 107, 38);
		contentPane.add(downloadFileButton);
		
		/*************************************上传文件窗口*******************************************************/
		final JFrame UploadFileWindow=new JFrame("上传文件");
		
		JLabel libID2=new JLabel("档案号：");
		libID2.setBounds(14, 24, 75, 18);
		
		final JTextField textID2=new JTextField(10);
		textID2.setBounds(86, 21, 238, 24);
		
		JLabel libUploadPath=new JLabel("上传文件：");
		libUploadPath.setBounds(14, 19, 75, 18);
		
		final JTextField textUploadPath=new JTextField(10);
		textUploadPath.setBounds(85, 16, 162, 24);
		
		/***点击【选择】按钮，进行上传文件的选择*/
		JButton btnSelect2=new JButton("选择");
		btnSelect2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择");
				File file=jfc.getSelectedFile();
				if (file.isFile()) {
					filename = file.getName().toString();
					System.out.println(filename);
					textUploadPath.setText(file.getAbsolutePath());
				}
				else {
					JOptionPane.showMessageDialog(null, "请选择文件");
				}
				
			}
		});
		btnSelect2.setBounds(261, 15, 63, 27);
		
		JLabel libdescription = new JLabel("文件描述:");
		libdescription.setBounds(14, 14, 68, 18);
		
		final JTextField textdescription=new JTextField(10);
		textdescription.setBounds(85, 5, 239, 50);
		
		/**点击【确定】按钮，开始上传操作**/
		JButton btnSure2=new JButton("确定");
		btnSure2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Doc doc = null;
					doc = DataProcessing.searchDoc(textID2.getText().toString());
					
					if (doc== null) {
						if(textID2.getText().length() !=0 && textUploadPath.getText().length() !=0) {
							if(uploadFile(textID2.getText().toString(),filename.toString(),textdescription.getText(),textUploadPath.getText())) {
								
								//System.out.println(textID2.getText());
								JOptionPane.showMessageDialog(null, "上传成功！！！");
								
								//UploadFileWindow.dispose();
								//刷新，重新加载页面
								FileManageFrame.this.dispose();
								try {
									
									FileManageFrame file = new FileManageFrame(userRole,userName);
									file.setVisible(true);
									file.setResizable(false);
								} catch (SQLException e1) {
									// TODO 自动生成的 catch 块
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(null, "上传失败，请重试！");
							}
						}else {
							JOptionPane.showMessageDialog(null, "请正确输入ID或路径！");
						}
					} else {
						JOptionPane.showMessageDialog(null, "档案号已存在，请更换！");
						//textID2.setText("");
						//textUploadPath.setText("");
						//textdescription.setText("");
					}
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		JButton btnReturn2=new JButton("返回");
		btnReturn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UploadFileWindow.dispose();
			}
		});
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		

		//**********************************
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
		UploadFileWindow.setResizable(false);//不可调窗口大小
		UploadFileWindow.setLocationRelativeTo(null);//窗口设为中间
		//UploadFileWindow.setVisible(true);//窗口可见
		
		/************************************上传文件按钮*******************************************************/
		Button uploadFileButton = new Button("上传文件");
		if(userRole == "administrator" || userRole == "browser") {
			uploadFileButton.setEnabled(false);  
		}
		uploadFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UploadFileWindow.setVisible(true);;
			}
		});
		uploadFileButton.setBounds(126, 2, 92, 38);
		contentPane.add(uploadFileButton);
				
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		/******************************************窗口中显示文件列表************************************************/
		//文件列表
		int docsize = 0;;
		while(DataProcessing.getAllDocs().hasMoreElements()) {   //=DataProcessing.getAllDocs()..docs.size();
			docsize++;
		}
		
		Object[][] fileinfo=new Object[docsize][5];
		Enumeration e = DataProcessing.getAllDocs();
		int i=0;
		while (e.hasMoreElements()) {
			Doc doc = (Doc) e.nextElement();
			fileinfo[i][0]=doc.getID().toString();
			fileinfo[i][1]=doc.getFilename().toString();
			fileinfo[i][2]=doc.getCreator().toString();
			fileinfo[i][3]=doc.getTimestamp().toString();
			fileinfo[i][4]=doc.getDescription();
			System.out.println(doc.getID()+"\t"+doc.getFilename()+"\t"+doc.getCreator()+"\t"+doc.getTimestamp()+"\t"+doc.getDescription());
			i++;
		}
		
		/*int docsize = DataProcessing.docs.size();

		Object[][] fileinfo=new Object[docsize][5];
		Enumeration e = DataProcessing.getAllDocs();
		int i=0;
		while (e.hasMoreElements()) {
			Doc doc = (Doc) e.nextElement();
			fileinfo[i][0]=doc.getID().toString();
			fileinfo[i][1]=doc.getFilename().toString();
			fileinfo[i][2]=doc.getCreator().toString();
			fileinfo[i][3]=doc.getTimestamp().toString();
			fileinfo[i][4]=doc.getDescription();
			System.out.println(doc.getID()+"\t"+doc.getFilename()+"\t"+doc.getCreator()+"\t"+doc.getTimestamp()+"\t"+doc.getDescription());
			i++;
		}
		*/
		String[] DocNames = { "档案号", "文档名", "上传者", "上传时间", "文档描述" };
		table = new JTable(fileinfo, DocNames);
		table.setBounds(0, 31, 532, 323);
		
		contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 46, 532, 308);
		contentPane.add(scrollPane);
		
	}
	
	
//	/**【下载】*************************下载文件时，选择文件保存路径窗口。返回保存的目录*******************************/

	
	/***【下载】****下载文件方法*********************************************/
	public boolean downloadFile(String ID,String downloadpath) throws IOException, SQLException{
		try {
			byte[] buffer=new byte[1024];
			Doc doc=DataProcessing.searchDoc(ID);			
			if(doc==null) { 
				return false;
			}
			
			String  uploadpath=uploadpaths+doc.getFilename();
		    String downloadpaths=downloadpath+"\\"+doc.getFilename();
		    System.out.println("输出下载路径：\t"+downloadpaths);
		    
			BufferedInputStream infile=new BufferedInputStream(new FileInputStream(uploadpath) );
			BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(downloadpaths) );
			
			while(true)
			{
				int byteRoad=infile.read(buffer);//从文件数据给字节数组
				if(byteRoad==-1)//在文件尾，无数据可读
					break;
				targetfile.write(buffer, 0, byteRoad);//将读到的数据写入目标文件
			}
			infile.close();
			targetfile.close();
			return true;
			
		}catch(Exception e) {
			System.out.println("下载文件操作出错"+e.toString()); 
			//e.printStackTrace(); 
		}
		return false;
		
	}
	
//	/***【上传】**********************************上传文件时弹出的文件选择框******************************************************/

	
	/**【上传】*****上传文件 方法************************************************/
		public boolean uploadFile(String ID,String filename,String description,String filepath) throws IOException, SQLException{
			try {  
		            int byteread = 0;  
		            File oldfile = new File(filepath);  
		            String targetfile = uploadpaths + File.separator +  oldfile.getName();  
		            if (oldfile.exists()) { //文件存在时  
		                InputStream inStream = new FileInputStream(filepath); //读入原文件  
		                FileOutputStream fs = new FileOutputStream(targetfile);  
		                byte[] buffer = new byte[1444];  
		                while ((byteread = inStream.read(buffer)) != -1) {  
		                    fs.write(buffer, 0, byteread);  
		                }  
		                inStream.close(); 
		                fs.close();
		                Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		                DataProcessing.insertDoc(ID, userName, timestamp, description, filename);
		                return true;
		                
		            }  
		            else return false;
		            
		        } catch (Exception e) {  
		        	System.out.println("上传文件操作出错"+e.toString()); 
		           // e.printStackTrace();  
		        } 
		     return false;
		}
		
		
		
		
}