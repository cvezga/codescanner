package com.gft.codescanner.indexing;

import java.io.File;

public class FileTypeIndexer implements Indexer {
	
	private WordIndex index;
	
	public FileTypeIndexer(){
		this.index = new WordIndex("File Type");
	}
	
	

	public void index(int id, File file)  {
		String name = file.getName();
		int idx = name.lastIndexOf(".");
		if(idx>-1){
		   String ext = name.substring(idx+1);
		   this.index.index(id, ext);
		}else{
			this.index.index(id, "No Extension");
		}
			
	}

	public BitSetIndex getBitSetIndex() {
		return this.index;
	}

}
