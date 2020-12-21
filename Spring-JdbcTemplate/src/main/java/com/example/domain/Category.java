package com.example.domain;

import java.io.Serializable;
import java.util.Set;

public class Category implements Serializable {
	
	private int cid;
	private String cname;
	private Set<Produit> produits;
	
	
	public Category() {}
	public Category(int id, String name) {
		super();
		this.cid = id;
		this.cname = name;
	}

	public Category(String name) {
		this.cname=name;
	}
	public int getId() {
		return cid;
	}

	public void setId(int id) {
		this.cid = id;
	}

	public String getName() {
		return cname;
	}

	public void setName(String name) {
		this.cname = name;
	}

	public Set<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits=produits;	
	}

	

	
	
	

}
