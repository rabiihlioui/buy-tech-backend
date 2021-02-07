package com.buytech.backend.jwt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.buytech.backend.dao.ClientDao;
import com.buytech.backend.models.Client;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ClientDao clientDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		List<GrantedAuthority> authorities
//	      = new ArrayList<>();
//		
//		authorities.add(new SimpleGrantedAuthority("admin"));
//		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode("hi");
//		if ("rabii@gmail.com".equals(email)) {
//			return new User("rabii@gmail.com", encodedPassword,
//					authorities);
//		} else {
//			throw new UsernameNotFoundException("User not found with email: " + email);
//		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword;
		try {
			Client user = clientDao.getClientByEmail(email);
			encodedPassword = passwordEncoder.encode(user.getPassword());
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getEmail(), encodedPassword, authorities);
		}
		catch(Exception e) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		
		
	}

}
