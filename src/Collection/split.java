package Collection;

public class split {
	
	public static void main(String[] args) {
	//Server - > DB �����ҋ� ��Ʈ��Ÿ�� �Ѱ��� �����ѵڿ� DB- > Server������ ���� split�޼ҵ�� �ɰ��� �������.
		
		String a = "1,3,7,9";
		String [] tmp = a.split(",");
		for(int i= 0; i< tmp.length; i++ ) {
			System.out.println(tmp[i]);
		}
		
		Object a2 = "1,3,7,9";
		
		String [] tmp2 =((String)a2).split(",");
		for(int i= 0; i< tmp2.length; i++ ) {
			System.out.println(tmp2[i]);
		}

		
	
	}
}
