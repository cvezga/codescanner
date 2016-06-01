package com.gft.codescanner.indexing;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.gft.codescanner.util.FileUtil;

public class WordIndexer implements Indexer {
	
	private static final List<String> EXCLUDE = Arrays.asList("package","public","class","import","extends","new","return","if","String","this","void","until","null","true","false","final","private","static","java","int");
	
	private WordIndex index;
	
	private long maxFileSize;
	
	public WordIndexer(long maxFileSize){
		this.index = new WordIndex("Word");
		this.maxFileSize = maxFileSize;
	}
	
	private void indexWordsFromLine(int id, String line){
		//String[] words = line.trim().split(" |\\(|\\)|\\<|\\>|\\.|\"|\\[|\\]|\\=|\\-|\\;|\\,");
		String[] words = line.trim().split("\\W");
		for(String w : words){
			if(w.trim().length()>0){
				if(!EXCLUDE.contains(w)){
		          index.index(id, w);
				}
			}
		}
		
	}

	public void index(int id, File file)  {
		if(file.length()>maxFileSize){
			System.out.println("***** File too big: "+file.getPath()+"; size="+file.length());
			if(!file.getName().endsWith(".java")) return;
		}
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
