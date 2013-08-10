package br.com.cavedon.passwordgenerator.core.test;

import br.com.cavedon.passwordgenerator.core.PasswordGenerator;
import junit.framework.Assert;
import junit.framework.TestCase;


public class PasswordGeneratorTest extends TestCase {

	private PasswordGenerator generator;
	
	@Override
	protected void setUp() throws Exception {
		generator = PasswordGenerator.getInstance("master_senha");
	}
	
	public void testGeneratePassword() throws Exception {		
		String pwdString = generator.createPasswordFor("website", false, -1);
		
		Assert.assertEquals("a149bf266a8b0ca74a9e33a06b23aa6f", pwdString);
	}
	
	public void testGeneratePasswordWithLimitedSize() throws Exception {
		String limitedPwdString = generator.createPasswordFor("website", false, 8);
		
		Assert.assertEquals("a149bf26", limitedPwdString);		
	}
	
	public void testGenerateNumericPassword() throws Exception {
		String numericPwd = generator.createNumericPasswordFor("website", -1);
		
		Assert.assertEquals("71498226678809774791337068237762", numericPwd);
	}
	
	public void testGenerateNumericPasswordWithLimitedSize() throws Exception {
		String limitedNumericPwd = generator.createNumericPasswordFor("website", 8);
		
		Assert.assertEquals("71498226", limitedNumericPwd);
	}
	
	public void testGeneratePasswordWithSpecialChars() throws Exception {
		String specialPwdString = generator.createPasswordFor("website", true, -1);
		
		Assert.assertEquals("P#!a149bf266a8b0ca74a9e33a06b23aa6f", specialPwdString);		
	}
	
	public void testGeneratePasswordWithLimitedSizeAndSpecialChars() throws Exception {
		String specialPwdString = generator.createPasswordFor("website", true, 8);
		
		Assert.assertEquals("P#!a149b", specialPwdString);
	}
}
