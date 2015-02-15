package minyanon.address;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public class AddressUtils {

	private static RestTemplate restTemplate = new RestTemplate();

	public static Address getAddressFromGoogle(String city, String streetName, String streetNumber) {
		JsonNode result = restTemplate
				.getForObject(
						"https://maps.googleapis.com/maps/api/geocode/json?language=iw&address={address}&key={myKey}",
						JsonNode.class, "Sharet 1 kiryat motzkin",
						"AIzaSyCuN-gakCi1sQnCimos09VP1sLyHw9zcFc");
		return new Address(result.findValue(fieldName))
	}

	public static double getAltLong() {
		JsonNode result = restTemplate
				.getForObject(
						"https://maps.googleapis.com/maps/api/elevation/json?locations={latitutd},{longtitude}&key={myKey}",
						JsonNode.class, "32.016553", "34.8369391",
						"AIzaSyCuN-gakCi1sQnCimos09VP1sLyHw9zcFc");
		return result.findValue("elevation").asDouble();
	}

}
