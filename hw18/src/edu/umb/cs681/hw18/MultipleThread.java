package edu.umb.cs681.hw18;

import java.util.Random;

public class MultipleThread {

    public static void main(String[] args) {
    	
    	DJIAQuoteObservable d = new DJIAQuoteObservable();
    	String quo = "TQQQ";
    	Float val = 300.0f;
    	
        StockQuoteObservable s = new StockQuoteObservable();
        String code = "TSLA";
        Float value = 900.0f;
        
        Random random = new Random();
        
        System.out.println("########################### DJIAQuoteObservable  ########################");
        
        d.addObserver((Observable o, Object obj) -> {
            Float t = ((DJIAEvent) obj).getDjia();
            System.out.println("Observer 1 - DIJA event: " + t);
        });
        
        d.addObserver((Observable o, Object obj) -> {
            Float t = ((DJIAEvent) obj).getDjia();
            System.out.println("Observer 2 - DIJA event: " + t);
        });
        
        d.addObserver((Observable o, Object obj) -> {
            Float t = ((DJIAEvent) obj).getDjia();
            System.out.println("Observer 3 - DIJA event: " + t);
        });
        
        d.addObserver((Observable o, Object obj) -> {
            Float t = ((DJIAEvent) obj).getDjia();
            System.out.println(" Observer 4 - DIJA event: " + t);
        });
        
        System.out.println("Observers Count: " + d.countObserver());        

        System.out.println("Add new DJIAQouote: " + quo);
        d.setQuote(val);

        val = 500.0f;
        System.out.println("DJIA is changed");
        d.changeQuote(val);
        
        System.out.println("########################### StockQuoteObservable ####################################");

        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 1 - Stock event: " + ticker + " " + quote);
        });

        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 2 - Stock event: " + ticker + " " + quote);
        });
        
        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 3 - Stock event: " + ticker + " " + quote);
        });

        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 4 - Stock event: " + ticker + " " + quote);
        });

        System.out.println("Observers Count is " + s.countObserver());        

        System.out.println("Add new Stock: " + code);
        s.setQuote(code, value);

        value = 200.0f;
        System.out.println("SQO  is changed");
        s.changeQuote(code, value);
        
        System.out.println("\n################################  MultiThread ##################################");
        
        Thread d1  = new Thread(() ->{ d.setQuote(random.nextFloat()*100f + 13000f); 
        							   d.notifyObservers(new DJIAEvent(random.nextFloat()*100f + 13000f));});
        Thread d2  = new Thread(() ->{ d.setQuote(random.nextFloat()*100f + 13000f); 
        							   d.notifyObservers(new DJIAEvent(random.nextFloat()*100f + 13000f));});
        Thread d3  = new Thread(() ->{ d.setQuote(random.nextFloat()*100f + 13000f); 
        							   d.notifyObservers(new DJIAEvent(random.nextFloat()*100f + 13000f));});
        Thread d4  = new Thread(() ->{ d.setQuote(random.nextFloat()*100f + 13000f); 
        							   d.notifyObservers(new DJIAEvent(random.nextFloat()*100f + 13000f));});
		Thread d5  = new Thread(() ->{ d.setQuote(random.nextFloat()*100f + 13000f); 
									   d.notifyObservers(new DJIAEvent(random.nextFloat()*100f + 13000f));});
		Thread d6  = new Thread(() ->{ d.setQuote(random.nextFloat()*100f + 13000f); 
								       d.notifyObservers(new DJIAEvent(random.nextFloat()*100f + 13000f));});
		
		d1.start();
		d2.start();
		d3.start();
		d4.start();
		d5.start();
		d6.start();
		
		
		Thread s1  = new Thread(() ->{ s.setQuote("SQO", random.nextFloat()*10f + 200f); 
				   s.notifyObservers(new StockEvent("SQO", random.nextFloat()*10f + 200f));});
		Thread s2  = new Thread(() ->{ s.setQuote("SQO", random.nextFloat()*10f + 200f); 
				   s.notifyObservers(new StockEvent("SQO", random.nextFloat()*10f + 200f));});
		Thread s3  = new Thread(() ->{ s.setQuote("SQO", random.nextFloat()*10f + 200f); 
				   s.notifyObservers(new StockEvent("SQO", random.nextFloat()*10f + 200f));});
		Thread s4  = new Thread(() ->{ s.setQuote("SQO", random.nextFloat()*10f + 200f); 
				   s.notifyObservers(new StockEvent("SQO", random.nextFloat()*10f + 200f));});
		Thread s5  = new Thread(() ->{ s.setQuote("SQO", random.nextFloat()*10f + 200f); 
				   s.notifyObservers(new StockEvent("SQO", random.nextFloat()*10f + 200f));});
		Thread s6  = new Thread(() ->{ s.setQuote("SQO", random.nextFloat()*10f + 200f); 
			       s.notifyObservers(new StockEvent("SQO", random.nextFloat()*10f + 200f));});
		
		s1.start();
		s2.start();
		s3.start();
		s4.start();
		s5.start();
		s6.start();
		
		        
		try {
			d1.join();
			d2.join();
			d3.join();
			d4.join();
			d5.join();
			d6.join();
			
			s1.join();
			s2.join();
			s3.join();
			s4.join();
			s5.join();
			s6.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 	
    }
}