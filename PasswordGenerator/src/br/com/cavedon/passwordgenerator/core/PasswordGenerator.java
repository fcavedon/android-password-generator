package br.com.cavedon.passwordgenerator.core;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.StringCharacterIterator;

import org.apache.commons.lang3.StringUtils;

public class PasswordGenerator {

	private static PasswordGenerator generator;
	private StringBuilder salt = new StringBuilder("5259155e7caf7098e1b0f8f145ae8758@$@1234567890*&#%^$");
	
	private PasswordGenerator(CharSequence masterPwd) {
		this.salt.append(masterPwd);
	}
	
	public static PasswordGenerator getInstance(CharSequence masterPwd) {
		if (generator == null) {
			generator = new PasswordGenerator(masterPwd);
		}		
		
		return generator;
	}

	public String createPasswordFor(CharSequence webSite, boolean specialChars, int size) throws Exception {
		
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update((webSite.toString()+salt.toString()).getBytes());
		
		String pwd = new BigInteger(1, digest.digest()).toString(16);
	
		if (specialChars) {
			pwd = "P#!"+pwd;
		}
		
		if (size > 0) {
			pwd = pwd.substring(0, size);
		}	
		
		return pwd;
	}

	public String createNumericPasswordFor(CharSequence webSite, int size) throws Exception {
		StringBuilder numericPwd = new StringBuilder();		
		StringCharacterIterator characterIterator = new StringCharacterIterator(createPasswordFor(webSite, false, size));
		
		char currentChar = characterIterator.first();
		
		while (currentChar != StringCharacterIterator.DONE) {
			if (StringUtils.isAlpha(""+currentChar)) {
				numericPwd.append(((int)currentChar)%10);
			} else {
				numericPwd.append(currentChar);
			}
			currentChar = characterIterator.next();
		}
		
		return numericPwd.toString();
	}
}
