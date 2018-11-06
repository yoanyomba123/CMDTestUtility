package Utilities;

import java.io.File;
import java.io.Serializable;

public class DirectoryFileList implements Serializable{
	
	String folderName = null;

	
	public DirectoryFileList(String folderName) {
		this.folderName = folderName;		
	}
	
	public File[] listFiles() {
		File folder = new File(getFolderName());
		File[] listOfFiles = folder.listFiles();
		int numFiles = listOfFiles.length;
		if(numFiles == 0) {
			noFileFoundWarning();
		}
		else {
			displayFiles(listOfFiles);
		}
		
		return listOfFiles;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	
	private void noFileFoundWarning() {
		System.out.println("No Files Found Warning");
	}
	
	private void displayFiles(File[] listOfFiles) {
		Integer index = 0;
		for(File file: listOfFiles) {
			String fileName = file.getName();
			System.out.println(index + ")" + fileName);
			index++;
		}
	}
	
}
