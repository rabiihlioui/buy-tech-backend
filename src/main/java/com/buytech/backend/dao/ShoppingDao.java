package com.buytech.backend.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.buytech.backend.models.Client;

@Component
public class ShoppingDao {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingDao.class);
    private JdbcTemplate jdbcTemplate;

    public ShoppingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	public String deleteAllBoughtProductsByClient(Integer clientId) {
		String sql = "DELETE FROM clientproduct WHERE id_client = " + clientId;
		jdbcTemplate.update(sql);
		return "All Bought Products are deleted for the chosen client";
	}
	
}
