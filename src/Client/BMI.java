package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class BMI extends JPanel {

	private  TitledBorder tborder = new TitledBorder("");
	
	private JLabel stature = new JLabel("Ű : ");
	private JLabel weight = new JLabel("ü�� : ");
	private JLabel gender = new JLabel("���� : ");
	private JTextField inputStature = new JTextField();
	private JTextField inputWeight = new JTextField();
	private JRadioButton male = new JRadioButton("����");
	private JRadioButton female = new JRadioButton("����");
	private ButtonGroup group = new ButtonGroup();
	private JButton resultButton = new JButton("���");
	private JPanel genderPan = new JPanel();
	private JPanel radioPan = new JPanel(new GridLayout(1, 2));
	// ==============����BMI���� �������Է�UI����=========================
	private String name;
	private double result;
	private String grade;
	private ImageIcon img = new ImageIcon("bmi�񸸵�.jpg");
	private JLabel imgResult = new JLabel(img);
	private JLabel result1 = new JLabel();
	private JLabel result2 = new JLabel();
	private JLabel result3 = new JLabel();
	private JPanel resultPan = new JPanel(new GridLayout(1, 2));
	private Font f = new Font("����", Font.ITALIC, 21);

	
	//================�����κ��� �̸��ޱ�=======================
	
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public void compInit() {
		setLayout(null);
		
		imgResult.setBounds(35, 76, 800, 400);	
		stature.setBounds(112, 500, 44, 40);
		inputStature.setBounds(171,500, 255, 40);
		weight.setBounds(492,500, 56, 40);
		inputWeight.setBounds(562,500,255, 40);
		gender.setBounds(112,550, 60, 50);
		radioPan.setBounds(178,550, 252, 50);
		resultButton.setBounds(670, 560, 84, 40);
		
		add(imgResult);
		add(stature);
		add(inputStature);
		add(weight);
		add(inputWeight);
		add(gender);
		group.add(male);
		group.add(female);
		radioPan.setBackground(Color.white);
		radioPan.add(male);
		radioPan.add(female);
		add(radioPan);

		add(resultButton);
		
		
		
	}// end

	public void eventInit() {
		
		resultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultBMI();
				gradeBMI();
				
				resultPan.setBounds(120, 620, 720, 150);
				
				result1.setFont(f);
				result2.setFont(f);
				result1.setHorizontalTextPosition(SwingConstants.CENTER);
				result1.setText(name + " ���� BMI �����"+result + " % �̹Ƿ� "+grade + " �Դϴ�.");
				
				resultPan.setBorder(tborder);
				resultPan.add(result1);
				
				resultPan.setBackground(new Color(255, 209, 43));
				add(resultPan);
			}
		});
	}
	
	public double resultBMI() {
		double stature = Integer.parseInt(inputStature.getText())*0.1;
		double weight = Double.parseDouble(inputWeight.getText());
		double resultdouble = (weight) / (stature * stature);
		return result = resultdouble*100;
	}// end

	public String gradeBMI() {
		
		if (result < 18.5) {
			grade = "��ü��";
		} else if (result >= 18.5 && result <= 24.9) {
			grade = "����";
		} else if (result >= 25 && result <= 29.9) {
			grade = "��ü��";
		} else if (result <= 30 && result <= 34.9) {
			grade = "��";
		} else if (result > 35) {
			grade = "����";
		}
		return grade;
	}// end

	public String ConnectClient() {
		try {
			client = new Socket("192.168.53.4",40000);
			dis = new DataInputStream(client.getInputStream());
		} catch (Exception e) {
			System.out.println("bmi �ʱ⿬�� ����");
		}
		
		try {
			name = dis.readUTF();
		}catch(Exception e1) {
			System.out.println("bmi �̸������͹ޱ� ����");
		}
		
		return name;
	}
	

	public BMI() {
		this.setBackground(Color.WHITE);
		compInit();
		eventInit();
	}// conductor end
}