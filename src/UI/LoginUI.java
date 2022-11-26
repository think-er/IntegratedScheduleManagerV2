package UI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Control.LoginSystem;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DB.DB_Conn_Query;
import java.awt.Font;
public class LoginUI {
	
	public JPanel loginPanel;
	private JLabel loginTitleLabel; // 프로그램 제목
	private JLabel loginImgLabel; // 프로그램 아이콘 이미지
	private JLabel loginIdLabel;
	private JLabel loginPwLabel;
	private JTextField loginIdField;
	private JPasswordField loginPwField;
	private JButton loginBtn;
	private JButton toRegisterBtn;
	
	public final static int LOGIN_FRAME_X = 0;
	public final static int LOGIN_FRAME_Y = 0;
	public final static int LOGIN_FRAME_WIDTH = 400;
	public final static int LOGIN_FRAME_HEIGHT = 400;
	
	public final static int LOGIN_LABEL_X = 25;
	public final static int LOGIN_LABEL_Y = 230;
	public final static int LOGIN_LABEL_WIDTH = 60;
	public final static int LOGIN_LABEL_HEIGHT = 30;
	
	public final static int LOGIN_FIELD_X = 80;
	public final static int LOGIN_FIELD_Y = 230;
	public final static int LOGIN_FIELD_WIDTH = 200;
	public final static int LOGIN_FIELD_HEIGHT = 30;
	
	public final static int LOGIN_BTN_X = 290;
	public final static int LOGIN_BTN_Y = 270;
	public final static int LOGIN_BTN_WIDTH = 90;
	public final static int LOGIN_BTN_HEIGHT = 30;
	
	public final static int TO_REGISTER_BTN_X = 135;
	public final static int TO_REGISTER_BTN_Y = 320;
	public final static int TO_REGISTER_BTN_WIDTH = 100;
	public final static int TO_REGISTER_BTN_HEIGHT = 30;
	
	public final static int BACK_BTN_X = 0;
	public final static int BACK_BTN_Y = 0;
	public final static int BACK_BTN_WIDTH = 60;
	public final static int BACK_BTN_HEIGHT = 30;
	private JLabel titleLabel;
	
	public LoginUI() {
		init();
	}
	
	private void init() {
		
		loginPanel = new JPanel();
		loginPanel.setBounds(LOGIN_FRAME_X, LOGIN_FRAME_Y, 
				LOGIN_FRAME_WIDTH, LOGIN_FRAME_HEIGHT);
		loginPanel.setLayout(null);
		
//		loginTitleLabel = new JLabel("통합 일정 관리");
//		loginTitleLabel.setBounds(150, 10, 100, 30);
//		loginPanel.add(loginTitleLabel);
		
		loginIdLabel = new JLabel("학번");
		loginIdLabel.setBounds(26, 186,
				LOGIN_LABEL_WIDTH, LOGIN_LABEL_HEIGHT);
		loginPanel.add(loginIdLabel);
		
		loginIdField = new JTextField();
		loginIdField.setBounds(92, 186, 
				234, 30);
		loginPanel.add(loginIdField);
		
		loginPwLabel = new JLabel("비밀번호");
		loginPwLabel.setBounds(26, 226, 
				LOGIN_LABEL_WIDTH, LOGIN_LABEL_HEIGHT);
		loginPanel.add(loginPwLabel);
		
		loginPwField = new JPasswordField();
		loginPwField.setBounds(92, 226,
				234, 30);
		loginPanel.add(loginPwField);
				
		loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			
		//----------------------로그인 기능 추가------------------------------
			public void actionPerformed(ActionEvent e) {
				String id = loginIdField.getText();
				String pw = String.valueOf(loginPwField.getPassword());
				
				LoginSystem login = new LoginSystem();
				String m = login.LoginSystem(id, pw);
				
				if(m==null) {
					JOptionPane.showMessageDialog(null,"로그인실패.");
				}
				else {
					JOptionPane.showMessageDialog(null,"로그인되었습니다.");
					loginPanel.setVisible(false);
					HomeUI homePanel = new HomeUI();
					homePanel.load(id,m);
					homePanel.homePanel.setVisible(true);
				}
			}
		});
		//-----------------------------------------------------------------
		loginBtn.setBounds(215, 276, 
				111, 30);
		loginPanel.add(loginBtn);
		
		toRegisterBtn = new JButton("회원가입");
		toRegisterBtn.setBounds(92, 276, 
				111, 30);
		toRegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUI registerPanel = new RegisterUI();
				loginPanel.setVisible(false);
				registerPanel.registerPanel.setVisible(true);
		}
	});
		loginPanel.add(toRegisterBtn);
		
		MainFrame.frame.getContentPane().add(loginPanel);
		
		titleLabel = new JLabel("<html><body><center>통합 일정 관리<br>프로그램</center></body></html>");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 33));
		titleLabel.setBounds(92, 35, 234, 123);
		loginPanel.add(titleLabel);
		MainFrame.frame.setTitle("로그인");
		MainFrame.frame.setSize(LOGIN_FRAME_WIDTH, LOGIN_FRAME_WIDTH);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
	}
}