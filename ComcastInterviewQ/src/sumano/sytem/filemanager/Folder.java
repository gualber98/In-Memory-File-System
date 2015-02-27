package sumano.sytem.filemanager;

import java.util.ArrayList;

public class Folder {
	
	ArrayList<Folder> folders = null;
	ArrayList<MyFile> files = null;
	
	int numOfFolders;
	int numOfFiles;
	
	String name = null;
	
	public Folder(String folderName){
		name = folderName;
		numOfFiles = 0;
		numOfFolders = 0;
		
	}	
	
	public void addFolder(Folder newFolder){
		if(!hasFolders()){
			folders = new ArrayList<Folder>();
			folders.add(newFolder);
			numOfFolders++;
		}else{
			folders.add(newFolder);
			numOfFolders++;
		}
	}
	public void addFile(MyFile newFile){
		if(!hasFiles()){
			files = new ArrayList<MyFile>();
			files.add(newFile);
			numOfFiles++;
		}else{
			files.add(newFile);
			numOfFiles++;
		}
	}
	public ArrayList<Folder> getAllFolders(){
		return folders;
	}
	public ArrayList<MyFile> getAllFiles(){
		return files;
	}
	public String getName(){
		return name;
	}
	public boolean isEmpty(){
		return(folders == null && files == null);
	}
	public boolean hasFiles(){
		return(numOfFiles != 0);
	}
	public boolean hasFolders(){
		return(numOfFolders != 0);
	}
	public int getNumberOfFolders(){
		if(folders == null){
			return 0;
		}else{
			return(folders.size());
		}
	}
	public int getNumberOfFiles(){
		if(files == null){
			return 0;
		}else{
			return(files.size());
		}
	}

	
	

}
