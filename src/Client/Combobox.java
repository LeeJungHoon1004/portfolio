package Client;

//콤보박스 저번 메인 포함한 목표란
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class Combobox extends JFrame{
	private Container cp = this.getContentPane(); 
	private JTextArea a = new JTextArea();
	private JButton id1 = new JButton("ID:");
	private JButton id2 = new JButton();
	private JButton pw1 = new JButton("PW:");
	private JButton pw2 = new JButton();
	private JButton membership = new JButton("회원가입");
	private JButton in = new JButton("로그인"); 

	private JPanel paneltest = new JPanel();

	private JLabel title = new JLabel("Today's Target",JLabel.CENTER);
	private Font font = new Font("바탕", Font.ITALIC, 30);

	
	private JLabel firstQ = new JLabel(" [ Pick up ! ]꼭 지켜야만 하는 오늘의 목표 3가지를 신중히 골라주세요.");
	
	
	private JLabel secondQ = new JLabel("[Check list] 오늘 하루 잘 지켰나요? ");
	private JLabel watercheck = new JLabel("[water time] 물마신 횟수를 체크해보세요 !");

	
	
	private ImageIcon cupIcon ;
	private JPanel waterpanel = new JPanel(new GridLayout(10,1));	JLabel waterLabel  = new JLabel(cupIcon);
	

	
	private JButton wc1 = new JButton();
	private JButton wc2 = new JButton();
	private JButton wc3 = new JButton();
	
	private JButton wc4 = new JButton();
	private JButton wc5 = new JButton();
	private JButton wc6 = new JButton();
	private JButton wc7 = new JButton();
	private JButton wc8 = new JButton();
	private JButton wc9 = new JButton();
	private JButton wc10 = new JButton();
	

	
	
	
	
	private JLabel loginshpe = new JLabel();
	private JLabel category = new JLabel();
	private JLabel target = new JLabel();
	private JLabel water= new JLabel();
	
	

	private JRadioButton ch1 = new JRadioButton("성공"); 
	private JRadioButton ch2 = new JRadioButton("실패"); 
	

	

	private TitledBorder tborder;

	public void comp(){

		setLayout(null);

		tborder=new TitledBorder("");
		tborder.setTitlePosition(TitledBorder.ABOVE_TOP);//지정한 위치에 타이틀을 나타내주는 보더...
		tborder.setTitleJustification(TitledBorder.CENTER);//자리맞춤을 가운데로 지정...
		title.setBorder(tborder);
		
		loginshpe.setBorder(tborder);
		category.setBorder(tborder);

		target.setBorder(tborder);
		water.setBorder(tborder);
		
		title.setFont(font);
		firstQ.setFont(font);
		secondQ.setFont(font);
		watercheck.setFont(font);

		

		add(title);
		add(loginshpe);
		add(category);

		add(target);
		
		add(water);

		add(firstQ);
		add(watercheck);
		
		
		String[] action = { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기", 
				"4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트" , "6.플랭크 1분 3세트" ,"7.저녁안먹기","8.샤워하며 마사지하기"
				,"9.자기전 하늘자전거 5분","10.일어나서 물한잔 원샷"};

		JComboBox combolist1 = new JComboBox(action);
		combolist1.addItem(action);
		
		combolist1.setEditable(false);
		
		
		
		System.out.println();

		JComboBox combolist2 = new JComboBox(action);
		combolist2.addItem(action);
		combolist2.setEditable(false);
		
		JComboBox combolist3 = new JComboBox(action);
		combolist3.addItem(action);
		combolist3.setEditable(false);
		
		
		

		target.setLayout(null);
		target.add(firstQ);
		target.add(secondQ);
		target.add(combolist1);
		target.add(combolist2);
		target.add(combolist3);
		target.add(ch1);
		target.add(ch2);
		
		water.setLayout(null);
		water.add(waterpanel);
	
		
//		waterpanel.add(wc1);
//		waterpanel.add(wc2);
//		waterpanel.add(wc3);
//		waterpanel.add(wc4);
//		waterpanel.add(wc5);
//		waterpanel.add(wc6);
//		waterpanel.add(wc7);
//		waterpanel.add(wc8);
//		waterpanel.add(wc9);
//		waterpanel.add(wc10);
		
		
//		 ImageIcon ic  = new ImageIcon("./raw/taehee1.png");
//	     JLabel lbImage1  = new JLabel(ic);
//	    
//	     frm.add(lbImage1);
//	     frm.setVisible(true);
//	     frm.setBounds(10, 10, 800, 600);
//	     
//	     frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		cupIcon = new ImageIcon("empty.jpg");
		
		
		
			
		combolist1.setBounds(40,60,500,50);
		combolist2.setBounds(40,130,500,50);
		combolist3.setBounds(40,200,500,50);

		firstQ.setBounds(100, 20, 1000, 30);
		secondQ.setBounds(100, 260, 1000, 30);
		ch1.setBounds(100, 300, 1000, 30);
		ch2.setBounds(150, 300, 1000, 30);
		watercheck.setBounds(400, 650, 1000, 30);

		title.setBounds(370,60,1090,80);
		loginshpe.setBounds(30,120,310,280);
		category.setBounds(30, 420,310,520);
		target.setBounds(370,160,1090,460);
		water.setBounds(370,640,1090,320);
		
		waterpanel.setBounds(370, 740, 1090, 320);
		
		
		id1.setBounds(40,160,100,60);
		id2.setBounds(150,160,170,60);
		pw1.setBounds(40, 240, 100, 60);
		pw2.setBounds(150,240,170,60);
		membership.setBounds(40,310,100,60);
		in.setBounds(150,310,100,60);



		add(id1);
		add(id2);
		add(pw1);
		add(pw2);
		add(membership);
		add(in);
	



	}

	public Combobox(){
		setTitle("목표테스트");
		setSize(1600,900);
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

		}

		new Combobox();
	}//main end
}