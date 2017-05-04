package project.rest.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Heart")
public class Heart implements Serializable {

	private static final long serialVersionUID = 1981591375959951849L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	@Column(name="PULSE")
	private int pulse;
	@Column(name="TIMESTAMP")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date timestamp;
	
	public Heart(){
		
	}

	public Heart(int id, int pulse, Date timestamp) {
		super();
		this.id = id;
		this.pulse = pulse;
		this.timestamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPulse() {
		return pulse;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "Heart [id=" + id + ", pulse=" + pulse + ", timestamp=" + timestamp + "]";
	}


}
