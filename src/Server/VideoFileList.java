package Server;

import java.io.File;
import java.io.Serializable;

public class VideoFileList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String urlPath;
	private String urlFileName;// ������ �̸�.
	private int urlFileSize;// ���ϻ�����
	private File urlTargetFile ; //����������Ʈ�� �� jpg����
	private String urlButtonName;
	private byte[] fileContents;// ���ϳ��빰
	
	private String urlTargetFilePath ;//����������Ʈ�� ��jpg������ ������.
	
	//Server->DB���� , DB->Server�ҷ����� �Ҷ� ���.
	public VideoFileList(String urlPath, String urlFileName, int urlFileSize, String urlButtonName , String urlTargetFilePath) {
		super();
		this.urlPath = urlPath;
		this.urlFileName = urlFileName;
		this.urlFileSize = urlFileSize;
		this.urlButtonName = urlButtonName;
		this.urlTargetFilePath = urlTargetFilePath;
	}
	//DB->Server�ҷ����� ���Ľ������� .. Ÿ�����ϵ��� ��θ� �˰��Ŀ� Server->Client ���� ���� �����͸� �����Ҷ� ���.
	public VideoFileList(String urlPath, String urlFileName, int urlFileSize, String urlButtonName , File urlTargetFile) {
		super();
		this.urlPath = urlPath;
		this.urlFileName = urlFileName;
		this.urlFileSize = urlFileSize;
		this.urlButtonName = urlButtonName;
		this.urlTargetFile = urlTargetFile;
	}
	public VideoFileList(String urlPath, String urlFileName, int urlFileSize, String urlButtonName,
			byte[] fileContents ) {
		super();
		
		this.urlPath = urlPath;
		this.urlFileName = urlFileName;
		this.urlFileSize = urlFileSize;
		this.urlButtonName = urlButtonName;
		this.fileContents = fileContents;
		
	}
	public String getUrlTargetFilePath() {
		return urlTargetFilePath;
	}
	public void setUrlTargetFilePath(String urlTargetFilePath) {
		this.urlTargetFilePath = urlTargetFilePath;
	}
	
	public File getUrlTargetFile() {
		return urlTargetFile;
	}
	public void setUrlTargetFile(File urlTargetFile) {
		this.urlTargetFile = urlTargetFile;
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
