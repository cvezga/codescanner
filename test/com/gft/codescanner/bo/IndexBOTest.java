package com.gft.codescanner.bo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.gft.codescanner.indexing.BitSetIndex;
import com.gft.codescanner.indexing.BitSetIndex.IndexCounts;

public class IndexBOTest {
	
	@Test
	public void shouldIndexAllfilesInPath() throws Exception{
		IndexBO bo = new IndexBO();
		
		bo.indexFilesOnPath(".");
		
		List<BitSetIndex> indexList = bo.getBitSetIndexList();
		
		assertTrue(indexList.size()>0);
		
		for(BitSetIndex index : indexList){
			System.out.println("["+index.getName()+"]");
			for(IndexCounts counts : index.getIndexCountList()){
				System.out.println(counts.getValue()+":"+counts.getCount());
			}
		}
		
	}

}
