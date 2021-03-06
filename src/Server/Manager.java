package Server;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * DB설계 seq.nextval; private String name ; not null private String id ; not null
 * private String pw ; not null private String gender ; //성별 not null private
 * double stature; //키 private double weight; //몸무게 sysdate;
 * 
 *
 */

public class Manager implements ManagerInterface {

	private Connection getConnection() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "java07";
		String pw = "java07";
		Connection con = DriverManager.getConnection(url, id, pw);

		return con;
	}

	@Override
	public boolean isExist(Member m) throws Exception {
		System.out.println("java07의 isExist메소드실행");
		Connection con = this.getConnection();

		System.out.println(m.getId());

		String sql = "select * from member where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());

		ResultSet rs = pstat.executeQuery(); // 디비에서 가져올때 꼭 써야하는 거

		boolean result = rs.next();
		// id가 존재하면 true
		// id가 없으면 false
		con.close();
		return result;
	}

	@Override
	public int insertData(Member m) throws Exception {
		// TODO Auto-generated method stub
		Connection con = this.getConnection();
		String sql = "insert into member(seq,name,id,pw,gender,regdate) values(member_seq.nextval , ? , ? ,? ,? , sysdate)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getName());
		pstat.setString(2, m.getId());
		pstat.setString(3, m.getPw());
		pstat.setString(4, m.getGender());
		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}

	@Override
	public boolean isLoginOk(Member m) throws Exception {
		Connection con = this.getConnection();
		String sql = "select * from member where id =? and pw =?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		pstat.setString(2, m.getPw());
		ResultSet rs = pstat.executeQuery();
		boolean result = rs.next();

		return result;
	}

	@Override
	public String getNameData(Member m) throws Exception {
		// name
		Connection con = this.getConnection();
		String sql = "select name from member where id =? ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		// 쿼리문 실행후 해당하는 id값주인의 이름 키 몸무게 성별을 가져옴
		String name = null;
		String id = null;
		String pw = null;

		if (rs.next()) {
			name = rs.getString(1);
		}

		// 해당하는 회원의 정보를 멤버m1으로 만들어서 리턴한다.

		return name;

	}

	@Override
	public boolean isLogoutOk(Member m) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int InsertDailyList(String id, String combolist) throws Exception {
		// TODO Auto-generated method stub

		Connection con = this.getConnection();
		System.out.println(id);
		System.out.println(combolist);
		String sql = "update member set combolist =? where id =?";

		PreparedStatement pstat = con.prepareStatement(sql);
		// 멤버자료형 따로만들것.
		// String list, String id

		pstat.setString(1, combolist);
		pstat.setString(2, id);

		int result = pstat.executeUpdate();

		// pstat.setString(1, m.getName());
		// pstat.setString(2, m.getId());
		// pstat.setString(3, m.getPw());
		// pstat.setString(4, m.getGender());
		// int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}

	@Override
	public void isExistWaterCupListData() throws Exception {

	}

	@Override
	public int InsertWaterCuplist(String id, String ChangeCupList) throws Exception {
		Connection con = this.getConnection();
		System.out.println(id);
		System.out.println(ChangeCupList);
		String sql = "update member set watercuplist =? where id =?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, ChangeCupList);
		pstat.setString(2, id);
		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}

	@Override
	public String getComboListData(Member m) throws Exception {

		Connection con = this.getConnection();
		String sql = "select combolist from member where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);

		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		boolean b = rs.next();
		System.out.println("ResultSet 검사후 존재여부 :" + b);
		String result = null;
		if (b) {
			result = rs.getString("combolist");
		}
		System.out.println("getComboListData메소드의 result :" + result);
		return result;

	}

	@Override
	public String getwaterCupListData(Member m) throws Exception {

		Connection con = this.getConnection();
		String sql = "select watercupList from member where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);

		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		boolean isexistID = rs.next();
		System.out.println("resultset 검사후 id존재여부 : " + isexistID);
		String result = null;
		if (isexistID) {
			result = rs.getString("watercuplist");
		}
		System.out.println("getwarterCupListData 메소드의 result : " + result);
		return result;

	}

	@Override
	public boolean isExistUrlData(VideoFileList vfl) throws Exception {
		Connection con = this.getConnection();
		String sql = "select * from url where buttonname = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, vfl.getUrlButtonName());

		ResultSet rs = pstat.executeQuery();
		boolean result = rs.next();
		// 해당 버튼에 대해서 데이터가 존재하면 true 존재하지않으면 false
		// 존재하면 -> 데이터를 업데이트
		// 존재하지않으면 -> 데이터를 인서트
		con.close();
		return result;
	}

	@Override
	public int insertUrlData(VideoFileList vfl) throws Exception {
		Connection con = this.getConnection();
		String sql = "insert into url(seq,url,filename,filesize,targetfile ,buttonname,regdate) values(url_seq.nextval , ? , ? ,?,? ,? , sysdate)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, vfl.getUrlPath());
		pstat.setString(2, vfl.getUrlFileName());
		pstat.setInt(3, vfl.getUrlFileSize());
		pstat.setString(4, vfl.getUrlTargetFilePath());
		pstat.setString(5, vfl.getUrlButtonName());
		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}

	@Override
	public int updateUrlData(VideoFileList vfl) throws Exception {
		Connection con = this.getConnection();
		String sql = "update url set   seq = url_seq.nextval , url =? , filename = ? , filesize =?  , targetfile = ?,regdate = sysdate where buttonname =?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, vfl.getUrlPath());
		pstat.setString(2, vfl.getUrlFileName());
		pstat.setInt(3, vfl.getUrlFileSize());
		pstat.setString(4, vfl.getUrlTargetFilePath());
		pstat.setString(5, vfl.getUrlButtonName());
		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}

	@Override
	public ArrayList<VideoFileList> getUrlAllDAta() throws Exception {
		Connection con = this.getConnection();
		
		String sql = "select * from url ";
		PreparedStatement pstat = con.prepareStatement(sql);
		ArrayList<VideoFileList> resultList = new ArrayList<VideoFileList>();
					
			ResultSet rs = pstat.executeQuery();
			
			while (rs.next()) {
				String urlPath = rs.getString("url");
				String urlFileName = rs.getString("filename");
				int urlFileSize = rs.getInt("filesize");
				String urlButtonName = rs.getString("buttonname");
				String urlTargetFilePath = rs.getString("targetfile"); //각각컴포넌트들에 들어갈 파일들의 저장경로
			//	System.out.println("urlPath" + urlFileName);
				VideoFileList tmpVideoFileList = new VideoFileList(urlPath, urlFileName, urlFileSize, urlButtonName , urlTargetFilePath);
				
				resultList.add(tmpVideoFileList);
				
			}
//			for(int i =0 ; i < resultList.size(); i++) {
//				System.out.println(resultList.get(i).getUrlFileName()); //실제파일이름 -- > 버튼이름_파일이름_파일경로
//				System.out.println(resultList.get(i).getUrlPath());
//				System.out.println(resultList.get(i).getUrlFileSize());
//				System.out.println(resultList.get(i).getUrlTargetFilePath());
//			
//			}
		

		return resultList;

		// boolean b = rs.next();
		// System.out.println("ResultSet 검사후 존재여부 :" +b);
		// String result =null;
		// if(b) {
		// result = rs.getString("combolist");
		// }
		// System.out.println("getComboListData메소드의 result :" +result);

	}

}
