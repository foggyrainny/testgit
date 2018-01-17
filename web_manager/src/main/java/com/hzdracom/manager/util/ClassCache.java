/**
 *
 * 项目名称：idass_dataService
 * 类名称：ClassCache.java
 * 类描述： 
 * 创建人：panke
 * 创建时间：2016年7月26日 下午5:36:18
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version V1.0
 * 
 */
package com.hzdracom.manager.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;


/**
 *  类缓存， 加快反射的进度
 */
public class ClassCache {

	private static  final Logger log = LoggerFactory.getLogger(ClassCache.class);
	
	private static final Map<Class<?>,Map<String, Field>> fieldMap = new HashMap<Class<?>, Map<String,Field>>();
	
	private static final Map<Class<?>, Map<String, Method>> readMethodsMap =  new HashMap<Class<?>, Map<String,Method>>();
	
	private static final Map<Class<?>, Map<String, Method>> writeMethodsMap =  new HashMap<Class<?>, Map<String,Method>>();
	
	
	
	
	
	
	public static Field[] getField(Class<?> clazz){
		
		if(!fieldMap.containsKey(clazz)) {
			synchronized (clazz) {
				Field[] fields = clazz.getDeclaredFields();
				Map<String, Field> map = new HashMap<String, Field>();
				for (Field field : fields) {
					map.put(field.getName(), field);
				}
				fieldMap.put(clazz,map);
				return fields;
			}
		}
		return fieldMap.get(clazz).values().toArray(new Field[]{});
	}
	
	
	public static Map<String, Field> getFieldAndName(Class<?> clazz){
		if(!fieldMap.containsKey(clazz)) {
			synchronized (clazz) {
				System.out.println(clazz.getName());
				Field[] fields = clazz.getDeclaredFields();
				Map<String, Field> map = new HashMap<String, Field>();
				for (Field field : fields) {
					map.put(field.getName(), field);
				}
				fieldMap.put(clazz,map);
				return map;
			}
		}
		return fieldMap.get(clazz);
	}
	
	public static Object getValue(Object onwer, String fieldName) {
		try {
			Class<?> clazz = onwer.getClass();
			Method getMethod = null;
			Map<String, Method> methodMap = readMethodsMap.get(clazz);
			if (methodMap != null) {
				getMethod = methodMap.get(fieldName);
			}
			
			if (getMethod == null) {
				methodMap = new HashMap<String,Method>();
				BeanInfo beaninfo = Introspector.getBeanInfo(clazz);
				PropertyDescriptor[] porpertydescriptors = beaninfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : porpertydescriptors) {
					if (pd.getName().equals(fieldName)) {
						getMethod = pd.getReadMethod(); // 获得该属性的get方法
						getMethod = getDeclaredMethod(clazz, getMethod.getName());
						methodMap.put(fieldName, getMethod);
						readMethodsMap.put(clazz, methodMap);
						break;
					}
				}
			}
			return getMethod.invoke(onwer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setValue(Object onwer, String fieldName,Object value) {
		try {
			Class<?> clazz = onwer.getClass();
			Method setMethod = null;
			Map<String, Method> methodMap = writeMethodsMap.get(clazz);
			if (methodMap != null) {
				setMethod = methodMap.get(fieldName);
			}else {
				methodMap = new HashMap<String,Method>();
			}
		//	System.out.println(writeMethodsMap);
			if (setMethod == null) {
				BeanInfo beaninfo = Introspector.getBeanInfo(clazz);
				PropertyDescriptor[] porpertydescriptors = beaninfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : porpertydescriptors) {
					if (pd.getName().equals(fieldName)) {
						setMethod = pd.getWriteMethod(); // 获得该属性的set方法
					}
					methodMap.put(fieldName, setMethod);
					writeMethodsMap.put(clazz, methodMap);
				}
			}
			setMethod.invoke(onwer,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setValue(final Object onwer, Field field,String value) {
		
		 // 赋值
		//log.debug("赋值  {}，{}，{}",onwer.getClass().getName(),field.getName(),value);
			if(field.getType() == String.class) {
				ClassCache.setValue(onwer, field.getName(), value);
			} else if(field.getType() == List.class) {
				ClassCache.setValue(onwer, field.getName(), JSON.parseArray(value,getListGenericClass(field)));
			} else  {
				ClassCache.setValue(onwer, field.getName(), JSON.parseObject(value,field.getType()));
			}
	}
	
	
	public static Method getDeclaredMethod(Class<?> clazz, String methodName) {
		Method method = null;
		try {
			for (Class<?> cl = clazz; clazz != Object.class; cl = clazz.getSuperclass()) {
				method = cl.getDeclaredMethod(methodName);
				return method;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return method;
	}
	
	
	public static Class<?> getListGenericClass(Field field){
		 Class<?> clazz = null;  
		Type type = field.getGenericType(); 
	//	clazz =	genericTypeMap.get(type.toString());
		if(clazz == null) {
			if (type instanceof ParameterizedType) {     
				ParameterizedType paramType = (ParameterizedType) type;     
		        Type[] actualTypes = paramType.getActualTypeArguments();     
		        for (Type aType : actualTypes) {         
		            if (aType instanceof Class) {         
		            	   clazz = (Class<?>) aType;
		//            	   genericTypeMap.put(type.toString(), clazz);
		 //           	   return clazz;
		               }     
		           } 
		        }
		}
		return clazz; 
	}
	
	
	public static void main(String[] args) {

		
	/*	setValue(detail, "ratings",new ArrayList<AssetRatings>());

		System.out.println(detail.getRatings());
		
		System.out.println(getValue(detail, "ratings"));;
*/		
	}
	
	
}
