package com.gft.codescanner.indexing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.gft.codescanner.util.FileUtil;

public class JavaPackageIndexer implements Indexer {
	
	private WordIndex index;
	
	public JavaPackageIndexer(){
		this.index = new WordIndex("Package");
	}
	
	private boolean indexPackage(int id, String line){
		boolean indexed = false;
		line = line.trim();
		if(line.startsWith("package ")){
			String p = line.substring(8).replace(";", "").trim();
			
			index.index(id, p);
			indexed=true;
		}
		return indexed;
		
	}

	public void index(int id, File file)  {
		if(!file.getName().endsWith(".java")) return;
		
		try {
			List<String> lines = FileUtil.readFile(file);
			for(String line : lines){
				if( indexPackage(id, line) ){
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-g
			e.printStackTrace();
		}
		
	}

	public BitSetIndex getBitSetIndex() {
		return this.index;
	}

}
