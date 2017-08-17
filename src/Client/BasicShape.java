package Client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
// 나 주석 달았다.
public class BasicShape extends JFrame {
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;

	// =======SOCKET========================

	private Container cp = this.getContentPane();

	private JLabel title = new JLabel("title", JLabel.CENTER);
	private Font font = new Font("바탕", Font.ITALIC, 30);

	private JButton goalBt = new JButton("나의 목표");
	private JButton dailyBt = new JButton("하루 목표");
	private JButton videoBt = new JButton("운동영상");
	private JButton imgBoardBt = new JButton("사진게시판");

	private JPanel category = new JPanel(new GridLayout(4, 1));
	private JPanel titlePan = new JPanel();
	private JPanel sidepan = new JPanel(new GridLayout(5, 1));

	// ▽▽▽▽▽▽▽▽▽▽프로필 바뀜▽▽▽▽▽▽▽▽▽▽▽▽
	// 로그아웃 중일때
	private JLabel lbID = new JLabel("아이디 :");
	private JTextField inputID = new JTextField();
	private JLabel lbPW = new JLabel("비밀번호 :");
	private JPasswordField inputPW = new JPasswordField();
	private JButton membership = new JButton("회원가입");
	private JButton login = new JButton("로그인");
	private JPanel panbox1 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox2 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox3 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox = new JPanel(new GridLayout(3, 1));
	// 로그인 중일때
	private JPanel panboxx = new JPanel(new GridLayout(3, 1));
	private JPanel namePan = new JPanel();
	private JPanel photoPan = new JPanel();
	private JPanel logoutPan = new JPanel();
	private ImageIcon photo = new ImageIcon("");
	private JLabel profilename = new JLabel();
	private JLabel profilePhoto = new JLabel();
	private JButton logout = new JButton("로그아웃");
	private String result;

	// 카드레이아웃 여기있음
	private CardLayout card = new CardLayout();
	private BasicShape self = this;
	private JPanel mainPan = new JPanel(card);
	private JScrollPane sc = new JScrollPane(mainPan);
	private JPanel profilePan = new JPanel(card);// 로그인전후 바뀔 프로필패널

	// COMPNENT - defaultPan
	private JPanel defaultPan = new JPanel(new GridLayout(2, 1));
	private JPanel panelUpper = new JPanel();
	private JPanel panellower = new JPanel();
	private JLabel labelUpper = new JLabel("이미지슬라이드1");
	private JLabel labellower = new JLabel("이미지BMI");

	// COMPNENT - home
	private JPanel picture = new JPanel();
	private JPanel bmi = new JPanel();
	private String name;
	// COMPNENT - goalPan
	private JPanel goalPan = new JPanel();

	// COMPNENT - dailyPan
	private Dailypan dailyPan = new Dailypan();
	
	// COMPNENT - videoPan
	private JPanel videoPan = new JPanel();

	// COMPNENT - imgBoardPan
	private JLabel labelPhoto1 = new JLabel("포토1");
	private JLabel labelPhoto2 = new JLabel("포토2");
	private JLabel labelPhoto3 = new JLabel("포토3");
	private JButton buttonUpload = new JButton("업로드");
	private JButton buttonRemove = new JButton("사진삭제"); // 사진삭제시 id와 패스워드 비번확인 필요함.
	private JButton buttonClose = new JButton("닫기");
	private JPanel imgBoardPan = new JPanel(new GridLayout(3, 1));
	private JPanel panelnull = new JPanel();
	private JPanel panelCenter = new JPanel(new GridLayout(1, 3)); // 센터-포토3개
	private JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	// =======COMPONENT========================

	public void comp() {

		setLayout(null);

		this.panelUpper.add(labelUpper);
		this.panellower.add(labellower);
		this.defaultPan.add(panelUpper);
		this.defaultPan.add(panellower);

		// compInit() -defaultPan

		this.panelCenter.add(labelPhoto1);
		this.panelCenter.add(labelPhoto2);
		this.panelCenter.add(labelPhoto3);
		this.panelSouth.add(buttonUpload);
		this.panelSouth.add(buttonRemove);
		this.panelSouth.add(buttonClose);
		this.imgBoardPan.add(panelCenter);
		this.imgBoardPan.add(panelnull);
		this.imgBoardPan.add(panelSouth);
		
		// compInit() - panelCard_StimulsPhoto

		category.add(goalBt);
		category.add(dailyBt);
		category.add(videoBt);
		category.add(imgBoardBt);

		panbox1.add(lbID);
		panbox1.add(inputID);
		panbox2.add(lbPW);
		panbox2.add(inputPW);
		panbox3.add(login);
		panbox3.add(membership);
		panbox.add(panbox1);
		panbox.add(panbox2);
		panbox.add(panbox3);

		// CardLayout이 들어있는 profilePan에 넣음.
		photoPan.add(profilePhoto);
		profilename.setText(name + " 님 환영합니다!");
		namePan.add(profilename);
		logoutPan.add(logout);

		panboxx.add(photoPan, BorderLayout.CENTER);
		panboxx.add(profilename, BorderLayout.CENTER);
		panboxx.add(logoutPan, BorderLayout.CENTER);

		profilePan.add(panbox, "loginBefore");
		profilePan.add(panboxx, "loginAfter");
		// =======================================================

		title.setFont(font);
		titlePan.add(title);

		sidepan.add(profilePan);
		sidepan.add(category);

		titlePan.setBounds(0, 0, 1400, 80);
		add(titlePan);
		sidepan.setBounds(0, 81, 200, 640);
		add(sidepan);

		// CardLayout들어있는 mainPan에 패널들 넣음
		mainPan.add(defaultPan, "NamedefaultPane");
		mainPan.add(goalPan);
		mainPan.add(dailyPan, "NamedailyBoard");
		mainPan.add(videoPan);
		mainPan.add(imgBoardPan, "NameimgBoard"); // 카드로 끼워넣는팬에
		// 이름을 부여함 .
		// 부여된 이름을 가지고 이벤트 처리부분에서
		// 카드의 이름으로 식별하여 visible함.

		this.setResizable(false);
		this.sc.setBounds(200, 79, 1000, 650);
		add(sc);

	}

	public String clientConnect() {
		// 로그인 버튼
		try {
		//	client = new Socket("", 4000);
			client = new Socket("127.0.0.1", 40000);
			
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			System.out.println("초기연결성공");
		} catch (Exception e1) {
			System.out.println("초기연결실패");
		}

		String userID = inputID.getText();
		String userPW = inputPW.getText();

		try {
			dos.writeUTF("로그인");
			dos.writeUTF(userID);
			dos.writeUTF(userPW);
			System.out.println("데이터보내기 성공");
		} catch (Exception e1) {
			System.out.println("데이터 보내기 실패");
		}

		try {

			result = dis.readUTF();
			if (result.equals("로그인성공")) {
				JOptionPane.showMessageDialog(null, "로그인 성공");

			} else if (result.equals("로그인실패")) {
				JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
			}
		
		} catch (Exception e2) {

		}

		return result;
	}// end

	public void eventInit() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 clientConnect();
				//서버에 계정 보냄.△△△△△△△
				
				if(result.equals("로그인성공")){
				card.show(self.profilePan, "loginAfter");
				}
				else if (result.equals("로그인실패")) {
				card.show(self.profilePan, "loginBefore");
				}
				//프로필창 로그인여부에 따라 다름.△△△△△△△
			}
		});

		//로그아웃 버튼
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.close();
					System.out.println("성공적으로 소켓종료");
				} catch (IOException e1) {
					System.out.println("소켓종료 실패");
				}
				card.show(self.profilePan, "loginBefore");
				
			}
		});
		
		
		//회원가입 버튼
		membership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원가입창 띄우기
				new SignUp(self).setVisible(true);
			}
		});

		
		//내목표 버튼
		goalBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(result.equals("로그인성공")){
					card.show(self.profilePan, "loginAfter");
					}
//					else if (result.equals("로그인실패")) {
//					card.show(self.profilePan, "loginBefore");
//					}
				else {
					card.show(self.profilePan, "loginBefore");
					}
				//프로필창 로그인여부에 따라 다름.△△△△△△△
			}
		});

		
		//오늘의목표 버튼
		dailyBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(result.equals("로그인성공")){
					card.show(self.profilePan, "loginAfter");
					}
//					else if (result.equals("로그인실패")) {
//					card.show(self.profilePan, "loginBefore");
//					}
				else {
					card.show(self.profilePan, "loginBefore");
					}
				//프로필창 로그인여부에 따라 다름.△△△△△△△
				card.show(self.mainPan, "NamedailyBoard" );
			}
		});

		//영상게시판 버튼
		videoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(result.equals("로그인성공")){
					card.show(self.profilePan, "loginAfter");
					}
//					else if (result.equals("로그인실패")) {
//					card.show(self.profilePan, "loginBefore");
//					}
				else {
					card.show(self.profilePan, "loginBefore");
					}
				//프로필창 로그인여부에 따라 다름.△△△△△△△
			}
		});

		//사진게시판 버튼
		imgBoardBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(result.equals("로그인성공")){
					card.show(self.profilePan, "loginAfter");
					}
//					else if (result.equals("로그인실패")) {
//					card.show(self.profilePan, "loginBefore");
//					}
				else {
					card.show(self.profilePan, "loginBefore");
					}
//				//프로필창 로그인여부에 따라 다름.△△△△△△△
				card.show(self.mainPan, "NameimgBoard");
				
			}
		});

		//제목 리스너
		title.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			// 마우스버튼이 누른뒤 뗄때의 이벤트처리
			@Override
			public void mouseReleased(MouseEvent e) {

				if(result.equals("로그인성공")){
					card.show(self.profilePan, "loginAfter");
					}
//					else if (result.equals("로그인실패")) {
//					card.show(self.profilePan, "loginBefore");
//					}
				else {
					card.show(self.profilePan, "loginBefore");
					}
//				//프로필창 로그인여부에 따라 다름.△△△△△△△
				card.show(self.mainPan, "NamedefaultPane");
			}

		});

		// ===============EVENTINIT()=========imgBoardPan
		this.buttonUpload.addActionListener(new ActionListener() {
			// 사진 업로드시 xx회원님의 포토. 로 남겨둠 .
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		this.buttonClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		this.buttonRemove.addActionListener(new ActionListener() {
			// 사진 삭제시 회원의 아이디와 비밀번호를 한번입력받아서 확인후 해당회원의 사진일경우에 삭제 .
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	public String userData() {

		try {
			name = dis.readUTF();
			System.out.println("이름데이터받기 성공");
		} catch (Exception e) {
			System.out.println("데이터받기 실패");
		}
		
		return name;
	}

	public BasicShape() {
		setTitle("기본shape테스트");
		// setSize(700, 500);
		setSize(1200, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp.setBackground(Color.WHITE);
		comp();
		eventInit();
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
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		new BasicShape();
	}// main end
}

// http://msource.tistory.com/5
// 카드레이아웃 쉬운 설명
// http://www.w3ii.com/ko/swing/swing_cardlayout.html
// 카드레이아웃 클래스