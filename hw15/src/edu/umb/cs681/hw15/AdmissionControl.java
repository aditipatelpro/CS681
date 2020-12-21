package edu.umb.cs681.hw15;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {

	private int currentVisitors = 0;
	private ReentrantLock lock = new ReentrantLock();

	private Condition sufficientCondition = lock.newCondition();
	private Condition lessThanLimitCondition = lock.newCondition();

	public void enter() {

		lock.lock();

		try {

			System.out.println(Thread.currentThread().getName() + "Total Current Visitors Count	: " + currentVisitors);

			while (currentVisitors >= 7) {
				try {
					System.out.println(Thread.currentThread().getName() + "Visitor count limit exceeded");
					sufficientCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}

			currentVisitors++;
			System.out.println(Thread.currentThread().getName() + "Current Visitors Count updated to " + currentVisitors);
			lessThanLimitCondition.signalAll();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			lock.unlock();
		}
	}

	public void exit() {

		lock.lock();

		try {
			System.out.println(Thread.currentThread().getName() + "Total Current Visitors Count	: " + currentVisitors);

			while (currentVisitors <= 0) {
				try {
					System.out.println(Thread.currentThread().getName() + " No Visitors are waiting!");
					lessThanLimitCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}

			currentVisitors--;

			System.out.println(Thread.currentThread().getName() + "Current Visitors Count updated to " + currentVisitors);
			sufficientCondition.signalAll();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public int visitorsCount() {
		lock.lock();
		try {
			return currentVisitors;
		} finally {
			lock.unlock();
		}
	}
}