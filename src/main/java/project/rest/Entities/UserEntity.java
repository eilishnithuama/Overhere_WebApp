package project.rest.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UserEntity")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -5563556964402919346L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_id;
	
	@Basic
	@Column(name="USERNAME")
	private String username;
	
	@Basic
	@Column(name="NAME")
	private String name;
	
	@Basic
	@Column(name="PASSWORD")
	private String password;
	
	@Basic
	@Column(name="TYPE")
	private int type;
	
	@Column(name="HOMEADDRESS")
	private GPSLocation homeAddress;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", referencedColumnName = "user_id",insertable = false, updatable = false)
	private List<Carer> carer_id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "DOCTOR_ID", referencedColumnName = "doc_id",insertable = false, updatable = false)
	private Doctor doctor;
	
	public UserEntity(){
		
	}

	public UserEntity(int user_id, String username, String name, String password, int type, GPSLocation homeAddress,
			List<Carer> carer_id, List<UserEvent> userEvents, Doctor doctor) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.type = type;
		this.homeAddress = homeAddress;
		this.carer_id = carer_id;
		this.doctor = doctor;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Carer> getCarer_id() {
		return carer_id;
	}

	public void setCarer_id(List<Carer> carer_id) {
		this.carer_id = carer_id;
	}

	@Override
	public String toString() {
		return "UserEntity [userid=" + user_id + ", username=" + username + ", name=" + name + ", password=" + password
				+", type=" + type  + "]";
	}

	public GPSLocation getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(GPSLocation homeAddress) {
		this.homeAddress = homeAddress;
	}
}
