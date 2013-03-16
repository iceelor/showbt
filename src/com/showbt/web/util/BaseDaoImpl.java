package com.showbt.web.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BaseDaoImpl<T> implements BaseDao<T> {
//	protected PersistenceManager pm = PMF.get().getPersistenceManager();
	private final static String PERSISTENCE_UNIT_NAME ="transactions-optional";
	protected static EntityManagerFactory factory;
	
	@Override
	public boolean addObj(T obj) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public boolean updateObj(T obj) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public boolean delObj(T obj) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getObj(Class<T> cls, String sqlWhere, String sqlOrder, int pageNO, int pageSize) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		String sql = createSql(cls);
		Query query = em.createQuery(sql+" "+sqlWhere+" "+sqlOrder);
		List<T> res = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getObj(Class<T> cls, String sqlWhere, Map<String, Object> values, String sqlOrder, int pageNO, int pageSize) {
		String sql = createSql(cls);
		if(sqlWhere != null){
			sql += " "+sqlWhere;
		}
		if(sqlOrder != null){
			sql += " "+sqlOrder;
		}
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery(sql);
		if(values != null){
			for(Entry<String, Object> entry : values.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		if(pageNO>=0){
			query.setFirstResult(pageNO);
		}
		if(pageSize>0){
			query.setMaxResults(pageSize);
		}
		List<T> res = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return res;
	}

	@Override
	public T getObj(Class<T> cls, Object obj) {
		T res = null;
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		res = em.find(cls, obj);
		em.getTransaction().commit();
		em.close();
		return res;
	}

	@Override
	public List<T> getObj(Query query) {
		return null;
	}

	private String createSql(Class<?> cls){
		TableName tn = TableName.getIntance();
		return "select "+tn.getAlias(cls)+" from "+tn.getTableName(cls) +" "+tn.getAlias(cls);
	}
}
