package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

import java.lang.reflect.Array;

public class Kreislauf {
	
	/**
	 * H�lt die Temperatur f�r jedes Wasserelement. Das erste
	 * h�ngt am Tauscher f�r den Reaktor, das letzte am 
	 * Tauscher f�r den Rhein.
	 *  
	 *  @author Jasmin Cano, Silvia Yildiz
	 */
	int[] elements = (int[]) Array.newInstance(int.class, 12);
	
	final int tRhein;
	
	public Kreislauf(int tRhein) {
		this.tRhein = tRhein;
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
	 * Liefert die Kreislauftemperatur am Rhein
	 * @return
	 */
	public int getTR() {
		synchronized(this) {
			return (elements[elements.length - 1] + tRhein) / 2; // Tauschereffekt am Rhein; 
		}
	}

}

