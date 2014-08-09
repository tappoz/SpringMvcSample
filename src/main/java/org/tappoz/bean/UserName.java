package org.tappoz.bean;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserName {

	private static final String FORBIDDEN_CHARACTERS = " !@#$%\\/:*?\"<>|';";
//	private static final String[] FORBIDDEN_SQL_CHARS = {"//", "/*", "*/"};
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	private UserName(String inputUserName) {
		this.userName = inputUserName;
	}

    // otherwise when deserializing: "com.fasterxml.jackson.databind.JsonMappingException: No suitable constructor found for type"
    public UserName() {
    }

    public static UserName getNewInstance(String userName) {
		if	(
					StringUtils.containsNone(userName, FORBIDDEN_CHARACTERS)
//			SQL stuff	&&	!Arrays.
				&&	StringUtils.isNotBlank(userName)
			) {
			return new UserName(userName);
		} else {
			throw new IllegalStateException("The following suggested user name: '" + userName   
					+ " can not be accepted, please review the characters you are using");
		}
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
