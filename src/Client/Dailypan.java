package Client;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dailypan extends JPanel{
   private Socket client;
   private DataOutputStream dos;
   private DataInputStream dis;
   
   //----------------소켓 
   
   private JComboBox combolist1 = new JComboBox();
   private JComboBox combolist2 = new JComboBox();
   private JComboBox combolist3 = new JComboBox();
   
   private JLabel today3 = new JLabel("오늘의 목표를 3개 선택하세요");
   private Font font = new Font("나무", Font.ITALIC, 20);
   
   private JLabel waterchecklabel = new JLabel("물마신 횟수를 체크해보세요! ");
   
   
   private JButton buttonUpload = new JButton("체크완료"); //체크완료 버튼


   private JButton buttonselect = new JButton("선택완료"); //선택완료 버튼 (콤보쪽)

   private Dailypan self = this;
   
   private boolean changecup1 =false;
   private boolean changecup2 =false;
   private boolean changecup3 =false;
   private boolean changecup4 =false;
   private boolean changecup5 =false;
   private boolean changecup6 =false;
   private boolean changecup7 =false;
   private boolean changecup8 =false;
   private boolean changecup9 =false;
   private boolean changecup10 =false;
   
   
   
   //------------------------

   private String result;
   
   

   private Image cupimage = new ImageIcon("empty2.png").getImage()
         .getScaledInstance(43, 72, java.awt.Image.SCALE_SMOOTH); //빈컵이미지
   private Image fullcupimage = new ImageIcon("full2.png").getImage()
         .getScaledInstance(43, 72, java.awt.Image.SCALE_SMOOTH); //물들어있는컵
   
   
   private Image water_effect = new ImageIcon("waterInform.jpg").getImage()
         .getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);

   
   private ImageIcon iconwater = new ImageIcon(water_effect);
   
   private ImageIcon icon1 = new ImageIcon(cupimage);
   private ImageIcon icon2 = new ImageIcon(cupimage);
   private ImageIcon icon3 = new ImageIcon(cupimage);
   private ImageIcon icon4 = new ImageIcon(cupimage);
   private ImageIcon icon5 = new ImageIcon(cupimage);
   private ImageIcon icon6 = new ImageIcon(cupimage);
   private ImageIcon icon7 = new ImageIcon(cupimage);
   private ImageIcon icon8 = new ImageIcon(cupimage);
   private ImageIcon icon9 = new ImageIcon(cupimage);
   private ImageIcon icon10 = new ImageIcon(cupimage);
   
   
   private JLabel water_inform = new JLabel(iconwater);
   
   private JButton cupb1 = new JButton();
   private JButton cupb2= new JButton();
   private JButton cupb3 = new JButton();

   private JButton cupb4 = new JButton();
   private JButton cupb5 = new JButton();
   private JButton cupb6 = new JButton();
   private JButton cupb7 = new JButton();
   private JButton cupb8 = new JButton();
   private JButton cupb9 = new JButton();
   private JButton cupb10 = new JButton();

   private JPanel cupPan = new JPanel(new GridLayout(1,5));

   private JPanel cupPan2 = new JPanel(new GridLayout(1,5));



   public void comp() {

      this.setSize(800, 800);
      
      today3.setFont(font); //오늘의 목표 고르라는 레이블 
      waterchecklabel.setFont(font);
      cupPan.setPreferredSize(new Dimension(300, 100));
   //   cupPan.add(water_inform);
      cupPan.add(cupb1);
      

      cupPan.add(cupb2);
      cupPan.add(cupb3);
      cupPan.add(cupb4);
      cupPan.add(cupb5);


      cupPan2.setPreferredSize(new Dimension(300, 100));
      cupPan2.add(cupb6);
      cupPan2.add(cupb7);
      cupPan2.add(cupb8);
      cupPan2.add(cupb9);
      cupPan2.add(cupb10);
      
      
      


      //컵 1-10까지 아이콘 에 사진넣기 
      this.cupb1.setIcon(icon1);
      this.cupb1.setPreferredSize(new Dimension(43, 72));
      this.cupb2.setIcon(icon2);
      this.cupb2.setPreferredSize(new Dimension(43, 72));
      this.cupb3.setIcon(icon3);
      this.cupb3.setPreferredSize(new Dimension(43, 72));
      this.cupb4.setIcon(icon4);
      this.cupb4.setPreferredSize(new Dimension(43, 72));
      this.cupb5.setIcon(icon5);
      this.cupb5.setPreferredSize(new Dimension(43, 72));
      this.cupb6.setIcon(icon6);
      this.cupb6.setPreferredSize(new Dimension(43, 72));
      this.cupb7.setIcon(icon7);
      this.cupb7.setPreferredSize(new Dimension(43, 72));
      this.cupb8.setIcon(icon8);
      this.cupb8.setPreferredSize(new Dimension(43, 72));
      this.cupb9.setIcon(icon9);
      this.cupb9.setPreferredSize(new Dimension(43, 72));
      this.cupb10.setIcon(icon10);
      this.cupb10.setPreferredSize(new Dimension(43, 72));

      this.water_inform.setIcon(iconwater);
      this.water_inform.setPreferredSize(new Dimension(500, 500));
      
      //그리드백을 이용하여 컴포넌트 배치함..
      this.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();

      
      //콤보박스 배열이용하여 목록 넣고, 배치 
      String[] action = { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기", 
            "4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트" , "6.플랭크 1분 3세트" ,"7.저녁안먹기","8.샤워하며 마사지하기"
            ,"9.자기전 하늘자전거 5분","10.일어나서 물한잔 원샷"};

      JComboBox combolist1 = new JComboBox(action);
      combolist1.setPreferredSize(new Dimension(500, 50));
      combolist1.addItem(action);
      combolist1.setEditable(false);



      JComboBox combolist2 = new JComboBox(action);
      combolist2.setPreferredSize(new Dimension(500, 50));
      combolist2.addItem(action);
      combolist2.setEditable(false);

      JComboBox combolist3 = new JComboBox(action);
      combolist3.setPreferredSize(new Dimension(500, 50));
      combolist3.addItem(action);
      combolist3.setEditable(false);



   


      c.insets = new Insets(0,10,30,0); //공간, 시계방향 

      c.gridy = 1; c.gridx = 1;
      this.add(today3,c);




      //2번 컨테이너. 콤보들 넣음 
      GridBagConstraints c2 = new GridBagConstraints();
      c2.insets = new Insets(10,10,30,0);

      c2.gridy = 2; c2.gridx = 1;
      this.add(combolist1,c2);
      c2.gridy = 3; c2.gridx = 1;
      this.add(combolist2,c2);
      c2.gridy = 4; c2.gridx =1;
      this.add(combolist3,c2);
      c2.gridy = 5; c2.gridx =2;
      this.add(buttonselect,c2);
      
      //3번 컨테이너, 물 체크해보세요 레이블

      GridBagConstraints c3 = new GridBagConstraints();
      c3.insets = new Insets(10,10,30,0);


      c3.gridy = 6; c3.gridx = 1;
      this.add(waterchecklabel,c3);
      
      
   

      
      //컵 패널 
      GridBagConstraints c4 = new GridBagConstraints();
      c4.insets = new Insets(10,20,30,0);
      c4.gridy = 7; c4.gridx = 1;
      this.add(water_inform,c4);
      c4.gridy = 8; c4.gridx = 1;
      this.add(cupPan,c4);

      c4.gridy = 9; c4.gridx = 1;
      this.add(cupPan2,c4);


      c3.gridy = 10; c3.gridx =2;
      this.add(buttonUpload,c3);



      //      this.add(buttonClose);

   }
   
   public void client(){
      
      try {
         client = new Socket("127.0.0.1", 40000);
         dos = new DataOutputStream(client.getOutputStream());
         dis = new DataInputStream(client.getInputStream());
         System.out.println("초기연결성공");
      } catch (Exception e1) {
         System.out.println("초기연결실패");
      }

   }
   
   
   
   
   //컵 1을 누르면 물컵 아이콘이 바뀜  
   public void event() {
      
      buttonselect.addActionListener(new ActionListener(){

         @Override
         public void actionPerformed(ActionEvent e) {
            
            
//            String selected1 = combolist1.getSelectedItem().toString();
//            System.out.println(selected1);
//            String selected2 = combolist1.getSelectedItem().toString();
//            String selected3 = combolist1.getSelectedItem().toString();
         
         
         
//         try{
//            dos.writeUTF("선택완료"); //리스트 선택완료
//            dos.writeUTF(selected1);
//            dos.writeUTF(selected2);
//            dos.writeUTF(selected3);
//            System.out.println("데이터보내기 성공! ");
//         } catch (Exception e1) {
//            System.out.println("데이터 보내기 실패");
      //
//         }
         
//         try {
//            
//             result = dis.readUTF();
//            if (result.equals("전송성공")) {
//                  JOptionPane.showMessageDialog(null, "전송 성공");
      //
//               } else if (result.equals("전송실패")) {
//                  JOptionPane.showMessageDialog(null, "전송에 실패하였습니다.");
//               }
//               System.out.println("전송 성공");
//         }catch (Exception e2) {
      //
//         }
         
         
         //return result;
         
            
               
            
            
         }
         
      });
      
      cupb1.addMouseListener(new MouseListener (){
         @Override
         public void mouseClicked(MouseEvent arg0) {}
         @Override
         public void mouseEntered(MouseEvent e) {}
         @Override
         public void mouseExited(MouseEvent e) {}
         @Override
         public void mousePressed(MouseEvent e) {}
         //마우스를 뗏을때의 이벤트 처리입니다.
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup1 =! changecup1;
            if(changecup1 == true) {
               self.icon1.setImage(fullcupimage);
            }else {
               self.icon1.setImage(cupimage);
               
            }
            self.cupb1.setIcon(icon1);
            }
         
         
      });
      
      cupb2.addMouseListener(new MouseListener (){
         @Override
         public void mouseClicked(MouseEvent arg0) {}
         @Override
         public void mouseEntered(MouseEvent e) {}
         @Override
         public void mouseExited(MouseEvent e) {}
         @Override
         public void mousePressed(MouseEvent e) {}
         //마우스를 뗏을때의 이벤트 처리입니다.
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup2 =! changecup2;
            if(changecup2 == true) {
               self.icon2.setImage(fullcupimage);
            }else {
               self.icon2.setImage(cupimage);
               
            }
            self.cupb2.setIcon(icon2);
            }
         
         
      });
      cupb3.addMouseListener(new MouseAdapter (){
         
      
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup3 =! changecup3;
            if(changecup3 == true) {
               self.icon3.setImage(fullcupimage);
            }else {
               self.icon3.setImage(cupimage);
               
            }
            self.cupb3.setIcon(icon3);
            }
         
         
      });
      cupb4.addMouseListener(new MouseAdapter (){
      
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup4 =! changecup4;
            if(changecup4 == true) {
               self.icon4.setImage(fullcupimage);
            }else {
               self.icon4.setImage(cupimage);
               
            }
            self.cupb4.setIcon(icon4);
            }
         
         
      });
      
      cupb5.addMouseListener(new MouseAdapter (){
         
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup5 =! changecup5;
            if(changecup5 == true) {
               self.icon5.setImage(fullcupimage);
            }else {
               self.icon5.setImage(cupimage);
               
            }
            self.cupb5.setIcon(icon5);
            }
         
         
      });
      cupb6.addMouseListener(new MouseAdapter (){
         
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup6 =! changecup6;
            if(changecup6 == true) {
               self.icon6.setImage(fullcupimage);
            }else {
               self.icon6.setImage(cupimage);
               
            }
            self.cupb6.setIcon(icon6);
            }
         
         
      });
      cupb7.addMouseListener(new MouseAdapter (){
         
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup7 =! changecup7;
            if(changecup7 == true) {
               self.icon7.setImage(fullcupimage);
            }else {
               self.icon7.setImage(cupimage);
               
            }
            self.cupb7.setIcon(icon7);
            }
         
         
      });
      cupb8.addMouseListener(new MouseAdapter (){
         
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup8 =! changecup8;
            if(changecup8 == true) {
               self.icon8.setImage(fullcupimage);
            }else {
               self.icon8.setImage(cupimage);
               
            }
            self.cupb8.setIcon(icon8);
            }
         
         
      });
      cupb9.addMouseListener(new MouseAdapter (){
         
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup9 =! changecup9;
            if(changecup9 == true) {
               self.icon9.setImage(fullcupimage);
            }else {
               self.icon9.setImage(cupimage);
               
            }
            self.cupb9.setIcon(icon9);
            }
         
         
      });
      cupb10.addMouseListener(new MouseAdapter (){
         
         
         @Override
         public void mouseReleased(MouseEvent e) {
            changecup10 =! changecup10;
            if(changecup10 == true) {
               self.icon10.setImage(fullcupimage);
            }else {
               self.icon10.setImage(cupimage);
               
            }
            self.cupb10.setIcon(icon10);
            }
         
         
      });
      
      
      

//      cupb1.addActionListener(new ActionListener() {
//
//         @Override
//         public void actionPerformed(ActionEvent e) {
//
//   
//
//            changecup1 =! changecup1;
//            if(changecup1 == true) {
//               self.icon1.setImage(fullcupimage);
//            }else {
//               self.icon1.setImage(cupimage);
//               
//            }
//            self.cupb1.setIcon(icon1);
//            }
//
//         
//      });
//
   }
   

   public Dailypan() {


      this.setSize(900,900);
      this.client();
      this.comp();
      this.event();

      
      this.setVisible(true);

   }



//   public static void main(String[] args) {
//
//      
//      JFrame f = new JFrame();
//      f.add(new Dailypan());
//      f.setSize(500,500);
//      f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
//      f.setLocationRelativeTo(null);
//      f.setVisible(true);
//      
//      
//
//
//}
}