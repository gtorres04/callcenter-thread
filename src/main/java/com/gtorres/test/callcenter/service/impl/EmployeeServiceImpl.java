package com.gtorres.test.callcenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.google.common.base.Preconditions.checkArgument;
import com.gtorres.test.callcenter.dto.Employee;
import com.gtorres.test.callcenter.dto.TypeEmployee;
import com.gtorres.test.callcenter.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	private List<Employee> listEmployee;
	
	/**
	 * @param listEmployee
	 */
	@Autowired
	public EmployeeServiceImpl(List<Employee> listEmployee) {
		super();
		this.listEmployee = listEmployee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gtorres.test.callcenter.service.IEmployeeService#create(com.gtorres.test.
	 * callcenter.dto.Employee)
	 */
	@Override
	public Employee create(Employee employee) {
		checkArgument(null != employee.getTypeEmployee(),
				String.format("%s debe relacionarse al empleado", "Tipo de empleado"));
		employee.setId(listEmployee.size() + 1l);
		listEmployee.add(employee);
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.IEmployeeService#
	 * findByTypeEmployeeAndIsBusy(com.gtorres.test.callcenter.dto.TypeEmployee,
	 * boolean)
	 */
	@Override
	public List<Employee> findByTypeEmployeeAndIsBusy(TypeEmployee typeEmployee, boolean isBusy) {
		checkArgument(null != typeEmployee, String.format("%s debe relacionarse", "Tipo de empleado"));
		List<Employee> list = new ArrayList<>();
		for (Employee employee : listEmployee) {
			if (typeEmployee.getId().equals(employee.getTypeEmployee().getId()) && isBusy == employee.isBusy()) {
				list.add(employee);
			}
		}
		return list.size() == 0 ? null : list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.IEmployeeService#occupyEmployee(com.
	 * gtorres.test.callcenter.dto.Employee)
	 */
	@Override
	public void occupyEmployee(Employee employee) {
		this.changeStateIsBusyToEmployee(employee, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.IEmployeeService#vacateEmployee(com.
	 * gtorres.test.callcenter.dto.Employee)
	 */
	@Override
	public void vacateEmployee(Employee employee) {
		this.changeStateIsBusyToEmployee(employee, false);
	}

	/**
	 * Cambiar el estado a ocupado (true) o desocupado (false) de un empleado
	 * 
	 * @param employee
	 *            empleado a cambiar el estado
	 * @param stateIsBusy
	 *            estado a ser asignado
	 */
	private void changeStateIsBusyToEmployee(Employee employee, boolean stateIsBusy) {
		for (Employee employeeToSearch : this.listEmployee) {
			if (employee.getId().equals(employeeToSearch.getId())) {
				employeeToSearch.setBusy(stateIsBusy);
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.gtorres.test.callcenter.service.IEmployeeService#findByIsBusy(boolean)
	 */
	@Override
	public List<Employee> findByIsBusy(boolean isBusy) {
		List<Employee> list = new ArrayList<>();
		for (Employee employee : listEmployee) {
			if (isBusy == employee.isBusy()) {
				list.add(employee);
			}
		}
		return list.size() == 0 ? null : list;
	}
}
