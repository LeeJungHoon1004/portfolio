package Server;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GoalPan extends JPanel {

	//멤버

	JButton button = new JButton("버튼");
	public void compInit() {
		this.add(button);
	}
	public void eventInit() {
		
	}
	
	//생성자
	public GoalPan(Server parent) {
		this.setBackground(Color.WHITE);
		this.compInit();
	}
	
}
