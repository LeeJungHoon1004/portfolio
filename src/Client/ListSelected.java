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
	private JLabel comment = new JLabel();
	private JScrollPane sc = new JScrollPane(comment);
	private JPanel pan = new JPanel();
	
	private void compInit() {
		setLayout(new BorderLayout(3, 3));
		
		img.setOpaque(true);
		title.setOpaque(true);
		comment.setOpaque(true);//이걸 해줘야 jlabel에 배경색이 입혀짐.
		
		img.setBackground(Color.white);
		title.setBackground(Color.white);
		comment.setBackground(Color.white);
		pan.setBackground(Color.white);
		
		img.setPreferredSize(new Dimension(500,430));
		title.setPreferredSize(new Dimension(120, 50));
		comment.setPreferredSize(new Dimension(500,150));
		
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		
		img.setBorder(tborder);
		pan.setBorder(tborder);
		comment.setBorder(tborder);
		
		pan.add(title);
		
		add(img,BorderLayout.NORTH);
		add(pan,BorderLayout.CENTER);
		add(sc,BorderLayout.SOUTH);
		
	}
	
	public ListSelected(PictureBoardPan parent,String img,String title,String comment,String writer) {
		
		this.setBackground(Color.white);
		
		this.img.setIcon(new ImageIcon(img));
		String text = "제목 : "+title+"글쓴이 : "+writer;
		this.title.setText(text);
		this.comment.setText(comment);
		this.setBackground(Color.WHITE);
		setSize(600, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		compInit();

		setModal(true);
	}

	
}
