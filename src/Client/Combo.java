package Client;


//그냥 간단한 프레임위의 목표란( 콤보박스포함)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Combo extends JFrame implements ActionListener{

	Container con;


	private Combo self = this;

	private JLabel labelPhoto1 = new JLabel("포토1");
	private JLabel labelPhoto2 = new JLabel("포토2");
	private JLabel labelPhoto3 = new JLabel("포토3");

	private JLabel labelPhotoTitle = new JLabel("타이틀");

	private JLabel labelPhotoFace = new JLabel("포토7");

	private JButton buttonInfo = new JButton("info");
	private JButton buttonDailyGoal = new JButton("하루목표");
	private JButton buttonGoal = new JButton("목표");
	private JButton buttonphoto = new JButton("사진"); 
	private JButton buttonvideo = new JButton("비디오");
	private JButton buttonCommunity = new JButton("커뮤니티");

	private JButton buttonUpload = new JButton("업로드");
	 
	private JButton buttonClose = new JButton("홈으로");



	private JPanel panelWest = new JPanel(new GridLayout(7,1)); //사이드바 
	private JPanel panelNorth = new JPanel(); //타이틀
	private JPanel panelCenter = new JPanel(new GridLayout(2,1)); // 센터-포토6개
	private JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private JPanel panelCombo = new JPanel((new FlowLayout()));
	private JPanel panelwater = new JPanel((new FlowLayout()));



	private JLabel today3 = new JLabel("오늘의 목표를 3개 선택하세요");
	private JLabel waterchecklabel = new JLabel("물마신 횟수를 체크해보세요");

	private JButton fullcup = new JButton(new ImageIcon("full.JPG"));

	private JButton cupb1 = new JButton(new ImageIcon("empty.JPG"));

	private JButton cupb2= new JButton(new ImageIcon("empty.JPG"));
	private JButton cupb3 = new JButton(new ImageIcon("empty.JPG"));

	private JButton cupb4 = new JButton(new ImageIcon("empty.JPG"));
	private JButton cupb5 = new JButton(new ImageIcon("empty.JPG"));





	public void compInit(){



		panelCenter.add(panelCombo);

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

		panelCenter.add(panelCombo,BorderLayout.NORTH);
		panelCenter.add(panelwater,BorderLayout.SOUTH);

		panelCombo.add(today3);
		panelCombo.add(combolist1);
		panelCombo.add(combolist2);
		panelCombo.add(combolist3);

		panelwater.add(waterchecklabel);
		panelwater.add(cupb1);
		
		panelwater.add(cupb2);
		panelwater.add(cupb3);
		panelwater.add(cupb4);
		panelwater.add(cupb5);

//버튼크기에 맞춰 이미지삽입
		cupb1.setBorderPainted(false);
		cupb1.setFocusPainted(false); 
		cupb1.setContentAreaFilled(false);
		cupb1.addActionListener(this); 

		setVisible(true);

		cupb2.setBorderPainted(false);
		cupb2.setFocusPainted(false); 
		cupb2.setContentAreaFilled(false);
		cupb2.addActionListener(this); 

		setVisible(true);

		cupb3.setBorderPainted(false);
		cupb3.setFocusPainted(false); 
		cupb3.setContentAreaFilled(false);
		cupb3.addActionListener(this); 

		setVisible(true);

		cupb4.setBorderPainted(false);
		cupb4.setFocusPainted(false); 
		cupb4.setContentAreaFilled(false);
		cupb4.addActionListener(this); 

		setVisible(true);

		cupb5.setBorderPainted(false);
		cupb5.setFocusPainted(false); 
		cupb5.setContentAreaFilled(false);
		cupb5.addActionListener(this); 

		setVisible(true);


		this.panelNorth.add(labelPhotoTitle);

		this.panelWest.add(labelPhotoFace);
		this.panelWest.add(buttonInfo);
		this.panelWest.add(buttonDailyGoal);
		this.panelWest.add(buttonGoal);
		this.panelWest.add(buttonphoto);
		this.panelWest.add(buttonvideo);
		this.panelWest.add(buttonCommunity);

		this.panelSouth.add(buttonUpload);
		
		this.panelSouth.add(buttonClose);

		this.add(panelWest, BorderLayout.WEST);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNorth , BorderLayout.NORTH);
		this.add(panelSouth , BorderLayout.SOUTH);



		setVisible(true);



	}

	void init(){
		cupb1.setIconTextGap(1);
		fullcup.setIconTextGap(1);
		con=this.getContentPane();
		con.add("Center",cupb1);
	}

	 void start(){
		  cupb1.addActionListener(this);
		  fullcup.addActionListener(this);
		     
		 }
	public void eventInit(){


		cupb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((e.getSource()).equals(cupb1)){
					con.removeAll();
					con.add(fullcup);
					fullcup.updateUI();
				}
				else{
					con.removeAll();
					con.add(cupb1);
					cupb1.updateUI();
				}
			}

		});

		//업로드버튼 누를시
		//		buttonUpload.addActionListener(new ActionListener(){
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				//			self.compInit().combolist1.getSelectedItem().toString();
		//
		//			}
		//
		//		});
	}




	public Combo(){




		this.setSize(800,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(Combo.EXIT_ON_CLOSE);
		this.init();
		this.start();
		this.compInit();
		this.eventInit();
		
		this.setVisible(true);
		



	}

	public static void main(String[] args) {
		new Combo();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}



}