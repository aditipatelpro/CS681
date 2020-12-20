package edu.umb.cs681.hw12;

public class MultipleThread implements Runnable {

    public void run() {
    	
        Customer customer = new Customer(new Address("1 south point drive", "Boston", "MA", 02125));
        System.out.println("Customer's original address	is "+ customer.getAddress());
        customer.setAddress(customer.getAddress().change("4822 Zachary lane", "Sugat land", "TX", 77479));
        System.out.println("Customer's address is changed to "+ customer.getAddress());
        customer.setAddress(new Address("48 Washington St", "Norwood", "MA", 02060));
        System.out.println("Customer's new address is set to "+ customer.getAddress());
    }

    public static void main(String[] args) {
    	
    	Thread T1 = new Thread(new MultiThread());
		Thread T2 = new Thread(new MultiThread());
		Thread T3 = new Thread(new MultiThread());
		Thread T4 = new Thread(new MultiThread());
		
		T1.start();
		T2.start();
		T3.start();
		T4.start();
		
		try {
			T1.join();
			T2.join();
			T3.join();
			T4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}        
    }
}