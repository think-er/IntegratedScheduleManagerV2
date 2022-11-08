import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTable;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import javax.swing.JCheckBox;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;


public class Main {

	private JFrame frame;
	private JTextField ID;
	private JPasswordField passwordField;
	private JTextField name;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
			JPanel Home = new JPanel();
			Home.setBounds(0, 0, 784, 481);
			frame.getContentPane().add(Home);
			Home.setLayout(null);
			Home.setVisible(false);
			
			JLabel mainuser = new JLabel("학번:20193355");
			mainuser.setToolTipText("");
			mainuser.setBounds(676, 49, 96, 23);
			Home.add(mainuser);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panel_1.setBounds(0, 0, 176, 184);
			Home.add(panel_1);
			panel_1.setLayout(null);
			
			JCalendar calendar = new JCalendar();
			calendar.setBounds(0, 0, 176, 184);
			panel_1.add(calendar);
			
			JLabel lblNewLabel_5 = new JLabel("(관리자)");
			lblNewLabel_5.setBounds(628, 49, 46, 23);
			Home.add(lblNewLabel_5);
			
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panel.setBounds(10, 194, 166, 277);
			Home.add(panel);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			JButton btnNewButton_3 = new JButton("생성");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
								.addContainerGap(129, Short.MAX_VALUE)
								.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
			);
			
			JList list = new JList();
			list.setModel(new AbstractListModel() {
				String[] values = new String[] {"학생1", "학생2", "학생3", "학생4"};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			scrollPane.setViewportView(list);
			panel.setLayout(gl_panel);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panel_2.setBounds(188, 77, 584, 404);
			Home.add(panel_2);
			panel_2.setLayout(null);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(12, 17, 560, 367);
			panel_2.add(scrollPane_1);
			
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowSelectionAllowed(false);
			scrollPane_1.setViewportView(table);
			table.setCellSelectionEnabled(true);
			table.setRowHeight(50);
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"09:00", null, null, null, null, null, null, null},
					{"09:30", null, null, null, null, null, null, null},
					{"10:00", null, null, null, null, null, null, null},
					{"10:30", null, null, null, null, null, null, null},
					{"11:00", null, null, null, null, null, null, null},
					{"11:30", null, null, null, null, null, null, null},
					{"12:00", null, null, null, null, null, null, null},
					{"12:30", null, null, null, null, null, null, null},
					{"13:00", null, null, null, null, null, null, null},
					{"13:30", null, null, null, null, null, null, null},
					{"14:00", null, null, null, null, null, null, null},
					{"14:30", null, null, null, null, null, null, null},
					{"15:00", null, null, null, null, null, null, null},
					{"15:30", null, null, null, null, null, null, null},
					{"16:00", null, null, null, null, null, null, null},
					{"16:30", null, null, null, null, null, null, null},
					{"17:00", null, null, null, null, null, null, null},
					{"17:30", null, null, null, null, null, null, null},
					{"18:00", null, null, null, null, null, null, null},
					{"18:30", null, null, null, null, null, null, null},
					{"19:00", null, null, null, null, null, null, null},
					{"19:30", null, null, null, null, null, null, null},
					{"20:00", null, null, null, null, null, null, null},
					{"20:30", null, null, null, null, null, null, null},
					{"21:00", null, null, null, null, null, null, null},
					{"21:30", null, null, null, null, null, null, null},
					{"22:00", null, null, null, null, null, null, null},
					{"22:30", null, null, null, null, null, null, null},
					{"23:00", null, null, null, null, null, null, null},
					{"23:30", null, null, null, null, null, null, null},
					{"24:00", null, null, null, null, null, null, null},
				},
				new String[] {
					"\uC2DC\uAC04", "11/07", "11/08", "11/09", "11/10", "11/11", "11/12", "11/13"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			
			JButton btnNewButton_4 = new JButton("일정 추가");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton_4.setBounds(521, 49, 95, 23);
			Home.add(btnNewButton_4);
			lblNewLabel_5.setVisible(true);
			
			JPanel LoginPanel = new JPanel();
			LoginPanel.setBounds(0, 0, 784, 481);
			frame.getContentPane().add(LoginPanel);
			LoginPanel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Login");
			lblNewLabel.setBounds(330, 158, 98, 64);
			lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 36));
			LoginPanel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("학번");
			lblNewLabel_1.setBounds(187, 232, 47, 34);
			lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 23));
			LoginPanel.add(lblNewLabel_1);
			
			ID = new JTextField();
			ID.setBounds(239, 232, 298, 34);
			LoginPanel.add(ID);
			ID.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("비밀번호");
			lblNewLabel_2.setBounds(139, 271, 95, 34);
			lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 23));
			LoginPanel.add(lblNewLabel_2);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(239, 271, 298, 34);
			LoginPanel.add(passwordField);
				
				
			JButton btnNewButton = new JButton("Log In");
			btnNewButton.setBounds(239, 310, 144, 30);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(ID.getText().length() >0 && passwordField.getPassword().length >0)
					{
					if(ID.getText().equals(name.getText())&&Arrays.equals(passwordField.getPassword(), passwordField_1.getText().toCharArray()))
					{
						JOptionPane.showMessageDialog(null,"you have been succesfully logged in");
						mainuser.setText("학번:"+ID.getText());
							LoginPanel.setVisible(false);
							Home.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null,"아이디와 비밀번호를 다시 확인하세요");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"아이디와 비밀번호를 다시 확인하세요");
					}
					}
				});
			LoginPanel.add(btnNewButton);
			

			
			
			
			
			JPanel RegisterPanel = new JPanel();
			RegisterPanel.setBounds(0, 0, 784, 481);
			frame.getContentPane().add(RegisterPanel);
			RegisterPanel.setLayout(null);
			RegisterPanel.setVisible(false);
			
			JButton btnNewButton_1 = new JButton("Register");
			btnNewButton_1.setBounds(388, 310, 149, 30);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LoginPanel.setVisible(false);
					RegisterPanel.setVisible(true);
				}
			});
			LoginPanel.add(btnNewButton_1);
			
			JLabel Regiseter = new JLabel("Register");
			Regiseter.setBounds(322, 99, 145, 83);
			Regiseter.setFont(new Font("Segoe Print", Font.PLAIN, 36));
			RegisterPanel.add(Regiseter);
			
			JLabel lblNewLabel_1_1 = new JLabel("학번");
			lblNewLabel_1_1.setBounds(189, 186, 47, 34);
			lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 23));
			RegisterPanel.add(lblNewLabel_1_1);
			
			name = new JTextField();
			name.setBounds(240, 186, 303, 34);
			name.setColumns(10);
			RegisterPanel.add(name);
			
				
				JLabel lblNewLabel_2_1 = new JLabel("비밀번호");
				lblNewLabel_2_1.setBounds(141, 233, 95, 34);
				lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 23));
				RegisterPanel.add(lblNewLabel_2_1);
				
				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(240, 233, 303, 34);
				RegisterPanel.add(passwordField_1);
				RegisterPanel.setVisible(false);
				
				
				
				JButton Input = new JButton("Register");
				Input.setFont(new Font("굴림", Font.PLAIN, 12));
				Input.setBounds(555, 277, 84, 34);
				Input.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textField.getText().equals("1234"))
						{
							if(passwordField_1.getText().length()==0||name.getText().length()==0)
							{
								JOptionPane.showMessageDialog(null,"아이디와 비밀번호를 다시 확인해 주세요.");
							}
							if(passwordField_1.getText().length() >0 && name.getText().length()>0)
							{
								JOptionPane.showMessageDialog(null,"관리자 등록되었습니다.");
								RegisterPanel.setVisible(false);
								LoginPanel.setVisible(true);
							
							}
						}
						
						else if(!textField.getText().equals("1234"))
						{
							if(passwordField_1.getText().length()==0||name.getText().length()==0)
							{
								JOptionPane.showMessageDialog(null,"아이디와 비밀번호를 다시 확인해 주세요.");
							}
							if(passwordField_1.getText().length() >0 && name.getText().length()>0)
							{
								JOptionPane.showMessageDialog(null,"등록되었습니다.");
								RegisterPanel.setVisible(false);
								LoginPanel.setVisible(true);
							
							}
						}
						
						
					}
				});
				RegisterPanel.add(Input);
				
				JButton btnNewButton_2 = new JButton("Back");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegisterPanel.setVisible(false);
						LoginPanel.setVisible(true);
					}
				});
				btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 12));
				btnNewButton_2.setBounds(555, 233, 84, 34);
				RegisterPanel.add(btnNewButton_2);
				
				JLabel lblNewLabel_4 = new JLabel("관리자 인증번호");
				lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 23));
				lblNewLabel_4.setBounds(64, 277, 172, 34);
				RegisterPanel.add(lblNewLabel_4);
				
				
				textField = new JTextField();
				textField.setBounds(240, 277, 303, 34);
				RegisterPanel.add(textField);
				textField.setColumns(10);
				
				textField.setEnabled(false);
				
				
				JCheckBox chckbxNewCheckBox = new JCheckBox("관리자");
				chckbxNewCheckBox.setFont(new Font("돋움체", Font.PLAIN, 12));
				chckbxNewCheckBox.setBounds(555, 186, 84, 34);
				RegisterPanel.add(chckbxNewCheckBox);
				chckbxNewCheckBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						boolean isChecked = chckbxNewCheckBox.isSelected();
						textField.setEnabled(isChecked);
				}
				});
				
	}
}
