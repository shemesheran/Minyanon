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
	
	
	@RequestMapping(method=RequestMethod.PUT)
	public void createSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "address") String address) {
		synagogueService.addNewSynagogue(synagogueName, address);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public SynagogueREST getSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "city") String city){
		return synagogueService.getSynagogue(synagogueName, city);
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public List<SynagogueREST> getSynagogue(
//			@RequestParam(value = "location") int location,
//			@RequestParam(value = "radius") int raduis){
//		return synagogueService.getSynagoguesInArea(location, raduis);
//	}

	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "city") String city) {
		synagogueService.deleteSynagogue(synagogueName, city);
	}

}
