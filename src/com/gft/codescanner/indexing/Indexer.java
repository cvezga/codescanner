package com.gft.codescanner.indexing;

import java.io.File;

public interface Indexer {
	
	void index(int id, File file);
	
	BitSetIndex getBitSetIndex();
	

}
