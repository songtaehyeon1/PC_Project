package member;

public class MemberVO {

	private String name;
	private String id;
	private String pw;
	private String humannum;
	private int birth;
	private String gender;
	private String tel;
	private String email;
	private int resttime;
	private int moneytemp;
	
	public MemberVO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
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

	public String getHumannum() {
		return humannum;
	}

	public void setHumannum(String humannum) {
		this.humannum = humannum;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getResttime() {
		return resttime;
	}

	public void setResttime(int resttime) {
		this.resttime = resttime;
	}

	public int getMoneytemp() {
		return moneytemp;
	}

	public void setMoneytemp(int moneytemp) {
		this.moneytemp = moneytemp;
	}

}
