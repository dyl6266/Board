package com.dy.project.common.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class MyBatisUtils {

	/**
	 * 자료형에 상관없이 오브젝트가 비어있는지 확인한다.
	 * 
	 * @param obj
	 * @return Boolean - (true/false)
	 */
	public static Boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			return obj == null || "".equals(obj.toString().trim());
		} else if (obj instanceof List) {
			return obj == null || ((List<?>) obj).isEmpty();
		} else if (obj instanceof Map) {
			return obj == null || ((Map<?, ?>) obj).isEmpty();
		} else if (obj instanceof Object[]) {
			return obj == null || Array.getLength(obj) == 0;
		} else {
			return obj == null;
		}
	}

}
