package com.autogeneral.agchallenge.bean.validation;

import java.io.Serializable;

/**
 * bean class for no todo item validation Errors
 * @author rajesh
 */
public class ToDoItemValidationError implements Serializable {

	/**
	 * default value for serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
	private String location;
	private String param;
	private String msg;
	private String value;
	
	public String getLocation() {
		return location;
	}
	public ToDoItemValidationError setLocation(String location) {
		this.location = location;
		return this;
	}
	
	public String getParam() {
		return param;
	}
	public ToDoItemValidationError setParam(String param) {
		this.param = param;
		return this;
	}
	
	public String getMsg() {
		return msg;
	}
	public ToDoItemValidationError setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public String getValue() {
		return value;
	}
	public ToDoItemValidationError setValue(String value) {
		this.value = value;
		return this;
	}	
}