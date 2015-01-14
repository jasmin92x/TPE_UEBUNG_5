package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */

public class Kraftwerk implements Runnable {

	final static int PUMP_LEISTUNG = 1;

	final static int TEMPERATUR_KERNSCHMELZE = 2878;
	final static int TEMPERATUR_RHEIN = 10;
	final static int ERWAERMUNGS_KOEFFIZIENT = 100;
	final static int MONITORING_INTERVAL = 1000;
	boolean stopped = false;

	Reaktor reaktor;
	Kreislauf kreislauf;
	Pumpe pumpe;
	Thread monitor = new Thread(this);

	public Kraftwerk(int pumpleistung) {
		reaktor = new Reaktor(ERWAERMUNGS_KOEFFIZIENT, TEMPERATUR_RHEIN);
		kreislauf = new Kreislauf(TEMPERATUR_RHEIN);
		pumpe = new Pumpe(reaktor, kreislauf, pumpleistung);
	}

	public static void main(String[] args) {

		Kraftwerk kraftwerk = new Kraftwerk(PUMP_LEISTUNG);
		kraftwerk.start();

		BufferedReader console = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			console.readLine();
		} catch (IOException e) {
		}
		try {
			kraftwerk.stop();
		} catch (InterruptedException e) {
		}
	}

	public void start() {
		pumpe.start();
		reaktor.start();
		monitor.start();
	}

	public void stop() throws InterruptedException {
		if (!reaktor.worker.isAlive()) {
			pumpe.stop();
			pumpe.worker.join();
			return;
		}
		reaktor.stop();
		reaktor.worker.join();
		pumpe.stop();
		pumpe.worker.join();
		stopped = true;
		System.out.println("Reaktor wurde heruntergefahren");
	}

	@Override
	public void run() {
		while (!stopped) {
			try {
				System.out.println("Temperatur Reaktor: "
						+ reaktor.getTemperatur()
						+ ", Temperatur Rückfluss in Rhein: "
						+ kreislauf.getTR());
				if (reaktor.getTemperatur() > TEMPERATUR_KERNSCHMELZE) {
					System.out.println("Der Ernstfall ist eingetreten: GAU!!!");
					reaktor.stop();
					reaktor.worker.join();
					stopped = true;
					stop();
				}
				Thread.sleep(MONITORING_INTERVAL);
			} catch (Exception e) {
			}
		}
	}

}
