package com.ice.shebao.model;

import java.util.List;
import java.util.Map;

public class JsonDataObject {
	private String state;
	private String message;
	private List<Map<String, String>> dataList;
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
	public List<Map<String, String>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<String, String>> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "JsonDataObject [state=" + state + ", message=" + message + ", dataList=" + dataList + "]";
	}
	
	
}
