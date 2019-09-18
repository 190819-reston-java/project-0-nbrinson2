package com.revature.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.service.Service;

public class UserInputMoneyFormatCheckerTest {
	
	// Testing string for correct format
	
	@Test
	public void nothingEntered() {
		assertFalse(Service.formatCheckerForMoney(""));
	}
	
	@Test
	public void onlySpaceEntered() {
		assertFalse(Service.formatCheckerForMoney(" "));
	}
	
	@Test
	public void onlyDotEntered() {
		assertFalse(Service.formatCheckerForMoney("."));
	}
	
	@Test
	public void multipleDotsEntered() {
		assertFalse(Service.formatCheckerForMoney("..."));
	}
	
	@Test
	public void dotsAndNumbers() {
		assertFalse(Service.formatCheckerForMoney("1.234.3.43"));
	}
	
	@Test
	public void onlyNumbers() {
		assertTrue(Service.formatCheckerForMoney("2398745"));
	}
	
	@Test
	public void oneNumber() {
		assertTrue(Service.formatCheckerForMoney("4"));
	}
	
	@Test
	public void oneNumberBeforeAndAfterDecimal() {
		assertTrue(Service.formatCheckerForMoney("7.6"));
	}
	
	@Test
	public void oneNumberBeforeAndTwoNumbersAfterDecimal() {
		assertTrue(Service.formatCheckerForMoney("3.25"));
	}
	
	@Test
	public void oneLetter() {
		assertFalse(Service.formatCheckerForMoney("f"));
	}
	
	@Test
	public void multipleLetters() {
		assertFalse(Service.formatCheckerForMoney("jfe"));
	}
	
	@Test
	public void lettersAndNumbers() {
		assertFalse(Service.formatCheckerForMoney("1658fhfeui"));
	}
	
	@Test
	public void lettersNumbersDecimal() {
		assertFalse(Service.formatCheckerForMoney("1ef65w."));
	}
	
	@Test
	public void lettersNumbersDecimals() {
		assertFalse(Service.formatCheckerForMoney("328.nfu.i"));
	}
	
	@Test
	public void twoLettersAfterDecimal() {
		assertFalse(Service.formatCheckerForMoney("321.fe"));
	}
	
	@Test
	public void oneLetterOneNumberAfterDecimal() {
		assertFalse(Service.formatCheckerForMoney("51.f4"));
	}
	
	@Test
	public void oneNumberOneLetterAfterDecimal() {
		assertFalse(Service.formatCheckerForMoney("7.6h"));
	}
	
	@Test
	public void twoLettersBeforeDecimal() {
		assertFalse(Service.formatCheckerForMoney("df.48"));
	}
	
	@Test
	public void oneLetterBeforeDecimal() {
		assertFalse(Service.formatCheckerForMoney("g.14"));
	}
	
	@Test
	public void oneNumberOneLetterBeforeDecimal() {
		assertFalse(Service.formatCheckerForMoney("7j.86"));
	}
	
	@Test
	public void oneLetterOneNumberBeforeDecimal() {
		assertFalse(Service.formatCheckerForMoney("i1.81"));
	}
	
	@Test
	public void dollarSign() {
		assertFalse(Service.formatCheckerForMoney("$1.58"));
	}
	
	@Test
	public void miscellaneousCharacters() {
		assertFalse(Service.formatCheckerForMoney(".,['./-*"));
	}
	
	@Test
	public void miscellaneousCharacterUsedInsteadOfDecimal() {
		assertFalse(Service.formatCheckerForMoney("5/48"));
	}
	
		
}
