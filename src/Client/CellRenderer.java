package Client;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import Server.FileList;
public class CellRenderer extends DefaultListCellRenderer {

	private ArrayList<FileList> fl;
	String [] titles ;
	String [] filenames;
	public CellRenderer(ArrayList<FileList> fl) {
		this.fl = fl;
//		for(int i =0 ; i >fl.size() ; i++){
//			titles [i] = fl.get(i).getTitle();
//			filenames[i] =fl.get(i).getFileName();
//		}
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(580, 100));
		this.setIconTextGap(5);
	}
//	public CellRenderer(String title , String fileName){
//		
//		for(int i = 0 ; i< fl.size() ; i++){
//			title = fl.get(i).getTitle();
//			fileName= fl.get(i).getFileName();
//		}
//		
//		
//	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{
		JLabel lb = new JLabel();
		
		String path = "C:/4W/PictureBoardPan";
		
		for(int i=0 ; i < fl.size(); i++){
						
		ImageIcon icon = new ImageIcon(path + "/" + fl.get(i).getFileName());
		Image originImg = icon.getImage();
		Image changedImg= originImg.getScaledInstance(200, 80, Image.SCALE_SMOOTH );
		ImageIcon image = new ImageIcon(changedImg);
		//리스트에 있는 사진 크기 조정.△
		
		lb.setIcon(image);
		lb.setText(fl.get(i).getTitle());
		
		}
		
		if (isSelected) {
			this.setBackground(Color.GRAY);
			this.setForeground(Color.WHITE);
		} else {
			this.setBackground(Color.WHITE);
			this.setForeground(Color.BLACK);
		}
		return this;
	}
}
