package Client;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//javafx example jar file  ..
public class ImageSlide extends JPanel {
	JLabel labelpicture = new JLabel();
	Timer tm;
	int x = 0;
	// Images Path In Array
	String[] list = { "C://Users//Administrator//Desktop//�����//KakaoTalk_20170808_111848677.jpg", // 0
			"C://Users//Administrator//Desktop//�����//KakaoTalk_20170808_111851622.jpg", // 1
			"C://Users//Administrator//Desktop//�����//KakaoTalk_20170808_111848043.jpg",// 2
	};

	// ������
	public ImageSlide() {
		/*
		 * Frame�� Background �� �����¹�. ContentPane Frame���� 5�������� ���� �پ��ִ����·� ��������ִ�. ���߿���
		 * 
		 * ���� �ܰ����Ϳ��� glass�� , ����Ʈ��+�޴��� , ���̾�� �� , ��Ʈ�� , ������ �� �ִ�. ������ �������� ����Ʈ���� ������
		 * 
		 * .setBackground �� �÷��� ������.
		 * 
		 */
		labelpicture.setBounds(38,25, 872, 461);
		// Call The Function SetImageSize
		SetImageSize(2);
		// set a timer
		// Timer Ŭ���� �̿�- ������ �Դϴ�.
		//
		tm = new Timer(1000, new ActionListener() { // delay��
			@Override
			public void actionPerformed(ActionEvent e) {
				SetImageSize(x);
				x += 1;
				if (x >= list.length)
					x = 0;
			}
		});
		add(labelpicture);
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
		// System.out.println(icon.getIconWidth()); // �̹����� ���� �ȼ� ���α���
		// System.out.println(icon.getIconHeight()); // �̹����� ���� �ȼ� ���α���
		Image img = icon.getImage();
		// ������ ���̺��� ũ�⸦ 700,300���� ���Ѵ�� �������� �̹���ũ�⸦ �̿��°� �����.
		Image newImg = img.getScaledInstance(labelpicture.getWidth(), labelpicture.getHeight(), Image.SCALE_SMOOTH);
		// System.out.println(labelpicture.getWidth());
		// System.out.println(labelpicture.getHeight());
		// �̹��� �����ܿ� ������ �̹����� �ִ´�. ( �̹����� ���ڰ����γ���)
		ImageIcon newImc = new ImageIcon(newImg);
		// ���̺� �������� ����Ѵ�.
		labelpicture.setIcon(newImc);
	}
}