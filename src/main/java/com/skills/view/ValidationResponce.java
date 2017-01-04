package com.skills.view;

import java.util.List;

public class ValidationResponce {
	private String status;
	private List<String> errorMessageList;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getErrorMessageList() {
		return this.errorMessageList;
	}
	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
}
