package Collection;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class VideoPan extends JFrame {
	
	//Socket client, DataInputStream dis, DataOutputStream dos
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream  dos;

	// ===========================================================
//	private Socket client;
//	private DataInputStream dis;
//	private DataOutputStream dos;
	private ObjectInputStream ois = null;
	private FileOutputStream fos;
	private BufferedOutputStream bos;
	// ===========================================================

	private String[] urls = null;
	private String[] fileNames = null;
	private String[] urlButtons = null;
	int[] fileSize = null;
	byte[] filecontents = null;
	private String path = "C:/Users/Administrator/4weeksWorkout/";
	private String[] imgpath;

	public void unmarsharlling() {
		try {

			
			dos.writeUTF("url데이터발신");

			
			ois = new ObjectInputStream(client.getInputStream());

			ArrayList<VideoFileList> vflList = new ArrayList<VideoFileList>();
			vflList = (ArrayList<VideoFileList>) ois.readObject();

			for (int i = 0; i < vflList.size(); i++) {

				//names[i] = 
				//fnames[i] = names[i].split("_");
				urls[i] = vflList.get(i).getUrl();
				fileNames[i] = vflList.get(i).getFilename();
				urlButtons[i] = vflList.get(i).getUrlButtons();
				fileSize[i] = vflList.get(i).getFileSize();
				filecontents = vflList.get(i).getFilecontents();

				//imgpath[i] = path + fileNames[i];

				System.out.println("url 데이터 : "+urls[i]);
				System.out.println("fileNames 데이터 : "+fileNames[i]);
				System.out.println("urlButtons 데이터 : "+urlButtons[i]);
				System.out.println("fileSize 데이터 : "+fileSize[i]);
				System.out.println("filecontents 데이터 : "+filecontents[i]);
				//System.out.println(imgpath[i]);
				System.out.println("====================");
			}
			// 배열에 데이터 넣기완료
//			System.out.println("배열에 데이터 넣기 완료");

//			File f = new File("");
//			fos = new FileOutputStream(f);
//			bos = new BufferedOutputStream(fos);
//			dos = new DataOutputStream(bos);
//			dos.write(filecontents);
//			dos.flush();

			System.out.println("비디오패널 언마셜링 성공");

			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	public VideoPan() {
		try {
		 client = new Socket("192.168.53.4",40000);
		 dis = new DataInputStream(client.getInputStream());
		 dos = new DataOutputStream(client.getOutputStream());
		}catch(Exception e1) {
			System.out.println("초기연결 실패");
		}
		
		unmarsharlling();
		//insertImage();
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);
	}

	public static void main(String[] args) {
		new VideoPan();
	}
}
