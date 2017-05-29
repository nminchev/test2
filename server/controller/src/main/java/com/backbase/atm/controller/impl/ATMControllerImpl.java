package com.backbase.atm.controller.impl;

import java.util.ArrayList;
import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.atm.controller.ATMController;
import com.backbase.atm.model.ATM;
import com.backbase.atm.service.ATMService;

@RestController
@RequestMapping(value = "rest")
public class ATMControllerImpl implements ATMController {

	@Resource(name = "atmService")
	private ATMService atmService;

	/**
	 * Get List of the ATMs within a city
	 */
	@Override
	@RequestMapping(value = "atm/{city}")
	public ArrayList<ATM> getATMList(@PathVariable(value = "city") String city) {
		return atmService.getCityATMs(city);
	}

	/**
	 * Get Set of All available cities with ATMs
	 */
	@Override
	@RequestMapping(value = "cities")
	public HashSet<String> getAllCities() {
		return atmService.getAllCities();
	}
}
