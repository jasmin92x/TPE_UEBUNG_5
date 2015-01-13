package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

public class Pumpe implements Runnable {

	final static int TEMPERATUR_RHEIN = 10;
	public int leistung;
	private boolean stopped = false;
	
	Kreislauf kreislauf = new Kreislauf(TEMPERATUR_RHEIN);
	Reaktor reaktor = new Reaktor();
	
	Thread worker = new Thread(this);
	
	private int interval = 1000 / leistung;
	
	public void setLeistung(int leistung) {
		this.leistung = leistung;
	}
	
	public int getLeistung() {
		return leistung;
	}
	
	public void run() {
		while (!stopped){
			try {
				pumpen();
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void pumpen() {
		
		// Reaktor kühlen
		int kuehlTemperatur = kreislauf.getTR();
		// Temperatur in Keislauf eingeben
		int temperaturReaktor = reaktor.kuehlen(kuehlTemperatur);
		// warten
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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

