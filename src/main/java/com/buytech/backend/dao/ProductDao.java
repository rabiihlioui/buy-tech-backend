package com.buytech.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.buytech.backend.models.Product;

@Component
public class ProductDao implements DAO<Product> {

    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);
    private JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Product> rowMapper = (rs, rowNum) -> {
    	Product product = new Product();
    	product.setId_prod(rs.getInt("id_prod"));
    	product.setType(rs.getString("type"));
    	product.setBrand(rs.getString("brand"));
    	product.setSubBrand(rs.getString("sub_brand"));
    	product.setPrice(rs.getLong("price"));
    	product.setDescription(rs.getString("description"));
    	product.setOperatingSystem(rs.getString("operating_system"));
    	product.setProcessorTechnology(rs.getString("processor_technology"));
    	product.setProcessor(rs.getString("processor"));
    	product.setGraphics(rs.getString("graphics"));
    	product.setMemory(rs.getString("memory"));
    	product.setHardDrive(rs.getString("hard_drive"));
    	product.setWireless(rs.getString("wireless"));
    	product.setPowerSupply(rs.getString("power_supply"));
    	product.setBattery(rs.getString("battery"));
    	product.setScreen(rs.getInt("screen"));
    	product.setQuantity(rs.getInt("quantity"));
        return product;
    };
    
    RowMapper<String> typeRowMapper = (rs, rowNum) -> {
    	String type = new String(rs.getString("type"));
    	return type;
    };

//    @Override
//    public List<Course> list() {
//        String sql = "SELECT course_id, title, description, link from course";
//        return jdbcTemplate.query(sql,rowMapper);
//    }

	@Override
	public List<Product> getProductsByType(String type) {
		String sql = "SELECT * FROM product WHERE type = '" + type + "'";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Product> getProductsScreenWide(int screen) {
		String sql = "SELECT * FROM product WHERE screen = " + screen;
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Product> getProductsByPrice(float minPrice, float maxPrice) {
		String sql = "SELECT * FROM product WHERE price between " + minPrice + " and " + maxPrice;
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		String sql = "SELECT * FROM product WHERE brand = '" + manufacturer + "'";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Product> getProductsBySortType(String sortType) {
		StringBuffer sql = new StringBuffer("SELECT * FROM product ORDER BY ");
		if(sortType.equals("nameUp"))
			sql.append("brand ASC");
		else if(sortType.equals("nameDown"))
			sql.append("brand DESC");
		else if(sortType.equals("priceUp"))
			sql.append("price ASC");
		else
			sql.append("price DESC");
		return jdbcTemplate.query(sql.toString(), rowMapper);
	}
	
	@Override
	public List<String> getProductTypesList() {
		String sql = new String("SELECT DISTINCT type FROM product");
		return jdbcTemplate.query(sql, typeRowMapper);
	}

}
