package Server;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GoalPan extends JPanel {

	//���

	JButton button = new JButton("��ư");
	public void compInit() {
		this.add(button);
	}
	public void eventInit() {
		
	}
	
	//������
	public GoalPan(Server parent) {
		this.setBackground(Color.WHITE);
		this.compInit();
	}
	
}
