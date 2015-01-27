package minyanon.prayer.shaharit;

import java.util.Date;

import javax.transaction.Transactional;

import minyanon.city.City;
import minyanon.prayer.AbstractPrayerService;
import minyanon.synagogue.Synagogue;

public class ShaharitService extends AbstractPrayerService<Shaharit>{
		
		ShaharitService(ShaharitDAO prayerDAO) {
			super(prayerDAO);
		}

		@Override
		@Transactional
		public void newPrayer(String cityName, String synagogueName, String dateStr)
				throws Exception {
			
			City city = new City(cityName);
			Synagogue synagogue = new Synagogue(synagogueName,city);
			Date date = dateFormatter.parse(dateStr);
			
			prayerDao.registerNew(new Shaharit(city, synagogue, date));
		}
		
	}
