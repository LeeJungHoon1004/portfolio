package Client;


import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;
//���� �ϼ�
public class FileList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title; //���ϰ� ���̺��� ����
	private String contents; //���ϰ� ���̺��� �ڸ�Ʈ
	private String fileName; //������ �̸�. 
	private int fileSize; //���ϻ�����
	private byte [] fileContents; //���ϳ��빰
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public byte[] getFileContents() {
		return fileContents;
	}
	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}
	public FileList(String id,String title, String contents, String fileName, int fileSize , byte[] fileContents) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.fileName = fileName;
		this.fileContents = fileContents;
		this.fileSize = fileSize;
		
	}
	
}
