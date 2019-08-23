package com.hoggen.COMangerment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class FrontController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainUser() {

		return "backEnd/index";
	}


	// login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {

		return "backEnd/login";
	}


	// login
	@RequestMapping(value = "record", method = RequestMethod.GET)
	public String record() {

		return "frontEnd/record";
	}





}
