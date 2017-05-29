var host = location.protocol + '//' + location.hostname
		+ (location.port ? ':' + location.port : '') + '//';
var map = "";
var markers = [];

$(document).ready(function() {
	$.when(ajaxAllCities()).done(function(data) {
		$("#city").autocomplete({
			source : data,
			select : function(event, ui) {

				clearMarkers();

				$.when(ajaxCityATM(ui.item.value)).done(function(data) {
					var lat = parseFloat(data[0].address.geoLocation.lat);
					var lng = parseFloat(data[0].address.geoLocation.lng);

					var center = {
						lat : lat,
						lng : lng
					};

					map.setCenter(center);
					map.setZoom(12);

					$.each(data, function(index, value) {
						addMartker(value);
					});
				});
			}
		});
	});

});

function ajaxAllCities() {
	return $.ajax({
		url : host + "rest/cities"
	})
}

function ajaxCityATM(city) {
	return $.ajax({
		url : host + "rest/atm/" + city
	})
}

function initMap() {
	var netherlands = {
		lat : 52.23,
		lng : 4.55
	};

	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 8,
		center : netherlands
	});
}

function addMartker(value) {
	var lat = parseFloat(value.address.geoLocation.lat);
	var lng = parseFloat(value.address.geoLocation.lng);

	var street = value.address.street;
	var housenumber = value.address.housenumber;
	var postalcode = value.address.postalcode;
	var city = value.address.city;

	var contentString = '<div id="content">' + '<div id="bodyContent">' + '<p>'
			+ street + ' ' + housenumber + '</p>' + '<p>' + postalcode + ' '
			+ city + '</p>' + '<p>NETHERLANDS</p>' + '</div>' + '</div>';

	var infowindow = new google.maps.InfoWindow({
		content : contentString
	});

	var atm = {
		lat : lat,
		lng : lng
	};

	var marker = new google.maps.Marker({
		position : atm,
		map : map,
		title : 'ATM'
	});

	marker.addListener('click', function() {
		infowindow.open(map, marker);
	});

	markers.push(marker);
}

function clearMarkers() {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
}
