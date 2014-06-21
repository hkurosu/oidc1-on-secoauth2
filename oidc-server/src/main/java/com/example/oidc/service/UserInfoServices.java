/**
 * 
 */
package com.example.oidc.service;

import com.example.oidc.model.UserInfo;

/**
 * @author Hiro
 *
 */
public interface UserInfoServices {
	UserInfo loadByUsername(String username);
}
