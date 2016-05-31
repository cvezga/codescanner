package com.gft.codescanner.indexing;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IndexerManager {
	
	private Map<Integer,File> fileMap;
	private Map<String, Indexer> indexerMap;
	
	public IndexerManager(){
		this.indexerMap = new HashMap<String, Indexer>();
		
	}

	public void register(Indexer indexer) {
		this.indexerMap.put( indexer.getBitSetIndex().getName(), indexer );
	}
	
	public void index(List<File> filesToIndex){
		
		createFilesIds(filesToIndex);

		indexFiles();
	}

	private void indexFiles() {
		for(Entry<Integer,File> entry : this.fileMap.entrySet()){
			Integer id = entry.getKey();
			File file = entry.getValue();
			//System.out.println("Indexing file: "+file.getPath()+"; id = "+id+"; size = "+file.length());
			for(Indexer indexer : this.indexerMap.values() ){
				indexer.index(id, file);
			}
		}
		
	}

	private void createFilesIds(List<File> filesToIndex) {
		this.fileMap = new HashMap<Integer,File>();
		int id=0;
		for(File f: filesToIndex){
			this.fileMap.put(id++, f);
		}
		
	}
	
	public Collection<Indexer> getIndexers(){
		return this.indexerMap.values();
	}
	

}
