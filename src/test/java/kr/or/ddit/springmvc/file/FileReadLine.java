package kr.or.ddit.springmvc.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileReadLine {

	public static void main(String[] args) throws IOException {
		 File file = new File("C:\\tempEncrypt\\TextCrytTest.java");
		 List<String> fileUtilList = FileUtils.readLines(file, "UTF-8");
		 for(String lineInfo : fileUtilList) {
			 System.out.println(lineInfo);
		 }
	}
	
}
