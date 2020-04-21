package Frame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import FileSystem.Client;

public class FileManageFrame{
	
	    String user_role;
	    String uploadpath="C:\\uploadfile\\";
	    String downloadpath="C:\\downloadfile\\";
	    public void showMenu(int index) {
	    	String role=Client.get_Role();
	        user_role=role;
	    	JFrame frame=new JFrame();
	        frame.setTitle("文件管理界面");
			frame.setSize(460,400);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			JPanel panel_download=new JPanel();
			panel_download.setLayout(null);
			tabbedPane.addTab("文件下载", panel_download);
			String[] columnName={"ID","Creator","Time","FileName","Description"};
			String[][] rowData=new String[50][5];
			rowData=Client.get_Docs();
			int j=Client.get_Rows2();
			String[] nameData=new String[50];
			int i=0;
			for(;j<i;j++) {
				nameData[j]=rowData[j][0];
			}
			JTable table=new JTable(rowData,columnName) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex,int ColIndex) {
					return false;
				}
			};
			table.setFont(new Font("黑体",Font.PLAIN,18));
			table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scroll=new JScrollPane(table);
			scroll.setVisible(true);
			scroll.setSize(460,160);
			scroll.setBounds(0,0,460,160);
			//scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_ALWAYS);
			panel_download.add(scroll);
			JButton B1=new JButton("下载");
			JButton B2=new JButton("退出");
			B1.setSize(80,40);
			B1.setBounds((460-80-5)/2-60,(400-40-30)/2+50,80,40);
			B2.setSize(80,40);
			B2.setBounds((460-80-5)/2+60,(400-40-30)/2+50,80,40);
			B1.addActionListener(new ButtonHandler(frame,table));
			B2.addActionListener(new ButtonHandler(frame));
			panel_download.add(B1);
			panel_download.add(B2);
			JPanel panel_upload=new JPanel();
			panel_upload.setLayout(null);
			tabbedPane.addTab("档案上传", panel_upload);
			JLabel L1=new JLabel("档案号:");
			JTextField te1=new JTextField(25);
			JLabel L2=new JLabel("档案描述:");
			JTextArea te2=new JTextArea(6,25);
			JLabel L3=new JLabel("档案文件名:");
			JTextField te3=new JTextField(25);
			L1.setFont(new Font("黑体",Font.PLAIN,18));
			L1.setSize(100,30);
			L1.setBounds(5,0,100,30);
			te1.setFont(new Font("黑体",Font.PLAIN,18));
			te1.setSize(250,30);
			te1.setBounds(5+100,0,250,30);
			L2.setFont(new Font("黑体",Font.PLAIN,18));
			L2.setSize(100,30);
			L2.setBounds(5,50,100,30);
			te2.setFont(new Font("黑体",Font.PLAIN,18));
			te2.setSize(250,150);
			te2.setBounds(5+100,50,250,150);
			L3.setFont(new Font("黑体",Font.PLAIN,18));
			L3.setSize(100,30);
			L3.setBounds(5,250,100,30);
			te3.setFont(new Font("黑体",Font.PLAIN,18));
			te3.setSize(250,30);
			te3.setBounds(5+100,250,250,30);
			panel_upload.add(L1);
			panel_upload.add(L2);
			panel_upload.add(L3);
			panel_upload.add(te1);
			panel_upload.add(te2);
			panel_upload.add(te3);
			JButton B3=new JButton("打开");
			JButton B4=new JButton("上传");
			JButton B5=new JButton("取消");
			B3.setSize(80,40);
			B3.setBounds(5+350,250,80,40);
			B4.setSize(80,40);
			B4.setBounds((460-80-5)/2-60,290,80,40);
			B5.setSize(80,40);
			B5.setBounds((460-80-5)/2+60,290,80,40);
			B3.addActionListener(new ButtonHandler(te3));
			B4.addActionListener(new ButtonHandler(frame,te1,te2,te3));
			B5.addActionListener(new ButtonHandler(frame));
			panel_upload.add(B3);
			panel_upload.add(B4);
			panel_upload.add(B5);
			tabbedPane.setVisible(true);
			tabbedPane.setSelectedIndex(index);
			if(Client.get_Role().equals("operator")) {
				tabbedPane.setEnabledAt(1, true);
			}
			else tabbedPane.setEnabledAt(1, false);
			frame.add(tabbedPane);
	        frame.setVisible(true);
	    }
	    public class ButtonHandler implements ActionListener{
	    	JTextField te1;
	    	JTextArea te2;
	    	JTextField te3;
	    	JFrame frame;
			JTable table=new JTable();
	    	ButtonHandler(JTextField te3){
	    		this.te3=te3;
	    	}
	    	ButtonHandler(JFrame frame,JTextField te1,JTextArea te2,JTextField te3){
	    		this.frame=frame;
	    		this.te1=te1;
	    		this.te2=te2;
	    		this.te3=te3;
	    	}
	    	ButtonHandler(JFrame frame){
	    		this.frame=frame;
	    	}
			ButtonHandler(JFrame frame,JTable table) {
		    	this.frame=frame;
				this.table=table;
			}
	    	public void actionPerformed(ActionEvent e) {
		    	JFileChooser fileChooser=new JFileChooser();
			    if(e.getActionCommand()=="档案下载") {
			    	int op=JOptionPane.showConfirmDialog(null, "确认下载", "温馨提示", JOptionPane.YES_NO_OPTION);
			    	if(op==JOptionPane.YES_OPTION) {
				    	if(table.getSelectedRow()<0) ; 
				    	else {
				    		String id=(String)table.getValueAt(table.getSelectedRow(), 0);
				    		try {
								Client.Download(id);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
				    	}
			    	}
			    }
			    else if(e.getActionCommand()=="退出") {
			    	frame.dispose();
			    }
			    else if(e.getActionCommand()=="打开") {
	    			fileChooser.setFileSelectionMode(0);
	    			int state=fileChooser.showOpenDialog(null);
	    			if(state==1) {
	    				return ;
	    			}
	    			else {
	    				File file=fileChooser.getSelectedFile();
	    				te3.setText(file.getAbsolutePath());
	    			}
	    		}
	    		else if(e.getActionCommand()=="上传"){
	    			int op=JOptionPane.showConfirmDialog(null, "确认上传", "温馨提示", JOptionPane.YES_NO_OPTION);
	    			if(op==JOptionPane.YES_OPTION) {
	    				String ID=te1.getText();
	        			String description=te2.getText();
	        			String filename=te3.getText();
	        	        String user_name=Client.get_Name();
	        	   	    try {
							Client.Upload(ID,user_name,description,filename);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	    			}
	    			else if(op==JOptionPane.NO_OPTION) {
	    				;
	    			}
	    		}
	    		else if(e.getActionCommand()=="取消") {
	    			frame.dispose();
	    		}
	    	}
	    }
	}
	