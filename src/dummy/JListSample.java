package dummy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JListSample extends JFrame implements MouseListener{
  protected JList list;
  protected DefaultListModel model;

  public static void main(String[] args){
    JListSample test = new JListSample("JListSample");

    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    test.setBounds( 10, 10, 250, 130);
    test.setVisible(true);
  }

  JListSample(String title){
    setTitle(title);

    model = new DefaultListModel();
    String[] initData = {"Blue", "Green", "Red", "Whit", "Black"};
    for (int i = 0 ; i < initData.length ; i++){
      model.addElement(new JCheckBox(initData[i]));
    }
    
    list = new JList(model);

    MyCellRenderer renderer = new MyCellRenderer();
    list.setCellRenderer(renderer);

    list.addMouseListener(this);

    JScrollPane sp = new JScrollPane();
    sp.getViewport().setView(list);
    sp.setPreferredSize(new Dimension(200, 80));

    JPanel p = new JPanel();
    p.add(sp);

    getContentPane().add(p, BorderLayout.CENTER);
  }

  class MyCellRenderer extends JCheckBox implements ListCellRenderer{

    public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus){

      JCheckBox checkBox = (JCheckBox)value;
      setText(checkBox.getText());

      setSelected(checkBox.isSelected());

      return this;
    }
  }

  public void mouseClicked(MouseEvent e){
    Point p = e.getPoint();
    int index = list.locationToIndex(p);

    JCheckBox checkBox = (JCheckBox)model.getElementAt(index);
    if (checkBox.isSelected()){
      checkBox.setSelected(false);
    }else{
      checkBox.setSelected(true);
    }

    list.repaint();
  }

  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
}