package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem implements Runnable {

	private String ownerName;
	private LocalDateTime lastModified;
	private static APFS instance = null;

	public APFS(String ownerName) {
		this.ownerName = ownerName;
		this.lastModified = LocalDateTime.now();
	}

	public APFS getInstance() {
		if (instance == null) {
			instance = new APFS(ownerName);
		}
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		return new ApfsDirectory(null, "root");
	}

	public ApfsDirectory getRootDir() {
		ApfsDirectory root = (ApfsDirectory) this.getRoot();
		return root;
	}

	public void setRootDir(ApfsDirectory root) {
		super.setRoot(root);
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}

	public void run() {
		System.out.println("\nThread #" + Thread.currentThread().getId());
		System.out.println("Size of " + getRootDir().getName() + " dir: " + getRootDir().getTotalSize());
		LinkedList<ApfsElement> rootChildren = getRootDir().getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println("\n-->"+child.getName());
			LinkedList<ApfsElement> child1Children =child.getChildren();
			for (ApfsElement child1 : child1Children) {
				System.out.println("------>"+child1.getName());
				LinkedList<ApfsElement> child2Children =child1.getChildren();
				for (ApfsElement child2 : child2Children) {
					System.out.println("------>"+child2.getName());
				}
			}
		}
	}

	public static void main(String args[]) {

		APFS apfs = new APFS("Aditi Patel");

		apfs.initFileSystem("Project Repository", 10000);
		ApfsDirectory root = apfs.getRootDir();

		ApfsDirectory applications = new ApfsDirectory(root, "applications");
		root.appendChild(applications);
		ApfsFile a, b;
		a = new ApfsFile(applications, "a", 50);
		b = new ApfsFile(applications, "b", 100);
		applications.appendChild(a);
		applications.appendChild(b);

		Thread t1 = new Thread(apfs);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ApfsDirectory repo  = new ApfsDirectory(root, "repo");
		root.appendChild((repo));
		ApfsFile c, d;
		c = new ApfsFile(repo, "c", 150);
		d = new ApfsFile(repo, "d", 200);
		repo.appendChild(c);
		repo.appendChild(d);

		Thread t2 = new Thread(apfs);
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ApfsDirectory code = new ApfsDirectory(repo, "code");
		repo.appendChild(code);
		ApfsFile e, f;
		e = new ApfsFile(code, "e", 250);
		f = new ApfsFile(code, "f", 300);
		code.appendChild(e);
		code.appendChild(f);

		Thread t3 = new Thread(apfs);
		t3.start();
		try {
			t3.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ApfsLink x, y;
		x = new ApfsLink(repo, "x", applications);
		y = new ApfsLink(code, "y", b);
		repo.appendChild(x);
		code.appendChild(y);

		Thread t4 = new Thread(apfs);
		t4.start();
		try {
			t4.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}