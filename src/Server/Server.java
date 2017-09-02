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
import java.util.ArrayList;
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
	
	private static ArrayList<VideoFileList> sendingvflList;

	// ����
	private Socket socket = null;
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

	public ConnectionThread(Socket socket , ArrayList<VideoFileList> receivedvflList ) {
		try {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("����:�����ʱ�ȭ �����ߴ�.");
		}
	}

	public void run() {

		try {
			System.out.println(socket.getInetAddress() + "���������߽��ϴ�");

			
			while (true) {
				String cmd = dis.readUTF();
				
				

				if (cmd.equals("ȸ������")) {
					/*
					 * ȸ������ name ,id , pw ,gender
					 * 
					 * 
					 */
					String name = dis.readUTF();
					String gender = dis.readUTF();
					String id = dis.readUTF();
					String pw = dis.readUTF();

					Member m = new Member(name, id, pw, gender);
					// Ŭ���̾�Ʈ�� ���� ���̵�� DB�� ����Ǿ��ִ� �������� �˻��մϴ�.
					// �����ڿ��� ���� �����߿��� id�� �˻��մϴ�
					System.out.println(name);
					boolean result = Server.manager.isExist(m);
					System.out.println(result);
					// ���� �����Ѵ�.(�����ϴ¾��̵�) //���� ���� ȸ������ ������.
					if (result) {
						System.out.println("test1");
						dos.writeUTF("ȸ�����Խ���");
					} else {
						System.out.println("test2");
						dos.writeUTF("ȸ�����Լ���");
						// �����ڿ��� ���� �����߿��� name, id , pw , gender�� �����մϴ�.
						int result2 = Server.manager.insertData(m);

						if (result2 > 0) {
							System.out.println("ȸ�������Է¿� �����߽��ϴ�");
						} else {
							System.out.println("ȸ�������Է¿� �����߽��ϴ�.");
						}
					}
					dos.flush();
					// dos.close();

				}

				else if (cmd.equals("�α���")) {

					id = dis.readUTF();
					pw = dis.readUTF();
					// �α����Ŀ� �α���â���� �̸��� ���
					// �α����Ŀ� BMI������ ������ִ� ��ü���������͸� �ҷ�����
					// ���� ,����, ü�� �� �����ɴϴ�.

					Member m2 = new Member(id, pw);
					boolean result = Server.manager.isLoginOk(m2);
					System.out.println("ȸ������� �������� :  " + result);
					if (result) {// ���������Ѵ�(ȸ������ �Ǿ��ִ� ���̵�ͺ���̴�) - �α����㰡
						dos.writeUTF("�α��μ���");

						// 1.�α��μ����� ����� ȸ���� �̸� ����.
						String name = null;
						Member m1 = new Member(name, id, pw);
						// ����� ���� (id,pw�� �ص� �ش� ������� �̸��� ������)
						name = Server.manager.getNameData(m2);
						System.out.println("�α����� ȸ���� : " + name);
						// �̸� , ����, ü�� ,���� ������� �����ϴ�
						dos.writeUTF(name);
						// 2.�α��μ����� ����� ȸ���� combolist����.
						// 2.1 DB�� �ش� ȸ���� ComboListData�ִ��� ����
						// 2.2 DB�� �ش� ȸ���� ComboListData������ ����
						// 2.2.1���Ŀ�ǵ庸���ų� 2.2.2����Ʈ �����ų�
						String sentComboListData = Server.manager.getComboListData(m2);
						System.out.println("DB�� ����Ǿ��ִ� ComboListData :" + sentComboListData);
						if (sentComboListData != null) {
							dos.writeUTF(sentComboListData);
						} else {
							dos.writeUTF("����ִ�ComboList������");
						}
						// 3.�α��μ����� ��ϵ� ȸ���� watercuplist����.

					} else {
						dos.writeUTF("�α��ν���");
					}
					dos.flush();
					// dos.close();

					// client �� �α������ĺ��� �޺�����Ʈ �����Ѱ� ������

					// String watercuplist = Server.manager.getComboListData(id,
					// pw);
					// System.out.println(watercuplist);
					// dos.writeUTF("�޺�����Ʈ3��");
					// dos.writeUTF(combolist);

				}
				// dailyPan combolist ������ ����
				else if (cmd.equals("�Ϸ��ǥ����")) {

					String combolist = dis.readUTF();
					System.out.println(combolist);

					// String list, String id
					Member m = new Member(id, pw);
					int result = Server.manager.InsertDailyList(id, combolist);
					if (result != 0) {
						System.out.println("������ִ�.");
					} else {
						System.out.println("���������");
					}

					System.out.println("�Ϸ��ǥ���۹޾Ҵ�.");

				}

				// ����üũ�� ���� Ŀ�ǵ� ����.
				else if (cmd.equals("����üũ")) {
					String ChangeCupListAA = dis.readUTF();
					System.out.println("����üũ����Ʈ�� Ŭ���̾�Ʈ���� ���� :" + ChangeCupListAA);

					int msg = Server.manager.InsertWaterCuplist(id, ChangeCupListAA);
					System.out.println("����üũ����Ʈ�� �������� DB�� ������ : (�����ϸ�1 ������ 0) " + msg);

				}

				// Ŀ�´�Ƽ�ҿ��� ���� �̹����� String ������ ����
				else if (cmd.equals("���Ͽ����ĵ����ͼ���")) {

					// �����Ϸ��� ������ �̸� , ũ�� , ���빰(������ü) , ������ Ÿ��Ʋ ,������ ����
					String title = null;
					String contents = null;
					String fileName = null;
					int fileSize = 0;
					byte[] fileContents = null;

					// 2.Ŭ���̾�Ʈ���� �����͸� �޽��ϴ� (2.ClientRam to ServerRam)
					ois = new ObjectInputStream(socket.getInputStream());
					FileList fl1 = (FileList) ois.readObject();
					FileList fl2 = (FileList) ois.readObject();
					// FileList fl3 = (FileList) ois.readObject();

					System.out.println(fl1.getTitle()); // ����
					System.out.println(fl1.getContents());// �ڸ�Ʈ
					System.out.println(fl1.getFileName());// �����̸�
					System.out.println(fl1.getFileSize());// ������ ũ��(int)
					System.out.println(fl1.getFileContents());// ������ ���빰(byte
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

					// 3.Ŭ���̾�Ʈ���� ���� �����͸� ��ο� �����մϴ� (3.Ram to Hdd)
					fileContents = fl1.getFileContents();
					File f = new File("D:/6���ڹ�_������_2��/�ڹٱ���������ھ缺_7��/Server/" + fl1.getFileName());
					fos = new FileOutputStream(f);
					bos = new BufferedOutputStream(fos);
					dos = new DataOutputStream(bos);
					dos.write(fileContents);
					dos.flush();

					fileContents = fl2.getFileContents();
					File f2 = new File("D:/6���ڹ�_������_2��/�ڹٱ���������ھ缺_7��/Server/" + fl2.getFileName());
					fos = new FileOutputStream(f2);
					dos = new DataOutputStream(fos);
					dos.write(fileContents);
					dos.flush();

					// fileContents = fl3.getFileContents();
					// File f3 = new
					// File("E:/���α׷���/Java���/�ڹٱ�������α׷��Ӿ缺_7��/Server/" +
					// fl3.getFileName());
					// fos = new FileOutputStream(f3);
					// dos = new DataOutputStream(fos);
					// dos.write(fileContents);
					// dos.flush();

					System.out.println("Ŭ���̾�Ʈ���� ���� �����͸� �ϵ��ũ�� ����Ϸ�.");

					dos.close();

				} else if (cmd.equals("url�����͹߽�")) {
//				//	fis = new FileInputStream()
//				
//				//	fis.read(fileContents);
//					
				
					for(int i = 0 ; i<Server.receivedvflList.size(); i++) {
						String urlPath=	Server.receivedvflList.get(i).getUrlPath();
						String urlFileName=	Server.receivedvflList.get(i).getUrlFileName();
						int urlfileSize=Server.receivedvflList.get(i).getUrlFileSize();
						byte[] fileContents = new byte[urlfileSize];		
						//receivedvflList.get(i).getUrlTargetFile();
						String urlButtonName = Server.receivedvflList.get(i).getUrlButtonName();
						String urlTargetFilePath = Server.receivedvflList.get(i).getUrlTargetFilePath();
						//Ŭ���̾�Ʈ�� �����ϴ� Ÿ�������� �̰Ż��
						File targetFile =new File (urlTargetFilePath); //DB�� ������ִ� ���� ������ ��θ� ���ڷγ־ ���� ���� �ν��Ͻ��θ���
						
						//������������ ���� ������ ����ش�.
						fis = new FileInputStream(targetFile);
						fis.read(fileContents);
						
						VideoFileList tmpVideoFileList = new VideoFileList(urlPath, urlFileName, urlfileSize, urlButtonName, fileContents );
						sendingvflList.add(tmpVideoFileList);
						}
					
						oos = new ObjectOutputStream(socket.getOutputStream());	
						//vflList�� �����ͷ� ���� ArrayList�� �߼��Ѵ�.
						oos.writeObject(sendingvflList); 
						
						//sendingvflList
						//�ȿ��ٰ� Ÿ�������� ���� ������ �Ⱦ ArrayList ������ ����.
						/*
						 * this.urlPath = urlPath;
						this.urlFileName = urlFileName;
						this.urlFileSize = urlFileSize;
						this.urlButtonName = urlButtonName;
						this.urlTargetFile = urlTargetFile;
						*/
					
				}

			}

		} catch (Exception e) {
			System.out.println("���Ͽ�������.");
			System.out.println("����ڰ� �α׾ƿ��Ͽ����ϴ�.");
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
	// �Ŵ���
	public static Manager manager = new Manager();
	// ������Ʈ����
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
	// �α��� ���϶�
	private JPanel panboxx = new JPanel(new GridLayout(3, 1));
	private JPanel namePan = new JPanel();
	private JPanel logoutPan = new JPanel();
	// ī�巹�̾ƿ� ��������
	private CardLayout card = new CardLayout();

	private JPanel mainPan = new JPanel(card);
	private JPanel profilePan = new JPanel(card);// �α������� �ٲ� �������г�
	// COMPNENT - homePan
	private Image titleimage = new ImageIcon("Ÿ��Ʋ06.jpg").getImage().getScaledInstance(1194, 100,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon titleicon = new ImageIcon(titleimage);

	private JPanel homePan = new JPanel(new GridLayout(3, 1));
	private JScrollPane homeSc = new JScrollPane(homePan);

	// homePan ���ٰ� ������.
	// urlPan
	private JPanel urlWholePan = new JPanel(new BorderLayout());
	private JPanel urlPan = new JPanel(new GridLayout(5, 6));
	private JPanel urlButtonPan = new JPanel(new GridLayout(5, 1));

	private JLabel yogaLabel = new JLabel("�䰡");
	private JButton yogaButton1 = new JButton("�䰡1");
	private JButton yogaButton2 = new JButton("�䰡2");
	private JButton yogaButton3 = new JButton("�䰡3");
	private JButton yogaButton4 = new JButton("�䰡4");
	private JButton yogaButton5 = new JButton("�䰡5");

	private JLabel stretchingLabel = new JLabel("��Ʈ��Ī");
	private JButton stretchingButton1 = new JButton("��Ʈ��Ī1");
	private JButton stretchingButton2 = new JButton("��Ʈ��Ī2");
	private JButton stretchingButton3 = new JButton("��Ʈ��Ī3");
	private JButton stretchingButton4 = new JButton("��Ʈ��Ī4");
	private JButton stretchingButton5 = new JButton("��Ʈ��Ī5");

	private JLabel mileyCyrusLabel = new JLabel("���ϸ����̷���");
	private JButton mileyCyrusButton1 = new JButton("���ϸ����̷���1");
	private JButton mileyCyrusButton2 = new JButton("���ϸ����̷���2");
	private JButton mileyCyrusButton3 = new JButton("���ϸ����̷���3");
	private JButton mileyCyrusButton4 = new JButton("���ϸ����̷���4");
	private JButton mileyCyrusButton5 = new JButton("���ϸ����̷���5");

	private JLabel homeDietLabel = new JLabel("Ȩ���̾�Ʈ");
	private JButton homeDietButton1 = new JButton("Ȩ���̾�Ʈ1");
	private JButton homeDietButton2 = new JButton("Ȩ���̾�Ʈ2");
	private JButton homeDietButton3 = new JButton("Ȩ���̾�Ʈ3");
	private JButton homeDietButton4 = new JButton("Ȩ���̾�Ʈ4");
	private JButton homeDietButton5 = new JButton("Ȩ���̾�Ʈ5");

	private JLabel smihottLabel = new JLabel("����ȨƮ");
	private JButton smihottButton1 = new JButton("����ȨƮ1");
	private JButton smihottButton2 = new JButton("����ȨƮ2");
	private JButton smihottButton3 = new JButton("����ȨƮ3");
	private JButton smihottButton4 = new JButton("����ȨƮ4");
	private JButton smihottButton5 = new JButton("����ȨƮ5");

	
	
	
	private JButton urlSendButton = new JButton("URL����");
	private JButton urlRenewalButton = new JButton("URL����");
	
	public static ArrayList<VideoFileList> receivedvflList;
	private String[] buttonName = new String[25];
	private int[] fileSize = new int[25];
	private String[] targetUrlImageFileName = new String[25];
	private File targetFile[] = new File[25];
	private	String targetFilePath[] = new String[25];
	private String urlImageFileName[] = new String[25];
	private String urlPath[] = new String[25];
	private VideoFileList [] vflList = new VideoFileList[25];
	private String ServerdirectoryPath;
	// homePan���ٰ� ������.
	// articlePan
	private JPanel articleWholePan = new JPanel(new BorderLayout());
	private JPanel articlePan = new JPanel(new BorderLayout());
	private JPanel articleButtonPan = new JPanel(new GridLayout(5, 1));

	private String[] columnArticle = new String[] { "�۹�ȣ", "ID(�ۼ���)", "������(title)", "�ۼ��ð�(REGDATE)" };
	private String[][] data;

	private Vector<String> userColumn = new Vector<String>();
	private DefaultTableModel model;
	private JTable userTable;
	private JScrollPane listJS;

	private JButton articleRenewalButton = new JButton("��ϰ���");
	private JButton articleDeleteButton = new JButton("��ϻ���");

	public void columnAdd() {
		model = new DefaultTableModel(data, columnArticle);
		// model = new DefaultTableModel(userColumn, 0);
		userTable = new JTable(model);
		listJS = new JScrollPane(userTable);

		userTable.getColumn("�۹�ȣ").setPreferredWidth(30);
		userTable.getColumn("ID(�ۼ���)").setPreferredWidth(280);
		userTable.getColumn("������(title)").setPreferredWidth(100);
		userTable.getColumn("�ۼ��ð�(REGDATE)").setPreferredWidth(250);
		// userTable.getColumn("FileName").setPreferredWidth(50);
		// userTable.getColumn("FileSize").setPreferredWidth(100);

	}

	public void compInit() {

		setLayout(null);
		// ����

		// ---------Ȩ
		homePan.setBackground(Color.white);
		// homePan - articlePan
		// �۹�ȣ,�ۼ���(id),������(title),�ۼ��ð�(regdate)
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
		// �䰡,��Ʈ��Ī,���ϸ����̷���,Ȩ���̾�Ʈ,����ȨƮ
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

		this.urlButtonPan.add(urlSendButton);
		this.urlButtonPan.add(urlRenewalButton);
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
		// CardLayout�� ����ִ� profilePan�� ����.

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
		// CardLayout����ִ� mainPan�� �гε� ����
		this.mainPan.add(homeSc, "NamedefaultPan");
		// �̸��� �ο��� .
		// �ο��� �̸��� ������ �̺�Ʈ ó���κп���
		// ī���� �̸����� �ĺ��Ͽ� visible��.
		this.setResizable(false);
		this.mainPan.setBounds(200, 99, 1000, 650);
		add(mainPan);
	}

	public void eventInit() {

		File home = new File(ServerdirectoryPath);

		// yogaButton1������ �̺�Ʈó���Դϴ�.
		this.yogaButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[0] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[0] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[0] + "\n" + "URL:" + urlPath[0],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[0] = "yogaButton1";
					targetUrlImageFileName[0] = buttonName[0] + "_" + urlImageFileName[0] + "_" + urlPath[0];
					targetFile[0] = new File(home.getPath() + "/" + urlImageFileName[0]);
					targetFilePath[0] = new String (home.getPath() + "/" + urlImageFileName[0]);
					fileSize[0] = (int) targetFile[0].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[0] = new VideoFileList(urlPath[0], targetUrlImageFileName[0], fileSize[0],
							buttonName[0] , targetFilePath[0]);
					System.out.println("�䰡1�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[0]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[0]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[0]);
						}
						
						System.out.println("�䰡1�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("�䰡1�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.yogaButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[1] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[1] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[1] + "\n" + "URL:" + urlPath[1],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[1] = "yogaButton2";
					targetUrlImageFileName[1] = buttonName[1] + "_" + urlImageFileName[1] + "_" + urlPath[1];
					targetFile[1] = new File(home.getPath() + "/" + urlImageFileName[1]);
					targetFilePath[1] = new String (home.getPath() + "/" + urlImageFileName[1]);
					fileSize[1] = (int) targetFile[1].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[1] = new VideoFileList(urlPath[1], targetUrlImageFileName[1], fileSize[1],
							buttonName[1] , targetFilePath[1]);
					System.out.println("�䰡2�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[1]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[1]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[1]);
						}
						
						System.out.println("�䰡2�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("�䰡2�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.yogaButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[2] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[2] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[2] + "\n" + "URL:" + urlPath[2],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[2] = "yogaButton3";
					targetUrlImageFileName[2] = buttonName[2] + "_" + urlImageFileName[2] + "_" + urlPath[2];
					targetFile[2] = new File(home.getPath() + "/" + urlImageFileName[2]);
					targetFilePath[2] = new String (home.getPath() + "/" + urlImageFileName[2]);
					fileSize[2] = (int) targetFile[2].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[2] = new VideoFileList(urlPath[2], targetUrlImageFileName[2], fileSize[2],
							buttonName[2] , targetFilePath[2]);
					System.out.println("�䰡3�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[2]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[2]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[2]);
						}
						
						System.out.println("�䰡3�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("�䰡3�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.yogaButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[3] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[3] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[3] + "\n" + "URL:" + urlPath[3],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[3] = "yogaButton4";
					targetUrlImageFileName[3] = buttonName[3] + "_" + urlImageFileName[3] + "_" + urlPath[3];
					targetFile[3] = new File(home.getPath() + "/" + urlImageFileName[3]);
					targetFilePath[3] = new String (home.getPath() + "/" + urlImageFileName[3]);
					fileSize[3] = (int) targetFile[3].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[3] = new VideoFileList(urlPath[3], targetUrlImageFileName[3], fileSize[3],
							buttonName[3] , targetFilePath[3]);
					System.out.println("�䰡4�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[3]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[3]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[3]);
						}
						
						System.out.println("�䰡4�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("�䰡4�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.yogaButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[4] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[4] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[4] + "\n" + "URL:" + urlPath[4],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[4] = "yogaButton5";
					targetUrlImageFileName[4] = buttonName[4] + "_" + urlImageFileName[4] + "_" + urlPath[4];
					targetFile[4] = new File(home.getPath() + "/" + urlImageFileName[4]);
					targetFilePath[4] = new String (home.getPath() + "/" + urlImageFileName[4]);
					fileSize[4] = (int) targetFile[4].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[4] = new VideoFileList(urlPath[4], targetUrlImageFileName[4], fileSize[4],
							buttonName[4] , targetFilePath[4]);
					System.out.println("�䰡5�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[4]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[4]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[4]);
						}
						
						System.out.println("�䰡5�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("�䰡5�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.stretchingButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[5] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[5] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[5] + "\n" + "URL:" + urlPath[5],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[5] = "stretchingButton1";
					targetUrlImageFileName[5] = buttonName[5] + "_" + urlImageFileName[5] + "_" + urlPath[5];
					targetFile[5] = new File(home.getPath() + "/" + urlImageFileName[5]);
					targetFilePath[5] = new String (home.getPath() + "/" + urlImageFileName[5]);
					fileSize[5] = (int) targetFile[5].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[5] = new VideoFileList(urlPath[5], targetUrlImageFileName[5], fileSize[5],
							buttonName[5] , targetFilePath[5]);
					System.out.println("��Ʈ��Ī1�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[5]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[5]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[5]);
						}
						
						System.out.println("��Ʈ��Ī1�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("��Ʈ��Ī1�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.stretchingButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[6] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[6] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[6] + "\n" + "URL:" + urlPath[6],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[6] = "stretchingButton2";
					targetUrlImageFileName[6] = buttonName[6] + "_" + urlImageFileName[6] + "_" + urlPath[6];
					targetFile[6] = new File(home.getPath() + "/" + urlImageFileName[6]);
					targetFilePath[6] = new String (home.getPath() + "/" + urlImageFileName[6]);
					fileSize[6] = (int) targetFile[6].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[6] = new VideoFileList(urlPath[6], targetUrlImageFileName[6], fileSize[6],
							buttonName[6] , targetFilePath[6]);
					System.out.println("��Ʈ��Ī2�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[6]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[6]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[6]);
						}
						
						System.out.println("��Ʈ��Ī2�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("��Ʈ��Ī2�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.stretchingButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[7] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[7] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[7] + "\n" + "URL:" + urlPath[7],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[7] = "stretchingButton3";
					targetUrlImageFileName[7] = buttonName[7] + "_" + urlImageFileName[7] + "_" + urlPath[7];
					targetFile[7] = new File(home.getPath() + "/" + urlImageFileName[7]);
					targetFilePath[7] = new String (home.getPath() + "/" + urlImageFileName[7]);
					fileSize[7] = (int) targetFile[7].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[7] = new VideoFileList(urlPath[7], targetUrlImageFileName[7], fileSize[7],
							buttonName[7] ,  targetFilePath[7]);
					System.out.println("��Ʈ��Ī3�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[7]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[7]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[7]);
						}
						
						System.out.println("��Ʈ��Ī3�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("��Ʈ��Ī3�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.stretchingButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[8] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[8] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[8] + "\n" + "URL:" + urlPath[8],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[8] = "stretchingButton4";
					targetUrlImageFileName[8] = buttonName[8] + "_" + urlImageFileName[8] + "_" + urlPath[8];
					targetFile[8] = new File(home.getPath() + "/" + urlImageFileName[8]);
					targetFilePath[8] = new String (home.getPath() + "/" + urlImageFileName[8]);
					fileSize[8] = (int) targetFile[8].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[8] = new VideoFileList(urlPath[8], targetUrlImageFileName[8], fileSize[8],
							buttonName[8] ,  targetFilePath[8]);
					System.out.println("��Ʈ��Ī4�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[8]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[8]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[8]);
						}
						
						System.out.println("��Ʈ��Ī4�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("��Ʈ��Ī4�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.stretchingButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[9] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[9] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[9] + "\n" + "URL:" + urlPath[9],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[9] = "stretchingButton5";
					targetUrlImageFileName[9] = buttonName[9] + "_" + urlImageFileName[9] + "_" + urlPath[9];
					targetFile[9] = new File(home.getPath() + "/" + urlImageFileName[9]);
					targetFilePath[9] = new String (home.getPath() + "/" + urlImageFileName[9]);
					fileSize[9] = (int) targetFile[9].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[9] = new VideoFileList(urlPath[9], targetUrlImageFileName[9], fileSize[9],
							buttonName[9] , targetFilePath[9]);
					System.out.println("��Ʈ��Ī5�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[9]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[9]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[9]);
						}
						
						System.out.println("��Ʈ��Ī5�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("��Ʈ��Ī5�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.mileyCyrusButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[10] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[10] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[10] + "\n" + "URL:" + urlPath[10],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[10] = "mileyCyrusButton1";
					targetUrlImageFileName[10] = buttonName[10] + "_" + urlImageFileName[10] + "_" + urlPath[10];
					targetFile[10] = new File(home.getPath() + "/" + urlImageFileName[10]);
					targetFilePath[10] = new String (home.getPath() + "/" + urlImageFileName[10]);
					fileSize[10] = (int) targetFile[10].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[10] = new VideoFileList(urlPath[10], targetUrlImageFileName[10], fileSize[10],
							buttonName[10] , targetFilePath[10]);
					System.out.println("���ϸ����̷���1�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[10]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[10]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[10]);
						}
						
						System.out.println("���ϸ����̷���1�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("���ϸ����̷���1�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.mileyCyrusButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[11] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[11] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[11] + "\n" + "URL:" + urlPath[11],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[11] = "mileyCyrusButton2";
					targetUrlImageFileName[11] = buttonName[11] + "_" + urlImageFileName[11] + "_" + urlPath[11];
					targetFile[11] = new File(home.getPath() + "/" + urlImageFileName[11]);
					targetFilePath[11] = new String (home.getPath() + "/" + urlImageFileName[11]);
					fileSize[11] = (int) targetFile[11].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[11] = new VideoFileList(urlPath[11], targetUrlImageFileName[11], fileSize[11],
							buttonName[11] , targetFilePath[11]);
					System.out.println("���ϸ����̷���2�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[11]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[11]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[11]);
						}
						
						System.out.println("���ϸ����̷���2�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("���ϸ����̷���2�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.mileyCyrusButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[12] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[12] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[12] + "\n" + "URL:" + urlPath[12],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[12] = "mileyCyrusButton3";
					targetUrlImageFileName[12] = buttonName[12] + "_" + urlImageFileName[12] + "_" + urlPath[12];
					targetFile[12] = new File(home.getPath() + "/" + urlImageFileName[12]);
					targetFilePath[12] = new String (home.getPath() + "/" + urlImageFileName[12]);
					fileSize[12] = (int) targetFile[12].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[12] = new VideoFileList(urlPath[12], targetUrlImageFileName[12], fileSize[12],
							buttonName[12] , targetFilePath[12]);
					System.out.println("���ϸ����̷���3�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[12]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[12]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[12]);
						}
						
						System.out.println("���ϸ����̷���3�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("���ϸ����̷���3�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.mileyCyrusButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[13] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[13] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[13] + "\n" + "URL:" + urlPath[13],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[13] = "mileyCyrusButton4";
					targetUrlImageFileName[13] = buttonName[13] + "_" + urlImageFileName[13] + "_" + urlPath[13];
					targetFile[13] = new File(home.getPath() + "/" + urlImageFileName[13]);
					targetFilePath[13] = new String (home.getPath() + "/" + urlImageFileName[13]);
					fileSize[13] = (int) targetFile[13].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[13] = new VideoFileList(urlPath[13], targetUrlImageFileName[13], fileSize[13],
							buttonName[13] , targetFilePath[13]);
					System.out.println("���ϸ����̷���4�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[13]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[13]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[13]);
						}
						
						System.out.println("���ϸ����̷���4�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("���ϸ����̷���4�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.mileyCyrusButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[14] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[14] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[14] + "\n" + "URL:" + urlPath[14],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[14] = "mileyCyrusButton5";
					targetUrlImageFileName[14] = buttonName[14] + "_" + urlImageFileName[14] + "_" + urlPath[14];
					targetFile[14] = new File(home.getPath() + "/" + urlImageFileName[14]);
					targetFilePath[0] = new String (home.getPath() + "/" + urlImageFileName[14]);
					fileSize[14] = (int) targetFile[14].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[14] = new VideoFileList(urlPath[14], targetUrlImageFileName[14], fileSize[14],
							buttonName[14] , targetFilePath[14]);
					System.out.println("���ϸ����̷���5�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[14]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[14]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[14]);
						}
						
						System.out.println("���ϸ����̷���5�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("���ϸ����̷���5�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.homeDietButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[15] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[15] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[15] + "\n" + "URL:" + urlPath[15],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[15] = "homeDietButton1";
					targetUrlImageFileName[15] = buttonName[15] + "_" + urlImageFileName[15] + "_" + urlPath[15];
					targetFile[15] = new File(home.getPath() + "/" + urlImageFileName[15]);
					targetFilePath[0] = new String (home.getPath() + "/" + urlImageFileName[15]);
					fileSize[15] = (int) targetFile[15].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[15] = new VideoFileList(urlPath[15], targetUrlImageFileName[15], fileSize[15],
							buttonName[15] , targetFilePath[15]);
					System.out.println("Ȩ���̾�Ʈ1�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[15]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[15]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[15]);
						}
						
						System.out.println("Ȩ���̾�Ʈ1�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("Ȩ���̾�Ʈ1�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.homeDietButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[16] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[16] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[16] + "\n" + "URL:" + urlPath[16],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[16] = "homeDietButton2";
					targetUrlImageFileName[16] = buttonName[16] + "_" + urlImageFileName[16] + "_" + urlPath[16];
					targetFile[16] = new File(home.getPath() + "/" + urlImageFileName[16]);
					targetFilePath[16] = new String (home.getPath() + "/" + urlImageFileName[16]);
					fileSize[16] = (int) targetFile[16].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[16] = new VideoFileList(urlPath[16], targetUrlImageFileName[16], fileSize[16],
							buttonName[16] , targetFilePath[16]);
					System.out.println("Ȩ���̾�Ʈ2�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[16]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[16]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[16]);
						}
						
						System.out.println("Ȩ���̾�Ʈ2�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("Ȩ���̾�Ʈ2�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.homeDietButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[17] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[17] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[17] + "\n" + "URL:" + urlPath[17],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[17] = "homeDietButton3";
					targetUrlImageFileName[17] = buttonName[17] + "_" + urlImageFileName[17] + "_" + urlPath[17];
					targetFile[17] = new File(home.getPath() + "/" + urlImageFileName[17]);
					targetFilePath[17] = new String (home.getPath() + "/" + urlImageFileName[17]);
					fileSize[17] = (int) targetFile[17].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[17] = new VideoFileList(urlPath[17], targetUrlImageFileName[17], fileSize[17],
							buttonName[17] , targetFilePath[17]);
					System.out.println("Ȩ���̾�Ʈ3�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[17]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[17]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[17]);
						}
						
						System.out.println("Ȩ���̾�Ʈ3�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("Ȩ���̾�Ʈ3�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.homeDietButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[18] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[18] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[18] + "\n" + "URL:" + urlPath[18],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[18] = "homeDietButton4";
					targetUrlImageFileName[18] = buttonName[18] + "_" + urlImageFileName[18] + "_" + urlPath[18];
					targetFile[18] = new File(home.getPath() + "/" + urlImageFileName[18]);
					targetFilePath[18] = new String (home.getPath() + "/" + urlImageFileName[18]);
					fileSize[18] = (int) targetFile[18].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[18] = new VideoFileList(urlPath[18], targetUrlImageFileName[18], fileSize[18],
							buttonName[18], targetFilePath[18]);
					System.out.println("Ȩ���̾�Ʈ4�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[18]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[18]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[18]);
						}
						
						System.out.println("Ȩ���̾�Ʈ4�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("Ȩ���̾�Ʈ4�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.homeDietButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[19] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[19] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[19] + "\n" + "URL:" + urlPath[19],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[19] = "homeDietButton5";
					targetUrlImageFileName[19] = buttonName[19] + "_" + urlImageFileName[19] + "_" + urlPath[19];
					targetFile[19] = new File(home.getPath() + "/" + urlImageFileName[19]);
					targetFilePath[19] = new String (home.getPath() + "/" + urlImageFileName[19]);
					fileSize[19] = (int) targetFile[19].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[19] = new VideoFileList(urlPath[19], targetUrlImageFileName[19], fileSize[19],
							buttonName[19] , targetFilePath[19]);
					System.out.println("Ȩ���̾�Ʈ5�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[19]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[19]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[19]);
						}
						
						System.out.println("Ȩ���̾�Ʈ5�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("Ȩ���̾�Ʈ5�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.smihottButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[20] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[20] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[20] + "\n" + "URL:" + urlPath[20],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[20] = "smihottButton1";
					targetUrlImageFileName[20] = buttonName[20] + "_" + urlImageFileName[20] + "_" + urlPath[20];
					targetFile[20] = new File(home.getPath() + "/" + urlImageFileName[20]);
					targetFilePath[20] = new String (home.getPath() + "/" + urlImageFileName[20]);
					fileSize[20] = (int) targetFile[20].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[20] = new VideoFileList(urlPath[20], targetUrlImageFileName[20], fileSize[20],
							buttonName[20] , targetFilePath[20]);
					System.out.println("����ȨƮ1�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[20]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[20]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[20]);
						}
						
						System.out.println("����ȨƮ1�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("����ȨƮ1�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.smihottButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[21] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[21] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[21] + "\n" + "URL:" + urlPath[21],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[21] = "smihottButton2";
					targetUrlImageFileName[21] = buttonName[21] + "_" + urlImageFileName[21] + "_" + urlPath[21];
					targetFile[21] = new File(home.getPath() + "/" + urlImageFileName[21]);
					targetFilePath[21] = new String (home.getPath() + "/" + urlImageFileName[21]);
					fileSize[21] = (int) targetFile[21].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[21] = new VideoFileList(urlPath[21], targetUrlImageFileName[21], fileSize[21],
							buttonName[21] ,targetFilePath[21]);
					System.out.println("����ȨƮ2�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[21]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[21]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[21]);
						}
						
						System.out.println("����ȨƮ2�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("����ȨƮ2�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.smihottButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[22] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[22] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[22] + "\n" + "URL:" + urlPath[22],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[22] = "smihottButton3";
					targetUrlImageFileName[22] = buttonName[22] + "_" + urlImageFileName[22] + "_" + urlPath[22];
					targetFile[22] = new File(home.getPath() + "/" + urlImageFileName[22]);
					targetFilePath[22] = new String (home.getPath() + "/" + urlImageFileName[22]);
					fileSize[22] = (int) targetFile[22].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[22] = new VideoFileList(urlPath[22], targetUrlImageFileName[22], fileSize[22],
							buttonName[22] , targetFilePath[22]);
					System.out.println("����ȨƮ3�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[22]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[22]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[22]);
						}
						
						System.out.println("����ȨƮ3�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("����ȨƮ3�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});
		this.smihottButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[23] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[23] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[23] + "\n" + "URL:" + urlPath[23],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[23] = "smihottButton4";
					targetUrlImageFileName[23] = buttonName[23] + "_" + urlImageFileName[23] + "_" + urlPath[23];
					targetFile[23] = new File(home.getPath() + "/" + urlImageFileName[23]);
					targetFilePath[23] = new String (home.getPath() + "/" + urlImageFileName[23]);
					fileSize[23] = (int) targetFile[23].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[23] = new VideoFileList(urlPath[23], targetUrlImageFileName[23], fileSize[23],
							buttonName[23] , targetFilePath[23]);
					System.out.println("����ȨƮ4�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[23]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[23]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[23]);
						}
						
						System.out.println("����ȨƮ4�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("����ȨƮ4�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.smihottButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				urlImageFileName[24] = JOptionPane.showInputDialog("�������̸� �Է�");
				urlPath[24] = JOptionPane.showInputDialog("URL�Է�");

				if (JOptionPane.showConfirmDialog(null, "�����̸�:" + urlImageFileName[24] + "\n" + "URL:" + urlPath[24],
						"�˸�", JOptionPane.YES_NO_OPTION) == 0) {
					// DB�� ������ �����̸� ������ ��ư�̸�_�����̸�_url_����ð� �� �Ѵ�.

					buttonName[24] = "smihottButton5";
					targetUrlImageFileName[24] = buttonName[24] + "_" + urlImageFileName[24] + "_" + urlPath[24];
					targetFile[24] = new File(home.getPath() + "/" + urlImageFileName[24]);
					targetFilePath[24] = new String (home.getPath() + "/" + urlImageFileName[24]);
					fileSize[24] = (int) targetFile[24].length();
					
					//vfl �ν��Ͻ�ȭ (��� , �����̸� ,������ , ��ư�̸� )
					vflList[24] = new VideoFileList(urlPath[24], targetUrlImageFileName[24], fileSize[24],
							buttonName[24] , targetFilePath[24]);
					System.out.println("����ȨƮ5�� �����������غ�.");

					try {
						boolean result = Server.manager.isExistUrlData(vflList[24]);
						if (result) { // ����� �����Ѵ� - > ������Ʈ
							Server.manager.updateUrlData(vflList[24]);
						} else {// ����� ���� - > �μ�Ʈ
							Server.manager.insertUrlData(vflList[24]);
						}
						
						System.out.println("����ȨƮ5�� ��ư �������Է� ����.");
					} catch (Exception e1) {
						System.out.println("����ȨƮ5�� ��ư �������Է� ������.");
						e1.printStackTrace();
					} // - try/catch
				} // -if
			}

		});

		this.urlRenewalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 receivedvflList =new ArrayList<VideoFileList>()  ;
				try{
				receivedvflList =Server.manager.getUrlAllDAta(); //DB�ʿ� �����ѵ����͸� �޴´� . �̶��� ���ϰ�δ� ���� �����̾ƴ϶� String���� ����.
//				for(int i =0 ; i <receivedvflList.size() ; i++) {
//					
//				}
				
				System.out.println("�������ϸ���Ʈ �迭 ������ ArrayList���·� ���� receivedvflList : "+receivedvflList.size());
		//		System.out.println(receivedvflList[0]);
				}catch(Exception e1) {
					System.out.println("urlȮ�ι�ư ������ ���� �߻�.");
					e1.printStackTrace();
				}
				new UrlDialog(self).setVisible(true);	
			}

		});
		
		this.urlSendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			try {
			receivedvflList	=Server.manager.getUrlAllDAta();//DB�ʿ� �����ѵ����͸� �޴´� . �̶��� ���ϰ�δ� ���� �����̾ƴ϶� String���� ����.
			
			
			}catch(Exception e1) {
				
				e1.printStackTrace();
				System.out.println("url���۹�ư ������ DB���� ���� receivedList�� ������� �Ѱ��ִ� �������� �����߻�");
		
			}
			
			
			
			
			
			}
			
		});

	}

	// ������
	public Server() {

		// ���α׷��� �������� �����!
		ServerdirectoryPath = "C:/4weeksWorkoutServer";
		// ���� ��ü ����
		File file = new File(ServerdirectoryPath);
		// !ǥ�� �ٿ��־� ������ �������� �ʴ� ����� ������ �ɾ���
		if (!file.exists()) {
			// ���丮 ���� �޼���
			file.mkdirs();
			System.out.println("created directory successfully!");
		}

		this.setSize(1200, 750);
		this.setTitle("����");
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
			new ConnectionThread(server.accept() ,receivedvflList  ).start();
			
		}

	}

}
