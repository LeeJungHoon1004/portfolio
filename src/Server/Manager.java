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
		System.out.println("java07�� isExist�޼ҵ����");
		Connection con =this.getConnection();
		
		System.out.println(m.getId());
		
		String sql = "select * from member where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		
		ResultSet rs = pstat.executeQuery();
		
		boolean result = rs.next();
		//id�� �����ϸ� true
		//id�� ������ false
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
	public Member getNameData(Member m)throws Exception {
		//name
		Connection con = this.getConnection();
		String sql = "select name from member where id =? ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		//������ ������ �ش��ϴ� id�������� �̸� Ű ������ ������ ������
		String name=null;
		if(rs.next()) {
		name = rs.getString(1);
		}
		Member m1 = new Member(name);
		
		//�ش��ϴ� ȸ���� ������ ���m1���� ���� �����Ѵ�. 
		
		return m1;
		
	}

	@Override
	public boolean isLogoutOk(Member m) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String InsertDailyList(int c1, int c2, int c3) throws Exception {
		// TODO Auto-generated method stub
		Connection con =this.getConnection();
		String sql = "insert into member(seq,name,id,pw,gender,regdate) values(member_seq.nextval , ? , ? ,? ,? , sysdate)";
		PreparedStatement pstat = con.prepareStatement(sql);
		//����ڷ��� ���θ����.
//		pstat.setString(1, m.getName());
//		pstat.setString(2, m.getId());
//		pstat.setString(3, m.getPw());
//		pstat.setString(4, m.getGender());
//		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return null;
	}

	

	

	

	
	

}
