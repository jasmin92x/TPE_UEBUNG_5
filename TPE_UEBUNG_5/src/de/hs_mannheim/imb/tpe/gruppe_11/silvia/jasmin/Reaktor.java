package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

public class Reaktor implements Runnable {
	
	public static final int INITIAL_TEMPERATURE = 10; // Temperatur beim Start
	public static final int HEAT_COEFFICIENT = 42; // Erwärmungskoeffizient
	boolean stopped =false;
	
	int temperatur = INITIAL_TEMPERATURE;
	
	final int interval = 1000 / HEAT_COEFFICIENT; // Wartezeit in Millisekunden
	
	Thread worker = new Thread(this);
	
	public int kuehlen(int kuehlTemperatur) {
		synchronized(this) {
			return temperatur = (temperatur + kuehlTemperatur) / 2;
		}
	}
	
	public void heizen() {
		synchronized(this) {
			temperatur++;
		}
	}
	
	public int getTemperatur() {
		synchronized(this) {
			return temperatur;
		}
	}
	
	public void run() {
		while (!stopped) {
			heizen();
			try {
				Thread.sleep(interval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		
		worker.start();
	}
	
	public void stop() {
		stopped = true;
	}
	

}

