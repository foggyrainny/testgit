package com.hzdracom.core.util;

public interface Validatable {

	/**
	 * 校验字段。返回null表示无需校验。
	 * 
	 * @return
	 */
	public ResultValue validate();
}
