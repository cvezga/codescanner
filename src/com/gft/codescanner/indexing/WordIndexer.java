package com.gft.codescanner.indexing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.gft.codescanner.util.FileUtil;

public class WordIndexer implements Indexer {
	
	private WordIndex index;
	
	public WordIndexer(){
		this.index = new WordIndex("Word");
	}
	
	private void indexWordsFromLine(int id, String line){
		//String[] words = line.trim().split(" |\\(|\\)|\\<|\\>|\\.|\"|\\[|\\]|\\=|\\-|\\;|\\,");
		String[] words = line.trim().split("\\W");
		for(String w : words){
			if(w.trim().length()>0){
		      index.index(id, w);
			}
		}
		
	}

	public void index(int id, File file)  {
		try {
			List<String> lines = FileUtil.readFile(file);
			for(String line : lines){
				indexWordsFromLine(id, line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public BitSetIndex getBitSetIndex() {
		return this.index;
	}

}
