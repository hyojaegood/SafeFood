package com.ssafy.edu.dto;

public class MemberDto {
	private String id;
	private String password;
	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	public MemberDto() {
	}
	public MemberDto(String id, String password, String name, String address, String email, String phoneNumber
			) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + ", email="
				+ email + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
}
