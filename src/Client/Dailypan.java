package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

class TimeThread extends Thread {
	int time = Client.Dailypan.time;

	public TimeThread(int time) {

	}

	public void run(int time) {
		for (time = 60; time > 0; time--) {

			System.out.println(time);
			// String t = String.valueOf(time);
			// label_countNumber.setText(t);
			try {
				Thread.sleep(1000); // 1��
			} catch (Exception e) {
			}
		}
		JOptionPane.showMessageDialog(null, "Game Over!");
		System.exit(0);
	}

}

public class Dailypan extends JPanel {
	public static int time;

	BasicShape parent;
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;

	// ----------------����
	// ���ù�ưbuttonselect

	private JComboBox combolist1;
	private JComboBox combolist2;
	private JComboBox combolist3;

	private JLabel today3 = new JLabel("������ ��ǥ�� 3�� �����ϼ���");
	private Font font = new Font("����", Font.ITALIC, 20);
	private Font font2 = new Font("����", Font.BOLD, 20);
	private JLabel waterchecklabel = new JLabel("������ Ƚ���� üũ�غ�����! ");

	private JButton buttonUpload = new JButton("üũ�Ϸ�"); // üũ�Ϸ� ��ư

	private JButton buttonselect = new JButton("���ÿϷ�"); // ���ÿϷ� ��ư (�޺���)
	private JButton buttonConfirm = new JButton("������ ��ǥ�� ���غ�����");

	private JLabel confirm = new JLabel();

	private Dailypan self = this;

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

	// ------------------------

	private String result;

	private Image confirmimg = new ImageIcon("goal.jpg").getImage().getScaledInstance(300, 150,
			java.awt.Image.SCALE_SMOOTH); // �����̹���

	private Image water_effect = new ImageIcon("waterInform.jpg").getImage().getScaledInstance(500, 500,
			java.awt.Image.SCALE_SMOOTH);

	private Image cupimage = new ImageIcon("empty2.png").getImage().getScaledInstance(50, 80,
			java.awt.Image.SCALE_SMOOTH); // �����̹���
	private Image fullcupimage = new ImageIcon("full2.png").getImage().getScaledInstance(50, 80,
			java.awt.Image.SCALE_SMOOTH); // ������ִ���

	private Image water_Sign = new ImageIcon("waterSign.jpg").getImage().getScaledInstance(500, 500,
			java.awt.Image.SCALE_SMOOTH);

	private Image dreamImage = new ImageIcon("����.jpg").getImage().getScaledInstance(600, 200,
			java.awt.Image.SCALE_SMOOTH);

	private ImageIcon iconconfirm = new ImageIcon(confirmimg);
	private ImageIcon icondream = new ImageIcon(dreamImage);

	private ImageIcon iconwaterSign = new ImageIcon(water_Sign);
	private ImageIcon iconwater = new ImageIcon(water_effect);

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

	private JLabel water_SignLabel = new JLabel();
	private JLabel water_inform = new JLabel();
	private JLabel dream = new JLabel();

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

	private JPanel cupPan = new JPanel(new GridLayout(1, 5));

	private JPanel cupPan2 = new JPanel(new GridLayout(1, 5));

	public void comp() {

		this.setSize(800, 800);

		today3.setFont(font); // ������ ��ǥ ����� ���̺�
		waterchecklabel.setFont(font);
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

		// �� 1-10���� ������ �� �����ֱ�
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

		//

		// private Image water_effect = new ImageIcon("waterInform.jpg").getImage()
		// .getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
		//
		//
		//
		// private ImageIcon iconwater = new ImageIcon(water_effect);
		// image1 = new ImageIcon("img/img.jpg"); //�̹��� ���
		//
		// lb = new JLabel("�̹����� ����",image1,JLabel.CENTER);
		// lb.setVerticalTextPosition(JLabel.CENTER);
		// lb.setHorizontalTextPosition(JLabel.RIGHT);
		buttonConfirm.setPreferredSize(new Dimension(200, 50));
		this.confirm.setIcon(iconconfirm);
		this.confirm.setPreferredSize(new Dimension(300, 230));

		// private Image water_effect = new ImageIcon("waterInform.jpg").getImage()
		// .getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
		//
		//
		//
		// private ImageIcon iconwater = new ImageIcon(water_effect);
		// image1 = new ImageIcon("img/img.jpg"); //�̹��� ���
		//
		// lb = new JLabel("�̹����� ����",image1,JLabel.CENTER);
		// lb.setVerticalTextPosition(JLabel.CENTER);
		// lb.setHorizontalTextPosition(JLabel.RIGHT);

		this.dream.setIcon(icondream);

		this.water_inform.setIcon(iconwater);
		this.water_inform.setPreferredSize(new Dimension(500, 500));

		this.water_SignLabel.setIcon(iconwaterSign);
		this.water_inform.setPreferredSize(new Dimension(500, 500));
		// �׸������ �̿��Ͽ� ������Ʈ ��ġ��..
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// �޺��ڽ� �迭�̿��Ͽ� ��� �ְ�, ��ġ

		Object[] action = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�", 
				"4.�������� ����̿��ϱ� ", "5.����Ʈ  30���� 3��Ʈ",
				"6.�÷�ũ 1�� 3��Ʈ", "7.����ȸԱ�", "8.�����ϸ� �������ϱ�", 
				"9.�ڱ��� �ϴ������� 5��", "10.�Ͼ�� ������ ����" };

		// String [] action = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�",
		// "4.�������� ����̿��ϱ� ", "5.����Ʈ 30���� 3��Ʈ" , "6.�÷�ũ 1�� 3��Ʈ" ,"7.����ȸԱ�","8.�����ϸ� �������ϱ�"
		// ,"9.�ڱ��� �ϴ������� 5��","10.�Ͼ�� ������ ����"};
		// JComboBox combolist1 = new JComboBox(action);
		// combolist1.addItem(action);
		// addItem �������� ObjectŸ�� ���� �־���.
		combolist1 = new JComboBox();
		for (int i = 0; i < action.length; i++) {

			combolist1.addItem(action[i]);

		}
		combolist1.setPreferredSize(new Dimension(500, 50));
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
		combolist2.setPreferredSize(new Dimension(500, 50));
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
		combolist3.setPreferredSize(new Dimension(500, 50));
		combolist3.setEditable(false);

		c.insets = new Insets(0, 10, 30, 0); // ����, �ð����

		c.gridy = 0;
		c.gridx = 1;
		this.add(dream, c);

		c.gridy = 1;
		c.gridx = 1;
		this.add(today3, c);

		// 2�� �����̳�. �޺��� ����
		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(10, 10, 10, 0);

		c2.gridy = 2;
		c2.gridx = 1;
		this.add(combolist1, c2);
		c2.gridy = 3;
		c2.gridx = 1;
		this.add(combolist2, c2);
		c2.gridy = 4;
		c2.gridx = 1;
		this.add(combolist3, c2);
		c2.gridy = 5;
		c2.gridx = 2;
		this.add(buttonselect, c2);
		c2.gridy = 5;
		c2.gridx = 1;
		this.add(confirm, c2);
		c2.gridy = 6;
		c2.gridx = 1;
		this.add(buttonConfirm, c2);

		// c2.gridy = 6; c2.gridx =3;
		// this.add(ConfirmQ,c2);

		// 3�� �����̳�, �� üũ�غ����� ���̺�

		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(10, 10, 30, 0);

		c3.gridy = 7;
		c3.gridx = 1;
		this.add(waterchecklabel, c3);

		// �� �г�
		GridBagConstraints c4 = new GridBagConstraints();
		c4.insets = new Insets(10, 20, 30, 0);
		c4.gridy = 8;
		c4.gridx = 1;
		this.add(water_inform, c4);
		c4.gridy = 9;
		c4.gridx = 1;
		this.add(cupPan, c4);

		c4.gridy = 10;
		c4.gridx = 1;
		this.add(cupPan2, c4);

		c4.gridy = 11;
		c4.gridx = 1;
		this.add(buttonUpload, c4);

		c4.gridy = 12;
		c4.gridx = 1;
		this.add(water_SignLabel, c4);

		// this.add(buttonClose);

	}


	public void event() {
		//�޺����ý� ������ ���뺸����
		buttonselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				// questList [0]= combolist1.getSelectedIndex();
				// questList [1] =combolist2.getSelectedIndex();
				// questList [2] =combolist3.getSelectedIndex();

				// new TimeThread(time).start();

				// 2���� ���̽��� ������ . ���ÿϷ� ��ư�� ������

				// 1.�α����� �� ���¿��� ��ư�� �����°��.
				if (parent.getClient() != null) {

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

							// System.out.println(combolist1.getSelectedIndex());
							// System.out.println(combolist2.getSelectedIndex());
							// System.out.println(combolist3.getSelectedIndex());
							// dos.writeInt(combolist1.getSelectedIndex());
							// dos.writeInt(combolist2.getSelectedIndex());
							// dos.writeInt(combolist3.getSelectedIndex());
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

				// String selected1 = combolist1.getSelectedItem().toString();
				// System.out.println(selected1);
				// String selected2 = combolist1.getSelectedItem().toString();
				// String selected3 = combolist1.getSelectedItem().toString();

				// try{
				// dos.writeUTF("���ÿϷ�"); //����Ʈ ���ÿϷ�
				// dos.writeUTF(selected1);
				// dos.writeUTF(selected2);
				// dos.writeUTF(selected3);
				// System.out.println("�����ͺ����� ����! ");
				// } catch (Exception e1) {
				// System.out.println("������ ������ ����");
				//
				// }

				// try {
				//
				// result = dis.readUTF();
				// if (result.equals("���ۼ���")) {
				// JOptionPane.showMessageDialog(null, "���� ����");
				//
				// } else if (result.equals("���۽���")) {
				// JOptionPane.showMessageDialog(null, "���ۿ� �����Ͽ����ϴ�.");
				// }
				// System.out.println("���� ����");
				// }catch (Exception e2) {
				//
				// }

				// return result;

			}

		});


		// �� 1�� ������ ���� �������� �ٲ�
		buttonUpload.addActionListener(new ActionListener() {
			// üũ�Ϸ� ��ư�� ���� �̺�Ʈó���Դϴ�.
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int Question = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ� ? ",
						"����",JOptionPane.OK_CANCEL_OPTION);
				
				if (Question == 0) {
					JOptionPane.showConfirmDialog(null, "����Ǿ����ϴ�");
				} 
				
				//	System.out.println(Boolean.valueOf(changecup1).toString());
				if (parent.getClient() != null) {


					dis = parent.getDis();
					dos = parent.getDos();
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
					System.out.println(changeCupList);

					try {
						dos.writeUTF(changeCupList);
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
				
				else if (parent.getClient() == null) {
					JOptionPane.showMessageDialog(null, "�α��θ������ּ���");
					return;
				}

			}
		});




		buttonConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int Question = JOptionPane.showConfirmDialog(null, "������ ��ǥ 3���� ��� �� �ϼ̳���?");
				// int i = JOptionPane.showConfirmDialog(null, "Ȯ���Ѱ���", "��¥?",
				// JOptionPane.OK_CANCEL_OPTION);

				if (Question == 0) {
					JOptionPane.showConfirmDialog(null, "���� �����ϼ̾��! ���ο� ��ǥ�� ȭ����! ", "��¥?",
							JOptionPane.OK_CANCEL_OPTION);
				} else if (Question == 1) {
					JOptionPane.showConfirmDialog(null, "�̷�����..������ �������ƿ�! ", "��¥?", JOptionPane.OK_CANCEL_OPTION);
				}
				//

			}

		});

		cupb1.addMouseListener(new MouseListener() {
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
				changecup1 = !changecup1;
				if (changecup1 == true) {
					self.icon1.setImage(fullcupimage);
				} else {
					self.icon1.setImage(cupimage);

				}
				self.cupb1.setIcon(icon1);
			}

		});

		cupb2.addMouseListener(new MouseListener() {
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

		// cupb1.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		//
		//
		// changecup1 =! changecup1;
		// if(changecup1 == true) {
		// self.icon1.setImage(fullcupimage);
		// }else {
		// self.icon1.setImage(cupimage);
		//
		// }
		// self.cupb1.setIcon(icon1);
		// }
		//
		//
		// });
		//
	}

	public Dailypan(BasicShape parent) {

		this.parent = parent;

		// if(client != null) {
		// this.client = client;
		// this.dos= dos;
		// this.dis = dis;
		// }
		// else if(client ==null) {
		// try {
		// this.client = new Socket("192.168.53.4",40000);
		// this.dos
		//
		// }catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		this.setBackground(Color.white);
		this.setSize(900, 900);

		this.comp();
		this.event();

		this.setVisible(true);

	}

	// public static void main(String[] args) {
	//
	//
	// JFrame f = new JFrame();
	// f.add(new Dailypan());
	// f.setSize(500,500);
	// f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	// f.setLocationRelativeTo(null);
	// f.setVisible(true);
	//
	//
	//
	//
	// }
}