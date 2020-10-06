package edu.umb.cs681.hw01;

public class MyExampleObserver {

    public static void main (String args[]){

        StockQuoteObservable stockQuote = new StockQuoteObservable();
        stockQuote.addObserver((Observable o, Object obj) -> {
            System.out.println("Observer 1 of stock notified.");
        });
        stockQuote.changeQuote("GOOG", 1416);
        stockQuote.addObserver((Observable o, Object obj) -> {
            System.out.println("Observer 2 of stock notified.");
        });
        stockQuote.changeQuote("AAPL", 150);

        DJIAQuoteObservable djiaQuote = new DJIAQuoteObservable();
        djiaQuote.addObserver((Observable o, Object obj) -> {
            System.out.println("Observer 1 of DJIA notified");
        });
        djiaQuote.changeQuote("TSLA", 450);
        djiaQuote.addObserver((Observable o, Object obj) -> {
            System.out.println("Observer 2 of DJIA notified");
        });
        djiaQuote.changeQuote("MSFT", 160);
    }

}