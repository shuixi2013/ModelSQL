package com.thang.utils.reflect;

import java.lang.reflect.Field;

import com.thang.utils.lang.StringUtils;
import com.thang.utils.lang.UUIDUtils;

public class ModelUtils {

	/**
	 * 取得对象实例中指定字段的值。
	 * @param model
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("all")
	public static Object getProperty(Object model,String fieldName){
		Object value=null;
		try{
		    value=model.getClass().getDeclaredMethod("get"+StringUtils.headUpper(fieldName),new Class[]{}).invoke(model, new Object[]{});
		    if(null==value){
		    	return "";
		    }
		    return String.valueOf(value);
		}catch(Exception e){
			e.printStackTrace();
		}
		return value==null?"":value;
	}
	
	/**
	 * 设置对象实例中指定字段的值。
	 * @param model
	 * @param fieldName
	 * @param value
	 */
	public static void setProperty(Object model,String fieldName,Class<?> fieldType,Object[] value){
		try{
		    model.getClass().getDeclaredMethod("set"+StringUtils.headUpper(fieldName),new Class[]{fieldType}).invoke(model, value); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isNumber(Field field){
		Class<?> type=field.getType();
		if(int.class==type||long.class==type||double.class==type){
			return true;
		}
		return false;
	}

	public static String getTableName(Class<?> model){
		return model.getAnnotation(com.thang.model.mate.Table.class).value();
	}
	
	public static String getColumnName(Field field){
		if(field.isAnnotationPresent(com.thang.model.mate.Column.class)){
			return field.getAnnotation(com.thang.model.mate.Column.class).value();
		}
		return StringUtils.addUnderline(field.getName());
	}
	
	public static String getFieldName(Class<?> model,String columnName){
		Field[] fields=model.getDeclaredFields();
		for(Field field:fields){
			if(field.isAnnotationPresent(com.thang.model.mate.Column.class)){
				if(columnName.equalsIgnoreCase(field.getAnnotation(com.thang.model.mate.Column.class).value())){
					return field.getName();
				}
				if(field.getName().equalsIgnoreCase(columnName)){
					return field.getName();
				}
				
			}
		}
		return null;
	}
	
	/**
	 * 判断实体的ID值是否有效。
	 * @param model
	 * @return
	 */
	public static boolean idValid(Object model){
		Field fid=getPrimaryKey(model.getClass());
		String id=String.valueOf(getProperty(model, fid.getName())).trim();
		if(null!=id&&!"".equals(id)&&!"0".equals(id)&&!"null".equalsIgnoreCase(id)){
			return true;
		}
		return false;
	}
	
	/**
	 * 默认ID是主键，如要覆盖用primary注解
	 * @param model
	 * @return
	 */
	public static Field getPrimaryKey(Class<?> model){
		Field[] fields=model.getDeclaredFields();
		for(Field field:fields){
			if(field.isAnnotationPresent(com.thang.model.mate.Primary.class)){
				return field;
			}
		}
		for(Field field:fields){
			if("id".equalsIgnoreCase(field.getName())){
				return field;
			}
		}
		return null;
	}
	
	
	/**
	 * 为实体自增一个ID值。如果ID字段类型为字符串则用UUID，如果是整数则用时间
	 * @param model
	 */
	public static void installID(Object model){
		try{
			Field id=getPrimaryKey(model.getClass());
		    if(isNumber(id)){
		    	//如果是数值类型，则不做处理，让其自增长。
			    //setProperty(model, id.getName(),id.getType(), new Object[]{System.currentTimeMillis()});
		    }else{
			    setProperty(model, id.getName(),id.getType(),new Object[]{UUIDUtils.getUUID()});
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
