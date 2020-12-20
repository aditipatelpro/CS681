package edu.umb.cs681.hw11;

public class MultipleThread implements Runnable {
	
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
    	
    	Thread T1 = new Thread(new MultipleThread());
		Thread T2 = new Thread(new MultipleThread());
		
		T1.start();
		T2.start();
		
		try {
			T1.join();
			T2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}