package Server;

import java.util.ArrayList;

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
	public String getNameData(Member m) throws Exception;
	
	/**�α��������� �ش��ϴ� ȸ���� bmidata�� �����ϴ�. 
	 * 
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public boolean isLogoutOk(Member m)throws Exception;
	
	/**Combolist���� ���� StringŸ���� combolist�� �ް�
	 * Member(String id, String pw)�����ڿ��� 
	 * id���� �����µڿ� id���� �ش��ϴ� ����� combolist�� ������Ʈ �մϴ�.
	 * @param c1
	 * @param c2
	 * @param c3
	 * @throws Exception
	 */
	public int InsertDailyList(String id, String combolist) throws Exception ;
	/**ChangeCupList���� ���� StringŸ���� changeCupList�� �ް�
	 * Member(String id, String pw)�����ڿ���
	 * id���� �����µڿ� id���� �ش��ϴ� ����� changeCupList�� ������Ʈ�մϴ�.
	 * ����Ŭ db������ changeCupList�̸��� watercuplist��.
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
