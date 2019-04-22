package com.br.alldreams.socialmidia.manager.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.br.alldreams.socialmidia.manager.service.JobService;

@Component
public class ServerInitializer implements ApplicationRunner {

	@Autowired
	JobService service;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		service.startService();
	}
}
