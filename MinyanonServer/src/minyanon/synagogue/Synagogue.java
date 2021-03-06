package minyanon.synagogue;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import minyanon.AbstractEntity;
import minyanon.address.Address;
import minyanon.prayer.Prayer;

@Entity
public class Synagogue extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NaturalId
	protected String name;
	
	@Embedded
	protected Address address;

	@OneToMany(mappedBy="synagogue")
	protected Set<Prayer> prayers;
	
	public Synagogue() {}
	
	public Synagogue(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setCity(Address address) {
		this.address = address;
	}

	public Set<Prayer> getPrayer() {
		return prayers;
	}

	public void setPrayer(Set<Prayer> prayers) {
		this.prayers = prayers;
	}
	
	
}
