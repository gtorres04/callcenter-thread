package com.gtorres.test.callcenter.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeEmployeeServiceImplTest {
	@Autowired
	private ITypeEmployeeService iTypeEmployeeService;
	/**
	 * Validar que encuentre el tipo de empleado por el id
	 */
	@Test
	public void findById() {
		Assert.assertNotNull(iTypeEmployeeService.findById(1l));
		Assert.assertEquals(iTypeEmployeeService.findById(1l).getName(), "OPERADOR");
		Assert.assertNotNull(iTypeEmployeeService.findById(2l));
		Assert.assertEquals(iTypeEmployeeService.findById(2l).getName(), "SUPERVISOR");
		Assert.assertNotNull(iTypeEmployeeService.findById(3l));
		Assert.assertEquals(iTypeEmployeeService.findById(3l).getName(), "DIRECTOR");
	}
}
