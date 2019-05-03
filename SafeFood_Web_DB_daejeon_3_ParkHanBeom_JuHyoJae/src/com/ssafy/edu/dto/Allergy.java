package com.ssafy.edu.dto;

import java.io.Serializable;

public class Allergy implements Serializable {
	private String id;
	private int Inum;
	public Allergy() {
		super();
	}
	public Allergy(String id, int inum) {
		super();
		this.id = id;
		Inum = inum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getInum() {
		return Inum;
	}
	public void setInum(int inum) {
		Inum = inum;
	}
	@Override
	public String toString() {
		return "Allergy [id=" + id + ", Inum=" + Inum + "]";
	}
		
	
}
