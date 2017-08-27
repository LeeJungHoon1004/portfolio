package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

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
//http://nicebury.tistory.com/15
//http://egloos.zum.com/dojeun/v/317825
//

public class AddPictureBoard extends JDialog {

	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;

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
				img = fileChooser();// �̹��� file������ return.
				picturePath.setText(img.getPath());
				
				imgIcon = new ImageIcon(img.getPath());
				picture = new JLabel(imgIcon);
				picturePan.add(picture, BorderLayout.NORTH);
				add(picturePan, BorderLayout.NORTH);
			}
		});


		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData();
				JOptionPane.showMessageDialog(null, "upload complete");
				dispose();
			}
		});
	}

	
	public File fileChooser() {
		fc.setAccessory(new ImagePreview(fc));
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		switch (fc.showOpenDialog(AddPictureBoard.this)) { // �����Ͽ���.
		case JFileChooser.APPROVE_OPTION:// �����ư
			img = fc.getSelectedFile();
			System.out.println(img);// �缱���� �����ּ�

			break;

		case JFileChooser.CANCEL_OPTION:
			JOptionPane.showMessageDialog(AddPictureBoard.this, "Cancelled", "FCDemo", JOptionPane.OK_OPTION);
			break;

		case JFileChooser.ERROR_OPTION:
			JOptionPane.showMessageDialog(AddPictureBoard.this, "error", "FCDemo", JOptionPane.OK_OPTION);
		}

		return img;
	}// ������ img�� ���������� return.


	
	public void sendData() {
		//connection();
		img = fileChooser(); //������ ���� ���.
		long fileSize = img.length();//���ϻ���� long������ ����.

		try{
			FileInputStream fis = new FileInputStream(img);//������ǲ��Ʈ�� fis�� �������ϰ������.
			DataInputStream dis = new DataInputStream(fis);//��������ǲ��Ʈ�� dis�� fis����.
			
			byte[] fileContents = new byte[(int)fileSize];//byte�� �迭�� ���� ������ ����.
			dis.readFully(fileContents);//���� ������ �� �о��.
			
			System.out.println(client.getInetAddress()+"���� ���ϻ�����..");
			
			this.dos.writeInt((int)fileSize);
			this.dos.write(fileContents);
			this.dos.flush();
			
		}catch(Exception e){
			System.out.println("������ ã�� �� �����ϴ�.");
		}
	}
	
	
	
	public AddPictureBoard(PictureBoardPan parent) {
		setSize(600, 800);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		compInit();
		eventInit();

		// pack();
		// �� pack���ϸ� ������ ������� ������ �ʰ� �޶�����..?
		setModal(true);
	}
	
	public AddPictureBoard(Socket client,DataInputStream dis,DataOutputStream dos) {
		this.client = client;
		this.dis= dis;
		this.dos = dos;
	}
}
