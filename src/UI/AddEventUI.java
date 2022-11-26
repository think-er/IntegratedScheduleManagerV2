package UI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.Font;

public class AddEventUI {
	
	private JFrame subFrame;
	
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel fixLabel;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JLabel endLabel;
	private JLabel memoLabel;
	
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	
	private JLabel stHourLabel;
	private JLabel stMinuteLabel;
	
	private JLabel edHourLabel;
	private JLabel edMinuteLabel;
	
	private JTextField titleField;
	private JTextField yearField;
	private JComboBox monthBox;
	private JTextField dayField;
	private JCheckBox fixBox;
	private JComboBox stHourBox;
//	private JComboBox stMinuteBox;
	private JComboBox edHourBox;
//	private JComboBox edMinuteBox;
	private JTextArea memoArea;
	private JScrollPane memoScrollPane;
	private JButton delBtn;
	
	public static String[] monthCb = {"01", "02", "03", "04", "05", "06"
			, "07", "08", "09", "10", "11", "12"};
	public static String[] hourCb = {"09", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22"
	};
	
	public static void main(String[] args) {
		new AddEventUI();
	}
	
	public AddEventUI() {
		init();
	}
	
	private void init() {
//		String[] minuteCb = {"00", "30"};
		
		subFrame = new JFrame();	
		
		titleLabel = new JLabel("일정 제목");
		dateLabel = new JLabel("일정 날짜");
		fixLabel = new JLabel("고정");
		startTimeLabel = new JLabel("시작 시간");
		endTimeLabel = new JLabel("종료 시간");
		memoLabel = new JLabel("일정 메모");
		
		yearLabel = new JLabel("년");
		monthLabel = new JLabel("월");
		dayLabel = new JLabel("일");
		stHourLabel = new JLabel("시");
		edHourLabel = new JLabel("시");
//		stMinuteLabel = new JLabel("분");
//		edMinuteLabel = new JLabel("분");
		titleField = new JTextField();
		yearField = new JTextField();
		monthBox = new JComboBox(monthCb);
		dayField = new JTextField();
		fixBox = new JCheckBox();
		stHourBox = new JComboBox(hourCb);
		edHourBox = new JComboBox(hourCb);
//		stMinuteBox = new JComboBox(minuteCb);
//		edMinuteBox = new JComboBox(minuteCb);
		memoArea = new JTextArea();
		delBtn = new JButton("삭제");
		
		
		subFrame.getContentPane().setLayout(null);
		
		int x = 30;
		int y = 20;
		
		titleLabel.setBounds(342,60, 60, 25);
		dateLabel.setBounds(342,100,60,25);
		startTimeLabel.setBounds(342,140,60,25);
		endTimeLabel.setBounds(342,180,60,25);
		memoLabel.setBounds(342,220,60,25);
		
		titleField.setBounds(412, 60, 250, 25);
		yearField.setBounds(412, 100, 50, 25);
		yearLabel.setBounds(462, 100, 60, 25);
		monthBox.setBounds(482, 100, 50, 25);
		monthLabel.setBounds(532, 100, 60,25);
		dayField.setBounds(552, 100, 50, 25);
		dayLabel.setBounds(602, 100, 60, 25);
		fixLabel.setBounds(622, 100, 50, 25);
		fixBox.setBounds(652,100,32,25);
		stHourBox.setBounds(412, 140, 50,25);
		stHourLabel.setBounds(462, 140, 60, 25);
//		stMinuteBox.setBounds(x+140, y+80, 50, 25);
//		stMinuteLabel.setBounds(x+190, y+80, 60, 25);
		edHourBox.setBounds(412, 180, 50,25);
		edHourLabel.setBounds(462, 180, 60, 25);
//		edMinuteBox.setBounds(x+140, y+120, 50, 25);
//		edMinuteLabel.setBounds(x+190, y+120, 60, 25);
		memoScrollPane = new JScrollPane(memoArea);
		memoScrollPane.setBounds(412, 220, 250, 130);
		delBtn.setBounds(602, 360, 60, 25);
		
		subFrame.getContentPane().add(titleLabel);
		subFrame.getContentPane().add(dateLabel);
		subFrame.getContentPane().add(fixLabel);
		subFrame.getContentPane().add(startTimeLabel);
		subFrame.getContentPane().add(endTimeLabel);
		subFrame.getContentPane().add(memoLabel);
		subFrame.getContentPane().add(yearLabel);
		subFrame.getContentPane().add(monthLabel);
		subFrame.getContentPane().add(dayLabel);
		
		subFrame.getContentPane().add(titleField);
		subFrame.getContentPane().add(yearField);
		subFrame.getContentPane().add(monthBox);
		subFrame.getContentPane().add(dayField);
		subFrame.getContentPane().add(fixBox);
		subFrame.getContentPane().add(stHourBox);
//		subFrame.add(stMinuteBox);
		subFrame.getContentPane().add(stHourLabel);
//		subFrame.add(stMinuteLabel);
		subFrame.getContentPane().add(edHourBox);
		subFrame.getContentPane().add(stHourBox);
		subFrame.getContentPane().add(edHourLabel);
//		subFrame.add(edMinuteBox);
//		subFrame.add(edMinuteLabel);
		subFrame.getContentPane().add(memoScrollPane);
		subFrame.getContentPane().add(delBtn);
		
		JScrollPane individualScrollPane = new JScrollPane();
		individualScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		individualScrollPane.setBounds(12, 60, 307, 290);
		subFrame.getContentPane().add(individualScrollPane);
		
		JList individuallist = new JList();
		individualScrollPane.setViewportView(individuallist);
		
		JLabel lblNewLabel = new JLabel("개인 일정 관리");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 20, 181, 30);
		subFrame.getContentPane().add(lblNewLabel);
		
		JButton modifyBtn = new JButton("수정");
		modifyBtn.setBounds(532, 360, 60, 25);
		subFrame.getContentPane().add(modifyBtn);
		
		JButton addBtn = new JButton("등록");
		addBtn.setBounds(462, 360, 60, 25);
		subFrame.getContentPane().add(addBtn);
		
		subFrame.setTitle("개인 일정 관리");
		subFrame.setResizable(false);
			
		subFrame.setSize(700,439);
		subFrame.setVisible(true);
		
	}
}