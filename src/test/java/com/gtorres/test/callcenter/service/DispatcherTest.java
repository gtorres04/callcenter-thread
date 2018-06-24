package com.gtorres.test.callcenter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gtorres.test.callcenter.UserRunnable;
import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.service.ICallCenterService;
import com.gtorres.test.callcenter.service.impl.CallCenterServiceImpl;
import com.gtorres.test.callcenter.service.impl.CallsServiceImpl;
import com.gtorres.test.callcenter.service.impl.Dispatcher;
import com.gtorres.test.callcenter.service.impl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatcherTest {

	@Autowired
	private ITypeEmployeeService iTypeEmployeeService;

	/**
	 * 2 llamadas concurrentes con 2 Operadores, 2 Supervisores y 2 Directores.
	 */
	@Test
	public void dispatcherCall_2llamadasConcurrentes() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ExecutorService executorServiceEmployees = Executors.newFixedThreadPool(6);
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, iCallsService,
				iTypeEmployeeService, executorServiceEmployees);
		Dispatcher dispatcher = new Dispatcher(iCallCenterService);
		List<UserRunnable> listUser = new ArrayList<>();
		iCallCenterService.createEmployeesOperator("Op1", "Op2");
		iCallCenterService.createEmployeesSupervisor("Sup1", "Sup2");
		iCallCenterService.createEmployeesDirector("Dir1", "Dir2");
		int numberThread = 2;
		for (int i = 0; i < numberThread; i++) {
			listUser.add(new UserRunnable(new Call(i + 1l), dispatcher));
		}
		final ExecutorService executorService = Executors.newFixedThreadPool(numberThread);
		for (UserRunnable userRunnable : listUser) {
			executorService.execute(userRunnable);
		}
		executorService.shutdown();
		while (!executorService.isTerminated())
			;
		while (!executorServiceEmployees.isTerminated()) {
			if (iCallsService.findByTimeFinished().size() == iCallsService.findAll().size()) {
				if (!executorServiceEmployees.isShutdown()) {
					executorServiceEmployees.shutdown();
				}
			}
		}
		
		iCallsService.findAll().forEach(call -> {
			Assert.assertNotNull(call.getTimeReceived());
			Assert.assertNotNull(call.getEmployeeAsigned());
			Assert.assertNotNull(call.getTimeAsigned());
			Assert.assertNotNull(call.getTimeFinished());
			Assert.assertTrue(call.getCallTimeInSeconds() > 0);
		});

		int countOperators = 0, countSupervisors = 0, countDirectors = 0;
		for (Call call : iCallsService.findAll()) {
			switch (call.getEmployeeAsigned().getTypeEmployee().getId().intValue()) {
			case 1:
				countOperators++;
				break;
			case 2:
				countSupervisors++;
				break;
			case 3:
				countDirectors++;
				break;
			}
		}
		Assert.assertTrue(countOperators == 2);
		Assert.assertTrue(countSupervisors == 0);
		Assert.assertTrue(countDirectors == 0);
	}
	/**
	 * 4 llamadas concurrentes con 2 Operadores, 2 Supervisores y 2 Directores.
	 */
	@Test
	public void dispatcherCall_4llamadasConcurrentes() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ExecutorService executorServiceEmployees = Executors.newFixedThreadPool(6);
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, iCallsService,
				iTypeEmployeeService, executorServiceEmployees);
		Dispatcher dispatcher = new Dispatcher(iCallCenterService);
		List<UserRunnable> listUser = new ArrayList<>();
		iCallCenterService.createEmployeesOperator("Op1", "Op2");
		iCallCenterService.createEmployeesSupervisor("Sup1", "Sup2");
		iCallCenterService.createEmployeesDirector("Dir1", "Dir2");
		int numberThread = 4;
		for (int i = 0; i < numberThread; i++) {
			listUser.add(new UserRunnable(new Call(i + 1l), dispatcher));
		}
		final ExecutorService executorService = Executors.newFixedThreadPool(numberThread);
		for (UserRunnable userRunnable : listUser) {
			executorService.execute(userRunnable);
		}
		executorService.shutdown();
		while (!executorService.isTerminated())
			;
		while (!executorServiceEmployees.isTerminated()) {
			if (iCallsService.findByTimeFinished().size() == iCallsService.findAll().size()) {
				if (!executorServiceEmployees.isShutdown()) {
					executorServiceEmployees.shutdown();
				}
			}
		}
		
		iCallsService.findAll().forEach(call -> {
			Assert.assertNotNull(call.getTimeReceived());
			Assert.assertNotNull(call.getEmployeeAsigned());
			Assert.assertNotNull(call.getTimeAsigned());
			Assert.assertNotNull(call.getTimeFinished());
			Assert.assertTrue(call.getCallTimeInSeconds() > 0);
		});
		int countOperators = 0, countSupervisors = 0, countDirectors = 0;
		for (Call call : iCallsService.findAll()) {
			switch (call.getEmployeeAsigned().getTypeEmployee().getId().intValue()) {
			case 1:
				countOperators++;
				break;
			case 2:
				countSupervisors++;
				break;
			case 3:
				countDirectors++;
				break;
			}
		}
		Assert.assertTrue(countOperators == 2);
		Assert.assertTrue(countSupervisors == 2);
		Assert.assertTrue(countDirectors == 0);
	}
	/**
	 * 5 llamadas concurrentes con 2 Operadores, 2 Supervisores y 2 Directores.
	 */
	@Test
	public void dispatcherCall_5llamadasConcurrentes() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ExecutorService executorServiceEmployees = Executors.newFixedThreadPool(6);
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, iCallsService,
				iTypeEmployeeService, executorServiceEmployees);
		Dispatcher dispatcher = new Dispatcher(iCallCenterService);
		List<UserRunnable> listUser = new ArrayList<>();
		iCallCenterService.createEmployeesOperator("Op1", "Op2");
		iCallCenterService.createEmployeesSupervisor("Sup1", "Sup2");
		iCallCenterService.createEmployeesDirector("Dir1", "Dir2");
		int numberThread = 5;
		for (int i = 0; i < numberThread; i++) {
			listUser.add(new UserRunnable(new Call(i + 1l), dispatcher));
		}
		final ExecutorService executorService = Executors.newFixedThreadPool(numberThread);
		for (UserRunnable userRunnable : listUser) {
			executorService.execute(userRunnable);
		}
		executorService.shutdown();
		while (!executorService.isTerminated())
			;
		while (!executorServiceEmployees.isTerminated()) {
			if (iCallsService.findByTimeFinished().size() == iCallsService.findAll().size()) {
				if (!executorServiceEmployees.isShutdown()) {
					executorServiceEmployees.shutdown();
				}
			}
		}
		
		iCallsService.findAll().forEach(call -> {
			Assert.assertNotNull(call.getTimeReceived());
			Assert.assertNotNull(call.getEmployeeAsigned());
			Assert.assertNotNull(call.getTimeAsigned());
			Assert.assertNotNull(call.getTimeFinished());
			Assert.assertTrue(call.getCallTimeInSeconds() > 0);
		});
		int countOperators = 0, countSupervisors = 0, countDirectors = 0;
		for (Call call : iCallsService.findAll()) {
			switch (call.getEmployeeAsigned().getTypeEmployee().getId().intValue()) {
			case 1:
				countOperators++;
				break;
			case 2:
				countSupervisors++;
				break;
			case 3:
				countDirectors++;
				break;
			}
		}
		Assert.assertTrue(countOperators == 2);
		Assert.assertTrue(countSupervisors == 2);
		Assert.assertTrue(countDirectors == 1);
	}
	/**
	 * 10 llamadas concurrentes 2 Operadores, 2 Supervisores y 2 Directores
	 */
	@Test
	public void dispatcherCall_10llamadasConcurrentes() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ExecutorService executorServiceEmployees = Executors.newFixedThreadPool(6);
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, iCallsService,
				iTypeEmployeeService, executorServiceEmployees);
		Dispatcher dispatcher = new Dispatcher(iCallCenterService);
		List<UserRunnable> listUser = new ArrayList<>();
		iCallCenterService.createEmployeesOperator("Op1", "Op2");
		iCallCenterService.createEmployeesSupervisor("Sup1", "Sup2");
		iCallCenterService.createEmployeesDirector("Dir1", "Dir2");
		int numberThread = 10;
		for (int i = 0; i < numberThread; i++) {
			listUser.add(new UserRunnable(new Call(i + 1l), dispatcher));
		}
		final ExecutorService executorService = Executors.newFixedThreadPool(numberThread);
		for (UserRunnable userRunnable : listUser) {
			executorService.execute(userRunnable);
		}
		executorService.shutdown();
		while (!executorService.isTerminated())
			;
		while (!executorServiceEmployees.isTerminated()) {
			if (iCallsService.findByTimeFinished().size() == iCallsService.findAll().size()) {
				if (!executorServiceEmployees.isShutdown()) {
					executorServiceEmployees.shutdown();
				}
			}
		}
		
		iCallsService.findAll().forEach(call -> {
			Assert.assertNotNull(call.getTimeReceived());
			Assert.assertNotNull(call.getEmployeeAsigned());
			Assert.assertNotNull(call.getTimeAsigned());
			Assert.assertNotNull(call.getTimeFinished());
			Assert.assertTrue(call.getCallTimeInSeconds() > 0);
		});
	}

	/**
	 * 15 llamadas concurrentes 2 Operadores, 2 Supervisores y 2 Directores
	 */
	@Test
	public void dispatcherCall_15llamadasConcurrentes() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		IEmployeeService iEmployeeService = new EmployeeServiceImpl(new ArrayList<>());
		ExecutorService executorServiceEmployees = Executors.newFixedThreadPool(6);
		ICallCenterService iCallCenterService = new CallCenterServiceImpl(iEmployeeService, iCallsService,
				iTypeEmployeeService, executorServiceEmployees);
		Dispatcher dispatcher = new Dispatcher(iCallCenterService);
		List<UserRunnable> listUser = new ArrayList<>();
		iCallCenterService.createEmployeesOperator("Op1", "Op2");
		iCallCenterService.createEmployeesSupervisor("Sup1", "Sup2");
		iCallCenterService.createEmployeesDirector("Dir1", "Dir2");
		int numberThread = 15;
		for (int i = 0; i < numberThread; i++) {
			listUser.add(new UserRunnable(new Call(i + 1l), dispatcher));
		}
		final ExecutorService executorService = Executors.newFixedThreadPool(numberThread);
		for (UserRunnable userRunnable : listUser) {
			executorService.execute(userRunnable);
		}
		executorService.shutdown();
		while (!executorService.isTerminated())
			;
		while (!executorServiceEmployees.isTerminated()) {
			if (iCallsService.findByTimeFinished().size() == iCallsService.findAll().size()) {
				if (!executorServiceEmployees.isShutdown()) {
					executorServiceEmployees.shutdown();
				}
			}
		}
		iCallsService.findAll().forEach(call -> {
			Assert.assertNotNull(call.getTimeReceived());
			Assert.assertNotNull(call.getEmployeeAsigned());
			Assert.assertNotNull(call.getTimeAsigned());
			Assert.assertNotNull(call.getTimeFinished());
			Assert.assertTrue(call.getCallTimeInSeconds() > 0);
		});
	}
}
