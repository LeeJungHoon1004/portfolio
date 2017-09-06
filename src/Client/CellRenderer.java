package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Server.FileList;

public class CellRenderer extends DefaultListCellRenderer {

//	private ArrayList<FileList> fl;
	//private FileSystemView fileSystemView;
	private FileList fl;
	public CellRenderer() {
		// this.fl = fl;

		this.setOpaque(true);
		this.setPreferredSize(new Dimension(200, 80));
		this.setIconTextGap(5);

	}

	public JPanel getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{  
		
		//������..
		//�����𸣰ڴµ� value�� ��̸���Ʈ<���ϸ���Ʈ>�� �ϸ� value�� �ڷ����� FileList�� �ȵȴٰ� ����������
		//Ŭ���� ������ �ڵ忡�� ������� fl�� FileList�� �����ϰ�
		//FileList�� �ٿ�ĳ�����ϰ����غ��ϱ� ������ѵ� �����䳪��..
		//�����ʿ��� �����͸� ����������� ���´�. 
		String path = "C:/4W/PictureBoardPan";
		JPanel pan  = new JPanel(new GridLayout(1,4));
		JLabel label = new JLabel();
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
//		fl= (ArrayList<FileList>)value; //���⼭ value �� Server.FileList �� �����µ� ��̸���Ʈ�� ĳ���þȵȴٰ���.
		
		fl = (FileList)value;
		
		ImageIcon icon = new ImageIcon(path + "/" + fl.getFileName());
		Image originImg = icon.getImage();
		Image changedImg= originImg.getScaledInstance(150, 80, Image.SCALE_SMOOTH );
		ImageIcon image = new ImageIcon(changedImg);
		
		label.setIcon(image);
		label1.setText(fl.getTitle());
		label2.setText(fl.getId());
		label3.setText(Integer.toString(index+1));
		
		pan.add(label,BorderLayout.WEST);
		pan.add(label1,BorderLayout.CENTER);
		pan.add(label2,BorderLayout.CENTER);
		pan.add(label3,BorderLayout.EAST);
		
    if (isSelected) {
    	pan.setBackground(Color.GRAY);
    	pan.setForeground(Color.WHITE);
	} else {
		pan.setBackground(Color.WHITE);
		pan.setForeground(Color.BLACK);
	}

    return pan;
	}
}
