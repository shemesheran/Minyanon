package minyanon.address.city;

import java.util.List;

import minyanon.synagogue.SynagogueREST;
import minyanon.synagogue.SynagogueRESTToEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{city}")
public class CityRestController {
	
	@Autowired
	SynagogueRESTToEntityService synagogueService;

	@RequestMapping(value = "/synagogues", method=RequestMethod.GET)
	public List<SynagogueREST> getAllSynagogues(
			@PathVariable("city") String cityName) throws Exception {
		return synagogueService.getAllSynagogues(cityName);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
