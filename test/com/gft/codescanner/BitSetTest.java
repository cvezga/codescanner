package com.gft.codescanner;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Test;

public class BitSetTest {
	
	
	@Test
	public void shouldCountNumberOfBitSetted(){
		BitSet bs = new BitSet();
		
		bs.set(0);
		bs.set(10);
		bs.set(100);
		
		assertEquals(3, bs.cardinality());
	}

}
