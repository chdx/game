package com.qh.api.constenum;

import java.util.HashMap;
import java.util.Map;

/**
 * 是否
 * @author Swell
 *
 */
public enum YesNoType {
	/**是**/
	yes(1),
	/**否**/
	not(0);

	/**** 描述 ****/
	private static final Map<Integer, String> descMap = new HashMap<>(4);
	static {
		descMap.put(yes.id(), "是");
		descMap.put(not.id(), "否");
	}

	private int id;

	private YesNoType(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}

	public static Map<Integer, String> desc() {
		return descMap;
	}
}
