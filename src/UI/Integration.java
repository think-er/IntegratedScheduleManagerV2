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

public class Integration {

	JFrame Integration;
	private JTextField Integration_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Integration window = new Integration();
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
	public Integration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Integration = new JFrame();
		Integration.setBounds(100, 100, 390, 500);
		Integration.getContentPane().setLayout(null);
		
		JPanel Integration_panel = new JPanel();
		Integration_panel.setBounds(27, 15, 320, 50);
		Integration.getContentPane().add(Integration_panel);
		
		JLabel lblNewLabel = new JLabel("이름: 자바 프로그래밍 팀프로젝트 일정");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 128, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 15));
		GroupLayout gl_Integration_panel = new GroupLayout(Integration_panel);
		gl_Integration_panel.setHorizontalGroup(
			gl_Integration_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
		);
		gl_Integration_panel.setVerticalGroup(
			gl_Integration_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
		);
		Integration_panel.setLayout(gl_Integration_panel);
		
		JPanel Integration_panel_1 = new JPanel();
		Integration_panel_1.setBounds(27, 75, 320, 333);
		Integration.getContentPane().add(Integration_panel_1);
		
		Integration_textField = new JTextField();
		Integration_textField.setBounds(12, 10, 296, 34);
		Integration_textField.setColumns(10);
		
		JScrollPane Integration_scrollPane = new JScrollPane();
		Integration_scrollPane.setBounds(0, 54, 320, 279);
		Integration_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		String[] strs = {"이정훈(20212940)", "홍길동(20211234)", "유저1(2020XXXX)", "유저2(2021XXXX)", "유저3(2021YYYY)"};
		
		JList list = new JList( createData(strs) );
		list.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		list.setToolTipText("");
		list.setSelectedIndices(new int[] {0});
		list.setCellRenderer(new CheckListRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setBorder(new EmptyBorder(0,4,0,0));
	    
	    list.addMouseListener(new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	        int index = list.locationToIndex(e.getPoint());
	        CheckableItem item = (CheckableItem)list.getModel().getElementAt(index);//리스트 선택
	        item.setSelected(! item.isSelected());
	        Rectangle rect = list.getCellBounds(index, index);
	        list.repaint(rect);
	      }
	    });   
		
		Integration_scrollPane.setViewportView(list);
		Integration_panel_1.setLayout(null);
		Integration_panel_1.add(Integration_textField);
		Integration_panel_1.add(Integration_scrollPane);
		
		JButton btnNewButton = new JButton("저장");
		btnNewButton.setBounds(247, 418, 100, 28);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integration.setVisible(false);
				HomeUI homePanel = new HomeUI();
				homePanel.homePanel.setVisible(true);
			}
		});
		Integration.getContentPane().add(btnNewButton);
		Integration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Integration.setResizable(false);
		Integration.setTitle("통합 일정표 추가");
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
