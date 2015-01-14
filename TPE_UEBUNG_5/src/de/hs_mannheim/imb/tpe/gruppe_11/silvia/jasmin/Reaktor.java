package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public class Reaktor implements Runnable {
	
	final int erwaermungsKoeffizient;
	final int interval; // Wartezeit auf 1° Temperaturerhöhung in Millisekunden
	
	boolean stopped = false;
	
	/**
	 * aktuelle Reaktortemperatur
	 */
	int temperatur;
	
	public Reaktor(int erwaermungsKoeffizient, int startTemperatur) {
		this.temperatur = startTemperatur;
		this.erwaermungsKoeffizient = erwaermungsKoeffizient;
		interval = 1000 / erwaermungsKoeffizient;
		System.out.println("Reaktor - intervall: " + interval);
	}
	
	/**
	 * lässt Rektorsimulation laufen
	 */
	public Thread worker = new Thread(this);
	
	public int kuehlen(int kuehlTemperatur) {
		synchronized(this) {						//heizen könnte in die Quere kommen und andersrum auch
			return temperatur = (temperatur + kuehlTemperatur) / 2;
		}
	}
	
	/**
	 * Temperatur um 1° erhöhen
	 */
	public void heizen() {
		synchronized(this) {
			temperatur++;
		}
	}
	
	/**
	 * aktuelle Temperatur abfragen
	 * @return
	 */
	public int getTemperatur() {
		synchronized(this) {
			return temperatur;
		}
	}
	
	/**
	 * simuliert permanente Erwärmung
	 */
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
