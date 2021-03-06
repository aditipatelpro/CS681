package edu.umb.cs681.hw18;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {	
	private Map<String, Float> ticker_quote = new HashMap<>();
	private ReentrantLock lock = new ReentrantLock();

	public void changeQuote(String tickers, float quote) {		
		lock.lock();
        ticker_quote.put(tickers, quote);
        this.setChanged();
		this.notifyObservers(new StockEvent(tickers, quote));
        lock.unlock();
	}
	
	public void setQuote(String tickers, float quote) {
        lock.lock();
        ticker_quote.put(tickers, quote);
        this.setChanged();
        notifyObservers(new StockEvent(tickers, quote));
        lock.unlock();
    }
}