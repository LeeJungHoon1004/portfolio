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
	private JButton upload = new JButton("글올리기");
	private JButton remove = new JButton("글삭제");

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

	public void getImgData() {// 썸네일 받아오기
		try {
			dos.writeUTF("사진게시판 썸네일");
			
			while (true) {
				int fileSize = dis.readInt();// 스트림에서 받아온파일을 인트형으로 읽어서 인트형 파일사이즈에 저장.
				byte[] fileContents = new byte[fileSize];// 바이트형 배열에 파일사이즈 저장(?)

				dis.readFully(fileContents);// 내용을 끝까지 읽어내게함(readFully()메소드)
				// 여기까지가 서버에서 받아온 파일을 임시변수에 저장하여 가져옴.

				File f = new File("D:/");// 파일형 f 에 저장 위치와 저장할 이름정함.
				FileOutputStream fos = new FileOutputStream(f);// f를 파일아웃풋스트림형 fos인스턴스에 저장.
				DataOutputStream dos = new DataOutputStream(fos);// fos를 데이터아웃풋스트림형 dos인스턴스테 저장.

				dos.write(fileContents);// 파일컨텐츠를 읽음.
				dos.flush();// 버퍼 날리기.
				// 여기까지가 임시변수에 저장되어있던 서버에서 받아온 파일을 로컬하드디스크로 쓰는것=저장.
			}
		} catch (Exception e) {
			System.out.println("영상썸네일 데이터 받기 실패");
		}
	}

	public void getUrlData() {
		try {
			dos.writeUTF("사진게시판 url");
			
			for(int i = 0;i<url.length;i++) {
				url[i] = dis.readUTF();
			}
		} catch (Exception e1) {
			System.out.println("영상url 데이터 받기 실패");
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
