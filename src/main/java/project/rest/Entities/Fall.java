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
@Table(name = "Fall")
public class Fall {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	@Column(name="X_AXIS")
    private double xaxis;
	@Column(name="Y_AXIS")
    private double yaxis;
	@Column(name="Z_AXIS")
    private double zaxis;
	
	@Basic
	@Column(name="USER_ID")
	@JsonFormat(shape=JsonFormat.Shape.NUMBER)
	private int userid;
	
	@Column(name="TIMESTAMP")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date timestamp;

	public Fall()
	{
		
	}
	
	public Fall(double xaxis)
	{
		this.xaxis = xaxis;
	}
	
    public Fall(int id, double xaxis) {
		super();
		this.id = id;
		this.xaxis = xaxis;
	}

	public int getId() {
        return this.id;
    }

	public double getXaxis() {
        return this.xaxis;
    }

	public double getYaxis() {
		return yaxis;
	}

	public void setYaxis(double yaxis) {
		this.yaxis = yaxis;
	}

	public double getZaxis() {
		return zaxis;
	}

	public void setZaxis(double zaxis) {
		this.zaxis = zaxis;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int user_id) {
		this.userid = user_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setXaxis(double xaxis) {
		this.xaxis = xaxis;
	}
	
	@Override
	public String toString() {
		return "Fall [id=" + id + ", xaxis=" + xaxis + ", yaxis=" + yaxis + ", zaxis=" + zaxis + ", userid=" + userid
				+ ", timestamp=" + timestamp + "]";
	}
}
