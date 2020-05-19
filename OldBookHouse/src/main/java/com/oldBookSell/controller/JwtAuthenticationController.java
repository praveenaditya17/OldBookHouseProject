package com.oldBookSell.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oldBookSell.config.JwtTockenUtil;
import com.oldBookSell.model.JwtRequest;
import com.oldBookSell.model.JwtResponse;
import com.oldBookSell.service.JwtUserDetailsService;

/**
	* This is JwtAuthenticationController that authenticate user and
	* genrate token.
	* @author  Kundan,Praveen
	* @version 1.0
	* @since 2020-05-18
*/

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JwtAuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTockenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	/**
	 * This method is used to authenticate and genrate jwt token
	 * @param authenticationRequest this is the parameter of createAuthenticationToken method 
	 * @return ResponseEntity this returns status ok or failed.
	 * @throws Exception if something went wrong then throw an exception
	 */
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		LOGGER.info("JwtAuthenticationController createAuthenticationToken method is calling....");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		LOGGER.info("JwtAuthenticationController authenticate method is calling....");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException exception) {
			throw new Exception("USER_DISABLED", exception);
		} catch (BadCredentialsException exception) {
			throw new Exception("INVALID_CREDENTIALS", exception);
		}
	}

}
