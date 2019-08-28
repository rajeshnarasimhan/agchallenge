package com.autogeneral.agchallenge.bean.validation;

import java.io.Serializable;

/**
 * bean class for no item found Errors
 * @author rajesh
 */
public class ToDoItemNotFoundError implements Serializable {

	/**
	 * default value for serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
    private String message;

	public String getMessage() {
		return message;
	}

	public ToDoItemNotFoundError setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public ToDoItemNotFoundError setIdInMessage(Long id) {
		this.message = String.format("Item with id %s not found", id);
		return this;
	}
}