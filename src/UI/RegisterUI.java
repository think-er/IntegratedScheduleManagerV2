// registerPanel

package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class RegisterUI {
	
	// 임의 테스트용 변수
	public static String ID = "";
	public static String PW = "";
	public static boolean TF;
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
		registerIdLabel.setBounds(LoginUI.LOGIN_LABEL_X, LoginUI.LOGIN_LABEL_Y,
				LoginUI.LOGIN_LABEL_WIDTH, LoginUI.LOGIN_LABEL_HEIGHT);
		registerPanel.add(registerIdLabel);
		registerPanel.setVisible(true);
		
		registerIdField = new JTextField();
		registerIdField.setBounds(LoginUI.LOGIN_FIELD_X, LoginUI.LOGIN_FIELD_Y,
				LoginUI.LOGIN_FIELD_WIDTH, LoginUI.LOGIN_FIELD_HEIGHT);
		registerIdField.setColumns(10);
		registerPanel.add(registerIdField);
		
		registerPwLabel = new JLabel("비밀번호");
		registerPwLabel.setBounds(LoginUI.LOGIN_LABEL_X, LoginUI.LOGIN_LABEL_Y+40, 
				LoginUI.LOGIN_LABEL_WIDTH, LoginUI.LOGIN_LABEL_HEIGHT);
		registerPanel.add(registerPwLabel);
			
		registerPwField = new JPasswordField();
		registerPwField.setBounds(LoginUI.LOGIN_FIELD_X, LoginUI.LOGIN_FIELD_Y+40,
				LoginUI.LOGIN_FIELD_WIDTH, LoginUI.LOGIN_FIELD_HEIGHT);
		registerPanel.add(registerPwField);
		
		registerManagerAuthLabel = new JLabel("인증번호");
		registerManagerAuthLabel.setBounds(LoginUI.LOGIN_LABEL_X, LoginUI.LOGIN_LABEL_Y+80, 
				LoginUI.LOGIN_LABEL_WIDTH, LoginUI.LOGIN_LABEL_HEIGHT);
		registerPanel.add(registerManagerAuthLabel);
		
		registerManagerAuthField = new JTextField();
		registerManagerAuthField.setBounds(LoginUI.LOGIN_FIELD_X, LoginUI.LOGIN_FIELD_Y+80,
				LoginUI.LOGIN_FIELD_WIDTH, LoginUI.LOGIN_FIELD_HEIGHT);
		registerManagerAuthField.setEnabled(false);
		registerPanel.add(registerManagerAuthField);
			
		registerBtn = new JButton("회원가입");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(registerManagerAuthField.getText().length()==0 && registerManagerAuthCheck.isSelected())
				{
					JOptionPane.showMessageDialog(null,"관리자 인증번호를 확인하세요.");
				}
				else if(registerManagerAuthField.getText().length()>0)
				{
					if(registerManagerAuthField.getText().equals(Admin))
					{
						ID = registerIdField.getText();
						PW = registerPwField.getText();
						TF = true;
						JOptionPane.showMessageDialog(null,"관리자 회원가입 되었습니다.");
						registerPanel.setVisible(false);
						LoginUI loginPanel = new LoginUI();
						loginPanel.loginPanel.setVisible(true);
					}
					else if(!registerManagerAuthField.getText().equals(Admin))
					{
						JOptionPane.showMessageDialog(null,"관리자 인증번호를 확인하세요.");
					}
				}
				else if(!registerManagerAuthCheck.isSelected() && registerManagerAuthField.getText().length()==0)
				{
					if(registerIdField.getText().length() ==0 || registerPwField.getText().length() ==0)
					{
						JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호를 입력하세요.");
					}
					else if(registerIdField.getText().length() >0 && registerPwField.getText().length() >0)
					{
						ID = registerIdField.getText();
						PW = registerPwField.getText();
						TF = false;
						registerPanel.setVisible(false);
						LoginUI loginPanel = new LoginUI();
						loginPanel.loginPanel.setVisible(true);
					}
			}
			}
		});
		registerBtn.setBounds(LoginUI.LOGIN_BTN_X, LoginUI.LOGIN_BTN_Y, 
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
		registerManagerAuthCheck.setBounds(REGISTER_MANAGERAUTH_CHECK_X, REGISTER_MANAGERAUTH_CHECK_Y,
				REGISTER_MANAGERAUTH_CHECK_WIDTH, REGISTER_MANAGERAUTH_CHECK_HEIGHT);
		registerManagerAuthCheck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			boolean isChecked = registerManagerAuthCheck.isSelected();
			registerManagerAuthField.setEnabled(isChecked);
	}
	});
		registerPanel.add(registerManagerAuthCheck);
		
		
		MainFrame.frame.getContentPane().add(registerPanel);
		MainFrame.frame.setTitle("로그인");
		MainFrame.frame.setSize(LoginUI.LOGIN_FRAME_WIDTH, LoginUI.LOGIN_FRAME_WIDTH);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
		
	}
}