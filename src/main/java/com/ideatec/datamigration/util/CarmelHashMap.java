package com.ideatec.datamigration.util;

import java.util.HashMap;

import com.google.common.base.CaseFormat;

/**
 * <pre>
 * com.ideatec.datamigration.util
 * CarmelHashMap.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 14. 오후 2:13:14
 * @desc    :
 * @version : x.x
 */
public class CarmelHashMap extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;

	@Override
		public Object put(String key, Object value) {

			return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key), value);
		}

}
