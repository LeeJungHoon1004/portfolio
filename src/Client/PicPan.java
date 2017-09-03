package Client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class PicPan extends JPanel {

	private static final Component PicPan = null;
	private TitledBorder b_f = new TitledBorder("BEFORE & AFTER");
	private TitledBorder tip = new TitledBorder("꿀팁 나누기");
	private TitledBorder moti = new TitledBorder("자극사진");
	private TitledBorder pharse = new TitledBorder("자극문구");
	private JScrollPane sc1 = new JScrollPane(PicPan);
//===========================================================
	private ImageIcon imgb_f1 = new ImageIcon("몸매.JPG");
	private ImageIcon imgb_f2 = new ImageIcon("수지에프터.JPG");
	private ImageIcon imgb_f3 = new ImageIcon("유빈.JPG");
	private ImageIcon imgb_f4 = new ImageIcon("에일리.JPG");
	private ImageIcon imgb_f5 = new ImageIcon("송혜교.JPG");
	
	private JLabel bfpic1 = new JLabel(imgb_f1);
	private JLabel bfpic2 = new JLabel(imgb_f2);
	private JLabel bfpic3 = new JLabel(imgb_f3);
	private JLabel bfpic4 = new JLabel(imgb_f4);
	private JLabel bfpic5 = new JLabel(imgb_f5);
	
//===========================================================
	private ImageIcon imgTip1 = new ImageIcon("여자체지방.JPG");
	private ImageIcon imgTip2= new ImageIcon("남자체지방.JPG");
	private ImageIcon imgTip3= new ImageIcon("옷발체중.JPG");
	
	private JLabel tip1 = new JLabel(imgTip1);
	private JLabel tip2 = new JLabel(imgTip2);
	private JLabel tip3 = new JLabel(imgTip3);
	

//===========================================================
	
	private ImageIcon imageprettypic1 = new ImageIcon("김사랑.JPG");
	private ImageIcon imageprettypic2= new ImageIcon("크리스탈3.JPG");
	private ImageIcon imageprettypic3= new ImageIcon("손나은.JPG");
	private ImageIcon imageprettypic4= new ImageIcon("설현.JPG");
	private ImageIcon imageprettypic5= new ImageIcon("하니.JPG");
	
	
	private JLabel pretty1 = new JLabel(imageprettypic1);
	private JLabel pretty2 = new JLabel(imageprettypic2);
	private JLabel pretty3 = new JLabel(imageprettypic3);
	private JLabel pretty4 = new JLabel(imageprettypic4);
	private JLabel pretty5 = new JLabel(imageprettypic5);
	
	//===========================================================
	private ImageIcon imgPhrase1 = new ImageIcon("야식.JPG");
	private ImageIcon imgPhrase2= new ImageIcon("인생은.JPG");
	private ImageIcon imgPhrase3= new ImageIcon("그맛이다.JPG");
	private ImageIcon imgPhrase4= new ImageIcon("음식아까워마라.JPG");
	private ImageIcon imgPhrase5= new ImageIcon("흔들리면지방.JPG");
	
	private JLabel phrase1 = new JLabel(imgPhrase1);
	private JLabel phrase2 = new JLabel(imgPhrase2);
	private JLabel phrase3 = new JLabel(imgPhrase3);
	private JLabel phrase4 = new JLabel(imgPhrase4);
	private JLabel phrase5 = new JLabel(imgPhrase5);
	
	//===========================================================
	private JButton upload_bt = new JButton("업로드");
	private JButton delete_bt = new JButton("삭제");
	private JPanel uploadPan = new JPanel(new FlowLayout());
	
	
	//---------------------------
	
	private JPanel b_fpicPan = new JPanel();
	private JPanel tipPan = new JPanel();
	private JPanel motivePan = new JPanel();
	private JPanel phrasePan = new JPanel();
	
	private JScrollPane bfSc = new JScrollPane(b_fpicPan);
	private JScrollPane tipSc = new JScrollPane(tipPan);
	private JScrollPane motiveSC = new JScrollPane(motivePan);
	private JScrollPane pharseSC = new JScrollPane(phrasePan);
	
	public void compInit() {

		b_fpicPan.setBackground(Color.white);
		tipPan.setBackground(Color.white);
		motivePan.setBackground(Color.white);
		phrasePan.setBackground(Color.white);
		
		setLayout(new GridLayout(5, 1));

		

		pretty1.setPreferredSize(new Dimension(240, 330));
		pretty2.setPreferredSize(new Dimension(240, 330));
		pretty3.setPreferredSize(new Dimension(240, 330));
		pretty4.setPreferredSize(new Dimension(240, 330));



		
		bfpic1.setPreferredSize(new Dimension(300, 300));
		bfpic2.setPreferredSize(new Dimension(300, 300));
		bfpic3.setPreferredSize(new Dimension(300, 300));
		
		
		tip1.setPreferredSize(new Dimension(300, 300));
		tip2.setPreferredSize(new Dimension(300, 300));
		tip3.setPreferredSize(new Dimension(300, 300));
		
		
		phrase1.setPreferredSize(new Dimension(300, 300));
		phrase2.setPreferredSize(new Dimension(300, 300));
		phrase3.setPreferredSize(new Dimension(300, 300));
		phrase4.setPreferredSize(new Dimension(300, 300));
		phrase5.setPreferredSize(new Dimension(300, 300));
		
		upload_bt.setPreferredSize(new Dimension(100, 30));
		
		motivePan.setBorder(moti);
		motivePan.add(pretty1);
		motivePan.add(pretty2);
		motivePan.add(pretty3);
		motivePan.add(pretty4);
		motivePan.add(pretty5);
		add(motiveSC);
		
		b_fpicPan.setBorder(b_f);
		b_fpicPan.add(bfpic1);
		b_fpicPan.add(bfpic2);
		b_fpicPan.add(bfpic3);
		b_fpicPan.add(bfpic4);
		b_fpicPan.add(bfpic5);
		
		add(bfSc);

		
		
		tipPan.setBorder(tip);
		tipPan.add(tip1);
		tipPan.add(tip2);
		tipPan.add(tip3);
		
		add(tipSc);
		
		phrasePan.setBorder(pharse);
		phrasePan.add(phrase1);
		phrasePan.add(phrase2);
		phrasePan.add(phrase3);
		phrasePan.add(phrase4);
		phrasePan.add(phrase5);
		
		add(pharseSC);
	
		

		add(uploadPan);
		uploadPan.add(upload_bt);
		uploadPan.add(delete_bt);
		
	}
	
	public void event() {
	
	}
	
	
	public PicPan() {
		compInit();
		this.setBackground(Color.white);
		

	}

}
