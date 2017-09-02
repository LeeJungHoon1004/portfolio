package Collection;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import Server.VideoFileList;

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

	private String urls = null;
	private String fileNames = null;
	private String urlButtons = null;
	int fileSize =0;
	byte[] filecontents = null;
	private String path = "C:/Users/Administrator/4weeksWorkout/";
	private String[] imgpath;

	public void unmarsharlling() {
		
		try {
			 client = new Socket("127.0.0.1",40000);
			 dis = new DataInputStream(client.getInputStream());
			 dos = new DataOutputStream(client.getOutputStream());
			}catch(Exception e1) {
				System.out.println("�ʱ⿬�� ����");
			}
		
		
		try {
			dos.writeUTF("url�����͹߽�");
			System.out.println(client.isClosed());
			System.out.println(client.isConnected());
			
			ois = new ObjectInputStream(client.getInputStream());

			ArrayList<VideoFileList> vflList = new ArrayList<VideoFileList>();
			vflList = (ArrayList<VideoFileList>) ois.readObject();
			System.out.println("ois�� ����Ʈ ���޹ޱ� ����");
			System.out.println(vflList.size());
			for (int i = 0; i < vflList.size(); i++) {
				//names[i] = 
				//fnames[i] = names[i].split("_");
				urls = vflList.get(i).getUrlPath();
				fileNames = vflList.get(i).getUrlFileName();
				urlButtons = vflList.get(i).getUrlButtonName();
				fileSize= vflList.get(i).getUrlFileSize();
				filecontents = vflList.get(i).getFileContents();

				//imgpath[i] = path + fileNames[i];

				System.out.println("url ������ : "+urls);
				System.out.println("fileNames ������ : "+fileNames);
				System.out.println("urlButtons ������ : "+urlButtons);
				System.out.println("fileSize ������ : "+fileSize);
				System.out.println("filecontents ������ : "+filecontents);
				//System.out.println(imgpath[i]);
				System.out.println("====================");
			}
			// �迭�� ������ �ֱ�Ϸ�
//			System.out.println("�迭�� ������ �ֱ� �Ϸ�");

//			File f = new File("");
//			fos = new FileOutputStream(f);
//			bos = new BufferedOutputStream(fos);
//			dos = new DataOutputStream(bos);
//			dos.write(filecontents);
//			dos.flush();

			System.out.println("�����г� �𸶼ȸ� ����");

			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	public VideoPan() {
		
		
		unmarsharlling();
		//insertImage();
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);
	}

	public static void main(String[] args) throws Exception{
		new VideoPan();
	}
}
