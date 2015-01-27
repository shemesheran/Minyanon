package minyanon.prayer.mincha;

import java.util.Date;

import javax.transaction.Transactional;

import minyanon.city.City;
import minyanon.prayer.AbstractPrayerService;
import minyanon.prayer.PrayerDAO;
import minyanon.synagogue.Synagogue;

public class MinchaService extends AbstractPrayerService<Mincha>{

	MinchaService(PrayerDAO<Mincha> prayerDAO) {
		super(prayerDAO);
	}

	@Override
	@Transactional
	public void newPrayer(String cityName, String synagogueName, String dateStr)
			throws Exception {
		
		City city = new City(cityName);
		Synagogue synagogue = new Synagogue(synagogueName,city);
		Date date = dateFormatter.parse(dateStr);
		
		prayerDao.registerNew(new Mincha(city, synagogue, date));
	}

}
