package minyanon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.web.client.RestTemplate;

import net.sourceforge.zmanim.ComplexZmanimCalendar;
import net.sourceforge.zmanim.util.GeoLocation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DatesTesting {

//	public static void main(String[] args) {
//		Calendar cal = new GregorianCalendar(new Locale("Israel"));
////		cal.getFirstDayOfWeek();
////		cal.set(Calendar.YEAR, 2015);
////		cal.set(Calendar.MONTH, Calendar.MARCH);
////		cal.set(Calendar.DAY_OF_MONTH, 27);
////		cal.set(Calendar.HOUR_OF_DAY, 1);
////		System.out.println(cal.getTime());
////		cal.add(Calendar.HOUR, 1);
////		System.out.println(cal.getTime());
////		cal.add(Calendar.HOUR, -1);
////		System.out.println(cal.getTime());
//		
////		cal.add(Calendar.HOUR, 10);
//
////		JewishDate js = new JewishDate(cal);
////		JewishDate js = new JewishDate();
////		System.out.println(js.getJewishDayOfMonth());
//		
//		
//		String locationName = "Or Yehuda, Israel";
//		double latitude = 32.0311; 
//		double longitude = 34.8458;
//		double elevation = 24; //optional elevation
//		TimeZone timeZone = TimeZone.getTimeZone("Israel");
//		GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
//		ComplexZmanimCalendar czc = new ComplexZmanimCalendar(location);
//		czc.setCalendar(cal);
//
//		System.out.println(czc);
//		
//	}
	
	private static RestTemplate restTemplate = new RestTemplate();

	private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

		  public static JsonNode readJsonFromUrl(String url) throws IOException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      ObjectMapper mapper = new ObjectMapper();
		      JsonNode actualObj = mapper.readTree(jsonText);

		      return actualObj;
		    } finally {
		      is.close();
		    }
		  }
		  
		  
		  public static void getEngName(){
				JsonNode result = restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={myKey}", JsonNode.class, "אריאל שרון אור יהודה", "AIzaSyCuN-gakCi1sQnCimos09VP1sLyHw9zcFc");
//				https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=API_KEY
		  }
		  
		  public static double getAltLong() {
			JsonNode result = restTemplate.getForObject("https://maps.googleapis.com/maps/api/elevation/json?locations={latitutd},{longtitude}&key={myKey}", JsonNode.class, "32.016553", "34.8369391","AIzaSyCuN-gakCi1sQnCimos09VP1sLyHw9zcFc");
			return result.findValue("elevation").asDouble();
		  }
		  
		  public static void main(String[] args) throws IOException  {
//			  System.out.println(getAltLong());
			  getEngName();
		  }

	
}
