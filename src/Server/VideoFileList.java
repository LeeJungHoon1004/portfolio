package Server;

import java.io.Serializable;

public class VideoFileList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String urlPath;
	private String filename;// 파일의 이름.
	private int fileSize;// 파일사이즈
	private byte[] filecontents;// 파일내용물
	private String buttonName;
	public VideoFileList(String urlPath, String filename, int fileSize, byte[] filecontents) {
		super();
		this.urlPath = urlPath;
		this.filename = filename;
		this.fileSize = fileSize;
		this.filecontents = filecontents;
	}

	public VideoFileList(String urlPath, String filename, int fileSize ,String buttonName) {

		this.urlPath = urlPath;
		this.filename = filename;
		this.fileSize = fileSize;
		this.buttonName = buttonName;
	}

	public String getUrl() {
		return urlPath;
	}

	public void setUrl(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFilecontents() {
		return filecontents;
	}

	public void setFilecontents(byte[] filecontents) {
		this.filecontents = filecontents;
	}

}
