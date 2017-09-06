package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{  
		
		//������..
		//�����𸣰ڴµ� value�� ��̸���Ʈ<���ϸ���Ʈ>�� �ϸ� value�� �ڷ����� FileList�� �ȵȴٰ� ����������
		//Ŭ���� ������ �ڵ忡�� ������� fl�� FileList�� �����ϰ�
		//FileList�� �ٿ�ĳ�����ϰ����غ��ϱ� ������ѵ� �����䳪��..
		//�����ʿ��� �����͸� ����������� ���´�. 
		String path = "C:/4W/PictureBoardPan";
		JLabel label = new JLabel();
		JLabel lb = new JLabel();
		JPanel pan = new JPanel();
//		fl= (ArrayList<FileList>)value; //���⼭ value �� Server.FileList �� �����µ� ��̸���Ʈ�� ĳ���þȵȴٰ���.
//    label.setIcon(new ImageIcon(path+"/"+fl.get(index).getFileName()));
//    label.setText(fl.get(index).getTitle());
		
		fl = (FileList)value;
		
		ImageIcon icon = new ImageIcon(path + "/" + fl.getFileName());
		Image originImg = icon.getImage();
		Image changedImg= originImg.getScaledInstance(150, 80, Image.SCALE_SMOOTH );
		ImageIcon image = new ImageIcon(changedImg);
		
		
		lb.setText(Integer.toString(index+1)+"|");
		label.setIcon(image);
		label.setText("\t" +fl.getTitle()+ "\t\t"+ fl.getId()+ "\t\t\t\t\t" + "|" + Integer.toString(index+1) );
		
		
		//pan.add(lb,BorderLayout.WEST);
		//pan.add(label,BorderLayout.EAST);
		
    if (isSelected) {
    	pan.setBackground(Color.GRAY);
    	pan.setForeground(Color.WHITE);
	} else {
		pan.setBackground(Color.WHITE);
		pan.setForeground(Color.BLACK);
	}

    return label;
	}
}
