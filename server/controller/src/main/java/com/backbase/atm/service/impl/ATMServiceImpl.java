package com.backbase.atm.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.backbase.atm.model.ATM;
import com.backbase.atm.service.ATMService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class ATMServiceImpl implements ATMService {

	private static final Logger log = Logger.getLogger(ClassName.class.getName());

	public static final String ING_ATM_URL = "https://www.ing.nl/api/locator/atms/";

	@Resource(name = "restTemplate")
	private RestTemplate restTemplate;

	@Resource(name = "gsonConverter")
	private Gson gsonConverter;

	private ArrayList<ATM> listATMs = new ArrayList<ATM>();

	@Override
	public ArrayList<ATM> geAllATMs() {
		if (listATMs.size() == 0) {
			retrieveAllATMs();
		}

		return listATMs;
	}

	/**
	 * Get List of the ATMs within a city
	 */
	@Override
	public ArrayList<ATM> getCityATMs(String searchCity) {
		ArrayList<ATM> searchATMs = new ArrayList<ATM>();

		ArrayList<ATM> allATMs = geAllATMs();
		for (int i = 0; i < allATMs.size(); i++) {
			ATM atm = allATMs.get(i);
			if (atm != null) {
				String city = atm.getAddress().getCity();

				if (searchCity.equalsIgnoreCase(city)) {
					searchATMs.add(atm);
				}
			}
		}

		return searchATMs;
	}

	/**
	 * Retrieves all ATMs from the specified URL
	 * 
	 * @return
	 */
	protected ArrayList<ATM> retrieveAllATMs() {
		String result = restTemplate.getForObject(ING_ATM_URL, String.class);
		return deserializeATMs(result);
	}

	/**
	 * Deserialize JSON to ArrayList of ATM objects Sorts the ArrayList based on
	 * city
	 * 
	 * @param result
	 * @return
	 */
	protected ArrayList<ATM> deserializeATMs(String result) {
		try {
			int start = result.indexOf("[");
			String substring = result.substring(start);

			Type type = new TypeToken<List<ATM>>() {
			}.getType();

			listATMs = gsonConverter.fromJson(substring, type);

			// listATMs.sort((atm1, atm2) ->
			// atm1.getAddress().getCity().compareTo(atm2.getAddress().getCity()));

		} catch (Exception e) {
			log.error(e);
			return new ArrayList<ATM>();
		}

		return listATMs;
	}

	/**
	 * Get Set of All available cities with ATMs
	 */
	@Override
	public HashSet<String> getAllCities() {
		HashSet<String> cities = new HashSet<String>();

		ArrayList<ATM> allATMs = geAllATMs();
		for (int i = 0; i < allATMs.size(); i++) {
			ATM atm = allATMs.get(i);
			if (atm.getAddress() != null) {

				cities.add(atm.getAddress().getCity());
			}

		}

		return cities;
	}
}
