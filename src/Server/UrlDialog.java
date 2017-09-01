package Server;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class UrlDialog extends JDialog{
	private UrlDialog self = this;
	private JButton button_close = new JButton("´Ý±â");
	private JPanel panel_south = new JPanel();
	private JButton button_yoga1 = new JButton();
	private JButton button_yoga2 = new JButton();
	private JButton button_yoga3 = new JButton();
	private JButton button_yoga4 = new JButton();
	private JButton button_yoga5 = new JButton();
	
	private JButton button_stretching1 = new JButton();
	private JButton button_stretching2 = new JButton();
	private JButton button_stretching3 = new JButton();
	private JButton button_stretching4 = new JButton();
	private JButton button_stretching5 = new JButton();
	
	private JButton button_mileyCyrus1 = new JButton();
	private JButton button_mileyCyrus2 = new JButton();
	private JButton button_mileyCyrus3 = new JButton();
	private JButton button_mileyCyrus4 = new JButton();
	private JButton button_mileyCyrus5 = new JButton();
	
	private JButton button_homeDiet1 = new JButton();
	private JButton button_homeDiet2 = new JButton();
	private JButton button_homeDiet3 = new JButton();
	private JButton button_homeDiet4 = new JButton();
	private JButton button_homeDiet5 = new JButton();
	
	private JButton button_smihott1 = new JButton();
	private JButton button_smihott2 = new JButton();
	private JButton button_smihott3 = new JButton();
	private JButton button_smihott4 = new JButton();
	private JButton button_smihott5 = new JButton();
	
	
	
	private JPanel panel_center = new JPanel (new GridLayout(5,5));
	
	
	public void compInit() {
	this.panel_center.add(button_yoga1);
	this.panel_center.add(button_yoga2);
	this.panel_center.add(button_yoga3);
	this.panel_center.add(button_yoga4);
	this.panel_center.add(button_yoga5);
	
	this.panel_center.add(button_stretching1);
	this.panel_center.add(button_stretching2);
	this.panel_center.add(button_stretching3);
	this.panel_center.add(button_stretching4);
	this.panel_center.add(button_stretching5);
	
	this.panel_center.add(button_mileyCyrus1);
	this.panel_center.add(button_mileyCyrus2);
	this.panel_center.add(button_mileyCyrus3);
	this.panel_center.add(button_mileyCyrus4);
	this.panel_center.add(button_mileyCyrus5);
	
	this.panel_center.add(button_homeDiet1);
	this.panel_center.add(button_homeDiet2);
	this.panel_center.add(button_homeDiet3);
	this.panel_center.add(button_homeDiet4);
	this.panel_center.add(button_homeDiet5);
	
	this.panel_center.add(button_smihott1);
	this.panel_center.add(button_smihott2);
	this.panel_center.add(button_smihott3);
	this.panel_center.add(button_smihott4);
	this.panel_center.add(button_smihott5);
	
	
	
	
	this.panel_south.add(button_close);	
		
	this.add(panel_center , BorderLayout.CENTER);
	this.add(panel_south, BorderLayout.SOUTH);
	}
	public void eventInit() {
		this.button_close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.dispose();
			}
			
		});
		
	}
	
	public UrlDialog(Server parent) {
		this.compInit();
		this.eventInit();
		this.setSize(800, 800);
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(UrlDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
