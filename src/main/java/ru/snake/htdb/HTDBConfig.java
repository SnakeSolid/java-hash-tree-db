package ru.snake.htdb;

import java.io.File;
import java.util.Optional;

public class HTDBConfig {

	private int pageSize;

	private Optional<Integer> nPages;

	private File storagePath;

	public HTDBConfig() {
		this.pageSize = 128;
		this.nPages = Optional.empty();
		this.storagePath = new File(".");
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			throw new IllegalArgumentException(
					"Value of `pageSize` must be greater than zero, but " + pageSize + " given.");
		}

		this.pageSize = pageSize;
	}

	public Optional<Integer> getNPages() {
		return nPages;
	}

	public void setnPages(Optional<Integer> nPages) {
		if (nPages == null) {
			this.nPages = Optional.empty();
		} else if (nPages.isPresent() && nPages.get() <= 0) {
			throw new IllegalArgumentException(
					"Value of `nPages` must be greater than zero, but " + pageSize + " given.");
		}

		this.nPages = nPages;
	}

	public File getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(File storagePath) {
		if (!storagePath.exists()) {
			throw new IllegalArgumentException("Path `" + storagePath + "` does not exists.");
		} else if (!storagePath.isDirectory()) {
			throw new IllegalArgumentException("Path `" + storagePath + "` is not directory.");
		}

		this.storagePath = storagePath;
	}

	@Override
	public String toString() {
		return "HTDBConfig [pageSize=" + pageSize + ", nPages=" + nPages + ", storagePath=" + storagePath + "]";
	}

}
