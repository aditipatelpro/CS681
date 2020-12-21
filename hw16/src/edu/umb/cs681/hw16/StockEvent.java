package edu.umb.cs681.hw16;

public class StockEvent {
    private String ticker;
    private float quote;

    public StockEvent(String ticker, float quote) {
        this.ticker = ticker;
        this.quote = quote;
    }
    //getters
    public String getTicker() {
        return ticker;
    }

    public float getQuote() {
        return quote;
    }
}