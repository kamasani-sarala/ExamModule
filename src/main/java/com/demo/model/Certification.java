package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Certification {
	@Id
	@GeneratedValue
	private int cId;
	private String cName;
	private int duration;
	private int cost;
	
	private int eId;


	public Certification(int cId, String cName, int duration, int cost, int eId) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.duration = duration;
		this.cost = cost;
		this.eId = eId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	@Override
	public String toString() {
		return "Certification [cId=" + cId + ", cName=" + cName + ", duration=" + duration + ", cost=" + cost + ", eId="
				+ eId + "]";
	}
	
	
	
	
}