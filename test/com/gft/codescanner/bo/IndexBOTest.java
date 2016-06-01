package com.gft.codescanner.bo;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.NumberFormat;
import java.util.List;

import org.junit.Test;

import com.gft.codescanner.indexing.BitSetIndex;
import com.gft.codescanner.indexing.BitSetItem;
import com.gft.codescanner.util.Timer;

public class IndexBOTest {
	
	private static final String PATH = "C:\\cygwin64-cv\\home\\cvezga\\dev\\omega-source\\omega";
	
	@Test
	public void shouldIndexAllfilesInPath() throws Exception{
		IndexBO bo = new IndexBO();
		
		bo.indexFilesOnPath(PATH);
		
		List<BitSetIndex> indexList = bo.getBitSetIndexList();
		
		assertTrue(indexList.size()>0);
		
		showIndexs(indexList);
		
		//CorpPricingSnapshot
		System.out.println("Matches:");
		Timer t = new Timer();
		List<File> files = bo.getMathes("Word","CorpPricingSnapshot");
		System.out.println("Time: "+t.getElapsedTime());
		System.out.println("Total Matching files: "+files.size());
		
		for(File f: files){
			System.out.println(f.getPath());
		}
		
		showUsage();
	}
	
	
	private void showIndexs(List<BitSetIndex> indexList){
		for(BitSetIndex index : indexList){
			System.out.println("\n==================================");
			System.out.println("["+index.getName()+"] ("+index.size()+")");
			System.out.println("Top 50");
			for(BitSetItem counts : index.getTopIndexCountList(50)){
				System.out.println(counts.getName()+" ("+counts.size()+")");
			}
		}
	}
	private void showUsage(){
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		 
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		System.out.println("free memory: " + format.format(freeMemory / 1024) );
		System.out.println("allocated memory: " + format.format(allocatedMemory / 1024) );
		System.out.println("max memory: " + format.format(maxMemory / 1024) );
		System.out.println("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) );
	}

}
