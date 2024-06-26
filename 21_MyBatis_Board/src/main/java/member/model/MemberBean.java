package member.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class MemberBean {
	private int num;
	
	private final String msg = " ����";
	
	@NotEmpty(message="ȸ�� ���̵�"+msg)
	private String id;
	
	@NotEmpty(message="ȸ�� ��й�ȣ"+msg)
	private String pw;
	
	@NotEmpty(message="ȸ�� �̸�"+msg)
	@Size(min=2, max=4, message="�̸��� 2~4���ڷ� �Է�")
	private String name;
	
	@NotEmpty(message="���� ����"+msg)
	private String gender;
	
	@NotEmpty(message="���� ����"+msg)
	private String job;
	
	@Min(value=15, message="���̴� �ּ� 15��")
	private int age;
	
	@NotEmpty(message="�����ϴ� �޴� ����"+msg)
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
