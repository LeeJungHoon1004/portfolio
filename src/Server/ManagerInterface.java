package Server;

import java.util.ArrayList;

public interface ManagerInterface {
	/**
	 * 멤버(name , id , pw , gender)
	 * 를 인자로 받아서 DB에서 일치하는 id값이 있는지 검사한뒤
	 * 값이 존재하면 true 없으면 false를 반환합니다.
	 * @param m
	 * @throws Exception 
	 */
	public boolean isExist(Member m) throws Exception;
	/**멤버(String name, String id , String pw , String gender) 
	 * 를 인자로 받아서 DB에 저장합니다.
	 * 
	 * @param m
	 * @throws Exception 
	 */
	public int insertData(Member m) throws Exception ;
	
	/**멤버(String name, String id , String pw, String gender) 일치하는 값이 있는지 검사한뒤
	 * 값이 존재하면 true 없으면 false를 반환합니다
	 * @param m
	 */
	public boolean isLoginOk(Member m) throws Exception;
	/**
	 * BMI데이터인 stature,wieght,gender를 가져옵니다.
	 * @return
	 */
	public String getNameData(Member m) throws Exception;
	
	/**로그인했을때 해당하는 회원의 bmidata를 보냅니다. 
	 * 
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public boolean isLogoutOk(Member m)throws Exception;
	
	/**Combolist에서 받은 String타입의 combolist를 받고
	 * Member(String id, String pw)생성자에서 
	 * id값을 가져온뒤에 id값에 해당하는 사람의 combolist를 업데이트 합니다.
	 * @param c1
	 * @param c2
	 * @param c3
	 * @throws Exception
	 */
	public int InsertDailyList(String id, String combolist) throws Exception ;
	/**ChangeCupList에서 받은 String타입의 changeCupList를 받고
	 * Member(String id, String pw)생성자에서
	 * id값을 가져온뒤에 id값에 해당하는 사람의 changeCupList를 업데이트합니다.
	 * 오라클 db에서의 changeCupList이름은 watercuplist임.
	 * @param id
	 * @param ChangeCupList
	 * @return
	 * @throws Exception
	 */
	

	public int InsertWaterCuplist(String id,String changeCupList) throws Exception;
	

	public void isExistWaterCupListData()throws Exception;
	
	public String getComboListData(Member m)throws Exception;
	public String getwaterCupListData(Member m)throws Exception;

	public boolean isExistUrlData(VideoFileList vfl) throws Exception;
	public int insertUrlData(VideoFileList vfl) throws Exception;
	public int updateUrlData(VideoFileList vfl) throws Exception;
	public ArrayList <VideoFileList> getUrlAllDAta() throws Exception;
	
	
	
}
