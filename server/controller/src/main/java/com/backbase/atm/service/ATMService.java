package com.backbase.atm.service;

import java.util.ArrayList;
import java.util.HashSet;

import com.backbase.atm.model.ATM;

public interface ATMService {

	public ArrayList<ATM> geAllATMs();

	public ArrayList<ATM> getCityATMs(String city);

	public HashSet<String> getAllCities();
}
