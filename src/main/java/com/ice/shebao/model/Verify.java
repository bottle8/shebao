package com.ice.shebao.model;

public class Verify {
	private int id;
	private String username;			//姓名
	private String identity;			//身份证
	private String verifyCode;			//验证码
	private String endTime;				//验证码过期的结束时间
	private String rfield;
	private String remarks;
	private String phone;				//电话号码

	public Verify() {
		super();
	}

	public Verify(int id, String username, String identity, String verifyCode, String endTime, String rfield,
			String remarks, String phone) {
		this.id = id;
		this.username = username;
		this.identity = identity;
		this.verifyCode = verifyCode;
		this.endTime = endTime;
		this.rfield = rfield;
		this.remarks = remarks;
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRfield() {
		return rfield;
	}

	public void setRfield(String rfield) {
		this.rfield = rfield;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
