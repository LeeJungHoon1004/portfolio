package Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Server.FileList;

//������Ʈ ��Ʈ�� = ��ü����ȭ / ��ü����ȭ �������
//<a target="_blank" href="http://nicebury.tistory.com/15" class="tx-link">http://nicebury.tistory.com/15</a>;

public class AddPictureBoard extends JDialog {

	private Socket client;
	private BasicShape parent2;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	// ��ǲ��Ʈ��
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	private AddPictureBoard self = this;
	private TitledBorder tborder = new TitledBorder("");
	// ======FILE CHOOSER=======================
	private JFileChooser fc = new JFileChooser();
	private File file;
	private int returnVal;
	private String id;
	
	
	private JLabel picture = new JLabel();
	private JButton findPicture = new JButton("����");
	private JLabel titleLabel = new JLabel("������:");
	private JTextField picturePath = new JTextField();
	private JTextArea comment = new JTextArea();
	private JTextField titleField = new JTextField();

	private JPanel picturePan = new JPanel();
	private JPanel PahtPan = new JPanel();
	private JPanel titlePan = new JPanel();
	private JPanel dataPan = new JPanel();
	private JPanel buttonPan = new JPanel();

	private JButton commit = new JButton("�ø���");
	private JButton cancel = new JButton("���ư���");

	public void compInit() {
		setLayout(new BorderLayout(0, 0));
		picturePan.setPreferredSize(new Dimension(510, 510));
		picturePath.setPreferredSize(new Dimension(455, 30));
		titleField.setPreferredSize(new Dimension(455, 30));
		comment.setPreferredSize(new Dimension(520, 110));

		picturePan.setBorder(tborder);
		picturePan.add(picture);

		PahtPan.add(findPicture, BorderLayout.WEST);
		PahtPan.add(picturePath, BorderLayout.EAST);

		titlePan.add(titleLabel, BorderLayout.WEST);
		titlePan.add(titleField, BorderLayout.EAST);

		comment.setLineWrap(true);        //Ȱ��ȭ, �ڵ� �� �ٲ� ��� 
		comment.setWrapStyleWord(true);
		
		dataPan.add(PahtPan, BorderLayout.NORTH);
		dataPan.add(titlePan, BorderLayout.CENTER);
		dataPan.add(comment, BorderLayout.SOUTH);
		buttonPan.add(commit);
		buttonPan.add(cancel);

		picturePan.setBackground(Color.white);
		dataPan.setBackground(Color.white);
		buttonPan.setBackground(Color.white);
		PahtPan.setBackground(Color.white);
		titlePan.setBackground(Color.white);

		add(picturePan, BorderLayout.NORTH);
		add(dataPan, BorderLayout.CENTER);
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

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					picture.setIcon(new ImageIcon(file.getPath()));
					picturePath.setText(file.getPath());
					repaint();

				} else if (returnVal == JFileChooser.ERROR_OPTION) {
					JOptionPane.showMessageDialog(AddPictureBoard.this, "error", "FCDemo", JOptionPane.OK_OPTION);
				}

				System.out.println("����ã��Ϸ�");
			}
		});

		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					marshalling();

					JOptionPane.showMessageDialog(null, "Ŀ�´�Ƽ ���ε� �Ϸ�");
					dispose();

			}
		});
	}

	public void fileChooser() {
		fc.setAccessory(new ImagePreview(fc));
		fc.setMultiSelectionEnabled(true);
		// ������ ���� ����.
		fc.setFileFilter(
				new javax.swing.filechooser.FileNameExtensionFilter("JPEG", "jpeg", "JPG", "jpg", "PNG", "png"));
		// ���� ���� ����
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		returnVal = fc.showOpenDialog(AddPictureBoard.this); // �����Ͽ���.
	}

	public void marshalling() {

		try {

			dos.writeUTF("Ŀ�´�Ƽ���Խñ��߰�");
			
			String title = null;
			String contents = null;
			String fileName = null;
			int fileSize = 0;
			byte[] fileContents = null;
	
			File targetFile = new File(picturePath.getText());
			title = titleField.getText();
			contents = comment.getText();
			fileName = targetFile.getName();
			fileSize = (int) targetFile.length();
			fileContents = new byte[fileSize];
			// ������������ ���� ������ ����ش�.
			fis = new FileInputStream(targetFile);
			fis.read(fileContents);
			fis.close();
			oos = new ObjectOutputStream(client.getOutputStream());
			// ����a���� , ����a���� , Ÿ������ ������ �̸� , ����ũ�� , ������ ����Ʈ�迭�� ��Ƽ� ���빭��
			FileList fl1 = new FileList(id, title, contents, fileName, fileSize, fileContents);
			oos.writeObject(fl1);
		//	fileName = "d1.JPG";
		//	targetFile = new File(home.getPath() + "/" + fileName);
			// ====================================================����1�� ������

		} catch (Exception e1) {
			System.out.println("���ȸ� ����");
		}
	}

	public AddPictureBoard(BasicShape parent2 ,PictureBoardPan parent, Socket client, DataInputStream dis, DataOutputStream dos) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		this.id = parent2.getUserID();
		this.setBackground(Color.white);
		setSize(600, 800);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		compInit();
		eventInit();

		// pack();
		// �� pack���ϸ� ������ ������� ������ �ʰ� �޶�����..?
		setModal(true);
	}

}