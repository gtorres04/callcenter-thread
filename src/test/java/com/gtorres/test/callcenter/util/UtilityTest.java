package com.gtorres.test.callcenter.util;

import java.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UtilityTest {

	/**
	 * Tiempo de espera de 2 segundos.
	 */
	@Test
	public void timeOutOfEmployee() {
		int secondsToValid = 2;
		LocalTime localTimeBefore = LocalTime.now();
		Utility.timeOutOfEmployee(secondsToValid);
		LocalTime localTimeAfter = LocalTime.now();
		Assert.assertTrue(localTimeBefore.plusSeconds(secondsToValid).getHour() == localTimeAfter.getHour());
		Assert.assertTrue(localTimeBefore.plusSeconds(secondsToValid).getMinute() == localTimeAfter.getMinute());
		Assert.assertTrue(localTimeBefore.plusSeconds(secondsToValid).getSecond() == localTimeAfter.getSecond());
	}

	/**
	 * Validar numero aleatorio que este entre 1 - 5, 3 - 10 y 10 -15
	 */
	@Test
	public void randomNumber() {
		int random = Utility.randomNumber(1, 5);
		Assert.assertTrue(random >= 1 && random <= 5);
		
		random = Utility.randomNumber(1, 5);
		Assert.assertTrue(random >= 1 && random <= 5);
		
		random = Utility.randomNumber(3, 10);
		Assert.assertTrue(random >= 3 && random <= 10);
		
		random = Utility.randomNumber(3, 10);
		Assert.assertTrue(random >= 3 && random <= 10);
		
		random = Utility.randomNumber(10, 15);
		Assert.assertTrue(random >= 10 && random <= 15);
		
		random = Utility.randomNumber(10, 15);
		Assert.assertTrue(random >= 10 && random <= 15);
	}

}
