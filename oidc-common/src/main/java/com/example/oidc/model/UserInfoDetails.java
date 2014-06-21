package com.example.oidc.model;

public class UserInfoDetails implements UserInfo {
	protected String sub;

	protected String name;

	protected String givenName;

	protected String familyName;

	protected String nickname;

	protected String preferredUsername;

	protected long udatedAt;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#getSub()
	 */
	public String getSub() {
		return sub;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#setSub(java.lang.String)
	 */
	public void setSub(String sub) {
		this.sub = sub;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#getGivenName()
	 */
	public String getGivenName() {
		return givenName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#setGivenName(java.lang.String)
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#getFamilyName()
	 */
	public String getFamilyName() {
		return familyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#setFamilyName(java.lang.String)
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#getNickname()
	 */
	public String getNickname() {
		return nickname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#setNickname(java.lang.String)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#getPreferredUsername()
	 */
	public String getPreferredUsername() {
		return preferredUsername;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#setPreferredUsername(java.lang.String)
	 */
	public void setPreferredUsername(String preferredUsername) {
		this.preferredUsername = preferredUsername;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#getUdatedAt()
	 */
	public long getUdatedAt() {
		return udatedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.oidc.model.UserInfo#setUdatedAt(long)
	 */
	public void setUdatedAt(long udatedAt) {
		this.udatedAt = udatedAt;
	}
}
