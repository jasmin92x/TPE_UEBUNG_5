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
	 * Hält die Temperatur für jedes Wasserelement. Das erste
	 * hängt am Tauscher für den Reaktor, das letzte am 
	 * Tauscher für den Rhein.
	 *  
	 */
	int[] elements = (int[]) Array.newInstance(int.class, NUMBER_OF_ELEMENTS);
	
	final int tRhein;
	
	/**
	 * Initialisiert die Instanzvariablen tRhein und elements mit der
	 * übergebenen Starttemperatur
	 * @param tRhein
	 */
	public Kreislauf(int tRhein) {
		this.tRhein = tRhein;
		for (int i = 0;i < elements.length; ++i) {
			elements[i] = tRhein;
		}
	}
	
	/**
	 * verschiebt die Temperaturen vom einen zum nächsten Element
	 * und setzt die Temperatur des ersten Elements anschließend
	 * auf die übergebene Temperatur
	 * 
	 * @param getauschteReaktortemperatur
	 */
	public void tauschen(int getauschteReaktortemperatur) {
		for (int i = elements.length - 1; i > 0; --i) {
			elements[i] = elements[i - 1];
		}
		elements[0] = getauschteReaktortemperatur;
//		System.out.printf("%d %d %d %d %d %d %d %d %d %d %d %d \n",
//				elements[0],
//				elements[1],
//				elements[2],
//				elements[3],
//				elements[4],
//				elements[5],
//				elements[6],
//				elements[7],
//				elements[8],
//				elements[9],
//				elements[10],
//				elements[11]
//				);
		return;
	}
	
	/**
	 * Liefert die getauschte Kreislauftemperatur am Rhein
	 * Benutzer: Pumpe, Monitor
	 * @return
	 */
	public int getTR() {
		return (elements[elements.length - 1] + tRhein) / 2; // Tauschereffekt am Rhein; 
	}

}

