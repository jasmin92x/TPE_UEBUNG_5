package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public class Reaktor implements Runnable {

	final int erwaermungsKoeffizient;
	boolean stopped = false;
	int temperatur;
	final int interval; // Wartezeit auf 1° Temperaturerhöhung in Millisekunden

	public Reaktor(int erwaermungsKoeffizient, int startTemperatur) {
		this.temperatur = startTemperatur;
		this.erwaermungsKoeffizient = erwaermungsKoeffizient;
		interval = 1000 / erwaermungsKoeffizient;
	}

	public Thread worker = new Thread(this);

	public int kuehlen(int kuehlTemperatur) {
		synchronized (this) {
			return temperatur = (temperatur + kuehlTemperatur) / 2;
		}
	}

	public void heizen() {
		synchronized (this) {
			temperatur++;
		}
	}

	public int getTemperatur() {
		synchronized (this) {
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
