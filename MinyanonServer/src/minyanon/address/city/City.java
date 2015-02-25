package minyanon.address.city;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import org.hibernate.annotations.NaturalId;

@Embeddable
public class City {
	
	@NaturalId
	String name;
	
	@ElementCollection
	@CollectionTable(name="Cities_Aliases")
	Set<String> cityAliases;
	
	public City() {}
	
	public City(String cityName) {
		this.name = cityName;
		cityAliases = new HashSet<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addCityAlias(String cityAlias){
		this.cityAliases.add(cityAlias);
	}

}
