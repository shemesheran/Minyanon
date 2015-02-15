package minyanon.address;

import minyanon.AbstractREST;

public class AddressREST extends AbstractREST{
	
	private String name;
	
	public AddressREST(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
