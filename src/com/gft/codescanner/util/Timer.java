package com.gft.codescanner.util;

public class Timer {
	
	private long startTime;
	
	public Timer(){
		this.startTime = System.currentTimeMillis();
	}

	public long getElapsedTime(){
		return System.currentTimeMillis() - startTime;
	}
	
}
