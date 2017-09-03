package Client;

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

public class Client {
	
	
	//���Ϻ���
	private Socket socket = null;
	//�ƿ� ��Ʈ��
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	//��ǲ��Ʈ��
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	public void func() {

		try {
			System.out.println("Ŭ���̾�Ʈ ���Ͽ���");
			socket = new Socket("127.0.0.1", 40000);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			dos.writeUTF("���Ͽ����ĵ����ͼ���");
			System.out.println("���Ͽ����ĵ����ͼ��ſ�û����");
			//DataOutputstream ������ ���ϸ� �켱 ����.
			
			
			
			
			
			//1.ObjectOutputStream ������ ����������������� fileList �� ����.
			String title = null;
			String contents = null;
			String fileName = null;
			int fileSize = 0;
			byte[] fileContents = null;
			File home = new File("L:/������/Ŭ���̾�Ʈ");
			File[] files = home.listFiles();
			for(File tmp : files) {
				System.out.println(tmp.getAbsolutePath() + " : "+ tmp.length());
				System.out.println(tmp.getAbsolutePath() + " : " + tmp.getName());
			}
			
			//�ǽ�������.
			// ���� fileName,title,contents�� Ŭ���̾�Ʈ�� ������Ʈ���� ���� �����ͼ� �־���ߵȴ�.
			//fileName ���� Ŭ���̾�Ʈ ���� �����ͼ� �Է��Ѵ� fileName = Component.gettext()..
			//title ���� Ŭ���̾�Ʈ ���� �����ͼ� �Է��Ѵ�	title = Component.gettext()..
			//contents ���� Ŭ���̾�Ʈ ���� �����ͼ� �Է��Ѵ�. contents = Component.gettext()..
			
			fileName = "Ŭ���̾�Ʈ1.txt";
			File targetFile = new File(home.getPath() + "/" + fileName);
			//	System.out.println(home.getPath()+"/"+fileName);
			
				title = "Ŭ���̾�Ʈ1.txt";
				contents = "Ŭ���̾�Ʈ1.txt";
				fileName = targetFile.getName();
				fileSize = (int)targetFile.length();
				fileContents = new byte[fileSize];
				
				//������������ ���� ������ ����ش�.
				fis = new FileInputStream(targetFile);
				fis.read(fileContents);
				fis.close();
				
				//	System.out.println("1��° ���� :" + fileName + "�� ���� ������ :" +fileSize + " : " + "�� ���� ���빰 :" + fileContents );
			oos = new ObjectOutputStream(socket.getOutputStream());
			//����a���� , ����a���� , Ÿ������ ������ �̸� , ����ũ�� , ������ ����Ʈ�迭�� ��Ƽ� ���빭��
			FileList fl1 = new FileList(title , contents , fileName , fileSize , fileContents);
			oos.writeObject(fl1);
			
			//===========================================================
			
			fileName = "d1.JPG";
			targetFile = new File(home.getPath() + "/" + fileName);
				
				title = "d1.JPG";
				contents ="d1.JPG����";
				fileName = targetFile.getName();
				fileSize = (int)targetFile.length();
				fileContents = new byte[fileSize];
				
				//������������ ���� ������ ����ش�.
				fis = new FileInputStream(targetFile);
				fis.read(fileContents);
				fis.close();
			
			FileList fl2 =  new FileList(title , contents , fileName , fileSize , fileContents);
		
			oos.writeObject(fl2);
			
			//=============================================================
			
//			fileName = "a.txt";
//			targetFile = new File(home.getPath() + "/" + fileName);
//				
//				title = "a������";
//				contents ="a�ǳ���";
//				fileName = targetFile.getName();
//				fileSize = (int)targetFile.length();
//				fileContents = new byte[fileSize];
//			
//			FileList fl3 =  new FileList(title , contents , fileName , fileSize , fileContents);
//			oos.writeObject(fl3);
			
			
			
			
			System.out.println("����ȭ�� ������Ʈ ���� ����");
			
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ŭ���̾�Ʈ ������ �����߻�.");
		}

	}

	public Client() {
		this.func();

	}

	public static void main(String[] args) throws Exception {
		System.out.println("Ŭ���̾�Ʈ����");
		new Client();
	}

}
