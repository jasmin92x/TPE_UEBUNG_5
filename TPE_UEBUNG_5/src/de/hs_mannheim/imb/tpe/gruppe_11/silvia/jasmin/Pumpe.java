package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public class Pumpe implements Runnable {

	public int leistung;
	private boolean stopped = false;

	Kreislauf kreislauf;
	Reaktor reaktor;

	public Pumpe(Reaktor reaktor, Kreislauf kreislauf, int leistung) {
		this.reaktor = reaktor;
		this.kreislauf = kreislauf;
		this.leistung = leistung;
		interval = 1000 / leistung;
	}

	public Thread worker = new Thread(this);

	private int interval;

	public void setLeistung(int leistung) {
		this.leistung = leistung;
	}

	public int getLeistung() {
		return leistung;
	}

	public void run() {
		while (!stopped) {
			try {
				pumpen();
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pumpen() {

		// Reaktor kühlen
		int kuehlTemperatur = kreislauf.getTR();
		// Temperatur in Keislauf eingeben
		int temperaturReaktor = reaktor.kuehlen(kuehlTemperatur);
		// tauschen
		kreislauf.tauschen(temperaturReaktor);
		// warten
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		worker.start();
	}

	public void stop() {
		stopped = true;
	}

}
