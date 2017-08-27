package Server;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class communityPan extends JPanel {

	JButton button = new JButton("¹öÆ°");
	public void compInit() {
		this.add(button);
	}
	public void eventInit() {
		
	}
	public communityPan(Server parent) {
		this.setBackground(Color.WHITE);
		this.compInit();
	}
}
