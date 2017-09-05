package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import Server.FileList;

public class CellRenderer extends DefaultListCellRenderer {

	private ArrayList<FileList> fl;
	//private FileSystemView fileSystemView;
	
	public CellRenderer() {
		// this.fl = fl;

		this.setOpaque(true);
		this.setPreferredSize(new Dimension(200, 80));
		this.setIconTextGap(5);

	}

	public JLabel getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{  
		String path = "C:/4W/PictureBoardPan";
		JLabel label = new JLabel();
		fl= (ArrayList<FileList>)value;
    label.setIcon(new ImageIcon(path+"/"+fl.get(index).getFileName()));
    label.setText(fl.get(index).getTitle());
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
