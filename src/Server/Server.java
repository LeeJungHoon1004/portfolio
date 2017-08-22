package Server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ConnectionThread extends Thread {
	
	String name;
	String gender;
	String id ;
	String pw ;
	
	private String dailyList ;
//	private int dailyList [] = new int[3] ;
	private Socket socket = null;
	private DataInputStream dis;
	private DataOutputStream dos;

	public ConnectionThread(Socket socket) {
		try {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("����:�����ʱ�ȭ �����ߴ�.");
		}
	}

	public void run() {
		
		try {
			System.out.println(socket.getInetAddress()+ "���������߽��ϴ�");
			
			while(true) {
			String cmd = dis.readUTF();
			
			if(cmd.equals("ȸ������")) {
			/* ȸ������ name ,id , pw ,gender
			 *  
			 * 
			 */
				String name = dis.readUTF();
				String gender = dis.readUTF();
				String id = dis.readUTF();
				String pw = dis.readUTF();
				
				
				Member m = new Member(name , id , pw , gender );
				//Ŭ���̾�Ʈ�� ���� ���̵��  DB�� ����Ǿ��ִ� �������� �˻��մϴ�.
				//�����ڿ��� ���� �����߿��� id�� �˻��մϴ�
				System.out.println(name);
				boolean result  = Server.manager.isExist(m); 
				System.out.println(result);
				//���� �����Ѵ�.(�����ϴ¾��̵�) //���� ���� ȸ������ ������.
				if(result) {
					System.out.println("test1");
					dos.writeUTF("ȸ�����Խ���");
				}else { 
					System.out.println("test2");
					dos.writeUTF("ȸ�����Լ���");
				//�����ڿ��� ���� �����߿��� name, id , pw , gender�� �����մϴ�.
				int result2=	Server.manager.insertData(m);
			
				if(result2 > 0 ){
										System.out.println("ȸ�������Է¿� �����߽��ϴ�");
									}else{
										System.out.println("ȸ�������Է¿� �����߽��ϴ�.");
									}
				}
				dos.flush();
			//	dos.close();
				
			}
			
			else if(cmd.equals("�α���")) {
				
				String id = dis.readUTF();
				String pw = dis.readUTF();
				//�α����Ŀ� �α���â���� �̸��� ���
				//�α����Ŀ� BMI������ ������ִ� ��ü���������͸� �ҷ�����
				//���� ,����, ü�� �� �����ɴϴ�.
				
				
				Member m2 = new Member(id , pw);
				boolean result = Server.manager.isLoginOk(m2);
				System.out.println(result);
				if (result) {// ���������Ѵ�(ȸ������ �Ǿ��ִ� ���̵�ͺ���̴�) - �α����㰡
					dos.writeUTF("�α��μ���");
					String name = null;
					Member m1 = new Member(name , id ,pw);
					//����� ���� (id,pw�� �ص� �ش� ������� �̸��� ������)
					name =Server.manager.getNameData(m2);
					
					System.out.println(name);
					//�̸� , ����, ü�� ,���� ������� �����ϴ�
					dos.writeUTF(name);

				} else {
					dos.writeUTF("�α��ν���");
				}
				dos.flush();
				// dos.close();
				
				
			}
			//dailyPan combolist ������ ����
			else if(cmd.equals("�Ϸ��ǥ����")) {
				
				String list = dis.readUTF();
				System.out.println(list);
				
			
				//String list, String id
				Member m = new Member(list , name );
			String msg =Server.manager.InsertDailyList(m);
			System.out.println("�Ϸ��ǥ���۹޾Ҵ�.");
			}
			
			
		}
			
			
			
			
			
			
			
		}
		catch(Exception e) {
			System.out.println("���Ͽ�������.");
			System.out.println("����ڰ� �α׾ƿ��Ͽ����ϴ�.");
			e.printStackTrace();
			try{
			dos.close();
			}catch(Exception e1){
		//		e1.printStackTrace();
			}
		}
}

}

public class Server extends JFrame {
	// �Ŵ���
	public static Manager manager = new Manager();
	// ������Ʈ����
	private JButton buttonClose = new JButton("�ݱ�");
	private JTextArea textareaShow = new JTextArea();
	private JScrollPane scrollpaneshow = new JScrollPane(textareaShow);
	private JPanel panelsouth = new JPanel();
	private Server self = this;

	public void compInit() {

		this.buttonClose.setPreferredSize(new Dimension(100, 50));
		this.textareaShow.setEditable(false);
		this.textareaShow.setLineWrap(true);
		this.panelsouth.add(buttonClose);

		this.add(panelsouth, BorderLayout.SOUTH);
		this.add(scrollpaneshow, BorderLayout.CENTER);

	}

	public void eventInit() {
		this.buttonClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				self.dispose();
			}

		});

	}

	// ������
	public Server() {
		this.setSize(1000, 800);
		this.setTitle("���̾�Ʈ���α׷�����");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(Server.EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}

	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(40000);
		new Server();
		while (true) {
			new ConnectionThread(server.accept()).start();

		}

	}

}
