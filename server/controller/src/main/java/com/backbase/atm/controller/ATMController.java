package com.backbase.atm.controller;

import java.util.ArrayList;
import java.util.HashSet;

import com.backbase.atm.model.ATM;

public interface ATMController {

	public ArrayList<ATM> getATMList(String city);

	public HashSet<String> getAllCities();

}
