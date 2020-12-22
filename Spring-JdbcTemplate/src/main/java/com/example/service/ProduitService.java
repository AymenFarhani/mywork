package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.example.domain.Category;
import com.example.domain.Produit;
import com.example.repository.ProduitDao;

public class ProduitService implements ProduitDao , InitializingBean{
	

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertProduit(Produit p) {
		String insertQuery = "INSERT INTO PRODUIT (pid, pname, category_id) VALUES (?,?,?)";
		
		jdbcTemplate.update(insertQuery, new Object[] { p.getId(), p.getPName(),p.getCategory_id() });
	}

	@Override
	public Produit findById(int id) {
		String selectQuery = "SELECT * FROM PRODUIT WHERE pid=?";
		Produit produit = jdbcTemplate.queryForObject(selectQuery, new Object[] { id }, new ProduitRowMapper());
		return produit;
	}

	@Override
	public List<Produit> findAll() {
		String selectAllQuery = "SELECT * FROM PRODUIT";
		List<Produit> produits = jdbcTemplate.query(selectAllQuery, new ProduitRowMapper());
		return produits;
	}

	@Override
	public String findNameById(int id) {
		String findNameQuery = "SELECT PNAME FROM PRODUIT WHERE pid=?";
		String produitName = jdbcTemplate.queryForObject(findNameQuery, new Object[] { id }, String.class);
		return produitName;
	}

//	@Override
//	public void insertBatchProduits(List<Produit> produits) {
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		String batchinsertQuery = "INSERT INTO PRODUIT (id, name, category_id) VALUES (?,?,?)";
//		jdbcTemplate.batchUpdate(batchinsertQuery, new BatchPreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement ps, int i) throws SQLException {
//				Produit produit = produits.get(i);
//				ps.setInt(1, produit.getId());
//				ps.setString(2, produit.getName());
//				ps.setInt(3, produit.getCategory_id());
//			}
//
//			@Override
//			public int getBatchSize() {
//				return produits.size();
//			}
//		});
//
//	}
	@Override
	public void insertBatchProduits (int category_id, List<Produit> produit) {
     String insert_produit = "insert into produit(id, name, category_id) values (:pid, :pname, :category_id)";

		SqlParameterSource[] params = new SqlParameterSource[produit.size()];
		for (int i = 0; i< produit.size(); i++) {
			params[i] = new MapSqlParameterSource("pid",produit.get(i).getId()).addValue("pname", produit.get(i).getPName()).addValue("category_id", category_id);
		}

		namedParameterJdbcTemplate.batchUpdate(insert_produit, params);
	}

	@Override
	public void insertBatchUpdate(String sql) {
		jdbcTemplate.batchUpdate(new String[] { sql });
	}

	@Override
	public String findProduitNameByName(int id) {
		String sqlQuerry = "SELECT pname FROM PRODUIT p WHERE p.pid= ?";
		Object[] params = { id };
		String produitName = jdbcTemplate.queryForObject(sqlQuerry, params, String.class);
		return produitName;
	}

	private final class ProduitRowMapper implements RowMapper<Produit> {
		@Override
		public Produit mapRow(ResultSet rs, int rowNum) throws SQLException {
			Produit p = new Produit();
			p.setId(rs.getInt("pid"));
			p.setPName(rs.getString("pname"));
			return p;
		}
	}

	private final class CategoryRowMapper implements RowMapper<Category> {
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category c = new Category();
			c.setId(rs.getInt("cid"));
			c.setName(rs.getString("cname"));
			return c;
		}
	}

	@Override
	public Category findCategoryById(int id) {
		String selectQuery = "SELECT * FROM CATEGORY WHERE cid=?";
		Category category = jdbcTemplate.queryForObject(selectQuery, new Object[] { id }, new CategoryRowMapper());
		return category;
	}

	@Override
	public List<Produit> findProduitByCategoryId(int CategoryId) {
		String selectQuery = "SELECT p.*, c.cname FROM PRODUIT p INNER JOIN CATEGORY c on p.category_id=c.cid WHERE c.cid= ?";
		Object[] params = { CategoryId };
		List<Produit> produits = jdbcTemplate.query(selectQuery, params, new ProduitRowMapper());
		return produits;
	}

	@Override
	public List<Produit> findProduitByCategroyName(String categoryName) {
		String selectQuery = "SELECT p.*, c.cname FROM PRODUIT p INNER JOIN CATEGORY c on p.category_id=c.cid WHERE c.cname= ?";
		Object[] params = { categoryName };
		List<Produit> produits = jdbcTemplate.query(selectQuery, params, new ProduitRowMapper());
		return produits;
	}

	@Override
	public List<String> findAllCategoryWithProduits() {
//		   String SelectAllProduit = "SELECT Produit FROM produit";
//		return (List<String>) jdbcTemplate.query(SelectAllProduit,
//				new ResultSetExtractor<List<String>>() {
//					@Override
//					public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
//						List<String> list = new ArrayList<String>();
//						while (rs.next()) {
//							if (rs.getString(1) != null) {
//								list.add(rs.getString(1));
//							}
//						}
//						return list;
//					}
//				});
		return null;
	}


	@Override
	public void insertCategory(Category c) {
		String insertQuery = "INSERT INTO CATEGORY (cid, cname) VALUES (?,?)";
		jdbcTemplate.update(insertQuery, new Object[] { c.getId(), c.getName()});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    simpleJdbcInsert=new SimpleJdbcInsert(dataSource);
	}

	@Override
	public Category findCategoryWithProduit(long id) {
		final String selectSqlQuery="select * from category, produit where cid="+id;
		return  jdbcTemplate.query(selectSqlQuery, new CategoryProduitExtractor());
	}
	@Override
	public void insertCategoryAndListOfProduit(Category c, List<Produit> produits) {
		List<String> columns=new ArrayList<String>();
		columns.add("cname");
		columns.add("cid");
		simpleJdbcInsert.setTableName("category");
		simpleJdbcInsert.setColumnNames(columns);
		Map<String, Object> map=new HashMap<>();
		map.put("cname", c.getName());
		map.put("cid", c.getId());
		simpleJdbcInsert.setGeneratedKeyName("id");
		
		int categoryId=(int) simpleJdbcInsert.executeAndReturnKey(map);
		
		insertBatchProduits(categoryId, produits);
		
	}

	


}

class CategoryProduitExtractor implements ResultSetExtractor<Category>{

	@Override
	public Category extractData(ResultSet rs) throws SQLException, DataAccessException {
		Category category=null;
		Set<Produit> produits=new HashSet<Produit>();
		while(rs.next()) {
			if(category == null) {
				category=new Category();
				category.setId(rs.getInt("cid"));
				category.setName(rs.getString("cname"));
			}
			Produit produit=new Produit();
			produit.setId(rs.getInt("pid"));
			produit.setPName(rs.getString("pname"));
			produits.add(produit);	
		}
		category.setProduits(produits);
		return category;
	}
	
}
