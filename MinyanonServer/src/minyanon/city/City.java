package minyanon.city;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import minyanon.AbstractEntity;
import minyanon.prayer.Prayer;
import minyanon.synagogue.Synagogue;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class City extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@NaturalId
	private String name;
	
	@OneToMany(mappedBy="city", cascade=CascadeType.ALL, orphanRemoval=true)
	Set<Synagogue> synagogues;
	
	@OneToMany(mappedBy="city")
	Set<Prayer> prayer;
	
	@NaturalId
	public String g;
	
	public City(){}
	
	public City(String name) {
		this.name = name;
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
	
	public Set<Synagogue> getSynagogues() {
		return synagogues;
	}
	
	public void setSynagogues(Set<Synagogue> synagogues) {
		this.synagogues = synagogues;
	}
	
	public Set<Prayer> getPrayer() {
		return prayer;
	}
	
	public void setPrayer(Set<Prayer> prayer) {
		this.prayer = prayer;
	}
	
}
