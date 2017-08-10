package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UploadPictureList extends JDialog {

	private UploadPictureList self = this;
	//소켓
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	//컴포넌트
	
	private JButton buttonUpload = new JButton("업로드");
	private JButton buttonClose = new JButton("닫기");
	
	private JPanel panel_south = new JPanel();
	
	private String [] title = new String[] { " ", "ImageName", "ImageSize"};
	private String [] [] data ;
	
	private JPanel listPanel = new JPanel();
	private Vector<String> userColumn = new Vector<String>();
	private DefaultTableModel model ;
	private JTable userTable;
	private JScrollPane listJS;
	
	public void columnAdd(){
		model = new DefaultTableModel(data, title);
		userTable = new JTable(model);
		listJS = new JScrollPane(userTable);
		
		userTable.getColumn(" ").setPreferredWidth(30);
		userTable.getColumn("ImageName").setPreferredWidth(280);
		userTable.getColumn("ImageSize").setPreferredWidth(80);
		
	}
	
	public void compInit(){
		this.listPanel.setLayout(new BorderLayout());
		this.listPanel.add(listJS);
		this.panel_south.add(buttonUpload);
		this.panel_south.add(buttonClose);
		this.add(panel_south , BorderLayout.SOUTH);
		this.add(listPanel, BorderLayout.CENTER);
		this.setModal(true);
	}
	
	public void eventInit(){
		
		this.buttonClose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				self.dispose();
				
			}
			
		});
	}
	public UploadPictureList(StimulsPhoto parent){
		
		this.setSize(500, 500);
		this.setDefaultCloseOperation(UploadPictureList.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.columnAdd();
		this.compInit();
		this.eventInit();
	}
	
	
	
}
