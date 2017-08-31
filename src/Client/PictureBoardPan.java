package Client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//��Ʈ���� �޸� �߰� ����
//http://yonoo88.tistory.com/230

public class PictureBoardPan extends JPanel {

	private PictureBoardPan self = this;
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

	
	private int index;// jlist ������ �ε��� ��ȣ
	private TitledBorder tborder = new TitledBorder("");
	
	private JPanel floor1 = new JPanel();
	private JPanel floor2 = new JPanel();
	
	private JButton upload = new JButton("�ۿø���");
	private JButton remove = new JButton("�ۻ���");

	private DefaultListModel dlm = new DefaultListModel();
	private JList list;
	private JScrollPane sc;
	private CellRenderer cellrender;
	private int cnt = 0;

	public void compInit() {
		setLayout(new BorderLayout(1, 1));

		list = new JList(dlm);
		sc = new JScrollPane(list);
		list.setCellRenderer(new CellRenderer());

		sc.setPreferredSize(new Dimension(970, 500));
		floor2.setPreferredSize(new Dimension(970,90));
		sc.setBorder(tborder);
		
		floor1.add(sc);
		floor2.add(upload);
		floor2.add(remove);

		floor1.setBackground(Color.white);
		floor2.setBackground(Color.white);
		
		add(floor1, BorderLayout.CENTER);
		add(floor2, BorderLayout.SOUTH);

	}

	public void addList() {
		
		for(int i = 0;i<cnt;i++) {
			new CellRenderer();
		}
//https://m.blog.naver.com/PostView.nhn?blogId=heoguni&logNo=130170350764&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F

	}

	public void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cnt++;

				new AddPictureBoard(self).setVisible(true);// JDialog ���̱�!
				new AddPictureBoard(client, dis, dos);// ������� ����(?)
				
				unmarshalling();
				repaint();

			}
		});
		
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex(); // ���õ� �׸��� �ε����� �����´�.
				 // �ε����� 0���� ����
		
				 try {
					 dos.writeUTF("����Ʈ ����");
					 dos.writeInt(index);
				 } catch (Exception e1) {
					 System.out.println("���� ����");
				 } // ������ �Խñ� ���شٰ� ����.
		
				 list.remove(index); // ����Ʈ�𵨿��� ���õ� �׸��� �����.
				 if (dlm.getSize() == 0) { // ����Ʈ���� ����� 0�̵Ǹ� ������ư�� ���� �� ���� �Ѵ�.
					 // btnDel.setEnabled(false);
				 }
				 if (index == dlm.getSize()) { // �ε����� ����Ʈ���� �������׸��� ������
					 index--; // ��,���õ� �ε����� ����Ʈ�� ������ �׸��̾�����
				 } // �ε����� -1�ؼ� �ε����� �Ű��ش�.
				 list.setSelectedIndex(index); //
				 list.ensureIndexIsVisible(index);
		
				 JOptionPane.showMessageDialog(null, "�Խñ��� �����Ǿ����ϴ�");
		 		}
		 });

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				index = list.getSelectedIndex();
				FileList[] fl = new FileList[index];
				String img = ""+"/"+fl[index].getFileName();
				String title = fl[index].getTitle();
				String comment = fl[index].getContents();
				
				new ListSelected(img,title,comment);
				new ListSelected(self).setVisible(true);
			}
		});

	}

	public void unmarshalling() {
		try {
		// �����Ϸ��� ������ �̸� , ũ�� , ���빰(������ü) , ������ Ÿ��Ʋ ,������ ����
		String title = null;
		String contents = null;
		String fileName = null;
		int fileSize = 0;
		byte[] fileContents = null;

		// 2.Ŭ���̾�Ʈ���� �����͸� �޽��ϴ� (2.ClientRam to ServerRam)
		ois = new ObjectInputStream(client.getInputStream());
		FileList fl1 = (FileList) ois.readObject();
		//FileList fl2 = (FileList) ois.readObject();
		// FileList fl3 = (FileList) ois.readObject();

		System.out.println(fl1.getTitle()); // ����
		System.out.println(fl1.getContents());// �ڸ�Ʈ
		System.out.println(fl1.getFileName());// �����̸�
		System.out.println(fl1.getFileSize());// ������ ũ��(int)
		System.out.println(fl1.getFileContents());// ������ ���빰(byte [])

		fileContents = fl1.getFileContents();
		File f = new File("L:/������/����/" + fl1.getFileName());
		fos = new FileOutputStream(f);
		bos = new BufferedOutputStream(fos);
		dos = new DataOutputStream(bos);
		dos.write(fileContents);
		dos.flush();
		//===========================����1�� �𸶼ȸ�
//		fileContents = fl2.getFileContents();
//		File f2 = new File("L:/������/����/" + fl2.getFileName());
//		fos = new FileOutputStream(f2);
//		dos = new DataOutputStream(fos);
//		dos.write(fileContents);
//		dos.flush();
		//===========================����2�� �𸶼ȸ�
		System.out.println("Ŭ���̾�Ʈ���� ���� �����͸� �ϵ��ũ�� ����Ϸ�.");

		dos.close();
		}catch(Exception e ) {
			System.out.println("Ŀ�´�Ƽ ������ �ޱ� ����");
		}
	}


	public PictureBoardPan(Socket client,DataInputStream dis,DataOutputStream dos) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		
	}

	public PictureBoardPan() {
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
		
		this.setBackground(Color.white);
		setSize(700, 800);
		compInit();
		eventInit();
	}

}