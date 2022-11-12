package dummy;

import java.awt.*;

public class Java {
  public static void main(String[] args) {
    Java_Sub round = new Java_Sub();
  }
}

class Java_Sub extends Frame {
  private Dimension dimen, dimen1;
  private int xpos, ypos;

  private Button bt = new Button("-->"); // 프레임에 필요한 Button 객체와 List 객체를 생성한다.
  private Button bt1 = new Button("<--");
  private List list = new List(10, true); // List list = new List(화면에 보여질 개수, 다중 선택 모드 설정)
  private List list1 = new List(10);

  public Java_Sub() {
    super("목록 선택");
    this.init();
    this.start();
    this.setSize(300, 200);
    // this.pack();
    dimen = Toolkit.getDefaultToolkit().getScreenSize();
    dimen1 = this.getSize();
    xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
    ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
    this.setLocation(xpos, ypos);
    this.setVisible(true);

    try{

      Thread.sleep(5000);
    }catch(InterruptedException ee){}
    list.select(2);
    list.select(4);
    list.select(6);
    list.select(8);

    try{
      Thread.sleep(5000);
    }catch(InterruptedException ee){}
    String[] str = list.getSelectedItems();
    for(int i=0; i<str.length; i++){
      list1.add(str[i]);
    }

    try{
      Thread.sleep(5000);
    }catch(InterruptedException ee){}
    list.deselect(2);
    list.deselect(4);
    list.deselect(6);
    list.deselect(8); 
  }
  public void init(){
    // 화면 구성 넣을 부분
    for(int i = 0; i<20; i++){
      list.add("Test" + (i+1));
    }

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    this.setLayout(gridbag);

    gc.insets = new Insets(0, 0, 0, 10);
    gridbag.setConstraints(list, gc);
    this.add(list);

    GridLayout grid = new GridLayout(2, 1, 0, 20);
    Panel p = new Panel(grid);
    p.add(bt);
    p.add(bt1);
    gc.insets = new Insets(0, 0, 0, 0);
    gridbag.setConstraints(p, gc);
    this.add(p);

    gc.insets = new Insets(0, 10, 0, 0);
    gridbag.setConstraints(list1, gc);
    this.add(list1);
  }
  public void start() {
    // Event나 Thread 처리할 부분
  }
}