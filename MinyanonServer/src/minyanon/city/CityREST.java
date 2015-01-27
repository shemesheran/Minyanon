package minyanon.city;

import minyanon.AbstractREST;

public class CityREST extends AbstractREST{
	
	private String name;
	
	public CityREST(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
