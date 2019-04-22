/**
 *
 */
package com.br.alldreams.socialmidia.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.alldreams.socialmidia.manager.service.JobService;

/**
 * @author Jorge Demetrio
 * @since 20 de abr de 2019 01:34:11
 * @version 1.0
 */
@RestController("/jobs")
public class JobsController {

	@Autowired
	private JobService service;

	@GetMapping("/start")
	@ResponseBody
	public String iniciar() {
		service.startService();
		return "Iniciado com sucesso!";
	}
}
