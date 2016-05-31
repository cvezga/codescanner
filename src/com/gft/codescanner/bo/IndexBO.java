package com.gft.codescanner.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gft.codescanner.indexing.BitSetIndex;
import com.gft.codescanner.indexing.FileTypeIndexer;
import com.gft.codescanner.indexing.FolderScanner;
import com.gft.codescanner.indexing.Indexer;
import com.gft.codescanner.indexing.IndexerManager;
import com.gft.codescanner.indexing.PackageIndexer;
import com.gft.codescanner.indexing.WordIndexer;


public class IndexBO {
	
	private List<String> exclude = Arrays.asList(".*\\.class");
	
	private IndexerManager indexManager;
	
	public IndexBO(){
		this.indexManager = new IndexerManager();
		
		indexManager.register( new WordIndexer() );
		indexManager.register( new FileTypeIndexer() );
		indexManager.register( new PackageIndexer() );
		
		
	}

	public void indexFilesOnPath(String path) throws Exception{
		FolderScanner folderScanner = new FolderScanner(path,null,exclude);
		
		List<File> files = folderScanner.getFileList();
		
		for(File f : files){
			System.out.println(f.getPath());
		}
		System.out.println(files.size());
		
		indexManager.index(files);
	}
	
	public List<BitSetIndex> getBitSetIndexList(){
		List<BitSetIndex> bsiList = new ArrayList<BitSetIndex>();
		for(Indexer indexer : this.indexManager.getIndexers() ){
			bsiList.add(indexer.getBitSetIndex());
		}
		return bsiList;
	}
}
