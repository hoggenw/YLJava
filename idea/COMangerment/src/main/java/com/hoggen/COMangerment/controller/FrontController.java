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
	@RequestMapping(value = "adduser", method = RequestMethod.GET)
	public String adduser() {

		return "backEnd/addUser";
	}

	@RequestMapping(value = "detailUser", method = RequestMethod.GET)
	public String detailUser() {

		return "backEnd/detailUser";
	}


	@RequestMapping(value = "updateUser", method = RequestMethod.GET)
	public String updateUser() {

		return "backEnd/updateUser";
	}



	@RequestMapping(value = "recommend", method = RequestMethod.GET)
	public String recommend() {

		return "backEnd/recommend";
	}


	@RequestMapping(value = "recommend_sUser", method = RequestMethod.GET)
	public String recommend_sUser() {

		return "backEnd/recommend_sUser";
	}

	@RequestMapping(value = "bill", method = RequestMethod.GET)
	public String bill() {

		return "backEnd/bill";
	}

	@RequestMapping(value = "billList", method = RequestMethod.GET)
	public String billList() {

		return "backEnd/billList";
	}

	@RequestMapping(value = "backCash", method = RequestMethod.GET)
	public String backCash() {

		return "backEnd/backCash";
	}
	@RequestMapping(value = "backCashList", method = RequestMethod.GET)
	public String backCashList() {

		return "backEnd/backCashList";
	}

	@RequestMapping(value = "tradeList", method = RequestMethod.GET)
	public String tradeList() {

		return "backEnd/tradeList";
	}

	@RequestMapping(value = "billListReally", method = RequestMethod.GET)
	public String billListReally() {

		return "backEnd/billListReally";
	}

	@RequestMapping(value = "setting", method = RequestMethod.GET)
	public String setting() {

		return "backEnd/setting";
	}











}
