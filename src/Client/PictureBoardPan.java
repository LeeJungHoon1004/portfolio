package Client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//��Ʈ���� �޸� �߰� ����
//http://yonoo88.tistory.com/230

public class PictureBoardPan extends JFrame {

	private PictureBoardPan self = this;
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;

	private File f;
	private File[] imgFile;
	private String[] comment;
	// private String img;
	private String[] imgPath;
	private String[] writer;
	private String[] title;
	private int filenum;

	private TitledBorder tborder = new TitledBorder("");
	private CardLayout card = new CardLayout();
	private JPanel mainboardPan = new JPanel();

	private JPanel board1 = new JPanel();
	private JPanel board2 = new JPanel();
	private JPanel board3 = new JPanel();
	private JPanel board4 = new JPanel();
	private JPanel board5 = new JPanel();

	private JPanel floor3 = new JPanel();
	private JPanel floor4 = new JPanel();

	private JLabel lb = new JLabel("1,2,3,4");
	private JButton upload = new JButton("�ۿø���");
	private JButton remove = new JButton("�ۻ���");

	private DefaultListModel listmodel = new DefaultListModel();
	private JList list;
	private JScrollPane sc;
	private CellRenderer cellrender;
	private SetRenderer[] renderer;
	private int cnt;
	private Object user;

	public void compInit() {
		setLayout(new BorderLayout(1, 1));

		board1.setPreferredSize(new Dimension(600, 640));
		board2.setPreferredSize(new Dimension(600, 640));
		board3.setPreferredSize(new Dimension(600, 640));
		board4.setPreferredSize(new Dimension(600, 640));
		board5.setPreferredSize(new Dimension(600, 640));

		floor3.setPreferredSize(new Dimension(600, 50));
		floor4.setPreferredSize(new Dimension(600, 50));

		board1.setBorder(tborder);
		board2.setBorder(tborder);
		board3.setBorder(tborder);
		board4.setBorder(tborder);
		board5.setBorder(tborder);

		floor3.add(upload);
		floor3.add(remove);
		floor4.add(lb);

		addList();

		list = new JList(renderer);
		sc = new JScrollPane(list);
		list.setCellRenderer(new CellRenderer());

		sc.setPreferredSize(new Dimension(580, 630));

		board1.add(sc);

		mainboardPan.add(board1, "page1");
		mainboardPan.add(board2, "page2");
		mainboardPan.add(board3, "page3");
		mainboardPan.add(board4, "page4");
		mainboardPan.add(board5, "page5");

		add(mainboardPan, BorderLayout.NORTH);
		add(floor3, BorderLayout.CENTER);
		add(floor4, BorderLayout.SOUTH);

	}

	public SetRenderer[] addList() {
		imgPath = getImgPathData();
		for (int i = 0; i < getFileNum(); i++) {
			renderer = new SetRenderer[i];
			renderer[i] = new SetRenderer(new ImageIcon(imgPath[i]), getTitleData());
		}

		// renderer = new SetRenderer[10];
		// renderer[0] = new SetRenderer(new ImageIcon("img1.jpg"), "���߳�");
		// renderer[1] = new SetRenderer(new ImageIcon("����1.jpg"), "���߳�1");
		// renderer[2] = new SetRenderer(new ImageIcon("img11.jpg"), "���߳�2");
		// renderer[3] = new SetRenderer(new ImageIcon("img13.jpg"), "���߳�3");
		// renderer[4] = new SetRenderer(new ImageIcon("img1.jpg"), "���߳�3");
		// renderer[5] = new SetRenderer(new ImageIcon("img11.jpg"), "���߳�3");
		// renderer[6] = new SetRenderer(new ImageIcon("img13.jpg"), "���߳�3");
		// renderer[7] = new SetRenderer(new ImageIcon("img1.jpg"), "���߳�3");
		// renderer[8] = new SetRenderer(new ImageIcon("img11.jpg"), "���߳�3");
		// renderer[9] = new SetRenderer(new ImageIcon("img13.jpg"), "���߳�3");

		return renderer;
	}

	public void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cnt++;
				new AddPictureBoard(client, dis, dos);// ������� ����(?)
				new AddPictureBoard(self).setVisible(true);// JDialog ���̱�!

				addList();
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
				if (listmodel.getSize() == 0) { // ����Ʈ���� ����� 0�̵Ǹ� ������ư�� ���� �� ���� �Ѵ�.
					// btnDel.setEnabled(false);
				}
				if (index == listmodel.getSize()) { // �ε����� ����Ʈ���� �������׸��� ������
					index--; // ��,���õ� �ε����� ����Ʈ�� ������ �׸��̾�����
				} // �ε����� -1�ؼ� �ε����� �Ű��ش�.
				list.setSelectedIndex(index); //
				list.ensureIndexIsVisible(index);

				JOptionPane.showMessageDialog(null, "�Խñ��� �����Ǿ����ϴ�");
			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				int index = list.getSelectedIndex();
				imgPath = getImgPathData();
				writer = getWriterData();
				title = getTitleData();
				comment = getCommentData();
				
				new ListSelected(imgPath[index],writer[index],title[index],comment[index]);
				new ListSelected(self).setVisible(true);
			}
		});

	}

	public int getFileNum() {
		try {
			filenum = dis.readInt();// ���� ����
		} catch (Exception e2) {
			System.out.println("�����Խ��� ���ϰ��� �ޱ� ����");
		}
		return filenum;
	}// ���ϰ���

	public void getImgData() {

		try {
			f = new File("C://Users//4weeksWorkOut");
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			FileOutputStream fos = new FileOutputStream(f);
			DataOutputStream dos = new DataOutputStream(fos);

			int fileSize = dis.readInt();// ���� ���������.
			byte[] filecontents = new byte[fileSize];// ����Ʈ�� ���ϸ�ŭ�ڸ���������.
			dis.readFully(filecontents);// ����Ʈ ������ �о��.

			dos.write(filecontents);// ������������ ��.
			dos.flush();
		} catch (Exception e1) {
			System.out.println("�����Խ��� ������ �ޱ� ����");
		}
	}// ������ ����.

	public String[] getImgPathData() {
		try {
			for (int i = 0; i < getFileNum(); i++) {
				imgPath[i] = "C://Users//4weeksWorkOut/" + dis.readUTF();// �̹��� �̸�.
			}
		} catch (Exception e2) {
			System.out.println("�̸��ޱ� ����");
		}
		return imgPath;
	}// �̸��ޱ�

	public String[] getWriterData() {
		try {
			for (int i = 0; i < getFileNum(); i++) {
				writer[i] = dis.readUTF();
			}
		} catch (Exception e2) {
			System.out.println("�̸��ޱ� ����");
		}
		return writer;
	}// �ۼ��ڹޱ�

	public String[] getTitleData() {
		try {
			for (int i = 0; i < getFileNum(); i++) {
				title[i] = dis.readUTF();
			}
		} catch (Exception e2) {
			System.out.println("�̸��ޱ� ����");
		}
		return title;
	}// ����ޱ�

	public String[] getCommentData() {
		try {
			for (int i = 0; i < getFileNum(); i++) {
				comment[i] = dis.readUTF();
			}
		} catch (Exception e2) {
			System.out.println("�̸��ޱ� ����");
		}
		return comment;
	}// �ڸ�Ʈ�ޱ�

	public PictureBoardPan(Socket client, DataInputStream dis, DataOutputStream dos) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
	}

	public PictureBoardPan() {
		setSize(700, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		compInit();
		eventInit();

		setVisible(true);
	}

	public static void main(String[] args) {
		if (new BasicShape().getUserID() != null && new BasicShape().getUserPW() != null) {
			new PictureBoardPan();
		} else {
			JOptionPane.showMessageDialog(null, "�α����� �ϰ� �̿��� �ּ���.");
		}
	}
}