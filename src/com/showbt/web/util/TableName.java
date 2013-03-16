package com.showbt.web.util;

import java.util.HashMap;
import java.util.Map;

public class TableName {
	
	private static Map<String, String> TableAlias;
	private static TableName tabName;
	
	private TableName(){
		TableAlias = new HashMap<String, String>();
		TableAlias.put("Movie", "m");
		TableAlias.put("Photo", "p");
		TableAlias.put("UserInfo", "u");
	}
	
	public static TableName getIntance(){
		 if(tabName == null){
			 return new TableName();
		 }
		 return tabName;
	}
	public String getTableName(Class<?> cls){
		String className = cls.getName();
		className = className.substring(className.lastIndexOf('.')+1,className.length());
		return className;
	}
	
	public String getAlias(Class<?> cls){
		return TableAlias.get(getTableName(cls));
	}
}
