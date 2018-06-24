package com.gtorres.test.callcenter.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.gtorres.test.callcenter.dto.Employee;
import com.gtorres.test.callcenter.service.impl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {
	@Autowired
	private ITypeEmployeeService iTypeEmployeeService;

	/**
	 * validar que se este relacionando un empleado en el listado.
	 */
	@Test
	public void create() {
		IEmployeeService iService = new EmployeeServiceImpl(new ArrayList<>());
		Employee employee = new Employee();
		employee.setTypeEmployee(iTypeEmployeeService.findById(2l));
		employee.setName("Supervisor 1");
		iService.create(employee);
		List<Employee> ListEmployeeValid = iService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l),
				false);
		Assert.assertTrue(1 == ListEmployeeValid.size());
		Assert.assertTrue("Supervisor 1".equals(ListEmployeeValid.get(0).getName()));
	}

	/**
	 * validar que se encuentre un empleado por el estado de su ocupacion y el tipo
	 * de empleados que es.
	 */
	@Test
	public void findByTypeEmployeeAndIsBusy() {
		IEmployeeService iService = new EmployeeServiceImpl(new ArrayList<>());
		Employee employee = new Employee();
		employee.setTypeEmployee(iTypeEmployeeService.findById(1l));
		employee.setName("Operador 1");
		employee.setBusy(true);
		iService.create(employee);
		employee = new Employee();
		employee.setTypeEmployee(iTypeEmployeeService.findById(2l));
		employee.setName("Supervisor 1");
		iService.create(employee);
		List<Employee> ListEmployeeValid = iService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l),
				true);
		Assert.assertTrue(1 == ListEmployeeValid.size());
		Assert.assertTrue("Operador 1".equals(ListEmployeeValid.get(0).getName()));
		
		ListEmployeeValid = iService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(2l),
				false);
		Assert.assertTrue(1 == ListEmployeeValid.size());
		Assert.assertTrue("Supervisor 1".equals(ListEmployeeValid.get(0).getName()));
	}
	
	/**
	 * Validar el cambio de estado de la ocupacion a "OCUPADO".
	 */
	@Test
	public void occupyEmployee() {
		IEmployeeService iService = new EmployeeServiceImpl(new ArrayList<>());
		Employee employee = new Employee();
		employee.setTypeEmployee(iTypeEmployeeService.findById(1l));
		employee.setName("Operador 1");
		iService.create(employee);
		iService.occupyEmployee(employee);
		List<Employee> ListEmployeeValid = iService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l),
				true);
		Assert.assertTrue(1 == ListEmployeeValid.size());
		Assert.assertTrue("Operador 1".equals(ListEmployeeValid.get(0).getName()));
	}
	
	/**
	 * Validar el cambio de estado de la ocupacion a "DESOCUPADO".
	 */
	@Test
	public void vacateEmployee() {
		IEmployeeService iService = new EmployeeServiceImpl(new ArrayList<>());
		Employee employee = new Employee();
		employee.setTypeEmployee(iTypeEmployeeService.findById(1l));
		employee.setName("Operador 1");
		employee.setBusy(true);
		iService.create(employee);
		iService.vacateEmployee(employee);
		List<Employee> ListEmployeeValid = iService.findByTypeEmployeeAndIsBusy(iTypeEmployeeService.findById(1l),
				false);
		Assert.assertTrue(1 == ListEmployeeValid.size());
		Assert.assertTrue("Operador 1".equals(ListEmployeeValid.get(0).getName()));
	}
	
	/**
	 * Validar la busqueda por el estado de la ocupacion.
	 */
	@Test
	public void findByIsBusy() {
		IEmployeeService iService = new EmployeeServiceImpl(new ArrayList<>());
		Employee employee = new Employee();
		employee.setTypeEmployee(iTypeEmployeeService.findById(1l));
		employee.setName("Operador 1");
		employee.setBusy(true);
		iService.create(employee);
		iService.vacateEmployee(employee);
		List<Employee> ListEmployeeValid = iService.findByIsBusy(false);
		Assert.assertTrue(1 == ListEmployeeValid.size());
		Assert.assertTrue("Operador 1".equals(ListEmployeeValid.get(0).getName()));
	}
}
