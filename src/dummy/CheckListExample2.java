//package dummy;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.border.*;
//
//
///**
// * @version 1.0 04/26/99
// */
//public class CheckListExample2 extends JFrame {
//  
//  public CheckListExample2() {
//    super("CheckList Example");
//    String[] strs = {"이정훈(20212940)", "홍길동(20211234)", "유저1(2020XXXX)", "유저2(2021XXXX)", "유저3(2021YYYY)"};    
//                                            
//    final JList list = new JList( createData(strs) );
//    
//    list.setCellRenderer(new CheckListRenderer());
//    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    list.setBorder(new EmptyBorder(0,4,0,0));
//    
//    list.addMouseListener(new MouseAdapter() {
//      public void mouseClicked(MouseEvent e) {
//        int index = list.locationToIndex(e.getPoint());
//        CheckableItem item = (CheckableItem)list.getModel().getElementAt(index);//리스트 체크박스 선택
//        item.setSelected(! item.isSelected());
//        Rectangle rect = list.getCellBounds(index, index);
//        list.repaint(rect);
//      }
//    });   
//    JScrollPane sp = new JScrollPane(list);  
//    final JTextArea textArea = new JTextArea(3,10);
//    JScrollPane textPanel = new JScrollPane(textArea);
//    getContentPane().add(sp,    BorderLayout.CENTER);
//  }
//  
//  private CheckableItem[] createData(String[] strs) {
//    int n = strs.length;
//    CheckableItem[] items = new CheckableItem[n];
//    for (int i=0;i<n;i++) {
//      items[i] = new CheckableItem(strs[i]);
//    }
//    return items;
//  }
//  
//  class CheckableItem {
//    private String  str;
//    private boolean isSelected;
//    
//    public CheckableItem(String str) {
//      this.str = str;
//      isSelected = false;
//    }
//    
//    public void setSelected(boolean b) {
//      isSelected = b;
//    }
//    
//    public boolean isSelected() {
//      return isSelected;
//    }
//    
//    public String toString() {
//      return str;
//    }
//  }
//  
//  class CheckListRenderer extends JCheckBox implements ListCellRenderer {
//    
//    
//
//    public Component getListCellRendererComponent(JList list, Object value,
//               int index, boolean isSelected, boolean hasFocus) {
//      setEnabled(list.isEnabled());
//      setSelected(((CheckableItem)value).isSelected());
//      setFont(list.getFont());
//      setText(value.toString());
//      return this;
//    }
//  } 
//
//  public static void main(String args[]) {
//    CheckListExample2 frame = new CheckListExample2();
//    frame.addWindowListener(new WindowAdapter() {
//      public void windowClosing(WindowEvent e) {System.exit(0);}
//    });
//    frame.setSize(300, 600);
//    frame.setVisible(true);
//  } 
//}