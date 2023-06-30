package com.demo.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;






@Entity
public class Exam {
	
	@Id
	@GeneratedValue
	private int eId;
	private String eName;
	private int duration;
	private String startTime;
	private String endTime;
    private String examDate;
    
   private int cId;

public Exam() {
	super();

}

public Exam(int eId, String eName, int duration, String startTime, String endTime, String examDate, int cId) {
	super();
	this.eId = eId;
	this.eName = eName;
	this.duration = duration;
	this.startTime = startTime;
	this.endTime = endTime;
	this.examDate = examDate;
	this.cId = cId;
}

public int geteId() {
	return eId;
}

public void seteId(int eId) {
	this.eId = eId;
}

public String geteName() {
	return eName;
}

public void seteName(String eName) {
	this.eName = eName;
}

public int getDuration() {
	return duration;
}

public void setDuration(int duration) {
	this.duration = duration;
}

public String getStartTime() {
	return startTime;
}

public void setStartTime(String startTime) {
	this.startTime = startTime;
}

public String getEndTime() {
	return endTime;
}

public void setEndTime(String endTime) {
	this.endTime = endTime;
}

public String getExamDate() {
	return examDate;
}

public void setExamDate(String examDate) {
	this.examDate = examDate;
}

public int getcId() {
	return cId;
}

public void setcId(int cId) {
	this.cId = cId;
}

@Override
public String toString() {
	return "Exam [eId=" + eId + ", eName=" + eName + ", duration=" + duration + ", startTime=" + startTime
			+ ", endTime=" + endTime + ", examDate=" + examDate + ", cId=" + cId + "]";
}
    
	
	}