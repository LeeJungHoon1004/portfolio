package Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class ListSelected extends JDialog {

	private TitledBorder tborder = new TitledBorder("");
	
	private JLabel img = new JLabel();
	private JLabel title = new JLabel();
	private JLabel writer = new JLabel();
	private JLabel date = new JLabel();
	private JLabel comment = new JLabel();
	private JScrollPane sc = new JScrollPane(comment);
	private JPanel pan1 = new JPanel(new BorderLayout(1,0));
	
	private void compInit() {
		setLayout(new BorderLayout(3, 3));
		
		img.setOpaque(true);
		title.setOpaque(true);
		writer.setOpaque(true);
		comment.setOpaque(true);//이걸 해줘야 jlabel에 배경색이 입혀짐.
		
		img.setBackground(Color.white);
		title.setBackground(Color.white);
		writer.setBackground(Color.white);
		comment.setBackground(Color.white);
		pan1.setBackground(Color.white);
		
		img.setPreferredSize(new Dimension(500,200));
		title.setPreferredSize(new Dimension(270, 150));
		writer.setPreferredSize(new Dimension(230,15));
		sc.setPreferredSize(new Dimension(500,125));
		
		pan1.setPreferredSize(new Dimension(500,50));
		
		img.setHorizontalAlignment(SwingConstants.CENTER);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		writer.setHorizontalAlignment(SwingConstants.CENTER);
		comment.setHorizontalAlignment(SwingConstants.CENTER);
		
		img.setBorder(tborder);
		pan1.setBorder(tborder);
		sc.setBorder(tborder);
		
		pan1.add(title,BorderLayout.WEST);
		pan1.add(writer,BorderLayout.EAST);
		
		add(pan1,BorderLayout.NORTH);
		add(img,BorderLayout.CENTER);
		add(sc,BorderLayout.SOUTH);
	}
	
	public ListSelected(PictureBoardPan parent,String img,String title,String comment,String writer) {
		
		this.setBackground(Color.white);
		this.img.setIcon(new ImageIcon(img));
		this.title.setText(title);
		this.writer.setText("|"+"작성자 :  "+writer);
		this.comment.setText(comment);
		
		setSize(600, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		compInit();

		setModal(true);
	}

	
}
