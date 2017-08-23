package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**	DB설계 
 * seq.nextval;
 * private String name ; not null
	private String id ; not null
	private String pw ; not null
	private String gender ; //성별 not null
	private double stature; //키
	private double weight; //몸무게
	sysdate;
 * 
 *
 */


public class Manager implements ManagerInterface{

	
	private Connection getConnection() throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "java07";
		String pw = "java07";
		Connection con = DriverManager.getConnection(url,id, pw );
		
		return con;
	}
	@Override
	public boolean isExist(Member m) throws Exception{
		System.out.println("java07의 isExist메소드실행");
		Connection con =this.getConnection();
		
		System.out.println(m.getId());
		
		String sql = "select * from member where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		
		ResultSet rs = pstat.executeQuery(); //디비에서 가져올때 꼭 써야하는 거
		
		boolean result = rs.next();
		//id가 존재하면 true
		//id가 없으면 false
		con.close();
		return result;
	}
	

	@Override
	public int insertData(Member m) throws Exception {
		// TODO Auto-generated method stub
		Connection con =this.getConnection();
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
	public boolean isLoginOk(Member m)throws Exception {
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
	public String getNameData(Member m)throws Exception {
		//name
		Connection con = this.getConnection();
		String sql = "select name from member where id =? ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		//쿼리문 실행후 해당하는 id값주인의 이름 키 몸무게 성별을 가져옴
		String name=null;
		String id = null;
		String pw = null;
		
		if(rs.next()) {
		name = rs.getString(1);
		}
		
		//해당하는 회원의 정보를 멤버m1으로 만들어서 리턴한다. 
		
		return name;
		
	}

	@Override
	public boolean isLogoutOk(Member m) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int InsertDailyList(String id ,String combolist) throws Exception {
		// TODO Auto-generated method stub
		
		Connection con =this.getConnection();
		System.out.println(id);
		System.out.println(combolist);
		String sql = "update member set combolist =? where id =?";
		
		PreparedStatement pstat = con.prepareStatement(sql);
		//멤버자료형 따로만들것.
		//String list, String id
		
		pstat.setString(1, combolist );
		pstat.setString(2 ,id  );

				
		int result = pstat.executeUpdate();

//		pstat.setString(1, m.getName());
//		pstat.setString(2, m.getId());
//		pstat.setString(3, m.getPw());
//		pstat.setString(4, m.getGender());
//		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result ;
	}
	

	@Override
	public void isExistWaterCupListData() throws Exception {
		
		
	}
	
	@Override
	public int InsertWaterCuplist(String id, String ChangeCupList) throws Exception {
		Connection con =this.getConnection();
		System.out.println(id);
		System.out.println(ChangeCupList);
		String sql = "update member set watercuplist =? where id =?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1,ChangeCupList);
		pstat.setString(2,id);
		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result ;	
	}
	
	
	
	@Override
	public String getComboListData(Member m) throws Exception {

		
		
		Connection con =this.getConnection();
		String sql = "select combolist from member where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		
		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		boolean b = rs.next();
		System.out.println("ResultSet 검사후 존재여부 :" +b);
		String result =null;
		if(b) {
			result = rs.getString("combolist");
		}
		System.out.println("getComboListData메소드의 result :" +result);
		return result;
		
	}
	@Override
	public void getwaterCupListData() throws Exception {
		// TODO Auto-generated method stub
		
	}


	
	



	
	

	

	

	

	
	

}
