package com.backbase.atm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.backbase.atm.model.ATM;

import javassist.bytecode.stackmap.TypeData.ClassName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-controller-config.xml" })
public class ATMServiceImplTest {

	private static final Logger log = Logger.getLogger(ClassName.class.getName());

	@Resource(name = "atmService")
	private ATMServiceImpl atmServiceImpl;

	@Test
	public void getAllATMs() throws Exception {
		ArrayList<ATM> listATMs = atmServiceImpl.geAllATMs();

		Assert.assertEquals(1538, listATMs.size());
	}

	@Test
	public void deserializeATMs_EmptyString() throws Exception {
		String result = "";
		ArrayList<ATM> deserializedATMs = atmServiceImpl.deserializeATMs(result);
		Assert.assertEquals(0, deserializedATMs.size());
	}

	@Test
	public void deserializeATMs_WrongJSON() throws Exception {
		String result = ")]}', [{\"address\":{\"str";
		ArrayList<ATM> deserializedATMs = atmServiceImpl.deserializeATMs(result);
		Assert.assertEquals(0, deserializedATMs.size());
	}

	@Test
	public void deserializeATMs_OneATM() throws Exception {
		String result = "[{\"address\":{\"street\":\"Hoofdstraat\",\"housenumber\":\"37\",\"postalcode\":\"1777 CA\",\"city\":\"Hippolytushoef\",\"geoLocation\":{\"lat\":\"52.906006\",\"lng\":\"4.961531\"}},\"distance\":0,\"type\":\"ING\"}]";
		ArrayList<ATM> deserializedATMs = atmServiceImpl.deserializeATMs(result);
		Assert.assertEquals(1, deserializedATMs.size());
	}

	@Test
	public void getCityATMs_Warnsveld() throws Exception {
		ArrayList<ATM> cityATMs = atmServiceImpl.getCityATMs("Warnsveld");
		Assert.assertEquals(1, cityATMs.size());
	}

	@Test
	public void getCityATMs_Rotterdam() throws Exception {
		ArrayList<ATM> cityATMs = atmServiceImpl.getCityATMs("Rotterdam");
		Assert.assertEquals(63, cityATMs.size());
	}

	@Test
	public void getAllCities() throws Exception {
		HashSet<String> allCities = atmServiceImpl.getAllCities();
		Assert.assertEquals(480, allCities.size());
	}
}
