package edu.umb.cs681.hw14;
import java.util.concurrent.atomic.AtomicBoolean;

public class WithdrawRunnable implements Runnable{
	
	private ThreadSafeBankAccount account = null;
	public AtomicBoolean done = new AtomicBoolean(false);
		
	public WithdrawRunnable(ThreadSafeBankAccount account) {
		this.account = account;
	}
	
	public void setDone() {
		done.getAndSet(true);
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			if(done.get()) {
    			System.out.println(Thread.currentThread().getName() +"Money withdrawed!");
    			break;
			}
			
			System.out.println(Thread.currentThread().getName() +"Money Withdrawing!");
			account.withdraw(200);
			
			try {
				Thread.sleep(2000);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}
}