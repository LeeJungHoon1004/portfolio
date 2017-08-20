package Client;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class PicPan extends JPanel {

	private TitledBorder b_f = new TitledBorder("BEFORE & AFTER");
	private TitledBorder tip = new TitledBorder("²ÜÆÁ ³ª´©±â");
	private TitledBorder moti = new TitledBorder("ÀÚ±Ø»çÁø");
	
//===========================================================
	private ImageIcon imgb_f1 = new ImageIcon("¼ÛÇý±³.JPG");
	private ImageIcon imgb_f2 = new ImageIcon("¼öÁö¿¡ÇÁÅÍ.JPG");
	private ImageIcon imgb_f3 = new ImageIcon("À¯ºó.JPG");
	
	private JLabel bfpic1 = new JLabel(imgb_f1);
	private JLabel bfpic2 = new JLabel(imgb_f2);
	private JLabel bfpic3 = new JLabel(imgb_f3);
	
//===========================================================
	private ImageIcon imgTip1 = new ImageIcon("¿©ÀÚÃ¼Áö¹æ.JPG");
	private ImageIcon imgTip2= new ImageIcon("³²ÀÚÃ¼Áö¹æ.JPG");
	private ImageIcon imgTip3= new ImageIcon("¿Ê¹ßÃ¼Áß2.JPG");
	
	private JLabel tip1 = new JLabel(imgTip1);
	private JLabel tip2 = new JLabel(imgTip2);
	private JLabel tip3 = new JLabel(imgTip3);
	

//===========================================================
	
	private ImageIcon imageprettypic1 = new ImageIcon("±è»ç¶û.JPG");
	private ImageIcon imageprettypic2= new ImageIcon("Å©¸®½ºÅ»3.JPG");
	private ImageIcon imageprettypic3= new ImageIcon("¼Õ³ªÀº.JPG");
	private ImageIcon imageprettypic4= new ImageIcon("¼³Çö1.JPG");
	
	private JLabel pretty1 = new JLabel(imageprettypic1);
	private JLabel pretty2 = new JLabel(imageprettypic2);
	private JLabel pretty3 = new JLabel(imageprettypic3);
	private JLabel pretty4 = new JLabel(imageprettypic4);
	
	
	//===========================================================
	
	private JPanel b_fpicPan = new JPanel();
	private JPanel tipPan = new JPanel();
	private JPanel motivePan = new JPanel();
	
	
	private JScrollPane bfSc = new JScrollPane(b_fpicPan);
	private JScrollPane tipSc = new JScrollPane(tipPan);
	private JScrollPane motiveSC = new JScrollPane(motivePan);
	
	
	public void compInit() {

		
		
		
		setLayout(new GridLayout(3, 1));

		
		pretty1.setPreferredSize(new Dimension(300, 400));
		pretty2.setPreferredSize(new Dimension(300, 400));
		pretty3.setPreferredSize(new Dimension(300, 400));
		pretty4.setPreferredSize(new Dimension(300, 400));
		
		bfpic1.setPreferredSize(new Dimension(310, 320));
		bfpic2.setPreferredSize(new Dimension(310, 320));
		bfpic3.setPreferredSize(new Dimension(310, 320));
		
		
		tip1.setPreferredSize(new Dimension(310, 350));
		tip2.setPreferredSize(new Dimension(310, 350));
		tip3.setPreferredSize(new Dimension(310, 350));
		
		motivePan.setBorder(moti);
		motivePan.add(pretty1);
		motivePan.add(pretty2);
		motivePan.add(pretty3);
		motivePan.add(pretty4);
		add(motiveSC);
		
		b_fpicPan.setBorder(b_f);
		b_fpicPan.add(bfpic1);
		b_fpicPan.add(bfpic2);
		b_fpicPan.add(bfpic3);
		
		add(bfSc);

		
		
		tipPan.setBorder(tip);
		tipPan.add(tip1);
		tipPan.add(tip2);
		tipPan.add(tip3);
		
		add(tipSc);
		
		

		
	}
	
	public void event() {
	
	}
	
	
	public PicPan() {
		compInit();
		
		

	}

}
