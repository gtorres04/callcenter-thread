package com.gtorres.test.callcenter.service;

import com.gtorres.test.callcenter.dto.Call;

public interface ICallCenterService {

	/**
	 * Crea un empleado de tipo operador
	 * 
	 * @param nameEmployee
	 *            nombre del empleado
	 */
	public void createEmployeeOperator(String nameEmployee);

	/**
	 * Crea un empleado de tipo Supervisor
	 * 
	 * @param nameEmployee
	 *            nombre del empleado
	 */
	public void createEmployeeSupervisor(String nameEmployee);

	/**
	 * Crea un empleado de tipo Director
	 * 
	 * @param nameEmployee
	 *            nombre del empleado
	 */
	public void createEmployeeDirector(String nameEmployee);

	/**
	 * Crea varios empleados de tipo operador
	 * 
	 * @param namesEmployees
	 *            nombres de los empleados a crear
	 */
	public void createEmployeesOperator(String... namesEmployees);

	/**
	 * Crea varios empleados de tipo Supervisor
	 * 
	 * @param namesEmployees
	 *            nombres de los empleados a crear
	 */
	public void createEmployeesSupervisor(String... namesEmployees);

	/**
	 * Crea varios empleados de tipo director
	 * 
	 * @param namesEmployees
	 *            nombres de los empleados a crear
	 */
	public void createEmployeesDirector(String... namesEmployees);

	/**
	 * Asigna una llamada a un empleado operador, si los operadores estan todo
	 * ocupados, la llamada es asignada a un supervisor y si los supervisores estan
	 * todos ocupados, la llamada es asignada a un director. Si llega una llamada y
	 * todos los empleados estan ocupados se verificada cada 1 segundo que exista un
	 * empleado desocupado, cumpliendo con el mismo orden de asignacion descrito
	 * anteriormente.
	 * 
	 * @param call
	 *            llamada a ser asignada.
	 */
	public void assignCall(Call call);

	/**
	 * Se adiciona a la lista de llamada recibidas
	 * 
	 * @param call
	 *            Llamada recibida
	 */
	public void receivedCall(Call call);

}
