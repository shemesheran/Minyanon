package minyanon.synagogue;

import minyanon.AbstractREST;

public class SynagogueREST extends AbstractREST{
	
	public String name;
	public String cityName;
	
	
	public SynagogueREST(String name, String cityName){
		this.name = name;
		this.cityName = cityName;
	}
}
