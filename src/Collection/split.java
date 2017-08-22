package Collection;

public class split {
	
	public static void main(String[] args) {
	//Server - > DB 저장할떄 스트링타입 한개로 저장한뒤에 DB- > Server꺼내서 쓸떄 split메소드로 쪼개서 꺼내어쓴다.
		
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
