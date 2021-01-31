package com.buytech.backend.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.buytech.backend.models.Client;

@Component
public class ClientDao {
	
    private static final Logger logger = LoggerFactory.getLogger(ClientDao.class);
    private JdbcTemplate jdbcTemplate;

    public ClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Client> rowMapper = (rs, rowNum) -> {
    	Client client = new Client();
    	client.setId_client(rs.getInt("id_client"));
    	client.setFirstName(rs.getString("first_name"));
    	client.setLastName(rs.getString("last_name"));
    	client.setGender(rs.getString("gender"));
    	client.setEmail(rs.getString("email"));
    	client.setPassword(rs.getString("password"));
    	client.setJoiningDate(rs.getDate("joining_date"));
    	client.setPhoneNumber(rs.getInt("phone_number"));
        return client;
    };

	public Client getClientByEmail(String email) {
		String sql = "SELECT * FROM client WHERE email = '" + email + "'";
		Client client = jdbcTemplate.query(sql, rowMapper).get(0);
		System.out.println(client);
		return client;
	}

}
