package com.gft.codescanner.indexing;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BitSetIndex {
	
	private String name;
	private Map<String,BitSet> bitsetMap;
	
	public class IndexCounts {
		private String value;
		int count;
		
		public IndexCounts(String value, int count) {
			super();
			this.value = value;
			this.count = count;
		}
		
		public String getValue() {
			return value;
		}
		
		public int getCount() {
			return count;
		}
		
		
	}
	
	public BitSetIndex(String name){
		this.name = name;
		this.bitsetMap = new HashMap<String, BitSet>();
	}

	
	public void index(int id, String value){
		BitSet bs = getOrCreateBitSet(value);
		bs.set(id);
	}


	private BitSet getOrCreateBitSet(String value) {
		BitSet bs = this.bitsetMap.get(value);
		if(bs==null){
			bs = new BitSet();
			this.bitsetMap.put(value, bs);
		}
		return bs;
	}


	public String getName() {
		return name;
	}
	
	
	public List<IndexCounts> getIndexCountList(){
		List<IndexCounts> indexCounts = new ArrayList<IndexCounts>();
		for(Entry<String, BitSet> entry : this.bitsetMap.entrySet() ){
			String value = entry.getKey();
			BitSet bs = entry.getValue();
			indexCounts.add( new IndexCounts(value, bs.cardinality()) );
		}
		
		return indexCounts;
	}


	public int size() {
		return bitsetMap.size();
	}
	
}
