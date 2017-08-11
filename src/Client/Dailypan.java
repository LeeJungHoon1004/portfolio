package Client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dailypan extends JPanel{

	private JLabel today3 = new JLabel("오늘의 목표를 3개 선택하세요");
	private JLabel waterchecklabel = new JLabel("물마신 횟수를 체크해보세요");
	
	
	private JButton buttonUpload = new JButton("업로드");

	
	private JButton buttonselect = new JButton("선택완료");
	
	
	//------------------------
	
	
	private Image cupimage = new ImageIcon("empty.jpg").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH);
	
	private ImageIcon icon1 = new ImageIcon(cupimage);
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
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
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
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(10,10,50,0);
		
		
		c3.gridy = 6; c3.gridx = 1;
		this.add(waterchecklabel,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.insets = new Insets(10,20,50,0);
		
		c4.gridy = 7; c4.gridx = 1;
		this.add(cupPan,c4);
		
		c4.gridy = 8; c4.gridx = 1;
		this.add(cupPan2,c4);
		
	
		c3.gridy = 9; c3.gridx =2;
		this.add(buttonUpload,c3);
		
		
		
//		this.add(buttonClose);
		
	}
	public void event() {

	}


	public Dailypan() {
		
		
		this.setSize(900,900);
		
		this.comp();
		this.event();

		this.setVisible(true);

	}



	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.add(new Dailypan());
		f.setVisible(true);
		f.setSize(800, 800);
	}


}
