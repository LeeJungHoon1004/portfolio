package Server;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CommunityPan extends JPanel{
	private Server parent;
	private CommunityPan self = this;
	//소켓
	private DataInputStream dis =null;
	private DataOutputStream dos = null;
	//컴포넌트
	private JButton button_listupdate = new JButton("목록갱신");
	private JButton button_download = new JButton("다운로드");
	private JButton button_close = new JButton("닫기");
	private JPanel panel_south = new JPanel();
	
	private String [] title = new String[] { " ",  "FileName" , "FileSize"};
	private String [][] data ;
	
	
	private JPanel listPanel = new JPanel();
    private Vector<String> userColumn = new Vector<String>();
    private DefaultTableModel model  ;
    private JTable  userTable ;
    private JScrollPane listJS ;
    
	
	public void columnAdd() {
		model  = new DefaultTableModel(data,title);
	//	model = new DefaultTableModel(userColumn, 0);
		userTable = new JTable(model);
	    listJS=new JScrollPane(userTable);
		
		userTable.getColumn(" ").setPreferredWidth(30);
		userTable.getColumn("FileName").setPreferredWidth(280);
    	userTable.getColumn("FileSize").setPreferredWidth(80);
	//    userTable.getColumn("FileName").setPreferredWidth(50);
	//    userTable.getColumn("FileSize").setPreferredWidth(100);
	    
	    
	    
    }
	
	public void compInit(){
		this.listPanel.setLayout(new BorderLayout());
		this.listPanel.add(listJS);
		this.panel_south.add(button_listupdate);
		this.panel_south.add(button_download);
		this.panel_south.add(button_close);
		this.add(panel_south , BorderLayout.SOUTH);
		this.add(listPanel, BorderLayout.CENTER);
		
	}
	public void eventInit(){
		
			
		//	dis = parent.getDis();
		//	dos = parent.getDos();
		this.button_listupdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
			//	dis = new DataInputStream(sock.getInputStream());
			//	dos = new DataOutputStream(sock.getOutputStream());
				dos.writeUTF("파일목록갱신");
				dos.flush();
				//서버가 보내준 파일의 갯수를 받음
				int filenumber = dis.readInt();
				String name;
				long length;
				for(int i = 0 ; i <filenumber ; i++){
				name =dis.readUTF();
				length = dis.readLong();
				model.addRow(new Object[] {(i+1),name,length});
				}
				
				
				
				}catch(Exception e1){
					e1.printStackTrace();
					}
				
			}
			
		});
		
		this.button_download.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					dos.writeUTF("다운로드");
					dos.flush();
					//서버가 보내준 파일의 갯수를 받음
					
				//서버에게 파일이름을 전송한다. 
				dos.writeUTF((String)model.getValueAt(userTable.getSelectedRow(), 1)); //1번이 이름
				//상대가보낸 파일사이즈를 받고 파일을받음.
				int fileSize = dis.readInt();
				byte[] fileContents = new byte[fileSize];
				dis.readFully(fileContents);
				
				//상대가 보낸 파일을 나의 Hdd에 저장한다.
				File f = new File("D:/6월자바_이정훈_2차/"+(String)model.getValueAt(userTable.getSelectedRow(), 1) );
		//		File f = new File("바탕화면"+(String)model.getValueAt(userTable.getSelectedRow(), 1) );
				FileOutputStream fos= new FileOutputStream(f);
				DataOutputStream dos = new DataOutputStream(fos);
				dos.write(fileContents);
				dos.flush();
				dos.close();
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
			
		});
		
		
		this.button_close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
		
	}
	

	public CommunityPan(){
		
		this.setSize(400,400);
		this.columnAdd();
		this.compInit();
		this.eventInit();
		
		
		
	}
}
