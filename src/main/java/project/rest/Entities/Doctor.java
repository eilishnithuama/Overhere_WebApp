package project.rest.Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DOCTOR")
public class Doctor implements Serializable{

	private static final long serialVersionUID = 7404170967378347067L;

	@Id
	@Column(name = "DOC_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    
    @Basic
    @Column(name="Name")
    private String name;
    
	@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	@JoinColumn(name = "doctor_ID", referencedColumnName = "doc_id",insertable = false)
	private Set<UserEntity> patientList;
     
    public Doctor(){
    	
    }
	
	public Doctor(int id, Set<UserEntity> patientList) {
		super();
		this.id = id;
		this.patientList = patientList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserEntity> getPatientList() {
		return patientList;
	}

	public void setPatientList(Set<UserEntity> patientList) {
		this.patientList = patientList;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", patientList=" + patientList + "]";
	}
}
