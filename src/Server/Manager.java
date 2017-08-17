package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**	DB���� 
 * seq.nextval;
 * private String name ; not null
	private String id ; not null
	private String pw ; not null
	private String gender ; //���� not null
	private double stature; //Ű
	private double weight; //������
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
		Connection con =this.getConnection();
		String sql = "select * from member where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		boolean result = rs.next();
		con.close();
		return false;
	}

	@Override
	public void insertData(Member m) throws Exception {
		// TODO Auto-generated method stub
		Connection con =this.getConnection();
		String sql = "insert into member values(member_seq.nextval , ? , ? ,? ,? , sysdate)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getName());
		pstat.setString(2, m.getId());
		pstat.setString(3, m.getPw());
		pstat.setString(4, m.getGender());
		int result = pstat.executeUpdate();
		con.commit();
	
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
	public Member getBMIData(Member m)throws Exception {
		//name , stature , weight, gender
		Connection con = this.getConnection();
		String sql = "select name, stature , weight, gender where id =? ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		//������ ������ �ش��ϴ� id�������� �̸� Ű ������ ������ ������
		String name = rs.getString(0);
		String stature = rs.getString(1);
		String weight = rs.getString(2);
		String gender = rs.getString(3);
		Member m1 = new Member(name ,stature ,weight , gender);
		//�ش��ϴ� ȸ���� ������ ���m1���� ���� �����Ѵ�. 
		return m1;
	}

	@Override
	public boolean isLogoutOk(Member m) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	

	

	

	
	

}
