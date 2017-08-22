package Client;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//javafx example jar file  ..
public class ImageSlide extends JPanel {
	JLabel labelpicture = new JLabel();
	JLabel labelpicture2 = new JLabel();
	JLabel labelpicture3 = new JLabel();
	Timer tm;
	int x = 0;
	// Images Path In Array
	String[] list = { "img8.jpg", // 0
			"img18.jpg", // 1
			"img14.jpg"// 2
	};
	
	String[] list2 = {"img13.jpg","img17.jpg","img20.jpg"};
	String[] list3 = {"img19.jpg","img1.jpg","img4.jpg"};
	// ������
	public ImageSlide() {
		this.setBackground(Color.WHITE);
		/*
		 * Frame�� Background �� �����¹�. ContentPane Frame���� 5�������� ���� �پ��ִ����·� ��������ִ�. ���߿���
		 * 
		 * ���� �ܰ����Ϳ��� glass�� , ����Ʈ��+�޴��� , ���̾�� �� , ��Ʈ�� , ������ �� �ִ�. ������ �������� ����Ʈ���� ������
		 * 
		 * .setBackground �� �÷��� ������.
		 * 
		 */
		labelpicture.setBounds(20,25, 500, 850);
		labelpicture2.setBounds(530, 25, 420, 500);
		labelpicture3.setBounds(530, 530, 420, 345);
		// Call The Function SetImageSize
		SetImageSize(2);
		// set a timer
		// Timer Ŭ���� �̿�- ������ �Դϴ�.
		//
		
		tm = new Timer(1500, new ActionListener() { // delay��
			@Override
			public void actionPerformed(ActionEvent e) {
				SetImageSize(x);
				x += 1;
				if (x >= list.length)
					x = 0;
				if (x >= list2.length)
					x = 0;
				if (x >= list3.length)
					x = 0;
			}
			
		});
		add(labelpicture);
		add(labelpicture2);
		add(labelpicture3);
		tm.start();
		setLayout(null);
		// getContentPane().setBackground(Color.decode("#bdb67b"));
		// this.setBackground(Color.decode("#bdb67b"));
	}

	// create a function to resize the image
	public void SetImageSize(int i) {
		// �̹��� �����ܿ� ��ο� �����ִ� �̹������� �־ ����Ѵ�.
		// img��� ��ü�� �����ܿ� ��ο� �����ִ� �̹������� get�Ͽ� �ְ�
		// newimg �� ��ź�� ��Ų�� . ( String ���ϳ����� ���ڰ����γ���)
		ImageIcon icon = new ImageIcon(list[i]);
		ImageIcon icon2 = new ImageIcon(list2[i]);
		ImageIcon icon3 = new ImageIcon(list3[i]);
		// System.out.println(icon.getIconWidth()); // �̹����� ���� �ȼ� ���α���
		// System.out.println(icon.getIconHeight()); // �̹����� ���� �ȼ� ���α���
		Image img = icon.getImage();
		Image img2 = icon2.getImage();
		Image img3 = icon3.getImage();
		// ������ ���̺��� ũ�⸦ 700,300���� ���Ѵ�� �������� �̹���ũ�⸦ �̿��°� �����.
		Image newImg = img.getScaledInstance(labelpicture.getWidth(), labelpicture.getHeight(), Image.SCALE_SMOOTH);
		Image newImg2 = img2.getScaledInstance(labelpicture2.getWidth(), labelpicture2.getHeight(), Image.SCALE_SMOOTH);
		Image newImg3 = img3.getScaledInstance(labelpicture3.getWidth(), labelpicture3.getHeight(), Image.SCALE_SMOOTH);
		// System.out.println(labelpicture.getWidth());
		// System.out.println(labelpicture.getHeight());
		// �̹��� �����ܿ� ������ �̹����� �ִ´�. ( �̹����� ���ڰ����γ���)
		ImageIcon newImc = new ImageIcon(newImg);
		ImageIcon newImc2 = new ImageIcon(newImg2);
		ImageIcon newImc3 = new ImageIcon(newImg3);
		// ���̺� �������� ����Ѵ�.
		labelpicture.setIcon(newImc);
		labelpicture2.setIcon(newImc2);
		labelpicture3.setIcon(newImc3);
	}
}