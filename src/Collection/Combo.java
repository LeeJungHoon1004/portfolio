package Collection;




//�׳� ������ ���������� ��ǥ��( �޺��ڽ�����)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;




public class Combo extends JFrame implements ActionListener{

	
	
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;

	
	
	//------------����
	
	Container con;


	private Combo self = this;

	private TitledBorder tborder;



	private LineBorder bb = new LineBorder(Color.black,  2	, true);
	
	private JButton buttonUpload = new JButton("���ε�");

	private JButton buttonClose = new JButton("Ȩ����");
	private JButton buttonclear = new JButton("���ÿϷ�");


	private JPanel panelWest = new JPanel(new GridLayout(7,1)); //���̵�� 
	private JPanel panelNorth = new JPanel(); //Ÿ��Ʋ
	private JPanel panelCenter = new JPanel(new GridLayout(2,1)); // ����-����6��
	private JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private JPanel panelCombo = new JPanel(new GridLayout(2,1));

	private JPanel Combo3 = new JPanel(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints(); //�޺�����Ʈ �ִ� ���̾ƿ�



	private JPanel panelwater = new JPanel(new GridLayout(2,1));

	private JPanel waterlinePan = new JPanel(new GridBagLayout());
	GridBagConstraints c2 = new GridBagConstraints();


	private JLabel today3 = new JLabel("������ ��ǥ�� 3�� �����ϼ���");
	private JLabel waterchecklabel = new JLabel("������ Ƚ���� üũ�غ�����");

	private JButton fullcup = new JButton(new ImageIcon("full2.PNG"));

	

	//�ſ� �̹����ֱ� 
	private Image cupimage = new ImageIcon("empty.jpg").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon icon1 = new ImageIcon(cupimage);
	private JButton cupb1 = new JButton();
	private JButton cupb2= new JButton();
	private JButton cupb3 = new JButton();

	private JButton cupb4 = new JButton();
	private JButton cupb5 = new JButton();
	private JButton cupb6 = new JButton();
	private JButton cupb7 = new JButton();
	private JButton cupb8 = new JButton();
	private JButton cupb9 = new JButton();
	private JButton cupb10 = new JButton();




	public void compInit(){

	
		this.cupb1.setIcon(icon1);
		this.cupb1.setPreferredSize(new Dimension(31, 47));
		this.add(cupb1);
		
		this.cupb2.setIcon(icon1);
		this.cupb2.setPreferredSize(new Dimension(31, 47));
		this.add(cupb2);
		
		this.cupb3.setIcon(icon1);
		this.cupb3.setPreferredSize(new Dimension(31, 47));
		this.add(cupb3);
		
		
		this.cupb4.setIcon(icon1);
		this.cupb4.setPreferredSize(new Dimension(31, 47));
		this.add(cupb4);
		
		
		this.cupb5.setIcon(icon1);
		this.cupb5.setPreferredSize(new Dimension(31, 47));
		this.add(cupb5);
		
		
		this.cupb6.setIcon(icon1);
		this.cupb6.setPreferredSize(new Dimension(31, 47));
		this.add(cupb6);
		
		
		this.cupb7.setIcon(icon1);
		this.cupb7.setPreferredSize(new Dimension(31, 47));
		this.add(cupb7);
		
		
		this.cupb8.setIcon(icon1);
		this.cupb8.setPreferredSize(new Dimension(31, 47));
		this.add(cupb8);
		
		
		this.cupb9.setIcon(icon1);
		this.cupb9.setPreferredSize(new Dimension(31, 47));
		this.add(cupb9);
		
		
		this.cupb10.setIcon(icon1);
		this.cupb10.setPreferredSize(new Dimension(31, 47));
		this.add(cupb10);
		
		
		
		



		today3.setBorder(tborder);



		panelCenter.add(panelCombo);

		String[] action = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�", 
				"4.�������� ����̿��ϱ� ", "5.����Ʈ  30���� 3��Ʈ" , "6.�÷�ũ 1�� 3��Ʈ" ,"7.����ȸԱ�","8.�����ϸ� �������ϱ�"
				,"9.�ڱ��� �ϴ������� 5��","10.�Ͼ�� ������ ����"};

		JComboBox combolist1 = new JComboBox(action);

		combolist1.addItem(action);
		combolist1.setEditable(false);



		JComboBox combolist2 = new JComboBox(action);
		combolist2.addItem(action);
		combolist2.setEditable(false);

		JComboBox combolist3 = new JComboBox(action);
		combolist3.addItem(action);
		combolist3.setEditable(false);

		panelCenter.add(panelCombo,BorderLayout.NORTH);
		panelCenter.add(panelwater,BorderLayout.SOUTH);

		panelCombo.add(today3);
		panelCombo.add(Combo3);//�� �׸��巹�̾ƿ�(2,1)�� �׸���鷹�̾ƿ� ����



		c.insets = new Insets(10,20,0,0); //����, �ð���� 

		c.gridy = 1; c.gridx = 1;
		Combo3.add(combolist1,c);
		c.gridy = 2; c.gridx = 1;
		Combo3.add(combolist2,c);
		c.gridy = 3; c.gridx =1;
		Combo3.add(combolist3,c);
		c.gridy = 4; c.gridx =1;
		Combo3.add(buttonclear,c);

		//		panelCombo.add(combolist1);
		//		panelCombo.add(combolist2);
		//		panelCombo.add(combolist3);

		panelwater.add(waterchecklabel);
		panelwater.add(waterlinePan);
		//		waterlinePan.add(cupb1);
		//		waterlinePan.add(cupb2);
		//		waterlinePan.add(cupb3);
		//		waterlinePan.add(cupb4);
		//		waterlinePan.add(cupb5);



		//		panelwater.add(cupb1);
		//		
		//		panelwater.add(cupb2);
		//		panelwater.add(cupb3);
		//		panelwater.add(cupb4);
		//		panelwater.add(cupb5);


		c2.insets = new Insets(10,10,0,0); //����, �ð���� 

		c2.gridy = 1; c2.gridx = 1;
		waterlinePan.add(cupb1,c2);
		c2.gridy = 1; c2.gridx = 2;
		waterlinePan.add(cupb2,c2);
		c2.gridy = 1; c2.gridx =3;
		waterlinePan.add(cupb3,c2);
		c2.gridy = 1; c2.gridx =4;
		waterlinePan.add(cupb4,c2);
		c2.gridy = 1; c2.gridx =5;
		waterlinePan.add(cupb5,c2); //���� 5��
		
		c2.gridy = 2; c2.gridx =1;
		waterlinePan.add(cupb6,c2);
		c2.gridy = 2; c2.gridx =2;
		waterlinePan.add(cupb7,c2);
		c2.gridy = 2; c2.gridx =3;
		waterlinePan.add(cupb8,c2);
		c2.gridy = 2; c2.gridx =4;
		waterlinePan.add(cupb9,c2);
		c2.gridy = 2; c2.gridx =5;
		waterlinePan.add(cupb10,c2);
		
		



		

		this.panelSouth.add(buttonUpload);

		this.panelSouth.add(buttonClose);




		this.add(panelWest, BorderLayout.WEST);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNorth , BorderLayout.NORTH);
		this.add(panelSouth , BorderLayout.SOUTH);



		setVisible(true);



	}

	void init(){
		cupb1.setIconTextGap(1);
		fullcup.setIconTextGap(1);
		con=this.getContentPane();
		con.add("Center",cupb1);
	}

	void start(){
		cupb1.addActionListener(this);
		fullcup.addActionListener(this);

	}
	public void eventInit(){




		cupb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((e.getSource()).equals(cupb1)){
					con.removeAll();
					con.add(fullcup);
					fullcup.updateUI();
				}
				else{
					con.removeAll();
					con.add(cupb1);
					cupb1.updateUI();
				}
			}

		});

		//		buttonclear.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				 String msg = buttonclear.getText();
		//	               if(JOptionPane.showConfirmDialog(null, msg, "���ÿϷ�!",  JOptionPane.OK_CANCEL_OPTION)==0) {
		//	                  //System.out.println("Ȯ��");
		//				
		//			}
		//			
		//		}
		//			
		//		});

		this.buttonClose.addActionListener(new ActionListener(){

			//��ưNo�� ���������� �̺�Ʈó��
			@Override
			public void actionPerformed(ActionEvent e) {

				Combo.this.dispose();

			}
		});


		//���ε��ư ������
		//		buttonUpload.addActionListener(new ActionListener(){
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				//			self.compInit().combolist1.getSelectedItem().toString();
		//
		//			}
		//
		//		});
	}




	public Combo(){




		this.setSize(800,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(Combo.EXIT_ON_CLOSE);
		this.init();
		this.start();
		this.compInit();
		this.eventInit();

		this.setVisible(true);




	}

	public static void main(String[] args) {
		new Combo();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}



}