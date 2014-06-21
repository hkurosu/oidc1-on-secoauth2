/**
 * 
 */
package com.example.oidc.auth;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.oidc.model.OIDCProviderMetadata;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;

/**
 * @author hirobumi.kurosu
 *
 */
public class OIDCAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	protected OIDCProviderMetadata providerMetadata;

	protected OAuth2RestOperations restTemplate;

	protected ResourceServerTokenServices tokenServices;

	public void setTokenServices(ResourceServerTokenServices tokenServices) {
		this.tokenServices = tokenServices;
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
		if (accessToken.getScope().contains("openid")) {
			ReadOnlyJWTClaimsSet claims = parseIdToken(accessToken);

			// ID validation rule from http://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation
			// 2) check "iss" claim
			if (claims.getIssuer() == null || !claims.getIssuer().equals(providerMetadata.getIssuer())) {
				throw new InvalidTokenException("Mismatch ID Token's Issuer");
			}
			// 3) check "aud" claim
			String clientId = restTemplate.getResource().getClientId();
			if (claims.getAudience() == null || !claims.getAudience().contains(clientId)) {
				throw new InvalidTokenException("Mismatch ID Token's Issuer");
			}

			// TODO: DO remaining validations
		}
	}

	protected ReadOnlyJWTClaimsSet parseIdToken(OAuth2AccessToken accessToken) throws InvalidTokenException {
		try {
			String idToken = retrieveIdToken(accessToken);
			return PlainJWT.parse(idToken).getJWTClaimsSet();
		}
		catch (ParseException e) {
			throw new InvalidTokenException("Cannot parse ID Token: ", e);
		}
	}

	protected String retrieveIdToken(OAuth2AccessToken accessToken) {
		return (String) accessToken.getAdditionalInformation().get("id_token");
	}
}
