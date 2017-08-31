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

import Server.FileList;


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
			//����
			private Socket socket = null;
			//�ƿ� ��Ʈ��
			private FileOutputStream fos = null;
			private BufferedOutputStream bos = null;
			private ObjectOutputStream oos = null;
			private DataOutputStream dos = null;
			//��ǲ��Ʈ��
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
			System.out.println("����:�����ʱ�ȭ �����ߴ�.");
		}
	}

	public void run() {

		try {
			System.out.println(socket.getInetAddress()+ "���������߽��ϴ�");

			while(true) {
				String cmd = dis.readUTF();

				if(cmd.equals("ȸ������")) {
					/* ȸ������ name ,id , pw ,gender
					 *  
					 * 
					 */
					String name = dis.readUTF();
					String gender = dis.readUTF();
					String id = dis.readUTF();
					String pw = dis.readUTF();


					Member m = new Member(name , id , pw , gender );
					//Ŭ���̾�Ʈ�� ���� ���̵��  DB�� ����Ǿ��ִ� �������� �˻��մϴ�.
					//�����ڿ��� ���� �����߿��� id�� �˻��մϴ�
					System.out.println(name);
					boolean result  = Server.manager.isExist(m); 
					System.out.println(result);
					//���� �����Ѵ�.(�����ϴ¾��̵�) //���� ���� ȸ������ ������.
					if(result) {
						System.out.println("test1");
						dos.writeUTF("ȸ�����Խ���");
					}else { 
						System.out.println("test2");
						dos.writeUTF("ȸ�����Լ���");
						//�����ڿ��� ���� �����߿��� name, id , pw , gender�� �����մϴ�.
						int result2=	Server.manager.insertData(m);

						if(result2 > 0 ){
							System.out.println("ȸ�������Է¿� �����߽��ϴ�");
						}else{
							System.out.println("ȸ�������Է¿� �����߽��ϴ�.");
						}
					}
					dos.flush();
					//	dos.close();

				}

				else if(cmd.equals("�α���")) {

					id = dis.readUTF();
					pw = dis.readUTF();
					//�α����Ŀ� �α���â���� �̸��� ���
					//�α����Ŀ� BMI������ ������ִ� ��ü���������͸� �ҷ�����
					//���� ,����, ü�� �� �����ɴϴ�.


					Member m2 = new Member(id , pw);
					boolean result = Server.manager.isLoginOk(m2);
					System.out.println("ȸ������� �������� :  " +result);
					if (result) {// ���������Ѵ�(ȸ������ �Ǿ��ִ� ���̵�ͺ���̴�) - �α����㰡
						dos.writeUTF("�α��μ���");

						//1.�α��μ����� ����� ȸ���� �̸� ����.
						String name = null;
						Member m1 = new Member(name , id ,pw);
						//����� ���� (id,pw�� �ص� �ش� ������� �̸��� ������)
						name =Server.manager.getNameData(m2);
						System.out.println("�α����� ȸ���� : " +name);
						//�̸� , ����, ü�� ,���� ������� �����ϴ�
						dos.writeUTF(name);
						//2.�α��μ����� ����� ȸ���� combolist����.
						//2.1 DB�� �ش� ȸ���� ComboListData�ִ��� ����
						//2.2 DB�� �ش� ȸ���� ComboListData������ ���� 
						//2.2.1���Ŀ�ǵ庸���ų� 2.2.2����Ʈ �����ų�
						String sentComboListData = Server.manager.getComboListData(m2);
						System.out.println("DB�� ����Ǿ��ִ� ComboListData :" +sentComboListData);
						if(sentComboListData !=null) {
						dos.writeUTF(sentComboListData);
						}
						else{
							dos.writeUTF("����ִ�ComboList������");
						}
						//3.�α��μ����� ��ϵ� ȸ���� watercuplist����.
						
						

					} else {
						dos.writeUTF("�α��ν���");
					}
					dos.flush();
					// dos.close();

					//client �� �α������ĺ��� �޺�����Ʈ �����Ѱ� ������


					//	String watercuplist = Server.manager.getComboListData(id, pw);
					//	System.out.println(watercuplist);
					//	dos.writeUTF("�޺�����Ʈ3��");
					//	dos.writeUTF(combolist);


				}
				//dailyPan combolist ������ ����
				else if(cmd.equals("�Ϸ��ǥ����")) {

					String combolist = dis.readUTF();
					System.out.println(combolist);

					//String list, String id
					Member m = new Member( id, pw);
					int result =Server.manager.InsertDailyList(id,combolist);
					if(result !=0) {
						System.out.println("������ִ�.");
					}
					else {
						System.out.println("���������");
					}

					System.out.println("�Ϸ��ǥ���۹޾Ҵ�.");
					
				}

				//����üũ�� ���� Ŀ�ǵ� ����.
				else if(cmd.equals("����üũ")) {
					String ChangeCupListAA = dis.readUTF();
					System.out.println("����üũ����Ʈ�� Ŭ���̾�Ʈ���� ���� :" +ChangeCupListAA);

					int msg =Server.manager.InsertWaterCuplist(id,ChangeCupListAA);
					System.out.println("����üũ����Ʈ�� �������� DB�� ������ : (�����ϸ�1 ������ 0) " +msg);
					
					
				}
				
				//Ŀ�´�Ƽ�ҿ��� ���� �̹����� String ������ ����
				else if(cmd.equals("���Ͽ����ĵ����ͼ���")) {
				
					// �����Ϸ��� ������ �̸� , ũ�� , ���빰(������ü) , ������ Ÿ��Ʋ ,������ ����
					String title = null;
					String contents = null;
					String fileName = null;
					int fileSize = 0;
					byte[] fileContents = null;
					
					//2.Ŭ���̾�Ʈ���� �����͸� �޽��ϴ� (2.ClientRam to ServerRam)
					ois = new ObjectInputStream(socket.getInputStream());
					FileList fl1 = (FileList) ois.readObject();
					FileList fl2 = (FileList) ois.readObject();
//					FileList fl3 = (FileList) ois.readObject();
					
					
					
					
					System.out.println(fl1.getTitle()); //����
					System.out.println(fl1.getContents());//�ڸ�Ʈ
					System.out.println(fl1.getFileName());//�����̸�
					System.out.println(fl1.getFileSize());//������ ũ��(int)
					System.out.println(fl1.getFileContents());//������ ���빰(byte [])
					
					
					System.out.println(fl2.getTitle());
					System.out.println(fl2.getContents());
					System.out.println(fl2.getFileName());
					System.out.println(fl2.getFileSize());
					System.out.println(fl2.getFileContents());
					
					
//					System.out.println(fl3.getTitle());
//					System.out.println(fl3.getContents());
//					System.out.println(fl3.getFileName());
//					System.out.println(fl3.getFileSize());
//					System.out.println(fl3.getFileContents());
					
					//3.Ŭ���̾�Ʈ���� ���� �����͸� ��ο� �����մϴ� (3.Ram to Hdd)
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
					
//					fileContents = fl3.getFileContents();
//					File f3 = new File("E:/���α׷���/Java���/�ڹٱ�������α׷��Ӿ缺_7��/Server/" + fl3.getFileName());
//					fos = new FileOutputStream(f3);
//					dos = new DataOutputStream(fos);
//					dos.write(fileContents);
//					dos.flush();
					
					System.out.println("Ŭ���̾�Ʈ���� ���� �����͸� �ϵ��ũ�� ����Ϸ�.");
					
					dos.close();
					
					
					
				}
				else if(cmd.equals("Ŭ���̾�Ʈ������ �۽�")) {
					
					
				}

			}

		}
		catch(Exception e) {
			System.out.println("���Ͽ�������.");
			System.out.println("����ڰ� �α׾ƿ��Ͽ����ϴ�.");
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
	
	
	
	
	
	
	// �Ŵ���
	public static Manager manager = new Manager();
	// ������Ʈ����
		private Server self = this;

	//Component Variable
		private Container cp = this.getContentPane();
		private JLabel title = new JLabel();


		private Font font = new Font("����", Font.ITALIC, 30);
		private JPanel category = new JPanel(new GridLayout(5, 1));
		private JPanel titlePan = new JPanel();
		private JPanel sidepan = new JPanel(new GridLayout(5, 1));
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
		private JButton logout = new JButton("�α׾ƿ�");
		
		// ī�巹�̾ƿ� ��������
		private CardLayout card = new CardLayout();
		
		private JPanel mainPan = new JPanel(card);
		private JPanel profilePan = new JPanel(card);// �α������� �ٲ� �������г�
		// COMPNENT - homePan
		private Image titleimage = new ImageIcon("Ÿ��Ʋ06.jpg").getImage().getScaledInstance(1194, 100,
				java.awt.Image.SCALE_SMOOTH);
		private ImageIcon titleicon = new ImageIcon(titleimage);

		private JPanel homePan = new JPanel(new GridLayout(1, 1));
		private JScrollPane homeSc = new JScrollPane(homePan);
		private String name = getName();
//		private ImageSlidePan imgSlide = new ImageSlidePan();
	


	public void compInit() {
		
		setLayout(null);
		// ����
		
		// ---------Ȩ
		homePan.setBackground(Color.white);



		// compInit() - panelCard_StimulsPhoto
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
		add(titlePan);
		sidepan.setBounds(0, 101, 200, 640);
		add(sidepan);
		// CardLayout����ִ� mainPan�� �гε� ����

		mainPan.add(homeSc, "NamedefaultPan");
		// �̸��� �ο��� .
		// �ο��� �̸��� ������ �̺�Ʈ ó���κп���
		// ī���� �̸����� �ĺ��Ͽ� visible��.
		this.setResizable(false);
		this.mainPan.setBounds(200, 99, 1000, 650);
		add(mainPan);
		}

	public void eventInit() {
		
		

		

	}

	// ������
	public Server() {
		this.setSize(1200, 750);
		this.setTitle("����");
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
