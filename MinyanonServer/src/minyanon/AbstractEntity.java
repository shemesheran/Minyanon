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

}
