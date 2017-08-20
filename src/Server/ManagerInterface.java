package Server;

public interface ManagerInterface {
	
	/**
	 * ���(name , id , pw , gender)
	 * �� ���ڷ� �޾Ƽ� DB���� ��ġ�ϴ� id���� �ִ��� �˻��ѵ�
	 * ���� �����ϸ� true ������ false�� ��ȯ�մϴ�.
	 * @param m
	 * @throws Exception 
	 */
	public boolean isExist(Member m) throws Exception;
	
	/**���(String name, String id , String pw , String gender) 
	 * �� ���ڷ� �޾Ƽ� DB�� �����մϴ�.
	 * 
	 * @param m
	 * @throws Exception 
	 */
	public int insertData(Member m) throws Exception ;
	
	/**���(String name, String id , String pw, String gender) ��ġ�ϴ� ���� �ִ��� �˻��ѵ�
	 * ���� �����ϸ� true ������ false�� ��ȯ�մϴ�
	 * @param m
	 */
	public boolean isLoginOk(Member m) throws Exception;
	/**
	 * BMI�������� stature,wieght,gender�� �����ɴϴ�.
	 * @return
	 */
	public Member getNameData(Member m) throws Exception;
	
	/**�α��������� �ش��ϴ� ȸ���� bmidata�� �����ϴ�. 
	 * 
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public boolean isLogoutOk(Member m)throws Exception;
	
	/**Combolist���� ���� int�� 3���� �迭�� �����մϴ�.
	 * 
	 * @param c1
	 * @param c2
	 * @param c3
	 * @throws Exception
	 */
	public String InsertDailyList(int c1 , int c2, int c3) throws Exception;
	
	
	
	
}
