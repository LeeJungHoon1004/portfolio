package Client;

public class VideoFileList {
	private String url;
	private String filename;//������ �̸�. 
	private int fileSize;//���ϻ�����
	private byte[] filecontents;//���ϳ��빰
	
	public VideoFileList(String url, String filename, int fileSize, byte[] filecontents) {
		super();
		this.url = url;
		this.filename = filename;
		this.fileSize = fileSize;
		this.filecontents = filecontents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
