package com.backbase.atm.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backbase.atm.controller.WelcomeFileController;

@Controller
public class WelcomeFileControllerImpl implements WelcomeFileController {

	@RequestMapping(value = "/")
	public String getIndex() {
		return "index.html";
	}
}
