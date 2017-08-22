package Collection;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//javafx example jar file  ..

public class ImageSliderAndTimerManual extends JFrame{
    JLabel labelpicture = new JLabel();
    Timer tm;
    int x = 0;
    
    //Images Path In Array
    
    String[] list = {
                      "empty.jpg",//0
                      "empty2.png",//1
                     // "D:/6���ڹ�_������_2��/�ǽ�img/c.jpg",//2
                    };
    //������
    public ImageSliderAndTimerManual(){
    	this.setTitle("Java SlideShow");
    	/* 	Frame�� Background �� �����¹�.
    		ContentPane
    	Frame���� 5�������� ���� �پ��ִ����·� ��������ִ�.
    	���߿��� ���� �ܰ����Ϳ��� glass�� , ����Ʈ��+�޴��� , ���̾�� �� , ��Ʈ�� , ������ �� �ִ�.
    	������ �������� ����Ʈ���� ������ .setBackground �� �÷��� ������. 
    	*/
    	
    	/*
    		Background �������� ��
    		Color
    	  
    	  
    	*/
    	
    	this.getContentPane().setBackground(Color.decode("#bdb67b"));
    	labelpicture.setBounds(40, 30, 700, 300);
        //Call The Function SetImageSize
        SetImageSize(0);
       
       //set a timer
        //Timer Ŭ���� �̿�-  ������ �Դϴ�. 
        //
     tm =  new Timer(1000,new ActionListener() { //delay�� 1000�̸� 1�� .
            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0; 
            }
        });
        
        add(labelpicture);
        tm.start();
        setLayout(null);
        setSize(800, 400);
        
   //     getContentPane().setBackground(Color.decode("#bdb67b"));
     //   this.setBackground(Color.decode("#bdb67b"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    //create a function to resize the image 
    public void SetImageSize(int i){
    	//�̹��� �����ܿ� ��ο� �����ִ� �̹������� �־ ����Ѵ�.
    	//img��� ��ü�� �����ܿ� ��ο� �����ִ� �̹������� get�Ͽ� �ְ�
    	//newimg �� ��ź�� ��Ų�� . ( String ���ϳ����� ���ڰ����γ���)
        ImageIcon icon = new ImageIcon(list[i]);
   //     System.out.println(icon.getIconWidth()); // �̹����� ���� �ȼ� ���α���
   //     System.out.println(icon.getIconHeight()); // �̹����� ���� �ȼ� ���α��� 
      
        Image img = icon.getImage();
      //������ ���̺��� ũ�⸦ 700,300���� ���Ѵ�� �������� �̹���ũ�⸦ �̿��°� �����.
        Image newImg = img.getScaledInstance(labelpicture.getWidth(), labelpicture.getHeight(), Image.SCALE_SMOOTH);
 //       System.out.println(labelpicture.getWidth());
 //       System.out.println(labelpicture.getHeight());
        //�̹��� �����ܿ� ������ �̹����� �ִ´�. ( �̹����� ���ڰ����γ���)
        ImageIcon newImc = new ImageIcon(newImg);
        
        //���̺� �������� ����Ѵ�.
        labelpicture.setIcon(newImc);
    }

public static void main(String[] args){ 
      
    new ImageSliderAndTimerManual();
}
}