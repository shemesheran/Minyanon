package minyanon.synagogue;

import java.util.List;

import minyanon.address.BadAddressException;

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
	public SynagogueREST getSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "city") String city){
		return synagogueService.getSynagogue(synagogueName, city);
	}	
	
	@RequestMapping(method=RequestMethod.PUT)
	public void createSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "address") String address) throws BadAddressException {
		synagogueService.addNewSynagogue(synagogueName, address);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteSynagogue(
			@RequestParam(value = "synagogueName") String synagogueName,
			@RequestParam(value = "city") String city) {
		synagogueService.deleteSynagogue(synagogueName, city);
	}

	@RequestMapping(method=RequestMethod.GET, params = { "latitude", "longtitude", "radius" })
	public List<SynagogueREST> getSynagogue(
			@RequestParam("latitude") double latitude,
			@RequestParam("longtitude") double longtitude,
			@RequestParam("radius") int radius){
		return synagogueService.getSynagoguesInArea(latitude, longtitude, radius);
	}

}
