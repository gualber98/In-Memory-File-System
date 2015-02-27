package sumano.sytem.filemanager;

import java.awt.DisplayMode;
import java.util.ArrayList;
import java.util.Scanner;


public class InMemoryFileSystemApp {
	
	private static Scanner sc = null;
	

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		FileUtil manager = new FileUtil();
		
		displayMenu();
		
		boolean exit = false;
		while (!exit ){
			
			String[] arguments = Validator.getValidArgs(sc, "Enter a command: ");
			String command = arguments[0];
			
			switch (command) {
			case "mkdir":
				manager.createNewFolder(arguments[1]);
				break;
			case "create":
				manager.createNewFile(arguments[1]);
				break;
			case "write":
				manager.addContentToFile(arguments[1], arguments[2]);
				break;
			case "cat":	
				manager.displayFileContents(arguments[1]);
				break;
			case "cp":	
				manager.copy(arguments[1], arguments[2]);
				break;
			case "find":
				if(arguments.length == 3){
					manager.searchFileByName(arguments[1], arguments[2]);
				}else{
					manager.searchFileByName(arguments[1]);
				}
				break;
			case "ls":
				manager.listFolderContents(arguments[1]);
				break;
			case "exit":
				System.out.println("Good Bye");
				exit = true;
				break;
			
			}
			

		}
		
	}
	  
	public static void displayMenu(){
		System.out.println(
				"-- Commands -- \n"+
				"mkdir   (Ex: mkdir /someName) \n" +
				"create  (Ex: create /file1) \n"+
				"create  (Ex: create /someName/file1) \n"+
				"write   (Ex: write \"Some text\" /file1 )\n"+
				"cat     (Ex: cat /file1)\n"+
				"cp      (Ex: cp /file1 /someName/file2)\n"+
				"find    (Ex: find file2) \n"+
				"find    (Ex: find /someName file2) \n"+
				"ls      (Ex: ls /someName) \n"+
				"cp      (Ex: cp /someName /copyFolder) \n"+
				"exit \n");
		
	}
	
 

}
