package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CalandarPan extends JPanel {

	BasicShape parent;


	private CalandarPan self = this;


	private JButton buttonUpload = new JButton("체크"); // 체크완료 버튼


	
	public void compInit() {

		this.add(buttonUpload);

	}


	public void eventInit() {
	
		
	}


	//생성자
	public CalandarPan() {
		this.setBackground(Color.WHITE);
	
		this.setSize(900, 900);

		this.compInit();
		this.eventInit();
		this.setVisible(true);

	}




}
