package Server;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class goalPan extends JPanel {

	//멤버

	JButton button = new JButton("버튼");
	public void compInit() {
		this.add(button);
	}
	public void eventInit() {
		
	}
	
	//생성자
	public goalPan(Server parent) {
		this.setBackground(Color.WHITE);
		this.compInit();
	}
	
}
