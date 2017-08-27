package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PlanPan extends JPanel {
	BasicShape parent;

	private JComboBox combolist1;
	private JComboBox combolist2;
	private JComboBox combolist3;

	private  TitledBorder tborder = new TitledBorder("");

	private JLabel j1 = new JLabel("Ķ����");
	private JLabel j2 = new JLabel("bmi");
	private JLabel j3 = new JLabel("�޺�");
	private JLabel j4 = new JLabel("����");

	private JPanel mainPanel = new JPanel(new GridLayout(3,1));

	private JPanel firstPanel = new JPanel(); //Ķ���� �ִ��г�
	private JPanel secondPanel = new JPanel(); //bmi�� �޺� �г�
	private JPanel thirdPanel = new JPanel(); //���� �ִ� �г�


	//--�޺�����Ʈ




	//---bmi
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



	public void comp() {
		//�г� ��ġ

				this.add(mainPanel);
		//�гε鿡 ������Ʈ �ֱ�

		this.mainPanel.add(firstPanel);
		this.mainPanel.add(secondPanel);
		this.mainPanel.add(thirdPanel);

		this.firstPanel.add(j1);
		this.firstPanel.setBorder(tborder);
		this.secondPanel.add(j2);
		this.secondPanel.setBorder(tborder);
		this.thirdPanel.add(j4);
		this.thirdPanel.setBorder(tborder);

		//�޺�
		

		secondPanel.setLayout(null);

		Object[] action = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�",
				"4.�������� ����̿��ϱ� ", "5.����Ʈ  30���� 3��Ʈ",
				"6.�÷�ũ 1�� 3��Ʈ", "7.����ȸԱ�", "8.�����ϸ� �������ϱ�",
				"9.�ڱ��� �ϴ������� 5��", "10.�Ͼ�� ������ ����" };
		combolist1 = new JComboBox();
		for (int i = 0; i < action.length; i++) {

			combolist1.addItem(action[i]);

		}
		combolist1.setPreferredSize(new Dimension(150, 50));
		combolist1.setEditable(false);
		combolist1.setBounds(200, 100, 10, 10);



		secondPanel.add(combolist1);

		//bmi ������Ʈ��ġ




		thirdPanel.setLayout(null);


		
		thirdPanel.add(imgResult);
		thirdPanel.add(stature);
		thirdPanel.add(inputStature);
		thirdPanel.add(weight);
		thirdPanel.add(inputWeight);
		thirdPanel.add(gender);
		group.add(male);
		group.add(female);
		radioPan.setBackground(Color.white);
		radioPan.add(male);
		radioPan.add(female);
		thirdPanel.add(radioPan);

		thirdPanel.add(resultButton);

		imgResult.setBounds(65, 76, 800, 400);	
		stature.setBounds(112, 500, 44, 40);
		inputStature.setBounds(171,500, 255, 40);
		weight.setBounds(492,500, 56, 40);
		inputWeight.setBounds(562,500,255, 40);
		gender.setBounds(112,550, 60, 50);
		radioPan.setBounds(178,550, 252, 50);
		resultButton.setBounds(670, 560, 84, 40);


		









		//�� �гο� ȭ��Ʈ���ֱ�
		this.mainPanel.setBackground(Color.WHITE);
		this.firstPanel.setBackground(Color.WHITE);
		this.secondPanel.setBackground(Color.WHITE);
		this.thirdPanel.setBackground(Color.WHITE);
	}
	public void event() {

	}

	public PlanPan(BasicShape parent) { //Ŭ���̾�Ʈ���� BasicShape�� ������ִ� ������.
		this.parent = parent;

		this.setBackground(Color.white);
		this.setSize(900, 900);
		this.comp();
		this.event();
		this.setLayout(new FlowLayout());
		this.setVisible(true);
	}
	public PlanPan() { // �������� �����ϴ� ������.


		this.setBackground(Color.white);
		this.setSize(900, 900);
		this.comp();
		this.event();
		this.setVisible(true);
	}


}
