package com.example.domain;

import java.io.Serializable;

public class Produit implements Serializable{
	private int pid;
	private String pname;
	private int category_id;
	
	public Produit() {}

	public Produit(int pid, String pname,int category_id) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.category_id=category_id;
	}

	public Produit(int id, String pname) {
		this.pid = id;
		this.pname = pname;
	}

	public int getId() {
		return pid;
	}

	public void setId(int id) {
		this.pid = id;
	}

	public String getPName() {
		return pname;
	}

	public void setPName(String name) {
		this.pname = name;
	}
	

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	
	
	

}
