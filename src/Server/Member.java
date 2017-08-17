package Server;

public class Member {

	private String name ;
	private String id ;
	private String pw ;
	private String gender ; //����
	private double stature; //Ű
	private double weight; //������
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public double getStature() {
		return stature;
	}
	public void setStature(double stature) {
		this.stature = stature;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	//������
	public Member(String name ,String id ,String pw , String gender) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
	}
	
	public Member(String name, String id, String pw, String gender, double stature, double weight) {
		
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
		this.stature = stature;
		this.weight = weight;
	}
	//stature , weight, gender
	public Member(String name, double stature , double weight , String gender) {
		this.name = name;
		this.stature = stature;
		this.weight = weight;
		this.gender= gender;
	}
	public Member(String id , String pw) {
		this.id= id;
		this.pw= pw;
		
	}
	
	
	
	
	

	
	
	
}
