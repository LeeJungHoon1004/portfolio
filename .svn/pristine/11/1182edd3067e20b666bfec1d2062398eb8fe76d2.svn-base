package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class BasicShape extends JFrame {

   private Container cp = this.getContentPane();
   private JLabel id1 = new JLabel("ID:");
   private JTextField id2 = new JTextField();
   private JLabel pw1 = new JLabel("PW:");
   private JPasswordField pw2 = new JPasswordField();
   private JButton membership = new JButton("회원가입");
   private JButton in = new JButton("로그인");

   private JLabel title = new JLabel("title", JLabel.CENTER);
   private Font font = new Font("바탕", Font.ITALIC, 30);
   private JLabel loginshpe = new JLabel();
   private JLabel category = new JLabel();
   private JLabel picture = new JLabel();
   private JLabel bmi = new JLabel();
   private TitledBorder tborder;

   private JPanel pan = new JPanel();
   private JScrollPane sc = new JScrollPane(pan);
   

   public void comp() {
      setLayout(null);
      
      tborder = new TitledBorder("");
      tborder.setTitlePosition(TitledBorder.ABOVE_TOP);// 지정한 위치에 타이틀을 나타내주는
                                             // 보더
      tborder.setTitleJustification(TitledBorder.CENTER);// 자리맞춤을 가운데로 지정
      title.setBorder(tborder);
      title.setFont(font);
      loginshpe.setBorder(tborder);
      category.setBorder(tborder);
      picture.setBorder(tborder);
      bmi.setBorder(tborder);
      
//      아직 추가 못한것들
//      title.setBounds(370, 60, 1090, 160);
//      loginshpe.setBounds(30, 120, 310, 280);
//      category.setBounds(30, 420, 310, 520);
//      picture.setBounds(370, 240, 1090, 360);
//      bmi.setBounds(370, 640, 1090,2000);
//      id1.setBounds(40, 160, 100, 60);
//      id2.setBounds(150, 160, 170, 60);
//      pw1.setBounds(40, 240, 100, 60);
//      pw2.setBounds(150, 240, 170, 60);
//      membership.setBounds(40, 310, 100, 60);
//      in.setBounds(150, 310, 100, 60);
      
      
      title.setPreferredSize(new Dimension(1090,160));
      loginshpe.setPreferredSize(new Dimension(310,280));
      category.setPreferredSize(new Dimension(310,520));
      
      
      pan.add(title,BorderLayout.NORTH);
      pan.add(loginshpe,BorderLayout.CENTER);
      pan.add(category,BorderLayout.SOUTH);
      
//      아직 추가 못한것들      
//      pan.add(picture);
//      pan.add(bmi);
//      pan.add(id1);
//      pan.add(id2);
//      pan.add(pw1);
//      pan.add(pw2);
//      pan.add(membership);
//      pan.add(in);
      
      
      this.sc.setBounds(0,0 ,1385, 865);
      add(sc);
   }

   public BasicShape() {
      setTitle("기본shape테스트");
      setSize(1400, 900);
      // setSize(1920,1080);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      cp.setBackground(Color.WHITE);
      comp();
      setVisible(true);
   }

   public static void main(String[] args) {
      try {
          for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName())) {
                  UIManager.setLookAndFeel(info.getClassName());
                  break;
              }
          }
      } catch (Exception e) {
          // If Nimbus is not available, you can set the GUI to another look and feel.
      }
      new BasicShape();
   }// main end

}