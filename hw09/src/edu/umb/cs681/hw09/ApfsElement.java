package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ApfsElement extends FSElement {
	private String ownerName;
	private LocalDateTime modifiedTime;
	private LinkedList<ApfsElement> ApfsChildren = new LinkedList<ApfsElement>();

	public ApfsElement(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, String ownerName,
			LocalDateTime modifiedTime) {
		super(parent, name, size, creationTime);
		this.ownerName = ownerName;
		this.modifiedTime = modifiedTime;
	}

	public LinkedList<ApfsElement> getChildren() {
		return this.ApfsChildren;
	}

	public void appendChild(FSElement child) {
		this.ApfsChildren.add((ApfsElement) child);
	}

	public boolean isDirectory() {
		return false;
	};

	public boolean isFile() {
		return false;
	}

	public boolean isLink() {
		return false;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedTime;
	}

	public void setModifiedDate(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}