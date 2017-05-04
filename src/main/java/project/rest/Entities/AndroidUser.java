package project.rest.Entities;

public class AndroidUser {
	
	private int user_id;
	private String username;
	private String name;
	private String password;
	private GPSLocation homeAddress;
	private String carerNumber;
	
	public AndroidUser(){
		
	}
	
	public AndroidUser(int user_id, String username, String name, String password,
			GPSLocation homeAddress,String carerNumber) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.homeAddress = homeAddress;
		this.carerNumber = carerNumber;
	}

	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public GPSLocation getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(GPSLocation homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public String getCarerNumber(){
		return carerNumber;
	}
	
	public void setCarerNumber(String carerNumbers) {
		this.carerNumber = carerNumbers;
		
	}
	
	@Override
	public String toString() {
		return "AndroidUser [user_id=" + user_id + ", username=" + username + ", name=" + name + ", password="
				+ password + ", homeAddress=" + homeAddress + ", carerNumber=" + carerNumber + "]";
	}
	
}
