package com.matio.unit;


import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;


public class JsonUtil {

	/**
	 * @note 把错误码转换成json对象
	 * @author sxy
	 *            *
	 * @return JSONObject
	 * @date 2016年4月28日
	 */
	public static JSONObject fromErrors(String err) {

		JSONObject json = new JSONObject();

		if (Errors.SUCCESS.equals(err)) {
			json.put(Keys.CODE, "1");
		} else {
			json.put(Keys.CODE, "0");
		}

		return json;
	}

	/**
	 * @note 把错误码转换成json字符串
	 * @author sxy
	 *            *
	 * @return String
	 * @date 2016年4月28日
	 */
	public static String fromErrorsToString(String err) {
		return fromErrors(err).toString();
	}

}
