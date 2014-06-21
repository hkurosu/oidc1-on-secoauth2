/**
 * 
 */
package com.example.oidc.endpoint;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.oidc.model.UserInfo;
import com.example.oidc.service.UserInfoServices;

/**
 * @author Hiro
 *
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoEndpoint {

	@Autowired
	protected UserInfoServices userInfoServices;

	public UserInfo get(Principal principal) {
		String username = principal.getName();
		return userInfoServices.loadByUsername(username);
	}
}
