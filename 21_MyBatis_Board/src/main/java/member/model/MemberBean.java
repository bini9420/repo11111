package member.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class MemberBean {
	private int num;
	
	private final String msg = " 누락";
	
	@NotEmpty(message="회원 아이디"+msg)
	private String id;
	
	@NotEmpty(message="회원 비밀번호"+msg)
	private String pw;
	
	@NotEmpty(message="회원 이름"+msg)
	@Size(min=2, max=4, message="이름은 2~4글자로 입력")
	private String name;
	
	@NotEmpty(message="성별 선택"+msg)
	private String gender;
	
	@NotEmpty(message="직업 선택"+msg)
	private String job;
	
	@Min(value=15, message="나이는 최소 15살")
	private int age;
	
	@NotEmpty(message="좋아하는 메뉴 선택"+msg)
	private String menu;
	
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
}
