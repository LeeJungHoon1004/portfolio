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
	private TitledBorder tip = new TitledBorder("꿀팁 나누기");
	
//===========================================================
	private ImageIcon imgb_f1 = new ImageIcon("송혜교.JPG");
	private ImageIcon imgb_f2 = new ImageIcon("수지에프터.JPG");
	private ImageIcon imgb_f3 = new ImageIcon("설현1.JPG");
	
	private JLabel bfpic1 = new JLabel(imgb_f1);
	private JLabel bfpic2 = new JLabel(imgb_f2);
	private JLabel bfpic3 = new JLabel(imgb_f3);
	
//===========================================================
	private ImageIcon imgTip1 = new ImageIcon("여자체지방.JPG");
	private ImageIcon imgTip2= new ImageIcon("남자체지방.JPG");
	private ImageIcon imgTip3= new ImageIcon("옷발체중.JPG");
	
	private JLabel tip1 = new JLabel(imgTip1);
	private JLabel tip2 = new JLabel(imgTip2);
	private JLabel tip3 = new JLabel(imgTip3);
	

//===========================================================
	private JPanel b_fpicPan = new JPanel();
	private JPanel tipPan = new JPanel();
	private JPanel motivePan = new JPanel();
	
	
	private JScrollPane bfSc = new JScrollPane(b_fpicPan);
	private JScrollPane tipSc = new JScrollPane(tipPan);
	private JScrollPane motiveSC = new JScrollPane(motivePan);
	
	
	public void compInit() {

		setLayout(new GridLayout(3, 1));

		bfpic1.setPreferredSize(new Dimension(300, 340));
		bfpic2.setPreferredSize(new Dimension(300, 340));
		bfpic3.setPreferredSize(new Dimension(300, 340));
		
		
		tip1.setPreferredSize(new Dimension(300, 340));
		tip2.setPreferredSize(new Dimension(300, 340));
		tip3.setPreferredSize(new Dimension(300, 340));
		
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
