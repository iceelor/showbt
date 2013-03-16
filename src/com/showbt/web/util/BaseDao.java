package com.showbt.web.util;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

public interface BaseDao<T> {
	public boolean addObj(T obj);
	
	public boolean updateObj(T obj);
	
	public boolean delObj(T obj);
	
	public List<T> getObj(Class<T> cls, String sqlWhere, String sqlOrder, int pageNO, int pageSize);
	
	/**
	 * "select m from Movie m where m.id=:id and m.name like :name"
	 * @param cls
	 * @param sqlWhere
	 * @param values
	 * @return
	 */
	public List<T> getObj(Class<T> cls, String sqlWhere, Map<String, Object> values ,String sqlOrder, int pageNO, int pageSize);
	
	public List<T> getObj(Query query);
	
	public T getObj(Class<T> cls,Object obj);
}
