package com.oldBookSell.serviceImpl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oldBookSell.repository.UserDetailRepository;
import com.oldBookSell.service.JwtUserDetailsService;

/**
 * This is JwtUserDetailsServiceImpl implements an application that
 * simply calls JwtUserDetailsService interface methods
 * @author Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	/**
	 * This method is use to load user by username
	 * @param username this is the parameter of loadUserByUsername method
	 * @return UserDetails this returns details of user
	 * @exception UsernameNotFoundException if user is not fuond then throw exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  LOGGER.info("JwtUserDetailsService loadUserByUsername method is calling...");	
		  com.oldBookSell.model.UserDetails userDetail = userDetailRepository.findByEmail(username); 
		 
		  if (!userDetail.getEmail().equals(username)) { 
			  throw new UsernameNotFoundException("User not found with username: " + username); 
		  }
		
		  return new
		  org.springframework.security.core.userdetails.User(userDetail.getEmail(),
		  userDetail.getPassword(), new ArrayList<>());
	}

}
