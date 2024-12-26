package com.siddhrans.biometric.security;


import java.util.ArrayList;
import java.util.List;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserProfile;
import com.siddhrans.biometric.service.UserService;



@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = userService.findByUserName(userName);
		logger.info("User : {}", user);
		
		if(user==null){
			logger.info("User or user Profile Not found");
			throw new UsernameNotFoundException("Username Not Found");
		}
		UserProfile userProfile = user.getUserProfile();
		if(userProfile == null){
			logger.info("User Profile Not found");
			throw new UsernameNotFoundException("User Profile Not Found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), 
				true, true, true, true, getGrantedAuthorities(user));
	}


	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		UserProfile userProfile = user.getUserProfile();
		logger.info("UserProfile : {}", userProfile);
		authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}