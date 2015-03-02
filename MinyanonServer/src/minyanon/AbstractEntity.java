package minyanon;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity{
		
	@Id
	@GeneratedValue(strategy = IDENTITY) 
	protected long id;
	
	
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

}
