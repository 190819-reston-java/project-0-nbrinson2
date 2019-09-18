
package com.revature.testing;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.exception.NotEnoughMoneyInAccountException;
import com.revature.service.Service;

public class WithdrawalMoneyTest {
	
	static Service testObject = new Service();
	static double amt = 0;
	static double expectedAmt = 0;
	static double startingBalance = 50;
	
	
	@Before
	public void setSelectedTestUser() {
		testObject.setSelectedUser(testObject.getUser(13));
		testObject.withdrawalMoney(testObject.getBalance() - startingBalance);
		
		
	}
	
	@Test
	public void smallAmountSuccess() {
		
		amt = 10;
		expectedAmt = startingBalance - amt;
		testObject.withdrawalMoney(amt);
		assertTrue(testObject.getBalance() == expectedAmt);
		
	}
	
	@Test (expected = NotEnoughMoneyInAccountException.class)
	public void smallAmountFailure() {
		amt = 60;
		testObject.withdrawalMoney(amt);
	}

	@Test
	public void largeAmountSuccess() {
		testObject.depositMoney(290000000);
		amt = 282250832;
		expectedAmt = testObject.getBalance() - amt;
		testObject.withdrawalMoney(amt);
		assertTrue(testObject.getBalance() == expectedAmt);
	}

	@Test (expected = NotEnoughMoneyInAccountException.class)
	public void largeAmountFailure() {
		amt = 282250832;
		testObject.withdrawalMoney(amt);
	}

	@Test (expected = NullPointerException.class)
	public void nullUser() {
		amt = 50;
		testObject.setSelectedUser(null);
		testObject.withdrawalMoney(amt);
	}

}
