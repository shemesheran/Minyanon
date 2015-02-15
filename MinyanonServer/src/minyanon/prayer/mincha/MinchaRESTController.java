package minyanon.prayer.mincha;

import java.util.List;

import minyanon.prayer.PrayerREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
@RequestMapping("/prayer/mincha")
public class MinchaRESTController{
	
	@Autowired
	MinchaService minchaService;
	
	@RequestMapping(value = "/getAllPrayers", method=RequestMethod.GET)
	public List<PrayerREST> getPrayers(
			@RequestParam(value="addresses") List<String> cities,
			@RequestParam(value="fromDate") List<String[]> datesRanges,
			@RequestParam(value="synagogue") List<String> prayerStyles) throws Exception {
		return minchaService.getAllPrayers(cities, datesRanges, prayerStyles);
	}

}
