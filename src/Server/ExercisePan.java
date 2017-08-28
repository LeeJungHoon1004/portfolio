package Server;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ExercisePan extends JPanel{

	JButton button = new JButton("獄動");

public void compInit() {
		this.add(button);
	}
	public void eventInit() {
		
	}
	
	//持失切
	public ExercisePan(Server parent) {
		this.setBackground(Color.WHITE);
		this.compInit();
	
	}

	
}
