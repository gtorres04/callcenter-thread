/**
 * 
 */
package com.gtorres.test.callcenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.service.ICallCenterService;

/**
 * @author gtorresoft
 *
 */
@Service
public class Dispatcher {
	
	private ICallCenterService iCallCenterService;

	/**
	 * @param iCallCenterService
	 */
	@Autowired
	public Dispatcher(ICallCenterService iCallCenterService) {
		super();
		this.iCallCenterService = iCallCenterService;
	}

	/**
	 * Recibe las llamadas y las asigna a un empleado
	 * 
	 * @param call
	 *            llamada a ser asignada a un empleado
	 * @throws InterruptedException
	 */
	public void dispatchCall(Call call) throws InterruptedException {
		iCallCenterService.receivedCall(call);
		iCallCenterService.assignCall(call);
	}
}
