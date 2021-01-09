package kr.or.ddit.springmvc.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.validator.GenericValidator;

public class FileNameExclud {

	public static void main(String[] args) {
		String filePath = "c:/tempEncrypt/전자정부 표준프레임워크 실무활용.pdf";

		
		String baseName = FilenameUtils.getBaseName(filePath); 
		String fullPath = FilenameUtils.getFullPath(filePath); 
		String fileName = FilenameUtils.getName(filePath);     
		String fileExtension = FilenameUtils.getExtension(filePath);
		
		
		System.out.println(baseName);
		System.out.println(fullPath);
		System.out.println(fileName);
		System.out.println(fileExtension);
		
		
		
		String encFileName = fullPath + baseName + "_enc." + fileExtension;
		String decFileName = fullPath + baseName + "_dec." + fileExtension;
		
		System.out.println("encFileName : "+encFileName);
		System.out.println("decFileName : "+decFileName);
		
	}
	
	
	
	public static String myFileNameUtilsEncrypt(String fileNameInfo) {
		String encFileName = "";
		
		if(GenericValidator.isBlankOrNull(fileNameInfo)) {
		}else {
			String baseName = FilenameUtils.getBaseName(fileNameInfo); 
			String fullPath = FilenameUtils.getFullPath(fileNameInfo); 
//			String fileName = FilenameUtils.getName(fileNameInfo);     
			String fileExtension = FilenameUtils.getExtension(fileNameInfo);
			encFileName = fullPath + baseName + "_enc." + fileExtension;
		}
		return encFileName;
	}
	
	public static String myFileNameUtilsDecrypt(String fileNameInfo) {
		String decFileName = "";
		
		if(GenericValidator.isBlankOrNull(fileNameInfo)) {
		}else {
			String baseName = FilenameUtils.getBaseName(fileNameInfo); 
			String fullPath = FilenameUtils.getFullPath(fileNameInfo); 
//			String fileName = FilenameUtils.getName(fileNameInfo);     
			String fileExtension = FilenameUtils.getExtension(fileNameInfo);
			decFileName = fullPath + baseName + "_dec." + fileExtension;
		}
		return decFileName;
	}
	
}
