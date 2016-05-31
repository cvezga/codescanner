package com.gft.codescanner.indexing;

import java.io.File;

public class FileSizeIndexer implements Indexer {
	
	
	private WordIndex index;
	
	public FileSizeIndexer(){
		this.index = new WordIndex("File Size");
	}
	
	public void index(int id, File file)  {
		
		long size = file.length();
		
		if(size >=0 && size <= 1000) index.index(id, "0-1K");
		else if(size >=     1000 && size <=      10000) index.index(id, ">1K-10K");
		else if(size >=    10000 && size <=     100000) index.index(id, ">10K-100K");
		else if(size >=   100000 && size <=     500000) index.index(id, ">100K-500K");
		else if(size >=   500000 && size <=    1000000) index.index(id, ">500K-1M");
		else if(size >=  1000000 && size <=   10000000) index.index(id, ">1M-10M");
		else if(size >= 10000000 && size <=   50000000) index.index(id, ">10M-50M");
		else if(size >= 50000000 && size <=  100000000) index.index(id, ">50M-100M");
		else if(size > 100000000                      ) index.index(id, ">100M");
		else index.index(id, "Other size (this should not happend)");
		
	}

	public BitSetIndex getBitSetIndex() {
		return this.index;
	}

}
