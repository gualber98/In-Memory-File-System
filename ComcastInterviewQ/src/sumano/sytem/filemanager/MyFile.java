package sumano.sytem.filemanager;

public class MyFile {
	
	private StringBuffer content = null;
	private String name = null;
	
	public MyFile(String fileName){
		name = fileName;
	}
			
	public String getName(){
		return name;
	}
	public boolean isEmpty(){
		return(content == null);
	}
	public void addContent(String text){
		if(content == null){
			content = new StringBuffer();
			content.append(text);
		}else{
			content.append(text);
		}
	}
	public String getContent(){
		if(content == null){
			return null;
		}else{
			return(content.toString());
		}
	}

}
