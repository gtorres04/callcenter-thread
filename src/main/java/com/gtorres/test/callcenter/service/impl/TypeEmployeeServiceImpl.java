package com.gtorres.test.callcenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gtorres.test.callcenter.dto.TypeEmployee;
import com.gtorres.test.callcenter.service.ITypeEmployeeService;

@Service
public class TypeEmployeeServiceImpl implements ITypeEmployeeService {
	
	@Autowired
	private List<TypeEmployee> listTypeEmployee;

	/* (non-Javadoc)
	 * @see com.gtorres.test.callcenter.service.ITypeEmployeeService#findById(java.lang.Long)
	 */
	@Override
	public TypeEmployee findById(Long id) {
		for (TypeEmployee typeEmployee : listTypeEmployee) {
			if(id.equals(typeEmployee.getId())) {
				return typeEmployee;
			}
		}
		return null;
	}
}
