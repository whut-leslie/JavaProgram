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
	String downLoadPath = "E:\\OOP\\downloadfile"; //����Ĭ������·��
	
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
					frame.setResizable(false);//���ô��ڴ�С���ɵ�
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
		super("�ļ�����");
		
		userName = nowname;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/***************************************�����ļ� ����******************************************/
		final JFrame DownloadFileWindow=new JFrame("�����ļ�");
		
		JLabel libID=new JLabel("������ţ�");
		libID.setBounds(26, 13, 75, 18);
		
		final JTextField textID=new JTextField(10);
		textID.setBounds(108, 10, 180, 24);
		
		JLabel libDownloadPath=new JLabel("����·��:");
		libDownloadPath.setBounds(26, 61, 68, 18);
		
		final JTextField textDownloadPath=new JTextField(10);
		textDownloadPath.setBounds(108, 58, 103, 24);
		
		/***ѡ���ļ�����·��*/
		JButton btnSelect=new JButton("ѡ��");
		btnSelect.addActionListener(new ActionListener() {//�������ѡ��ѡ��ʱ�����в���
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser choose = new JFileChooser();
				choose.setDialogTitle("��ѡ�񱣴�·�� ");
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
		
		/**�����ȷ������ť��ִ�����ز���*/
		JButton btnSure=new JButton("ȷ��");
		btnSure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					System.out.println("\t"+textID.getText()+"\t"+	textDownloadPath.getText());
					if(	downloadFile(textID.getText(),textDownloadPath.getText())) {
						JOptionPane.showMessageDialog(null, "���سɹ�������");
						
						FileManageFrame.this.dispose();
						//ˢ�£����¼���ҳ��
						try {
							
							FileManageFrame file = new FileManageFrame(userRole,userName);
							file.setVisible(true);
							file.setResizable(false);
						} catch (SQLException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
						
						
					}else {
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����");
					}
					DownloadFileWindow.dispose();  //�رմ���
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnReturn=new JButton("����");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DownloadFileWindow.dispose();      //��������ء���ť���رմ���
			}
		});
		
		/*****��������ء�ʱ�������Ĵ���***�����ļ��Ĵ���******************/
		JPanel pnlDownloadFileWindow=new JPanel();
		pnlDownloadFileWindow.setLayout(new BorderLayout());
		//��Ϣ���
		//��������ť���뵽�����
		JPanel pnlDownloadFileInfo=new JPanel();
		pnlDownloadFileInfo.setLayout(null);
		pnlDownloadFileInfo.add(libID);
		pnlDownloadFileInfo.add(textID);
		pnlDownloadFileInfo.add(libDownloadPath);
		pnlDownloadFileInfo.add(textDownloadPath);
		pnlDownloadFileInfo.add(btnSelect);
		pnlDownloadFileWindow.add(pnlDownloadFileInfo, BorderLayout.CENTER);
		//��ť���
		JPanel pnlButton=new JPanel();
		pnlButton.add(btnSure);
		pnlButton.add(btnReturn);
		pnlDownloadFileWindow.add(pnlButton, BorderLayout.SOUTH);
		//��������
		DownloadFileWindow.getContentPane().add(pnlDownloadFileWindow, BorderLayout.CENTER);
		DownloadFileWindow.setSize(320, 185);//���ڴ�С
		DownloadFileWindow.setResizable(false);//���ɵ����ڴ�С
		DownloadFileWindow.setLocationRelativeTo(null);//������Ϊ�м�
		//DownloadFileWindow.setVisible(true);//���ڿɼ�
		
		/**************************************�����ļ���ť******************************************************/
		Button downloadFileButton = new Button("�����ļ�");
		downloadFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DownloadFileWindow.setVisible(true);/***����������ļ�����ť�󣬽����ؽ�����ʾ*/
			}
		});
		downloadFileButton.setBounds(10, 2, 107, 38);
		contentPane.add(downloadFileButton);
		
		/*************************************�ϴ��ļ�����*******************************************************/
		final JFrame UploadFileWindow=new JFrame("�ϴ��ļ�");
		
		JLabel libID2=new JLabel("�����ţ�");
		libID2.setBounds(14, 24, 75, 18);
		
		final JTextField textID2=new JTextField(10);
		textID2.setBounds(86, 21, 238, 24);
		
		JLabel libUploadPath=new JLabel("�ϴ��ļ���");
		libUploadPath.setBounds(14, 19, 75, 18);
		
		final JTextField textUploadPath=new JTextField(10);
		textUploadPath.setBounds(85, 16, 162, 24);
		
		/***�����ѡ�񡿰�ť�������ϴ��ļ���ѡ��*/
		JButton btnSelect2=new JButton("ѡ��");
		btnSelect2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "ѡ��");
				File file=jfc.getSelectedFile();
				if (file.isFile()) {
					filename = file.getName().toString();
					System.out.println(filename);
					textUploadPath.setText(file.getAbsolutePath());
				}
				else {
					JOptionPane.showMessageDialog(null, "��ѡ���ļ�");
				}
				
			}
		});
		btnSelect2.setBounds(261, 15, 63, 27);
		
		JLabel libdescription = new JLabel("�ļ�����:");
		libdescription.setBounds(14, 14, 68, 18);
		
		final JTextField textdescription=new JTextField(10);
		textdescription.setBounds(85, 5, 239, 50);
		
		/**�����ȷ������ť����ʼ�ϴ�����**/
		JButton btnSure2=new JButton("ȷ��");
		btnSure2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Doc doc = null;
					doc = DataProcessing.searchDoc(textID2.getText().toString());
					
					if (doc== null) {
						if(textID2.getText().length() !=0 && textUploadPath.getText().length() !=0) {
							if(uploadFile(textID2.getText().toString(),filename.toString(),textdescription.getText(),textUploadPath.getText())) {
								
								//System.out.println(textID2.getText());
								JOptionPane.showMessageDialog(null, "�ϴ��ɹ�������");
								
								//UploadFileWindow.dispose();
								//ˢ�£����¼���ҳ��
								FileManageFrame.this.dispose();
								try {
									
									FileManageFrame file = new FileManageFrame(userRole,userName);
									file.setVisible(true);
									file.setResizable(false);
								} catch (SQLException e1) {
									// TODO �Զ����ɵ� catch ��
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(null, "�ϴ�ʧ�ܣ������ԣ�");
							}
						}else {
							JOptionPane.showMessageDialog(null, "����ȷ����ID��·����");
						}
					} else {
						JOptionPane.showMessageDialog(null, "�������Ѵ��ڣ��������");
						//textID2.setText("");
						//textUploadPath.setText("");
						//textdescription.setText("");
					}
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		JButton btnReturn2=new JButton("����");
		btnReturn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UploadFileWindow.dispose();
			}
		});
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		

		//**********************************
		JPanel pnlUploadFileWindow=new JPanel();
		pnlUploadFileWindow.setLayout(new BorderLayout());
		//��Ϣ���
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
		//��ť���
		JPanel pnlButton1=new JPanel();
		pnlButton1.add(btnSure2);
		pnlButton1.add(btnReturn2);
		pnlUploadFileWindow.add(pnlButton1, BorderLayout.SOUTH);
		//��������
		UploadFileWindow.getContentPane().add(pnlUploadFileWindow, BorderLayout.CENTER);
		UploadFileWindow.setSize(356, 250);//���ڴ�С
		UploadFileWindow.setResizable(false);//���ɵ����ڴ�С
		UploadFileWindow.setLocationRelativeTo(null);//������Ϊ�м�
		//UploadFileWindow.setVisible(true);//���ڿɼ�
		
		/************************************�ϴ��ļ���ť*******************************************************/
		Button uploadFileButton = new Button("�ϴ��ļ�");
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
		
		/******************************************��������ʾ�ļ��б�************************************************/
		//�ļ��б�
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
		String[] DocNames = { "������", "�ĵ���", "�ϴ���", "�ϴ�ʱ��", "�ĵ�����" };
		table = new JTable(fileinfo, DocNames);
		table.setBounds(0, 31, 532, 323);
		
		contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 46, 532, 308);
		contentPane.add(scrollPane);
		
	}
	
	
//	/**�����ء�*************************�����ļ�ʱ��ѡ���ļ�����·�����ڡ����ر����Ŀ¼*******************************/

	
	/***�����ء�****�����ļ�����*********************************************/
	public boolean downloadFile(String ID,String downloadpath) throws IOException, SQLException{
		try {
			byte[] buffer=new byte[1024];
			Doc doc=DataProcessing.searchDoc(ID);			
			if(doc==null) { 
				return false;
			}
			
			String  uploadpath=uploadpaths+doc.getFilename();
		    String downloadpaths=downloadpath+"\\"+doc.getFilename();
		    System.out.println("�������·����\t"+downloadpaths);
		    
			BufferedInputStream infile=new BufferedInputStream(new FileInputStream(uploadpath) );
			BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(downloadpaths) );
			
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
	
//	/***���ϴ���**********************************�ϴ��ļ�ʱ�������ļ�ѡ���******************************************************/

	
	/**���ϴ���*****�ϴ��ļ� ����************************************************/
		public boolean uploadFile(String ID,String filename,String description,String filepath) throws IOException, SQLException{
			try {  
		            int byteread = 0;  
		            File oldfile = new File(filepath);  
		            String targetfile = uploadpaths + File.separator +  oldfile.getName();  
		            if (oldfile.exists()) { //�ļ�����ʱ  
		                InputStream inStream = new FileInputStream(filepath); //����ԭ�ļ�  
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
		        	System.out.println("�ϴ��ļ���������"+e.toString()); 
		           // e.printStackTrace();  
		        } 
		     return false;
		}
		
		
		
		
}