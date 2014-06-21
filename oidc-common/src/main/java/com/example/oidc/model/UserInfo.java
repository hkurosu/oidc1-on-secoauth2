package com.example.oidc.model;

public interface UserInfo {

	String getSub();

	void setSub(String sub);

	String getName();

	void setName(String name);

	String getGivenName();

	void setGivenName(String givenName);

	String getFamilyName();

	void setFamilyName(String familyName);

	String getNickname();

	void setNickname(String nickname);

	String getPreferredUsername();

	void setPreferredUsername(String preferredUsername);

	long getUdatedAt();

	void setUdatedAt(long udatedAt);

}