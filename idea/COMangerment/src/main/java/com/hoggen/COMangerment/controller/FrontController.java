package com.hoggen.COMangerment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class FrontController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainUser() {

		return "frontEnd/index";
	}


	// login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {

		return "frontEnd/login";
	}


	// login
	@RequestMapping(value = "record", method = RequestMethod.GET)
	public String record() {

		return "frontEnd/record";
	}



	@RequestMapping(value = "manager", method = RequestMethod.GET)
	public String mainAdmin() {

		return "backEnd/index";
	}

	@RequestMapping(value = "manager/login", method = RequestMethod.GET)
	public String managerLogin() {

		return "backEnd/login";
	}


	@RequestMapping(value = "manager/normal", method = RequestMethod.GET)
	public String chart() {

		return "backEnd/normal";
	}

	@RequestMapping(value = "manager/none", method = RequestMethod.GET)
	public String config() {

		return "backEnd/none";
	}

	@RequestMapping(value = "manager/ntwo", method = RequestMethod.GET)
	public String ntwo() {

		return "backEnd/ntwo";
	}

	@RequestMapping(value = "manager/pone", method = RequestMethod.GET)
	public String pone() {

		return "backEnd/pone";
	}

	@RequestMapping(value = "manager/period", method = RequestMethod.GET)
	public String period() {

		return "backEnd/period";
	}

	@RequestMapping(value = "manager/ptwo", method = RequestMethod.GET)
	public String ptwo() {

		return "backEnd/ptwo";
	}



}
