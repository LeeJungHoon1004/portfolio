package Client;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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
import javax.swing.border.TitledBorder;

import Server.VideoFileList;
import Server.FileList;

public class BasicShape extends JFrame {

	private Socket client;
	// �ƿ� ��Ʈ��
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	// ��ǲ��Ʈ��
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	// =======SOCKET========================

	String receivedComboListData;
	String tmpComboListData[];
	String receiveaction[] = null;
	int tmpcombo1;
	String tmpComboString1;
	int tmpcombo2;
	String tmpComboString2;
	int tmpcombo3;
	String tmpComboString3;
	String tmpComboStringList;

	// ����� ��̸���Ʈ
	private ArrayList<VideoFileList> vflList;

	private String[] urls = new String[25];
	private String[] splitN = new String[25];
	private String[] urlButtons = new String[25];
	private int[] fileSize = new int[25];
	private byte[] filecontents;
	private String[] fileNames = new String[25];
	private String path = "C:/4W/VideoPan";
	private String[] imgpath = new String[25];

	// Ŀ�´�Ƽ ��̸���Ʈ
	private ArrayList<FileList> fl;
	private String title2;
	private String contents;
	private String fileName;
	private int fileSize1 = 0;
	private byte[] fileContents2 = null;

	// ========ComboList Variable====================

	public ArrayList<FileList> getFl() {
		return fl;
	}

	public void setFl(ArrayList<FileList> fl) {
		this.fl = fl;
	}
	
	public ArrayList<VideoFileList> getVflList() {
		return vflList;
	}

	public void setVflList(ArrayList<VideoFileList> vflList) {
		this.vflList = vflList;
	}

	private Container cp = this.getContentPane();
	private JLabel title = new JLabel();

	private Font font = new Font("����", Font.ITALIC, 30);
	private JButton homeBt = new JButton("Ȩ");
	// private JButton dailyBt = new JButton("��ǥ");
	private JButton planBt = new JButton("��ǥ");
	private JButton videoBt = new JButton("�");
	private JButton imgBoardBt = new JButton("Ŀ�´�Ƽ");
	private JButton calandarBt = new JButton("Ķ����");
	private JButton GraphBt = new JButton("�׷���");
	private JPanel category = new JPanel(new GridLayout(5, 1));
	private JPanel titlePan = new JPanel();
	private JPanel sidepan = new JPanel(new GridLayout(5, 1));

	private JLabel passionlabel = new JLabel();

	private Image passionimg = new ImageIcon("��.jpg").getImage().getScaledInstance(187, 130,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon passionicon = new ImageIcon(passionimg);

	// ����������������� �ٲ�������������
	// �α׾ƿ� ���϶�
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
	private JButton membership = new JButton("ȸ������");
	private JButton login = new JButton("�α���");
	private JPanel panbox1 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox2 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox3 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox = new JPanel(new GridLayout(3, 1));
	// �α��� ���϶�
	private JPanel panboxx = new JPanel(new GridLayout(3, 1));
	private JPanel namePan = new JPanel();
	private JPanel logoutPan = new JPanel();
	private JLabel profilename = new JLabel();
	private JLabel profilePhoto = new JLabel();
	private JButton logout = new JButton("�α׾ƿ�");
	private String result;
	// ī�巹�̾ƿ� ��������
	private CardLayout card = new CardLayout();
	private BasicShape self = this;
	private JPanel mainPan = new JPanel(card);
	private JPanel profilePan = new JPanel(card);// �α������� �ٲ� �������г�
	// COMPNENT - homePan
	private Image titleimage = new ImageIcon("Ÿ��Ʋ3.jpg").getImage().getScaledInstance(1194, 100,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon titleicon = new ImageIcon(titleimage);

	private TitledBorder tborder = new TitledBorder("");
	private JPanel homePan = new JPanel(new GridLayout(1, 1));
	private JScrollPane homeSc = new JScrollPane(homePan);

	private String name = getName();
	private String userID = null;
	private String userPW = null;

	private ImageSlide imgSlide = new ImageSlide();
	// private BMI bmi = new BMI();

	// COMPNENT - dailyPan

	private Dailypan dailyPan = new Dailypan(self);
	private JScrollPane dailySc = new JScrollPane(dailyPan);// ��ũ��

	private JPanel calandarPan = new JPanel();
	private CalandarPan calandarpan = new CalandarPan();
	private JScrollPane calandarSc = new JScrollPane(calandarpan);

	private JPanel mygoalPan = new JPanel();
	private myGoalPan mygoalpan = new myGoalPan();
	private JScrollPane mygoalPanSc = new JScrollPane(mygoalpan);

	// COMPNENT - videoPan
	private JPanel videoPan;
	private VideoPan video;
	private JScrollPane videoSc;// ��ũ��

	// COMPNENT - imgBoardPan
	private JPanel imgPanel;
	private PictureBoardPan pbp;
	private JScrollPane picSc;// ��ũ��

	// COMPNENT - planPan
	private JPanel planPan = new JPanel();
	private PlanPan plan = new PlanPan(self);
	private JScrollPane planSc = new JScrollPane(planPan);// ��ũ��

	// ===========================================================================
	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {

		this.userPW = userPW;
	}

	public String getName() {

		try {
			name = dis.readUTF();
		} catch (Exception e1) {
			// System.out.println("Ȩ-������ �̸������͹ޱ� ����");
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// =============================������&���͡��=====================================

	public void comp() {

		setLayout(null);
		// ����

		imgSlide.setPreferredSize(new Dimension(400, 1000));
		// bmi.setPreferredSize(new Dimension(672, 800));
		// bmi.setBorder(tborder);
		this.homePan.add(imgSlide);
		// this.homePan.add(bmi);

		// ��ǥ(plan) ���ο� ��ǥ �г��� (�޷µ���)
		planPan.setBackground(Color.white);
		this.planPan.add(plan);

		// ---------�

		video = new VideoPan(self, client, vflList, urls, fileNames);
		videoPan = new JPanel();
		videoSc = new JScrollPane(videoPan);
		videoSc.getVerticalScrollBar().setUnitIncrement(16);
		videoPan.setBackground(Color.white);
		//videoPan.setPreferredSize(new Dimension(965, 1000));
		this.video.setPreferredSize(new Dimension(965, 1600));
		this.videoPan.add(video);
		System.out.println("����� ������ �ϼ�");
		// ---------Ŀ�´�Ƽ
		
		pbp = new PictureBoardPan(self,client, dis,dos,fl);
		imgPanel = new JPanel();
		picSc = new JScrollPane(imgPanel);// ��ũ��
		picSc.getVerticalScrollBar().setUnitIncrement(16);
		pbp.setBackground(Color.white);
		imgPanel.setBackground(Color.white);
		this.pbp.setPreferredSize(new Dimension(975, 640));
		this.imgPanel.add(pbp);
		System.out.println("Ŀ�´�Ƽ�� ������ �ϼ�");
		
		this.lbID.setIcon(iconid);
		this.lbPW.setIcon(iconpw);

		// compInit() - panelCard_StimulsPhoto
		category.setBackground(Color.WHITE);
		sidepan.setBackground(Color.WHITE);
		panbox1.setBackground(Color.WHITE);
		panbox2.setBackground(Color.WHITE);
		panbox3.setBackground(Color.WHITE);

		category.add(homeBt);
		category.add(planBt);
		// category.add(dailyBt);
		category.add(videoBt);
		category.add(imgBoardBt);
		category.add(calandarBt);
		category.add(GraphBt);

		panbox1.add(lbID);
		panbox1.add(inputID);
		panbox2.add(lbPW);
		panbox2.add(inputPW);
		panbox3.add(login);
		panbox3.add(membership);
		panbox.add(panbox1);
		panbox.add(panbox2);
		panbox.add(panbox3);
		// CardLayout�� ����ִ� profilePan�� ����.

		profilePan.setBackground(Color.white);
		panboxx.setBackground(Color.white);
		namePan.setBackground(Color.white);
		logoutPan.setBackground(Color.white);
		planPan.setBackground(Color.WHITE);
		imgPanel.setBackground(Color.white);

		profilename.setText(name + " �� ȯ���մϴ�!");
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
		sidepan.add(passionlabel);
		titlePan.setBounds(0, 0, 1194, 100);
		add(titlePan);
		sidepan.setBounds(0, 101, 200, 640);
		add(sidepan);
		// CardLayout����ִ� mainPan�� �гε� ����
		this.passionlabel.setIcon(passionicon);
		mainPan.add(homeSc, "NamedefaultPane");
		mainPan.add(dailySc, "NamedailyPane");
		mainPan.add(calandarSc, "NamecalandarPane");
		mainPan.add(planSc, "NameplanPane");
		mainPan.add(videoSc, "NamevideoPane");
		mainPan.add(imgPanel, "NameimgBoard"); // ī��� �����ִ��ҿ�
		mainPan.add(mygoalPanSc, "NamegoalBoard");

		// �̸��� �ο��� .
		// �ο��� �̸��� ������ �̺�Ʈ ó���κп���
		// ī���� �̸����� �ĺ��Ͽ� visible��.
		this.setResizable(false);
		this.mainPan.setBounds(200, 99, 1000, 650);
		add(mainPan);

	}

	public void receiveDataAfterLogin() {

		// �α������Ľ������� �����κ��� �޴� �����͸� ó���մϴ�.
		// 1.��ǥ - Ķ���� �޼��ѵ����� Num ������ �ް� ���������ʹ� �հ�sum �� ���� ������Ų��
		// �Ѵ޵��� ����� sum ���� ������ ������.

		// 4.������url����Ʈ+��Ÿ������(String)
		// 5.(�α������Ľ������� ������ǻ�Ϳ��� �����µ�����)���ε��ѻ��� + �ڸ�Ʈ������ ( String)
		// 1.�޺�����Ʈ������ �ޱ�.
		try {
			receivedComboListData = dis.readUTF();
			System.out.println("������receivedComboListData �� :" + receivedComboListData);
			// �α����� ���� �޺�����Ʈ �����Ͱ� null���̾ƴϸ� �ɰ��� ����Ʈ�� �и��Ѵ�.
			// �����ѵ����Ͱ� null�̾ƴϸ� �ɰ��� �и��Ѵ�.
			// �����ѵ����Ͱ� null�̸� ����ִµ����Ͷ�� ����Ѵ�.
			if (receivedComboListData != null) {
				tmpComboListData = receivedComboListData.split(",");
				receiveaction = new String[] { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�", "4.�������� ����̿��ϱ� ",
						"5.����Ʈ  30���� 3��Ʈ", "6.�÷�ũ 1�� 3��Ʈ", "7.����ȸԱ�", "8.�����ϸ� �������ϱ�", "9.�ڱ��� �ϴ������� 5��",
						"10.�Ͼ�� ������ ����" };

				tmpcombo1 = Integer.parseInt(tmpComboListData[0]);
				tmpComboString1 = receiveaction[tmpcombo1];

				tmpcombo2 = Integer.parseInt(tmpComboListData[1]);
				tmpComboString2 = receiveaction[tmpcombo2];

				tmpcombo3 = Integer.parseInt(tmpComboListData[2]);
				tmpComboString3 = receiveaction[tmpcombo3];

				tmpComboStringList = tmpComboString1 + tmpComboString2 + tmpComboString3;
			} else if (receivedComboListData.equals("����ִ�ComboList������")) {

				tmpComboStringList = "����ִ�ComboList������";
			}
		} catch (Exception e1) {
			System.out.println("�޺�����Ʈ���� �޴°����� �����߻�����.");
			e1.printStackTrace();
		}

		// 2.���ŵ����͹ޱ�.
		// try {
		// String receicedWaterCupData = dis.readUTF();
		// System.out.println("���ŵ����͹ޱ� :" + receicedWaterCupData);
		// } catch (Exception e2) {
		// System.out.println("���Ÿ���Ʈ �޴°����� �����߻�");
		// e2.printStackTrace();
		// }

	}

	// ������ ��������
	public void clientConnect() {

		try {
			client = new Socket("192.168.53.4", 40000);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			System.out.println("�ʱ⿬�Ἲ��");
		} catch (Exception e1) {
			System.out.println("�ʱ⿬�����");
		}

	}// end

	public ArrayList<VideoFileList> receiveData() {
		try {
			
			dos.writeUTF("url�����͹߽�");
			
			ois = new ObjectInputStream(client.getInputStream());
			
			vflList = new ArrayList<VideoFileList>();
			
			vflList = (ArrayList<VideoFileList>) ois.readObject();
			
			System.out.println("ois�� ����Ʈ ���޹ޱ� ����");
			System.out.println(vflList.size());

			System.out.println("������ ������ ����� �𸶼ȸ� ����");

			// dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < vflList.size(); i++) {

			urls[i] = vflList.get(i).getUrlPath();
			splitN[i] = vflList.get(i).getUrlFileName();
			String[] tmp;
			tmp = splitN[i].split("_");

			urlButtons[i] = vflList.get(i).getUrlButtonName();
			fileSize[i] = vflList.get(i).getUrlFileSize();
			filecontents = vflList.get(i).getFileContents();

			// VideoFileList vfl = new VideoFileList(path, path, flags, path,
			// filecontents);
			fileNames[i] = tmp[1];
			System.out.println("�����ҿ��� ���� �����̸� :" + fileNames[i]);
			imgpath[i] = path + "/" + fileNames[i];

			System.out.println("url ������ : " + urls[i]);
			System.out.println("fileNames ������ : " + fileNames[i]);
			System.out.println("urlButtons ������ : " + urlButtons[i]);
			System.out.println("fileSize ������ : " + fileSize[i]);
			System.out.println("filecontents ������ : " + filecontents[i]);
			System.out.println("imgpath ������ :" + imgpath[i]);
			System.out.println("====================");
			System.out.println("����� �迭�� ������ �ֱ� �Ϸ�");

			try {
				File f = new File(imgpath[i]);
				fos = new FileOutputStream(f);
				bos = new BufferedOutputStream(fos);
				dos = new DataOutputStream(bos);
				dos.write(filecontents);
				dos.flush();

				System.out.println("����� ����Ʈ ����");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("����� �迭 ���� ����.");

		}

		return vflList;
	}

	public ArrayList<FileList> receivedCommunityData() {

		try {


			dos.writeUTF("Ŀ�´�Ƽ�гΰ���");
			// ���۹޴� ������ �̸� , ũ�� , ���빰(������ü) , ������ Ÿ��Ʋ ,������ ����

			String title = null;
			String contents = null;
			String fileName = null;
			int fileSize = 0;
			byte[] fileContents = null;

			// 2.�������� �����͸� �޽��ϴ� (2.ServerRam to ClientRam)
			ois = new ObjectInputStream(client.getInputStream());
			ArrayList <FileList> receivedPostingList = new ArrayList <FileList>();
			receivedPostingList = (ArrayList <FileList>) ois.readObject();

			for(int i = 0 ; i< receivedPostingList.size(); i++){
				System.out.println("+++++++Ŀ�´�Ƽ������+++++++");
			System.out.println(receivedPostingList.get(i).getTitle()); // ����
			System.out.println(receivedPostingList.get(i).getContents());// �ڸ�Ʈ
			System.out.println(receivedPostingList.get(i).getFileName());// �����̸�
			System.out.println(receivedPostingList.get(i).getFileSize());// ������ ũ��(int)
			System.out.println(receivedPostingList.get(i).getFileContents());// ������ ���빰(byte [])
			System.out.println(receivedPostingList.get(i).getId());
			
			fileContents = receivedPostingList.get(i).getFileContents();
			File f = new File("C:/4W/PictureBoardPan" + "/" + receivedPostingList.get(i).getFileName());
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);
			dos.write(fileContents);
			dos.flush();
			}
			// ===========================����1�� �𸶼ȸ�
			System.out.println("Ŭ���̾�Ʈ���� ���� Ŀ�´�Ƽ �����͸� �ϵ��ũ�� ����Ϸ�.");

			//dos.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" ������ �ޱ� ����");
		}

		return fl;
	}

	public String getResult() {
		// �α��� ��ư
		userID = inputID.getText();
		userPW = inputPW.getText();

		try {
			dos.writeUTF("�α���");// �α��� �ñ׳�
			dos.writeUTF(userID);
			dos.writeUTF(userPW);
			System.out.println("�����ͺ����� ����");
		} catch (Exception e1) {
			System.out.println("������ ������ ����");
		}

		try {
			result = dis.readUTF();
			if (result.equals("�α��μ���")) {
				JOptionPane.showMessageDialog(null, "�α��� ����");
				System.out.println("�α��� ����");

			} else if (result.equals("�α��ν���")) {
				JOptionPane.showMessageDialog(null, "�α��ο� �����Ͽ����ϴ�.");
				System.out.println("�α��� ����");
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return result;
	}// end

	public void eventInit() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ���ϻ����� Ŀ��Ʈ , �α��μ������� ������ ui���

				getResult();

				// ������ ���� ����.��������
				if (result.equals("�α��μ���")) {

					profilename.setText(getName() + " �� ȯ���մϴ�!");
					card.show(self.profilePan, "loginAfter");
					// receiveDataAfterLogin();

				} else if (result.equals("�α��ν���")) {

					card.show(self.profilePan, "loginBefore");
				}
				// ������â �α��ο��ο� ���� �ٸ�.��������

			}

		});
		// �α׾ƿ� ��ư
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// dos.close();
				// dis.close();
				// client.close();
				// dos = null;
				// dis = null;
				// client = null;
				self.userID = null;
				self.userPW = null;
				result=null;//result �� "�α��μ���"���� ä�����ִ��������.
				System.out.println("���������� ��������");
				inputID.setText("");
				inputPW.setText("");

				card.show(self.profilePan, "loginBefore");
			}
		});
		// ȸ������ ��ư
		membership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ȸ������â ����
				new SignUp(self).setVisible(true);

			}
		});

		homeBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(self.mainPan, "NamedefaultPane");
			}
		});
		planBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				card.show(self.mainPan, "NameplanPane");

			}

		});

		calandarBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				card.show(self.mainPan, "NamecalandarPane");

			}

		});

		// ��ǥ ��ư
		// dailyBt.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// // if() �Ϸ�ð��� �������Ľ���.
		// System.out.println("���Ǹ�ǥ :" + tmpComboStringList);
		// // else �Ϸ�ð��� ��������������. �ȳ���.
		// // JOptionPane.showMessageDialog( , );
		//
		// // ������â �α��ο��ο� ���� �ٸ�.��������
		// card.show(self.mainPan, "NamedailyPane");
		// }
		// });
		// � ��ư
		videoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(self.mainPan, "NamevideoPane");

			}
		});
		// Ŀ�´�Ƽ ��ư
		imgBoardBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�α������Ľ������� result�� �α��μ���
				//�α׾ƿ������Ľ������� result�� null�� ä�����ִ�.
				if(result.equals("�α��μ���")){
					
				}
					
				else{
					JOptionPane.showMessageDialog(null,"�α��θ������ּ���");
					return;
				}
				
				
				// new PictureBoardPan(client,dis,dos);
				card.show(self.mainPan, "NameimgBoard");
			}
		});

		GraphBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				card.show(self.mainPan, "NamegoalBoard");
			}
		});

		// ���� ������
		title.addMouseListener(new MouseAdapter() {

			// ���콺��ư�� ������ ������ �̺�Ʈó��
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				card.show(self.mainPan, "NamedefaultPane");
			}
		});

	}

	public BasicShape() {

		setTitle("�⺻shape�׽�Ʈ");
		setSize(1200, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp.setBackground(Color.WHITE);

		clientConnect(); // sock
		vflList = receiveData();// ����� �г� ������ �ޱ� //dos��.. �ڱ� �ϵ��ũ dos�� �����.
		
		try {
			dos = new DataOutputStream(client.getOutputStream());
		} catch (Exception e) {
			System.out.println("�������� �����͹ް����Ŀ� DataOutputStream�� ������ �ٽ� �����ϴµ��߿� ��������.");
			e.printStackTrace();
		}
		
		fl = receivedCommunityData();//Ŀ�´�Ƽ �г� ������ �ޱ�
		System.out.println("\n"+"�� ���� ���ĺ����ҿ� �������� ����");
		System.out.println(fl.get(0).getContents());
		
		// receiveDataAfterLogin();//���� ������ �ޱ�
		comp();
		eventInit();

		setVisible(true);

	}

	public static void main(String[] args) {

		String path1 = "C:/4W/PictureBoardPan";
	      // ���� ��ü ����
	      File file1 = new File(path1);
	      // !ǥ�� �ٿ��־� ������ �������� �ʴ� ����� ������ �ɾ���
	      if (!file1.exists()) {
	         // ���丮 ���� �޼���
	         file1.mkdirs();
	         System.out.println("created directory successfully!");
	      }
	      String path2 = "C:/4W/VideoPan";
	      // ���� ��ü ����
	      File file2 = new File(path2);
	      // !ǥ�� �ٿ��־� ������ �������� �ʴ� ����� ������ �ɾ���
	      if (!file2.exists()) {
	         // ���丮 ���� �޼���
	         file2.mkdirs();
	         System.out.println("created directory successfully!");
	      }

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
