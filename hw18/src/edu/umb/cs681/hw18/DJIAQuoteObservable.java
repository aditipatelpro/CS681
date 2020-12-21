package edu.umb.cs681.hw18;
import java.util.concurrent.locks.ReentrantLock;

public class DJIAQuoteObservable extends Observable {
	
	private ReentrantLock lock = new ReentrantLock();

	public void changeQuote(float quote) {
		lock.lock();
		this.setChanged();
		this.notifyObservers(new DJIAEvent(quote));
		lock.unlock();
	}
	
	public void setQuote(float quote) {	
		lock.lock();
		this.setChanged();
		notifyObservers(new DJIAEvent(quote));
		lock.unlock();
	}

}