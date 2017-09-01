package Server;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

class ConnectionThread extends Thread {

	private ConnectionThread self = this;

	public ConnectionThread getSelf() {
		return self;
	}

	public void setSelf(ConnectionThread self) {
		this.self = self;
	}

	private String name;
	private String gender;
	private String id;
	private String pw;

	private String dailyList;

	// private int dailyList [] = new int[3] ;
	// 소켓
	private Socket socket = null;
	// 아웃 스트림
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	// 인풋스트림
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

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
			System.out.println(socket.getInetAddress() + "님이접속했습니다");

			while (true) {
				String cmd = dis.readUTF();

				if (cmd.equals("회원가입")) {
					/*
					 * 회원가입 name ,id , pw ,gender
					 * 
					 * 
					 */
					String name = dis.readUTF();
					String gender = dis.readUTF();
					String id = dis.readUTF();
					String pw = dis.readUTF();

					Member m = new Member(name, id, pw, gender);
					// 클라이언트로 받은 아이디와 DB에 저장되어있는 내용인지 검사합니다.
					// 생성자에서 받은 정보중에서 id만 검사합니다
					System.out.println(name);
					boolean result = Server.manager.isExist(m);
					System.out.println(result);
					// 값이 존재한다.(존재하는아이디) //값이 없다 회원가입 시켜줌.
					if (result) {
						System.out.println("test1");
						dos.writeUTF("회원가입실패");
					} else {
						System.out.println("test2");
						dos.writeUTF("회원가입성공");
						// 생성자에서 받은 정보중에서 name, id , pw , gender를 저장합니다.
						int result2 = Server.manager.insertData(m);

						if (result2 > 0) {
							System.out.println("회원정보입력에 성공했습니다");
						} else {
							System.out.println("회원정보입력에 실패했습니다.");
						}
					}
					dos.flush();
					// dos.close();

				}

				else if (cmd.equals("로그인")) {

					id = dis.readUTF();
					pw = dis.readUTF();
					// 로그인후에 로그인창에는 이름을 출력
					// 로그인후에 BMI란에는 저장되있는 신체지수데이터를 불러오고
					// 성별 ,신장, 체중 를 가져옵니다.

					Member m2 = new Member(id, pw);
					boolean result = Server.manager.isLoginOk(m2);
					System.out.println("회원목록의 존재유무 :  " + result);
					if (result) {// 값이존재한다(회원가입 되어있는 아이디와비번이다) - 로그인허가
						dos.writeUTF("로그인성공");

						// 1.로그인성공후 등록한 회원의 이름 전송.
						String name = null;
						Member m1 = new Member(name, id, pw);
						// 멤버의 정보 (id,pw를 준뒤 해당 사용자의 이름을 가져옴)
						name = Server.manager.getNameData(m2);
						System.out.println("로그인한 회원님 : " + name);
						// 이름 , 신장, 체중 ,성별 순서대로 보냅니다
						dos.writeUTF(name);
						// 2.로그인성공후 등록한 회원의 combolist전송.
						// 2.1 DB에 해당 회원의 ComboListData있는지 조사
						// 2.2 DB에 해당 회원의 ComboListData유무에 따라
						// 2.2.1명령커맨드보내거나 2.2.2리스트 보내거나
						String sentComboListData = Server.manager.getComboListData(m2);
						System.out.println("DB에 저장되어있는 ComboListData :" + sentComboListData);
						if (sentComboListData != null) {
							dos.writeUTF(sentComboListData);
						} else {
							dos.writeUTF("비어있는ComboList데이터");
						}
						// 3.로그인성공후 등록된 회원의 watercuplist전송.

					} else {
						dos.writeUTF("로그인실패");
					}
					dos.flush();
					// dos.close();

					// client 에 로그인이후부터 콤보리스트 저장한거 보내기

					// String watercuplist = Server.manager.getComboListData(id,
					// pw);
					// System.out.println(watercuplist);
					// dos.writeUTF("콤보리스트3개");
					// dos.writeUTF(combolist);

				}
				// dailyPan combolist 데이터 수신
				else if (cmd.equals("하루목표전송")) {

					String combolist = dis.readUTF();
					System.out.println(combolist);

					// String list, String id
					Member m = new Member(id, pw);
					int result = Server.manager.InsertDailyList(id, combolist);
					if (result != 0) {
						System.out.println("결과가있다.");
					} else {
						System.out.println("결과가없다");
					}

					System.out.println("하루목표전송받았다.");

				}

				// 물컵체크에 대한 커맨드 수신.
				else if (cmd.equals("물컵체크")) {
					String ChangeCupListAA = dis.readUTF();
					System.out.println("물컵체크리스트를 클라이언트에서 받음 :" + ChangeCupListAA);

					int msg = Server.manager.InsertWaterCuplist(id, ChangeCupListAA);
					System.out.println("물컵체크리스트를 서버에서 DB로 저장함 : (존재하면1 없으면 0) " + msg);

				}

				// 커뮤니티팬에서 받은 이미지와 String 데이터 수신
				else if (cmd.equals("소켓연결후데이터수신")) {

					// 전송하려는 파일의 이름 , 크기 , 내용물(파일자체) , 파일의 타이틀 ,파일의 내용
					String title = null;
					String contents = null;
					String fileName = null;
					int fileSize = 0;
					byte[] fileContents = null;

					// 2.클라이언트에서 데이터를 받습니다 (2.ClientRam to ServerRam)
					ois = new ObjectInputStream(socket.getInputStream());
					FileList fl1 = (FileList) ois.readObject();
					FileList fl2 = (FileList) ois.readObject();
					// FileList fl3 = (FileList) ois.readObject();

					System.out.println(fl1.getTitle()); // 제목
					System.out.println(fl1.getContents());// 코멘트
					System.out.println(fl1.getFileName());// 파일이름
					System.out.println(fl1.getFileSize());// 파일의 크기(int)
					System.out.println(fl1.getFileContents());// 파일의 내용물(byte
																// [])

					System.out.println(fl2.getTitle());
					System.out.println(fl2.getContents());
					System.out.println(fl2.getFileName());
					System.out.println(fl2.getFileSize());
					System.out.println(fl2.getFileContents());

					// System.out.println(fl3.getTitle());
					// System.out.println(fl3.getContents());
					// System.out.println(fl3.getFileName());
					// System.out.println(fl3.getFileSize());
					// System.out.println(fl3.getFileContents());

					// 3.클라이언트에서 받은 데이터를 경로에 저장합니다 (3.Ram to Hdd)
					fileContents = fl1.getFileContents();
					File f = new File("D:/6월자바_이정훈_2차/자바기반웹개발자양성_7월/Server/" + fl1.getFileName());
					fos = new FileOutputStream(f);
					bos = new BufferedOutputStream(fos);
					dos = new DataOutputStream(bos);
					dos.write(fileContents);
					dos.flush();

					fileContents = fl2.getFileContents();
					File f2 = new File("D:/6월자바_이정훈_2차/자바기반웹개발자양성_7월/Server/" + fl2.getFileName());
					fos = new FileOutputStream(f2);
					dos = new DataOutputStream(fos);
					dos.write(fileContents);
					dos.flush();

					// fileContents = fl3.getFileContents();
					// File f3 = new
					// File("E:/프로그래밍/Java언어/자바기반웹프로그래머양성_7월/Server/" +
					// fl3.getFileName());
					// fos = new FileOutputStream(f3);
					// dos = new DataOutputStream(fos);
					// dos.write(fileContents);
					// dos.flush();

					System.out.println("클라이언트에서 받은 데이터를 하드디스크로 저장완료.");

					dos.close();

				} else if (cmd.equals("클라이언트데이터 송신")) {

				}

			}

		} catch (Exception e) {
			System.out.println("소켓연결해제.");
			System.out.println("사용자가 로그아웃하였습니다.");
			e.printStackTrace();
			try {
				dos.close();
			} catch (Exception e1) {
				// e1.printStackTrace();
			}
		}
	}

}

public class Server extends JFrame {
	// 매니저
	public static Manager manager = new Manager();
	// 컴포넌트변수
	private Server self = this;

	// Component Variable
	private JLabel title = new JLabel();
	private JPanel category = new JPanel(new GridLayout(5, 1));
	private JPanel titlePan = new JPanel();
	private JPanel sidepan = new JPanel(new GridLayout(5, 1));
	private JPanel panbox1 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox2 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox3 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox = new JPanel(new GridLayout(3, 1));
	// 로그인 중일때
	private JPanel panboxx = new JPanel(new GridLayout(3, 1));
	private JPanel namePan = new JPanel();
	private JPanel logoutPan = new JPanel();
	// 카드레이아웃 여기있음
	private CardLayout card = new CardLayout();

	private JPanel mainPan = new JPanel(card);
	private JPanel profilePan = new JPanel(card);// 로그인전후 바뀔 프로필패널
	// COMPNENT - homePan
	private Image titleimage = new ImageIcon("타이틀06.jpg").getImage().getScaledInstance(1194, 100,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon titleicon = new ImageIcon(titleimage);

	private JPanel homePan = new JPanel(new GridLayout(3, 1));
	private JScrollPane homeSc = new JScrollPane(homePan);

	// homePan 에다가 부착함.
	// urlPan
	private JPanel urlWholePan = new JPanel(new BorderLayout());
	private JPanel urlPan = new JPanel(new GridLayout(5, 6));
	private JPanel urlButtonPan = new JPanel(new GridLayout(5, 1));

	private JLabel yogaLabel = new JLabel("요가");
	private JButton yogaButton1 = new JButton("요가1");
	private JButton yogaButton2 = new JButton("요가2");
	private JButton yogaButton3 = new JButton("요가3");
	private JButton yogaButton4 = new JButton("요가4");
	private JButton yogaButton5 = new JButton("요가5");

	private JLabel stretchingLabel = new JLabel("스트레칭");
	private JButton stretchingButton1 = new JButton("스트레칭1");
	private JButton stretchingButton2 = new JButton("스트레칭2");
	private JButton stretchingButton3 = new JButton("스트레칭3");
	private JButton stretchingButton4 = new JButton("스트레칭4");
	private JButton stretchingButton5 = new JButton("스트레칭5");

	private JLabel mileyCyrusLabel = new JLabel("마일리사이러스");
	private JButton mileyCyrusButton1 = new JButton("마일리사이러스1");
	private JButton mileyCyrusButton2 = new JButton("마일리사이러스2");
	private JButton mileyCyrusButton3 = new JButton("마일리사이러스3");
	private JButton mileyCyrusButton4 = new JButton("마일리사이러스4");
	private JButton mileyCyrusButton5 = new JButton("마일리사이러스5");

	private JLabel homeDietLabel = new JLabel("홈다이어트");
	private JButton homeDietButton1 = new JButton("홈다이어트1");
	private JButton homeDietButton2 = new JButton("홈다이어트2");
	private JButton homeDietButton3 = new JButton("홈다이어트3");
	private JButton homeDietButton4 = new JButton("홈다이어트4");
	private JButton homeDietButton5 = new JButton("홈다이어트5");

	private JLabel smihottLabel = new JLabel("스미홈트");
	private JButton smihottButton1 = new JButton("스미홈트1");
	private JButton smihottButton2 = new JButton("스미홈트2");
	private JButton smihottButton3 = new JButton("스미홈트3");
	private JButton smihottButton4 = new JButton("스미홈트4");
	private JButton smihottButton5 = new JButton("스미홈트5");

	private JButton urlRenewalButton = new JButton("URL갱신");
	private JButton urlConfirmButton = new JButton("URL확인");

	private String[] buttonName = new String[25];
	private int[] fileSize = new int[25];
	private String[] targetUrlImageFileName = new String[25];
	private File targetFile[] = new File[25];

	private String urlImageFileName[] = new String[25];
	private String urlPath[] = new String[25];
	private String ServerdirectoryPath;
	// homePan에다가 부착함.
	// articlePan
	private JPanel articleWholePan = new JPanel(new BorderLayout());
	private JPanel articlePan = new JPanel(new BorderLayout());
	private JPanel articleButtonPan = new JPanel(new GridLayout(5, 1));

	private String[] columnArticle = new String[] { "글번호", "ID(작성자)", "글제목(title)", "작성시간(REGDATE)" };
	private String[][] data;

	private Vector<String> userColumn = new Vector<String>();
	private DefaultTableModel model;
	private JTable userTable;
	private JScrollPane listJS;

	private JButton articleRenewalButton = new JButton("목록갱신");
	private JButton articleDeleteButton = new JButton("목록삭제");

	public void columnAdd() {
		model = new DefaultTableModel(data, columnArticle);
		// model = new DefaultTableModel(userColumn, 0);
		userTable = new JTable(model);
		listJS = new JScrollPane(userTable);

		userTable.getColumn("글번호").setPreferredWidth(30);
		userTable.getColumn("ID(작성자)").setPreferredWidth(280);
		userTable.getColumn("글제목(title)").setPreferredWidth(100);
		userTable.getColumn("작성시간(REGDATE)").setPreferredWidth(250);
		// userTable.getColumn("FileName").setPreferredWidth(50);
		// userTable.getColumn("FileSize").setPreferredWidth(100);

	}

	public void compInit() {

		setLayout(null);
		// 투명

		// ---------홈
		homePan.setBackground(Color.white);
		// homePan - articlePan
		// 글번호,작성자(id),글제목(title),작성시간(regdate)
		this.articlePan.setLayout(new BorderLayout());
		this.articlePan.add(listJS);

		this.articleButtonPan.add(articleRenewalButton);
		this.articleButtonPan.add(articleDeleteButton);

		this.articlePan.setBackground(Color.WHITE);
		this.articleButtonPan.setBackground(Color.WHITE);
		this.articleWholePan.add(articleButtonPan, BorderLayout.EAST);
		this.articleWholePan.add(articlePan, BorderLayout.CENTER);
		this.homePan.add(articleWholePan);
		// homePan - urlPan
		// 요가,스트레칭,마일리사이러스,홈다이어트,스미홈트
		this.urlPan.add(yogaLabel);
		this.urlPan.add(yogaButton1);
		this.urlPan.add(yogaButton2);
		this.urlPan.add(yogaButton3);
		this.urlPan.add(yogaButton4);
		this.urlPan.add(yogaButton5);

		this.urlPan.add(stretchingLabel);
		this.urlPan.add(stretchingButton1);
		this.urlPan.add(stretchingButton2);
		this.urlPan.add(stretchingButton3);
		this.urlPan.add(stretchingButton4);
		this.urlPan.add(stretchingButton5);

		this.urlPan.add(mileyCyrusLabel);
		this.urlPan.add(mileyCyrusButton1);
		this.urlPan.add(mileyCyrusButton2);
		this.urlPan.add(mileyCyrusButton3);
		this.urlPan.add(mileyCyrusButton4);
		this.urlPan.add(mileyCyrusButton5);

		this.urlPan.add(homeDietLabel);
		this.urlPan.add(homeDietButton1);
		this.urlPan.add(homeDietButton2);
		this.urlPan.add(homeDietButton3);
		this.urlPan.add(homeDietButton4);
		this.urlPan.add(homeDietButton5);

		this.urlPan.add(smihottLabel);
		this.urlPan.add(smihottButton1);
		this.urlPan.add(smihottButton2);
		this.urlPan.add(smihottButton3);
		this.urlPan.add(smihottButton4);
		this.urlPan.add(smihottButton5);
		this.urlWholePan.add(urlPan, BorderLayout.CENTER);

		this.urlButtonPan.add(urlRenewalButton);
		this.urlButtonPan.add(urlConfirmButton);
		this.urlWholePan.add(urlButtonPan, BorderLayout.EAST);

		this.homePan.add(urlWholePan);
		// compInit() - panelCard_StimulsPhoto
		this.urlButtonPan.setBackground(Color.WHITE);
		this.urlPan.setBackground(Color.white);
		category.setBackground(Color.WHITE);
		sidepan.setBackground(Color.WHITE);
		panbox1.setBackground(Color.WHITE);
		panbox2.setBackground(Color.WHITE);
		panbox3.setBackground(Color.WHITE);
		panbox.add(panbox1);
		panbox.add(panbox2);
		panbox.add(panbox3);
		// CardLayout이 들어있는 profilePan에 넣음.

		panboxx.setBackground(Color.white);
		namePan.setBackground(Color.white);
		logoutPan.setBackground(Color.white);
		profilePan.add(panboxx, "loginAfter");
		// =======================================================
		// title.setFont(font);
		title.setIcon(titleicon);
		titlePan.add(title);
		titlePan.setBounds(0, 0, 1194, 100);
		this.add(titlePan);
		sidepan.setBounds(0, 101, 200, 640);
		this.add(sidepan);
		// CardLayout들어있는 mainPan에 패널들 넣음
		this.mainPan.add(homeSc, "NamedefaultPan");
		// 이름을 부여함 .
		// 부여된 이름을 가지고 이벤트 처리부분에서
		// 카드의 이름으로 식별하여 visible함.
		this.setResizable(false);
		this.mainPan.setBounds(200, 99, 1000, 650);
		add(mainPan);
	}

	public void eventInit() {

		File home = new File(ServerdirectoryPath);

		// yogaButton1에대한 이벤트처리입니다.
		this.yogaButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[0] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[0] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[0] + "\n" + "URL:" + urlPath[0]);

			}

		});
		this.yogaButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[1] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[1] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[1] + "\n" + "URL:" + urlPath[1]);

			}

		});
		this.yogaButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[2] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[2] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[2] + "\n" + "URL:" + urlPath[2]);

			}

		});
		this.yogaButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[3] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[3] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[3] + "\n" + "URL:" + urlPath[3]);

			}

		});
		this.yogaButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[4] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[4] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[4] + "\n" + "URL:" + urlPath[4]);

			}

		});
		this.stretchingButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[5] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[5] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[5] + "\n" + "URL:" + urlPath[5]);

			}

		});
		this.stretchingButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[6] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[6] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[6] + "\n" + "URL:" + urlPath[6]);

			}

		});

		this.stretchingButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[7] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[7] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[7] + "\n" + "URL:" + urlPath[7]);

			}

		});

		this.stretchingButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[8] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[8] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[8] + "\n" + "URL:" + urlPath[8]);

			}

		});

		this.stretchingButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[9] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[9] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[9] + "\n" + "URL:" + urlPath[9]);

			}

		});

		this.mileyCyrusButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[10] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[10] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[10] + "\n" + "URL:" + urlPath[10]);

			}

		});
		this.mileyCyrusButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[11] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[11] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[11] + "\n" + "URL:" + urlPath[11]);

			}

		});

		this.mileyCyrusButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[12] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[12] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[12] + "\n" + "URL:" + urlPath[12]);

			}

		});
		this.mileyCyrusButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[13] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[13] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[13] + "\n" + "URL:" + urlPath[13]);

			}

		});
		this.mileyCyrusButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[14] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[14] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[14] + "\n" + "URL:" + urlPath[14]);

			}

		});

		this.homeDietButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[15] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[15] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[15] + "\n" + "URL:" + urlPath[15]);

			}

		});
		this.homeDietButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[16] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[16] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[16] + "\n" + "URL:" + urlPath[16]);

			}

		});
		this.homeDietButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[17] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[17] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[17] + "\n" + "URL:" + urlPath[17]);

			}

		});
		this.homeDietButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[18] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[18] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[18] + "\n" + "URL:" + urlPath[18]);

			}

		});
		this.homeDietButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[19] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[19] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[19] + "\n" + "URL:" + urlPath[19]);

			}

		});
		this.smihottButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[20] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[20] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[20] + "\n" + "URL:" + urlPath[20]);

			}

		});
		this.smihottButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[21] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[21] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[21] + "\n" + "URL:" + urlPath[21]);

			}

		});
		this.smihottButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[22] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[22] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[22] + "\n" + "URL:" + urlPath[22]);

			}

		});
		this.smihottButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[23] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[23] = JOptionPane.showInputDialog("URL입력");
				JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[23] + "\n" + "URL:" + urlPath[23]);

			}

		});

		this.smihottButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[24] = JOptionPane.showInputDialog("파일의이름 입력");
				urlPath[24] = JOptionPane.showInputDialog("URL입력");

				if (JOptionPane.showConfirmDialog(null, "파일이름:" + urlImageFileName[24] + "\n" + "URL:" + urlPath[24],
						"알림", JOptionPane.YES_NO_OPTION) == 0) {
					// DB에 저장할 파일이름 형식은 버튼이름_파일이름_url_현재시각 로 한다.

					buttonName[24] = "smihottButton5";
					targetUrlImageFileName[24] = buttonName[24] + "_" + urlImageFileName[24] + "_" + urlPath[24];
					targetFile[24] = new File(home.getPath() + "/" + targetUrlImageFileName[24]);
					fileSize[24] = (int) targetFile[24].length();
					
					//vfl 인스턴스화 (경로 , 파일이름 ,사이즈 , 버튼이름 )
					VideoFileList vfl = new VideoFileList(urlPath[24], targetUrlImageFileName[24], fileSize[24],
							buttonName[24]);
					System.out.println("요가5번 데이터전송준비끝.");

					try {
						boolean result = Server.manager.isExistUrlData(vfl);
						if (result) { // 결과가 존재한다 - > 업데이트
							Server.manager.updateUrlData(vfl);
						} else {// 결과가 없다 - > 인서트
							Server.manager.insertUrlData(vfl);
						}
						
						System.out.println("스미홈트5번 버튼 데이터입력 성공.");
					} catch (Exception e1) {
						System.out.println("스미홈트5번 버튼 데이터입력 오류다.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.urlConfirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", JOptionPane.YES_NO_OPTION);
				// JOptionPane.show
			}

		});

	}

	// 생성자
	public Server() {

		// 프로그램의 공통파일 만들기!
		ServerdirectoryPath = "C:/4weeksWorkoutServer";
		// 파일 객체 생성
		File file = new File(ServerdirectoryPath);
		// !표를 붙여주어 파일이 존재하지 않는 경우의 조건을 걸어줌
		if (!file.exists()) {
			// 디렉토리 생성 메서드
			file.mkdirs();
			System.out.println("created directory successfully!");
		}

		this.setSize(1200, 750);
		this.setTitle("서버");
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(Server.EXIT_ON_CLOSE);
		this.columnAdd();
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
