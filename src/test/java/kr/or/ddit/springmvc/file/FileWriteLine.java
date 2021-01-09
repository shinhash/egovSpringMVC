package kr.or.ddit.springmvc.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileWriteLine {

	public static void main(String[] args) throws IOException {
		
		List<String> lineInfo = new ArrayList<String>();
		
		for(int i=0; i<100; i++) {
			lineInfo.add("test 문자열"+(i+1));
		}
		File file = new File("C:\\tempEncrypt\\TextCrytTest.java");
		FileUtils.writeLines(file, lineInfo, true);
	}
}
