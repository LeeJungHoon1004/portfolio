package Collection;

//�޺��ڽ� ���� ���� ������ ��ǥ��
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class Combobox extends JFrame {
	private Container cp = this.getContentPane();
	private JTextArea a = new JTextArea();
	private JButton id1 = new JButton("ID:");
	private JButton id2 = new JButton();
	private JButton pw1 = new JButton("PW:");
	private JButton pw2 = new JButton();
	private JButton membership = new JButton("ȸ������");
	private JButton in = new JButton("�α���");

	private JPanel paneltest = new JPanel();

	private JLabel title = new JLabel("Today's Target", JLabel.CENTER);
	private Font font = new Font("����", Font.ITALIC, 30);

	private JLabel firstQ = new JLabel(" [ Pick up ! ]�� ���Ѿ߸� �ϴ� ������ ��ǥ 3������ ������ ����ּ���.");

	private JLabel secondQ = new JLabel("[Check list] ���� �Ϸ� �� ���׳���? ");
	private JLabel watercheck = new JLabel("[water time] ������ Ƚ���� üũ�غ����� !");

	private ImageIcon cupIcon;
	private JPanel waterpanel = new JPanel(new GridLayout(10, 1));
	JLabel waterLabel = new JLabel(cupIcon);

	private JButton wc1 = new JButton();
	private JButton wc2 = new JButton();
	private JButton wc3 = new JButton();

	private JButton wc4 = new JButton();
	private JButton wc5 = new JButton();
	private JButton wc6 = new JButton();
	private JButton wc7 = new JButton();
	private JButton wc8 = new JButton();
	private JButton wc9 = new JButton();
	private JButton wc10 = new JButton();

	private JLabel loginshpe = new JLabel();
	private JLabel category = new JLabel();
	private JLabel target = new JLabel();
	private JLabel water = new JLabel();

	private JRadioButton ch1 = new JRadioButton("����");
	private JRadioButton ch2 = new JRadioButton("����");

	private TitledBorder tborder;

	public void comp() {

		setLayout(null);

		tborder = new TitledBorder("");
		tborder.setTitlePosition(TitledBorder.ABOVE_TOP);// ������ ��ġ�� Ÿ��Ʋ�� ��Ÿ���ִ�
															// ����...
		tborder.setTitleJustification(TitledBorder.CENTER);// �ڸ������� ����� ����...
		title.setBorder(tborder);

		loginshpe.setBorder(tborder);
		category.setBorder(tborder);

		target.setBorder(tborder);
		water.setBorder(tborder);

		title.setFont(font);
		firstQ.setFont(font);
		secondQ.setFont(font);
		watercheck.setFont(font);

		add(title);
		add(loginshpe);
		add(category);

		add(target);

		add(water);

//		add(firstQ);
		add(watercheck);

		String[] action = { "1.������� �������� �̿��ϱ� ", "2.�30�� �ϱ�", "3.�Ͼ�� ��Ʈ��Ī �ϱ�", "4.�������� ����̿��ϱ� ", "5.����Ʈ  30���� 3��Ʈ",
				"6.�÷�ũ 1�� 3��Ʈ", "7.����ȸԱ�", "8.�����ϸ� �������ϱ�", "9.�ڱ��� �ϴ������� 5��", "10.�Ͼ�� ������ ����" };

		JComboBox combolist1 = new JComboBox(action);
		combolist1.addItem(action);

		combolist1.setEditable(false);

		System.out.println();

		JComboBox combolist2 = new JComboBox(action);
		combolist2.addItem(action);
		combolist2.setEditable(false);

		JComboBox combolist3 = new JComboBox(action);
		combolist3.addItem(action);
		combolist3.setEditable(false);

		target.setLayout(null);
		target.add(firstQ);
		target.add(secondQ);
		target.add(combolist1);
		target.add(combolist2);
		target.add(combolist3);
		target.add(ch1);
		target.add(ch2);

		water.setLayout(null);
		water.add(waterpanel);

		// waterpanel.add(wc1);
		// waterpanel.add(wc2);
		// waterpanel.add(wc3);
		// waterpanel.add(wc4);
		// waterpanel.add(wc5);
		// waterpanel.add(wc6);
		// waterpanel.add(wc7);
		// waterpanel.add(wc8);
		// waterpanel.add(wc9);
		// waterpanel.add(wc10);

		// ImageIcon ic = new ImageIcon("./raw/taehee1.png");
		// JLabel lbImage1 = new JLabel(ic);
		//
		// frm.add(lbImage1);
		// frm.setVisible(true);
		// frm.setBounds(10, 10, 800, 600);
		//
		// frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cupIcon = new ImageIcon("empty.jpg");

		combolist1.setBounds(40, 60, 500, 50);
		combolist2.setBounds(40, 130, 500, 50);
		combolist3.setBounds(40, 200, 500, 50);

		firstQ.setBounds(100, 20, 1000, 30);
		secondQ.setBounds(100, 260, 1000, 30);
		ch1.setBounds(100, 300, 1000, 30);
		ch2.setBounds(150, 300, 1000, 30);
		watercheck.setBounds(400, 650, 1000, 30);

		title.setBounds(370, 60, 1090, 80);
		loginshpe.setBounds(30, 120, 310, 280);
		category.setBounds(30, 420, 310, 520);
		target.setBounds(370, 160, 1090, 460);
		water.setBounds(370, 640, 1090, 320);

		waterpanel.setBounds(370, 740, 1090, 320);

		id1.setBounds(40, 160, 100, 60);
		id2.setBounds(150, 160, 170, 60);
		pw1.setBounds(40, 240, 100, 60);
		pw2.setBounds(150, 240, 170, 60);
		membership.setBounds(40, 310, 100, 60);
		in.setBounds(150, 310, 100, 60);

		add(id1);
		add(id2);
		add(pw1);
		add(pw2);
		add(membership);
		add(in);

	}

	public Combobox() {
		setTitle("��ǥ�׽�Ʈ");
		setSize(1600, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp.setBackground(Color.WHITE);
		comp();
		setVisible(true);

		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		//// if(e.getSource()==bt){ JOptionPane }

	}

	public static void main(String[] args) {
//		try {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				if ("Nimbus".equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (Exception e) {
//
//		}

		new Combobox();
	}// main end
}