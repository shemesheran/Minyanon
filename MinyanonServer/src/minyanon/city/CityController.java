package minyanon.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {
		
	@Autowired
	CityRESTToEntityService cityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CityREST> getAll() throws Exception {
		return cityService.getAll(new CityREST(null));
	}
	
	@RequestMapping(value="/{cityName}", method=RequestMethod.PUT)
	public String createCity(@PathVariable String cityName) {
		cityService.addNewCity(cityName);
		return null;
	}
	
	@RequestMapping(value="/{cityName}", method=RequestMethod.DELETE)
	public void deleteCity(@PathVariable String cityName) {
		cityService.deleteCity(cityName);
	}

}
