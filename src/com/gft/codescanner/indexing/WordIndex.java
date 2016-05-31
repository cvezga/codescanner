package com.gft.codescanner.indexing;

import java.util.Arrays;
import java.util.List;

public class WordIndex extends BitSetIndex {

	private static List<String> exclude = Arrays.asList("(",")","[","]");
	
	public WordIndex(String name) {
		super(name);
		 
	}

	@Override
	public void index(int id, String value) {
		if(!exclude.contains(value)){
		   super.index(id, value);
		}
	}
	
}
