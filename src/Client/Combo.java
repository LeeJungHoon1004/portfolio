package Client;


//그냥 간단한 프레임위의 목표란( 콤보박스포함)
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Combo extends JFrame{

	private Combo self = this;

	private JLabel labelPhoto1 = new JLabel("포토1");
	private JLabel labelPhoto2 = new JLabel("포토2");
	private JLabel labelPhoto3 = new JLabel("포토3");

	private JLabel labelPhotoTitle = new JLabel("타이틀");

	private JLabel labelPhotoFace = new JLabel("포토7");

	private JButton buttonInfo = new JButton("info");
	private JButton buttonDailyGoal = new JButton("하루목표");
	private JButton buttonGoal = new JButton("목표");
	private JButton buttonphoto = new JButton("사진"); // 나자신선택
	private JButton buttonvideo = new JButton("비디오");
	private JButton buttonCommunity = new JButton("커뮤니티");

	private JButton buttonUpload = new JButton("업로드");
	private JButton buttonRemove = new JButton("사진삭제"); //사진삭제시 id와 패스워드 비번확인 필요함.
	private JButton buttonClose = new JButton("닫기");


	private JPanel panelWest = new JPanel(new GridLayout(7,1)); //사이드바 
	private JPanel panelNorth = new JPanel(); //타이틀
	private JPanel panelCenter = new JPanel(new GridLayout(1,2)); // 센터-포토6개
	private JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private JPanel panelCombo = new JPanel((new FlowLayout()));



	private JLabel today3 = new JLabel("오늘의 목표를 3개 선택하세요");
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



		panelCombo.add(today3);
		panelCombo.add(combolist1);
		panelCombo.add(combolist2);
		panelCombo.add(combolist3);
		
	
		

		//		String selected1 = combolist1.getSelectedItem().toString();
		//		System.out.println(selected1);
		//		String selected2 = combolist2.getSelectedItem().toString();
		//		System.out.println(selected2);
		//		String selected3 = combolist3.getSelectedItem().toString();
		//		System.out.println(selected3);

		this.panelNorth.add(labelPhotoTitle);

		this.panelWest.add(labelPhotoFace);
		this.panelWest.add(buttonInfo);
		this.panelWest.add(buttonDailyGoal);
		this.panelWest.add(buttonGoal);
		this.panelWest.add(buttonphoto);
		this.panelWest.add(buttonvideo);
		this.panelWest.add(buttonCommunity);

		this.panelSouth.add(buttonUpload);
		this.panelSouth.add(buttonRemove);
		this.panelSouth.add(buttonClose);

		this.add(panelWest, BorderLayout.WEST);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNorth , BorderLayout.NORTH);
		this.add(panelSouth , BorderLayout.SOUTH);
	}
	
	public void eventInit(){

		buttonUpload.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//			self.compInit().combolist1.getSelectedItem().toString();


			}

		});
	}
	public Combo(){



		this.setLocationRelativeTo(null);
		this.setSize(800,800);
		this.setDefaultCloseOperation(Combo.EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Combo();
	}

}