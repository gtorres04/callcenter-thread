package com.gtorres.test.callcenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gtorres.test.callcenter.service.ICallCenterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallcenterApplicationTests {
	
	@Autowired
	private ICallCenterService iCallCenterService;
	
	@Test
	public void contextLoads() {
		System.out.println("Hola");
	}

}
