package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Test;

public class Tests {

	@Test
	public void getTRLiefertMittelWertBeiInitialisierung() {

		// arrange
		final int tRhein = 10;
		Kreislauf kreislauf = new Kreislauf(tRhein);
		// act
		int retVal = kreislauf.getTR();
		// assert
		assertEquals(tRhein, retVal);
	}
	
	@Test
	public void zwoelffachesTauschenVerschiebtElementMitEingangsTemperaturAnsEnde() {
		
		// arrange
		final int tRhein = 10;
		final int runtimeTemp = 346;
		
		Kreislauf kreislauf = new Kreislauf(tRhein);
		for (int i = 0; i < Kreislauf.NUMBER_OF_ELEMENTS; ++i) {
			kreislauf.tauschen(runtimeTemp);
		}
		int expected = (tRhein + runtimeTemp) /2;
	
		// act
		int retVal = kreislauf.getTR();
		// assert
		assertEquals(expected, retVal);
	}
	
	@Test
	public void kuehlenLiefertMittelwert() {
		// arrange
		final int tStart = 1372;
		final int tKuehl = 28;
		final int expected = (tStart + tKuehl) / 2;
		Reaktor reaktor = new Reaktor(1, tStart);
		// act
		int tRetVal = reaktor.kuehlen(tKuehl);
		// assert
		assertEquals(expected, tRetVal);
	}
	
}

