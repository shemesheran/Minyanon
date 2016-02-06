package minyanon;

import java.io.Serializable;

import org.hibernate.SessionFactory;

public abstract class GenericDAO<E extends Serializable> {
	
	protected SessionFactory sessionFactory;

	protected final Class<E> type;

	protected final String HQL_NaturalIDWhereClause;
	
	public GenericDAO(Class<E> type, SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.type = type;
		HQL_NaturalIDWhereClause = createNaturalIDWhereClause();
	}

	public void registerNew(E entity) throws EntityNotFoundException {
		sessionFactory.getCurrentSession().persist(entity);
	}
	
	public void delete(E entity) throws EntityNotFoundException {
		sessionFactory.getCurrentSession().createQuery("delete " + sessionFactory.getClassMetadata(type).getEntityName() +" "
				+ HQL_NaturalIDWhereClause)
				.setProperties(getEntityWithAttachedDependencies(entity))
				.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public abstract E getByNaturalIDs(E entity); 	
	/**
	 * Getting an entity and making its dependencies persistent
	 * @param The transient entity
	 * @return The prayer with a persistent dependencies
	 * @throws EntityNotFoundException 
	 */
	protected abstract E getEntityWithAttachedDependencies(E entity) throws EntityNotFoundException;

	/**
	 * Get the HQL where clause composed by the natural ID fields only
	 * @return The HQL natural ID where clause
	 */
	final protected String createNaturalIDWhereClause(){
		StringBuilder HQL_WhereClause  = new StringBuilder("where "); 
		int[] naturalID_Indexes = sessionFactory.getClassMetadata(type)
				.getNaturalIdentifierProperties();
		String[] propsNames = sessionFactory.getClassMetadata(type)
				.getPropertyNames();
		
		int size = naturalID_Indexes.length;
		for (int i = 0; i < naturalID_Indexes.length; i++) {
			String fieldName = propsNames[naturalID_Indexes[i]];
			HQL_WhereClause.append(fieldName);
			HQL_WhereClause.append("=:");
			HQL_WhereClause.append(fieldName);
			if(--size != 0){
				HQL_WhereClause.append(" and ");
			}
		}
		return HQL_WhereClause.toString();
	}
	
}
