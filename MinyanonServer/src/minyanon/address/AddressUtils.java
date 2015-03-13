package minyanon.address;

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
		if(resultFromGoogle.get("status").asText().equals("ZERO_RESULTS")){
			return null;
		}
		
		JsonNode addressComponent = resultFromGoogle.get("results").get(0).get("address_components");
		JsonNode geometryComponent = resultFromGoogle.get("results").get(0).get("geometry");
		String streetNumber = null;
		String streetName = null;
		String cityName = null;
		for(JsonNode addressType : addressComponent){
			if(addressType.get("types").get(0).asText().equals("street_number")){
				streetNumber = addressType.get("long_name").toString();
				continue;
			}
			else if(addressType.get("types").get(0).asText().equals("route")){
				streetName = addressType.get("long_name").toString();
				continue;

			}
			else if(addressType.get("types").get(0).asText().equals("locality")){
				cityName = addressType.get("long_name").toString();
				continue;
			}
		}
		
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
