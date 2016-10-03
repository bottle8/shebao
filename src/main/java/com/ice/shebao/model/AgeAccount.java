package com.ice.shebao.model;

public class AgeAccount {
	private String aid;				//主键
	private String buildtime;		//账户建立日期
	private String type;			//账户类型
	private String state;			//账户状态
	private String unitCutIn;		//累计单位划入
	private String addCutIn;		//累计划入利息
	private String unitSum;			//单位划入余额
	private String personAdd;		//累计个人缴纳
	private String personInterest;	//累计缴纳利息
	private String personSum;		//个人缴纳余额
	private String accountAmont;	//账户总余额
	private String serial;			//个人序号
	private String identity;		//身份证号
	private String areanum;			//地区编码
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(String buildtime) {
		this.buildtime = buildtime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUnitCutIn() {
		return unitCutIn;
	}
	public void setUnitCutIn(String unitCutIn) {
		this.unitCutIn = unitCutIn;
	}
	public String getAddCutIn() {
		return addCutIn;
	}
	public void setAddCutIn(String addCutIn) {
		this.addCutIn = addCutIn;
	}
	public String getUnitSum() {
		return unitSum;
	}
	public void setUnitSum(String unitSum) {
		this.unitSum = unitSum;
	}
	public String getPersonAdd() {
		return personAdd;
	}
	public void setPersonAdd(String personAdd) {
		this.personAdd = personAdd;
	}
	public String getPersonInterest() {
		return personInterest;
	}
	public void setPersonInterest(String personInterest) {
		this.personInterest = personInterest;
	}
	public String getPersonSum() {
		return personSum;
	}
	public void setPersonSum(String personSum) {
		this.personSum = personSum;
	}
	public String getAccountAmont() {
		return accountAmont;
	}
	public void setAccountAmont(String accountAmont) {
		this.accountAmont = accountAmont;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getAreanum() {
		return areanum;
	}
	public void setAreanum(String areanum) {
		this.areanum = areanum;
	}
	
	
}
