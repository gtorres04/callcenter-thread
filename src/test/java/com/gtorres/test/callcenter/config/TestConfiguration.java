package com.gtorres.test.callcenter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.gtorres.test.callcenter.ServiceConfig;

@Configuration
@Import(ServiceConfig.class)
public class TestConfiguration {
	
}
