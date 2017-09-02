package Server;

import java.io.File;
import java.io.Serializable;

public class VideoFileList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String urlPath;
	private String urlFileName;// 파일의 이름.
	private int urlFileSize;// 파일사이즈
	private File urlTargetFile ; //각각컴포넌트에 들어갈 jpg파일
	private String urlButtonName;
	private byte[] fileContents;// 파일내용물
	
	private String urlTargetFilePath ;//각각컴포넌트에 들어갈jpg파일의 저장경로.
	
	//Server->DB저장 , DB->Server불러오기 할때 사용.
	public VideoFileList(String urlPath, String urlFileName, int urlFileSize, String urlButtonName , String urlTargetFilePath) {
		super();
		this.urlPath = urlPath;
		this.urlFileName = urlFileName;
		this.urlFileSize = urlFileSize;
		this.urlButtonName = urlButtonName;
		this.urlTargetFilePath = urlTargetFilePath;
	}
	//DB->Server불러오기 이후시점에서 .. 타겟파일들의 경로를 알고난후에 Server->Client 으로 실제 데이터를 전송할때 사용.
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
