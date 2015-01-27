package minyanon.prayer;

import java.util.LinkedList;
import java.util.List;

import minyanon.GenericRESTController;
import minyanon.prayer.arvit.ArvitService;
import minyanon.prayer.mincha.MinchaService;
import minyanon.prayer.shaharit.ShaharitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/prayers")
public class PrayerRESTController extends GenericRESTController<PrayerREST>{
		
	@Autowired
	ShaharitService shaharitService;
	
	@Autowired
	MinchaService minchaService;

	@Autowired
	ArvitService arvitService;

	@RequestMapping(value = "/getAllPrayers", method=RequestMethod.GET)
	public List<PrayerREST> getPrayers(
			@RequestParam(value="prayerType", defaultValue="") List<String> prayerTypes,
			@RequestParam(value="city", defaultValue="") List<String> cities,
			@RequestParam(value="datesRanges", defaultValue="") List<String[]> datesRanges,
			@RequestParam(value="synagogue", defaultValue="") List<String> prayerStyles) throws Exception {
		List<PrayerREST> list = new LinkedList<PrayerREST>();
		for(AbstractPrayerService<? extends Prayer> service : returnSpecificServices(prayerTypes)){
		    list.addAll(service.getAllPrayers(cities, datesRanges, prayerStyles));
		}
		return list;
	}
	
	@RequestMapping(value = "/addPrayer", method=RequestMethod.GET)
	public void addPrayer(
			@RequestParam(value="prayerType",required=true)String prayerType,
			@RequestParam(value="cityName",required=true)String cityName,
			@RequestParam(value="synagogueName",required=true)String synagogueName,
			@RequestParam(value="date",required=true)String date) throws Exception {
		returnPrayerService(prayerType).newPrayer(cityName, synagogueName, date);
	}
		
	@RequestMapping(value = "/deletePrayers", method=RequestMethod.GET)
	public void deletePrayers(
			@RequestParam(value="prayerType",required=true)String prayerType,
			@RequestParam(value="city",required=true)String city,
			@RequestParam(value="synagogue",required=true)String synagogue,
			@RequestParam(value="fromDate",required=true)String fromDate,
			@RequestParam(value="toDate",required=true)String toDate) throws Exception {
	}
	
	private List<AbstractPrayerService<? extends Prayer>> returnSpecificServices(List<String> prayerTypes) throws Exception{
		List<AbstractPrayerService<? extends Prayer>> servicesList = new LinkedList<AbstractPrayerService<? extends Prayer>>();
		if(prayerTypes.isEmpty()){
			servicesList.add(shaharitService);
			servicesList.add(minchaService);
			servicesList.add(arvitService);
		}
		else if(prayerTypes.contains(Prayer.PrayerType.SHAHRIT.toString())){
			servicesList.add(shaharitService);
		}
		else if(prayerTypes.contains(Prayer.PrayerType.MINCHA.toString())){
			servicesList.add(minchaService);
		}
		else if(prayerTypes.contains(Prayer.PrayerType.ARVIT.toString())){
			servicesList.add(arvitService);
		}
		return servicesList;

	}
	
	private AbstractPrayerService<? extends Prayer> returnPrayerService(String prayerType) throws Exception{
		if(prayerType.equals(Prayer.PrayerType.SHAHRIT.toString())){
			return shaharitService;
		} else if(prayerType.equals(Prayer.PrayerType.MINCHA.toString())){
			return minchaService;
		} else if(prayerType.equals(Prayer.PrayerType.ARVIT.toString())){
			return arvitService;
		}
		else
			throw new Exception();
	}
	
}
