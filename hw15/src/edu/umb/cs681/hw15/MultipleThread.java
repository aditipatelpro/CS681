package edu.umb.cs681.hw15;

public class MultipleThread {

	public static void main(String[] args) {

		AdmissionControl admission = new AdmissionControl();
		EntranceHandler entrance = new EntranceHandler(admission);
		ExitHandler exit_handler = new ExitHandler(admission);
		AdmissionMonitor monitor = new AdmissionMonitor(admission);

		Thread TEntrance = new Thread(entrance);
		Thread TExit = new Thread(exit_handler);
		Thread TMonitor = new Thread(monitor);

		TEntrance.start();
		TExit.start();
		TMonitor.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		entrance.setDone();
		exit_handler.setDone();
		monitor.setDone();

		TEntrance.interrupt();
		TExit.interrupt();
		TMonitor.interrupt();

		try {
			TEntrance.join();
			TExit.join();
			TMonitor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}