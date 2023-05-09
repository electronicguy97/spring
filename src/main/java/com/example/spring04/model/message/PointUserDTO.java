package com.example.spring04.model.message;

public class PointUserDTO {
	private String userid;
	private String pwd;
	private String name;
	private int point;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "PointUserDTO [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", point=" + point + "]";
	}

}
