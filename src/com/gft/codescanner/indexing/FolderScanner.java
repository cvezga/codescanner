package com.gft.codescanner.indexing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderScanner {

	private String path;
	private List<File> fileList;
	private List<String> includePatternList;
	private List<String> excludePatternList;
	
	public FolderScanner(String path) throws Exception {
		this(path,null,null);
	}

	public FolderScanner(String path, List<String> includePatternList, List<String> excludePatternList)
			throws Exception {
		this.path = path;
		this.fileList = new ArrayList<File>();
		this.includePatternList = includePatternList;
		this.excludePatternList = excludePatternList;
		init();
	}

	public FolderScanner() {
		// TODO Auto-generated constructor stub
	}

	private void init() throws Exception {
		File dir = new File(this.path);
		if (!dir.isDirectory()) {
			throw new Exception("Path is not a directory");
		}

		scanFolder(dir);

	}

	private void scanFolder(File dir) {
		File[] files = dir.listFiles();
		for (File f : files) {
			if (!f.isDirectory() && f.isFile() && !f.isHidden()) {
				addFile(f);
			} else if (f.isDirectory() && !f.isHidden()) {
				scanFolder(f);
			}
		}
	}

	private void addFile(File f) {
		if (matchesInclude(f) && !matchesExclude(f)) {
			fileList.add(f);
		}
	}

	private boolean matchesInclude(File f) {
		if (this.includePatternList == null || this.includePatternList.isEmpty())
			return true;

		return matchesPattern(f.getPath(), this.includePatternList);
	}

	private boolean matchesExclude(File f) {
		if (this.excludePatternList == null || this.excludePatternList.isEmpty())
			return false;

		return matchesPattern(f.getPath(), this.excludePatternList);
	}

	private boolean matchesPattern(String text, List<String> patterns) {
		boolean rtn = false;

		for (String pattern : patterns) {
			if (text.matches(pattern)) {
				rtn = true;
				break;
			}
		}

		return rtn;
	}

	public List<File> getFileList() {
		return this.fileList;
	}

}
