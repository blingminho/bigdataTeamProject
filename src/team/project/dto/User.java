package team.project.dto;

public class User {
	private String userId;
	private String email;
	private String password;
	private String name;
	private String gender;
	private String born;

	private String enrollDt;
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", born=" + born + ", enrollDt=" + enrollDt + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getBorn() {
		return born;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public String getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(String enrollDt) {
		this.enrollDt = enrollDt;
	}
}
