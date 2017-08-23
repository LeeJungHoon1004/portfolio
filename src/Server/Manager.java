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
		
		ResultSet rs = pstat.executeQuery(); //��񿡼� �����ö� �� ����ϴ� ��
		
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
	public String getNameData(Member m)throws Exception {
		//name
		Connection con = this.getConnection();
		String sql = "select name from member where id =? ";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, m.getId());
		ResultSet rs = pstat.executeQuery();
		//������ ������ �ش��ϴ� id�������� �̸� Ű ������ ������ ������
		String name=null;
		String id = null;
		String pw = null;
		
		if(rs.next()) {
		name = rs.getString(1);
		}
		
		//�ش��ϴ� ȸ���� ������ ���m1���� ���� �����Ѵ�. 
		
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
		//����ڷ��� ���θ����.
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
		System.out.println("ResultSet �˻��� ���翩�� :" +b);
		String result =null;
		if(b) {
			result = rs.getString("combolist");
		}
		System.out.println("getComboListData�޼ҵ��� result :" +result);
		return result;
		
	}
	@Override
	public void getwaterCupListData() throws Exception {
		// TODO Auto-generated method stub
		
	}


	
	



	
	

	

	

	

	
	

}
