package com.jingbabyadmin.utils;

import org.json.JSONObject;

/**
 * json返回结果实体
 * @author Administrator
 *
 */
public class ResponseBody {

	private boolean result = true;
	
	private String message = "操作成功";
	
	private Object data;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 返回成功
	 * @return
	 */
	public static String success() {
		ResponseBody responseBody = new ResponseBody();
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
	
	/**
	 * 返回成功
	 * @param message
	 * @return
	 */
	public static String success(String message) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setMessage(message);
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
	
	/**
	 * 返回成功
	 * @param data
	 * @return
	 */
	public static String success(Object data) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setData(data);
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
	
	/**
	 * 返回成功
	 * @param message
	 * @param data
	 * @return
	 */
	public static String success(String message, Object data) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setMessage(message);
		responseBody.setData(data);
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
	
	/**
	 * 返回失败
	 * @return
	 */
	public static String error() {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setResult(false);
		responseBody.setMessage("操作失败");
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
	
	/**
	 * 返回失败
	 * @param message
	 * @return
	 */
	public static String error(String message) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setResult(false);
		responseBody.setMessage(message);
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
	
	/**
	 * 返回失败
	 * @param data
	 * @return
	 */
	public static String error(Object data) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setResult(false);
		responseBody.setMessage("操作失败");
		responseBody.setData(data);
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
	
	/**
	 * 返回失败
	 * @param message
	 * @param data
	 * @return
	 */
	public static String error(String message, Object data) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setResult(false);
		responseBody.setMessage(message);
		responseBody.setData(data);
		JSONObject jsonObject = new JSONObject(responseBody);
		return jsonObject.toString();
	}
}
