package minyanon.address;

import java.util.Iterator;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public class AddressUtils {

	private static final String GOOGLE_KEY = "AIzaSyCuN-gakCi1sQnCimos09VP1sLyHw9zcFc";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public static Address getAddressFromGoogle(String addressStr) {
		JsonNode result = restTemplate
				.getForObject(
						"https://maps.googleapis.com/maps/api/geocode/json?language=iw&address={address}&key={myKey}",
						JsonNode.class, addressStr,
						GOOGLE_KEY);

		return createNewAddress(result);
	}

	private static Address createNewAddress(JsonNode resultFromGoogle) {
		//TODO Check if the results were empty
		
		JsonNode addressComponent = resultFromGoogle.get("results").get(0).get("address_components");
		JsonNode geometryComponent = resultFromGoogle.get("results").get(0).get("geometry");

		String cityName = addressComponent.get(2).get("long_name").toString();
		String streetName = addressComponent.get(1).get("long_name").toString();
		String streetNumber = addressComponent.get(0).get("long_name").toString();
		double latitude = geometryComponent.findValue("lat").asDouble();
		double longtitude = geometryComponent.findValue("lng").asDouble();
		return new Address(cityName,
				streetName,
				streetNumber,
				latitude,
				longtitude);
	}

	public static double getAltLong() {
		JsonNode result = restTemplate
				.getForObject(
						"https://maps.googleapis.com/maps/api/elevation/json?locations={latitutd},{longtitude}&key={myKey}",
						JsonNode.class, "32.016553", "34.8369391",
						GOOGLE_KEY);
		return result.findValue("elevation").asDouble();
	}

}
