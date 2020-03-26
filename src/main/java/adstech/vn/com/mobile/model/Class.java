package adstech.vn.com.mobile.model;

import java.util.Date;

public class Class {
	private Integer id;
	private String name;
	private String capcha;
	private Integer teacherId;
	private Integer userClassId;
	private Date createdAt;
	private Date updatedAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapcha() {
		return capcha;
	}
	public void setCapcha(String capcha) {
		this.capcha = capcha;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getUserClassId() {
		return userClassId;
	}
	public void setUserClassId(Integer userClassId) {
		this.userClassId = userClassId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
