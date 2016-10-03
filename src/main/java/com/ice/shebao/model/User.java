package com.ice.shebao.model;

public class User {
	private int id;				//主键
	private String name;		//姓名
	private String sex;			//性别
	private String identitynum;	//身份证号
	private String phone;		//电话号码
	private String bank;		//开户银行
	private String buildtime;	//建档日期
	private String cardnum;		//社保卡号
	private String serial;		//个人序号
	private String areanum;		//地区码
	private String password;	//密码
	private String photo;		//照片
	
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdentitynum() {
		return identitynum;
	}
	public void setIdentitynum(String identitynum) {
		this.identitynum = identitynum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(String buildtime) {
		this.buildtime = buildtime;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getAreanum() {
		return areanum;
	}
	public void setAreanum(String areanum) {
		this.areanum = areanum;
	}
}
