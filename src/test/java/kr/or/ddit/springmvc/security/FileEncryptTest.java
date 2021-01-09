package kr.or.ddit.springmvc.security;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.fdl.cryptography.EgovCryptoService;
import kr.or.ddit.springmvc.file.FileNameExclud;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/egovframework/spring/context-aspect.xml"
		, "/egovframework/spring/context-common.xml"
		, "/egovframework/spring/context-datasource.xml"
		, "/egovframework/spring/context-properties.xml"
		, "/egovframework/spring/context-mapper.xml"
		, "/egovframework/spring/context-transaction.xml"
		, "/egovframework/spring/context-security.xml"
})
public class FileEncryptTest{
	@Resource(name="ARIACryptoService")
	EgovCryptoService cryptoService;
	
	String password = "egovframe";		
	@Test
	public void testFile(){
//		String filePath = "c:/tempEncrypt/TextCrytTest.java";
//		String filePath = "c:/tempEncrypt/FileZilla_3.50.0_win64_sponsored-setup.exe";
		String filePath = "c:/tempEncrypt/TextCrytTest.java";
		File srcFile = new File(filePath);
		
		File targetFile;
		File decryptedFile;
		
		try {
			String encFileInfo = FileNameExclud.myFileNameUtilsEncrypt(filePath);
			targetFile = new File(encFileInfo);
			
			cryptoService.encrypt(srcFile, password, targetFile);
			
			String decFileInfo = FileNameExclud.myFileNameUtilsDecrypt(filePath);
			decryptedFile = new File(decFileInfo);
			
			cryptoService.decrypt(targetFile, password, decryptedFile);
			
			Assert.assertTrue("Decrypted file not same!!", FileUtils.contentEquals(srcFile, decryptedFile));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
