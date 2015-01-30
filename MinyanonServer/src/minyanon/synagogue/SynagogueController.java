package minyanon.synagogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synagogues")
public class SynagogueController {
		
	@Autowired
	SynagogueRESTToEntityService synagogueService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<SynagogueREST> getAllSynagogues(
			@RequestParam(value = "citiesNamesList") List<String> citiesNamesList) throws Exception {
		return synagogueService.getAllSynagogues(citiesNamesList);
	}
	
	@RequestMapping(value = "/registerNewSynagogue", method=RequestMethod.POST)
	public String createSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "cityName") String cityName) {
		synagogueService.addNewSynagogue(synagogueName, cityName);
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void deleteSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "cityName") String cityName) {
		synagogueService.deleteSynagogue(synagogueName, cityName);
	}

}
