package UI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DB.DB_Conn_Query;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class AdminAuth extends JFrame {
	
	public JPanel AuthPadPanel;
	private JButton OKbtn;
	private JPasswordField AuthPadpwField;
	private JButton CancelBtn;
	public int AuthPw;
	
	public AdminAuth() {
		init();
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init() {
		this.setSize(250,150);
		AuthPadPanel = new JPanel();
		AuthPadPanel.setLayout(null);
		
		getContentPane().add(AuthPadPanel);
		
		JLabel AuthPadLabel = new JLabel("관리자 번호를 입력하세요.");
		AuthPadLabel.setBounds(28, 11, 144, 15);
		AuthPadPanel.add(AuthPadLabel);
		
		//확인 버튼 눌렀을 때
		
		
		
		//--------------
		
		
		AuthPadpwField = new JPasswordField();
		AuthPadpwField.setBounds(60, 36, 76, 21);
		AuthPadPanel.add(AuthPadpwField);
		
		CancelBtn = new JButton("취소");
		CancelBtn.setBounds(115, 67, 57, 23);
		AuthPadPanel.add(CancelBtn);
		
		
		
		OKbtn = new JButton("확인");
		AuthPadPanel.add(OKbtn);
		OKbtn.setBounds(28, 67, 57, 23);
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = String.valueOf(AuthPadpwField.getPassword());
				if (pw.equals("1234")) {
					AuthPadPanel.setVisible(false);
					new CreateTeam();
					getContentPane().setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null,"비밀번호를 틀렸습니다...");
				}
				
				
			}
		});
		
		
	}
}
