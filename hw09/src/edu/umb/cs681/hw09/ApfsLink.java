package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public class ApfsLink extends ApfsElement {

	private ApfsElement target;

	public ApfsLink(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, ApfsElement target,
			String ownerName, LocalDateTime modifiedDate) {
		super(parent, name, size, creationTime, ownerName, modifiedDate);
		this.target = target;
	}

	public boolean isLink() {
		return true;
	}

	public boolean isDirectory() {
		return false;
	}

	public boolean isFile() {
		return false;
	}

	public int getTargetSize() {
		if (target.isDirectory()) {
			return ((ApfsDirectory) target).getTotalSize();
		} else if (target.isLink()) {
			return 0;
		} else {
			return target.getSize();
		}
	}

	public boolean targetisLink() {
		return target.isLink();
	}

	public boolean targetisFile() {
		return target.isFile();
	}

	public boolean targetisDirectory() {
		return target.isDirectory();
	}

	public void setTarget(ApfsElement target) {
		this.target = target;
	}

	public ApfsElement getTarget() {
		return this.target;
	}

}