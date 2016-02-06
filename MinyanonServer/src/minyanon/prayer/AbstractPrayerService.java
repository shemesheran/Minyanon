package minyanon.prayer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import minyanon.RESTToEntityService;

public abstract class AbstractPrayerService<E extends Prayer> extends RESTToEntityService<PrayerREST, E>{

	private final String defaultDateFormat = "dd/MM/yyyy HH:mm";
		
	protected final PrayerDAO<E> prayerDao;
	
	protected DateFormat dateFormatter = new SimpleDateFormat(defaultDateFormat);
	
	public AbstractPrayerService(PrayerDAO<E> prayerDAO) {
		this.prayerDao = prayerDAO;
	}
	
	@Transactional
	public List<PrayerREST> getAllPrayers(List<String> cities, List<String[]> datesRanges, List<String> prayerStyles) throws ParseException {
		//Converting the dates ranges list to type of Date
		List<Date[]> datesRangesDate = datesRangeStrToDate(datesRanges);
		
		//Converting the prayer styles list to type of Prayer.PrayerStyle
		List<Prayer.PrayerStyle> prayerStylesEnum = prayerStylesStrToEnum(prayerStyles);
		
		List<E> prayersList = prayerDao.getAllPrayers(cities, datesRangesDate, prayerStylesEnum);
		
		List<PrayerREST> prayerRESTList = new LinkedList<PrayerREST>();
		for(E prayer : prayersList){
			prayerRESTList.add(new PrayerREST(prayer));
		}
		
		return prayerRESTList;
	}

	public abstract void newPrayer(String cityName, String synagogueName, String dateStr) throws Exception;
		
	private List<Prayer.PrayerStyle> prayerStylesStrToEnum(List<String> prayerStyles) {
		List<Prayer.PrayerStyle> prayerStylesEnum = new LinkedList<Prayer.PrayerStyle>();
		for(String prayerStyleStr : prayerStyles){
			prayerStylesEnum.add(Prayer.PrayerStyle.valueOf(prayerStyleStr));
		}
		return prayerStylesEnum;
	}
	
	private List<Date[]> datesRangeStrToDate(List<String[]> datesRanges) throws ParseException {
		List<Date[]> datesRangesDate = new LinkedList<Date[]>();
		for(String[] dateRangeStr : datesRanges){
			if(dateRangeStr.length != 2){
				// TODO Throw some sort of an Exception
			}
			datesRangesDate.add(new Date[]{dateFormatter.parse(dateRangeStr[0]), dateFormatter.parse(dateRangeStr[1])});
		}
		return datesRangesDate;
	}

}
