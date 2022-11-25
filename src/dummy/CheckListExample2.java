package dummy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CheckListExample2 extends JFrame {

public CheckListExample2() {
 super("CheckList Example");
 String[] strs = {"swing", "home", "basic", "metal", "JList"};    
                                         
 final JList list = new JList( createData(strs) );
 
 list.setCellRenderer(new CheckListRenderer());
 list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 list.setBorder(new EmptyBorder(0,4,0,0));
 
 list.addMouseListener(new MouseAdapter() {
   public void mouseClicked(MouseEvent e) {
     int index = list.locationToIndex(e.getPoint());
     CheckableItem item = (CheckableItem)list.getModel().getElementAt(index);
     item.setSelected(! item.isSelected());
     Rectangle rect = list.getCellBounds(index, index);
     list.repaint(rect);
   }
 });   
 
 JScrollPane sp = new JScrollPane(list);  
 getContentPane().add(sp,    BorderLayout.CENTER);
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

public static void main(String args[]) {
 CheckListExample2 frame = new CheckListExample2();
 frame.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {System.exit(0);}
 });
 frame.setSize(400, 200);
 frame.setVisible(true);
} 
}