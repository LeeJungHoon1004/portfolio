package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class myGoalPan extends JPanel {
	static DrawingPanel drawingPanel =new DrawingPanel();;
   public void compInit() {
	   this.setLayout(new BorderLayout());
	  ControlPanel controlPanel = new ControlPanel();
       
      this.add(controlPanel, BorderLayout.SOUTH);
      this.add(drawingPanel, BorderLayout.CENTER);
      this.setPreferredSize(new Dimension(920, 350));
      // myGoalPanel.add(drawingPanel, BorderLayout.CENTER);
   }

   public void eventInit() {

   }

   public myGoalPan() {
  //    this.setSize(920, 350);
      this.compInit();
      this.eventInit();
      this.setVisible(true);
   }

   // "�Է�" ��ư�� �������� �۵� �� �����͵��
   // frame.pack();

   public static void main(String[] args) {
	   JFrame f = new JFrame();
	   
	   f.setSize(1000,400);
	   f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	   f.setLocationRelativeTo(null);
	   myGoalPan goalPan = new myGoalPan();
	   f.add(goalPan);
	   f.setVisible(true);
      
   }
}
// �׷��Ǹ� �׸��� �г� Ŭ����

class ControlPanel extends JPanel {

   // �׷����� �׸� �г�

   JPanel controlPanel = new JPanel();

   JTextField text1 = new JTextField(3);
   JTextField text2 = new JTextField(3);
   JTextField text3 = new JTextField(3);
   JTextField text4 = new JTextField(3);
   JTextField text5 = new JTextField(3);
   JTextField text6 = new JTextField(3);
   JTextField text7 = new JTextField(3);
   JTextField text8 = new JTextField(3);
   JTextField text9 = new JTextField(3);
   JTextField text10 = new JTextField(3);
   JTextField text11 = new JTextField(3);
   JTextField text12 = new JTextField(3);

   JButton button1 = new JButton("�Է�");
   JButton button2 = new JButton("����");
   
   public void compInit() {
   this.add(new JLabel("1��"));this.add(text1);
   this.add(new JLabel("2��"));this.add(text2);
   this.add(new JLabel("3��"));this.add(text3);
   this.add(new JLabel("4��"));this.add(text4);
   this.add(new JLabel("5��"));this.add(text5);
   this.add(new JLabel("6��"));this.add(text6);
   this.add(new JLabel("7��"));this.add(text7);
   this.add(new JLabel("8��"));this.add(text8);
   this.add(new JLabel("9��"));this.add(text9);
   this.add(new JLabel("10��"));this.add(text10);
   this.add(new JLabel("11��"));this.add(text11);
   this.add(new JLabel("12��"));this.add(text12);

   this.add(button1);
   // controlPanel.add(button2);

   // myGoalPan.add(controlPanel, BorderLayout.SOUTH);
   }
   
   public void eventInit() {
   button1.addActionListener(new DrawActionListener(text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,myGoalPan.drawingPanel));
   }
   public ControlPanel() {
      
      this.compInit();
      this.eventInit();
      this.setVisible(true);
   }
}

class DrawingPanel extends JPanel {
   int January, February, March, April, May, June, July, August, September, October, November, December;

   public void paint(Graphics g) {
      g.clearRect(0, 0, getWidth(), getHeight());
      g.drawLine(50, 250, 850, 250);
      for (int cnt = 1; cnt < 11; cnt++) {
         g.drawString(cnt * 10 + "", 25, 255 - 20 * cnt);
         g.drawLine(50, 250 - 20 * cnt, 850, 250 - 20 * cnt);
      }
      g.drawLine(50, 20, 50, 250);
      g.drawString("1��", 70, 270);
      g.drawString("2��", 140, 270);
      g.drawString("3��", 210, 270);
      g.drawString("4��", 280, 270);
      g.drawString("5��", 350, 270);
      g.drawString("6��", 420, 270);
      g.drawString("7��", 490, 270);
      g.drawString("8��", 560, 270);
      g.drawString("9��", 630, 270);
      g.drawString("10��", 700, 270);
      g.drawString("11��", 770, 270);
      g.drawString("12��", 840, 270);
      g.setColor(Color.RED);
      if (January > 0)
         g.fillRect(70, 250 - January * 2, 10, January * 2);
      if (February > 0)
         g.fillRect(140, 250 - February * 2, 10, February * 2);
      if (March > 0)
         g.fillRect(210, 250 - March * 2, 10, March * 2);
      if (April > 0)
         g.fillRect(280, 250 - April * 2, 10, April * 2);
      if (May > 0)
         g.fillRect(350, 250 - May * 2, 10, May * 2);
      if (June > 0)
         g.fillRect(420, 250 - June * 2, 10, June * 2);
      if (July > 0)
         g.fillRect(490, 250 - July * 2, 10, July * 2);
      if (August > 0)
         g.fillRect(560, 250 - August * 2, 10, August * 2);
      if (September > 0)
         g.fillRect(630, 250 - September * 2, 10, September * 2);
      if (October > 0)
         g.fillRect(700, 250 - October * 2, 10, October * 2);
      if (November > 0)
         g.fillRect(770, 250 - November * 2, 10, November * 2);
      if (December > 0)
         g.fillRect(840, 250 - December * 2, 10, December * 2);
   }

   public void setScores(int January, int February, int March, int April, int May, int June, int July, int August,
         int September, int October, int November, int December) {
      this.January = January;
      this.February = February;
      this.March = March;
      this.April = April;
      this.May = May;
      this.June = June;
      this.July = July;

      this.August = August;
      this.September = September;
      this.October = October;
      this.November = November;
      this.December = December;
   }
}

// ��ư �������� �����ϴ� ������
class DrawActionListener implements ActionListener {
   JTextField text1;
   JTextField text2;
   JTextField text3;
   JTextField text4;
   JTextField text5;
   JTextField text6;
   JTextField text7;
   JTextField text8;
   JTextField text9;
   JTextField text10;
   JTextField text11;
   JTextField text12;
   DrawingPanel drawingPanel;

   DrawActionListener(JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5,
         JTextField text6, JTextField text7, JTextField text8, JTextField text9, JTextField text10,
         JTextField text11, JTextField text12, DrawingPanel drawingPanel) {
      this.text1 = text1;
      this.text2 = text2;
      this.text3 = text3;
      this.text4 = text4;
      this.text5 = text5;
      this.text6 = text6;
      this.text7 = text7;
      this.text8 = text8;
      this.text9 = text9;
      this.text10 = text10;
      this.text11 = text11;
      this.text12 = text12;

      this.drawingPanel = drawingPanel;
   }

   public void actionPerformed(ActionEvent e) { // �ؽ�Ʈ ����� �۵��ϰ��ϴ� ���
      // 1
      int January;
      if (text1.getText().equals("")) {
         January = 0;
      } else {
         January = Integer.parseInt(text1.getText());
      }
      // 2 ----------------------------------------------------------
      int February;
      if (text2.getText().equals("")) {
         February = 0;
      } else {
         February = Integer.parseInt(text2.getText());
      }
      // 3 ----------------------------------------------------------
      int March;
      if (text3.getText().equals("")) {
         March = 0;
      } else {
         March = Integer.parseInt(text3.getText());
      }
      // 4 ----------------------------------------------------------
      int April;
      if (text4.getText().equals("")) {
         April = 0;
      } else {
         April = Integer.parseInt(text4.getText());
      }
      // 5 ----------------------------------------------------------
      int May;
      if (text5.getText().equals("")) {
         May = 0;
      } else {
         May = Integer.parseInt(text5.getText());
      }
      // 6 ----------------------------------------------------------
      int June;
      if (text6.getText().equals("")) {
         June = 0;
      } else {
         June = Integer.parseInt(text6.getText());
      }

      // 7 ----------------------------------------------------------
      int July;
      if (text7.getText().equals("")) {
         July = 0;
      } else {
         July = Integer.parseInt(text7.getText());
      }
      // 8 ----------------------------------------------------------
      int August;
      if (text8.getText().equals("")) {
         August = 0;
      } else {
         August = Integer.parseInt(text8.getText());
      }
      // 9 ----------------------------------------------------------
      int September;
      if (text9.getText().equals("")) {
         September = 0;
      } else {
         September = Integer.parseInt(text9.getText());
      }
      // 10 ----------------------------------------------------------
      int October;
      if (text10.getText().equals("")) {
         October = 0;
      } else {
         October = Integer.parseInt(text10.getText());
      }
      // 11 ----------------------------------------------------------
      int November;
      if (text11.getText().equals("")) {
         November = 0;
      } else {
         November = Integer.parseInt(text11.getText());
      }
      // 12 ----------------------------------------------------------
      int December;
      if (text12.getText().equals("")) {
         December = 0;
      } else {
         December = Integer.parseInt(text12.getText());
      }

      drawingPanel.setScores(January, February, March, April, May, June, July, August, September, October, November,
            December);
      drawingPanel.repaint();
   }

}