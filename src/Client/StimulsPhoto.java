package Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StimulsPhoto extends JFrame{
	
	
	
	private StimulsPhoto self = this;
	//===========================
	
	
	
	
	
	private JLabel labelPhoto1 = new JLabel("����1");
	private JLabel labelPhoto2 = new JLabel("����2");
	private JLabel labelPhoto3 = new JLabel("����3");
	//===========================
	private JLabel labelPhotoTitle = new JLabel("Ÿ��Ʋ");
	
	private JLabel labelPhotoFace = new JLabel("����7");
	
	private JButton buttonInfo = new JButton("info");
	private JButton buttonDailyGoal = new JButton("�Ϸ��ǥ");
	private JButton buttonGoal = new JButton("��ǥ");
	private JButton buttonphoto = new JButton("����"); // ���ڽż���
	private JButton buttonvideo = new JButton("����");
	private JButton buttonCommunity = new JButton("Ŀ�´�Ƽ");
	private JButton buttonSet = new JButton("����");
	
	//=========================
	
	private JButton buttonUpload = new JButton("���ε�");
	private JButton buttonRemove = new JButton("��������"); //���������� id�� �н����� ���Ȯ�� �ʿ���.
	private JButton buttonClose = new JButton("�ݱ�");
	//==========================
	
	private JPanel panelWest = new JPanel(new GridLayout(8,1)); //���̵�� 
	private JPanel panelNorth = new JPanel(); //Ÿ��Ʋ
	//==========================================
	private JPanel panelCard_StimulsPhoto = new JPanel(new GridLayout(3,1));
	private JPanel panelnull = new JPanel();
	private JPanel panelCenter = new JPanel(new GridLayout(1,3)); // ����-����3��
	private JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	//===========================================
	
	
	public void compInit(){
		
		
	//====================================
	this.panelCenter.add(labelPhoto1);
	this.panelCenter.add(labelPhoto2);
	this.panelCenter.add(labelPhoto3);
	//====================================
	
	this.panelNorth.add(labelPhotoTitle);
	
	this.panelWest.add(labelPhotoFace);
	this.panelWest.add(buttonInfo);
	this.panelWest.add(buttonDailyGoal);
	this.panelWest.add(buttonGoal);
	this.panelWest.add(buttonphoto);
	this.panelWest.add(buttonvideo);
	this.panelWest.add(buttonCommunity);
	this.panelWest.add(buttonSet);
	
	//================================================
	this.panelSouth.add(buttonUpload);
	this.panelSouth.add(buttonRemove);
	this.panelSouth.add(buttonClose);
	
	this.panelCard_StimulsPhoto.add(panelCenter);
	this.panelCard_StimulsPhoto.add(panelnull);
	this.panelCard_StimulsPhoto.add(panelSouth);
	
	this.add(panelWest, BorderLayout.WEST);
	this.add(panelCard_StimulsPhoto, BorderLayout.CENTER);
	this.add(panelNorth , BorderLayout.NORTH);
	//==================================================
	}
	public void eventInit(){
		
		this.buttonUpload.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new UploadPictureList(self).setVisible(true);
				
			}
			
		});
		
		this.buttonRemove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.buttonClose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//	self.dispose();
			//�ݱ��������� Ȩȭ������ �̵���.. ī�巹�̾ƿ� 
			}
			
		});
		
		
	}
	public StimulsPhoto(){
		
		
		this.setSize(800,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(StimulsPhoto.EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new StimulsPhoto();
	}
	
}
