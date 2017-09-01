package Server;

import java.io.Serializable;

public class VideoFileList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String urlPath;
	private String urlFileName;// 파일의 이름.
	private int urlFileSize;// 파일사이즈
	
	private String urlButtonName;
	private byte[] fileContents;// 파일내용물
	
	
	
	public VideoFileList(String urlPath, String urlFileName, int urlFileSize, String urlButtonName) {
		super();
		this.urlPath = urlPath;
		this.urlFileName = urlFileName;
		this.urlFileSize = urlFileSize;
		this.urlButtonName = urlButtonName;
	}
	public VideoFileList(String urlPath, String urlFileName, int urlFileSize, String urlButtonName,
			byte[] fileContents) {
		super();
		this.urlPath = urlPath;
		this.urlFileName = urlFileName;
		this.urlFileSize = urlFileSize;
		this.urlButtonName = urlButtonName;
		this.fileContents = fileContents;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getUrlFileName() {
		return urlFileName;
	}
	public void setUrlFileName(String urlFileName) {
		this.urlFileName = urlFileName;
	}
	public int getUrlFileSize() {
		return urlFileSize;
	}
	public void setUrlFileSize(int urlFileSize) {
		this.urlFileSize = urlFileSize;
	}
	public String getUrlButtonName() {
		return urlButtonName;
	}
	public void setUrlButtonName(String urlButtonName) {
		this.urlButtonName = urlButtonName;
	}
	public byte[] getFileContents() {
		return fileContents;
	}
	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}
	
}
