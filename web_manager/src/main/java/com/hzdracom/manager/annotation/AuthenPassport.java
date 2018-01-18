package com.hzdracom.manager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hzdracom.manager.bean.Option;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthenPassport {
	boolean validate() default true;
	// 操作，  默认是查询
	Option  op()   default Option.QUERY;
}
