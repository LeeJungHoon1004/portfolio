
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	//------------------------

	private String result;
	
	
	

	private Image cupimage = new ImageIcon("empty2.png").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH); //빈컵이미지
	private Image fullcupimage = new ImageIcon("full2.png").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH); //물들어있는컵
	
	
	private Image water_effect = new ImageIcon("waterinform.jpg").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH);

	private ImageIcon water_effect_icon = new ImageIcon(water_effect);
	private JLabel water_inform = new JLabel();

	private ImageIcon icon1 = new ImageIcon(cupimage);
	private ImageIcon icon2 = new ImageIcon(cupimage);
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
		cupPan.setPreferredSize(new Dimension(100, 350));
		cupPan.add(cupb1);

		cupPan.add(cupb2);
		cupPan.add(cupb3);
		cupPan.add(cupb4);
		cupPan.add(cupb5);


		cupPan2.setPreferredSize(new Dimension(100, 350));
		cupPan2.add(cupb6);
		cupPan2.add(cupb7);
		cupPan2.add(cupb8);
		cupPan2.add(cupb9);
		cupPan2.add(cupb10);
		
		
		this.water_inform.setIcon(water_effect_icon);

		//컵 1-10까지 아이콘 에 사진넣기 
		this.cupb1.setIcon(icon1);
		this.cupb1.setPreferredSize(new Dimension(31, 47));
		this.cupb2.setIcon(icon2);
		this.cupb2.setPreferredSize(new Dimension(31, 47));
		this.cupb3.setIcon(icon1);
		this.cupb3.setPreferredSize(new Dimension(31, 47));
		this.cupb4.setIcon(icon1);
		this.cupb4.setPreferredSize(new Dimension(31, 47));
		this.cupb5.setIcon(icon1);
		this.cupb5.setPreferredSize(new Dimension(31, 47));
		this.cupb6.setIcon(icon1);
		this.cupb6.setPreferredSize(new Dimension(31, 47));
		this.cupb7.setIcon(icon1);
		this.cupb7.setPreferredSize(new Dimension(31, 47));
		this.cupb8.setIcon(icon1);
		this.cupb8.setPreferredSize(new Dimension(31, 47));
		this.cupb9.setIcon(icon1);
		this.cupb9.setPreferredSize(new Dimension(31, 47));
		this.cupb10.setIcon(icon1);
		this.cupb10.setPreferredSize(new Dimension(31, 47));

		
		//그리드백을 이용하여 컴포넌트 배치함..
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		//콤보박스 배열이용하여 목록 넣고, 배치 
		String[] action = { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기", 
				"4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트" , "6.플랭크 1분 3세트" ,"7.저녁안먹기","8.샤워하며 마사지하기"
				,"9.자기전 하늘자전거 5분","10.일어나서 물한잔 원샷"};

		JComboBox combolist1 = new JComboBox(action);

		combolist1.addItem(action);
		combolist1.setEditable(false);



		JComboBox combolist2 = new JComboBox(action);
		combolist2.addItem(action);
		combolist2.setEditable(false);

		JComboBox combolist3 = new JComboBox(action);
		combolist3.addItem(action);
		combolist3.setEditable(false);



		//		this.cupb1.setIcon(icon1);
		//		this.cupb1.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb1);
		//		
		//		this.cupb2.setIcon(icon1);
		//		this.cupb2.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb2);
		//		
		//		this.cupb3.setIcon(icon1);
		//		this.cupb3.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb3);
		//		
		//		
		//		this.cupb4.setIcon(icon1);
		//		this.cupb4.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb4);
		//		
		//		
		//		this.cupb5.setIcon(icon1);
		//		this.cupb5.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb5);
		//		
		//		
		//		this.cupb6.setIcon(icon1);
		//		this.cupb6.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb6);
		//		
		//		
		//		this.cupb7.setIcon(icon1);
		//		this.cupb7.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb7);
		//		
		//		
		//		this.cupb8.setIcon(icon1);
		//		this.cupb8.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb8);
		//		
		//		
		//		this.cupb9.setIcon(icon1);
		//		this.cupb9.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb9);
		//		
		//		
		//		this.cupb10.setIcon(icon1);
		//		this.cupb10.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb10);
		//		


		c.insets = new Insets(10,10,40,0); //공간, 시계방향 

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
		
		c3.gridy = 7; c3.gridx = 1;
		this.add(water_inform,c3);
	

		
		//컵 패널 
		GridBagConstraints c4 = new GridBagConstraints();
		c4.insets = new Insets(10,20,30,0);

		c4.gridy = 8; c4.gridx = 1;
		this.add(cupPan,c4);

		c4.gridy = 9; c4.gridx = 1;
		this.add(cupPan2,c4);


		c3.gridy = 10; c3.gridx =2;
		this.add(buttonUpload,c3);



		//		this.add(buttonClose);

	}
	
//	public void client(){
//		
//		try {
//			client = new Socket("127.0.0.1", 40000);
//			dos = new DataOutputStream(client.getOutputStream());
//			dis = new DataInputStream(client.getInputStream());
//			System.out.println("초기연결성공");
//		} catch (Exception e1) {
//			System.out.println("초기연결실패");
//		}
//
//	}
	
	
	
	
	//컵 1을 누르면 물컵 아이콘이 바뀜  
	public void event() {
		
		buttonselect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				
//				String selected1 = combolist1.getSelectedItem().toString();
//				System.out.println(selected1);
//				String selected2 = combolist1.getSelectedItem().toString();
//				String selected3 = combolist1.getSelectedItem().toString();
			
			
			
//			try{
//				dos.writeUTF("선택완료"); //리스트 선택완료
//				dos.writeUTF(selected1);
//				dos.writeUTF(selected2);
//				dos.writeUTF(selected3);
//				System.out.println("데이터보내기 성공! ");
//			} catch (Exception e1) {
//				System.out.println("데이터 보내기 실패");
		//
//			}
			
//			try {
//				
//				 result = dis.readUTF();
//				if (result.equals("전송성공")) {
//						JOptionPane.showMessageDialog(null, "전송 성공");
		//
//					} else if (result.equals("전송실패")) {
//						JOptionPane.showMessageDialog(null, "전송에 실패하였습니다.");
//					}
//					System.out.println("전송 성공");
//			}catch (Exception e2) {
		//
//			}
			
			
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
		

//		cupb1.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup1 =! changecup1;
//				if(changecup1 == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb1.setIcon(icon1);
//				}
//
//			
//		});
//
//		cupb2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup2 =! changecup2;
//				if(changecup2 == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb2.setIcon(icon1);
//				}
//
//			
//		});
		
//		cupb3.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb3.setIcon(icon1);
//				}
//
//			
//		});
//		
//		cupb4.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb4.setIcon(icon1);
//				}
//
//			
//		});
//
//		cupb5.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb5.setIcon(icon1);
//				}
//
//			
//		});
//		cupb6.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb6.setIcon(icon1);
//				}
//
//			
//		});
//		cupb7.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb7.setIcon(icon1);
//				}
//
//			
//		});
//		cupb8.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb8.setIcon(icon1);
//				}
//
//			
//		});
//		cupb9.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb9.setIcon(icon1);
//				}
//
//			
//		});
//		cupb10.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb10.setIcon(icon1);
//				}
//
//			
//		});

	}
	
	


	public Dailypan() {


		this.setSize(900,900);
//		this.client();
		this.comp();
		this.event();

		
		this.setVisible(true);

	}



//	public static void main(String[] args) {
//
//		
//		JFrame f = new JFrame();
//		f.add(new Dailypan());
//		f.setSize(500,500);
//		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
//		f.setLocationRelativeTo(null);
//		f.setVisible(true);
//		
//		
//
//
//}
}

