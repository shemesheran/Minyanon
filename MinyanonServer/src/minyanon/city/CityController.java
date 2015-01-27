package minyanon.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {
		
	@Autowired
	CityRESTToEntityService cityService;
	
	@RequestMapping(value = "/getAllCities", method=RequestMethod.GET)
	public List<CityREST> getAll() throws Exception {
		return cityService.getAll(new CityREST(null));
	}
	
	@RequestMapping(value = "/registerNewCity", method=RequestMethod.POST)
	public String createCity(@RequestParam(value = "name") String cityName) {
		cityService.addNewCity(cityName);
		return null;
	}
	
	@RequestMapping(value = "/deleteCity", method=RequestMethod.POST)
	public void deleteCity(@RequestParam(value = "name") String cityName) {
		cityService.deleteCity(cityName);
	}

}
