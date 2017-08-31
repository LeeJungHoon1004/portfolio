package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


//������Ʈ ��Ʈ�� = ��ü����ȭ / ��ü����ȭ �������
//<a target="_blank" href="http://nicebury.tistory.com/15" class="tx-link">http://nicebury.tistory.com/15</a>;

public class AddPictureBoard extends JDialog {

	private Socket client;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	//��ǲ��Ʈ��
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	private AddPictureBoard self = this;
	private TitledBorder tborder = new TitledBorder("");
	// ======FILE CHOOSER=======================
	private JFileChooser fc = new JFileChooser();
	private File img;

	private ImageIcon imgIcon;
	private JLabel picture = new JLabel(imgIcon);
	private JButton findPicture = new JButton("����");
	private JTextField picturePath = new JTextField();
	private JTextField comment = new JTextField();
	private JTextField title = new JTextField();

	private JPanel picturePan = new JPanel();
	private JPanel pathPan = new JPanel();
	private JPanel buttonPan = new JPanel();

	private JButton commit = new JButton("�ø���");
	private JButton cancel = new JButton("���ư���");

	public void compInit() {
		setLayout(new BorderLayout(1, 1));
		picture.setPreferredSize(new Dimension(520, 565));
		picturePath.setPreferredSize(new Dimension(455, 30));
		comment.setPreferredSize(new Dimension(520, 100));

		picture.setBorder(tborder);
		picturePan.add(picture, BorderLayout.NORTH);
		pathPan.add(findPicture, BorderLayout.WEST);
		pathPan.add(picturePath, BorderLayout.EAST);
		pathPan.add(comment, BorderLayout.SOUTH);
		buttonPan.add(commit);
		buttonPan.add(cancel);

		add(picturePan, BorderLayout.NORTH);
		add(pathPan, BorderLayout.CENTER);
		add(buttonPan, BorderLayout.SOUTH);
	}

	public void eventInit() {
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		findPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��������ã���� �ؽ�Ʈ�ʵ忡 ��� ���̱�.
				fileChooser();
				img = fc.getSelectedFile();// �̹��� file������ return.
				picturePath.setText(img.getPath() + "/" + img.getName());

				System.out.println(img.getPath() + "/");

				imgIcon = new ImageIcon(img.getPath() + "/" + img.getName());
				picture = new JLabel(imgIcon);
				picturePan.add(picture, BorderLayout.NORTH);
				add(picturePan, BorderLayout.NORTH);
				repaint();

				System.out.println("����ã��Ϸ�");
			}
		});

		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//
				try {
					dos.writeUTF("�߰�");
					marshalling();
					JOptionPane.showMessageDialog(null, "�Խù� �ø��� �Ϸ�");
					dispose();
				} catch (IOException e1) {
					System.out.println("Ŀ�´�Ƽ ���ȸ� ����");
				}
				
			}
		});
	}

	public void fileChooser() {
		fc.setAccessory(new ImagePreview(fc));
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		switch (fc.showOpenDialog(AddPictureBoard.this)) { // �����Ͽ���.
		case JFileChooser.APPROVE_OPTION:// �����ư
			// img = fc.getSelectedFile(); // img�� ������ ���� ����.

			break;

		// case JFileChooser.CANCEL_OPTION:
		// JOptionPane.showMessageDialog(AddPictureBoard.this, "Cancelled", "FCDemo",
		// JOptionPane.OK_OPTION);
		// break;

		case JFileChooser.ERROR_OPTION:
			JOptionPane.showMessageDialog(AddPictureBoard.this, "error", "FCDemo", JOptionPane.OK_OPTION);
		}

		// return img;
	}

	public void marshalling() {
		
		try {
		
		System.out.println("Ŭ���̾�Ʈ ���Ͽ���");
		dos = new DataOutputStream(client.getOutputStream());
		dis = new DataInputStream(client.getInputStream());
		dos.writeUTF("���Ͽ����ĵ����ͼ���");
		System.out.println("���Ͽ����ĵ����ͼ��ſ�û����");
	

		String title = null;
		String contents = null;
		String fileName = null;
		int fileSize = 0;
		byte[] fileContents = null;
		File home = new File("L:/������/Ŭ���̾�Ʈ");
		File[] files = home.listFiles();
	
		for(File tmp:files)	{
			System.out.println(tmp.getAbsolutePath() + " : " + tmp.length());
			System.out.println(tmp.getAbsolutePath() + " : " + tmp.getName());
		}

		fileName="Ŭ���̾�Ʈ1.txt";
		File targetFile = new File(home.getPath() + "/" + fileName);
		// System.out.println(home.getPath()+"/"+fileName);
		title="Ŭ���̾�Ʈ1.txt";
		contents="Ŭ���̾�Ʈ1.txt";
		fileName=targetFile.getName();
		fileSize=(int)targetFile.length();
		fileContents=new byte[fileSize];
		// ������������ ���� ������ ����ش�.
		fis=new FileInputStream(targetFile);fis.read(fileContents);fis.close();
		// System.out.println("1��° ���� :" + fileName + "�� ���� ������ :" +fileSize + " : " +
		// "�� ���� ���빰 :" + fileContents );
		oos=new ObjectOutputStream(client.getOutputStream());
		// 	����a���� , ����a���� , Ÿ������ ������ �̸� , ����ũ�� , ������ ����Ʈ�迭�� ��Ƽ� ���빭��
		FileList fl1 = new FileList(title, contents, fileName, fileSize, fileContents);
		oos.writeObject(fl1);fileName="d1.JPG";targetFile=new File(home.getPath()+"/"+fileName);
		//====================================================����1�� ������
//		
//		fileName="d1.JPG";
//		targetFile = new File(home.getPath() + "/" + fileName);
//		title="d1.JPG";
//		contents="d1.JPG����";
//		fileName=targetFile.getName();
//		fileSize=(int)targetFile.length();
//		fileContents=new byte[fileSize];
//		// ������������ ���� ������ ����ش�.
//		fis=new FileInputStream(targetFile);fis.read(fileContents);fis.close();
//		FileList fl2 = new FileList(title, contents, fileName, fileSize, fileContents);
//		oos.writeObject(fl2);
//		//====================================================����2�� ������
		
		}catch(Exception e1) {
			System.out.println("���ȸ� ����");
		}
	}

	public AddPictureBoard(PictureBoardPan parent) {
		this.setBackground(Color.white);
		setSize(600, 800);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		compInit();
		eventInit();

		// pack();
		// �� pack���ϸ� ������ ������� ������ �ʰ� �޶�����..?
		setModal(true);
	}

	public AddPictureBoard(Socket client, DataInputStream dis, DataOutputStream dos) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
	}
}