/**
 * 
 */
package com.example.oidc.token;


/**
 * @author hirobumi.kurosu
 *
 */
public interface OIDCIdTokenValidator {
	boolean validate(String idToken);

}
