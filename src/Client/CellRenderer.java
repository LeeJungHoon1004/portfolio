package Client;

import java.awt.BorderLayout;
import java.awt.Color;
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

	public JPanel getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{  
		
		//현수야..
		//잘은모르겠는데 value를 어레이리스트<파일리스트>로 하면 value가 자료형이 FileList라서 안된다고 에러가떠서
		//클래스 맨위에 코드에서 멤버변수 fl을 FileList로 정의하고
		//FileList로 다운캐스팅하고등록해보니까 어설프긴한데 나오긴나와..
		//서버쪽에서 데이터를 보낸순서대로 나온다. 
		String path = "C:/4W/PictureBoardPan";
		JLabel label = new JLabel();
		JLabel lb = new JLabel();
		JPanel pan = new JPanel();
//		fl= (ArrayList<FileList>)value; //여기서 value 가 Server.FileList 로 나오는데 어레이리스트로 캐스팅안된다고함.
//    label.setIcon(new ImageIcon(path+"/"+fl.get(index).getFileName()));
//    label.setText(fl.get(index).getTitle());
		
		fl = (FileList)value;
		
		ImageIcon icon = new ImageIcon(path + "/" + fl.getFileName());
		Image originImg = icon.getImage();
		Image changedImg= originImg.getScaledInstance(150, 80, Image.SCALE_SMOOTH );
		ImageIcon image = new ImageIcon(changedImg);
		
		//System.out.println("리스트" + fl.getTitle());
		lb.setText(Integer.toString(index+1)+"|"+"     			     ");
		label.setIcon(image);
		label.setText("       "+fl.getTitle());
		
		pan.add(lb,BorderLayout.WEST);
		pan.add(label,BorderLayout.CENTER);
		
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
