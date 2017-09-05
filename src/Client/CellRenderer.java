package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

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

	public JLabel getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{  
		
		//������..
		//�����𸣰ڴµ� value�� ��̸���Ʈ<���ϸ���Ʈ>�� �ϸ� value�� �ڷ����� FileList�� �ȵȴٰ� ����������
		//Ŭ���� ������ �ڵ忡�� ������� fl�� FileList�� �����ϰ�
		//FileList�� �ٿ�ĳ�����ϰ����غ��ϱ� ������ѵ� �����䳪��..
		//�����ʿ��� �����͸� ����������� ���´�. 
		String path = "C:/4W/PictureBoardPan";
		JLabel label = new JLabel();
//		fl= (ArrayList<FileList>)value; //���⼭ value �� Server.FileList �� �����µ� ��̸���Ʈ�� ĳ���þȵȴٰ���.
//    label.setIcon(new ImageIcon(path+"/"+fl.get(index).getFileName()));
//    label.setText(fl.get(index).getTitle());
		
		fl = (FileList)value;
		label.setIcon(new ImageIcon(path+"/"+fl.getFileName()));
    //label.setToolTipText(fl.getPath());

    if (isSelected) {
		this.setBackground(Color.GRAY);
		this.setForeground(Color.WHITE);
	} else {
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
	}

    return label;
	}
}
