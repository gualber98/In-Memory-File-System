package sumano.sytem.filemanager;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Scanner;

public class Validator {

	private static final int MAX = 3;  // Maximum number of arguments
	
	
	public static String[] getValidArgs(Scanner sc, String prompt){
		String[] args = null;
		while(args == null){
			args = getString(sc, prompt);
		}
		if(args[2] == null){
			String[] args2 = {args[0], args[1]}; 
			return (args2);
		}else{
			return args;
		}
	}
    public static String[] getString(Scanner sc, String prompt)
    {
    	String[] args = new String[MAX]; 	
    	StringBuffer text = new StringBuffer();
    	int count = 0;
    	boolean readContent = false;
    	boolean errors = false;
    	
        System.out.print(prompt);
        String input = sc.nextLine();
        Scanner scan = new Scanner(input);
        while(scan.hasNext() && !errors){
        	String arg = scan.next();
        	
        	if(count == 0){
        		if(isValidCommand(arg)){
        			if(arg.equals("write")){
        				readContent = true;
        				args[count] = arg;
        				count++;
        			}else{
        				args[count] = arg;
        				count++;
        			}
        		}else{
        			System.out.println("Invalid command!!");
        			errors = true;
        			break;
        		}
        	}else{
        		if(readContent){
                   	args[count] = readContent(input);
                   	advanceScaner(scan);
                   	readContent = false;
                   	count++;
        		}else{
        			args[count] = arg; 
        			count++;  			
        		}
        	}
        }
        if(errors || count  == 1){
        	System.out.println("Invalid input");
        	return null;
        }else{
        	return args;
        }
    }
    private static String readContent(String line){
    	String quoteMark = "\"";
    	int start = line.indexOf(quoteMark);
    	int end = line.indexOf(quoteMark, start + 1);
    	
    	return line.substring(start+1, end);
    }
    private static void advanceScaner(Scanner sc){
      	while(!sc.next().endsWith("\"")){
    	}

    }

    public static boolean isValidCommand(String command){
		switch (command) {
		case "mkdir": return true;
		case "create": return true;
		case "write": return true;
		case "cat":	return true;	
		case "cp":	return true;
		case "find": return true;				
		case "ls": return true;
		case "exit": return true;		
		}
		return false;
    }
    
}
