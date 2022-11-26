package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class IntegrationUI {

	JFrame Integration;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntegrationUI window = new IntegrationUI();
					window.Integration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IntegrationUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		fixLabel.setBounds(622, 106, 50, 25);
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
		yearLabel.setBounds(462, 106, 60, 25);
		Integration.getContentPane().add(yearLabel);
		
		JLabel monthLabel = new JLabel("월");
		monthLabel.setBounds(532, 106, 60, 25);
		Integration.getContentPane().add(monthLabel);
		
		JLabel dayLabel = new JLabel("일");
		dayLabel.setBounds(602, 106, 60, 25);
		Integration.getContentPane().add(dayLabel);
		
		textField = new JTextField();
		textField.setBounds(412, 66, 250, 25);
		Integration.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(412, 106, 50, 25);
		Integration.getContentPane().add(textField_1);
		
		JComboBox monthBox = new JComboBox(new Object[]{});
		monthBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthBox.setBounds(482, 106, 50, 25);
		Integration.getContentPane().add(monthBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(552, 106, 50, 25);
		Integration.getContentPane().add(textField_2);
		
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
		
		JTextArea memoArea = new JTextArea();
		memoScrollPane.setViewportView(memoArea);
		
		JButton delBtn = new JButton("삭제");
		delBtn.setBounds(602, 366, 60, 25);
		Integration.getContentPane().add(delBtn);
		
		JScrollPane IntegrationScrollPane = new JScrollPane();
		IntegrationScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		IntegrationScrollPane.setBounds(12, 66, 307, 290);
		Integration.getContentPane().add(IntegrationScrollPane);
		
		JList integrationList = new JList();
		IntegrationScrollPane.setViewportView(integrationList);
		
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
		
		String[] strs = {"이정훈(20212940)", "홍길동(20211234)", "유저1(2020XXXX)", "유저2(2021XXXX)", "유저3(2021YYYY)"};
		Integration.setResizable(false);
		Integration.setTitle("통합 일정 관리");
	}


private CheckableItem[] createData(String[] strs) {
    int n = strs.length;
    CheckableItem[] items = new CheckableItem[n];
    for (int i=0;i<n;i++) {
      items[i] = new CheckableItem(strs[i]);
    }
    return items;
  }
  
  class CheckableItem {
    private String  str;
    private boolean isSelected;
    
    public CheckableItem(String str) {
      this.str = str;
      isSelected = false;
    }
    
    public void setSelected(boolean b) {
      isSelected = b;
    }
    
    public boolean isSelected() {
      return isSelected;
    }
    
    public String toString() {
      return str;
    }
  }

class CheckListRenderer extends JCheckBox implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value,
               int index, boolean isSelected, boolean hasFocus) {
      setEnabled(list.isEnabled());
      setSelected(((CheckableItem)value).isSelected());
      setFont(list.getFont());
      setText(value.toString());
      return this;
    }
}
}
