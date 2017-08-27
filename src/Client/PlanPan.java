package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PlanPan extends JPanel {
	private TitledBorder tborder = new TitledBorder("");
	//���
	private JButton buttonNorth = new JButton("���ʹ�ư");
	private JButton buttoncenter = new JButton("���͹�ư");
	private BMI bmi = new BMI();
	private JPanel panelNorth = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JPanel panelSouth = new JPanel();
	
	public void compInit() {
		
		this.panelNorth.add(buttonNorth);
		this.panelCenter.add(buttoncenter);
		this.panelSouth.add(bmi);
		bmi.setPreferredSize(new Dimension(872, 900));
		bmi.setBorder(tborder);
		this.add(panelNorth);
		this.add(panelCenter);
		this.add(panelSouth);
		
		panelNorth.setBackground(Color.white);
		panelCenter.setBackground(Color.white);
		panelSouth.setBackground(Color.white);
		
	}
	public void eventInit() {
		
	}
	
	//������
	public PlanPan() {
		
		this.setLayout(new GridLayout(3,1));
		this.setSize(900, 1800);
		
		this.compInit();
		this.eventInit();
		this.setVisible(true);
		
	}
	
	


}
