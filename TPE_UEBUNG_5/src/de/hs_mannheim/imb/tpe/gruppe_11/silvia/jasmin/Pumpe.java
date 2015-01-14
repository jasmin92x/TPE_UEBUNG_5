package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

/**
 * Simuliert eine Pumpe
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public class Pumpe implements Runnable {

	public int leistung;
	private boolean stopped = false;
	private int interval;					//liefert den Zeitraum für ruhemodus
	
	Kreislauf kreislauf;					//Objektreferenz auf Kreislauf
	Reaktor reaktor;

	public Pumpe(Reaktor reaktor, Kreislauf kreislauf, int leistung) {
		this.reaktor = reaktor;
		this.kreislauf = kreislauf;
		this.leistung = leistung;			//Pumpvorgänge pro 1000 mil.sek.
		interval = 1000 / leistung;			//abstand zwischen zeit Pumpvorgängen
	}

	public Thread worker = new Thread(this);  

	public void run() {
		while (!stopped) {
			try {
				pumpen();
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();			//printed die Exception aus
			}
		}
	}
/**
 * Simuliert einen Pumpvorgang
 */
	public void pumpen() {

		// Reaktor kühlen
		int kuehlTemperatur = kreislauf.getTR();
		// Temperatur in Keislauf eingeben
		int temperaturReaktor = reaktor.kuehlen(kuehlTemperatur);
		// tauschen
		kreislauf.tauschen(temperaturReaktor);
		// warten
		
	}

	public void start() {
		worker.start();
	}

	public void stop() {
		stopped = true;
	}

}
