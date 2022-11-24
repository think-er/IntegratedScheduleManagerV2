package UI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

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
	private JButton saveButton;
	
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
		subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
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
		saveButton = new JButton("저장");
		
		
		subFrame.setLayout(null);
		
		int x = 30;
		int y = 20;
		
		titleLabel.setBounds(x,y, 60, 25);
		dateLabel.setBounds(x,y+40,60,25);
		startTimeLabel.setBounds(x,y+80,60,25);
		endTimeLabel.setBounds(x,y+120,60,25);
		memoLabel.setBounds(x,y+160,60,25);
		
		titleField.setBounds(x+70, y, 250, 25);
		yearField.setBounds(x+70, y+40, 50, 25);
		yearLabel.setBounds(x+120, y+40, 60, 25);
		monthBox.setBounds(x+140, y+40, 50, 25);
		monthLabel.setBounds(x+190, y+40, 60,25);
		dayField.setBounds(x+210, y+40, 50, 25);
		dayLabel.setBounds(x+260, y+40, 60, 25);
		fixLabel.setBounds(x+280, y+40, 50, 25);
		fixBox.setBounds(x+310,y+40,60,25);
		stHourBox.setBounds(x+70, y+80, 50,25);
		stHourLabel.setBounds(x+120, y+80, 60, 25);
//		stMinuteBox.setBounds(x+140, y+80, 50, 25);
//		stMinuteLabel.setBounds(x+190, y+80, 60, 25);
		edHourBox.setBounds(x+70, y+120, 50,25);
		edHourLabel.setBounds(x+120, y+120, 60, 25);
//		edMinuteBox.setBounds(x+140, y+120, 50, 25);
//		edMinuteLabel.setBounds(x+190, y+120, 60, 25);
		memoScrollPane = new JScrollPane(memoArea);
		memoScrollPane.setBounds(x+70, y+160, 250, 130);
		saveButton.setBounds(x+260, y+300, 60, 25);
		
		subFrame.add(titleLabel);
		subFrame.add(dateLabel);
		subFrame.add(fixLabel);
		subFrame.add(startTimeLabel);
		subFrame.add(endTimeLabel);
		subFrame.add(memoLabel);
		subFrame.add(yearLabel);
		subFrame.add(monthLabel);
		subFrame.add(dayLabel);
		
		subFrame.add(titleField);
		subFrame.add(yearField);
		subFrame.add(monthBox);
		subFrame.add(dayField);
		subFrame.add(fixBox);
		subFrame.add(stHourBox);
//		subFrame.add(stMinuteBox);
		subFrame.add(stHourLabel);
//		subFrame.add(stMinuteLabel);
		subFrame.add(edHourBox);
		subFrame.add(stHourBox);
		subFrame.add(edHourLabel);
//		subFrame.add(edMinuteBox);
//		subFrame.add(edMinuteLabel);
		subFrame.add(memoScrollPane);
		subFrame.add(saveButton);
		
		subFrame.setTitle("세부 일정 추가");
		subFrame.setResizable(false);
			
		subFrame.setSize(400,400);
		subFrame.setVisible(true);
		
	}
}