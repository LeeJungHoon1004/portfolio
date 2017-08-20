package Server;

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
	public Member getNameData(Member m) throws Exception;
	
	/**로그인했을때 해당하는 회원의 bmidata를 보냅니다. 
	 * 
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public boolean isLogoutOk(Member m)throws Exception;
	
	
	
	
	
	
}
