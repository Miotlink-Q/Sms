package com.sms.android.bean;

import java.io.Serializable;

public class PhoneBook implements Serializable{
	
	private static final long serialVersionUID = 989150590004L;
	
	private String username = "";
	private String phoneNumber = "";
	private boolean isCheck=true;
	private String address="";

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean check) {
		isCheck = check;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
