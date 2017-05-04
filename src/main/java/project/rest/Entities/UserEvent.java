package project.rest.Entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="USER_EVENT")
public class UserEvent {
	
    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int event_id;
    
    @Column(name ="EVENT_TYPE")
    private String event_type;
    
	@Column(name="TIMESTAMP")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date timestamp;
	
	@Column(name="Location")
	private GPSLocation location;
	
	@Basic
	@Column(name="USER_ID")
	@JsonFormat(shape=JsonFormat.Shape.NUMBER)
	private int userid;

	public UserEvent(){
		
	}
	
	public UserEvent(int id, String event_type, Date timestamp) {
		super();
		this.event_id = id;
		this.event_type = event_type;
		this.timestamp = timestamp;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getUser_id() {
		return userid;
	}

	public void setUser_id(int user_id) {
		this.userid = user_id;
	}

	public GPSLocation getLocation() {
		return location;
	}

	public void setLocation(GPSLocation location) {
		this.location = location;
	}
}
