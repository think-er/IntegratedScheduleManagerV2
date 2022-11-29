package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DB.DB_Conn_Query;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class IntegrationUI extends JFrame {
	private static String ID;
	JFrame Integration;
	private JTextField titleTextField;
	private JTextField yearTextField;
	private JTextField dayTextField;
	
	DB_Conn_Query db = new DB_Conn_Query();
	
	/**
	 * Create the application.
	 */
	public static void main(String[] args) {
		new PersonalUI(ID);
	}
	
	public IntegrationUI(String id) {
		initialize(id);
		ID=id;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) {
		Integration = new JFrame();
		Integration.setBounds(100, 100, 700, 440);
		Integration.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("일정 제목");
		titleLabel.setBounds(342, 66, 60, 25);
		Integration.getContentPane().add(titleLabel);
		
		JLabel dateLabel = new JLabel("일정 날짜");
		dateLabel.setBounds(342, 106, 60, 25);
		Integration.getContentPane().add(dateLabel);
		
		JLabel fixLabel = new JLabel("고정");
		fixLabel.setBounds(622, 106, 40, 25);
		Integration.getContentPane().add(fixLabel);
		
		JLabel startTimeLabel = new JLabel("시작 시간");
		startTimeLabel.setBounds(342, 146, 60, 25);
		Integration.getContentPane().add(startTimeLabel);
		
		JLabel endTimeLabel = new JLabel("종료 시간");
		endTimeLabel.setBounds(342, 186, 60, 25);
		Integration.getContentPane().add(endTimeLabel);
		
		JLabel memoLabel = new JLabel("일정 메모");
		memoLabel.setBounds(342, 226, 60, 25);
		Integration.getContentPane().add(memoLabel);
		
		JLabel yearLabel = new JLabel("년");
		yearLabel.setBounds(462, 106, 20, 25);
		Integration.getContentPane().add(yearLabel);
		
		JLabel monthLabel = new JLabel("월");
		monthLabel.setBounds(532, 106, 20, 25);
		Integration.getContentPane().add(monthLabel);
		
		JLabel dayLabel = new JLabel("일");
		dayLabel.setBounds(602, 106, 20, 25);
		Integration.getContentPane().add(dayLabel);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(412, 66, 250, 25);
		Integration.getContentPane().add(titleTextField);
		
		yearTextField = new JTextField();
		yearTextField.setBounds(412, 106, 50, 25);
		Integration.getContentPane().add(yearTextField);
		
		JComboBox monthBox = new JComboBox(new Object[]{});
		monthBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthBox.setBounds(482, 106, 50, 25);
		Integration.getContentPane().add(monthBox);
		
		dayTextField = new JTextField();
		dayTextField.setBounds(552, 106, 50, 25);
		Integration.getContentPane().add(dayTextField);
		
		JCheckBox fixBox = new JCheckBox();
		fixBox.setBounds(652, 106, 32, 25);
		Integration.getContentPane().add(fixBox);
		
		JComboBox stHourBox = new JComboBox(new Object[]{});
		stHourBox.setModel(new DefaultComboBoxModel(new String[] {"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
		stHourBox.setBounds(412, 146, 50, 25);
		Integration.getContentPane().add(stHourBox);
		
		JLabel stHourLabel = new JLabel("시");
		stHourLabel.setBounds(462, 146, 60, 25);
		Integration.getContentPane().add(stHourLabel);
		
		JComboBox edHourBox = new JComboBox(new Object[]{});
		edHourBox.setModel(new DefaultComboBoxModel(new String[] {"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
		edHourBox.setBounds(412, 186, 50, 25);
		Integration.getContentPane().add(edHourBox);
		
		JLabel edHourLabel = new JLabel("시");
		edHourLabel.setBounds(462, 186, 60, 25);
		Integration.getContentPane().add(edHourLabel);
		
		JScrollPane memoScrollPane = new JScrollPane((Component) null);
		memoScrollPane.setBounds(412, 226, 250, 130);
		Integration.getContentPane().add(memoScrollPane);
		
		JTextArea memoTextField = new JTextArea();
		memoScrollPane.setViewportView(memoTextField);
		
		JButton delBtn = new JButton("삭제");
		delBtn.setBounds(602, 366, 60, 25);
		Integration.getContentPane().add(delBtn);
		
		JScrollPane IntegrationScrollPane = new JScrollPane();
		IntegrationScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		IntegrationScrollPane.setBounds(12, 66, 307, 130);
		Integration.getContentPane().add(IntegrationScrollPane);
		
		JList<String> integrationList = new JList<String>();
		integrationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		IntegrationScrollPane.setViewportView(integrationList);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		String query = "SELECT 통합스케줄_이름 "
				+ "FROM 통합스케줄,소속 "
				+ "WHERE 통합스케줄.팀_번호=소속.팀_번호 "
				+ "AND 소속.유저_아이디="+id;
		ResultSet rs = db.executeQurey(query);
		try {
			while(rs.next()) {
				listModel.addElement(rs.getString("통합스케줄_이름"));
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
		integrationList.setModel(listModel);
		
		integrationList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					System.out.println("selected :"+integrationList.getSelectedValue());
				}
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("통합 일정 관리");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 26, 181, 30);
		Integration.getContentPane().add(lblNewLabel);
		
		JButton modifyBtn = new JButton("수정");
		modifyBtn.setBounds(532, 366, 60, 25);
		Integration.getContentPane().add(modifyBtn);
		
		JButton addBtn = new JButton("등록");
		addBtn.setBounds(462, 366, 60, 25);
		Integration.getContentPane().add(addBtn);
		
		JScrollPane TeamMemberScrollPane = new JScrollPane();
		TeamMemberScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		TeamMemberScrollPane.setBounds(12, 226, 307, 137);
		Integration.getContentPane().add(TeamMemberScrollPane);
		
		JList TeamMemberList = new JList();
		TeamMemberScrollPane.setViewportView(TeamMemberList);
		
		JLabel lblNewLabel_1 = new JLabel("[팀원 선택]");
		lblNewLabel_1.setBounds(22, 206, 82, 15);
		Integration.getContentPane().add(lblNewLabel_1);
		
		Integration.setResizable(false);
		Integration.setTitle("통합 일정 관리");
	}


}
