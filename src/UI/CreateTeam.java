package UI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import DB.DB_Conn_Query;
import java.awt.Font;

public class CreateTeam extends JFrame {
	
	DB.DB_Conn_Query db = new DB_Conn_Query();
	private JTextField varField;
	public CreateTeam() {
		
		ImageIcon img = new ImageIcon("img/IntegratedScheduleManager.png");
		setIconImage(img.getImage());
		
		this.setTitle("팀 만들기");
		this.setVisible(true);
		this.setSize(278,199);
		setLocationRelativeTo(null);	//화면 중앙 배치
		setResizable(false);			// 화면 사이즈 고정
		getContentPane().setLayout(null);
		
		JLabel teamNameLabel = new JLabel("생성할 팀명을 입력하세요.");
		teamNameLabel.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		teamNameLabel.setBounds(50, 38, 161, 15);
		getContentPane().add(teamNameLabel);
		
		varField = new JTextField();
		varField.setBounds(42, 72, 177, 21);
		getContentPane().add(varField);
		varField.setColumns(10);
		
		JButton OKBtn = new JButton("확인");
		OKBtn.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		OKBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = varField.getText();
				int count=0;
				if (varField.getText()=="") {
					JOptionPane.showMessageDialog(null,"등록에 실패했습니다.");
				}
				else {
					ResultSet rs = db.executeQuery("SELECT COUNT(*) FROM 팀");
					try {
						while (rs.next()) {
							count = rs.getInt(1);
						}
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
					String query = "INSERT INTO 팀 VALUES("+(count+1)+",'"+name+"')";
					
					int n = db.executeUpdate(query);
					if(n<0) {
						JOptionPane.showMessageDialog(null,"등록에 실패했습니다.");
					}
					else {
						JOptionPane.showMessageDialog(null,"등록에 성공했습니다.");
						setVisible(false);
					}
				}
			}
		});
		OKBtn.setBounds(89, 112, 83, 23);
		getContentPane().add(OKBtn);
		init();
	}
	
	private void init() {
		
	}
}
