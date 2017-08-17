
package Client;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dailypan extends JPanel{
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	//----------------���� 
	
	private JComboBox combolist1 = new JComboBox();
	private JComboBox combolist2 = new JComboBox();
	private JComboBox combolist3 = new JComboBox();
	
	private JLabel today3 = new JLabel("������ ��ǥ�� 3�� �����ϼ���");
	private Font font = new Font("����", Font.ITALIC, 20);
	
	private JLabel waterchecklabel = new JLabel("������ Ƚ���� üũ�غ�����! ");
	
	private JButton buttonUpload = new JButton("üũ�Ϸ�"); //üũ�Ϸ� ��ư


	private JButton buttonselect = new JButton("���ÿϷ�"); //���ÿϷ� ��ư (�޺���)

	private Dailypan self = this;
	
	private boolean changecup1 =false;
	private boolean changecup2 =false;
	//------------------------

	private String result;
	
	
	

	private Image cupimage = new ImageIcon("empty2.png").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH); //�����̹���
	private Image fullcupimage = new ImageIcon("full2.png").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH); //������ִ���
	
	
	private Image water_effect = new ImageIcon("waterinform.jpg").getImage()
			.getScaledInstance(31, 47, java.awt.Image.SCALE_SMOOTH);

	private ImageIcon water_effect_icon = new ImageIcon(water_effect);
	private JLabel water_inform = new JLabel();

	private ImageIcon icon1 = new ImageIcon(cupimage);
	private ImageIcon icon2 = new ImageIcon(cupimage);
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

	private JPanel cupPan = new JPanel(new GridLayout(1,5));

	private JPanel cupPan2 = new JPanel(new GridLayout(1,5));



	public void comp() {

		this.setSize(800, 800);
		
		today3.setFont(font); //������ ��ǥ ����� ���̺� 
		waterchecklabel.setFont(font);
		cupPan.setPreferredSize(new Dimension(100, 350));
		cupPan.add(cupb1);

		cupPan.add(cupb2);
		cupPan.add(cupb3);
		cupPan.add(cupb4);
		cupPan.add(cupb5);


		cupPan2.setPreferredSize(new Dimension(100, 350));
		cupPan2.add(cupb6);
		cupPan2.add(cupb7);
		cupPan2.add(cupb8);
		cupPan2.add(cupb9);
		cupPan2.add(cupb10);
		
		
		this.water_inform.setIcon(water_effect_icon);

		//�� 1-10���� ������ �� �����ֱ� 
		this.cupb1.setIcon(icon1);
		this.cupb1.setPreferredSize(new Dimension(31, 47));
		this.cupb2.setIcon(icon2);
		this.cupb2.setPreferredSize(new Dimension(31, 47));
		this.cupb3.setIcon(icon1);
		this.cupb3.setPreferredSize(new Dimension(31, 47));
		this.cupb4.setIcon(icon1);
		this.cupb4.setPreferredSize(new Dimension(31, 47));
		this.cupb5.setIcon(icon1);
		this.cupb5.setPreferredSize(new Dimension(31, 47));
		this.cupb6.setIcon(icon1);
		this.cupb6.setPreferredSize(new Dimension(31, 47));
		this.cupb7.setIcon(icon1);
		this.cupb7.setPreferredSize(new Dimension(31, 47));
		this.cupb8.setIcon(icon1);
		this.cupb8.setPreferredSize(new Dimension(31, 47));
		this.cupb9.setIcon(icon1);
		this.cupb9.setPreferredSize(new Dimension(31, 47));
		this.cupb10.setIcon(icon1);
		this.cupb10.setPreferredSize(new Dimension(31, 47));

		
		//�׸������ �̿��Ͽ� ������Ʈ ��ġ��..
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		//�޺��ڽ� �迭�̿��Ͽ� ��� �ְ�, ��ġ 
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



		//		this.cupb1.setIcon(icon1);
		//		this.cupb1.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb1);
		//		
		//		this.cupb2.setIcon(icon1);
		//		this.cupb2.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb2);
		//		
		//		this.cupb3.setIcon(icon1);
		//		this.cupb3.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb3);
		//		
		//		
		//		this.cupb4.setIcon(icon1);
		//		this.cupb4.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb4);
		//		
		//		
		//		this.cupb5.setIcon(icon1);
		//		this.cupb5.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb5);
		//		
		//		
		//		this.cupb6.setIcon(icon1);
		//		this.cupb6.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb6);
		//		
		//		
		//		this.cupb7.setIcon(icon1);
		//		this.cupb7.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb7);
		//		
		//		
		//		this.cupb8.setIcon(icon1);
		//		this.cupb8.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb8);
		//		
		//		
		//		this.cupb9.setIcon(icon1);
		//		this.cupb9.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb9);
		//		
		//		
		//		this.cupb10.setIcon(icon1);
		//		this.cupb10.setPreferredSize(new Dimension(31, 47));
		//		this.add(cupb10);
		//		


		c.insets = new Insets(10,10,40,0); //����, �ð���� 

		c.gridy = 1; c.gridx = 1;
		this.add(today3,c);




		//2�� �����̳�. �޺��� ���� 
		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(10,10,30,0);

		c2.gridy = 2; c2.gridx = 1;
		this.add(combolist1,c2);
		c2.gridy = 3; c2.gridx = 1;
		this.add(combolist2,c2);
		c2.gridy = 4; c2.gridx =1;
		this.add(combolist3,c2);
		c2.gridy = 5; c2.gridx =2;
		this.add(buttonselect,c2);
		
		//3�� �����̳�, �� üũ�غ����� ���̺�

		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(10,10,30,0);


		c3.gridy = 6; c3.gridx = 1;
		this.add(waterchecklabel,c3);
		
		c3.gridy = 7; c3.gridx = 1;
		this.add(water_inform,c3);
	

		
		//�� �г� 
		GridBagConstraints c4 = new GridBagConstraints();
		c4.insets = new Insets(10,20,30,0);

		c4.gridy = 8; c4.gridx = 1;
		this.add(cupPan,c4);

		c4.gridy = 9; c4.gridx = 1;
		this.add(cupPan2,c4);


		c3.gridy = 10; c3.gridx =2;
		this.add(buttonUpload,c3);



		//		this.add(buttonClose);

	}
	
//	public void client(){
//		
//		try {
//			client = new Socket("127.0.0.1", 40000);
//			dos = new DataOutputStream(client.getOutputStream());
//			dis = new DataInputStream(client.getInputStream());
//			System.out.println("�ʱ⿬�Ἲ��");
//		} catch (Exception e1) {
//			System.out.println("�ʱ⿬�����");
//		}
//
//	}
	
	
	
	
	//�� 1�� ������ ���� �������� �ٲ�  
	public void event() {
		
		buttonselect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				
//				String selected1 = combolist1.getSelectedItem().toString();
//				System.out.println(selected1);
//				String selected2 = combolist1.getSelectedItem().toString();
//				String selected3 = combolist1.getSelectedItem().toString();
			
			
			
//			try{
//				dos.writeUTF("���ÿϷ�"); //����Ʈ ���ÿϷ�
//				dos.writeUTF(selected1);
//				dos.writeUTF(selected2);
//				dos.writeUTF(selected3);
//				System.out.println("�����ͺ����� ����! ");
//			} catch (Exception e1) {
//				System.out.println("������ ������ ����");
		//
//			}
			
//			try {
//				
//				 result = dis.readUTF();
//				if (result.equals("���ۼ���")) {
//						JOptionPane.showMessageDialog(null, "���� ����");
		//
//					} else if (result.equals("���۽���")) {
//						JOptionPane.showMessageDialog(null, "���ۿ� �����Ͽ����ϴ�.");
//					}
//					System.out.println("���� ����");
//			}catch (Exception e2) {
		//
//			}
			
			
			//return result;
			
				
					
				
				
			}
			
		});
		
		cupb1.addMouseListener(new MouseListener (){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			//���콺�� �������� �̺�Ʈ ó���Դϴ�.
			@Override
			public void mouseReleased(MouseEvent e) {
				changecup1 =! changecup1;
				if(changecup1 == true) {
					self.icon1.setImage(fullcupimage);
				}else {
					self.icon1.setImage(cupimage);
					
				}
				self.cupb1.setIcon(icon1);
				}
			
			
		});
		
		cupb2.addMouseListener(new MouseListener (){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			//���콺�� �������� �̺�Ʈ ó���Դϴ�.
			@Override
			public void mouseReleased(MouseEvent e) {
				changecup2 =! changecup2;
				if(changecup2 == true) {
					self.icon2.setImage(fullcupimage);
				}else {
					self.icon2.setImage(cupimage);
					
				}
				self.cupb2.setIcon(icon2);
				}
			
			
		});
		

//		cupb1.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup1 =! changecup1;
//				if(changecup1 == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb1.setIcon(icon1);
//				}
//
//			
//		});
//
//		cupb2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup2 =! changecup2;
//				if(changecup2 == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb2.setIcon(icon1);
//				}
//
//			
//		});
		
//		cupb3.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb3.setIcon(icon1);
//				}
//
//			
//		});
//		
//		cupb4.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb4.setIcon(icon1);
//				}
//
//			
//		});
//
//		cupb5.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb5.setIcon(icon1);
//				}
//
//			
//		});
//		cupb6.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb6.setIcon(icon1);
//				}
//
//			
//		});
//		cupb7.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb7.setIcon(icon1);
//				}
//
//			
//		});
//		cupb8.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb8.setIcon(icon1);
//				}
//
//			
//		});
//		cupb9.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb9.setIcon(icon1);
//				}
//
//			
//		});
//		cupb10.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//	
//
//				changecup =! changecup;
//				if(changecup == true) {
//					self.icon1.setImage(fullcupimage);
//				}else {
//					self.icon1.setImage(cupimage);
//					
//				}
//				self.cupb10.setIcon(icon1);
//				}
//
//			
//		});

	}
	
	


	public Dailypan() {


		this.setSize(900,900);
//		this.client();
		this.comp();
		this.event();

		
		this.setVisible(true);

	}



//	public static void main(String[] args) {
//
//		
//		JFrame f = new JFrame();
//		f.add(new Dailypan());
//		f.setSize(500,500);
//		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
//		f.setLocationRelativeTo(null);
//		f.setVisible(true);
//		
//		
//
//
//}
}

