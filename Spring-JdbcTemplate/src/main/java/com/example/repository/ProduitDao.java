package com.example.repository;

import java.util.List;

import com.example.domain.Category;
import com.example.domain.Produit;

public interface ProduitDao {
	
	public void insertProduit(Produit p);
	public String findProduitNameByName(int id);
	public Produit findById(int id);
	public List<Produit> findAll();
	public String findNameById(int id);
	public void insertBatchProduits(int category_id, List<Produit> produits);
	public void insertBatchUpdate(String sql);
	public Category findCategoryById(int id);
	public List<Produit> findProduitByCategoryId(int CategoryId);
	public List<Produit> findProduitByCategroyName(String categoryName);
	public List<String> findAllCategoryWithProduits();
	public void insertCategory(Category c);
	public Category findCategoryWithProduit(long id);
	public void insertCategoryAndListOfProduit(Category c, List<Produit> produits);
}
