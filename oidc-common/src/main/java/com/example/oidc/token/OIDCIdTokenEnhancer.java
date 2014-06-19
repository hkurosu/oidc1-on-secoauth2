/**
 * 
 */
package com.example.oidc.token;

import java.util.Arrays;
import java.util.Date;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;

/**
 * @author hirobumi.kurosu
 *
 */
public class OIDCIdTokenEnhancer implements TokenEnhancer {

	private String issuer;

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (authentication.getOAuth2Request().getScope().contains("openid")) {
			JWTClaimsSet idClaims = new JWTClaimsSet();
			idClaims.setIssuer(issuer);
			idClaims.setSubject(authentication.getName());
			idClaims.setAudience(Arrays.asList(authentication.getOAuth2Request().getClientId()));
			idClaims.setExpirationTime(accessToken.getExpiration());
			idClaims.setIssueTime(new Date());
			JWT idToken = new PlainJWT(idClaims);
			accessToken = new DefaultOAuth2AccessToken(accessToken);
			accessToken.getAdditionalInformation().put("id_token", idToken.serialize());
		}
		return accessToken;
	}

}
