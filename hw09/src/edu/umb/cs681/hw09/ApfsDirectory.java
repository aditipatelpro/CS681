package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ApfsDirectory extends ApfsElement {

	private LinkedList<ApfsElement> children = new LinkedList<ApfsElement>();
	private LinkedList<ApfsFile> files = new LinkedList<ApfsFile>();
	private LinkedList<ApfsDirectory> subdir = new LinkedList<ApfsDirectory>();

	public ApfsDirectory(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, String ownerName,
			LocalDateTime modifiedTime) {
		super(parent, name, 0, creationTime, ownerName, modifiedTime);
	}

	public LinkedList<ApfsElement> getChildren() {
		return this.children;
	}

	public void appendChild(ApfsElement child) {
		this.children.add(child);
		child.setParent(this);
	}

	public int countChildren() {
		return this.children.size();
	}

	public boolean isDirectory() {
		return true;
	}

	public boolean isFile() {
		return false;
	}

	public boolean isLink() {
		return false;
	}

	public LinkedList<ApfsFile> getFiles() {
		for (ApfsElement element : children) {
			if (element.isFile()) {
				files.add((ApfsFile) element);
			}
		}
		return files;
	}

	public LinkedList<ApfsDirectory> getSubDirectories() {
		for (FSElement element : children) {
			if (element.isDirectory()) {
				subdir.add((ApfsDirectory) element);
			}
		}
		return subdir;
	}

	public int getTotalSize() {
		int totalSize = 0;
		for (FSElement element : children) {
			if (element.isDirectory()) {
				totalSize += ((ApfsDirectory) element).getTotalSize();
			} else {
				totalSize += element.getSize();
			}
		}
		return totalSize;
	}

}