package minyanon.address.city;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import minyanon.AbstractEntity;

import org.hibernate.annotations.NaturalId;

@Entity
public class City extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NaturalId
	String name;
	
	@ElementCollection(fetch=FetchType.LAZY)
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
