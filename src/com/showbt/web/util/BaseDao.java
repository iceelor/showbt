package com.showbt.web.util;

import java.util.List;

public interface BaseDao<T> {
	public boolean addObj(T obj);
	
	public boolean updateObj(T obj);
	
	public boolean delObj(T obj);
	
	public List<T> getObj(Class<T> cls, String sqlWhere, String sqlOrder, int pageNO, int pageSize);
	
	public T getObj(Class<T> cls,T obj);
}
