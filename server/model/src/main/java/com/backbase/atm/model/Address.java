package com.backbase.atm.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "street", "housenumber", "postalcode", "city", "geoLocation" })
public class Address {

	@JsonProperty("street")
	private String street;
	@JsonProperty("housenumber")
	private String housenumber;
	@JsonProperty("postalcode")
	private String postalcode;
	@JsonProperty("city")
	private String city;
	@JsonProperty("geoLocation")
	private GeoLocation geoLocation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("street")
	public String getStreet() {
		return street;
	}

	@JsonProperty("street")
	public void setStreet(String street) {
		this.street = street;
	}

	@JsonProperty("housenumber")
	public String getHousenumber() {
		return housenumber;
	}

	@JsonProperty("housenumber")
	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	@JsonProperty("postalcode")
	public String getPostalcode() {
		return postalcode;
	}

	@JsonProperty("postalcode")
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("geoLocation")
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	@JsonProperty("geoLocation")
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}