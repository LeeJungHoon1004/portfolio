package Client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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


//github.com/LeeJungHoon1004/portfolio.git	


public class BasicShape extends JFrame {
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;
	// =======SOCKET========================
	private Container cp = this.getContentPane();
	private JLabel title = new JLabel();
	private JLabel title2 = new JLabel();
	
	private Font font = new Font("����", Font.ITALIC, 30);
	private JButton homeBt = new JButton("Ȩ");
	private JButton goalBt = new JButton("���� ��ǥ");
	private JButton dailyBt = new JButton("�Ϸ� ��ǥ");
	private JButton videoBt = new JButton("�����");
	private JButton imgBoardBt = new JButton("�����Խ���");
	private JPanel category = new JPanel(new GridLayout(5, 1));
	private JPanel titlePan = new JPanel();
	private JPanel sidepan = new JPanel(new GridLayout(5, 1));
	// ����������������� �ٲ�������������
	// �α׾ƿ� ���϶�
	private JLabel lbID = new JLabel();
	private JLabel lbPW = new JLabel();
	
	private Image idimage = new ImageIcon("ID (3).jpg").getImage()
			.getScaledInstance(98, 30, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon iconid= new ImageIcon(idimage);
	private Image pwimage = new ImageIcon("PW (2).jpg").getImage()
			.getScaledInstance(98, 30, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon iconpw= new ImageIcon(pwimage);
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
	private Image titleimage = new ImageIcon("Ÿ��Ʋ06.jpg").getImage()
			.getScaledInstance(1194, 100, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon titleicon= new ImageIcon(titleimage);

	private TitledBorder tborder = new TitledBorder("");
	private JPanel homePan = new JPanel(new GridLayout(2, 1));
	private JScrollPane homeSc = new JScrollPane(homePan);
	private String name = getName();
	private ImageSlide imgSlide = new ImageSlide();
	private BMI bmi = new BMI();

	// COMPNENT - goalPan
	private JPanel goalPan = new JPanel();
	private JScrollPane goalSc = new JScrollPane(goalPan);//��ũ��
	// COMPNENT - dailyPan
	private Dailypan dailyPan = new Dailypan();
	private JScrollPane dailySc = new JScrollPane(dailyPan);//��ũ��
	// COMPNENT - videoPan
	private JPanel videoPan = new JPanel();
	private VideoPan video = new VideoPan();
	private JScrollPane videoSc = new JScrollPane(videoPan);//��ũ��
	
	// COMPNENT - imgBoardPan
	private JPanel imgPanel = new JPanel();
	private PicPan picpan = new PicPan();
	private JScrollPane picSc = new JScrollPane(imgPanel);//��ũ��
	
	private JButton buttonUpload = new JButton("���ε�");
	private JButton buttonRemove = new JButton("��������"); // ���������� id�� �н����� ���Ȯ�� �ʿ���.
	private JButton buttonClose = new JButton("�ݱ�");
	
	private JPanel imgBoardPan = new JPanel(new GridLayout(3, 1));
	private JPanel panelnull = new JPanel();
	private JPanel panelCenter = new JPanel(new GridLayout(1, 3)); // ����-����3��
	private JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	// =======COMPONENT========================

	
	
	
	public void comp() {
		setLayout(null);
		    //����
		
		imgSlide.setPreferredSize(new Dimension(400, 700));
		bmi.setPreferredSize(new Dimension(872, 800));
		bmi.setBorder(tborder);
		this.homePan.add(imgSlide);
		this.homePan.add(bmi);
		
		//---------�����
		videoPan.setBackground(Color.white);
		this.video.setPreferredSize(new Dimension(965, 1600));
		this.videoPan.add(video);
		//---------����
		picpan.setBackground(Color.white);
		imgPanel.setBackground(Color.white);
		this.picpan.setPreferredSize(new Dimension(975,1600));
		this.imgPanel.add(picpan);
		
		this.lbID.setIcon(iconid);
		this.lbPW.setIcon(iconpw);
		this.panelSouth.add(buttonUpload);
		this.panelSouth.add(buttonRemove);
		this.panelSouth.add(buttonClose);
		this.imgBoardPan.add(panelCenter);
		this.imgBoardPan.add(panelnull);
		this.imgBoardPan.add(panelSouth);
		// compInit() - panelCard_StimulsPhoto
		category.setBackground(Color.WHITE);
		sidepan.setBackground(Color.WHITE);
		panbox1.setBackground(Color.WHITE);
		panbox2.setBackground(Color.WHITE);
		panbox3.setBackground(Color.WHITE);
		
		
		
		category.add(homeBt);
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
		// CardLayout�� ����ִ� profilePan�� ����.
		
		panboxx.setBackground(Color.white);
		namePan.setBackground(Color.white);
		logoutPan.setBackground(Color.white);
		profilename.setText(name + " �� ȯ���մϴ�!");
		namePan.add(profilename);
		logoutPan.add(logout);
		
		panboxx.add(profilename, BorderLayout.CENTER);
		panboxx.add(logoutPan, BorderLayout.CENTER);
		profilePan.add(panbox, "loginBefore");
		profilePan.add(panboxx, "loginAfter");
		
		// =======================================================
//		title.setFont(font);
		title.setIcon(titleicon);
		titlePan.add(title);
		sidepan.add(profilePan);
		sidepan.add(category);
		titlePan.setBounds(0, 0, 1194, 100);
		add(titlePan);
		sidepan.setBounds(0, 101, 200, 640);
		add(sidepan);
		// CardLayout����ִ� mainPan�� �гε� ����

		
		mainPan.add(homeSc, "NamedefaultPane");
		mainPan.add(goalSc);
		mainPan.add(dailySc, "NamedailyPane");
		mainPan.add(videoSc,"NamevideoPane");
		mainPan.add(picSc, "NameimgBoard"); // ī��� �����ִ��ҿ�
		// �̸��� �ο��� .
		// �ο��� �̸��� ������ �̺�Ʈ ó���κп���
		// ī���� �̸����� �ĺ��Ͽ� visible��.
		this.setResizable(false);
		this.mainPan.setBounds(200, 99, 1000, 650);
		add(mainPan);
		
		
	}

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




	public String getResult() {
		// �α��� ��ư
		String userID = inputID.getText();
		String userPW = inputPW.getText();
		try {
			dos.writeUTF("�α���");
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
		}
		return result;
	}// end

	public String getName() {


		try {
			name = dis.readUTF();
		} catch (Exception e1) {
		//	System.out.println("Ȩ-������ �̸������͹ޱ� ����");
		}
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}




	public void eventInit() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientConnect();
				getResult();
				 
				// ������ ���� ����.��������
				if (result.equals("�α��μ���")) {
					
					profilename.setText(getName() + " �� ȯ���մϴ�!");
					card.show(self.profilePan, "loginAfter");
				} else if (result.equals("�α��ν���")) {
					card.show(self.profilePan, "loginBefore");

				}
				// ������â �α��ο��ο� ���� �ٸ�.��������
			}
		});
		// �α׾ƿ� ��ư
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.close();
					System.out.println("���������� ��������");
					inputID.setText("");
					inputPW.setText("");
				} catch (IOException e1) {
					System.out.println("�������� ����");
				}
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
		// ����ǥ ��ư
		goalBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ������â �α��ο��ο� ���� �ٸ�.��������
			}
		});
		// �����Ǹ�ǥ ��ư
		dailyBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ������â �α��ο��ο� ���� �ٸ�.��������
				card.show(self.mainPan, "NamedailyPane");
			}
		});
		// ����Խ��� ��ư
		videoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(self.mainPan, "NamevideoPane");
				
			}
		});
		// �����Խ��� ��ư
		imgBoardBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				card.show(self.mainPan, "NameimgBoard");
			}
		});
		// ���� ������
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

			// ���콺��ư�� ������ ������ �̺�Ʈó��
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				card.show(self.mainPan, "NamedefaultPane");
			}
		});
		// ===============EVENTINIT()=========imgBoardPan
		this.buttonUpload.addActionListener(new ActionListener() {
			// ���� ���ε�� xxȸ������ ����. �� ���ܵ� .
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
			// ���� ������ ȸ���� ���̵�� ��й�ȣ�� �ѹ��Է¹޾Ƽ� Ȯ���� �ش�ȸ���� �����ϰ�쿡 ���� .
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	public BasicShape() {
		setTitle("�⺻shape�׽�Ʈ");
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
// <a target="_blank" href="http://msource.tistory.com/5"
// class="tx-link">http://msource.tistory.com/5</a>;;
// ī�巹�̾ƿ� ���� ����
// <a target="_blank" href="http://www.w3ii.com/ko/swing/swing_cardlayout.html"
// class="tx-link">http://www.w3ii.com/ko/swing/swing_cardlayout.html</a>;;
// ī�巹�̾ƿ� Ŭ����