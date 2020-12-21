package com.example.demo;

import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.domain.Category;
import com.example.domain.Produit;
import com.example.repository.ProduitDao;

public class TestProduit {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ProduitDao produitDao = (ProduitDao) context.getBean("produitDAO");
		Produit produit = new Produit(1, "Spring with JdbcTemplate", 1);
		Produit produit2 = new Produit(2, "Clean code", 1);
		produitDao.insertProduit(produit);
		produitDao.insertProduit(produit2);
//		Produit produitInDataBase = produitDao.findById(1);
//		out.println(produitInDataBase);
//		List<Produit> produits = produitDao.findAll();
//		produits.stream().forEach(out::println);
//		String produitName = produitDao.findNameById(2);
//		out.println(produitName);

		Category c = new Category(1,"Pharmacetique");
		produitDao.insertCategory(c);
//		Produit produit3 = new Produit(10, "Spring Framework");
//		Produit produit4 = new Produit(11, "Mysql DataBase");
//		List<Produit> batchList = new ArrayList<Produit>();
//		batchList.add(produit3);
//		batchList.add(produit4);
//		produitDao.insertBatchProduits(c.getId(),batchList);
//		out.println("INSERT BATCH PRODUITS " + batchList);
//		produitDao.insertBatchUpdate("UPDATE PRODUIT SET name='spring with jpa'");
		//produitDao.insertCategoryAndListOfProduit(c, batchList);
		
		System.out.println("**********************************************");
		Category category=produitDao.findCategoryWithProduit(1);
		System.out.println(category.getName());
		
		Set<Produit> produits=category.getProduits();
		for(Produit p:produits) {
			System.out.println("Le nom de prouit avec id "+p.getId() +" est "+p.getPName());
		}

		}

}
