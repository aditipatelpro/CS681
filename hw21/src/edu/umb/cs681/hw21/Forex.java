package edu.umb.cs681.hw21;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Forex {
    private double performance;
    private double rate;
    private int spread;
    private String ticker;

    public Forex(double performance, double rate, int spread, String ticker){
        this.performance = performance;
        this.rate = rate;
        this.spread = spread;
        this.ticker = ticker;
    }

    public double getPerformance(){
        return this.performance;
    }

    public double getRate(){
        return this.rate;
    }

    public int getSpread(){
        return this.spread;
    }

    public String getTicker(){
        return this.ticker;
    }
    
    @Override
    public String toString() {
        return this.performance +" "+ this.rate+" "+ this.spread+" "+this.ticker;
    }

    public static void main(String args[]) {
        
        ArrayList<Forex> watchList = new ArrayList<>();

        watchList.add(new Forex(0.95,1.1748,10,"EUR/USD"));
        watchList.add(new Forex(0.75,1.2983,20,"GBP/USD"));
        watchList.add(new Forex(0.80,0.7052,15,"AUD/USD"));
        watchList.add(new Forex(0.65,1.3320,20,"USD/CAD"));

        long numberOfTickers = watchList.stream().count();
        System.out.println("Number of Tickers in Watchlist is " + numberOfTickers);

        Forex highestPerformance = watchList.stream()
                                            .parallel()
                                            .max(Comparator.comparing(Forex::getPerformance)).get();
        System.out.println("Highest performing pair is " + highestPerformance);

        Forex lowestSpread = watchList.stream()
                                      .parallel()
                                      .min(Comparator.comparing(Forex::getSpread)).get();
        System.out.println("Lowest spread in the pairs is " + lowestSpread);

        List<Forex> sortedByPerformance = watchList.stream()
                                                    .parallel()
                                                   .sorted(Comparator.comparing(Forex::getPerformance))
                                                   .collect(Collectors.toList());
        System.out.println("Sorted by performance");
        sortedByPerformance.forEach((Forex forex) -> System.out.println(forex.getTicker() + ": " + forex.getPerformance()));
    }
}
