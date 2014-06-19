/**
 * 
 */
package com.example.oidc.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.oidc.token.ODIDCDefaultIdTokenValidator;
import com.example.oidc.token.OIDCIdTokenValidator;

/**
 * @author hirobumi.kurosu
 *
 */
public class OIDCAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	protected OAuth2RestOperations restTemplate;

	protected OIDCIdTokenValidator idTokenValidator = new ODIDCDefaultIdTokenValidator();

	protected ResourceServerTokenServices tokenServices;

	public void setTokenServices(ResourceServerTokenServices tokenServices) {
		this.tokenServices = tokenServices;
	}

	public OIDCIdTokenValidator getIdTokenValidator() {
		return idTokenValidator;
	}

	public void setIdTokenValidator(OIDCIdTokenValidator idTokenValidator) {
		this.idTokenValidator = idTokenValidator;
	}

	public OAuth2RestOperations getRestTemplate() {
		return restTemplate;
	}

	/**
	 * A rest template to be used to obtain an access token.
	 * 
	 * @param restTemplate a rest template
	 */
	public void setRestTemplate(OAuth2RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}

	protected OIDCAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(new OAuth2AuthenticationManager());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		OAuth2AccessToken accessToken = restTemplate.getAccessToken();
		validateIdToken(accessToken);
		validateAccessToken(accessToken);
		try {
			return tokenServices.loadAuthentication(accessToken.getValue());
		}
		catch (InvalidTokenException e) {
			throw new BadCredentialsException("Could not obtain user details from token", e);
		}

	}

	/**
	 * Access Token validation.
	 * 
	 * @throws AuthenticationException
	 * @see http://openid.net/specs/openid-connect-core-1_0.html#CodeFlowTokenValidation
	 */
	protected void validateAccessToken(OAuth2AccessToken accessToken) throws AuthenticationException {
		// TODO:
	}

	/**
	 * ID Token validation.
	 * 
	 * @throws AuthenticationException
	 * @see http://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation
	 */
	protected void validateIdToken(OAuth2AccessToken accessToken) throws AuthenticationException {
		String idToken = retrieveIdToken(accessToken);
		if (accessToken.getScope().contains("openid")) {
			if (idToken == null) {
				// TODO: throw an error
			}
			if (!idTokenValidator.validate(idToken)) {
				// TODO: throw and error
			}
		}
	}

	protected String retrieveIdToken(OAuth2AccessToken accessToken) {
		return (String) accessToken.getAdditionalInformation().get("id_token");
	}

}
