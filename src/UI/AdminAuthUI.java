package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class AdminAuthUI extends JFrame{
	private JPasswordField AuthPadpwField;
	public AdminAuthUI() {
		setVisible(true);
		setSize(200,150);
		setLocationRelativeTo(null);	//화면 중앙 배치
		setResizable(false);			// 화면 사이즈 고정
		getContentPane().setLayout(null);
		
		JLabel AuthPadLabel = new JLabel("관리자 번호를 입력하세요.");
		AuthPadLabel.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		AuthPadLabel.setBounds(20, 23, 164, 15);
		getContentPane().add(AuthPadLabel);
		
		AuthPadpwField = new JPasswordField();
		AuthPadpwField.setBounds(20, 48, 144, 21);
		getContentPane().add(AuthPadpwField);
		
		JButton OKbtn = new JButton("확인");
		OKbtn.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = String.valueOf(AuthPadpwField.getPassword());
				if (pw.equals("1234")) {
					setVisible(false);
					new CreateTeam();
				}
				else {
					JOptionPane.showMessageDialog(null,"비밀번호를 틀렸습니다...");
				}

			}
		});
		OKbtn.setBounds(50, 78, 84, 23);
		getContentPane().add(OKbtn);
	}
}
