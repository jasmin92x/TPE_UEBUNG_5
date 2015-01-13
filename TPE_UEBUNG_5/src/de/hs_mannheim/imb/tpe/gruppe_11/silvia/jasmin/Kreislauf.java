package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

import java.lang.reflect.Array;

public class Kreislauf {
	
	/**
	 * Hält die Temperatur für jedes Wasserelement. Das erste
	 * hängt am Tauscher für den Reaktor, das letzte am 
	 * Tauscher für den Rhein.
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

