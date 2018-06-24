/**
 * 
 */
package com.gtorres.test.callcenter.runnable;

import java.time.LocalTime;

import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.dto.Employee;
import com.gtorres.test.callcenter.service.IEmployeeService;
import com.gtorres.test.callcenter.util.Utility;

/**
 * @author gtorresoft
 *
 */
public class AnsweringCallRunnable implements Runnable {
	private Call call;
	private Employee employee;
	private IEmployeeService iEmployeeService;

	/**
	 * @param call
	 * @param employee
	 */
	public AnsweringCallRunnable(Call call, Employee employee, IEmployeeService iEmployeeService) {
		super();
		this.call = call;
		this.employee = employee;
		this.iEmployeeService = iEmployeeService;
	}

	@Override
	public void run() {
		LocalTime justoAhora = LocalTime.now();
		System.out.printf("Empleado %s (%s) Atendiendo la llamada %d a las %d horas con %d minutos y %d segundos\n",
				this.employee.getName(), this.employee.getTypeEmployee().getName(), this.call.getId(),
				justoAhora.getHour(), justoAhora.getMinute(), justoAhora.getSecond());
		call.setCallTimeInSeconds(Utility.randomNumber(1, 10));
		Utility.timeOutOfEmployee(call.getCallTimeInSeconds());
		call.setTimeFinished(LocalTime.now());
		System.out.printf("Empleado %s (%s) finalizo la llamada %d a las %d horas con %d minutos y %d segundos. Tiempo de la llamada %d\n",
				this.employee.getName(), this.employee.getTypeEmployee().getName(), call.getId(),
				call.getTimeFinished().getHour(), call.getTimeFinished().getMinute(),
				call.getTimeFinished().getSecond(), call.getCallTimeInSeconds());
		this.iEmployeeService.vacateEmployee(employee);
	}
}
