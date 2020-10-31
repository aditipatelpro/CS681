package edu.umb.cs681.hw09;

import java.util.LinkedList;

public abstract class FileSystem {
	protected static String name;
	protected static int capacity;
	protected static int id;
	private LinkedList<FSElement> rootDirs = new LinkedList<FSElement>();;

	protected abstract FSElement createDefaultRoot();

	public FSElement initFileSystem(String name, int capacity) {
		FileSystem.name = name;
		FileSystem.capacity = capacity;

		FSElement root = createDefaultRoot();
		if (root.isDirectory() && capacity >= root.getSize()) {
			setRoot(root);
			FileSystem.id = root.hashCode();
			return root;
		} else {
			return null;
		}
	}

	protected void setRoot(FSElement root) {
		rootDirs.add(root);
	}

	public static String getName() {
		return name;
	}

	public static int getCapacity() {
		return capacity;
	}

	public static int getId() {
		return id;
	}

}