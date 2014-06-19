/**
 * 
 */
package com.example.oidc.token;

/**
 * @author hirobumi.kurosu
 *
 */
public class ODIDCDefaultIdTokenValidator implements OIDCIdTokenValidator {

	@Override
	public boolean validate(String idToken) {
		// TODO: decode JWT
		// TODO: need to implement http://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation
		return true;
	}

}
