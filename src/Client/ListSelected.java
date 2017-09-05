package Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		
		img.setPreferredSize(new Dimension(500,430));
		title.setPreferredSize(new Dimension(120, 50));
		comment.setPreferredSize(new Dimension(500,150));
		
		img.setBorder(tborder);
		pan.setBorder(tborder);
		comment.setBorder(tborder);
		
		pan.add(title);
		
		add(img,BorderLayout.NORTH);
		add(pan,BorderLayout.CENTER);
		add(sc,BorderLayout.SOUTH);
		
	}
	
	public ListSelected(PictureBoardPan parent,String img,String title,String comment,String writer) {
		
		this.img.setIcon(new ImageIcon(img));
		this.title.setText("제목 : "+title+"글쓴이 : "+writer);
		this.comment.setText(comment);
		this.setBackground(Color.WHITE);
		setSize(600, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		compInit();

		setModal(true);
	}


	
}
