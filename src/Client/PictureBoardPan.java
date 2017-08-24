package Client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PictureBoardPan extends JFrame {

	private PictureBoardPan self = this;

	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;

	private String[] url;
	private String[] preview;
	
	private TitledBorder tborder = new TitledBorder("");
	private CardLayout card = new CardLayout();
	private JPanel mainboardPan = new JPanel(card);
	private JPanel boardPan = new JPanel();
	
	private JPanel board1 = new JPanel(new GridLayout(2,3));
	private JPanel board2 = new JPanel();
	
	private JPanel floor3 = new JPanel();
	private JPanel floor4 = new JPanel();
	
	private ImageIcon img1 = new ImageIcon("img1.jpg");
	
	private JLabel picture1 = new JLabel(img1);
	private JPanel picture2 = new JPanel();
	private JPanel picture3 = new JPanel();

	private JPanel picture11 = new JPanel();
	private JPanel picture22 = new JPanel();
	private JPanel picture33 = new JPanel();

	private JButton pageNum;

	private JLabel lb = new JLabel("1,2,3,4");
	private JButton upload = new JButton("�ۿø���");
	private JButton remove = new JButton("�ۻ���");

	public void compInit() {
		setLayout(new BorderLayout(1,1));

		board1.setPreferredSize(new Dimension(600,700));
		floor3.setPreferredSize(new Dimension(600,50));
		floor4.setPreferredSize(new Dimension(600,50));
		
		board1.setBorder(tborder);
		board1.add(picture1);
		board1.add(picture1);
		board1.add(picture1);
		board1.add(picture1);
		board1.add(picture1);
		board1.add(picture1);

		floor3.add(upload);
		floor3.add(remove);

		floor4.add(lb);

		boardPan.add(board1,BorderLayout.NORTH);
		boardPan.add(floor3,BorderLayout.CENTER);
		boardPan.add(floor4,BorderLayout.SOUTH);
		
		mainboardPan.add(boardPan,"Board1");
		//mainboardPan.add(board,"Board2");
		
		add(mainboardPan);
	}

	private void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPictureBoard(client, dis, dos);
				new AddPictureBoard(self).setVisible(true);
				
				getImgData();
				getUrlData();
			}
		});
	}

	public void getImgData() {// ����� �޾ƿ���
		try {
			dos.writeUTF("�����Խ��� �����");
			
			while (true) {
				int fileSize = dis.readInt();// ��Ʈ������ �޾ƿ������� ��Ʈ������ �о ��Ʈ�� ���ϻ���� ����.
				byte[] fileContents = new byte[fileSize];// ����Ʈ�� �迭�� ���ϻ����� ����(?)

				dis.readFully(fileContents);// ������ ������ �о����(readFully()�޼ҵ�)
				// ��������� �������� �޾ƿ� ������ �ӽú����� �����Ͽ� ������.

				File f = new File("D:/");// ������ f �� ���� ��ġ�� ������ �̸�����.
				FileOutputStream fos = new FileOutputStream(f);// f�� ���Ͼƿ�ǲ��Ʈ���� fos�ν��Ͻ��� ����.
				DataOutputStream dos = new DataOutputStream(fos);// fos�� �����;ƿ�ǲ��Ʈ���� dos�ν��Ͻ��� ����.

				dos.write(fileContents);// ������������ ����.
				dos.flush();// ���� ������.
				// ��������� �ӽú����� ����Ǿ��ִ� �������� �޾ƿ� ������ �����ϵ��ũ�� ���°�=����.
			}
		} catch (Exception e) {
			System.out.println("�������� ������ �ޱ� ����");
		}
	}

	public void getUrlData() {
		try {
			dos.writeUTF("�����Խ��� url");
			
			for(int i = 0;i<url.length;i++) {
				url[i] = dis.readUTF();
			}
		} catch (Exception e1) {
			System.out.println("����url ������ �ޱ� ����");
		}
	}

//	public PictureBoardPan(Socket client, DataInputStream dis, DataOutputStream dos) {
//		this.client = client;
//		this.dis = dis;
//		this.dos = dos;
//	}

	public PictureBoardPan() {
		setSize(700, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		compInit();
		eventInit();

		setVisible(true);
	}

	public static void main(String[] args) {
		new PictureBoardPan();
	}
}
