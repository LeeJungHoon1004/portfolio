package Server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ConnectionThread extends Thread {

	private Socket socket = null;
	private DataInputStream dis;
	private DataOutputStream dos;

	public ConnectionThread(Socket socket) {
		try {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("서버:소켓초기화 실패했다.");
		}

	}

	public void run() {
		
		try {
			System.out.println(socket.getInetAddress()+ " 님이 접속했습니다");
			
			while(true) {
			String cmd = dis.readUTF();
			
			if(cmd.equals("회원가입")) {
			/* 회원가입 name ,id , pw ,gender
			 *  
			 * 
			 */
				String name = dis.readUTF();
				String gender = dis.readUTF();
				String id = dis.readUTF();
				String pw = dis.readUTF();
				
				
				Member m = new Member(name , id , pw , gender );
				//클라이언트로 받은 아이디와  DB에 저장되어있는 내용인지 검사합니다.
				//생성자에서 받은 정보중에서 id만 검사합니다
				boolean result  = Server.manager.isExist(m); 
				//값이 존재한다.(존재하는아이디) //값이 없다 회원가입 시켜줌.
				if(result) {
					dos.writeUTF("회원가입실패");
				}else { 
					dos.writeUTF("회원가입성공");
				//생성자에서 받은 정보중에서 id,pw만 저장합니다.
					Server.manager.insertData(m);
			
				}
				dos.flush();
				dos.close();
			}
			
			else if(cmd.equals("로그인")) {
				
				String id = dis.readUTF();
				String pw = dis.readUTF();
				//로그인후에 로그인창에는 이름을 출력
				//로그인후에 BMI란에는 저장되있는 신체지수데이터를 불러오고
				//성별 ,신장, 체중 를 가져옵니다.
				
				
				Member m2 = new Member(id , pw);
				boolean result = Server.manager.isLoginOk(m2);
				if (result) {// 값이존재한다(회원가입 되어있는 아이디와비번이다) - 로그인허가
					dos.writeUTF("로그인성공");
					String gender =null;
					double stature =0;
					double weight =0;
					String name = null;
					Member m1 = new Member(name , stature , weight, gender);
					m1 =Server.manager.getBMIData();
					//멤버 m1의 정보를 불러와서 4개를 채우고(성별, 키 , 몸무게 , 이름)
					//클라이언트의 정보 2개를 입력받아서 2개를 채웁니다.
					
					gender =m1.isGender();
					stature= m1.getStature();
					weight = m1.getWeight();
					name = m1.getName();
					
					//이름 , 신장, 체중 ,성별 순서대로 보냅니다
					dos.writeUTF(name);
					dos.writeDouble(stature);
					dos.writeDouble(weight);
					dos.writeUTF(gender);			
					
				} else {
					dos.writeUTF("로그인실패");
				}
				dos.flush();
				// dos.close();
				
			}
//			else if("커맨드") {
//			
//			}
			
			
		}
			
			
			
			
			
			
			
		}
		catch(Exception e) {
			System.out.println("비정상종료.");
			try{
			dos.close();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
}

}

public class Server extends JFrame {
	// 매니저
	public static Manager manager = new Manager();
	// 컴포넌트변수
	private JButton buttonClose = new JButton("닫기");
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

	// 생성자
	public Server() {
		this.setSize(1000, 800);
		this.setTitle("다이어트프로그램서버");
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
