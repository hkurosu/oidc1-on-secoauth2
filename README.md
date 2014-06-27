oidc1-on-secoauth2
==================

Extends Spring Security OAuth2 to suport OpenID Connect (OIDC) 1.0. tonr/sparklr samples from SECOAUTH2 to work with ODIC.

This project is inspired by [MITREid Connect](https://github.com/mitreid-connect/) and [Nimbus OAuth 2.0 SDK w/ OpenID Connect](http://connect2id.com/products/nimbus-oauth-openid-connect-sdk), borrowed a lot of idea from them.

# Getting Started

## Spring framework dependencies

1. spring-* : 3.2.8.RELEASE
1. spring-security : 3.2.3.RELEASE
1. spring-security-oauth2 : 2.0.1.RELEASE

## Build samples applications (command line)

"mvn package" in oidc1-on-secoauth2 directory will create three war files (tonr, sparklr, keyhole).

## Run in eclipse

- Import tonr, sparklr, keyhole and oidc-* projects with "Existing Maven projects" (Import -> Maven)
- If necessary, change the "authServerUri" property in odic.properties.

Now you can deploy all 3 apps (tonr2, sparklr2, keyhole2) into your Servers environment!

## OIDC features

### Supported:
(2014-06-26)
- ID token validation (partially) [3.1.3.7.  ID Token Validation](http://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation)
- (partially) [7.  Self-Issued OpenID Provider](http://openid.net/specs/openid-connect-core-1_0.html#SelfIssued)

(2014-06-19)
- Authorization Code Flow in [Section 3.1.1 to 3.1.3.6](http://openid.net/specs/openid-connect-core-1_0.html#CodeFlowAuth) in OpenID Connect Core 1.0.
- Plain JWT format for ID Token (Uses [Nimbus JOSE JWT](https://bitbucket.org/connect2id/nimbus-jose-jwt/wiki/Home))
 

### Working (Hopfully support soon):
- ID Token Validation (Section 3.1.3.7)
- Signed/Encrypted JWT
- UserInfo (/userinfo) endpoint and claims (Section 5)

### Not Yet Supported:
- Implict Flow (Section 3.2), Hybrid Flow (Section 3.3)
- Initiating Login from Third parties (Section 4)
- [OpenID Connect Discovery](http://openid.net/specs/openid-connect-discovery-1_0.html)
- [OpenID Connect Dynamic Registration](http://openid.net/specs/openid-connect-registration-1_0.html)
- [Session Management](http://openid.net/specs/openid-connect-session-1_0.html)

## Next step

See this document how these sample apps works: [OAuth2 Single Sign On with spring-security-oauth2](
https://github.com/hkurosu/oauth2-sso-samples/blob/master/docs/OAuth2%20Single%20Sign%20On%20with%20Spring%20\(Demo\).pptx)


