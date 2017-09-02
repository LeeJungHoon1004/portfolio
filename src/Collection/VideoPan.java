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
				System.out.println("초기연결 실패");
			}
		
		
		try {
			dos.writeUTF("url데이터발신");
			System.out.println(client.isClosed());
			System.out.println(client.isConnected());
			
			ois = new ObjectInputStream(client.getInputStream());

			ArrayList<VideoFileList> vflList = new ArrayList<VideoFileList>();
			vflList = (ArrayList<VideoFileList>) ois.readObject();
			System.out.println("ois로 리스트 전달받기 성공");
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

				System.out.println("url 데이터 : "+urls);
				System.out.println("fileNames 데이터 : "+fileNames);
				System.out.println("urlButtons 데이터 : "+urlButtons);
				System.out.println("fileSize 데이터 : "+fileSize);
				System.out.println("filecontents 데이터 : "+filecontents);
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
