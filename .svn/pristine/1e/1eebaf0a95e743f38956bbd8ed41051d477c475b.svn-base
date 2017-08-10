package Server;

public interface ManagerInterface {
	
	/**
	 * 멤버(String id)
	 * 를 인자로 받아서 DB에서 일치하는 값이 있는지 검사한뒤
	 * 값이 존재하면 true 없으면 false를 반환합니다.
	 * @param m
	 */
	public boolean isExist(Member m);
	
	/**멤버(String name, String id , String pw , boolean gender)
	 * 를 인자로 받아서 DB에 저장합니다.
	 * 
	 * @param m
	 */
	public void insertData(Member m);
	
	/**멤버(String id , String pw) 일치하는 값이 있는지 검사한뒤
	 * 값이 존재하면 true 없으면 false를 반환합니다
	 * @param m
	 */
	public boolean isLoginOk(Member m);
	/**
	 * BMI데이터인 stature,wieght,gender를 가져옵니다.
	 * @return
	 */
	public Member getBMIData();
	
	
	
}
