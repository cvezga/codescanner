package com.gft.codescanner.indexing;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitSetIndex {
	
	private String name;
	private Map<String,BitSetItem> bitsetItemMap;
	

	
	public BitSetIndex(String name){
		this.name = name;
		this.bitsetItemMap = new HashMap<String, BitSetItem>();
	}

	
	public void index(int id, String value){
		BitSetItem bs = getOrCreateBitSet(value);
		bs.set(id);
	}


	private BitSetItem getOrCreateBitSet(String value) {
		BitSetItem bs = this.bitsetItemMap.get(value);
		if(bs==null){
			bs = new BitSetItem(value);
			this.bitsetItemMap.put(value, bs);
		}
		return bs;
	}


	public String getName() {
		return name;
	}
	
	
	public List<BitSetItem> getIndexCountList(){
		List<BitSetItem> indexCounts = new ArrayList<BitSetItem>();
		for(BitSetItem entry : this.bitsetItemMap.values() ){
			
			indexCounts.add( entry );
		}
		
		return indexCounts;
	}


	public int size() {
		return bitsetItemMap.size();
	}


	public List<BitSetItem> getTopIndexCountList(int topNum) {
		
		List<BitSetItem> bitSetItems = new ArrayList<BitSetItem>(this.bitsetItemMap.values());
		
		Collections.sort(bitSetItems);
		
		List<BitSetItem> topIndexCounts = new ArrayList<BitSetItem>(topNum);
		
		if(topNum>bitSetItems.size()) topNum=bitSetItems.size();
		
		for(int i=0; i<topNum; i++){
			topIndexCounts.add(bitSetItems.get(i));
		}
		
		
		return topIndexCounts;
	}


	public BitSet getBitSet(String value) {
		BitSetItem bsi = bitsetItemMap.get(value);
		if(bsi!=null) return bsi.getBitset(); 
		return null;
	}
	
}
