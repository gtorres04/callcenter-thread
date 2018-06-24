package com.gtorres.test.callcenter.service;

import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.service.impl.CallsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallsServiceImplTest {
	/**
	 * validar que Una llamada recibida se este relacionando
	 */
	@Test
	public void addCallReceived() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		iCallsService.addCallReceived(new Call(1l));
		Assert.assertTrue(1 == iCallsService.findAll().size());
	}
	
	/**
	 * validar que el codigo de la llamada recibida se este relacionando en la lista
	 */
	@Test
	public void findById() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		iCallsService.addCallReceived(new Call(1l));
		Assert.assertTrue(1l == iCallsService.findById(1l).getId().longValue());
	}

	/**
	 * Ninguna llamada finalizada
	 */
	@Test
	public void findByTimeFinished_anyCallFinished() {
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		iCallsService.addCallReceived(new Call(1l));
		Assert.assertTrue(0 == iCallsService.findByTimeFinished().size());
	}

	/**
	 * Ninguna llamada finalizada
	 */
	@Test
	public void findByTimeFinished_oneCallFinished() {
		Call call = new Call(1l);
		call.setTimeFinished(LocalTime.now());
		ICallsService iCallsService = new CallsServiceImpl(new ArrayList<>());
		iCallsService.addCallReceived(call);
		Assert.assertTrue(1 == iCallsService.findByTimeFinished().size());
	}
}
