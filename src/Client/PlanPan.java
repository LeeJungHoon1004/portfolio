package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PlanPan extends JPanel {
	private BasicShape parent ;
	private PlanPan self = this;
	
	private TitledBorder tborder = new TitledBorder("");
	//糕滚
	private JButton buttonNorth = new JButton("合率滚瓢");

	private BMI bmi = new BMI();
	private ComboPan combo = new ComboPan();
	private JPanel panelNorth = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JPanel panelSouth = new JPanel();
	
	public void compInit() {
		
		this.setLayout(new GridLayout(3,1));
		this.panelNorth.add(buttonNorth);
		this.panelCenter.add(combo);
		this.panelSouth.add(bmi);
		
		combo.setPreferredSize(new Dimension(872,600));
		combo.setBorder(tborder);
		
		
		bmi.setPreferredSize(new Dimension(872, 650));
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
	
	//积己磊
	public PlanPan(BasicShape self) {
		 
		this.setBackground(Color.WHITE);
		
		this.setSize(900, 900);
		
		this.compInit();
		this.eventInit();
		this.setVisible(true);
		
	}
	
	


}
