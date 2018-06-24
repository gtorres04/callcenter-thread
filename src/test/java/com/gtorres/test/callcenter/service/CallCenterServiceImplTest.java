package com.gtorres.test.callcenter.service;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gtorres.test.callcenter.service.impl.CallCenterServiceImpl;
import com.gtorres.test.callcenter.service.impl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallCenterServiceImplTest {
	@Autowired
	private ITypeEmployeeService iTypeEmployeeService;
	/**
	 * Validar que cree un operador
	 */
	@Test
	public void createEmployeeOperator() {
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, null, iTypeEmployeeService, null);
		iCallCenterService.createEmployeeOperator("Operador");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l), false));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l),false).get(0).getName(), "Operador");
		
	}
	
	/**
	 * Validar que cree un supervisor
	 */
	@Test
	public void createEmployeeSupervisor() {
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, null, iTypeEmployeeService, null);
		iCallCenterService.createEmployeeSupervisor("Supervisor");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l), false));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l),false).get(0).getName(), "Supervisor");
		
	}
	
	/**
	 * Validar que cree un director
	 */
	@Test
	public void createEmployeeDirector() {
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, null, iTypeEmployeeService, null);
		iCallCenterService.createEmployeeDirector("Director");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(3l), false));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(3l),false).get(0).getName(), "Director");
	}
	
	/**
	 * Validar que cree varios operadores
	 */
	@Test
	public void createEmployeesOperator() {
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, null, iTypeEmployeeService, null);
		iCallCenterService.createEmployeesOperator("Operador 1", "Operador 2");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l), false).get(0));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l),false).get(0).getName(), "Operador 1");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l), false).get(1));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l),false).get(1).getName(), "Operador 2");
	}
	
	/**
	 * Validar que cree varios supervisores
	 */
	@Test
	public void createEmployeesSupervisor() {
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, null, iTypeEmployeeService, null);
		iCallCenterService.createEmployeesSupervisor("Supervisor 1", "Supervisor 2");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l), false).get(0));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l),false).get(0).getName(), "Supervisor 1");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l), false).get(1));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l),false).get(1).getName(), "Supervisor 2");
	}
	
	/**
	 * Validar que cree varios supervisores
	 */
	@Test
	public void createEmployeesDirector() {
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, null, iTypeEmployeeService, null);
		iCallCenterService.createEmployeesDirector("Director 1", "Director 2");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(3l), false).get(0));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(3l),false).get(0).getName(), "Director 1");
		Assert.assertNotNull(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(3l), false).get(1));
		Assert.assertEquals(iEmployeeService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(3l),false).get(1).getName(), "Director 2");
	}
	
}
