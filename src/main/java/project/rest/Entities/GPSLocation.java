package project.rest.Entities;

import java.io.Serializable;
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
@Table(name = "GPSLocation")
public class GPSLocation implements Serializable {
	
	private static final long serialVersionUID = -5993744049673905490L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	@Basic
	@Column(name="LONGITUDE")
	private float longitude;
	@Basic
	@Column(name="LATITUDE")
	private float latitude;
	@Basic
	@Column(name="USER_ID")
	@JsonFormat(shape=JsonFormat.Shape.NUMBER)
	private int userid;
    
	@Column(name="TIMESTAMP")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date timestamp;
	
	public GPSLocation(){
		
	}
	
	public GPSLocation(int id, float longitude, float latitude, Date timestamp,int user_id) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timestamp = timestamp;
		this.userid = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
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
	
	@Override
	public String toString() {
		return "GPSLocation [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", timestamp="
				+ timestamp + "]";
	}	
}
