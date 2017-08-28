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
	//����
	private DataInputStream dis =null;
	private DataOutputStream dos = null;
	//������Ʈ
	private JButton button_listupdate = new JButton("��ϰ���");
	private JButton button_download = new JButton("�ٿ�ε�");
	private JButton button_close = new JButton("�ݱ�");
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
				dos.writeUTF("���ϸ�ϰ���");
				dos.flush();
				//������ ������ ������ ������ ����
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
					dos.writeUTF("�ٿ�ε�");
					dos.flush();
					//������ ������ ������ ������ ����
					
				//�������� �����̸��� �����Ѵ�. 
				dos.writeUTF((String)model.getValueAt(userTable.getSelectedRow(), 1)); //1���� �̸�
				//��밡���� ���ϻ���� �ް� ����������.
				int fileSize = dis.readInt();
				byte[] fileContents = new byte[fileSize];
				dis.readFully(fileContents);
				
				//��밡 ���� ������ ���� Hdd�� �����Ѵ�.
				File f = new File("D:/6���ڹ�_������_2��/"+(String)model.getValueAt(userTable.getSelectedRow(), 1) );
		//		File f = new File("����ȭ��"+(String)model.getValueAt(userTable.getSelectedRow(), 1) );
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
