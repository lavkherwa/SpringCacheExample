package com.example.cache.pojo;

public class LoggedOnUser implements ILoggedOnUser {

	private int id;
	private String email;

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public int getId() {
		return id;
	}

}
