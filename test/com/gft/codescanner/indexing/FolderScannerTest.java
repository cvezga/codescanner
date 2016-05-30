package com.gft.codescanner.indexing;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FolderScannerTest {

	@Test
	public void shouldScannFolder() throws Exception {

		List<File> files = new FolderScanner(System.getProperty("user.dir")).getFileList();

		assertTrue(files.size() > 0);

	}

	@Test
	public void shouldGetAOnlyJavaFiles() throws Exception {

		List<String> include = Arrays.asList(".*\\.java");
		
		List<File> files = new FolderScanner(System.getProperty("user.dir"), include, null ).getFileList();
		
		assertTrue(files.size() > 0);

	}

	@Test
	public void shouldGetMdFile() throws Exception {

		List<String> include = Arrays.asList(".*\\.md");
		
		List<File> files = new FolderScanner(System.getProperty("user.dir"), include, null ).getFileList();
		
		assertTrue(files.size() == 1);

	}
}
