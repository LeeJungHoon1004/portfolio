package Server;

public class Member {

	private String name ;
	private String id ;
	private String pw ;
	private boolean gender ; //己喊
	private double stature; //虐
	private double weight; //个公霸
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
	
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	//积己磊
	public Member(String name ,String id ,String pw , boolean gender) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
	}
	
	public Member(String name, String id, String pw, boolean gender, double stature, double weight) {
		
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
		this.stature = stature;
		this.weight = weight;
	}
	//stature , weight, gender
	public Member(String name, double stature , double weight , boolean gender) {
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
