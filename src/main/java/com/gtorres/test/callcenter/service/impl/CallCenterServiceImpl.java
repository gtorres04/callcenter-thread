package com.gtorres.test.callcenter.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.dto.Employee;
import com.gtorres.test.callcenter.dto.TypeEmployee;
import com.gtorres.test.callcenter.runnable.AnsweringCallRunnable;
import com.gtorres.test.callcenter.service.ICallCenterService;
import com.gtorres.test.callcenter.service.ICallsService;
import com.gtorres.test.callcenter.service.IEmployeeService;
import com.gtorres.test.callcenter.service.ITypeEmployeeService;
import com.gtorres.test.callcenter.util.Utility;

@Service
public class CallCenterServiceImpl implements ICallCenterService {

	private IEmployeeService iEmployeeService;
	private ICallsService iCallsService;
	private ITypeEmployeeService iTypeEmployeeService;
	private ExecutorService executorServiceEmployees;
	
	/**
	 * @param iEmployeeService
	 * @param iCallsService
	 */
	@Autowired
	public CallCenterServiceImpl(IEmployeeService iEmployeeService, ICallsService iCallsService, ITypeEmployeeService iTypeEmployeeService, ExecutorService executorServiceEmployees) {
		super();
		this.iEmployeeService = iEmployeeService;
		this.iCallsService = iCallsService;
		this.iTypeEmployeeService = iTypeEmployeeService;
		this.executorServiceEmployees = executorServiceEmployees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gtorres.test.callcenter.service.ICallCenterService#createEmployeeOperator
	 * (java.lang.String)
	 */
	@Override
	public void createEmployeeOperator(String nameEmployee) {
		this.createEmployee(nameEmployee, 1l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.ICallCenterService#
	 * createEmployeeSupervisor(java.lang.String)
	 */
	@Override
	public void createEmployeeSupervisor(String nameEmployee) {
		this.createEmployee(nameEmployee, 2l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gtorres.test.callcenter.service.ICallCenterService#createEmployeeDirector
	 * (java.lang.String)
	 */
	@Override
	public void createEmployeeDirector(String nameEmployee) {
		this.createEmployee(nameEmployee, 3l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.ICallCenterService#
	 * createEmployeesOperator(java.lang.String[])
	 */
	@Override
	public void createEmployeesOperator(String... namesEmployees) {
		for (String nameEmployee : namesEmployees) {
			this.createEmployeeOperator(nameEmployee);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.ICallCenterService#
	 * createEmployeesSupervisor(java.lang.String[])
	 */
	@Override
	public void createEmployeesSupervisor(String... namesEmployees) {
		for (String nameEmployee : namesEmployees) {
			this.createEmployeeSupervisor(nameEmployee);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.ICallCenterService#
	 * createEmployeesDirector(java.lang.String[])
	 */
	@Override
	public void createEmployeesDirector(String... namesEmployees) {
		for (String nameEmployee : namesEmployees) {
			this.createEmployeeDirector(nameEmployee);
		}
	}

	/**
	 * Crea un empleado segun el tipo de empleado deseado
	 * 
	 * @param nameEmployee
	 *            nombre del empleado
	 * @param idTypeEmployee
	 *            id del tipo de empleado
	 */
	private void createEmployee(String nameEmployee, Long idTypeEmployee) {
		Employee employee = new Employee();
		employee.setTypeEmployee(iTypeEmployeeService.findById(idTypeEmployee));
		employee.setName(nameEmployee);
		employee = iEmployeeService.create(employee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gtorres.test.callcenter.service.ICallCenterService#assignCall(com.gtorres
	 * .test.callcenter.dto.Call)
	 */
	@Override 
	synchronized public void assignCall(Call call) {
		Employee employeeAsigned = null;
		while (!this.existVacantEmployee()) {
			Utility.timeOutOfEmployee(1);
		}
		TypeEmployee typeEmployee = iTypeEmployeeService.findById(1l);
		List<Employee> employeesOperators = this.iEmployeeService.findByTypeEmployeeAndIsBusy(typeEmployee, false);
		if (null != employeesOperators) {
			employeeAsigned = employeesOperators.get(0);
			this.iEmployeeService.occupyEmployee(employeeAsigned);
		} else {
			typeEmployee = iTypeEmployeeService.findById(2l);
			List<Employee> employeesSupervisors = this.iEmployeeService.findByTypeEmployeeAndIsBusy(typeEmployee,
					false);
			if (null != employeesSupervisors) {
				employeeAsigned = employeesSupervisors.get(0);
				this.iEmployeeService.occupyEmployee(employeeAsigned);
			} else {
				typeEmployee = iTypeEmployeeService.findById(3l);
				List<Employee> employeesDirectors = this.iEmployeeService.findByTypeEmployeeAndIsBusy(typeEmployee,
						false);
				if (null != employeesDirectors) {
					employeeAsigned = employeesDirectors.get(0);
					this.iEmployeeService.occupyEmployee(employeeAsigned);
				}
			}
		}
		call.setEmployeeAsigned(employeeAsigned);
		call.setTimeAsigned(LocalTime.now());
		LocalTime justoAhora = call.getTimeAsigned();
		System.out.printf("LLamada %d Asignada a %s a las %d horas con %d minutos, %d segundos y %d Nano Segundos\n",
				call.getId(), employeeAsigned.getName(), justoAhora.getHour(), justoAhora.getMinute(),
				justoAhora.getSecond(), justoAhora.getNano());
		this.answerCall(call, employeeAsigned);
	}

	/**
	 * Valida si existen empleados desocupados
	 * 
	 * @return true si existen empleados desocupados
	 */
	private boolean existVacantEmployee() {
		return null != this.iEmployeeService.findByIsBusy(false);
	}

	/**
	 * Un empleado contesta la llamada asignada.
	 * 
	 * @param call
	 *            llamada a ser atendida
	 * @param employee
	 *            empleado asignado para atender la llamada.
	 */
	private void answerCall(Call call, Employee employee) {
		this.executorServiceEmployees.execute(new AnsweringCallRunnable(call, employee, this.iEmployeeService));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gtorres.test.callcenter.service.ICallCenterService#receivedCall(com.
	 * gtorres.test.callcenter.dto.Call)
	 */
	@Override
	public void receivedCall(Call call) {
		call.setTimeReceived(LocalTime.now());
		this.iCallsService.addCallReceived(call);
		System.out.printf(
				"LLamada %d recibida: En este momento son las %d horas con %d minutos, %d segundos y %d Nano Segundos\n",
				call.getId(), call.getTimeReceived().getHour(), call.getTimeReceived().getMinute(),
				call.getTimeReceived().getSecond(), call.getTimeReceived().getNano());
	}
}
