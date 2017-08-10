package Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StimulsPhoto extends JFrame{
	
	private StimulsPhoto self = this;
	//===========================
	private JLabel labelPhoto1 = new JLabel("포토1");
	private JLabel labelPhoto2 = new JLabel("포토2");
	private JLabel labelPhoto3 = new JLabel("포토3");
	//===========================
	private JLabel labelPhotoTitle = new JLabel("타이틀");
	
	private JLabel labelPhotoFace = new JLabel("포토7");
	
	private JButton buttonInfo = new JButton("info");
	private JButton buttonDailyGoal = new JButton("하루목표");
	private JButton buttonGoal = new JButton("목표");
	private JButton buttonphoto = new JButton("사진"); // 나자신선택
	private JButton buttonvideo = new JButton("비디오");
	private JButton buttonCommunity = new JButton("커뮤니티");
	private JButton buttonSet = new JButton("설정");
	
	//=========================
	
	private JButton buttonUpload = new JButton("업로드");
	private JButton buttonRemove = new JButton("사진삭제"); //사진삭제시 id와 패스워드 비번확인 필요함.
	private JButton buttonClose = new JButton("닫기");
	//==========================
	
	private JPanel panelWest = new JPanel(new GridLayout(8,1)); //사이드바 
	private JPanel panelNorth = new JPanel(); //타이틀
	//==========================================
	private JPanel panelCard_StimulsPhoto = new JPanel(new GridLayout(3,1));
	private JPanel panelnull = new JPanel();
	private JPanel panelCenter = new JPanel(new GridLayout(1,3)); // 센터-포토3개
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
			//닫기했을때는 홈화면으로 이동함.. 카드레이아웃 
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
