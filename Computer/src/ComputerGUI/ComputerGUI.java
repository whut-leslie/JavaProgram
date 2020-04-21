package ComputerGUI;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Computer.Computer;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "unused" })
public class ComputerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField showField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComputerGUI frame = new ComputerGUI();
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
	public ComputerGUI() {
		setTitle("\u8BA1\u7B97\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		showField = new JTextField();
		showField.setEditable(false);
		showField.setBounds(5, 5, 424, 42);
		showField.setFont(new Font("华文楷体", Font.PLAIN, 27));
		contentPane.add(showField);
		showField.setColumns(12);
		
		JButton left = new JButton("(");
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
				temp="";
				temp+="(";
				showField.setText(temp);
			}
		});
		left.setBounds(5, 71, 93, 23);
		contentPane.add(left);
		
		JButton right = new JButton(")");
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+=")";
				showField.setText(temp);
			}
		});
		right.setBounds(108, 71, 93, 23);
		contentPane.add(right);
		
		JButton clear = new JButton("delete");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				else temp=temp.substring(0,temp.length()-1);
				showField.setText(temp);
			}
		});
		clear.setBounds(218, 71, 93, 23);
		contentPane.add(clear);
		
		JButton reset = new JButton("reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showField.setText("");
			}
		});
		reset.setBounds(321, 71, 93, 23);
		contentPane.add(reset);
		
		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="7";
				showField.setText(temp);
			}
		});
		button_7.setBounds(5, 104, 93, 23);
		contentPane.add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="8";
				showField.setText(temp);
			}
		});
		button_8.setBounds(108, 104, 93, 23);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="9";
				showField.setText(temp);
			}
		});
		button_9.setBounds(218, 104, 93, 23);
		contentPane.add(button_9);
		
		JButton devide = new JButton("\u00F7");
		devide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="/";
				showField.setText(temp);
			}
		});
		devide.setBounds(321, 104, 93, 23);
		contentPane.add(devide);
		
		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="4";
				showField.setText(temp);
			}
		});
		button_4.setBounds(5, 137, 93, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="5";
				showField.setText(temp);
			}
		});
		button_5.setBounds(108, 137, 93, 23);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="6";
				showField.setText(temp);
			}
		});
		button_6.setBounds(218, 137, 93, 23);
		contentPane.add(button_6);
		
		JButton btnNewButton_3 = new JButton("X");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="*";
				showField.setText(temp);
			}
		});
		btnNewButton_3.setBounds(321, 137, 93, 23);
		contentPane.add(btnNewButton_3);
		
		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="1";
				showField.setText(temp);
			}
		});
		button_1.setBounds(5, 169, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="2";
				showField.setText(temp);
			}
		});
		button_2.setBounds(108, 169, 93, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="3";
				showField.setText(temp);
			}
		});
		button_3.setBounds(218, 170, 93, 23);
		contentPane.add(button_3);
		
		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="-";
				showField.setText(temp);
			}
		});
		minus.setBounds(321, 170, 93, 23);
		contentPane.add(minus);
		
		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="0";
				showField.setText(temp);
			}
		});
		button_0.setBounds(5, 201, 93, 23);
		contentPane.add(button_0);
		
		JButton shudian = new JButton("\u00B7");
		shudian.setFont(new Font("宋体", Font.BOLD, 12));
		shudian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+=".";
				showField.setText(temp);
			}
		});
		shudian.setBounds(108, 202, 93, 23);
		contentPane.add(shudian);
		
		JButton equal = new JButton("=");
		equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				String  ans=Computer.center(temp);
				showField.setText(ans);
			}
		});
		equal.setBounds(218, 201, 93, 23);
		contentPane.add(equal);
		
		JButton and = new JButton("+");
		and.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=showField.getText();
				if(temp.lastIndexOf("=")!=-1)
					temp="";
				temp+="+";
				showField.setText(temp);
			}
		});
		and.setBounds(321, 201, 93, 23);
		contentPane.add(and);
	}
}
