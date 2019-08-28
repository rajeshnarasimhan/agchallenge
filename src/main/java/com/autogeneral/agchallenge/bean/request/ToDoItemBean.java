package com.autogeneral.agchallenge.bean.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * domain class for ToDoItem 
 * @author rajesh
 */
public class ToDoItemBean implements Serializable {

	/**
	 * default value for serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Must not be Null or Empty")
	@Size(min = 1, max = 50, message = "Must be between 1 and 50 chars long")
	private String text;
	private boolean isCompleted;
	
	public String getText() {
		return text;
	}
	public ToDoItemBean setText(String text) {
		this.text = text;
		return this;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}
	public ToDoItemBean setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
		return this;
	}
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}