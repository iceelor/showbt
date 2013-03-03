package com.showbt.web.util;

import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class BaseDaoImpl<T> implements BaseDao<T> {
	protected PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public boolean addObj(T obj) {
		try {
			pm.makePersistent(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateObj(T obj) {
		try {
			pm.makePersistent(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delObj(T obj) {
		try {
			pm.deletePersistent(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<T> getObj(Class<T> cls, String sqlWhere, String sqlOrder, int pageNO, int pageSize) {
		Query query = pm.newQuery(cls,sqlWhere+" "+sqlOrder);
		query.setRange(pageNO, pageSize);
		return (List<T>) query.execute();
	}

	@Override
	public T getObj(Class<T> cls, T obj) {
		return pm.getObjectById(cls,obj);
	}

}
