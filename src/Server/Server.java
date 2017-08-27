package Server;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
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


import Server.ImageSlidePan;


class ConnectionThread extends Thread {

	String name;
	String gender;
	String id;
	String pw;

	private String dailyList;
	// private int dailyList [] = new int[3] ;
	private Socket socket = null;
	private DataInputStream dis;
	private DataOutputStream dos;

	public ConnectionThread(Socket socket) {
		try {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("서버:소켓초기화 실패했다.");
		}
	}

	public void run() {

		try {
			System.out.println(socket.getInetAddress()+ "님이접속했습니다");

			while(true) {
				String cmd = dis.readUTF();

				if(cmd.equals("회원가입")) {
					/* 회원가입 name ,id , pw ,gender
					 *  
					 * 
					 */
					String name = dis.readUTF();
					String gender = dis.readUTF();
					String id = dis.readUTF();
					String pw = dis.readUTF();


					Member m = new Member(name , id , pw , gender );
					//클라이언트로 받은 아이디와  DB에 저장되어있는 내용인지 검사합니다.
					//생성자에서 받은 정보중에서 id만 검사합니다
					System.out.println(name);
					boolean result  = Server.manager.isExist(m); 
					System.out.println(result);
					//값이 존재한다.(존재하는아이디) //값이 없다 회원가입 시켜줌.
					if(result) {
						System.out.println("test1");
						dos.writeUTF("회원가입실패");
					}else { 
						System.out.println("test2");
						dos.writeUTF("회원가입성공");
						//생성자에서 받은 정보중에서 name, id , pw , gender를 저장합니다.
						int result2=	Server.manager.insertData(m);

						if(result2 > 0 ){
							System.out.println("회원정보입력에 성공했습니다");
						}else{
							System.out.println("회원정보입력에 실패했습니다.");
						}
					}
					dos.flush();
					//	dos.close();

				}

				else if(cmd.equals("로그인")) {

					id = dis.readUTF();
					pw = dis.readUTF();
					//로그인후에 로그인창에는 이름을 출력
					//로그인후에 BMI란에는 저장되있는 신체지수데이터를 불러오고
					//성별 ,신장, 체중 를 가져옵니다.


					Member m2 = new Member(id , pw);
					boolean result = Server.manager.isLoginOk(m2);
					System.out.println("회원목록의 존재유무 :  " +result);
					if (result) {// 값이존재한다(회원가입 되어있는 아이디와비번이다) - 로그인허가
						dos.writeUTF("로그인성공");

						//1.로그인성공후 등록한 회원의 이름 전송.
						String name = null;
						Member m1 = new Member(name , id ,pw);
						//멤버의 정보 (id,pw를 준뒤 해당 사용자의 이름을 가져옴)
						name =Server.manager.getNameData(m2);
						System.out.println("로그인한 회원님 : " +name);
						//이름 , 신장, 체중 ,성별 순서대로 보냅니다
						dos.writeUTF(name);
						//2.로그인성공후 등록한 회원의 combolist전송.
						//2.1 DB에 해당 회원의 ComboListData있는지 조사
						//2.2 DB에 해당 회원의 ComboListData유무에 따라 
						//2.2.1명령커맨드보내거나 2.2.2리스트 보내거나
						String sentComboListData = Server.manager.getComboListData(m2);
						System.out.println("DB에 저장되어있는 ComboListData :" +sentComboListData);
						if(sentComboListData !=null) {
						dos.writeUTF(sentComboListData);
						}
						else{
							dos.writeUTF("비어있는ComboList데이터");
						}
						//3.로그인성공후 등록된 회원의 waterlist전송.

					} else {
						dos.writeUTF("로그인실패");
					}
					dos.flush();
					// dos.close();

					//client 에 로그인이후부터 콤보리스트 저장한거 보내기


					//	String watercuplist = Server.manager.getComboListData(id, pw);
					//	System.out.println(watercuplist);
					//	dos.writeUTF("콤보리스트3개");
					//	dos.writeUTF(combolist);


				}
				//dailyPan combolist 데이터 수신
				else if(cmd.equals("하루목표전송")) {

					String combolist = dis.readUTF();
					System.out.println(combolist);

					//String list, String id
					Member m = new Member( id, pw);
					int result =Server.manager.InsertDailyList(id,combolist);
					if(result !=0) {
						System.out.println("결과가있다.");
					}
					else {
						System.out.println("결과가없다");
					}

					System.out.println("하루목표전송받았다.");
					
				}

				//물컵체크에 대한 커맨드 수신.
				else if(cmd.equals("물컵체크")) {
					String ChangeCupListAA = dis.readUTF();
					System.out.println("물컵체크리스트를 클라이언트에서 받음 :" +ChangeCupListAA);

					int msg =Server.manager.InsertWaterCuplist(id,ChangeCupListAA);
					System.out.println("물컵체크리스트를 서버에서 DB로 저장함 : (존재하면1 없으면 0) " +msg);
					
					
				}

			}

		}
		catch(Exception e) {
			System.out.println("소켓연결해제.");
			System.out.println("사용자가 로그아웃하였습니다.");
			e.printStackTrace();
			try{
				dos.close();
			}catch(Exception e1){
				//		e1.printStackTrace();
			}
		}
	}

}

public class Server extends JFrame {
	// 매니저
	public static Manager manager = new Manager();
	// 컴포넌트변수
		private Server self = this;

	//Component Variable
		private Container cp = this.getContentPane();
		private JLabel title = new JLabel();


		private Font font = new Font("바탕", Font.ITALIC, 30);
		private JButton homeBt = new JButton("홈");
		
		private JButton goalBt = new JButton("목표");
		private JButton exerciseBt = new JButton("운동");
		private JButton communityBt = new JButton("커뮤니티");
		private JPanel category = new JPanel(new GridLayout(5, 1));
		private JPanel titlePan = new JPanel();
		private JPanel sidepan = new JPanel(new GridLayout(5, 1));
		// ▽▽▽▽▽▽▽▽▽▽프로필 바뀜▽▽▽▽▽▽▽▽▽▽▽▽
		// 로그아웃 중일때
		private JLabel lbID = new JLabel();
		private JLabel lbPW = new JLabel();

		private Image idimage = new ImageIcon("ID (3).jpg").getImage().getScaledInstance(98, 30,
				java.awt.Image.SCALE_SMOOTH);
		private ImageIcon iconid = new ImageIcon(idimage);

		private Image pwimage = new ImageIcon("PW (2).jpg").getImage().getScaledInstance(98, 30,
				java.awt.Image.SCALE_SMOOTH);
		private ImageIcon iconpw = new ImageIcon(pwimage);
		private JTextField inputID = new JTextField();

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
		private JPanel logoutPan = new JPanel();
		private JLabel profilename = new JLabel();
		private JLabel profilePhoto = new JLabel();
		private JButton logout = new JButton("로그아웃");
		private String result;
		// 카드레이아웃 여기있음
		private CardLayout card = new CardLayout();
		
		private JPanel mainPan = new JPanel(card);
		private JPanel profilePan = new JPanel(card);// 로그인전후 바뀔 프로필패널
		// COMPNENT - homePan
		private Image titleimage = new ImageIcon("타이틀06.jpg").getImage().getScaledInstance(1194, 100,
				java.awt.Image.SCALE_SMOOTH);
		private ImageIcon titleicon = new ImageIcon(titleimage);

		private TitledBorder tborder = new TitledBorder("");
		
		private JPanel homePan = new JPanel(new GridLayout(1, 1));
		private JScrollPane homeSc = new JScrollPane(homePan);
		private String name = getName();
		private ImageSlidePan imgSlide = new ImageSlidePan();
	

		// COMPNENT - goalPan 목표
		private JPanel goalPan = new JPanel();
		private goalPan goal = new goalPan(self);
		private JScrollPane goalSc = new JScrollPane(goalPan);// 스크롤
		
		// COMPNENT - exercisePan 운동
		private JPanel exercisePan = new JPanel();
		private exercisePan exercise = new exercisePan(self);
		private JScrollPane exerciseSc = new JScrollPane(exercisePan);// 스크롤

		// COMPNENT - communityPan 커뮤니티
		private JPanel communityPan = new JPanel();
		private communityPan community = new communityPan(self);
		private JScrollPane communitySc = new JScrollPane(communityPan);// 스크롤

	public void compInit() {

		setLayout(null);
		// 투명
		imgSlide.setPreferredSize(new Dimension(400, 700));
		// ---------홈
		homePan.setBackground(Color.white);
		this.homePan.add(imgSlide);

		// ---------목표
		goalPan.setBackground(Color.white);
		this.goal.setPreferredSize(new Dimension(965, 1500));
		this.goalPan.add(goal);
		// ---------운동
		exercisePan.setBackground(Color.white);
		this.exercise.setPreferredSize(new Dimension(965, 1500));
		this.exercisePan.add(exercise);
		// ---------커뮤니티
		communityPan.setBackground(Color.white);
		this.community.setPreferredSize(new Dimension(965, 1500));
		this.communityPan.add(community);

		this.lbID.setIcon(iconid);
		this.lbPW.setIcon(iconpw);

		// compInit() - panelCard_StimulsPhoto
		category.setBackground(Color.WHITE);
		sidepan.setBackground(Color.WHITE);
		panbox1.setBackground(Color.WHITE);
		panbox2.setBackground(Color.WHITE);
		panbox3.setBackground(Color.WHITE);

		category.add(homeBt);
		category.add(goalBt);
		category.add(exerciseBt);
		category.add(communityBt);
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

		panboxx.setBackground(Color.white);
		namePan.setBackground(Color.white);
		logoutPan.setBackground(Color.white);
		profilename.setText(name + " 님 환영합니다!");
		namePan.add(profilename);
		logoutPan.add(logout);

		panboxx.add(profilename, BorderLayout.CENTER);
		panboxx.add(logoutPan, BorderLayout.CENTER);
		profilePan.add(panbox, "loginBefore");
		profilePan.add(panboxx, "loginAfter");

		// =======================================================
		// title.setFont(font);
		title.setIcon(titleicon);
		titlePan.add(title);
		sidepan.add(profilePan);
		sidepan.add(category);
		titlePan.setBounds(0, 0, 1194, 100);
		add(titlePan);
		sidepan.setBounds(0, 101, 200, 640);
		add(sidepan);
		// CardLayout들어있는 mainPan에 패널들 넣음

		mainPan.add(homeSc, "NamedefaultPan");
		mainPan.add(goalSc, "NamegoalPan");
		mainPan.add(exerciseSc, "NameexercisePan");
		mainPan.add(communitySc, "NamecommunityPan"); // 카드로 끼워넣는팬에
		// 이름을 부여함 .
		// 부여된 이름을 가지고 이벤트 처리부분에서
		// 카드의 이름으로 식별하여 visible함.
		this.setResizable(false);
		this.mainPan.setBounds(200, 99, 1000, 650);
		add(mainPan);
		}

	public void eventInit() {
		
		homeBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(self.mainPan, "NamedefaultPan");
			}
		});
		
		
		// 목표 버튼
		goalBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				card.show(self.mainPan, "NamegoalPan");
			}							
		});
		// 운동 버튼
		exerciseBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(self.mainPan, "NameexercisePan");

			}
		});
		// 커뮤니티 버튼
		communityBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				card.show(self.mainPan, "NamecommunityPan");
			}
		});
		// 제목 리스너
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
				// TODO Auto-generated method stub
				card.show(self.mainPan, "NamedefaultPane");
			}
		});

	}

	// 생성자
	public Server() {
		this.setSize(1200, 750);
		this.setTitle("서버");
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(Server.EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
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
		ServerSocket server = new ServerSocket(40000);
		new Server();
		while (true) {
			new ConnectionThread(server.accept()).start();

		}

	}
	

}
