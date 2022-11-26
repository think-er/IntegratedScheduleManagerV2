// registerPanel

package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.DB_Conn_Query;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class RegisterUI {
	
	// 임의 테스트용 변수
	public static String ID = "";
	public static String PW = "";
	public static String TF;
	public static String Admin = "1234";
	
	public JPanel registerPanel;
	private JLabel registerTitleLabel; // 제목
	private JLabel registerImgLabel; // 프로그램 아이콘 이미지
	private JLabel registerIdLabel;
	private JTextField registerIdField;
	private JLabel registerPwLabel;
	private JPasswordField registerPwField;
	private JButton registerBtn;
	private JLabel registerManagerAuthLabel;
	private JTextField registerManagerAuthField;
	private JCheckBox registerManagerAuthCheck;
	private JButton toLoginBtn;
	
	public final static int	REGISTER_MANAGERAUTH_CHECK_X = LoginUI.LOGIN_BTN_X;
	public final static int	REGISTER_MANAGERAUTH_CHECK_Y = LoginUI.LOGIN_BTN_Y - 40;
	public final static int	REGISTER_MANAGERAUTH_CHECK_WIDTH = 70;
	public final static int	REGISTER_MANAGERAUTH_CHECK_HEIGHT = 30;
	private JLabel registerLabel2;
	private JTextField registerNameField;
	private JTextField registerTellField;
	private JTextField registerGradeField;
	
	
	public RegisterUI() {
		init();
	}

	private void init() {
		registerPanel = new JPanel();
		registerPanel.setBounds(LoginUI.LOGIN_FRAME_X, LoginUI.LOGIN_FRAME_Y, 
				LoginUI.LOGIN_FRAME_WIDTH, LoginUI.LOGIN_FRAME_HEIGHT);
		registerPanel.setLayout(null);
//		registerPanel.setVisible(true);
		
//		registerTitle = new JLabel("통합 일정 관리");
//		registerTitle.setBounds(322, 99, 145, 83);
//		registerPanel.add(registerTitle);
		
		registerIdLabel = new JLabel("학번");
		registerIdLabel.setBounds(25, 83,
				LoginUI.LOGIN_LABEL_WIDTH, LoginUI.LOGIN_LABEL_HEIGHT);
		registerPanel.add(registerIdLabel);
		registerPanel.setVisible(true);
		
		registerIdField = new JTextField();
		registerIdField.setBounds(80, 84,
				LoginUI.LOGIN_FIELD_WIDTH, LoginUI.LOGIN_FIELD_HEIGHT);
		registerIdField.setColumns(10);
		registerPanel.add(registerIdField);
		
		registerPwLabel = new JLabel("비밀번호");
		registerPwLabel.setBounds(25, 123, 
				LoginUI.LOGIN_LABEL_WIDTH, LoginUI.LOGIN_LABEL_HEIGHT);
		registerPanel.add(registerPwLabel);
			
		registerPwField = new JPasswordField();
		registerPwField.setBounds(80, 123,
				LoginUI.LOGIN_FIELD_WIDTH, LoginUI.LOGIN_FIELD_HEIGHT);
		registerPanel.add(registerPwField);
		
		registerManagerAuthLabel = new JLabel("인증번호");
		registerManagerAuthLabel.setBounds(25, 318, 
				LoginUI.LOGIN_LABEL_WIDTH, LoginUI.LOGIN_LABEL_HEIGHT);
		registerPanel.add(registerManagerAuthLabel);
		
		registerManagerAuthField = new JTextField();
		registerManagerAuthField.setBounds(80, 319,
				LoginUI.LOGIN_FIELD_WIDTH, LoginUI.LOGIN_FIELD_HEIGHT);
		registerManagerAuthField.setEnabled(false);
		registerManagerAuthField.setBackground(Color.LIGHT_GRAY);
		registerPanel.add(registerManagerAuthField);
			
		registerBtn = new JButton("회원가입");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID = registerIdField.getText();
				PW = String.valueOf(registerPwField.getPassword());
				String NAME = registerNameField.getText();
				String TELL = registerTellField.getText();
				String GRADE = registerGradeField.getText();
				Boolean SF = false;
				
				if(ID.length()==0||PW.length()==0) {	//id pw 입력하지 않았을 때
					JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호를 입력하세요.");
				}
				else {	//id pw 입력했을 때
					if(registerManagerAuthCheck.isSelected()) {//관리자 여부를 체크했을 때
						if(!registerManagerAuthField.getText().equals(Admin))	//인증번호 불일치
						{
							JOptionPane.showMessageDialog(null,"관리자 인증번호를 확인하세요.");
						}
						else {	//인증번호 일치
							TF = "1";
							SF=true;
						}
					}
					else	//관리자 여부 체크 x : 일반 회원가입
						TF = "0";
				}
				if(SF) {
					DB_Conn_Query db = new DB_Conn_Query();
					String query = "insert into 유저 values("+ID+",'"+PW+"','"+TF+"','"+NAME+"','"+TELL+"',"+GRADE+")";
					db.executeUpdate(query);
					
					JOptionPane.showMessageDialog(null,"회원가입 성공");
					registerPanel.setVisible(false);
					LoginUI loginPanel = new LoginUI();
					loginPanel.loginPanel.setVisible(true);
				}
				
			}
		});
		registerBtn.setBounds(292, 318, 
				LoginUI.LOGIN_BTN_WIDTH, LoginUI.LOGIN_BTN_HEIGHT);
		registerPanel.add(registerBtn);
		
		toLoginBtn = new JButton("뒤로");
		toLoginBtn.setBounds(LoginUI.BACK_BTN_X, LoginUI.BACK_BTN_Y,
				LoginUI.BACK_BTN_WIDTH, LoginUI.BACK_BTN_HEIGHT);
		toLoginBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LoginUI loginPanel = new LoginUI();
			registerPanel.setVisible(false);
			loginPanel.loginPanel.setVisible(true);
		}
	});
		registerPanel.add(toLoginBtn);
		
		registerManagerAuthCheck = new JCheckBox("관리자");
		registerManagerAuthCheck.setBounds(302, 282,
				REGISTER_MANAGERAUTH_CHECK_WIDTH, REGISTER_MANAGERAUTH_CHECK_HEIGHT);
		registerManagerAuthCheck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			boolean isChecked = registerManagerAuthCheck.isSelected();
			registerManagerAuthField.setEnabled(isChecked);
			registerManagerAuthField.setBackground(Color.white);
	}
	});
		registerPanel.add(registerManagerAuthCheck);
		
		
		MainFrame.frame.getContentPane().add(registerPanel);
		
		registerLabel2 = new JLabel("이름");
		registerLabel2.setBounds(25, 163, 60, 30);
		registerPanel.add(registerLabel2);
		
		registerNameField = new JTextField();
		registerNameField.setColumns(10);
		registerNameField.setBounds(80, 163, 200, 30);
		registerPanel.add(registerNameField);
		
		JLabel registerLabel3 = new JLabel("전화번호");
		registerLabel3.setBounds(25, 203, 60, 30);
		registerPanel.add(registerLabel3);
		
		JLabel registerLabel4 = new JLabel("학년");
		registerLabel4.setBounds(25, 243, 60, 30);
		registerPanel.add(registerLabel4);
		
		registerTellField = new JTextField();
		registerTellField.setColumns(10);
		registerTellField.setBounds(80, 203, 200, 30);
		registerPanel.add(registerTellField);
		
		registerGradeField = new JTextField();
		registerGradeField.setColumns(10);
		registerGradeField.setBounds(80, 243, 200, 30);
		registerPanel.add(registerGradeField);
		MainFrame.frame.setTitle("로그인");
		MainFrame.frame.setSize(LoginUI.LOGIN_FRAME_WIDTH, LoginUI.LOGIN_FRAME_WIDTH);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
		
	}
}