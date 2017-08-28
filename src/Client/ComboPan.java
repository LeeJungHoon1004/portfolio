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

public class ComboPan extends JPanel{


	BasicShape parent;
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;

	// ----------------����
	private  TitledBorder tborder = new TitledBorder("");
	private Font font = new Font("����", Font.ITALIC, 30);

	private ComboPan self = this;

	//�г��� 
	private JPanel comboPanel = new JPanel(new BorderLayout()); //ū �κ� �޺��г�(������ ������ ��, �Ʒ��¹�ư)
	private JPanel combolistPanel = new JPanel(new FlowLayout()); //�߰��κ� �޺�����Ʈ�г�
	private JPanel waterPanel = new JPanel(new BorderLayout()); //ū �κ� �����г�(������ ������ ��, �Ʒ��¹�ư)
	private JPanel waterlistPanel = new JPanel(); //�߰� ���� ������ ���� �г�


	private JLabel combolabel = new JLabel("1. ������ ��ǥ�� �����ϼ���!");
	private JButton selectBt = new JButton("���ÿϷ� >_<");
	private JLabel waterchecklabel = new JLabel("2. ������ Ƚ���� üũ�غ�����! ");
	private JButton buttonUpload = new JButton("üũ�Ϸ� >_<"); // üũ�Ϸ� ��ư

	private JLabel goallabel = new JLabel("");
	private JLabel waterinformlabel = new JLabel("");


	private JComboBox combolist1;
	private JComboBox combolist2;
	private JComboBox combolist3;


	//�Ź�ư ����
	private JButton cupb1 = new JButton();
	private JButton cupb2 = new JButton();
	private JButton cupb3 = new JButton();

	private JButton cupb4 = new JButton();
	private JButton cupb5 = new JButton();
	private JButton cupb6 = new JButton();
	private JButton cupb7 = new JButton();
	private JButton cupb8 = new JButton();
	private JButton cupb9 = new JButton();
	private JButton cupb10 = new JButton();


	//�� �̹����� ������ ����
	private Image goalimg = new ImageIcon("��ǥ.jpg").getImage().getScaledInstance(330, 150,
			java.awt.Image.SCALE_SMOOTH); // 
	private Image waterinformimg = new ImageIcon("waterinform.jpg").getImage().getScaledInstance(300, 300,
			java.awt.Image.SCALE_SMOOTH); 


	private Image cupimage = new ImageIcon("empty2.png").getImage().getScaledInstance(50, 80,
			java.awt.Image.SCALE_SMOOTH); // �����̹���
	private Image fullcupimage = new ImageIcon("full2.png").getImage().getScaledInstance(50, 80,
			java.awt.Image.SCALE_SMOOTH); // ������ִ���

	private JPanel cupPan = new JPanel(new GridLayout(1, 5));

	private JPanel cupPan2 = new JPanel(new GridLayout(1, 5));

	private ImageIcon icongoal = new ImageIcon(goalimg);
	private ImageIcon iconwaterinform = new ImageIcon(waterinformimg);
	private ImageIcon icon1 = new ImageIcon(cupimage);

	private ImageIcon icon2 = new ImageIcon(cupimage);
	private ImageIcon icon3 = new ImageIcon(cupimage);
	private ImageIcon icon4 = new ImageIcon(cupimage);
	private ImageIcon icon5 = new ImageIcon(cupimage);
	private ImageIcon icon6 = new ImageIcon(cupimage);
	private ImageIcon icon7 = new ImageIcon(cupimage);
	private ImageIcon icon8 = new ImageIcon(cupimage);
	private ImageIcon icon9 = new ImageIcon(cupimage);
	private ImageIcon icon10 = new ImageIcon(cupimage);

	private boolean changecup1 = false;
	private boolean changecup2 = false;
	private boolean changecup3 = false;
	private boolean changecup4 = false;
	private boolean changecup5 = false;
	private boolean changecup6 = false;
	private boolean changecup7 = false;
	private boolean changecup8 = false;
	private boolean changecup9 = false;
	private boolean changecup10 = false;

	public void compInit() {


		this.add(comboPanel);
		this.add(waterPanel);

		comboPanel.setBackground(Color.white);
		waterPanel.setBackground(Color.white);
		waterlistPanel.setBackground(Color.WHITE);
		Object[] action = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�", 
				"4.�������� ����̿��ϱ� ", "5.����Ʈ  30���� 3��Ʈ",
				"6.�÷�ũ 1�� 3��Ʈ", "7.����ȸԱ�", "8.�����ϸ� �������ϱ�", 
				"9.�ڱ��� �ϴ������� 5��", "10.�Ͼ�� ������ ����" };

		
		combolist1 = new JComboBox();
		for (int i = 0; i < action.length; i++) {

			combolist1.addItem(action[i]);

		}
		combolist1.setPreferredSize(new Dimension(320, 60));
		combolist1.setEditable(false);

		Object[] action2 = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�",
				"4.�������� ����̿��ϱ� ", "5.����Ʈ  30���� 3��Ʈ",
				"6.�÷�ũ 1�� 3��Ʈ", "7.����ȸԱ�", "8.�����ϸ� �������ϱ�",
				"9.�ڱ��� �ϴ������� 5��", "10.�Ͼ�� ������ ����" };
		// JComboBox combolist2 = new JComboBox(action);
		combolist2 = new JComboBox();
		for (int i = 0; i < action.length; i++) {
			combolist2.addItem(action2[i]);
		}
		combolist2.setPreferredSize(new Dimension(320, 60));
		combolist2.setEditable(false);

		Object[] action3 = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�", 
				"4.�������� ����̿��ϱ� ", "5.����Ʈ  30���� 3��Ʈ",
				"6.�÷�ũ 1�� 3��Ʈ", "7.����ȸԱ�", "8.�����ϸ� �������ϱ�", 
				"9.�ڱ��� �ϴ������� 5��", "10.�Ͼ�� ������ ����" };
		// JComboBox combolist3 = new JComboBox(action);
		combolist3 = new JComboBox();
		for (int i = 0; i < action.length; i++) {
			combolist3.addItem(action3[i]);
		}
		combolist3.setPreferredSize(new Dimension(320, 60));
		combolist3.setEditable(false);

		this.add(comboPanel);
		this.comboPanel.add(combolabel,BorderLayout.NORTH);
		this.comboPanel.add(combolistPanel);

		combolistPanel.add(goallabel);
		combolistPanel.add(combolist1);
		combolistPanel.add(combolist2);
		combolistPanel.add(combolist3);


		this.goallabel.setIcon(icongoal);
		combolabel.setFont(font);
		waterchecklabel.setFont(font);
		this.comboPanel.add(selectBt, BorderLayout.SOUTH);
		combolistPanel.setBackground(Color.white);


		this.add(waterPanel);

		waterPanel.add(waterlistPanel,BorderLayout.CENTER);
		waterPanel.add(waterchecklabel, BorderLayout.NORTH);
		waterPanel.add(buttonUpload, BorderLayout.SOUTH);

		waterlistPanel.add(waterinformlabel);
		waterlistPanel.add(cupPan);
		waterlistPanel.add(cupPan2);


		this.waterinformlabel.setIcon(iconwaterinform);

		//		waterlistPanel.add(cupb1);
		//		waterlistPanel.add(cupb2);
		//		waterlistPanel.add(cupb3);
		//		waterlistPanel.add(cupb4);
		//		waterlistPanel.add(cupb5);
		//		waterlistPanel.add(cupb6);
		//		waterlistPanel.add(cupb7);
		//		waterlistPanel.add(cupb8);
		//		waterlistPanel.add(cupb9);
		//		waterlistPanel.add(cupb10);

		cupPan.setPreferredSize(new Dimension(250, 80));
		// cupPan.add(water_inform);

		cupPan.add(cupb1);

		cupPan.add(cupb2);
		cupPan.add(cupb3);
		cupPan.add(cupb4);
		cupPan.add(cupb5);

		cupPan2.setPreferredSize(new Dimension(250, 80));

		cupPan2.add(cupb6);
		cupPan2.add(cupb7);
		cupPan2.add(cupb8);
		cupPan2.add(cupb9);
		cupPan2.add(cupb10);

		//�źκ� �����ֱܳ�
		this.cupb1.setIcon(icon1);
		// this.cupb1.setPreferredSize(new Dimension(43, 72));
		this.cupb2.setIcon(icon2);
		// this.cupb2.setPreferredSize(new Dimension(43, 72));
		this.cupb3.setIcon(icon3);
		//this.cupb3.setPreferredSize(new Dimension(43, 72));
		this.cupb4.setIcon(icon4);
		//this.cupb4.setPreferredSize(new Dimension(43, 72));
		this.cupb5.setIcon(icon5);
		//this.cupb5.setPreferredSize(new Dimension(43, 72));
		this.cupb6.setIcon(icon6);
		//this.cupb6.setPreferredSize(new Dimension(43, 72));
		this.cupb7.setIcon(icon7);
		//this.cupb7.setPreferredSize(new Dimension(43, 72));
		this.cupb8.setIcon(icon8);
		//this.cupb8.setPreferredSize(new Dimension(43, 72));
		this.cupb9.setIcon(icon9);
		//this.cupb9.setPreferredSize(new Dimension(43, 72));
		this.cupb10.setIcon(icon10);
		//this.cupb10.setPreferredSize(new Dimension(43, 72));
	}
	public void eventInit() {


		//�Ź�ư �ν�

		cupb1.addMouseListener(new MouseAdapter() {


			// ���콺�� �������� �̺�Ʈ ó���Դϴ�.
			@Override
			public void mouseReleased(MouseEvent e) {
				changecup1 = !changecup1;
				if (changecup1 == true) {
					self.icon1.setImage(fullcupimage);
				} else {
					self.icon1.setImage(cupimage);

				}
				self.cupb1.setIcon(icon1);
			}

		});

		cupb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			// ���콺�� �������� �̺�Ʈ ó���Դϴ�.
			@Override
			public void mouseReleased(MouseEvent e) {
				changecup2 = !changecup2;
				if (changecup2 == true) {
					self.icon2.setImage(fullcupimage);
				} else {
					self.icon2.setImage(cupimage);

				}
				self.cupb2.setIcon(icon2);
			}

		});
		cupb3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup3 = !changecup3;
				if (changecup3 == true) {
					self.icon3.setImage(fullcupimage);
				} else {
					self.icon3.setImage(cupimage);

				}
				self.cupb3.setIcon(icon3);
			}

		});
		cupb4.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup4 = !changecup4;
				if (changecup4 == true) {
					self.icon4.setImage(fullcupimage);
				} else {
					self.icon4.setImage(cupimage);

				}
				self.cupb4.setIcon(icon4);
			}

		});

		cupb5.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup5 = !changecup5;
				if (changecup5 == true) {
					self.icon5.setImage(fullcupimage);
				} else {
					self.icon5.setImage(cupimage);

				}
				self.cupb5.setIcon(icon5);
			}

		});
		cupb6.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup6 = !changecup6;
				if (changecup6 == true) {
					self.icon6.setImage(fullcupimage);
				} else {
					self.icon6.setImage(cupimage);

				}
				self.cupb6.setIcon(icon6);
			}

		});
		cupb7.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup7 = !changecup7;
				if (changecup7 == true) {
					self.icon7.setImage(fullcupimage);
				} else {
					self.icon7.setImage(cupimage);

				}
				self.cupb7.setIcon(icon7);
			}

		});
		cupb8.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup8 = !changecup8;
				if (changecup8 == true) {
					self.icon8.setImage(fullcupimage);
				} else {
					self.icon8.setImage(cupimage);

				}
				self.cupb8.setIcon(icon8);
			}

		});
		cupb9.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup9 = !changecup9;
				if (changecup9 == true) {
					self.icon9.setImage(fullcupimage);
				} else {
					self.icon9.setImage(cupimage);

				}
				self.cupb9.setIcon(icon9);
			}

		});
		cupb10.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup10 = !changecup10;
				if (changecup10 == true) {
					self.icon10.setImage(fullcupimage);
				} else {
					self.icon10.setImage(cupimage);

				}
				self.cupb10.setIcon(icon10);
			}

		});

		selectBt.addActionListener(new ActionListener() {
			//���ÿϷ� ��ư �̺�Ʈó���Դϴ�.
			@Override
			public void actionPerformed(ActionEvent e) {

				// 2���� ���̽��� ������ . ���ÿϷ� ��ư�� ������

				// 1.�α����� �� ���¿��� ��ư�� �����°��.
				if (parent.getClient() != null) {

					int Question = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ� ? ",
							"����",JOptionPane.OK_OPTION);
					if (Question == 0) {
						JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�");
					} 


					else if(Question == 1) {
						JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.�ٽü������ּ���");
						return ;
					}


					// 1-1 ��ȿ���˻� combolist �� ��ϵ� ������Ʈ3������ ���� ��ȿ�� �˻�
					// a != b , a !=c , b !=c 3�������� ��� ����������쿡 ���ؼ��� ���� �����Ų��.
					if (combolist1.getSelectedIndex() != combolist2.getSelectedIndex()
							&& combolist1.getSelectedIndex() != combolist3.getSelectedIndex()
							&& combolist2.getSelectedIndex() != combolist3.getSelectedIndex()) {



						try {

							dis = parent.getDis();
							dos = parent.getDos();
							dos.writeUTF("�Ϸ��ǥ����");

							System.out.println(combolist1.getSelectedIndex());
							System.out.println(combolist2.getSelectedIndex());
							System.out.println(combolist3.getSelectedIndex());
							String list = combolist1.getSelectedIndex() + "," + combolist2.getSelectedIndex() + ","
									+ combolist3.getSelectedIndex();
							System.out.println(list);
							dos.writeUTF(list);

							System.out.println("�Ϸ��ǥ���� ����");

							System.out.println("combolist������ �߽� ����");




						} catch (Exception e1) {
							System.out.println("combolist������ �߽� ����.");
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "��ǥ�� �ߺ��˴ϴ�. �ٽ� �������ּ���.");
						return;
					}

				}

				// 2.�α����� �������� ��쿡�� ��ư�� �����°��
				else if (parent.getClient() == null) {
					JOptionPane.showMessageDialog(null, "�α��θ������ּ���");
					return;
				}

				
			}

		});


		buttonUpload.addActionListener(new ActionListener() {
			// üũ�Ϸ� ��ư�� ���� �̺�Ʈó���Դϴ�.
			@Override
			public void actionPerformed(ActionEvent arg0) {


				//�� /�ƴϿ� ó��.
				//	System.out.println(Boolean.valueOf(changecup1).toString());
				if (parent.getClient() != null) {

					int Question = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ� ? ",
							"����",JOptionPane.OK_OPTION);
					if (Question == 0) {
						JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�");
					} 


					else if(Question == 1) {
						JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.�ٽü������ּ���");
						return ;
					}

					//parent�� �ּҸ� �޾Ƽ� input,output ��Ʈ�� ����
					dis = parent.getDis();
					dos = parent.getDos();

					//����üũ�� ���� Ŀ�ǵ� �߽�.
					try {
						dos.writeUTF("����üũ");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String changeCup1 =Boolean.valueOf(changecup1).toString();
					String changeCup2 =Boolean.valueOf(changecup2).toString();
					String changeCup3 =Boolean.valueOf(changecup3).toString();
					String changeCup4 =Boolean.valueOf(changecup4).toString();
					String changeCup5 =Boolean.valueOf(changecup5).toString();
					String changeCup6 =Boolean.valueOf(changecup6).toString();
					String changeCup7 =Boolean.valueOf(changecup7).toString();
					String changeCup8 =Boolean.valueOf(changecup8).toString();
					String changeCup9 =Boolean.valueOf(changecup9).toString();
					String changeCup10 =Boolean.valueOf(changecup10).toString();


					String changeCupList = changeCup1 + "," +changeCup2+ ","+changeCup3+ ","
							+changeCup4+ ","+ changeCup5+ ","+ changeCup6+ 
							","+changeCup7+ ","+changeCup8+ ","+ changeCup9+ ","+changeCup10 ;
					System.out.println("Ŭ���̾�Ʈ���� ������ ��ưŬ���� ������changeCupList " + changeCupList);

					try {
						dos.writeUTF(changeCupList);
					} catch (IOException e) {

						e.printStackTrace();
					}



				}

			}
		});
	}

	public ComboPan() {

		this.setBackground(Color.WHITE);
		this.setSize(900,900);
		this.setLayout(new GridLayout(1,2));
		this.compInit();
		this.eventInit();


	}
}
