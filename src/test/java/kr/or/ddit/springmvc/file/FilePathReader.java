package kr.or.ddit.springmvc.file;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class FilePathReader {

	// 하위폴더 찾기
	public static void main(String[] args) {
		
		String pathInfo = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\springMVC\\src\\main\\java";
		File directory = new File(pathInfo);
		String[] extensions = {"java"};
		Iterator<File> fileIterator = FileUtils.iterateFiles(directory, extensions, true);
		while(fileIterator.hasNext()) {
			File file = fileIterator.next();
//			System.out.println(file.getPath());
			System.out.println("package path : "+file.getPath().substring(pathInfo.length()+1).replace("\\\\", "."));
			System.out.println("file name : "+file.getName());
		}
	}
}
