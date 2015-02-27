package sumano.sytem.filemanager;

import java.io.File;
import java.util.ArrayList;

import javax.print.attribute.standard.Fidelity;


/***
 * 
 1. Create a new folder - Takes a parameter of absolute folder path
 2. Create a new file - Take a parameter of absolute file path
 3. Add content to a file - Take 2 parameters: Content to append to a file; Absolute path to a file
 4. Copy files - Takes 2 parameters: Absolute path to a source file; Absolute path to a destination file
  (NOTE: If destination file exists, it will be overwritten)
 5. Display file contents - Takes absolute path to a file as an input; Prints out file contents as an output
 6. List folder contents - Takes absolute path to a folder as an input; Prints out folder contents as an output
 7. Search for a file by name - Takes name of a file to find; Prints out list of absolute paths to files with matching names
 8. Search for a file by name - Takes 2 parameters: Absolute path to a starting folder and file name; Outputs list of absolute paths to files with matching names
 9. (Optional) Copy folders - Takes 2 parameters: Absolute path to source folder, Absolute path to destination folder

 * @author Gualberto Sumano
 *
 */
public class FileUtil {

	private static ArrayList<Folder> rootPtr = null;
	Folder root = null;
	static String path = "";
	
	public FileUtil(){
		rootPtr =  new ArrayList<Folder>();
		rootPtr.add(new Folder(""));
		root = rootPtr.get(0);
	}
	
	/*
	 * Create a new folder - Takes a parameter of absolute folder path 
	 */
	public void createNewFolder(String folderPath){
		String[] path = parsePath(folderPath);
		int deep = path.length;
		if(deep == 2){
			root.addFolder(new Folder(path[1]));
			//rootPtr.add(new Folder(path[1]));
		}
			
	}
	/*
	 * Create a new file - Take a parameter of absolute file path
	 */
	public void createNewFile(String filePath){
		String[] path = parsePath(filePath);
		int deep = path.length;	
		if(deep == 2){
			root.addFile(new MyFile(path[1]));
		}
		if(deep == 3){
			int found = findFolderName(root.getAllFolders(), path[1]);
			if(found >= 0){
				root.getAllFolders().get(found).addFile(new MyFile(path[2]));
			}else{
				System.out.println("Invalid path");
			}
		}
	}
	/*
	 * Add content to a file - Take 2 parameters: Content to append to a file; Absolute path to a file
	 */
	public void addContentToFile(String content, String filePath){
		String[] path = parsePath(filePath);
		int deep = path.length;	
		if(deep == 2){
			int foundFile = findFileName(root.getAllFiles(), path[1]);
			if(foundFile >= 0){
				root.getAllFiles().get(foundFile).addContent(content);
			}else{
				System.out.println("Error:1. Appending text to a non-existent file");
			}
		}
		if(deep == 3){
			int foundFolder = findFolderName(root.getAllFolders(), path[1]);
			if(foundFolder >= 0){
				int foundFile = findFileName(root.getAllFolders().get(foundFolder).getAllFiles(), path[2]);
				if(foundFile >= 0){
					root.getAllFolders().get(foundFolder).getAllFiles().get(foundFile).addContent(content);
				}else{
					System.out.println("Error:1. Appending text to a non-existent file");
				}
			}
		}

	}
	public void copy(String src, String dest){
		MyFile destFile = getFile(dest);
		MyFile srcFile = getFile(src);
		destFile = srcFile;
	}
	public MyFile getFile(String loc){
		String[] path = parsePath(loc);
		int deep = path.length;
		if(deep == 2){
			int foundFile = findFileName(root.getAllFiles(), path[1]);
			if(foundFile >= 0){
				return (root.getAllFiles().get(foundFile));
			}else{
				System.out.println("non-existent file");
			}
		}
		if(deep == 3){
			int foundFolder = findFolderName(root.getAllFolders(), path[1]);
			if(foundFolder >= 0){
				int foundFile = findFileName(root.getAllFolders().get(foundFolder).getAllFiles(), path[2]);
				if(foundFile >= 0){
					return(root.getAllFolders().get(foundFolder).getAllFiles().get(foundFile));
				}else{
					System.out.println("non-existent file");
				}
			}
		}
		return null;

	}
	/*
	 * Copy files - Takes 2 parameters: Absolute path to a source file; Absolute path to a destination file 
	 * (NOTE: If destination file exists, it will be overwritten)
	 */
	public void copyFiles(String srcFilePath, String destFilePath){
		
	}
	/*
	 * Display file contents - Takes absolute path to a file as an input; Prints out file contents as an output
	 */
	public void displayFileContents(String filePath){
		String[] path = parsePath(filePath);
		int deep = path.length;	
		if(deep == 2){
			int foundFile = findFileName(root.getAllFiles(), path[1]);
			if(foundFile >= 0){				
				System.out.println(root.getAllFiles().get(foundFile).getContent());
			}else{
				System.out.println("non-existent file");
			}		}
		if(deep ==3){
			int foundFolder = findFolderName(root.getAllFolders(), path[1]);
			if(foundFolder >= 0){
				int foundFile = findFileName(root.getAllFolders().get(foundFolder).getAllFiles(), path[2]);
				if(foundFile >= 0){
					System.out.println(root.getAllFolders().get(foundFolder).getAllFiles().get(foundFile).getContent());
				}else{
					System.out.println("non-existent file");
				}
			}
		}
	}
	/*
	 * List folder contents - Takes absolute path to a folder as an input; Prints out folder contents as an output
	 */
	public void listFolderContents(String folderPath){
		String[] path = parsePath(folderPath);
		int found = findFolderName(root.getAllFolders(), path[1]);
		if(found >= 0){
			displayFolderContent(root.getAllFolders().get(found));
		}

	}
	public void displayFolderContent(Folder folder){
		if(folder.hasFolders()){
			for(Folder eachFolder: folder.getAllFolders()){
				System.out.println("["+eachFolder.name +"]");
			}
		}
		if(folder.hasFiles()){
			for(MyFile eachFile: folder.getAllFiles()){
				System.out.println(eachFile.getName());
			}			
		}
		if( !folder.hasFolders() && !folder.hasFiles()){
			System.out.println("<empty>");
		}
		
	}
	/*
	 * Search for a file by name - Takes name of a file to find; Prints out list of absolute paths to files with matching names
	 */
	public void searchFileByName(String fileName){ 
		if(!root.isEmpty()){
			searchForFile(root, fileName);
			if(!root.getAllFolders().isEmpty()){
				for(Folder eachFolder: root.getAllFolders()){
					String path = searchForFile(eachFolder, fileName);
					if(path != null)System.out.println("/"+ path);
				}
			}
		}

	}
	
	public String searchForFile(Folder folder, String fileName){
		if(!folder.isEmpty()){
			for(MyFile eachFile: folder.getAllFiles()){
				if(eachFile.getName().equals(fileName)){
					System.out.println(folder.getName() +"/"+ eachFile.getName());
				}
			}
		}
		return (null);
	}
	/*
	 * Search for a file by name - Takes 2 parameters: Absolute path to a starting folder and file name; Outputs list of absolute paths to files with matching names 
	 */
	public void searchFileByName(String folderPath, String fileName){
		String[] path = parsePath(folderPath);
		int deep = path.length;	
		if(deep == 2){
			int foundFolder = findFolderName(root.getAllFolders(), path[1]);
			if(foundFolder >= 0){
				String absPath = searchForFile(root.getAllFolders().get(foundFolder), fileName);
				if(absPath != null)System.out.println("/" + absPath);
			}else{
				System.out.println("Invalid path");
			}
			
		}
	}
	/*
	 * Copy folders - Takes 2 parameters: Absolute path to source folder, Absolute path to destination folder
	 */
	public void copyFolders(String srcFolderPath, String destFolderPath){
		
	}
	
	public int findFolderName(ArrayList<Folder> folders, String name){
		for(int i=0; i<folders.size(); i++){
			if(folders.get(i).name.equals(name)){
				return(i);
			}
		}	
		return -1;
	}
	public int findFileName(ArrayList<MyFile> files, String name){
		for(int i=0; i<files.size(); i++){
			if(files.get(i).getName().equals(name)){
				return(i);
			}
		}	
		return -1;		
	}
		
	public String[] parsePath(String path){		
		return(path.split("/"));
	}
	
	
}
