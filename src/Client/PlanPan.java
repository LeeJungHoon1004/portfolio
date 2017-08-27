package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PlanPan extends JPanel {
	BasicShape parent;

	private JComboBox combolist1;
	private JComboBox combolist2;
	private JComboBox combolist3;

	private  TitledBorder tborder = new TitledBorder("");

	private JLabel j1 = new JLabel("캘린더");
	private JLabel j2 = new JLabel("bmi");
	private JLabel j3 = new JLabel("콤보");
	private JLabel j4 = new JLabel("물컵");

	private JPanel mainPanel = new JPanel(new GridLayout(3,1));

	private JPanel firstPanel = new JPanel(); //캘린더 넣는패널
	private JPanel secondPanel = new JPanel(); //bmi랑 콤보 패널
	private JPanel thirdPanel = new JPanel(); //물컵 넣는 패널


	//--콤보리스트




	//---bmi
	private JLabel stature = new JLabel("키 : ");
	private JLabel weight = new JLabel("체중 : ");
	private JLabel gender = new JLabel("성별 : ");
	private JTextField inputStature = new JTextField();
	private JTextField inputWeight = new JTextField();
	private JRadioButton male = new JRadioButton("남자");
	private JRadioButton female = new JRadioButton("여자");
	private ButtonGroup group = new ButtonGroup();
	private JButton resultButton = new JButton("결과");
	private JPanel genderPan = new JPanel();
	private JPanel radioPan = new JPanel(new GridLayout(1, 2));
	// ==============△△△BMI측정 데이터입력UI△△△=========================
	private String name;
	private double result;
	private String grade;
	private ImageIcon img = new ImageIcon("bmi비만도.jpg");
	private JLabel imgResult = new JLabel(img);
	private JLabel result1 = new JLabel();
	private JLabel result2 = new JLabel();
	private JLabel result3 = new JLabel();
	private JPanel resultPan = new JPanel(new GridLayout(1, 2));
	private Font f = new Font("바탕", Font.ITALIC, 21);



	public void comp() {
		//패널 배치

				this.add(mainPanel);
		//패널들에 컴포넌트 넣기

		this.mainPanel.add(firstPanel);
		this.mainPanel.add(secondPanel);
		this.mainPanel.add(thirdPanel);

		this.firstPanel.add(j1);
		this.firstPanel.setBorder(tborder);
		this.secondPanel.add(j2);
		this.secondPanel.setBorder(tborder);
		this.thirdPanel.add(j4);
		this.thirdPanel.setBorder(tborder);

		//콤보
		

		secondPanel.setLayout(null);

		Object[] action = { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기",
				"4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트",
				"6.플랭크 1분 3세트", "7.저녁안먹기", "8.샤워하며 마사지하기",
				"9.자기전 하늘자전거 5분", "10.일어나서 물한잔 원샷" };
		combolist1 = new JComboBox();
		for (int i = 0; i < action.length; i++) {

			combolist1.addItem(action[i]);

		}
		combolist1.setPreferredSize(new Dimension(150, 50));
		combolist1.setEditable(false);
		combolist1.setBounds(200, 100, 10, 10);



		secondPanel.add(combolist1);

		//bmi 컴포넌트위치




		thirdPanel.setLayout(null);


		
		thirdPanel.add(imgResult);
		thirdPanel.add(stature);
		thirdPanel.add(inputStature);
		thirdPanel.add(weight);
		thirdPanel.add(inputWeight);
		thirdPanel.add(gender);
		group.add(male);
		group.add(female);
		radioPan.setBackground(Color.white);
		radioPan.add(male);
		radioPan.add(female);
		thirdPanel.add(radioPan);

		thirdPanel.add(resultButton);

		imgResult.setBounds(65, 76, 800, 400);	
		stature.setBounds(112, 500, 44, 40);
		inputStature.setBounds(171,500, 255, 40);
		weight.setBounds(492,500, 56, 40);
		inputWeight.setBounds(562,500,255, 40);
		gender.setBounds(112,550, 60, 50);
		radioPan.setBounds(178,550, 252, 50);
		resultButton.setBounds(670, 560, 84, 40);


		









		//각 패널에 화이트색넣기
		this.mainPanel.setBackground(Color.WHITE);
		this.firstPanel.setBackground(Color.WHITE);
		this.secondPanel.setBackground(Color.WHITE);
		this.thirdPanel.setBackground(Color.WHITE);
	}
	public void event() {

	}

	public PlanPan(BasicShape parent) { //클라이언트에서 BasicShape와 연결되있는 생성자.
		this.parent = parent;

		this.setBackground(Color.white);
		this.setSize(900, 900);
		this.comp();
		this.event();
		this.setLayout(new FlowLayout());
		this.setVisible(true);
	}
	public PlanPan() { // 서버에서 참조하는 생성자.


		this.setBackground(Color.white);
		this.setSize(900, 900);
		this.comp();
		this.event();
		this.setVisible(true);
	}


}
