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
import java.net.ServerSocket;
import java.net.Socket;


class ConnectionThread extends Thread {
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

	public ConnectionThread(Socket socket) {
		try {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("����:�����ʱ�ȭ�� �����ߴ�");
		}
	}

	public void run() {

		System.out.println(socket.getInetAddress() + "���� �����߽��ϴ�");
		try {
			
		

			

				String cmd = dis.readUTF();

				if (cmd.equals("���Ͽ����ĵ����ͼ���")) {
					// �����Ϸ��� ������ �̸� , ũ�� , ���빰(������ü) , ������ Ÿ��Ʋ ,������ ����
					String title = null;
					String contents = null;
					String fileName = null;
					int fileSize = 0;
					byte[] fileContents = null;
					
					//2.Ŭ���̾�Ʈ���� �����͸� �޽��ϴ� (2.ClientRam to ServerRam)
					ois = new ObjectInputStream(socket.getInputStream());
					FileList fl1 = (FileList) ois.readObject();
					FileList fl2 = (FileList) ois.readObject();
//					FileList fl3 = (FileList) ois.readObject();
					
					
					
					
					System.out.println(fl1.getTitle()); //����
					System.out.println(fl1.getContents());//�ڸ�Ʈ
					System.out.println(fl1.getFileName());//�����̸�
					System.out.println(fl1.getFileSize());//������ ũ��(int)
					System.out.println(fl1.getFileContents());//������ ���빰(byte [])
					
					
					System.out.println(fl2.getTitle());
					System.out.println(fl2.getContents());
					System.out.println(fl2.getFileName());
					System.out.println(fl2.getFileSize());
					System.out.println(fl2.getFileContents());
					
					
//					System.out.println(fl3.getTitle());
//					System.out.println(fl3.getContents());
//					System.out.println(fl3.getFileName());
//					System.out.println(fl3.getFileSize());
//					System.out.println(fl3.getFileContents());
					
					//3.Ŭ���̾�Ʈ���� ���� �����͸� ��ο� �����մϴ� (3.Ram to Hdd)
					fileContents = fl1.getFileContents();
					File f = new File("L:/������/����/" + fl1.getFileName());
					fos = new FileOutputStream(f);
					bos = new BufferedOutputStream(fos);
					dos = new DataOutputStream(bos);
					dos.write(fileContents);
					dos.flush();
					
					fileContents = fl2.getFileContents();
					File f2 = new File("L:/������/����/" + fl2.getFileName());
					fos = new FileOutputStream(f2);
					dos = new DataOutputStream(fos);
					dos.write(fileContents);
					dos.flush();
					
//					fileContents = fl3.getFileContents();
//					File f3 = new File("E:/���α׷���/Java���/�ڹٱ�������α׷��Ӿ缺_7��/Server/" + fl3.getFileName());
//					fos = new FileOutputStream(f3);
//					dos = new DataOutputStream(fos);
//					dos.write(fileContents);
//					dos.flush();
					
					System.out.println("Ŭ���̾�Ʈ���� ���� �����͸� �ϵ��ũ�� ����Ϸ�.");
					
					dos.close();
					
					
				}
				
//				else if() {
//					
//				}
				
			 

			}
		catch (Exception e) {
			System.out.println("Server���� ���������� �����߻�.");
			e.printStackTrace();
		
		} // - while
	}
}

public class Server {
	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(40000);
		new Server();
		while (true) {
			new ConnectionThread(server.accept()).start();
		}

	}
}
