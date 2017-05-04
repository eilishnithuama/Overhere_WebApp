package project.rest.Entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="Carer")
public class Carer implements Serializable {
	
	private static final long serialVersionUID = 477735487326918035L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long carer_id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MOBILE")
	private String mobile;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "User_id",insertable = false, updatable = false)
	private UserEntity user_id;
	
	public Carer(){
		
	}

	public Carer(long carer_id, String name, String mobile, UserEntity patient_id) {
		super();
		this.carer_id = carer_id;
		this.name = name;
		this.mobile = mobile;
		this.user_id = patient_id;
	}

	public long getCarer_id() {
		return carer_id;
	}

	public void setCarer_id(long carer_id) {
		this.carer_id = carer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserEntity getUser_id() {
		return this.user_id;
	}

	public void setUser_id(UserEntity user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Carer [carer_id=" + carer_id + ", name=" + name + ", mobile=" + mobile + ", user_id=" + user_id + "]";
	}	
}
