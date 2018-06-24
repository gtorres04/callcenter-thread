package com.gtorres.test.callcenter.service;

import java.util.List;

import com.gtorres.test.callcenter.dto.Employee;
import com.gtorres.test.callcenter.dto.TypeEmployee;

public interface IEmployeeService {

	/**
	 * Crea un empleado en la lista de empleado
	 * 
	 * @param employee
	 * @return
	 */
	public Employee create(Employee employee);

	/**
	 * Busca un empleado por el tipo de empleado y por la ocupacion
	 * 
	 * @param typeEmployee
	 *            tipo de empleado a buscar
	 * @param isBusy
	 *            si esta ocupado o no
	 * @return
	 */
	public List<Employee> findByTypeEmployeeAndIsBusy(TypeEmployee typeEmployee, boolean isBusy);

	/**
	 * Coloca el empleado en un estado de ocupado
	 * 
	 * @param employee
	 *            empleado a ocupar
	 */

	public void occupyEmployee(Employee employee);

	/**
	 * Coloca el empleado en un estado de desocupado
	 * 
	 * @param employee
	 *            empleado a desocupar
	 */
	public void vacateEmployee(Employee employee);

	/**
	 * Buscar empleados con la ocupacion segun el valor del parametro isBusy
	 * 
	 * @param isBusy
	 *            estado de la ocupacion del empleado
	 * @return listado de empleados desocupados
	 */
	public List<Employee> findByIsBusy(boolean isBusy);
}
