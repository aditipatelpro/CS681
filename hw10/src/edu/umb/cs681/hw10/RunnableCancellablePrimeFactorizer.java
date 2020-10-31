package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer (long dividend, long from, long to){
        super(dividend, from, to);
    }
    public void setDone() {
        lock.lock();
        try { done = false; }
        finally { lock.unlock(); }
    }

    public void generatePrimeFactors(){
        long divisor = from;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            try{
                if( divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if(divisor==2){ divisor++; }
                    else{ divisor += 2; }
                }
            } finally { lock.unlock();}
        }
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(84, 2,(long) Math.sqrt(84));
        Thread thread = new Thread(gen);

        thread.start();
        gen.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Prime factors are: " + gen.getPrimeFactors() + "\n");
    }
}