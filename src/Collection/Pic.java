package Collection;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Pic extends JFrame implements ActionListener{
	
	
	JButton bt;
	
	private void compInit(){
		
		//this.setLayout(new GridLayout(3,2,20,10)); // 칸나누기
		this.setLayout(new FlowLayout(FlowLayout.RIGHT)); // 한라인상에 한번에 두기
		
		
		//this. button.setSize(200, 30);
		
//		this.button.setPreferredSize(new Dimension(200,30));
		
		
		
		
		bt = new JButton(new ImageIcon("empty.jpg"));
		bt.setBackground(Color.red);
		
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
		
		bt.addActionListener(this);
		add(bt);
		setVisible(true);


		
	}
		
		public Pic(){
			
			this.setSize(500,400);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(Pic.EXIT_ON_CLOSE);
			this.setVisible(true);
			this.compInit();
			
		}
	


@Override
public void actionPerformed(ActionEvent e) {

//	if(e.getSource()==bt){ JOptionPane }


}



public static void main(String[] args) {
	new Pic();
	
}
	
}
