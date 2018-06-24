/**
 * 
 */
package com.gtorres.test.callcenter;

import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.service.impl.Dispatcher;

/**
 * @author gtorresoft 
 *
 */
public class UserRunnable implements Runnable{
	private Call call;
	private Dispatcher dispatcher;
	
	/**
	 * @param call
	 */
	public UserRunnable(Call call, Dispatcher dispatcher) {
		super();
		this.call = call;
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		try {
			this.dispatcher.dispatchCall(call);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
