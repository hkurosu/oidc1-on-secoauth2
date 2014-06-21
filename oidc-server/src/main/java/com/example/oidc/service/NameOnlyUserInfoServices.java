/**
 * 
 */
package com.example.oidc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.oidc.model.UserInfo;
import com.example.oidc.model.UserInfoDetails;

/**
 * @author Hiro
 *
 */
public class NameOnlyUserInfoServices implements UserInfoServices {

	@Autowired
	protected UserDetailsService userDetailsService;

	@Override
	public UserInfo loadByUsername(String username) {
		UserDetails user = userDetailsService.loadUserByUsername(username);
		return createUserInfo(user);
	}

	protected UserInfo createUserInfo(UserDetails user) {
		UserInfo userInfo = new UserInfoDetails();
		userInfo.setSub("subject-" + user.getUsername());
		userInfo.setName(user.getUsername());
		userInfo.setPreferredUsername(user.getUsername());
		userInfo.setUdatedAt(new Date().getTime() / 1000);
		return userInfo;
	}
}
