package com.ice.shebao.model;

import java.util.List;

public class Json<T>{
	private String state;
	private String message;
	private List<T> datebean;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getDatebean() {
		return datebean;
	}

	public void setDatebean(List<T> datebean) {
		this.datebean = datebean;
	}

	
}
