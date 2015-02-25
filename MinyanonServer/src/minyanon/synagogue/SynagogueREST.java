package minyanon.synagogue;

import minyanon.AbstractREST;
import minyanon.address.AddressREST;

public class SynagogueREST extends AbstractREST{
	
	public String name;
	public AddressREST address;

	public SynagogueREST(Synagogue synagogue) {
		this.name = synagogue.getName();
		this.address = new AddressREST(synagogue.getAddress());
	}
}
