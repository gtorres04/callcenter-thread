package com.gtorres.test.callcenter.service;

import com.gtorres.test.callcenter.dto.TypeEmployee;

public interface ITypeEmployeeService {
	/**
	 * Busca un tipo de empleado por su ID.
	 * 
	 * @param id
	 * @return
	 */
	public TypeEmployee findById(Long id);
}
