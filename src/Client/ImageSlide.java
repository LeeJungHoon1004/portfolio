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
	String[] list = { "C://Users//Administrator//Desktop//찌르레기//KakaoTalk_20170808_111848677.jpg", // 0
			"C://Users//Administrator//Desktop//찌르레기//KakaoTalk_20170808_111851622.jpg", // 1
			"C://Users//Administrator//Desktop//찌르레기//KakaoTalk_20170808_111848043.jpg",// 2
	};

	// 생성자
	public ImageSlide() {
		/*
		 * Frame에 Background 색 입히는법. ContentPane Frame에는 5계층으로 팬이 붙어있는형태로 만들어져있다. 그중에서
		 * 
		 * 가장 외곽부터에는 glass층 , 컨텐트팬+메뉴바 , 레이어드 팬 , 루트팬 , 프레임 이 있다. 배경색을 입힐때는 컨텐트팬을 꺼내서
		 * 
		 * .setBackground 에 컬러를 입힌다.
		 * 
		 */
		labelpicture.setBounds(38,25, 872, 461);
		// Call The Function SetImageSize
		SetImageSize(2);
		// set a timer
		// Timer 클래스 이용- 쓰레드 입니다.
		//
		tm = new Timer(1000, new ActionListener() { // delay값
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
		// 이미지 아이콘에 경로에 잡혀있는 이미지들을 넣어서 등록한다.
		// img라는 객체에 아이콘에 경로에 잡혀있는 이미지들을 get하여 넣고
		// newimg 로 재탄생 시킨다 . ( String 파일네임을 인자값으로넣음)
		ImageIcon icon = new ImageIcon(list[i]);
		// System.out.println(icon.getIconWidth()); // 이미지의 실제 픽셀 가로길이
		// System.out.println(icon.getIconHeight()); // 이미지의 실제 픽셀 세로길이
		Image img = icon.getImage();
		// 위에서 레이블의 크기를 700,300으로 정한대로 아이콘의 이미지크기를 이에맞게 등록함.
		Image newImg = img.getScaledInstance(labelpicture.getWidth(), labelpicture.getHeight(), Image.SCALE_SMOOTH);
		// System.out.println(labelpicture.getWidth());
		// System.out.println(labelpicture.getHeight());
		// 이미지 아이콘에 수정된 이미지를 넣는다. ( 이미지를 인자값으로넣음)
		ImageIcon newImc = new ImageIcon(newImg);
		// 레이블에 아이콘을 등록한다.
		labelpicture.setIcon(newImc);
	}
}