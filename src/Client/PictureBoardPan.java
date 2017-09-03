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
//스트링에 콤마 추가 제거
//http://yonoo88.tistory.com/230
import night0903.CellRenderer;

public class PictureBoardPan extends JPanel {
	private BasicShape parent ;
	private PictureBoardPan self = this;
	private Socket client;
	// 아웃 스트림
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	// 인풋스트림
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	
//	private String userID;
//	private String userPW;
	private int index;// jlist 선택한 인덱스 번호
	private TitledBorder tborder = new TitledBorder("");
	
	private JPanel floor1 = new JPanel();
	private JPanel floor2 = new JPanel();
	
	private JButton upload = new JButton("글올리기");
	private JButton remove = new JButton("글삭제");

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
				
				new AddPictureBoard(parent,self,client, dis, dos).setVisible(true);
				// JDialog 보이기!
				
				renew();//renew 안에 unmarshalling도 들어있음.
				//repaint();

			}
		});
		
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex(); // 선택된 항목의 인덱스를 가져온다.
				 // 인덱스는 0부터 시작
		
				 try {
					 dos.writeUTF("리스트 삭제");
					 dos.writeInt(index);//삭제할 리스트의 프라이머리 번호
				 } catch (Exception e1) {
					 System.out.println("삭제 실패");
				 } // 서버에 게시글 없앤다고 보냄.
		
				 list.remove(index); // 리스트모델에서 선택된 항목을 지운다.
				 
				 if (dlm.getSize() == 0) { // 리스트모델의 사이즈가 0이되면 삭제버튼을 누를 수 없게 한다.
					 // btnDel.setEnabled(false);
				 }
				 if (index == dlm.getSize()) { // 인덱스와 리스트모델의 마지막항목이 같으면
					 index--; // 즉,선택된 인덱스가 리스트의 마지막 항목이었으면
				 } // 인덱스를 -1해서 인덱스를 옮겨준다.
				 list.setSelectedIndex(index); //
				 list.ensureIndexIsVisible(index);
		
				 JOptionPane.showMessageDialog(null, "게시글이 삭제되었습니다");
				 
				 renew();
				 //삭제후 갱신
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

	public void renew() {//갱신 메소드
		
		list = new JList(dlm);
		dlm = (DefaultListModel)list.getModel();
		//for(int i=0;i<dlm.getSize();i++){
			dlm.addElement(new CellRenderer(fl));
		//}
		sc = new JScrollPane(list);
		//list.setCellRenderer(new CellRenderer(fl));
	}


	public PictureBoardPan(BasicShape parent ,Socket client,DataInputStream dis, DataOutputStream dos,
					ArrayList<FileList> fl) {
		this.parent = parent;
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