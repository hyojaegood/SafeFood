package com.ssafy.edu.dto;

import java.io.Serializable;

public class Ingredient implements Serializable {
	private int Inum;
	private String name;
	
	public Ingredient() {
		super();
	}

	public Ingredient(int inum, String name) {
		super();
		Inum = inum;
		this.name = name;
	}

	public int getInum() {
		return Inum;
	}

	public void setInum(int inum) {
		Inum = inum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ingredient [Inum=" + Inum + ", name=" + name + "]";
	}
	
	
}
