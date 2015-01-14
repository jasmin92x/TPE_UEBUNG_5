package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

import java.lang.reflect.Array;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */

public class Kreislauf {

	public static int NUMBER_OF_ELEMENTS = 12;

	/**
	 * H�lt die Temperatur f�r jedes Wasserelement. Das erste h�ngt am Tauscher
	 * f�r den Reaktor, das letzte am Tauscher f�r den Rhein.
	 * 
	 */
	int[] elements = (int[]) Array.newInstance(int.class, NUMBER_OF_ELEMENTS);

	final int tRhein;

	public Kreislauf(int tRhein) {
		this.tRhein = tRhein;
		for (int i = 0; i < elements.length; ++i) {
			elements[i] = tRhein;
		}
	}

	public void tauschen(int getauschteReaktortemperatur) {
		synchronized (this) {
			for (int i = elements.length - 1; i > 0; --i) {
				elements[i] = elements[i - 1];
			}
			elements[0] = getauschteReaktortemperatur;
		}
		return;
	}

	/**
	 * Liefert die getauschte Kreislauftemperatur am Rhein
	 * 
	 * @return
	 */
	public int getTR() {
		synchronized (this) {
			return (elements[elements.length - 1] + tRhein) / 2; // Tauschereffekt
																	// am Rhein;
		}
	}

}
