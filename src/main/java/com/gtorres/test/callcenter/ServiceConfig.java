/**
 * 
 */
package com.gtorres.test.callcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.dto.Employee;
import com.gtorres.test.callcenter.dto.TypeEmployee;

/**
 * @author gtorresoft
 *
 */
@Configuration
public class ServiceConfig {
	/**
	 * Lista de tipos de empleados por defecto.
	 * 
	 * @return
	 */
	@Bean
	public List<TypeEmployee> listTypeEmployee() {
		List<TypeEmployee> list = new ArrayList<>();
		TypeEmployee employee = new TypeEmployee();
		employee.setId(1l);
		employee.setName("OPERADOR");
		list.add(employee);
		employee = new TypeEmployee();
		employee.setId(2l);
		employee.setName("SUPERVISOR");
		list.add(employee);
		employee = new TypeEmployee();
		employee.setId(3l);
		employee.setName("DIRECTOR");
		list.add(employee);
		return list;
	}

	/**
	 * Listado de llamadas recibidas
	 * 
	 * @return
	 */
	@Bean
	public List<Call> listCalls() {
		return new ArrayList<>();
	}

	/**
	 * Listado de empleados
	 * 
	 * @return
	 */
	@Bean
	public List<Employee> listEmployee() {
		List<Employee> list = new ArrayList<>();
		return list;
	}
	
	/**
	 * Administrador de los 10 hilos de los empleados
	 * 
	 * @return
	 */
	@Bean
	public ExecutorService executorServiceEmployees() {
		return Executors.newFixedThreadPool(10);
	}
}
