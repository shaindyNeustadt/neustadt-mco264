package neustadt.fileRecursion;

import java.io.File;

public class RecursiveFileLocator {
		
public RecursiveFileLocator(){
}

public void print(File file){
	for(File f : file.listFiles()){
		if(!f.isDirectory()){
			System.out.println("\tFile Name: " + f.getName() + " Size:" + f.length());
		}
		else{
			System.out.println("Directory Name: " + f.getName() + " Size:" + f.getTotalSpace());
			print(f);
		}
	}
	return;
}

public static void main(String [] args){
	RecursiveFileLocator rfl = new RecursiveFileLocator();
	rfl.print(new File("C://Users//Shaindy//workspace//Datastructures//RandomAccessFiles//src"));
}
}