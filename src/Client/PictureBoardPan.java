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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Server.FileList;
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

	
//	private String userID;
//	private String userPW;
	private int index;// jlist ������ �ε��� ��ȣ
	private TitledBorder tborder = new TitledBorder("");
	
	private JPanel floor1 = new JPanel();
	private JPanel floor2 = new JPanel();
	
	private JButton upload = new JButton("�ۿø���");
	private JButton remove = new JButton("�ۻ���");

	private ArrayList<FileList> fl;
	private DefaultListModel dlm = new DefaultListModel();
	private JList list;
	private JScrollPane sc;
	private CellRenderer cellrender;
	private int cnt = 0;
	private String[] title;
	private String[] contents;
	private String[] fileName;

	public void compInit() {
		setLayout(new BorderLayout(1, 1));

//		list = new JList(dlm);
//		sc = new JScrollPane(list);
//		list.setCellRenderer(new CellRenderer());
		renew();
		
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

	public void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cnt++;
				
				new AddPictureBoard(self,client, dis, dos).setVisible(true);
				// JDialog ���̱�!
				
				renew();//renew �ȿ� unmarshalling�� �������.
				//repaint();

			}
		});
		
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex(); // ���õ� �׸��� �ε����� �����´�.
				 // �ε����� 0���� ����
		
				 try {
					 dos.writeUTF("����Ʈ ����");
					 dos.writeInt(index);//������ ����Ʈ�� �����̸Ӹ� ��ȣ
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
				 
				 renew();
				 //������ ����
		 		}
		 });

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				index = list.getSelectedIndex();
				
				String img = "C:/4W/PictureBoardPan"+"/"+fl.get(index).getFileName();
				String title = fl.get(index).getTitle();
				String comment = fl.get(index).getContents();
				
				new ListSelected(self, img,title,comment).setVisible(true);
			}
		});

	}

	public void renew() {//���� �޼ҵ�
		list = new JList(dlm);
		sc = new JScrollPane(list);
		list.setCellRenderer(new CellRenderer( fl));
	}


	public PictureBoardPan(Socket client,DataInputStream dis, DataOutputStream dos,
					ArrayList<FileList> fl) {
		
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		this.fl = fl;
		
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