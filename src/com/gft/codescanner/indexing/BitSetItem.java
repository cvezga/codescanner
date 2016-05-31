package com.gft.codescanner.indexing;

import java.util.BitSet;

public class BitSetItem implements Comparable<BitSetItem> {
	
	private String name;
	private BitSet bitset;
	
	public BitSetItem(String name) {
		super();
		this.name = name;
		this.bitset = new BitSet();
	}

	public int compareTo(BitSetItem o) {
		return o.size() - size();
	}

	 

	public int size(){
		return this.bitset.cardinality();
	}

	public String getName() {
		return name;
	}

	public BitSet getBitset() {
		return bitset;
	}

	public void set(int id) {
		this.bitset.set(id);
		
	}
	 
	
	

}
